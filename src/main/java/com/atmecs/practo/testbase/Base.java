package com.atmecs.practo.testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.atmecs.practo.constant.Constant;

/**
 * Different browser setup to run the script in different different browser.
 * 
 * @author ranjitha.selvam
 *
 */
public class Base {
	
	static Properties property = new Properties();
	static FileInputStream stream;
	public static WebDriver driver;

	@BeforeTest
	public static void setup() throws IOException {

		
		stream = new FileInputStream(Constant.config_file_path);
		property.load(stream);
		String url = property.getProperty("url");
		String browserName = property.getProperty("browsername");
		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", Constant.chrome_path);
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", Constant.fireFox_path);
			driver = new FirefoxDriver();
			break;
		case "internetexplorer":
			System.setProperty("webdriver.ie.river", Constant.ie_path);
			driver = new InternetExplorerDriver();
			break;

		}
		driver.get(url);
		driver.manage().window().maximize();

	}

	
	  @AfterTest 
	  public void close() { 
      driver.close(); 
      }
	 
}
