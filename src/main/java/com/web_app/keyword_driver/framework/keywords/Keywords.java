package com.web_app.keyword_driver.framework.keywords;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.HashSet;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.web_app.keyword_driver.framework.excelWriter.ExcelWriter;
import com.web_app.keyword_driver.framework.testBase.LoggerHelper;
import com.web_app.keyword_driver.framework.testBase.TestBase;
import com.web_app.keyword_driver.framework.testBase.Wait;

public class Keywords extends TestBase {
	public static String checkerValue = "Pass";
	static String keywordSSDirectory = System.getProperty("user.dir") + "/src/test/java/reports/screenshots/keyword_screenshots";

	// Initializing the logger for outputting the pass or fail data.
	private final static Logger logger = LoggerHelper.getLogger(Keywords.class);

	// Gather the web elements path and locate it on the browser.
	public static WebElement getWebElement(String locator) throws Exception {
		String keywordValue = Repository.getProperty(locator);
		return getLocator(keywordValue);
	}

	// Locate multiple web elements by using a single path.
	public static List<WebElement> getWebElements(String locator) throws Exception {
		return getLocators(Repository.getProperty(locator));
	}

	// Try navigating the intended url.
	public static String navigate() {
		try {
			driver.get(webElement);
		}
		catch (Throwable t) {
			logger.info("Failed: The 'navigate' keyword was called. Could not open the driver and navigate to the web page url");
			return "Failed: The 'navigate' keyword was called. Could not open the driver and navigate to the web page url";
		}
		return "Passed";
	}

	// Try to open a new empty tab in the driver.
	public static String emptyNewTab() {
		try {
			Thread.sleep(1000);
			((JavascriptExecutor)driver).executeScript("window.open()");
		}
		catch (Throwable t) {
			logger.info("Failed: The 'emptyNewTab' keyword was called. Could not open a new empty web tab");
			return "Failed: The 'emptyNewTab' keyword was called. Could not open a new empty web tab";
		}
		return "Passed";
	}

	// Try to open a new tab on the driver by using a url to go to.
	public static String linkedNewTab() {
		try {
			Thread.sleep(1000);
			((JavascriptExecutor)driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			driver.get(webElement);
		}
		catch (Throwable t) {
			logger.info("Failed: The 'linkedNewTab' keyword was called. Could not open a new linked web tab");
			return "Failed: The 'linkedNewTab' keyword was called. Could not open a new linked web tab";
		}
		return "Passed";
	}

	// Try to focus on the new tab that was opened.
	public static String focusNewTab() {
		try {
			Thread.sleep(1000);
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
		}
		catch (Throwable t) {
			logger.info("Failed: The 'focusNewTab' keyword was called. The new web tab was not found");
			return "Failed: The 'focusNewTab' keyword was called. The new web tab was not found";
		}
		return "Passed";
	}

	// Try to focus back onto the old tab.
	public static String focusOldTab() {
		try {
			Thread.sleep(1000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(0));
		}
		catch (Throwable t) {
			logger.info("Failed: The 'focusOldTab' keyword was called. The old web tab was not found");
			return "Failed: The 'focusOldTab' keyword was called. The old web tab was not found";
		}
		return "Passed";
	}

	// Try to close the tab that it is focused on right now. Closing the initially opened tab (the one that started the test) will result in a failure for the test.
	public static String closeTab() {
		try {
			Thread.sleep(1000);
			driver.close();
		}
		catch (Throwable t) {
			logger.info("Failed: The 'closeTab' keyword was called. The current web tab could not be closed");
			return "Failed: The 'closeTab' keyword was called. The current web tab could not be closed";
		}
		return "Passed";
	}

	// Try to mouse over an element on the screen.
	public static String mouseOver() {
		try {
			Thread.sleep(1000);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'mouseOver' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'mouseOver' keyword was called. The web element was not found: '" + webElement + "'";
			}
			WebElement element = getWebElement(webElement);
			new Actions(driver).moveToElement(element).build().perform();
		}
		catch (Throwable t) {
			logger.info("Failed: The 'mouseOver' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'mouseOver' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed";
	}

	// Try to left mouse click on a web element.
	public static String leftClick() {
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'leftClick' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'leftClick' keyword was called. The web element was not found: '" + webElement + "'";
			}
			getWebElement(webElement).click();
		}
		catch (Throwable t) {
			logger.info("Failed: The 'leftClick' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'leftClick' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed";
	}

	// Try to right mouse click on a web element.
	public static String rightClick() {
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'rightClick' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'rightClick' keyword was called. The web element was not found: '" + webElement + "'";
			}
			WebElement element = getWebElement(webElement);
			new Actions(driver).contextClick(element).build().perform();
		}
		catch (Throwable t) {
			logger.info("Failed: The 'rightClick' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'rightClick' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed";
	}

	// Try to double click on a web element.
	public static String doubleClick() {
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'doubleClick' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'doubleClick' keyword was called. The web element was not found: '" + webElement + "'";
			}
			WebElement element = getWebElement(webElement);
			new Actions(driver).doubleClick(element).build().perform();
		}
		catch (Throwable t) {
			logger.info("Failed: The 'doubleClick' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'doubleClick' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed";
	}

	// Try to input some text into a web element on screen.
	public static String inputText() {
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'inputText' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'inputText' keyword was called. The web element was not found: '" + webElement + "'";
			}
			WebElement element = getWebElement(webElement);
			element.click();
			element.clear();
			Thread.sleep(75);
			element.click();
			Thread.sleep(75);
			for (int string_character = 0; string_character < TestData.length(); string_character++) {
				element.sendKeys(String.valueOf(TestData.charAt(string_character)));
				Thread.sleep(15);
			}
		}
		catch (Throwable t) {
			logger.info("Failed: The 'inputText' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'inputText' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed";
	}

	// Try to verify some on screen text with what the text is expected to say.
	public static String verifyText() {
		String ActualText = "";
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'verifyText' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'verifyText' keyword was called. The web element was not found: '" + webElement + "'";
			}
			ActualText = getWebElement(webElement).getText();
			if (!ActualText.equals(TestData)) {
				logger.info("Partially Failed: The 'verifyText' keyword was called. The actual text '" + ActualText + "' was not equal to the expected text '" + TestData + "'");
				return "Partially Failed: The 'verifyText' keyword was called. The actual text '" + ActualText + "' was not equal to the expected text '" + TestData + "'";
			}
		}
		catch (Throwable t) {
			logger.info("Failed: The 'verifyText' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'verifyText' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed: The following text was found: '" + ActualText + "'";
	}

	// Try to select a web element on screen by it's "value" attribute.
	public static String selectByValue() {
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'selectByValue' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'selectByValue' keyword was called. The web element was not found: '" + webElement + "'";
			}
			WebElement element = getWebElement(webElement);
			Select select = new  Select(element);
			select.selectByValue(TestData);
		}
		catch (Throwable t) {
			logger.info("Failed: The 'selectByValue' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'selectByValue' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed";
	}

	// Try to select a web element on screen by the text that it is showing on the screen.
	public static String selectByVisibleText() {
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'selectByVisibleText' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'selectByVisibleText' keyword was called. The web element was not found: '" + webElement + "'";
			}
			WebElement element = getWebElement(webElement);
			Select select = new  Select(element);
			select.selectByVisibleText(TestData);
		}
		catch (Throwable t) {
			logger.info("Failed: The 'selectByVisibleText' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'selectByVisibleText' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed";
	}

	// Try to select a web element on screen by it's index value (i.e. 0, 1, 2, 3, etc...).
	public static String selectByIndex() {
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'selectByIndex' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'selectByIndex' keyword was called. The web element was not found: '" + webElement + "'";
			}
			WebElement element = getWebElement(webElement);
			Select select = new  Select(element);
			select.selectByIndex(Integer.parseInt(TestData));
		}
		catch (Throwable t) {
			logger.info("Failed: The 'selectByIndex' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'selectByIndex' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed";
	}

	// Check a list on screen (i.e. a drop down list).
	public static String listCheck() {
		int indexHTMLNumber = 1;
		List<String> checkList = new ArrayList<String>();
		List<String> duplicateList = new ArrayList<String>();
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'listCheck' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'listCheck' keyword was called. The web element was not found: '" + webElement + "'";
			}
			String keywordValue = Repository.getProperty(webElement);
			String keywordPath = keywordValue.replace("xpath:", "");
			try {
				List<WebElement> textElementList = getLocators(keywordValue + "//div[text()]");
				for (@SuppressWarnings("unused") WebElement i : textElementList) {
					String ActualText = getLocator("xpath:(" + keywordPath + "//div[text()])[" + indexHTMLNumber + "]").getAttribute("textContent");
					checkList.add(ActualText);
					indexHTMLNumber++;
				}
			}
			catch (Throwable t) {
				logger.info("Failed: The 'listCheck' keyword was called. No 'div' tag was found under the web element: '" + webElement + "'");
				return "Failed: The 'listCheck' keyword was called. No 'div' tag was found under the web element: '" + webElement + "'";
			}
			HashSet<String> newHash = new HashSet<String>();
			for (String i : checkList) {
				if (!newHash.add(i)) {
					duplicateList.add(i);
				}
			}
			boolean listAns = duplicateList.isEmpty();
			if (listAns != true) {
				String duplicateString = String.join(", ", duplicateList);
				logger.info("Partially Failed: The 'listCheck' keyword was called. These duplicate values were found in the list: '" + duplicateString + "'");
				return "Partially Failed: The 'listCheck' keyword was called. These duplicate values were found in the list: '" + duplicateString + "'";
			}
		}
		catch (Throwable t) {
			logger.info("Failed: The 'listCheck' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'listCheck' keyword was called. The web element was not found: '" + webElement + "'";
		}
		String checkString = String.join(", ", checkList);
		return "Passed: No duplicate values were found, the values in the list are: '" + checkString + "'";
	}

	// Try to get some text from on screen and display it in the report made.
	public static String receiveText() {
		String screenText = "";
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'receiveText' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'receiveText' keyword was called. The web element was not found: '" + webElement + "'";
			}
			screenText = getWebElement(webElement).getText();
		}
		catch (Throwable t) {
			logger.info("Failed: The 'receiveText' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'receiveText' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed: The following text was found: '" + screenText + "'";
	}

	// Try to verify some on screen text that contains the data entered.
	public static String containsText() {
		String containsText = "";
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'containsText' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'containsText' keyword was called. The web element was not found: '" + webElement + "'";
			}
			containsText = getWebElement(webElement).getText();
			if (!containsText.contains(TestData)) {
				logger.info("Partially Failed: The 'containsText' keyword was called. The actual text '" + containsText + "' does not contain the expected text '" + TestData + "'");
				return "Partially Failed: The 'containsText' keyword was called. The actual text '" + containsText + "' does not contain the expected text '" + TestData + "'";
			}
		}
		catch (Throwable t) {
			logger.info("Failed: The 'containsText' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'containsText' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed: The following text was found: '" + containsText + "'; and it does contain: '" + TestData + "'";
	}

	// Try to take a screenshot of the current screen.
	public static String screenShot(String TestCaseID) {
		File destFile = null;
		try {
			Thread.sleep(1000);
			SimpleDateFormat formater = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
			File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			destFile = new File(keywordSSDirectory + "/" + TestCaseID + "_screenShot_" + formater.format(Calendar.getInstance().getTime()) + ".png");
			Files.copy(screFile.toPath(), destFile.toPath());
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath() + "'height='100' width='100'/></a>");
		}
		catch (Throwable t) {
			logger.info("Failed: The 'screenShot' keyword was called. Could not save the captured screenshot");
			return "Failed: The 'screenShot' keyword was called. Could not save the captured screenshot";
		}
		return "Screenshot: " + destFile.toString();
	}

	// Try to write to an Excel file to a specified cell.
	public static String writeToExcelCell() {
		String writtenText = "";
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'writeToExcelCell' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'writeToExcelCell' keyword was called. The web element was not found: '" + webElement + "'";
			}
			writtenText = getWebElement(webElement).getText();
			String excelFileName = TestData.split(":", 4)[0];
			String excelSheetName = TestData.split(":", 4)[1];
			String excelColumnName = TestData.split(":", 4)[2];
			String excelRowName = TestData.split(":", 4)[3];
			excelDataOverview = System.getProperty("user.dir") + "/src/test/java/test_data/" + excelFileName;
			DataOverview = new ExcelWriter(excelDataOverview);
			DataOverview.writeCellDataString(excelDataOverview, excelSheetName, excelColumnName, excelRowName, writtenText);
		}
		catch (Throwable t) {
			logger.info("Failed: The 'writeToExcelCell' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'writeToExcelCell' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed: The following text was found and saved to the specified Excel file: '" + writtenText + "'";
	}

	// Try to write to an Excel file going straight down a column.
	public static String writeToExcelColumn() {
		String writtenText = "";
		try {
			Thread.sleep(1000);
			explicitWait(true);
			if (checkerValue == "Fail") {
				logger.info("Failed: The 'writeToExcelColumn' keyword was called. The web element was not found: '" + webElement + "'");
				return "Failed: The 'writeToExcelColumn' keyword was called. The web element was not found: '" + webElement + "'";
			}
			writtenText = getWebElement(webElement).getText();
			String excelFileName = TestData.split(":", 3)[0];
			String excelSheetName = TestData.split(":", 3)[1];
			String excelColumnName = TestData.split(":", 3)[2];
			excelDataOverview = System.getProperty("user.dir") + "/src/test/java/test_data/" + excelFileName;
			DataOverview = new ExcelWriter(excelDataOverview);
			DataOverview.writeCellDataInt(excelDataOverview, excelSheetName, excelColumnName, excelRowNum, writtenText);
			excelRowNum++;
		}
		catch (Throwable t) {
			logger.info("Failed: The 'writeToExcelColumn' keyword was called. The web element was not found: '" + webElement + "'");
			return "Failed: The 'writeToExcelColumn' keyword was called. The web element was not found: '" + webElement + "'";
		}
		return "Passed: The following text was found and saved to the specified Excel file: '" + writtenText + "'";
	}

	// Try to wait for 5 seconds.
	public static String waitFor() {
		try {
			Thread.sleep(5000);
		}
		catch (Throwable t) {
			logger.info("Failed: The 'waitFor' keyword was called. Could not wait for 5 seconds");
			return "Failed: The 'waitFor' keyword was called. Could not wait for 5 seconds";
		}
		return "Passed";
	}

	// Locates the web element by the given path and path type.
	public static WebElement getLocator(String locator) throws Exception {
		String[] split = locator.split(":", 2);
		String locatorType = split[0];
		String locatorValue = split[1];
		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.tagName(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else
			throw new Exception("Failed: Unknown locator type: '" + locatorType + "'");
	}

	// Locates multiple web elements by the given path and and path type.
	public static List<WebElement> getLocators(String locator) throws Exception {
		String[] split = locator.split(":", 2);
		String locatorType = split[0];
		String locatorValue = split[1];
		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Failed: Unknown locator type: '" + locatorType + "'");
	}

	// Try's to wait for the element to load and become visible on screen before continuing.
	public static void explicitWait(boolean firstTry) {
		checkerValue = "Pass";
		try {
			new WebDriverWait(driver, Integer.parseInt(Wait.getExplicitWait())).until(ExpectedConditions.visibilityOf(getWebElement(webElement)));
		}
		catch (org.openqa.selenium.StaleElementReferenceException ser) {
			if (firstTry)explicitWait(false);
		}
		catch (Throwable t) {
			try {
				scrollImplicitWait();
				new Actions(driver).moveToElement(getWebElement(webElement)).build().perform();
			}
			catch (Throwable e) {
				checkerValue = "Fail";
			}
			finally {
				regularImplicitWait();
			}
		}
	}
}
