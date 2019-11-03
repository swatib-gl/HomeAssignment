package com.discovery.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseDriver {
	
	public WebDriver driver;
	Properties prop=new Properties();
	public static ExtentReports extentreport;
	public static ExtentTest extenttest;
	public static final Logger log=Logger.getLogger(BaseDriver.class.getName());
	
	static {
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat simpledateformat=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extentreport = new ExtentReports(System.getProperty("user.dir")+"/src/main/java/com/discovery/extentReports/report"+simpledateformat.format(calendar.getTime())+".html",false);
	}
		
	public void init() throws IOException {
		loadconfigprop();
		String logpath=System.getProperty("user.dir")+"/src/main/java/com/discovery/utils/log4j.properties";
		PropertyConfigurator.configure(logpath);
		selectBrowser(prop.getProperty("browser"));
		getURL(prop.getProperty("Url"));
		
	}

public void getURL(String Url) {
	log.info("fetched url");
	driver.get(Url);
	log.info("window maximized");
	driver.manage().window().maximize();
	
}
	public void loadconfigprop() throws IOException {
		File file=new File(System.getProperty("user.dir")+"/src/main/java/com/discovery/utils/config.properties");
		FileInputStream fis=new FileInputStream(file);
		prop.load(fis);
		log.info("fetched log properties file");
	}
	
	public void selectBrowser(String browser) throws IOException { 
//	String osname=System.getProperty("os.name");
//	if(System.getProperty("os.name").contains("window")) {
			
               if (browser.equalsIgnoreCase("chrome")) {
                     System.setProperty("webdriver.chrome.driver",
                    		 "D://selenium workspace//workspace//Home.Assignment//src//main//java//drivers//chromedriver.exe");
                     driver = new ChromeDriver();
                     log.info("selected chrome browser");
                     }
               else {
            	   log.info("no browser selected");
               }
	
	}
	
	public void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 80);
        wait.until(pageLoadCondition);
        log.info("waited till page loaded");
    }
	
public void closeBrowser() {
	driver.quit();
	log.info("closing browser");
	extentreport.endTest(extenttest);
	extentreport.flush();
}

@BeforeMethod()
 public void teststart(Method result) {
	 extenttest = extentreport.startTest(result.getName());
	 extenttest.log(LogStatus.INFO, result.getName()+ "test started");

 }
@AfterMethod()
public void afterTest(ITestResult result) {
	getResult(result);
}


public void getResult(ITestResult result) {
	if(result.getStatus()==ITestResult.SUCCESS) {
		extenttest.log(LogStatus.PASS, result.getName()+"test is passed");
	}else if(result.getStatus()==ITestResult.FAILURE) {
		extenttest.log(LogStatus.FAIL, result.getName()+"test is failed");		
	}
	else
		extenttest.log(LogStatus.SKIP, result.getName()+"test is skipped");
}

}
