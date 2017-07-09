package test.core.DesignMode.Responsibility;

public class ConsoleLogger extends AbstractLogger {

	   public ConsoleLogger(int level){
	      this.level = level;
	   }

	   @Override
	   protected void write(String message) {		
	      System.out.println("Logger " + message);
	   }
	}
