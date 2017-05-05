package newb.c.qrcode;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class Test {

	/**
	 * @param args
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {

		try {
			String content = "http://www.baidu.com";
			// String path = "D:/tt";
			String path = "D:/";
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map hints = new HashMap();
			// 内容所使用编码
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
			// 生成二维码
			File outputFile = new File(path, "14.png");
			if (!outputFile.exists())
				outputFile.createNewFile();
			MatrixToImageWriter.writeToFile(bitMatrix, "png", outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
