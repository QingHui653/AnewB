package test.core.io.TokenStream;

import java.io.IOException;

public interface TokenStream {
    public Token getToken()  throws IOException;
    public void consumeToken();
} 
