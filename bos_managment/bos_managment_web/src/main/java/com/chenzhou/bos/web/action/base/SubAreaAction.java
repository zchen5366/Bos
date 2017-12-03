package com.chenzhou.bos.web.action.base;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

import com.chenzhou.bos.bean.base.Courier;
import com.chenzhou.bos.bean.base.FixedArea;
import com.chenzhou.bos.bean.base.SubArea;
import com.chenzhou.bos.service.action.base.SubAreaService;
import com.chenzhou.bos.utils.FileUtils;
import com.chenzhou.bos.web.action.common.CommonAction;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONArray;

@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller
public class SubAreaAction extends CommonAction<SubArea> {
	private static final long serialVersionUID = 1L;
	
	public SubAreaAction() {
		super(SubArea.class);
	}
	
	@Resource(name="subareaService")
	private SubAreaService subAreaService;
	
	@Action(value="subareaAction_save",results={@Result(name="success",location="pages/base/sub_area.html",type="redirect")})
	public String save() {
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		model.setId(id);
		subAreaService.save(model);
		return SUCCESS;
	}

	@Action(value="subareaAction_findByPage")
	public String findByPage() throws IOException {
		Pageable pageable = new PageRequest(page-1,rows);
		Page<SubArea> page = subAreaService.findByPage(pageable);
		page2Json(page, new String[]{"fixedArea","subareas"});
		return NONE;
	}
	
	//关联定区subAreaAction_noassociation
	@Action("subAreaAction_noassociation")
	public String noassociation() throws IOException {
		List<SubArea> list  = subAreaService.findByfixedAreaIsNull();
		list2Json(list, new String[]{"subareas"});
		return NONE;
	}
	
	/*private FixedArea fixedArea;
	public void setFixedArea(FixedArea fixedArea) {
		this.fixedArea = fixedArea;
	}*/
	
//	subAreaAction_association
	@Action("subAreaAction_association")
	public String association() throws IOException {
		System.out.println(model.getFixedArea().getId());
		List<SubArea> list = subAreaService.findByFixedArea(model.getFixedArea().getId());
		System.out.println(list);
		list2Json(list, new String[]{"area","subareas","couriers"});
		return NONE;
	}
	
	@SuppressWarnings("resource")
	@Action("subAreaAction_exportExcel")
	public String exportExcel() throws IOException {
		//查询所有分区
		List<SubArea> list = subAreaService.findAll();
		//在内存中创建excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建工作簿sheet
		HSSFSheet sheet = workbook.createSheet();
		//创建第一行表头
		HSSFRow row = sheet.createRow(0);
		//创建列
		row.createCell(0).setCellValue("关键字");
		row.createCell(1).setCellValue("起始号");
		row.createCell(2).setCellValue("终止号");
		row.createCell(3).setCellValue("单双号");
		row.createCell(4).setCellValue("辅助关键字");
		row.createCell(5).setCellValue("区域信息");
		//遍历集合
		for (SubArea subArea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subArea.getKeyWords());
			dataRow.createCell(1).setCellValue(subArea.getStartNum());
			dataRow.createCell(2).setCellValue(subArea.getEndNum());
			dataRow.createCell(3).setCellValue(subArea.getSingle());
			dataRow.createCell(4).setCellValue(subArea.getAssistKeyWords());
			dataRow.createCell(5).setCellValue(subArea.getArea().getName());
		}
		//获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		//获取输出流
		ServletOutputStream outputStream = response.getOutputStream();
		//定义文件名
		String fileName = "分区数据统计.xls";
		//通过request从请求头获取客户端类型
		String header = ServletActionContext.getRequest().getHeader("User-Agent");
		//对文件名进行重新编码
		fileName = FileUtils.encodeDownloadFilename(fileName, header);
		//以指定附件形式处理文件
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		//指定文件的MIME类型
		response.setContentType(ServletActionContext.getServletContext().getMimeType(fileName));
		//写出文件
		workbook.write(outputStream);
		//释放资源
		workbook.close();
		return NONE;
	}
	
	//分区数据图表显示
	@Action("subArea_chart")
	public String chart() throws IOException {
		List<Object[]> list = subAreaService.getChartData();
		System.out.println(list.toString());
		list2Json(list, null);
		return NONE;
	}
}










