package newb.c.webmodule.jsptag;

/**
 * Created by woshizbh on 2016/12/19.
 */
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class HelloTag2 extends SimpleTagSupport {

   StringWriter sw = new StringWriter();

   @Override
   public void doTag()
      throws JspException, IOException
    {
       getJspBody().invoke(sw);
       getJspContext().getOut().println(sw.toString());
    }

}
