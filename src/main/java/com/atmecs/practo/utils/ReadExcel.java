package com.atmecs.practo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * Take the value from excel file and passing the value to website.
 * Apache poi Supports to read value from excel file.
 * 
 * @author ranjitha.selvam
 */
public class ReadExcel {
	
	static Workbook book;
	static Sheet sheet;
	/*
	 * @param File Name
	 * 
	 * @param Sheet Name
	 * 
	 */
	
    public static Object[][] getExcel(String path,String sheetName) throws InvalidFormatException {
		File file=new File(path);
	FileInputStream read = null;
	try {
	read = new FileInputStream(file);
	} catch (FileNotFoundException e) {
	e.printStackTrace();
	}
	try {
	book = WorkbookFactory.create(file);
	} catch (IOException e) {
	e.printStackTrace();
	}
	sheet = book.getSheet(sheetName);
	Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	for (int rowCount = 0; rowCount < sheet.getLastRowNum(); rowCount++) {
	for (int cellCount = 0; cellCount < sheet.getRow(0).getLastCellNum(); cellCount++) {
	data[rowCount][cellCount] = sheet.getRow(rowCount + 1).getCell(cellCount).toString();
	}
	}
	return data;
	}
	}





