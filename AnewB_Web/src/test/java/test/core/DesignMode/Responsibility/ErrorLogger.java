package test.core.DesignMode.Responsibility;

public class ErrorLogger extends AbstractLogger {

	public ErrorLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		System.out.println("错误 " + message);
	}
}