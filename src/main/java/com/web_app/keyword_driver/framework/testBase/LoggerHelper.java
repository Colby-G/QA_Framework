package com.web_app.keyword_driver.framework.testBase;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

// Calls on the log4j properties files to get the properties of that module so it can be ran in a way that will help log the results of the test.
@SuppressWarnings("rawtypes")
public class LoggerHelper {
	private static boolean root = false;

	public static Logger getLogger(Class clas) {
		if (root) {
			return Logger.getLogger(clas);
		}
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/test/java/properties_files/log4j.properties");
		root = true;
		return Logger.getLogger(clas);
	}
}
