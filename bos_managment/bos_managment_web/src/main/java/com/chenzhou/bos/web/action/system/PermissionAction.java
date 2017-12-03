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

import com.chenzhou.bos.bean.system.Permission;
import com.chenzhou.bos.service.action.system.PermissionService;
import com.chenzhou.bos.web.action.common.CommonAction;

@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class PermissionAction extends CommonAction<Permission> {
	private static final long serialVersionUID = 1L;

	public PermissionAction() {
		super(Permission.class);
	}

	@Resource
	private PermissionService permissionService;
	
	@Action(value="permissionAction_findByPage")
	public String findByPage() throws IOException {
		Pageable pageable = new PageRequest(page-1,rows);
		Page<Permission> page = permissionService.findAll(pageable);
		page2Json(page, new String[]{"roles"});
		return NONE;
	}
	
	@Action(value="permissionAction_save",results={@Result(name="success",location="/pages/system/permission.html",type="redirect")})
	public String save() {
		permissionService.save(model);
		return SUCCESS;
	}
	
	@Action("permissionAction_findAll")
	public String findAll() throws IOException {
		List<Permission> list = permissionService.findAll();
		list2Json(list, new String[]{"roles"});
		return NONE;
	}
	
}
