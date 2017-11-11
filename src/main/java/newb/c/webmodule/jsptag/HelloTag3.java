package newb.c.webmodule.jsptag;

/**
 * Created by woshizbh on 2016/12/19.
 */
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class HelloTag3 extends SimpleTagSupport {

   private String message;

   public void setMessage(String msg) {
      this.message = msg;
   }

   StringWriter sw = new StringWriter();

   @Override
   public void doTag()
      throws JspException, IOException
    {
       if (message != null) {
          /* 从属性中使用消息 */
          JspWriter out = getJspContext().getOut();
          out.println( message );
       }
       else {
          /* 从内容体中使用消息 */
          getJspBody().invoke(sw);
          getJspContext().getOut().println(sw.toString());
       }
   }

}
