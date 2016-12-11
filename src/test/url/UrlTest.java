package test.url;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlTest {
	public static void main(String[] args) {
		String domain = "aliyunbaidu.com";
        UrlTest test = new UrlTest();
        boolean check = test.domainIsAvailable(domain);
        System.out.println(domain + " 是否可用：" + check);
	}
	
	/**
	 * get 用例
	 * @param domain
	 * @return
	 */
	public boolean domainIsAvailable(String domain){
        boolean isAvailable = false;  //该域名是否可用
        try {
            URL url = new URL("http://panda.www.net.cn/cgi-bin/check.cgi?area_domain=" + domain);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);  //毫秒
            connection.setReadTimeout(5000);
            
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";  //每次读取一行数据
            String reg = "<original>(.*?)</original>";  //正则
            while((line = reader.readLine()) != null){
                if(line.matches(reg)){
//                    System.out.println(line);
                    //只有两种状态，210表示可用，211表示不可用
                    String state = line.substring(10, 13);
                    if("210".equals(state))
                        isAvailable = true;                    
                }
            }
            
        }  catch (IOException e) {
            e.printStackTrace();
        }        
        return isAvailable;
    }
	
	/**
	 * POST用例
	 */
	public void POSTTest(){
        try {
            URL url = new URL("http://localhost:8080/Demo/jsp/show_get");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);  //这里因为post请求有数据传输，一定要是true
            connection.setDoInput(true);
            connection.setUseCaches(false);
            
            String str = "str=aaavc";
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(str.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            
            System.out.println(connection.getResponseCode());
            if(connection.getResponseCode() == 200){
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String line = "";
                while((line = reader.readLine()) != null){
                    stringBuffer.append(line + "\n");
                }
                System.out.println(stringBuffer.toString());
            }
                    
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

