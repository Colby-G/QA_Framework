package com.web_app.keyword_driver.framework.excelReader;

import java.io.FileInputStream;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.web_app.keyword_driver.framework.keywords.Keywords;
import com.web_app.keyword_driver.framework.testBase.LoggerHelper;

public class ExcelReader {
	public FileInputStream fis = null;
	private final static Logger logger = LoggerHelper.getLogger(Keywords.class);
	XSSFWorkbook workbook;
	String excelPath;
	String fName;
	XSSFSheet sheet;

	// Gets the path to the Excel file and opens it to read the data.
	public ExcelReader(String excelPath) {
		this.excelPath = excelPath;
		fName = excelPath.substring(excelPath.lastIndexOf("/") + 1);
		try {
			fis = new FileInputStream(excelPath);
			workbook = new XSSFWorkbook(fis);
			fis.close();
		}
		catch (Throwable t) {
			logger.info("Failed: Could not find or read the Excel file that will be read");
		}
	}

	// Returns the row count in the Excel sheet.
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			return 0;
		}
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum();
			XSSFCell cell = sheet.getRow(number).getCell(2);
			while (cell == null) {
				number = number - 1;
				cell = sheet.getRow(number).getCell(2);
			}
			return number + 1;
		}
	}

	// Returns the data from a cell in the Excel sheet.
	public String getCellData(String sheetName, String colName, int rowNum) {
		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);
		XSSFRow row = sheet.getRow(0);
		int col_Num = 0;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
				col_Num = i;
				break;
			}
		}
		row = sheet.getRow(rowNum - 1);
		XSSFCell cell = row.getCell(col_Num);
		if (cell == null) {
			return "";
		}
		if (cell.getCellType() != CellType.STRING) {
			cell.setCellType(CellType.STRING);
		}
		String cellInfo = cell.getStringCellValue();
		return cellInfo;
	}
}
