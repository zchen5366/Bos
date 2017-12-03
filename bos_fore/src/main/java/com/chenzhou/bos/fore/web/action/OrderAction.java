package com.chenzhou.bos.fore.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chenzhou.bos.bean.base.Area;
import com.chenzhou.bos.bean.take_delivery.Order;
import com.chenzhou.bos.service.action.base.impl.OrderService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	private static final long serialVersionUID = 1L;
	
	private Order model = new Order();
	
	private String sendAreaInfo;
	private String recAreaInfo;
	public void setSendAreaInfo(String sendAreaInfo) {
		this.sendAreaInfo = sendAreaInfo;
	}
	public void setRecAreaInfo(String recAreaInfo) {
		this.recAreaInfo = recAreaInfo;
	}
	@Resource
	private OrderService orderService;
	
	@Action(value="orderAction_add")
	public String save() {
		if(StringUtils.isNotEmpty(sendAreaInfo)) {
			String[] split = sendAreaInfo.split("/");
			String province = split[0];
			String city = split[1];
			String district = split[2];
			Area area = new Area();
			area.setProvince(province);
			area.setCity(city);
			area.setDistrict(district);
			model.setSendArea(area);
		}
		if(StringUtils.isNotEmpty(recAreaInfo)) {
			String[] split = recAreaInfo.split("/");
			String province = split[0];
			String city = split[1];
			String district = split[2];
			Area area = new Area();
			area.setProvince(province);
			area.setCity(city);
			area.setDistrict(district);
			model.setRecArea(area);
		}
		orderService.save(model);
		return NONE;
	}

	@Override
	public Order getModel() {
		return model;
	}

}
