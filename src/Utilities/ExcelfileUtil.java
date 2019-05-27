package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelfileUtil {
Workbook wb;
//it loads all excel sheet
public ExcelfileUtil() throws Throwable
{
	FileInputStream fis = new FileInputStream("./TestInputs/InputSheet.xlsx");
	wb = WorkbookFactory.create(fis);
}
public int rowCount(String sheetname)
{
return wb.getSheet(sheetname).getLastRowNum();
}
public int colcount(String sheetname, int rownum)
{
	return wb.getSheet(sheetname).getRow(rownum).getLastCellNum();
	
}
public String getdata(String sheetname, int rownum, int colnum)
{
	String data="";
	if(wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata = (int) wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getNumericCellValue();
		data=String.valueOf(celldata);
	}else
	{
		data=wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getStringCellValue();
		}
	return data;
}
public void setdata(String sheetname, int rownum, int column, String status) throws Throwable
{
	Sheet sh = wb.getSheet(sheetname);
	Row rownumber = sh.getRow(rownum);
	Cell cell = rownumber.createCell(column);
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("pass"))
	{
		CellStyle style = wb.createCellStyle();
		Font font= wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		style.setFont(font);
		rownumber.getCell(column).setCellStyle(style);
		
		
	}else
	
		if(status.equalsIgnoreCase("FAIL"))
		{
			CellStyle style =wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rownumber.getCell(column).setCellStyle(style);
			
		} else
			if(status.equalsIgnoreCase("not Executed"))
		{
				CellStyle style =wb.createCellStyle();
				Font font = wb.createFont();
				font.setColor(IndexedColors.BLUE.getIndex());
				font.setBold(true);
				style.setFont(font);
				rownumber.getCell(column).setCellStyle(style);
		}
	
		FileOutputStream fos = new FileOutputStream("./TestOutput/op.xlsx");
		
			wb.write(fos);
			fos.close();
			
	}
	
}


