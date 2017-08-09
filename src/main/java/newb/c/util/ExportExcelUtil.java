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

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 *
 * 导出文件Util
 *
 * @author woshizbh
 *
 */
public class ExportExcelUtil {

	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

	private Workbook wkb = new XSSFWorkbook();

	public void simpleExport(HttpServletResponse response, String[] headList, List<String[]> bodylist)

			throws IOException {
		// 建立新的sheet对象（excel的表单） 下面的分页
		Sheet sheet = wkb.createSheet("1");

		Row row = sheet.createRow(0);
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		for (int i = 0; i < headList.length; i++) {
			row.createCell(i).setCellValue(headList[i]);
		}

		Row bodyRow = null;

		for (int i = 0; i < bodylist.size(); i++) {
			String[] body = bodylist.get(i);
			bodyRow = sheet.createRow(i + 1);
			for (int j = 0; j < body.length; j++) {
				bodyRow.createCell(j).setCellValue(body[j]);
			}
		}

		// 输出Excel文件
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=salesOrder.xlsx");
		response.setContentType("application/msexcel");
		wkb.write(output);
		output.close();
	}

	/**
	 * 描述：获取IO流中的数据，组装成List<List<Object>>对象
	 * 不能解析跨行类型
	 * @param in,fileName
	 * @return
	 * @throws IOException
	 */
	public List<List<Object>> simpleImport(InputStream in, String fileName) throws Exception {
		List<List<Object>> list = null;
		// 创建Excel工作薄
		Workbook work = this.getWorkbook(in, fileName);
		if (null == work) {
			throw new Exception("Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;

		list = new ArrayList<List<Object>>();
		// 遍历Excel中所有的sheet
		int s = work.getNumberOfSheets();
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			int s2 = sheet.getLastRowNum();
			// 遍历当前sheet中的所有行
			for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
				row = sheet.getRow(j);
				if (row == null || row.getFirstCellNum() == j) {
					continue;
				}

				// 遍历所有的列
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
	 *
	 * @param inStr,fileName
	 * @return
	 * @throws Exception
	 */
	public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr); // 2003-
		} else if (excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr); // 2007+
		} else {
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}

	/**
	 * 描述：对表格中数值进行格式化
	 *
	 * @param cell
	 * @return
	 */
	public Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
			} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
				value = sdf.format(cell.getDateCellValue());
			} else {
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

	/**
	 * 读取excel数据
	 *
	 * @param path
	 */
	private void readExcelToObj(String path) {

		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new File(path));
			readExcel(wb, 0, 0, 0);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
   * 读取excel文件
   * 能解析跨行类型的，将跨行 解析成每行
   * @param wb
   * @param sheetIndex sheet页下标：从0开始
   * @param startReadLine 开始读取的行:从0开始
   * @param tailLine 去除最后读取的行
   */
   private void readExcel(Workbook wb,int sheetIndex, int startReadLine, int tailLine) {
	   Sheet sheet = wb.getSheetAt(sheetIndex);
	   Row row = null;

	   ArrayList<List<Object>> bodyList = new ArrayList<List<Object>>();

		for (int i = startReadLine; i < sheet.getLastRowNum() - tailLine + 1; i++) {
			List<Object> rowList = new ArrayList<>();
			row = sheet.getRow(i);
			for (Cell c : row) {
				boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
				Object cellValue;
				// 判断是否具有合并单元格
				if (isMerge) {
					Object rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
					System.out.print(rs + "|");
					cellValue = rs;
				} else {
					System.out.print(getCellValue(c) + "|");
					cellValue = getCellValue(c);
				}

				rowList.add(cellValue);

			}
			System.out.println();
			bodyList.add(rowList);
		}
		System.out.println(bodyList.toString());
   }

	/**
	 * 获取合并单元格的值
	 *
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public Object getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell);
				}
			}
		}

		return null;
	}

	/**
	 * 判断合并了行
	 *
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean isMergedRow(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row == firstRow && row == lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 *
	 * @param sheet
	 * @param row
	 *            行下标
	 * @param column
	 *            列下标
	 * @return
	 */
	private boolean isMergedRegion(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断sheet页中是否含有合并单元格
	 *
	 * @param sheet
	 * @return
	 */
	private boolean hasMerged(Sheet sheet) {
		return sheet.getNumMergedRegions() > 0 ? true : false;
	}

	/**
	 * 合并单元格
	 *
	 * @param sheet
	 * @param firstRow
	 *            开始行
	 * @param lastRow
	 *            结束行
	 * @param firstCol
	 *            开始列
	 * @param lastCol
	 *            结束列
	 */
	private void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}

	@Test
	public void getListByExcelTest() throws Exception {
		ExportExcelUtil excelUtil = new ExportExcelUtil();
		InputStream in = new FileInputStream(new File("D:\\2.xlsx"));
		List<List<Object>> list = excelUtil.simpleImport(in, "2.xlsx");
		System.out.println(list.size());

		excelUtil.readExcelToObj("D:\\2.xlsx");
	}

}
