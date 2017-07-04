package test.core.io.TokenStream;

import java.io.IOException;

public class Test {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
        System.out.println("输入算术 ? ");
		
		TokenStream ts = new MyTokenStream(System.in); // 标准输入的适配器
		 
		while (ts.getToken().tokenType != TokenType.NONE) {
			ts.consumeToken();
		}
	}
}
