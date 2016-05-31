import org.apache.log4j.Logger;

public class Test {
	private static Logger logger = Logger.getLogger(Test.class);
	public static void main(String[] args) {
		logger.debug("this is debug message");
		logger.info("this is info message");
		logger.error("this is error message");
		logger.fatal("this is fatal message");
	}
}
