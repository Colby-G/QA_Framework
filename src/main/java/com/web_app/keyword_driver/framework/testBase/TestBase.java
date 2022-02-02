package com.web_app.keyword_driver.framework.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import com.web_app.keyword_driver.framework.enums.Browsers;
import com.web_app.keyword_driver.framework.enums.OS;
import com.web_app.keyword_driver.framework.excelReader.ExcelReader;
import com.web_app.keyword_driver.framework.excelWriter.ExcelWriter;
import com.web_app.keyword_driver.framework.keywords.Keywords;
import java.nio.file.Files;

public class TestBase {
	public static WebDriver driver;
	public static Properties Repository = new Properties();
	public static Properties Config = new Properties();
	public static Properties AppText = new Properties();
	public static ExcelReader SuiteData;
	public static ExcelReader TestStepData;
	public static ExcelWriter SuiteDataOut;
	public static ExcelWriter DataOverview;
	public static String keyword;
	public static String webElement;
	public static String TestDataField;
	public static String TestData;
	public static String TSID;
	public static String Description;
	public static int excelRowNum = 2;
	public static File f;
	public static FileInputStream FI;
	public static String excelDataOverview;
	public static String excelSuite = System.getProperty("user.dir") + "/src/test/java/test_data/TestSuite1.xlsx";
	static String excelDataSuite = System.getProperty("user.dir") + "/src/test/java/test_data/TestSuite1Data.xlsx";
	static String ssReportDirectory = System.getProperty("user.dir") + "/src/test/java/reports/screenshots/failed_screenshots";
	private final static Logger logger = LoggerHelper.getLogger(Keywords.class);

	// Initializes the properties files to get the open and ready to read.
	public static void Initialize() throws IOException {
		TestStepData = new ExcelReader(excelDataSuite);
		SuiteData = new ExcelReader(excelSuite);
		f = new File(System.getProperty("user.dir") + "/src/test/java/properties_files");
		for (File file: f.listFiles()) {
		    if (!file.isDirectory() && !file.equals(new File(System.getProperty("user.dir") + "/src/test/java/properties_files/log4j.properties"))) {
		    	FI = new FileInputStream(file);
				Repository.load(FI);
		    }
		}
	}

	// Collects the current time when called on.
	public static String now(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat fm = new SimpleDateFormat();
		return fm.format(cal.getTime());
	}

	// Selects the OS and the driver type to run the test with.
	public static WebDriver selectBrowser(String browser) {
		if (System.getProperty("os.name").toLowerCase().contains(OS.WINDOW.name().toLowerCase())) {
			if (browser.equalsIgnoreCase(Browsers.CHROME.name())) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				regularImplicitWait();
			}
			else if (browser.equalsIgnoreCase(Browsers.FIREFOX.name())) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				regularImplicitWait();
			}
		}
		else if (System.getProperty("os.name").toLowerCase().contains(OS.MAC.name().toLowerCase())) {
			if (browser.equalsIgnoreCase(Browsers.CHROME.name())) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
				driver = new ChromeDriver();
				regularImplicitWait();
			}
			else if (browser.equalsIgnoreCase(Browsers.FIREFOX.name())) {
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver");
				driver = new FirefoxDriver();
				regularImplicitWait();
			}
		}
		return driver;
	}

	// Sets the framework implicit wait to the regular value as defined in the configuration file.
	public static void regularImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Wait.getImplicitWait()), TimeUnit.SECONDS);
	}

	// Sets the framework implicit wait to the scrolling value when called on by the framework.
	public static void scrollImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Wait.getScrollWait()), TimeUnit.SECONDS);
	}

	// Deletes the old report files form the folder location when a new test run starts up.
	public static void deleteOldReports() {
		f = new File(System.getProperty("user.dir") + "/src/test/java/reports");
		for (File file: f.listFiles()) {
		    if (!file.isDirectory()) {
		        file.delete();
		    }
		}
	}

	// Deletes the old screenshots from the folder location when a new test run starts up.
	public static void deleteScreenShots() {
		f = new File(System.getProperty("user.dir") + "/src/test/java/reports/screenshots/failed_screenshots");
		for (File file: f.listFiles()) {
		    if (!file.isDirectory()) {
		        file.delete();
		    }
		}
		f = new File(System.getProperty("user.dir") + "/src/test/java/reports/screenshots/keyword_screenshots");
		for (File file: f.listFiles()) {
		    if (!file.isDirectory()) {
		        file.delete();
		    }
		}
	}

	// Takes a screenshot when a failure occurs in the test cases.
	public static String getScreenShot(String fileName) {
		if (driver == null) {
			return null;
		}
		if (fileName == "") {
			fileName = "blank";
		}
		Reporter.log("A failure was recorded. See screenshot for details.");
		File destFile = null;
		SimpleDateFormat formater = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			destFile = new File(ssReportDirectory + "/" + fileName + "_" + formater.format(Calendar.getInstance().getTime()) + ".png");
			Files.copy(screFile.toPath(), destFile.toPath());
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath() + "'height='100' width='100'/></a>");
		}
		catch (Throwable t) {
			logger.info("Failed: Could not save the captured screenshot");
		}
		return destFile.toString();
	}
}
