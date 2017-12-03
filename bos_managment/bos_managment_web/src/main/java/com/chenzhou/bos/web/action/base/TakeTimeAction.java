package com.chenzhou.bos.web.action.base;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chenzhou.bos.bean.base.TakeTime;
import com.chenzhou.bos.service.action.base.TakeTimeService;
import com.chenzhou.bos.web.action.common.CommonAction;

@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class TakeTimeAction extends CommonAction<TakeTime> {
	private static final long serialVersionUID = 1L;

	public TakeTimeAction() {
		super(TakeTime.class);
	}

	@Resource
	private TakeTimeService takeTimeService;
	
	@Action("takeTimeAction_listAjax")
	public String listAjax() throws IOException {
		List<TakeTime> list = takeTimeService.findAll();
		list2Json(list, new String[]{""});
		return NONE;
	}
}
