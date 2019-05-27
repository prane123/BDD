package Utilities;

import org.apache.poi.util.SystemOutLogger;

public class Retrieve{

	public static void main(String[] args) throws Throwable {
		
	
		ExcelfileUtil data = new ExcelfileUtil();
	
		System.out.println(data.rowCount("Sheet1"));
		
		System.out.println(data.getdata("sheet1", 1, 0));
		data.setdata("Sheet1", 1, 3, "FAIL");
}

}
