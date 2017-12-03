package bos_managment_web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class TestPOI {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		String path = "D:\\内网通文件\\就业班软件\\BOS\\Day04\\资料\\03_区域测试数据\\区域导入测试数据.xls";
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
		//解析哪个sheet
		HSSFSheet sheetAt = workbook.getSheetAt(0);
		//对sheet的每一行进行遍历
		for (Row row : sheetAt) {
			if(row.getRowNum()==0) {
				continue;
			}
			//对每一个格子进行遍历
			for (Cell cell : row) {
				//得到格子里面的内容
				String stringCellValue = cell.getStringCellValue();
				System.out.print(stringCellValue+"\t");
			}
			System.out.println("");
		}
		workbook.close();
	}
}
