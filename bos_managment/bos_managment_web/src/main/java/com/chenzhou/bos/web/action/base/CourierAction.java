package com.chenzhou.bos.web.action.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.chenzhou.bos.bean.base.Courier;
import com.chenzhou.bos.bean.base.Standard;
import com.chenzhou.bos.service.action.base.CourierService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import aj.org.objectweb.asm.Type;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class CourierAction extends ActionSupport implements ModelDriven<Courier> {
	private static final long serialVersionUID = 1L;
	
	private Courier courier = new Courier();
	@Resource(name="courierService")
	private CourierService courierService;
	
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	@Action(value="courierAction_save",results={@Result(name="success",location="pages/base/courier.html",type="redirect")})
	public String save() {
		courierService.save(courier);
		return SUCCESS;
	}
	
	@Action("courierAction_findByPage")
	public String findByPage() throws IOException {
		String courierNum = courier.getCourierNum();
		Standard standard = courier.getStandard();
		String company = courier.getCompany();
		String type = courier.getType();
		ArrayList<Predicate> list2 = new ArrayList<Predicate>();
		Specification<Courier> specification = new Specification<Courier>() {
			//root是实体类，query查询对象，cb构造查询条件的对象
			@Override
			public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(StringUtils.isNotEmpty(courierNum)) {
					Predicate p1 = cb.equal(root.get("courierNum").as(String.class), courierNum);
					list2.add(p1);
				}
				if(standard!=null && StringUtils.isNotEmpty(standard.getName())) {
					//根据收派标准的名字进行级联查询
					Join<Object, Object> join = root.join("standard");
					Predicate p2 = cb.equal(join.get("name").as(String.class), standard.getName());
					list2.add(p2);
				}
				if(StringUtils.isNotEmpty(company)) {
					Predicate p3 = cb.equal(root.get("company").as(String.class), company);
					list2.add(p3);
				}
				if(StringUtils.isNotEmpty(type)) {
					Predicate p4 = cb.equal(root.get("type").as(Type.class), type);
					list2.add(p4);
				}
				Predicate[] predicate = new Predicate[list2.size()];
				Predicate[] array = list2.toArray(predicate);
				if(list2.size()==0) {
					return null;
				}
				return cb.and(array);
			}
		};
		
		
		Pageable pageable = new PageRequest(page-1, rows);
		Page<Courier> page = courierService.findByPage(specification,pageable);
		List<Courier> list = page.getContent();
		//判断是否作废，如果不作废才加入list集合中并咋页面展示出来
		List<Courier> newlist = new ArrayList<Courier>();
		for (Courier courier : list) {
//			System.out.println(courier.getDeltag().getClass());
			if(courier.getDeltag()==null || courier.getDeltag().charValue()!='1') {
				newlist.add(courier);
			}
		}
//		System.out.println(newlist);
		long totalElements = page.getTotalElements();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", totalElements);
		map.put("rows", newlist);
		//懒加载，让takeTime和fixedAreas变成瞬时态
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"takeTime","fixedAreas"});
		String json = JSONObject.fromObject(map,config).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
		return null;
	}
	
	@Action(value="courierAction_batchDel",results={@Result(name="success",location="pages/base/courier.html",type="redirect")})
	public String batchDel() {
		courierService.batchDel(courier.getId());
		return SUCCESS;
	}
	
	@Action(value="courierAction_listAjax")
	public String listAjax() throws IOException {
		List<Courier> list = courierService.findByDeltagIsNull();
		//懒加载，让takeTime和fixedAreas变成瞬时态
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"takeTime","fixedAreas"});
		String json = JSONArray.fromObject(list,config).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
		return NONE;
	}
	
	@Override
	public Courier getModel() {
		return courier;
	}

}
