package com.chenzhou.bos.web.action.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class CommonAction<T> extends ActionSupport implements ModelDriven<T> {
	private static final long serialVersionUID = 1L;
	
	public T model;
	public CommonAction(Class<T> clazz) {
		try {
			model = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public int page;
	public int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	public void page2Json(Page<T> page,String[] s) throws IOException {
		//从封装的PageBean中获取Area对象的list集合
		List<T> list = page.getContent();
		//获取总条数
		long totalElements = page.getTotalElements();
		Map<String,Object> map  =new HashMap<String,Object>();
		//把结果放入map集合中
		map.put("total", totalElements);
		map.put("rows", list);
		//应对session关闭懒加载问题
		JsonConfig config = new JsonConfig();
		config.setExcludes(s);
		//把map集合转为json数据
		String json = JSONObject.fromObject(map,config).toString();
		//把json写到页面
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}
	
	public void list2Json(List list,String[] s) throws IOException {
		//应对session关闭懒加载问题
		JsonConfig config = new JsonConfig();
		config.setExcludes(s);
		//把map集合转为json数据
		String json = JSONArray.fromObject(list, config).toString();
		//把json写到页面
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}
	
	@Override
	public T getModel() {
		return model;
	}

}
