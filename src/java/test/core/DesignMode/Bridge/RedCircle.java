package test.core.DesignMode.Bridge;

public class RedCircle implements DrawAPI {
	   @Override
	   public void drawCircle(int radius, int x, int y) {
	      System.out.println("Drawing Circle[ color: 红, radius: "
	         + radius +", x: " +x+", "+ y +"]");
	   }
	}
