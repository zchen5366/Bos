package com.chenzhou.bos.web.action.system;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.chenzhou.bos.bean.system.Menu;
import com.chenzhou.bos.bean.system.User;
import com.chenzhou.bos.service.action.system.MenuService;
import com.chenzhou.bos.web.action.common.CommonAction;
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class MenuAction extends CommonAction<Menu> {
	private static final long serialVersionUID = 1L;

	public MenuAction() {
		super(Menu.class);
	}

	@Resource
	private MenuService menuService;
	
	@Action(value="menuAction_findAll")
	public String findAll() throws IOException {
		List<Menu> list = menuService.findAll();
		list2Json(list, new String[]{"roles","childrenMenus","parentMenu"});
		return NONE;
	}
	
	@Action(value="menuAction_save",results={@Result(name="success",location="/pages/system/menu.html",type="redirect")})
	public String save() {
		menuService.save(model);
		return SUCCESS;
	}
	
	@Action(value="menuAction_findByPage")
	public String findByPage() throws IOException {
		String page2 = model.getPage();
		page = Integer.parseInt(page2);
		Pageable pageable = new PageRequest(page-1, rows);
		Page<Menu> page = menuService.findAll(pageable);
		page2Json(page, new String[]{"roles","childrenMenus","parentMenu"});
		return NONE;
	}
	
	@Action("menuAction_findByUser")
	public String findByUser() throws IOException {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		List<Menu> list = menuService.findByUser(user);
		list2Json(list, new String[]{"roles","childrenMenus","parentMenu"});
		return NONE;
	}
	
	
}
