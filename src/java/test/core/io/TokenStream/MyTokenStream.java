package test.core.io.TokenStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import sun.nio.cs.StreamDecoder;

/**
 * 实现输入 (5 + 8) * 13
 * 输出 {LPAR, "("} {INT, Integer(5)}, {PLUS, "+"}, {INT, INTEGER(8)}, {RPAR, ")"}, 
 * {MULT, "*"}, {INT, INTEGER(13)}
 * @author woshizbh
 *
 */
public class MyTokenStream implements TokenStream {
	
	private Reader r;
	
	private char[] cbuf = new char[50];
	
	private int offset=0;
	
	private Token token;
	
	public MyTokenStream(InputStream in) throws IOException {
		r = new InputStreamReader(System.in);
		r.read(cbuf);
    }
	
	@Override
	public Token getToken() throws IOException {
		System.out.print("{");
		
		//字符之间比较 直接使用 == 和单引号
		if('('==cbuf[offset]){
			token=new Token(TokenType.LPAR, cbuf[offset]);
			System.out.print(TokenType.LPAR+",");
		}else if(')'==cbuf[offset]){
			token=new Token(TokenType.RPAR, cbuf[offset]);
			System.out.print(TokenType.RPAR+",");
		}else if('+'==cbuf[offset]){
			token=new Token(TokenType.PLUS, cbuf[offset]);
			System.out.print(TokenType.PLUS+",");
		}else if('-'==cbuf[offset]){
			token=new Token(TokenType.MINUS, cbuf[offset]);
			System.out.print(TokenType.MINUS+",");
		}else if('*'==cbuf[offset]){
			token=new Token(TokenType.MULT, cbuf[offset]);
			System.out.print(TokenType.MULT+",");
		}else if('/'==cbuf[offset]){
			token=new Token(TokenType.DIV, cbuf[offset]);
			System.out.print(TokenType.DIV+",");
		}else if(cbuf[offset]=='\0'){//字符只能通过'\0'来判断是否为null
			token=new Token(TokenType.NONE, cbuf[offset]);
		}else {
			token=new Token(TokenType.INT, cbuf[offset]);
			System.out.print(TokenType.INT+",");
		}
		
		System.out.print("\""+cbuf[offset]+"\"");
		System.out.print("}");
		
		return token;
	}

	@Override
	public void consumeToken() {
		offset++;
	}


	

}
