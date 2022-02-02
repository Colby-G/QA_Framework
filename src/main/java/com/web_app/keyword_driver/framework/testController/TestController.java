package com.web_app.keyword_driver.framework.testController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.web_app.keyword_driver.framework.keywords.Keywords;
import com.web_app.keyword_driver.framework.enums.Browsers;
import com.web_app.keyword_driver.framework.excelWriter.ExcelWriter;
import com.web_app.keyword_driver.framework.reportUtils.ReportUtil;
import com.web_app.keyword_driver.framework.testBase.TestBase;

public class TestController extends TestBase {

	// Calls to start the browser and delete the old screenshots.
	@BeforeClass
	public void initBrowser() throws IOException {
		TestBase.deleteOldReports();
		TestBase.deleteScreenShots();
		Initialize();
	}

	// Start making decisions for the reports and framework based on the data gathered from the Excel file.
	@Test
	public void TestCaseController() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		// Initialize start time of the overall test and set the display information.
		String TestingSuite = SuiteData.getCellData("TestCases", "TestingSuite", 2);
		String BatchNumber = SuiteData.getCellData("TestCases", "BatchNumber", 2);
		String startTime = TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa");
		ReportUtil.startTesting("index", startTime, TestingSuite, BatchNumber);
		ReportUtil.startSuite("Suite1");
		// Loop through the test cases.
		for (int TC = 2; TC <= SuiteData.getRowCount("TestCases"); TC++) {
			String TCStatus = "Passed";
			String startTimes = TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa");
			String TestCaseID = SuiteData.getCellData("TestCases", "TCID", TC);
			String TestCaseDescription = SuiteData.getCellData("TestCases", "Description", TC);
			String RunMode = SuiteData.getCellData("TestCases", "RunMode", TC);
			// Check the run mode on each test case.
			if (RunMode.equals("Y")) {
				String TSStatus = "Passed";
				driver = TestBase.selectBrowser(Browsers.CHROME.name());
				int rows = TestStepData.getRowCount(TestCaseID);
				if (rows < 2) {
					rows = 2;
				}
				// Looping through the test steps for each test case.
				for (int TS = 2; TS <= SuiteData.getRowCount(TestCaseID); TS++) {
					keyword = SuiteData.getCellData(TestCaseID, "Keyword", TS);
					webElement = SuiteData.getCellData(TestCaseID, "WebElement", TS);
					TSID = SuiteData.getCellData(TestCaseID, "TSID", TS);
					Description = SuiteData.getCellData(TestCaseID, "Description", TS);
					TestDataField = SuiteData.getCellData(TestCaseID, "TestDataField", TS);
					if (TestDataField != null && TestDataField != "") {
						TestData = TestStepData.getCellData("TestData", TestDataField, TC);
					}
					if (keyword.equals("screenShot")) {
						TSStatus = Keywords.screenShot(TestCaseID);
					}
					if (!keyword.equals("screenShot")) {
						Method method = Keywords.class.getMethod(keyword);
						TSStatus = (String) method.invoke(method);
					}
					// Taking a screenshot on a failed step and reporting the error.
					if (TSStatus.startsWith("Failed") || TSStatus.startsWith("Partially Failed")) {
						String screenShot = TestBase.getScreenShot(TestCaseID + "_" + keyword);
						TCStatus = "Failed";
						// Send the data to the report file for that test step that failed.
						ReportUtil.addKeyword(TSID, Description, keyword, TSStatus, screenShot);
						if (TSStatus.startsWith("Failed")) {
							TS++;
							for (int SkipTS = TS; SkipTS <= SuiteData.getRowCount(TestCaseID); SkipTS++) {
								TSID = SuiteData.getCellData(TestCaseID, "TSID", SkipTS);
								keyword = SuiteData.getCellData(TestCaseID, "Keyword", SkipTS);
								Description = SuiteData.getCellData(TestCaseID, "Description", SkipTS);
								ReportUtil.addKeyword(TSID, Description, keyword, "Skipped", null);
							}
							break;
						}
					}
					// Send the data to the report file for the screenShot keyword cases.
					else if (TSStatus.startsWith("Screenshot")) {
						String screenshot = TSStatus.split(": ", 2)[1];
						ReportUtil.addKeyword(TSID, Description, keyword, "Passed", screenshot);
					}
					// Send the data to the report file for that test step that passed.
					else {
						ReportUtil.addKeyword(TSID, Description, keyword, TSStatus, null);
					}
				}
				// Send the data to the report file for that test case that passed or failed and update the Excel run mode to reflect the case status.
				SuiteDataOut = new ExcelWriter(excelSuite);
				SuiteDataOut.writeCellDataInt(excelSuite, "TestCases", "RunMode", TC, TCStatus);
				ReportUtil.addTestCase(TestCaseID, TestCaseDescription, startTimes, TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"), TCStatus, RunMode);
				driver.quit();
			}
			// Send the data to the report file for that test case that was skipped.
			else {
				ReportUtil.addTestCase(TestCaseID, TestCaseDescription, startTimes, TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"), "Skipped", RunMode);
				if (driver != null) {
					driver.quit();	
				}
			}
		}
		// End the communication with the report file and update all the end times for each test case.
		ReportUtil.endSuite();
		ReportUtil.updateEndTimes(TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"));
	}

	// Update the passed and failed percent, update the overall end time, then quit the driver when all test cases have been completed.
	@AfterClass
	public void quitBrowser() {
		ReportUtil.updatePassPercent();
		ReportUtil.updateFailPercent();
		ReportUtil.updateOverallEndTime(TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"));
		if (driver != null){
			driver.quit();	
		}
	}
}
