package com.web_app.keyword_driver.framework.excelWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.web_app.keyword_driver.framework.keywords.Keywords;
import com.web_app.keyword_driver.framework.testBase.LoggerHelper;

public class ExcelWriter {
	public FileInputStream fInStream = null;
	public FileOutputStream fOutStream = null;
	private final static Logger logger = LoggerHelper.getLogger(Keywords.class);
	XSSFWorkbook workbookInAndOut;
	String excelFilePath;
	String eFileName;
	XSSFSheet workingSheet;

	// Gets the path to the Excel file and opens it to write to it.
	public ExcelWriter(String ePath) {
		this.excelFilePath = ePath;
		eFileName = ePath.substring(ePath.lastIndexOf("/") + 1);
		try {
			fInStream = new FileInputStream(ePath);
			workbookInAndOut = new XSSFWorkbook(fInStream);
			fInStream.close();
		}
		catch (Throwable t) {
			logger.info("Failed: Could not find or read the Excel file that will be written to");
		}
	}

	// Writes the data to a cell in the Excel file by using an integer as the row reference.
	public void writeCellDataInt(String efPath, String sName, String cName, int rNum, String inputText) {
		int indexSheet = workbookInAndOut.getSheetIndex(sName);
		workingSheet = workbookInAndOut.getSheetAt(indexSheet);
		XSSFRow workingRow = workingSheet.getRow(0);
		int workingColumn = 0;
		for (int i = 0; i < workingRow.getLastCellNum(); i++) {
			if (workingRow.getCell(i).getStringCellValue().trim().equals(cName.trim())) {
				workingColumn = i;
				break;
			}
		}
		workingRow = workingSheet.getRow(rNum - 1);
		XSSFCell workingCell = workingRow.getCell(workingColumn);
		if (workingCell == null) {
			workingCell = workingRow.createCell(workingColumn);
		}
		workingCell.setCellValue(inputText);
		try {
			fOutStream = new FileOutputStream(efPath);
			workbookInAndOut.write(fOutStream);
			fOutStream.close();
		}
		catch (Throwable t) {
			logger.info("Failed: Could not find or write to the Excel file");
		}
	}

	// Writes the data to a cell in the Excel file by using a string name as the row reference.
	public void writeCellDataString(String efPath, String sName, String cName, String rNumString, String inputText) {
		int indexSheet = workbookInAndOut.getSheetIndex(sName);
		workingSheet = workbookInAndOut.getSheetAt(indexSheet);
		XSSFRow workingRow = workingSheet.getRow(0);
		int workingColumn = 0;
		for (int i = 0; i < workingRow.getLastCellNum(); i++) {
			if (workingRow.getCell(i).getStringCellValue().trim().equals(cName.trim())) {
				workingColumn = i;
				break;
			}
		}
		int rNumInt = Integer.parseInt(rNumString);
		workingRow = workingSheet.getRow(rNumInt - 1);
		XSSFCell workingCell = workingRow.getCell(workingColumn);
		if (workingCell == null) {
			workingCell = workingRow.createCell(workingColumn);
		}
		workingCell.setCellValue(inputText);
		try {
			fOutStream = new FileOutputStream(efPath);
			workbookInAndOut.write(fOutStream);
			fOutStream.close();
		}
		catch (Throwable t) {
			logger.info("Failed: Could not find or write to the Excel file");
		}
	}
}
