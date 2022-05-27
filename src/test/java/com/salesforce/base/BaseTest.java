package com.salesforce.base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.salesforce.utility.CommonUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	 protected static WebDriver driver;
	 protected static WebDriverWait wait;
	 public static ExtentHtmlReporter htmlReporter;
	 public static ExtentReports extent;
	 public static ExtentTest logger;
	 public static GenerateReports report;
	 
	 @BeforeTest
	 public static void initialTestSetup() {
		 System.out.println("before test started");
		report= GenerateReports.getInstance();
		report.startExtentReport();
	 }
	 
	 @BeforeMethod
	 public static void setUp(Method method) {
		 report.startSingleTestReport(method.getName());
		 System.out.println("before method started");
		 String url=CommonUtilities.getApplicationProperty("url");
		 getDriver();
		 gotoUrl(url);
		
	 }

	 public static void getDriver() 
	 {
		 WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
		 report.logTestInfo("driver instance created");
	 }
	 
	 public static void gotoUrl(String url) 
	 {
		 driver.get(url);
		 report.logTestInfo("url entered is "+url);
	 }

	 @AfterMethod
	 public static void tearDown() {
		 System.out.println("after method started");
		 closeAllDriver();
	 }
	 
	 @AfterTest
	 public static void finalTestTearDown() {
		 System.out.println("after test started");
		 report.endReport();
	 }

	 public static void closeDriver() 
	 {
		 driver.close();
	 }
		
	 public static void closeAllDriver() 
	 {
		 driver.quit();
	 }


}

