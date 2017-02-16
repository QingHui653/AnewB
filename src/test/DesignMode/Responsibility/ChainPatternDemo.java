package test.DesignMode.Responsibility;
/**
 * 责任链模式
 * @author woshizbh
 *
 */
public class ChainPatternDemo {

	private static AbstractLogger getChainOfLoggers() {

		AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
		AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
		AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

		errorLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger);

		return errorLogger;
	}

	public static void main(String[] args) {
		AbstractLogger loggerChain = getChainOfLoggers();

		loggerChain.logMessage(AbstractLogger.INFO, "普通信息");
		System.out.println();
		loggerChain.logMessage(AbstractLogger.DEBUG, "DEBUG信息");
		System.out.println();
		loggerChain.logMessage(AbstractLogger.ERROR, "错误信息");
	}
}
