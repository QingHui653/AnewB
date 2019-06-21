package test.core.SerializableTest;

import java.io.Serializable;

public class Employee implements Serializable {
	public String name;
	public String address;
	//transient 短暂的，忽略序列化
	public transient int SSN;
	public int number;

	public void mailCheck() {
		System.out.println("Mailing a check to " + name + " " + address);
	}
}
