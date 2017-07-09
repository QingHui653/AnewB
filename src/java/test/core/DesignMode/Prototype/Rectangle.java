package test.core.DesignMode.Prototype;

public class Rectangle extends Shape{
	
	public Rectangle() {
		type="Rectangle";
	}
	
	@Override
	public void draw() {
		System.out.println("Rectangle");
	}

}
