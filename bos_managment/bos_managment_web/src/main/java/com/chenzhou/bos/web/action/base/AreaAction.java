package com.chenzhou.bos.web.action.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
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

import com.chenzhou.bos.bean.base.Area;
import com.chenzhou.bos.service.action.base.AreaService;
import com.chenzhou.bos.utils.PinYin4jUtils;
import com.chenzhou.bos.web.action.common.CommonAction;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class AreaAction extends CommonAction<Area> {
	private static final long serialVersionUID = 1L;

	private AreaAction() {
		super(Area.class);
	}
	private File areaFile;
	public void setAreaFile(File areaFile) {
		this.areaFile = areaFile;
	}
	
	private String q;
	public void setQ(String q) {
		this.q = q;
	}
	
	@Resource(name="areaService")
	private AreaService areaService;
	
	
	@Action(value="areaAction_importXls",results={@Result(name="success",location="pages/base/area.html",type="redirect")})
	public String importXLS() throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(areaFile));
		//解析哪个sheet
		HSSFSheet sheetAt = workbook.getSheetAt(0);
		List<Area> list = new ArrayList<Area>();
		//对sheet的每一行进行遍历
		for (Row row : sheetAt) {
			if(row.getRowNum()==0) {
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			//获得简码shortcode
			String p = province.substring(0, province.length()-1);
			String c = city.substring(0, city.length()-1);
			String pc = district.substring(0, district.length()-1);
			//通过PinYin4jUtils工具类来得到一个shortcode的数组
			String[] headByString = PinYin4jUtils.getHeadByString(p+c+pc);
			//把数组转换为大写的字符串，去掉逗号和括号
			String shortcode = StringUtils.join(headByString).toUpperCase();
			//获得城市编码citycode
			String citycode = PinYin4jUtils.hanziToPinyin(c, "").toUpperCase();
			//封装参数
			Area area1 = new Area();
			area1.setId(id);
			area1.setProvince(province);
			area1.setCity(city);
			area1.setDistrict(district);
			area1.setPostcode(postcode);
			area1.setShortcode(shortcode);
			area1.setCitycode(citycode);
			list.add(area1);
		}
		areaService.save(list);
		workbook.close();
		return SUCCESS;
	}
	
	@Action("areaAction_findByPage")
	public String findByPage() throws IOException {
		Pageable pageable = new PageRequest(page-1, rows);
		Page<Area> page = areaService.findAll(pageable);
		page2Json(page,new String[]{"subareas"});
		return NONE;
	}
	
	@Action(value="areaAction_findAll")
	public String findAll() throws IOException {
		List<Area> list;
		if(StringUtils.isNotEmpty(q)) {
			list = areaService.findLike(q);
		}else {
			list = areaService.findAll();
		}
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"subareas"});
		String json = JSONArray.fromObject(list, config).toString();
		//把json写到页面
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
		return NONE;
	}
	

}
