package newb.c.util;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Map;

/**
 * @Auther:woshizbh
 * @Date: 2018/12/27
 * @Deseription
 */
public class PDFUtil {
/*
    private final static Logger log = Logger.getLogger(StatePDFUtil.class);

    private static Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);

    private static String templatePath =StatePDFUtil.class.getResource("/").getPath();

    static {
        try {
            templatePath = templatePath.substring(0,templatePath.lastIndexOf("/classes/"))+"/template/";
            cfg.setDirectoryForTemplateLoading(new File(templatePath));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void create() throws Exception {
        // html中字体非常郁闷
        // 1. html中不指定字体，则默认使用英文字体，中文会不显示。
        // 2. html中指定的字体必须是英文名称，如宋体：font-family:SimSun;
        // 3. html中不能指定自定义字体，必须指定itext支持的字体，还好itext支持字体比较多，常见操作系统带的都支持
        // 4. 暂没有找到如何html中支持自定义字体方法，网上都是修改源码实现默认字体中文，也很重要
        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<body style='font-size:20px;font-family:SimSun;'>");
        html.append("<table width='19cm'border='1' cellpadding='0' cellspacing='0'>");
        html.append("<tr>");
        html.append("<td colspan='2'>凉州词</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>黄河远上白云间，一片孤城万仞山。</td>");
        html.append("<td>羌笛何须怨杨柳，春风不度玉门关。</td>");
        html.append("</tr>");
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");

        InputStream is = new ByteArrayInputStream(html.toString().getBytes());
        File f = new File("C:\\demo4.pdf");
        System.out.println(f.exists());
        if(!f.exists()){
            f.createNewFile();
        }
        OutputStream os = new FileOutputStream(f);
        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document, os);

        document.open();

        // 将html转pdf
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);

        document.close();
    }

    public static void coverHtml(String html,String cssSource) throws IOException, DocumentException {
        InputStream is = new ByteArrayInputStream(html.getBytes());
        File f = new File("C:\\demo4.pdf");
        System.out.println(f.exists());
        if(f.exists()) {
            f.delete();
        }
        f.createNewFile();
        OutputStream os = new FileOutputStream(f);
        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document, os);

        document.open();

        if(cssSource!=null && cssSource.length()>0){
            cssSource = templatePath+cssSource;
            File cssFile = new File(cssSource);
            InputStream cssis = new FileInputStream(cssFile);
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, is,cssis);
        }else {
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
        }
        document.close();
    }

    public static String getTemplate(String template, Map map) throws IOException,freemarker.template.TemplateException {
        Template temp = cfg.getTemplate(template);
        StringWriter stringWriter = new StringWriter();
        temp.process(map, stringWriter);
        return stringWriter.toString();
    }*/
}
