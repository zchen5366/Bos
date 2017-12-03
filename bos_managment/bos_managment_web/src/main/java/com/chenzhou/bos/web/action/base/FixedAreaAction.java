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

import com.chenzhou.bos.bean.base.FixedArea;
import com.chenzhou.bos.service.action.base.FixedAreaService;
import com.chenzhou.bos.web.action.common.CommonAction;
import com.chenzhou.crm.service.Customer;
import com.chenzhou.crm.service.impl.CustomerService;

import net.sf.json.JSONArray;

@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class FixedAreaAction extends CommonAction<FixedArea> {
	private static final long serialVersionUID = 1L;

	public FixedAreaAction() {
		super(FixedArea.class);
	}
	
	@Resource(name="fixedAreaService")
	private FixedAreaService fixedAreaService;
	
	@Action(value="fixedAreaAction_save",results={@Result(name="success",location="pages/base/fixed_area.html",type="redirect")})
	public String save() {
		fixedAreaService.save(model);
		return SUCCESS;
	}
	
	@Action("fixedAreaAction_findByPage")
	public String findByPage() throws IOException {
		Pageable pageable = new PageRequest(page-1, rows);
		Page<FixedArea> page = fixedAreaService.findAll(pageable);
		page2Json(page, new String[]{"subareas","couriers"});
		return NONE;
	}
	
	@Resource(name="crmService")
	private CustomerService customerService;
	
//	fixedAreaAction_findByFixedAreaIdIsNull
	@Action(value="fixedAreaAction_findByFixedAreaIdIsNull")
	public String findByFixedAreaIdIsNull() throws IOException {
		List<Customer> list = customerService.findByFixedAreaIdIsNull();
		String json = JSONArray.fromObject(list).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
		return NONE;
	}
	
	//fixedAreaAction_findCustomerByFixedAreaId
	@Action("fixedAreaAction_findCustomerByFixedAreaId")
	public String findCustomerByFixedAreaId() throws IOException {
		List<Customer> list = customerService.findByFixedAreaId(model.getId());
		String json = JSONArray.fromObject(list).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
		return NONE;
	}
	
	private List<Integer> customerIds;
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
	
	//fixedAreaAction_assignCustomers2FixedArea
	@Action(value="fixedAreaAction_assignCustomers2FixedArea",results={@Result(name="success",location="pages/base/fixed_area.html",type="redirect")})
	public String assignCustomers2FixedArea() {
		customerService.assignCustomers2FixedArea(customerIds,model.getId());
		return SUCCESS;
	}
	
	private Integer courierId;
	private Integer takeTimeId;
	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}
	public void setTakeTimeId(Integer takeTimeId) {
		this.takeTimeId = takeTimeId;
	}
	
	//fixedAreaAction_associationCourierToFixedArea关联定区快递员和时间
	@Action(value="fixedAreaAction_associationCourierToFixedArea",results={@Result(name="success",location="pages/base/fixed_area.html",type="redirect")})
	public String associationCourierToFixedArea() {
		fixedAreaService.associationCourierToFixedArea(model.getId(),courierId,takeTimeId);
		return SUCCESS;
	}
	
	private List<String> subAreaIds;
	public void setSubAreaIds(List<String> subAreaIds) {
		this.subAreaIds = subAreaIds;
	}
	
	//fixedAreaAction_assignSubArea2FixedArea
	@Action(value="fixedAreaAction_assignSubArea2FixedArea",results={@Result(name="success",location="pages/base/fixed_area.html",type="redirect")})
	public String assignSubArea2FixedArea() {
		System.out.println(subAreaIds+"---"+model.getId());
		fixedAreaService.assignSubArea2FixedArea(subAreaIds,model.getId());
		return SUCCESS;
	}

}
















