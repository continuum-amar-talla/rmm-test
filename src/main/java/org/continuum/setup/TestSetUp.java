package org.continuum.setup;

import org.aeonbits.owner.ConfigFactory;
import org.continuum.utils.ConfigProperties;
import org.continuum.utils.ExcelReader;
import org.continuum.utils.ExtentManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;

public class TestSetUp
{
	public static ConfigProperties configProperties;
	public static ExtentReports extentReports;
	public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelLog = new ThreadLocal<ExtentTest>();
	public static ExcelReader excelReader = new ExcelReader(System.getProperty("user.dir") + "\\src"
			+ "\\test\\resources\\testData\\testData.xlsx");
	
	@BeforeSuite
	public void setUpFramework()
	{
		configProperties = ConfigFactory.create(ConfigProperties.class);
		RestAssured.baseURI = configProperties.getBrightGaugeBaseURI();
		RestAssured.basePath = configProperties.getBrightGaugeBasePath();
		extentReports = ExtentManager.getInstance(System.getProperty("user.dir") + "\\src\\main" +
				"\\resources\\ExtentReports\\ExtentReport.html");
	}
	
	@BeforeClass
	public synchronized void beforeClass()
	{
		ExtentTest extentTest = extentReports.createTest(getClass().getSimpleName());
		classLevelLog.set(extentTest);
	}
	
	@AfterSuite
	public void afterSuite()
	{
		extentReports.flush();
	}
}