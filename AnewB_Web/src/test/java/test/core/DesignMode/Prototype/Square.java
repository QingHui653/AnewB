package test.core.DesignMode.Prototype;

public class Square extends Shape{
	
	public Square() {
		type="Square";
	}
	
	@Override
	public void draw() {
		System.out.println("Square");
	}

}
