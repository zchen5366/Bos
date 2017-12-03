package com.chenzhou.bos.web.action.base;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.chenzhou.bos.bean.base.Standard;
import com.chenzhou.bos.service.action.base.StandardService;
import com.chenzhou.bos.web.action.common.CommonAction;

import net.sf.json.JSONArray;

@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class StandardAction extends CommonAction<Standard> {
	private static final long serialVersionUID = 1L;
	
	public StandardAction() {
		super(Standard.class);
	}

	
//	private Standard standard = new Standard();
	
	@Resource(name="standardService")
	private StandardService standardService;
	
	/*private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}*/
	
	@Action(value = "standardAction_save", results = {
            @Result(name = "success", location = "/pages/base/standard.html", type = "redirect")})
	public String save() {
		standardService.save(model);
		return SUCCESS;
	}
	
	@Action("standardAction_findByPage")
	public String findByPage() throws IOException {
		Pageable pageable = new PageRequest(page-1, rows);
		Page<Standard> page = standardService.findByPage(pageable);
		/*//获得总页数
		long totalElements = page.getTotalElements();
		List<Standard> list = page.getContent();
		Map<String,Object> map = new HashMap<>();
		map.put("total", totalElements);
		map.put("rows", list);
		String json = JSONObject.fromObject(map).toString();
		//设置中文乱码
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);*/
		page2Json(page,new String[]{""});
		return NONE;
	}
	
	@Action("standardAction_findAll")
	public String findAll() throws IOException {
		List<Standard> list = standardService.findAll();
		String json = JSONArray.fromObject(list).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
		return NONE;
	}
	
	
	/*@Override
	public Standard getModel() {
		return standard;
	}*/
}
