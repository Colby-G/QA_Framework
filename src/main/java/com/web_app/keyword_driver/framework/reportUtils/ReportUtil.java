package com.web_app.keyword_driver.framework.reportUtils;

import java.io.*;
import java.util.ArrayList;

public class ReportUtil {
	public static int scriptNumber = 1;
	public static float allCaseNumber = 0;
	public static float passCounter = 0;
	public static float failCounter = 0;
	public static boolean newTest = true;
	public static String resultFilename;
	public static String currentDir;
	public static String suiteName;
	public static String OVERALL_END_TIME = "Test Not Complete, A Framework Error Was Thrown";
	public static String PASSPERCENT = "None Passed, All Were Skipped";
	public static String FAILPERCENT = "None Failed, All Were Skipped";
	public static ArrayList<String> testStepID = new ArrayList<String>();
	public static ArrayList<String> description = new ArrayList<String>();
	public static ArrayList<String> keyword = new ArrayList<String>();
	public static ArrayList<String> teststatus = new ArrayList<String>();
	public static ArrayList<String> screenShotPath = new ArrayList<String>();

	// Get the location where the reports should be saved to.
	public static String getReportDir() {
		return System.getProperty("user.dir") + "/src/test/java/reports/";
	}

	// Set the overall report file name of the test cases.
	private static String getOverAllReportFilePath(String fileName) {
		return getReportDir() + "Test_Results_Overview.html";
	}

	// Creating a HyperText Mark up Language log file for each test case separately.
	private static String getTestFilePath(String testCaseName) {
		return getReportDir() + suiteName + "_" + testCaseName + ".html";
	}

	// Creating the actual HyperText Mark up Language log file by adding all the tables and initial words onto it.
	public static void startTesting(String filename, String testStartTime, String env, String rel) {
		resultFilename = getOverAllReportFilePath(filename);
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			fstream = new FileWriter(getOverAllReportFilePath(filename));
			out = new BufferedWriter(fstream);
			String ENVIRONMENT = env;
			String RELEASE = rel;
			out.newLine();
			out.write("<html>\n");
			out.write("<HEAD>\n");
			out.write("<TITLE>Test Results Overview</TITLE>\n");
			out.write("</HEAD>\n");
			out.write("<body>\n");
			out.write("<h4 align=center><FONT COLOR=660066 FACE=AriaL SIZE=6><b>Automation Test Results Overview</b></h4>\n");
			out.write("<table border=1 cellspacing=1 cellpadding=1>\n");
			out.write("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4>Test Details:</h4>\n");
			out.write("<tr>\n");
			out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Start Time</b></td>\n");
			out.write("<td width=250 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + testStartTime + "</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");
			out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>End Time</b></td>\n");
			out.write("<td width=250 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + OVERALL_END_TIME + "</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");
			out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Testing Suite</b></td>\n");
			out.write("<td width=250 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + ENVIRONMENT + "</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");
			out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Batch Number</b></td>\n");
			out.write("<td width=250 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + RELEASE + "</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");
			out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Percent Passed</b></td>\n");
			out.write("<td width=250 align=left bgcolor=#00FF00><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + PASSPERCENT + "</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");
			out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Percent Failed</b></td>\n");
			out.write("<td width=250 align=left bgcolor=#FF0000><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + FAILPERCENT + "</b></td>\n");
			out.write("</tr>\n");
			out.write("</table>\n");
			out.close();
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		finally {
			fstream = null;
			out = null;
		}
	}

	// Write the table headers for the test cases table.
	public static void startSuite(String suiteName) {
		ReportUtil.suiteName = suiteName;
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			fstream = new FileWriter(resultFilename, true);
			out = new BufferedWriter(fstream);
			out.write("<h4><FONT COLOR=660000 FACE=Arial SIZE=4>" + suiteName + " Report:</h4>\n");
			out.write("<table border=1 cellspacing=1 cellpadding=1 width=100%>\n");
			out.write("<tr>\n");
			out.write("<td width=10% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Test Case Number</b></td>\n");
			out.write("<td width=40% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Case Description</b></td>\n");
			out.write("<td width=10% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Status</b></td>\n");
			out.write("<td width=20% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Start Time</b></td>\n");
			out.write("<td width=20% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>End Time</b></td>\n");
			out.write("</tr>\n");
			out.close();
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		finally {
			fstream = null;
			out = null;
		}
	}

	// End the table by closing it when called.
	public static void endSuite() {
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			fstream = new FileWriter(resultFilename, true);
			out = new BufferedWriter(fstream);
			out.write("</table>\n");
			out.close();
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		finally {
			fstream = null;
			out = null;
		}
	}

	// Gather the information needed from the Excel Reader and add it to the table for each test case and test step.
	public static void addTestCase(String testCaseName, String testCaseDescription, String testCaseStartTime, String testCaseEndTime, String status, String rMode) {
		newTest = true;
		FileWriter fstream = null;
		BufferedWriter out = null;
		// Building the test steps page.
		try {
			newTest = true;
			if (status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip")) {
			}
			else {
				fstream = new FileWriter(getTestFilePath(testCaseName));
				out = new BufferedWriter(fstream);
				out.write("<html>");
				out.write("<head>");
				out.write("<title>");
				out.write(suiteName + " " + testCaseName + " Detailed Step Report");
				out.write("</title>");
				out.write("</head>");
				out.write("<body>");
				out.write("<h4><FONT COLOR=660000 FACE=Arial SIZE=4>" + suiteName + " " + testCaseName + " Detailed Step Report: " + testCaseDescription + "</h4>");
				out.write("<table border=1 cellspacing=1 cellpadding=1 width=100%>");
				out.write("<tr> ");
				out.write("<td align=center width=10% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Test Step Number</b></td>");
				out.write("<td align=center width=40% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Step Description</b></td>");
				out.write("<td align=center width=10% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Keyword</b></td>");
				out.write("<td align=center width=20% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Status</b></td>");
				out.write("<td align=center width=20% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Screen Shot</b></td>");
				out.write("</tr>");
				if (description != null) {
					for (int i = 0; i < description.size(); i++) {
						out.write("<tr>");
						out.write("<td align=center width=10%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + testStepID.get(i) + "</b></td>");
						out.write("<td align=center width=40%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + description.get(i) + "</b></td>");
						out.write("<td align=center width=10%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + keyword.get(i) + "</b></td>");
						if (teststatus.get(i).startsWith("Passed")) {
							out.write("<td width=20% align=center bgcolor=#00FF00><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + teststatus.get(i) + "</b></td>\n");
						}
						else if (teststatus.get(i).startsWith("Failed") || teststatus.get(i).startsWith("Partially Failed")) {
							out.write("<td width=20% align=center bgcolor=#FF0000><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + teststatus.get(i) + "</b></td>\n");
						}
						else if (teststatus.get(i).startsWith("Skipped")) {
							out.write("<td width=20% align=center bgcolor=#FFFF00><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>Skipped</b></td>\n");
						}
						if (screenShotPath.get(i) != null) {
							out.write("<td align=center width=20%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b><a href=" + screenShotPath.get(i) + " target=_blank>Screen Shot</a></b></td>");
						}
						else {
							out.write("<td align=center width=20%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>&nbsp;</b></td>");
						}
						out.write("</tr>");
					}
				}
				out.close();
			}
			// Building the test cases (overview) page.
			fstream = new FileWriter(resultFilename, true);
			out = new BufferedWriter(fstream);
			out.write("<tr>\n");
			if (status.equals("Skipped") && rMode.equals("N")) {
				out.write("<td width=10% align=center bgcolor=#FFFF00><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + testCaseName + "</b></td>\n");
				out.write("<td width=40% align=center><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + testCaseDescription + "</b></td>\n");
				out.write("<td width=10% align=center bgcolor=#FFFF00><FONT COLOR=153E7E FACE=Arial SIZE=1><b>Skipped</b></td>\n");
			}
			else if (status.equals("Skipped") && rMode.equals("Passed")) {
				out.write("<td width=10% align=center bgcolor=#80FF80><FONT COLOR=#153E7E FACE=Arial SIZE=1><b><a href=file:///" + getTestFilePath(testCaseName) + ">" + testCaseName + "</a></b></td>\n");
				out.write("<td width=40% align=center><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + testCaseDescription + "</b></td>\n");
				out.write("<td width=10% align=center bgcolor=#80FF80><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>Previously Passed</b></td>\n");
				passCounter++;
				allCaseNumber++;
			}
			else if (status.equals("Skipped") && rMode.equals("Failed")) {
				out.write("<td width=10% align=center bgcolor=#FF8080><FONT COLOR=#153E7E FACE=Arial SIZE=1><b><a href=file:///" + getTestFilePath(testCaseName) + ">" + testCaseName + "</a></b></td>\n");
				out.write("<td width=40% align=center><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + testCaseDescription + "</b></td>\n");
				out.write("<td width=10% align=center bgcolor=#FF8080><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>Previously Failed</b></td>\n");
				failCounter++;
				allCaseNumber++;
			}
			else if (status.startsWith("Passed") && rMode.equals("Y")) {
				out.write("<td width=10% align=center bgcolor=#00FF00><FONT COLOR=#153E7E FACE=Arial SIZE=1><b><a href=file:///" + getTestFilePath(testCaseName) + ">" + testCaseName + "</a></b></td>\n");
				out.write("<td width=40% align=center><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + testCaseDescription + "</b></td>\n");
				out.write("<td width=10% align=center bgcolor=#00FF00><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>Passed</b></td>\n");
				passCounter++;
				allCaseNumber++;
			}
			else if ((status.startsWith("Failed")  && rMode.equals("Y")) || (status.startsWith("Partially Failed") && rMode.equals("Y"))) {
				out.write("<td width=10% align=center bgcolor=#FF0000><FONT COLOR=#153E7E FACE=Arial SIZE=1><b><a href=file:///" + getTestFilePath(testCaseName) + ">" + testCaseName + "</a></b></td>\n");
				out.write("<td width=40% align=center><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + testCaseDescription + "</b></td>\n");
				out.write("<td width=10% align=center bgcolor=#FF0000><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>Failed</b></td>\n");
				failCounter++;
				allCaseNumber++;
			}
			out.write("<td width=20% align=center><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + testCaseStartTime + "</b></td>\n");
			out.write("<td width=20% align=center><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>" + testCaseEndTime + "</b></td>\n");
			out.write("</tr>\n");
			scriptNumber++;
		}
		catch (IOException e) {
			e.printStackTrace();
			return;
		}
		finally {
			try {
				out.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		testStepID = new ArrayList<String>();
		description = new ArrayList<String>();
		keyword = new ArrayList<String>();
		teststatus = new ArrayList<String>();
		screenShotPath = new ArrayList<String>();
		newTest = false;
	}

	// Gathering the test steps information.
	public static void addKeyword(String tsid, String desc, String key, String stat, String path) {
		testStepID.add(tsid);
		description.add(desc);
		keyword.add(key);
		teststatus.add(stat);
		screenShotPath.add(path);
	}

	// Updating the end times for each test case.
	public static void updateEndTimes(String endTime) {
		StringBuffer buf = new StringBuffer();
		try {
			FileInputStream fstream = new FileInputStream(resultFilename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				if (strLine.indexOf("END_TIME") != -1) {
					strLine = strLine.replace("END_TIME", endTime);
				}
				buf.append(strLine);
			}
			in.close();
			FileOutputStream fos = new FileOutputStream(resultFilename);
			DataOutputStream output = new DataOutputStream(fos);
			output.writeBytes(buf.toString());
			fos.close();
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Updating the overall end time for the full test.
	public static void updateOverallEndTime(String endTime) {
		StringBuffer buf = new StringBuffer();
		try {
			FileInputStream fstream = new FileInputStream(resultFilename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				if (strLine.indexOf("Test Not Complete, A Framework Error Was Thrown") != -1) {
					strLine = strLine.replace("Test Not Complete, A Framework Error Was Thrown", endTime);
				}
				buf.append(strLine);
			}
			in.close();
			FileOutputStream fos = new FileOutputStream(resultFilename);
			DataOutputStream output = new DataOutputStream(fos);
			output.writeBytes(buf.toString());
			fos.close();
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Updating the passing percent for the full test.
	public static void updatePassPercent() {
		if (allCaseNumber != 0) {
			float floatPassPercent = (passCounter / allCaseNumber) * 100;
			String PASSPERCENT = String.format("%.2f", floatPassPercent);
			StringBuffer buf = new StringBuffer();
			try {
				FileInputStream fstream = new FileInputStream(resultFilename);
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				while ((strLine = br.readLine()) != null) {
					if (strLine.indexOf("None Passed, All Were Skipped") != -1) {
						strLine = strLine.replace("None Passed, All Were Skipped", PASSPERCENT + "%");
					}
					buf.append(strLine);
				}
				in.close();
				FileOutputStream fos = new FileOutputStream(resultFilename);
				DataOutputStream output = new DataOutputStream(fos);
				output.writeBytes(buf.toString());
				fos.close();
			}
			catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

	// Updating the failing percent for the full test.
	public static void updateFailPercent() {
		if (allCaseNumber != 0) {
			float floatFailPercent = (failCounter / allCaseNumber) * 100;
			String FAILPERCENT = String.format("%.2f", floatFailPercent);
			StringBuffer buf = new StringBuffer();
			try {
				FileInputStream fstream = new FileInputStream(resultFilename);
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				while ((strLine = br.readLine()) != null) {
					if (strLine.indexOf("None Failed, All Were Skipped") != -1) {
						strLine = strLine.replace("None Failed, All Were Skipped", FAILPERCENT + "%");
					}
					buf.append(strLine);
				}
				in.close();
				FileOutputStream fos = new FileOutputStream(resultFilename);
				DataOutputStream output = new DataOutputStream(fos);
				output.writeBytes(buf.toString());
				fos.close();
			}
			catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}
}
