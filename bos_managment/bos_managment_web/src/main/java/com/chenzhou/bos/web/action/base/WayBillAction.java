package com.chenzhou.bos.web.action.base;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chenzhou.bos.bean.take_delivery.WayBill;
import com.chenzhou.bos.service.action.base.WayBillService;
import com.chenzhou.bos.web.action.common.CommonAction;
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class WayBillAction extends CommonAction<WayBill> {
	private static final long serialVersionUID = 1L;

	public WayBillAction() {
		super(WayBill.class);
	}
	
	@Resource
	public WayBillService wayBillService;

	@Action("wayBillAction_save")
	public String save() throws IOException {
		String flag="0";
		try{
			wayBillService.save(model);
		} catch(Exception e){
			flag = "1";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(flag);
		return NONE;
	}
}
