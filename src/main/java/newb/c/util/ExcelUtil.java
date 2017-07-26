package newb.c.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 
 * 导出文件Util
 * @author woshizbh
 *
 */
public class ExcelUtil {
	
	// 创建HSSFWorkbook对象(excel的文档对象)
	private HSSFWorkbook wkb = new HSSFWorkbook();
	
	public void demoExport(HttpServletResponse response) throws IOException {
		
		
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wkb.createSheet("成绩表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);
		// 设置单元格内容
		cell.setCellValue("学员考试成绩一览表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		row2.createCell(0).setCellValue("姓名");
		row2.createCell(1).setCellValue("班级");
		row2.createCell(2).setCellValue("笔试成绩");
		row2.createCell(3).setCellValue("机试成绩");
		// 在sheet里创建第三行
		HSSFRow row3 = sheet.createRow(2);
		row3.createCell(0).setCellValue("李明");
		row3.createCell(1).setCellValue("As178");
		row3.createCell(2).setCellValue(87);
		row3.createCell(3).setCellValue(78);
		// .....省略部分代码

		// 输出Excel文件
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=details.xls");
		response.setContentType("application/msexcel");
		wkb.write(output);
		output.close();
	}
	
	public void simpleExport(HttpServletResponse response, String[] headList, List<String[]> bodylist)
			throws IOException {
		// 建立新的sheet对象（excel的表单） 下面的分页
		HSSFSheet sheet = wkb.createSheet("1");

		HSSFRow headRow = sheet.createRow(0);
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		for (int i = 0; i < headList.length; i++) {
			headRow.createCell(i).setCellValue(headList[i]);
		}
		
		HSSFRow bodyRow=null;
		
		for (int i = 0; i < bodylist.size(); i++) {
			String[] body =bodylist.get(i);
			bodyRow=sheet.createRow(i+1);
			for (int j = 0; j < body.length; j++) {
				bodyRow.createCell(j).setCellValue(body[j]);
			}
		}

		// 输出Excel文件
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=file"+RandomUtils.nextInt(1, 100)+".xls");
		response.setContentType("application/msexcel");
		wkb.write(output);
		output.close();
	}
}

