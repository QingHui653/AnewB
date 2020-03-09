package newb.c.util.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * jackJson 自定义 String 序列化注解
 * //在需要加密的 字段上加上此注解 然后使用 jackson 进行转换 json
 */
public class WxSerialize extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeString("加密后的字符串"+ value);
    }

//    private String encrypt(String data){
//        try {
//            return WxPayV3Util.rsaEncryptOAEP(data, SpringContextUtil.getBean(WxPayV3Config.class).getCertificate());
//        } catch (IllegalBlockSizeException | IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}