package newb.c.test;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import newb.c.backend.model.basemodel.User;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by 26939 on 2017/9/17.
 */
public class JsonTest {

        @Test
        public void  test() throws IOException, ParseException {
            User u =new User();
            u.setOid(1);
            u.setUsername("xxxx");
            u.setPassword("xxxx22");
            String json = JSON.json(u);
            System.out.println("json--"  +json);
            User u2 = JSON.parse(json,User.class);
            u2.setUsername("模板id");
            u2.setPassword("模板内容");
            System.out.println(u2.toString());
        }
}
