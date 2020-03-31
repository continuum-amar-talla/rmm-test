package org.continuum.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader
{
	public String path;
	public static FileInputStream fis = null;
	public static FileOutputStream fos = null;
	public static XSSFWorkbook workbook = null;
	public static XSSFSheet sheet = null;
	public static XSSFRow row = null;
	public static XSSFCell cell = null;
	
	public ExcelReader(String path)
	{
		this.path = path;
		try
		{
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			fis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// returns true if sheet exist with the given name when sheet name is passed as an argument
	public boolean isSheetExist(String sheetName)
	{
		int index = workbook.getSheetIndex(sheetName);
		if(index == -1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	// returns the number of rows in a sheet when sheet name is passed as an argument
	public int getRowCount(String sheetName)
	{
		if(!(isSheetExist(sheetName)))
		{
			return -1;
		}
		else
		{
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			int rows = sheet.getLastRowNum() + 1;
			return rows;
		}
	}
	
	// returns the number of columns when sheet name is passed as an argument
	public int getColumnCount(String sheetName)
	{
		if(!(isSheetExist(sheetName)))
		{
			return -1;
		}
		else
		{
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			if(row == null)
			{
				return -1;
			}
			int columns = row.getLastCellNum();
			return columns;
		}
	}
	
	// returns column number as in when sheet and column names are passed as an argument
	public int getColumnNumber(String sheetName, String colName)
	{
		if(!(isSheetExist(sheetName)))
		{
			return -1;
		}
		else
		{
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			int colNum = -1;
			for(int i = 0; i < row.getLastCellNum(); i++)
			{
				if(getCellData(sheetName, i, 1).equalsIgnoreCase(colName))
				{
					colNum = i;
					break;
				}
			}
			return colNum;
		}
	}
	
	// returns the row number when sheet name, column name and cell value are passed as an argument
	public int getCellRowNumber(String sheetName, String colName, String cellValue)
	{
		if(!(isSheetExist(sheetName)))
		{
			return -1;
		}
		else
		{
			for(int i = 2; i <= getRowCount(sheetName); i++)
			{
				if(getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue))
				{
					return i;
				}
			}
			return -1;
		}
	}
	
	// returns cell data as String when sheet name, column and row numbers are passed as an argument
	public String getCellData(String sheetName, int colNum, int rowNum)
	{
		if(!(isSheetExist(sheetName)))
		{
			return "";
		}
		else
		{
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if(row == null)
			{
				return "";
			}
			cell = row.getCell(colNum);
			if(cell == null)
			{
				return "";
			}
			if(cell.getCellType() == Cell.CELL_TYPE_STRING)
			{
				return cell.getStringCellValue();
			}
			else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
			{
				cell.setCellType(Cell.CELL_TYPE_STRING);
				return cell.getStringCellValue();
			}
			else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
			{
				return "";
			}
			else
			{
				return "";
			}
		}
	}
	
	/*
	 * returns cell data as String when sheet name, column name and row number are
	 * passed as an argument
	 */
	public String getCellData(String sheetName, String colName, int rowNum)
	{
		int colNum = getColumnNumber(sheetName, colName);
		
		return getCellData(sheetName, colNum, rowNum);
	}
	
	/*
	 * returns true if cell data is set properly when sheet name, column name, row
	 * number and data are passed as an argument
	 */
	public boolean setCellData(String sheetName, String colName, int rowNum, String data)
	{
		if(!(isSheetExist(sheetName)))
		{
			return false;
		}
		else
		{
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			int colNum = getColumnNumber(sheetName, colName);
			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);
			if(row == null)
			{
				row = sheet.createRow(rowNum - 1);
			}
			cell = row.createCell(colNum);
			if(cell == null)
			{
				cell = row.createCell(colNum);
			}
			try
			{
				fos = new FileOutputStream(path);
				cell.setCellValue(data);
				workbook.write(fos);
				fos.close();
				return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
	}
	
	// returns true if sheet is added properly when sheet name is passed as an argument
	public boolean addSheet(String sheetName)
	{
		try
		{
			fos = new FileOutputStream(path);
			workbook.createSheet(sheetName);
			workbook.write(fos);
			fos.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	// returns true if sheet is removed properly when sheet name is passed as an argument
	public boolean removeSheet(String sheetName)
	{
		if(!(isSheetExist(sheetName)))
		{
			return false;
		}
		else
		{
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			try
			{
				fos = new FileOutputStream(path);
				workbook.removeSheetAt(index);
				workbook.write(fos);
				fos.close();
				return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
	}
}