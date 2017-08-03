package newb.c.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * 导出文件Util
 * @author woshizbh
 *
 */
public class ExcelUtil {
	
	private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel
	
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
	
	
	/**
    * 描述：获取IO流中的数据，组装成List<List<Object>>对象
    * @param in,fileName
    * @return
    * @throws IOException 
    */
   public  List<List<Object>> getBankListByExcel(InputStream in,String fileName) throws Exception{
       List<List<Object>> list = null;

       //创建Excel工作薄
       Workbook work = this.getWorkbook(in,fileName);
       if(null == work){
           throw new Exception("创建Excel工作薄为空！");
       }
       Sheet sheet = null;
       Row row = null;
       Cell cell = null;

       list = new ArrayList<List<Object>>();
       //遍历Excel中所有的sheet
       int s=work.getNumberOfSheets();
       for (int i = 0; i < work.getNumberOfSheets(); i++) {
           sheet = work.getSheetAt(i);
           if(sheet==null){continue;}
           int s2=sheet.getLastRowNum();
           //遍历当前sheet中的所有行
           for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
               row = sheet.getRow(j);
               if(row==null||row.getFirstCellNum()==j){continue;}

               //遍历所有的列
               List<Object> li = new ArrayList<Object>();
               for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                   cell = row.getCell(y);
                   li.add(this.getCellValue(cell));
               }
               list.add(li);
           }
       }
       work.close();
       return list;
   }

   /**
    * 描述：根据文件后缀，自适应上传文件的版本 
    * @param inStr,fileName
    * @return
    * @throws Exception
    */
   public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
       Workbook wb = null;
       String fileType = fileName.substring(fileName.lastIndexOf("."));
       if(excel2003L.equals(fileType)){
           wb = new HSSFWorkbook(inStr);  //2003-
       }else if(excel2007U.equals(fileType)){
           wb = new XSSFWorkbook(inStr);  //2007+
       }else{
           throw new Exception("解析的文件格式有误！");
       }
       return wb;
   }

   /**
    * 描述：对表格中数值进行格式化
    * @param cell
    * @return
    */
   public  Object getCellValue(Cell cell){
       Object value = null;
       DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
       SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
       DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

       switch (cell.getCellType()) {
       case Cell.CELL_TYPE_STRING:
           value = cell.getRichStringCellValue().getString();
           break;
       case Cell.CELL_TYPE_NUMERIC:
           if("General".equals(cell.getCellStyle().getDataFormatString())){
               value = df.format(cell.getNumericCellValue());
           }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
               value = sdf.format(cell.getDateCellValue());
           }else{
               value = df2.format(cell.getNumericCellValue());
           }
           break;
       case Cell.CELL_TYPE_BOOLEAN:
           value = cell.getBooleanCellValue();
           break;
       case Cell.CELL_TYPE_BLANK:
           value = "";
           break;
       default:
           break;
       }
       return value;
   }
   

}

