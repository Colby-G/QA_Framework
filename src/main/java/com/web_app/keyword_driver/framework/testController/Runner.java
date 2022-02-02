package com.web_app.keyword_driver.framework.testController;

import org.testng.TestNG;

// Runner file that is need so that the framework can have a "main" function that the .jar file can be created off of.
@SuppressWarnings("rawtypes")
public class Runner {

	public static void main(String[] args) {
		TestNG testng = new TestNG();
	    Class[] classes = new Class[]{TestController.class};
	    testng.setTestClasses(classes);
	    testng.run();
	}
}
