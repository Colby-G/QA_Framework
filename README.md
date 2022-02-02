# Initial Installs, Downloads, and Setups to Work on the Framework:
###### note: you do *NOT* need to download or install these if you are only running the executable version of the framework
- Download and install "Eclipse IDE for Enterprise Java and Web Developers"
     - Install TestNG in the IDE itself
- Download and install Java 16 (including the latest JRE)
     - Setup Java system environment path(s)
- Download and install Maven
     - Setup Maven system environment path(s)
- Download the GitHub repository
     - Place it in the "eclipse-workspace" folder in your user folder
- Include any additional dependencies in the POM.xml file within the framework
     - Do this within Eclipse itself after importing the framework into Eclipse
- Specify any properties in POM.xml file for the build path (compiler source and target)
     - Do this within Eclipse itself after importing the framework into Eclipse
- Check the browser version you wish to use (browser on your computer) and see if it matches the drivers in this framework
     - If it does NOT match, get the driver version from online that matches the browser you are using

# What Each File/Folder Does in the Framework:
- Browser.java file: Selects which browser driver to use (the framework includes a Chrome and a FireFox driver)
- OS.java file: Selects which operating system you are using (the framework works with Mac and Windows)
- ExcelReader.java file: Reads the Excel file (both the test cases/steps and the data needed), counts the rows and goes column by column for the number of rows to collect the data
- ExcelWriter.java file: Writes to an Excel file, it can write both going straight down a column or it can write anywhere as long as you specify a row number and column name
- Keywords.java file: Performs the Selenium action(s) specified for that keyword called out from the Excel file
- ReportUtil.java file: Takes all the information from the test and create HTML reports based on the outcomes
- LoggerHelper.java file: A simple helper files calling on the log4j properties
- TestBase.java file: Initializes all the properties files, calls on the driver and OS to start up, and includes the screenshot functions
- Wait.java file: Provides the framework with 3 different waiting options to choose from
- Runner.java file: This contains the "main" class that the executable jar file calls on to run the whole framework, without using the executables this is the file you would run the whole framework from
- TestController.java file: The second main file in the whole framework, right under the "Runner.java" file, this file calls everything else to run during testing
- properties_files folder: Contains all the properties files for all of the test cases
- reports folder: Contains all the reports files (HTML files) that describe the outcome of the whole test
- screenshots folder: Contains all the screenshots from the test, it contains two seperate folders for failed and keyword screenshots
- test_data folder: Contains all the Excel files need, where all the test cases and steps are written out
- drivers folder: Contains the Chrome and FireFox drivers needed for the framework
- test-output folder: This folder has all of the output files containing to the TestNG dependency, just contains the generic output files
- automation.out file: Has a history of all the logged console statements that were printed using the "logger.info" command in the framework
- pom.xml file: Has all of the dependences and properties needed to run the framework
- Test.jar file: This is the executable jar file that the batch/shell scripts call on to then call on the "main" class and call every other file in the framework needed to run properly
- MacOS_QA_Runner.sh file: An executable shell script for Macintosh or Linux users that can be double clicked to run the whole framework
- Windows_QA_Runner.bat file: An executable batch script for Windows users that can be double clicked to run the whole framework

# Framework Keywords:
###### note: this is a keyword driven framework and these are the current keywords that can be used in the Excel file(s) when creating the test steps
1. "navigation": used to open a new driver instance and go to the specified URL
2. "emptyNewTab": this keyword will open a new blank tab with no URL specified
3. "linkedNewTab": this keyword will open a new tab with the specified URL loaded
4. "focusNewTab": allows the user to switch the testing focus to the new tab
5. "focusOldTab": allows the user to switch the testing focus to the old tab
6. "closeTab": will close the currently focused tab
7. "mouseOver": simulates a user hovering their mouse over an object
8. "leftClick": simulates a user left clicking on the mouse
9. "rightClick": simulates a user right clicking on the mouse
10. "doubleClick": simulates a user double clicking the left side of the mouse
11. "inputText": will type the specified characters into the specified element
12. "verifyText": will verify on screen text as compared to exactly how you expect it to look
13. "selectByValue": will select an on screen option based on it's value
14. "selectByVisibleText": will select an on screen option based on it's visible text
15. "selectByIndex": will select an on screen option based on it's index value
16. "listCheck": this keyword will check a list of elements and see if there are any duplicates in the list
17. "receiveText": this keyword will gather the text located at the specified element and will return exactly the text that is there
18. "containsText": this keyword is like "verifyText", except that instead of needed to know the exact text word for word you can check if any part of the phrase has the text you specify
19. "screenShot": this keyword will capture a screenshot of what is on the current screen, much like the failures do automatically
20. "writeToExcelCell": this keyword will take in some parameters (file name, sheet name, column name, and row number) then will write some captured text to the Excel file in that specified cell location
21. "writeToExcelColumn": this keyword is like writeToExcel except it takes in less parameters (file name, sheet name, and column name) and it write the data to the excel in order starting from row 2 (right under the column headers), it is useful when downloading the DTs.
22. "waitFor": this keyword will allow the user to tell the test to stop for 5 seconds (this will only stop inputs and outputs, pages and elements will still continue to load and be displayed)

# How to Add Additional Keywords:
- Locate the "Keywords.java" file in the framework and open it up
     - Open it up within the Eclipse IDE for a better format
- Simply add a new function to the end of this file and specify the keyword as a string
     - In the body of the function include the Selenium action you wish to perform, be sure to include the webElement (comes from the Excel file and properties files)

# How to Write a New Test Case:
- Start in the "TestSuite1.xlsx" Excel file and add a new sheet for the new test case and also add the test case number and name to the first sheet in the Excel file
     - In this file you will specify if you want the case to run and you will write all your steps in the test case sheet too
     - In this test case sheet specify all the details of each test step
       - Name, number, data name, xpath variable name, and the keyword
- Then go to the "TestSuite1Data.xlsx" Excel file to create a new sheet for that new test case and here add the data name and value for the needed inputs
- Next, open the framework in Eclipse and create a new properties file for this new test case
     - Name this the same as your test case and within this add your xpaths linked to the commands in the testing application
     - Each xpath name should be the same as the xpath name you entered into your Excel file

# Test Reports Function:
- Each test case will create a new HTML report file that shows all the details of that test case and the outcome of it
- There will also be an overview HTML report that will show the information and outcomes of each test case as well
- Each of the test case reports goes into the details on each test step
- From this you can see where the case failed (which step it failed on) and how it failed
- If a step does fail then there is also a screenshot that will be provide to show you where on the web site/web application it failed at and how
- All of the report files can be found in the "reports" folder (QA_Framework/src/test/java/reports) and the screenshots are in a "screenshots" folder within the "reports" folder

# How to Run an Automation Test:
- There are 2 ways of running tests using the framework:
     - The first way requires the download and installation of all the above softwares and dependences
       - After downloading all of these, importing the project, and creating your cases, you will right click on the "Runner.java", locate and hover over the "Run As" button, then click on "Java Application"
       - The framework should display some messages in the "Console" section in Eclipse and the test should proceed on screen
     - The second way of running tests is by simply double clicking the correct executable for your computer
       - After downloading the framework and creating your cases, you will locate the correct executable (MacOS_QA_Runner.sh or Windows_QA_Runner.bat) and double click it
       - The framework should open a console on the device and the test should proceed on screen
           - MacOS_QA_Runner.sh should be used for any Mac or Linux user
           - Windows_QA_Runner.bat should be used for any Windows user