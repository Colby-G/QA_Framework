package com.web_app.keyword_driver.framework.testBase;

// Wait times that the system can call on depending on different lengths to wait that is needed.
public class Wait extends TestBase {

	public static String getScrollWait() {
		return Repository.getProperty("scrollWait");
	}

	public static String getExplicitWait() {
		return Repository.getProperty("explicitWait");
	}

	public static String getImplicitWait() {
		return Repository.getProperty("implicitWait");
	}
}
