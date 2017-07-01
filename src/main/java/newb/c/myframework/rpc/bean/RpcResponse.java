package newb.c.myframework.rpc.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class RpcResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7210281712449653365L;

	boolean isException;
	
	Object result;
	
	Exception exception;
}
