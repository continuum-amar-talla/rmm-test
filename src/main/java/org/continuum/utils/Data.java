package org.continuum.utils;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import org.continuum.setup.TestSetUp;

public class Data extends TestSetUp
{
	@DataProvider(name = "data")
	public Object[][] getData(Method m)
	{
		String sheetName = configProperties.getSheetName();
		int rows = excelReader.getRowCount(sheetName);
		String testCaseName = m.getName();
		int testCaseRowNum = 1;
		
		for(testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++)
		{
			String testCaseNameInExcelFile = excelReader.getCellData(sheetName, 0, testCaseRowNum);
			if(testCaseNameInExcelFile.equalsIgnoreCase(testCaseName))
			{
				break;
			}
		}
		
		int dataStartRowNum = testCaseRowNum + 2;
		int testRows = 0;
		while(!(excelReader.getCellData(sheetName, 0, dataStartRowNum + testRows).
				equalsIgnoreCase("endOfTestData")))
		{
			testRows++;
		}
		
		int colStartColNum = testCaseRowNum + 1;
		int testCols = 0;
		while(!(excelReader.getCellData(sheetName, testCols, colStartColNum).
				equalsIgnoreCase("")))
		{
			testCols++;
		}
		
		Object[][] data = new Object[testRows][1];
		int i = 0;
		Hashtable<String, String> table = null;
		for(int rowNum = dataStartRowNum; rowNum < (dataStartRowNum + testRows); rowNum++)
		{
			table = new Hashtable<String, String>();
			for(int colNum = 0; colNum < testCols; colNum++)
			{
				table.put(excelReader.getCellData(sheetName, colNum, colStartColNum), 
						excelReader.getCellData(sheetName, colNum, rowNum));
			}
			data[i][0] = table;
			i++;
		}
		return data;
	}
}