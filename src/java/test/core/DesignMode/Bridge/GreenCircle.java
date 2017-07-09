package test.core.DesignMode.Bridge;

public class GreenCircle implements DrawAPI {
	   @Override
	   public void drawCircle(int radius, int x, int y) {
	      System.out.println("Drawing Circle[ color: 绿, radius: "
	         + radius +", x: " +x+", "+ y +"]");
	   }
	}
