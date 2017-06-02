package test.SerializableTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SerializableDemoInt {

	public static void main(String[] args) {
		Employee ep= null;
		try {
			FileInputStream fileint = new FileInputStream("G:/bean/employee.ser");
			ObjectInputStream in = new ObjectInputStream(fileint); 
			ep= (Employee) in.readObject();
			in.close();
			fileint.close();
			System.out.println("Deserialized Employee...");
		      System.out.println("Name: " + ep.name);
		      System.out.println("Address: " + ep.address);
		      System.out.println("SSN: " + ep.SSN);
		      System.out.println("Number: " + ep.number);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
