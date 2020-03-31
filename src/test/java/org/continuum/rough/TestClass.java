package org.continuum.rough;

import org.continuum.utils.ExcelReader;

public class TestClass
{
	public static void main(String[] args)
	{
		ExcelReader excelReader = new ExcelReader("C:\\Users\\amar.talla\\Desktop\\Amar Talla"
				+ "\\Workspace\\Project Workspace\\rmm-delta-framework\\src\\test\\resources"
				+ "\\testData\\TestDataSheet.xlsx");
		System.out.println(excelReader.getRowCount("TestData"));
		excelReader.getCellData("TestData", 1, 1);
		System.out.println(excelReader.getRowCount("Amar"));
	}
}