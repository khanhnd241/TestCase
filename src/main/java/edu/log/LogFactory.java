package edu.log;

import org.apache.log4j.Logger;

public class LogFactory {
	public static Logger getLogger() {
		Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
		return logger;
	}
}
