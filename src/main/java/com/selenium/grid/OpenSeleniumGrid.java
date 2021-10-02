package com.selenium.grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OpenSeleniumGrid {
	public RemoteWebDriver driver;
	public static String appURL="https://www.google.com/";
	@BeforeClass
	@Parameters({"browser"})
	public void setUp(String browser) throws MalformedURLException {
		DesiredCapabilities capabilities=null;
		//Decide which browser needs to invoke
		if(browser.equalsIgnoreCase("Chrome")) {
			capabilities=DesiredCapabilities.chrome();
		
		}else if(browser.equalsIgnoreCase("Firefox")){
			capabilities=DesiredCapabilities.firefox();
			
		}
		
		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		driver.manage().window().maximize();
		}
	@Test
	public void testGooglePageTitle() {
		driver.navigate().to(appURL);
		String pageTitle=driver.getTitle();
		Assert.assertTrue(pageTitle.equalsIgnoreCase("Google"),"Page title doesn't match");
	}
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
