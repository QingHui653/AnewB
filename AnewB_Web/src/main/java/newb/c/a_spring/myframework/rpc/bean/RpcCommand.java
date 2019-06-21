package newb.c.a_spring.myframework.rpc.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class RpcCommand implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5834556180239317528L;

	String className;
	
	String methodName;
	
	String[] argumetsType;
	
	Object[] params;
	
}
