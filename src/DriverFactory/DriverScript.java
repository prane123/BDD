package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExcelfileUtil;
import commonfunctionlibrary.Functionlibrary;

public class DriverScript {
	WebDriver driver ;
	ExtentReports report;
	ExtentTest logger;
	
	public void startTest() throws Throwable
	{
		
		ExcelfileUtil excel = new ExcelfileUtil();
		for(int i=1;i<=excel.rowCount("MasterTestCases");i++)
        {
			String moduleSatus = "";
        	if(excel.getdata("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
        	{
        		//Define Module name
        		String TCModule = excel.getdata("MasterTestCases", i, 1);
        		report = new ExtentReports("./Reports/"+TCModule+".html"+Functionlibrary.generatDate());
        		
        		logger=report.startTest(TCModule);
        			
        		
        		int rowcount = excel.rowCount(TCModule);
        		for(int j=1;j<=rowcount;j++)
        		{
        			String Description = excel.getdata(TCModule, j, 0);
        			String Object_Type=excel.getdata(TCModule, j, 1);
        			String Locator_Type = excel.getdata(TCModule, j, 2);
        			String Locator_Value =  excel.getdata(TCModule, j, 3);
        			String Test_Data =  excel.getdata(TCModule, j, 4);
        			try
        			{
        			if(Object_Type.equalsIgnoreCase("startBrowser"))
        			{
        				driver=Functionlibrary.startBrowser(driver);
        				logger.log(LogStatus.INFO,Description);
        			}
        			if(Object_Type.equalsIgnoreCase("openApplication"))
        			{
        				Functionlibrary.openApplication(driver);
        				logger.log(LogStatus.INFO,Description);
        			}
        			if(Object_Type.equalsIgnoreCase("clickAction"))
        			{
        				Functionlibrary.clickAction(driver, Locator_Type, Locator_Value);
        				logger.log(LogStatus.INFO,Description);
        			}
        			
        			if(Object_Type.equalsIgnoreCase("typeAction"))
        			{
        				Functionlibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
        				logger.log(LogStatus.INFO,Description);
        			}
        			
        			if(Object_Type.equalsIgnoreCase("closeBrowser"))
        			{
        				Functionlibrary.closeBrowser(driver);
        				logger.log(LogStatus.INFO,Description);
        			}
        			if(Object_Type.equalsIgnoreCase("waitForElement"))	
        			{
        				Functionlibrary.waitForElement(driver, Locator_Type, Locator_Value,Test_Data);
        				logger.log(LogStatus.INFO,Description);
        				
        			}
        			if(Object_Type.equalsIgnoreCase("titleValidation"))
        			{
        				Functionlibrary.titleValidation(driver, Test_Data);
        				logger.log(LogStatus.INFO,Description);
        			}
        			
        			
        			
        			
        			excel.setdata(TCModule, j, 5, "pass");
        			moduleSatus="true";
        			logger.log(LogStatus.PASS,Description);
        		}catch(Exception e)
        			{
        			excel.setdata(TCModule, j, 5, "Fail");
        			moduleSatus="False";
        			logger.log(LogStatus.INFO,Description);
        			File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        			FileUtils.copyFile(srcfile, new File("./Screenshots/"+Description+Functionlibrary.generatDate()+".png"));
        			break;
        			}
        			
        			 
        			}
        		if(moduleSatus.equalsIgnoreCase("true"))
        		{
        			excel.setdata("MasterTestCases", i, 3, "pass");
        			
        		}else
        			if(moduleSatus.equalsIgnoreCase("false"))
        			{
        				excel.setdata("MasterTestCases", i, 3, "Fail");
        			}
        		report.endTest(logger);
        		report.flush();
        	}
        	
        	else
        	{
        		excel.setdata("MasterTestCases", i, 3, "not executed");
        	}
         }
		
	
		
		
		
	}
}
