package com.chenzhou.bos.web.action.system;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.chenzhou.bos.bean.system.Role;
import com.chenzhou.bos.service.action.system.RoleService;
import com.chenzhou.bos.web.action.common.CommonAction;
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class RoleAction extends CommonAction<Role> {
	private static final long serialVersionUID = -3996577405934378295L;

	public RoleAction() {
		super(Role.class);
	}
	
	@Resource
	private RoleService roleService;
	
	private String menuIds;
	private Integer[] permissionIds;
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	public void setPermissionIds(Integer[] permissionIds) {
		this.permissionIds = permissionIds;
	}

	@Action("roleAction_findByPage")
	public String findByPage() throws IOException {
		Pageable pageable = new PageRequest(page-1, rows);
		Page<Role> page = roleService.findAll(pageable);
		page2Json(page, new String[]{"users","permissions","menus"});
		return NONE;
	}
	
	@Action(value="roleAction_save",results={@Result(name="success",location="/pages/system/role.html",type="redirect")})
	public String save() {
		roleService.save(model,menuIds,permissionIds);
		return SUCCESS;
	}
	
	@Action("roleAction_findAll")
	public String findAll() throws IOException{
		List<Role> list = roleService.findAll();
		list2Json(list, new String[]{"users","permissions","menus"});
		return NONE;
	}
	
}

















