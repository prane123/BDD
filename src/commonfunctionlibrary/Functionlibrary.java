package commonfunctionlibrary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertiFileUtil;

public class Functionlibrary
{
	public static WebDriver startBrowser(WebDriver driver) throws Throwable
	{
		if(PropertiFileUtil.getValueForkey("Browser").equalsIgnoreCase("Firefox"))
{
	driver= new FirefoxDriver();
	
} else
	if(PropertiFileUtil.getValueForkey("Browser").equalsIgnoreCase("Chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "Commonjarfiles/chromedriver.exe");
		driver = new ChromeDriver();
		
	} else
		
		if(PropertiFileUtil.getValueForkey("Browser").equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "Commonjarfiles/IEDriverserver.exe");
			driver = new InternetExplorerDriver();	
			
		}
		return driver;
	}
	public static void  openApplication(WebDriver driver) throws Throwable, Throwable
	{
		driver.manage().window().maximize();
			driver.get(PropertiFileUtil.getValueForkey("URL"));
			
		}
	
	public static void  clickAction(WebDriver driver , String locatortype , String locatorvalue) throws Throwable, Throwable
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).click();
		} else
		
			if(locatortype.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorvalue)).click();
			}else
				if(locatortype.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorvalue)).click();
			
				}else
	            	
	            	if(locatortype.equalsIgnoreCase("linkText"))
	            	{
	            	
	            		driver.findElement(By.linkText(locatorvalue)).click();
	            	}
		}
			public static void typeAction(WebDriver driver , String locatorType, String Locatorvalue , String data)
			{
				if(locatorType.equalsIgnoreCase("id"))
				{
					driver.findElement(By.id(Locatorvalue)).clear();
					driver.findElement(By.id(Locatorvalue)).sendKeys(data);
	            } else
	            	if(locatorType.equalsIgnoreCase("name"))
	            {
	            		driver.findElement(By.name(Locatorvalue)).clear();
						driver.findElement(By.name(Locatorvalue)).sendKeys(data);
	            }else
	            	if(locatorType.equalsIgnoreCase("xpath"))
	            {
	            		driver.findElement(By.xpath(Locatorvalue)).clear();
						driver.findElement(By.xpath(Locatorvalue)).sendKeys(data);
	            }
	         }
			public static void closeBrowser(WebDriver driver)
			{
				driver.close();
			}
			public static void waitForElement(WebDriver driver,String locatorType, String Locatorvalue , String waittime)
			{
				WebDriverWait mywait = new WebDriverWait(driver , Integer.parseInt(waittime));
				if(locatorType.equalsIgnoreCase("id"))
				{
					mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locatorvalue)));
					} else
						if(locatorType.equalsIgnoreCase("name"))
						{
							mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locatorvalue)));
						}else
							if(locatorType.equalsIgnoreCase("xpath"))
							{
								mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locatorvalue)));
							}
				
			}
			
			public static void titleValidation(WebDriver driver , String Test_data)
			{
				String act_data=driver.getTitle();
				 
				if(Test_data.equalsIgnoreCase(act_data))
				{
					System.out.println("title matched");
				}else
				{
					System.out.println("title mismatched");
				}
			}
		
			public static String generatDate()
			{
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd_ss");
			return sdf.format(date);
			}
			
			
			
			
				
	}



