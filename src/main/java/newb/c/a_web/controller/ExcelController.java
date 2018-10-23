package newb.c.a_web.controller;

//import com.alibaba.excel.ExcelReader;
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.metadata.Sheet;
//import com.alibaba.excel.read.context.AnalysisContext;
//import com.alibaba.excel.read.event.AnalysisEventListener;
//import com.alibaba.excel.support.ExcelTypeEnum;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelWriter;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import newb.c.util.ExcelUtil;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther:woshizbh
 * @Date: 2018/9/29
 * @Deseription
 */
@Controller
@RequestMapping("excel")
@Slf4j
public class ExcelController {

    /**
     * 获取excel 文档  JXL
     */
    @RequestMapping(value = "/getExcByJXL", method = RequestMethod.GET)
    @ApiOperation("测试使用JXL获取excel文档  已替换为POI 去掉JXL jar包")
    @Test
    @Deprecated
    public void getExcByJXL() throws SQLException, IOException {
		/*String[] title = {"姓名"};
		String filePath = "g:\\newbc.xls";
		Connection send = DriverManager.getConnection("proxool.local");
		String a0101 = "select username from user";
		List<String> a0101List = data.getDataStringList(a0101, send);
		WritableWorkbook wwb;
		OutputStream out = new FileOutputStream(filePath);
		wwb = Workbook.createWorkbook(out);
		WritableSheet sheet = wwb.createSheet("第一页", 0);
		Label label;
		for (int i = 0; i < title.length; i++) {
			// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
			// 在Label对象的子对象中指明单元格的位置和内容
			label = new Label(i, 0, title[i]);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
		}
		for (int j = 0; j < a0101List.size(); j++) {
			label = new Label(1, j+1, a0101List.get(j).toString());
			sheet.addCell(label);
		}
		log.info("保存路径为-- "+filePath);
		wwb.write();
		wwb.close();
		log.info("保存成功");*/
    }

    /**
     * 获取excel 文档 POI
     */
    @RequestMapping(value = "/getExcByPOI", method = RequestMethod.GET)
    @ApiOperation("测试使用POI获取excel文档  ")
    public void getExcByPOI(HttpServletResponse response) throws SQLException, IOException {
        ExcelUtil excelUtil = new ExcelUtil();
//		excelUtil.demoExport(response);

        String[] headList = {"姓名", "班级"};
        List<String[]> bodyList = new ArrayList<String[]>();
        String[] body1 = {"李明", "As178"};
        String[] body2 = {"李明2", "As179"};
        bodyList.add(body1);
        bodyList.add(body2);

        excelUtil.simpleExport(response, headList, bodyList);

    }

    @RequestMapping("/getExcByHutool")
    @ApiOperation("测试使用hutool获取excel文档  ")
    public void getExcByHutool(HttpServletRequest request,HttpServletResponse response) throws IOException {

        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");

        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);
        ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getWriter();
        writer.merge(3, "全部");
        writer.write(rows);

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=1.xls");

        writer.flush(response.getOutputStream());
        writer.close();

    }

    /* 更多 样例 查看 github easyExcel */
    //操作 excel 不使用 easyExcel, 请使用hutool
    /*@GetMapping("/writeExcelByEasyExcel")
    @ApiOperation("测试使用EasyExcel获取excel文档 ")
    public void writeExcelByEasyExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream out = out = response.getOutputStream();;
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        try {

            String fileName = new String(("UserInfo " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                    .getBytes(), "UTF-8");

            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("第一个sheet");
            sheet1.setHead(getHead());

            writer.write0(getListString(), sheet1);
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.finish();
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    *//**
     * 在 controller 中 无法解析,需要在单独的类中
     * @throws IOException
     *//*
    @GetMapping("/readByEasyExcel")
    @Test
    public void readByEasyExcel() throws IOException {

        InputStream inputStream = new FileInputStream("C:\\Users\\b\\Desktop\\1.xlsx");
        try {
            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null,
                    new AnalysisEventListener<List<String>>() {
                        @Override
                        public void invoke(List<String> object, AnalysisContext context) {
                            System.out.println(
                                    "当前sheet:" + context.getCurrentSheet().getSheetNo() + " 当前行：" + context.getCurrentRowNum()
                                            + " data:" + object);

                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext context) {
                        }
                    });

            reader.read();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private List<List<String>> getHead() {
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> headCoulumn1 = new ArrayList<String>();
        List<String> headCoulumn2 = new ArrayList<String>();
        List<String> headCoulumn3 = new ArrayList<String>();
        List<String> headCoulumn4 = new ArrayList<String>();
        headCoulumn1.add("第一列");
        headCoulumn2.add("第二列");
        headCoulumn3.add("第三列");
        headCoulumn4.add("第四列");
        head.add(headCoulumn1);
        head.add(headCoulumn2);
        head.add(headCoulumn3);
        head.add(headCoulumn4);

        return head;
    }

    private List<List<String>> getListString() {
        List<List<String>> ll = new ArrayList<List<String>>();

        List list = new ArrayList();
        list.add("ooo1");
        list.add("ooo2");
        list.add("ooo3");
        list.add("ooo4");

        List list1 = new ArrayList();
        list1.add("ooo1");
        list1.add("ooo2");
        list1.add("ooo3");
        list1.add("ooo4");

        ll.add(list);
        ll.add(list1);

        return ll;
    }*/

}
