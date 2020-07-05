package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase() throws IOException
	{
		try {	
		prop = new Properties();
		
			FileInputStream fs = new FileInputStream("C:\\Users\\manoj\\workspace\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
 
	public static void Initialization()
	{
		String browsername = prop.getProperty("browser");
		
		//Doing browser selection on the basis of browser mentioned in the config file
			if(browsername.equals("chrome"))
				{				
					System.setProperty("webdriver.chrome.driver", "C:/Users/manoj/OneDrive/Desktop/chromedriver.exe");
					driver = new ChromeDriver();
				}
			else if (browsername.equals("IE"))
				{
					System.setProperty("webdriver.ie.driver", "C:/Users/manoj/OneDrive/Desktop/IEDriverServer.exe");
					driver = new InternetExplorerDriver();
				}
			else if (browsername.equals("Firefox"))
				{
					System.setProperty("webdriver.firefox.marionette", "C:/Users/manoj/OneDrive/Desktop/geckodriver.exe");
					DesiredCapabilities capabilities = DesiredCapabilities.firefox();  
			        capabilities.setCapability("marionette",true);  
			        driver= new FirefoxDriver(capabilities);  
					//driver = new FirefoxDriver();
				}
			else 
				System.out.println("No browser defined in Config file");
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.get(prop.getProperty("url")); //Launching application using url mentioned in the config file
		}
}
