package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class PropertiFileUtil {
	public static String getValueForkey(String key) throws Throwable, Exception
	{
		Properties configProperties = new Properties();
		configProperties.load(new FileInputStream(new File("C:\\Users\\praneeth.k\\StockAccounting\\PropertiesFile\\Environment.properties")));
		return configProperties.getProperty(key);
	}
}

	