package org.continuum.utils;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager
{
	public static ExtentReports extentReports;
	public static ExtentHtmlReporter extentHTMLReporter;
	
	public static ExtentReports getInstance(String filePath)
	{
		if(extentReports != null)
		{
			return extentReports;
		}
		else
		{
			extentReports = new ExtentReports();
			extentReports.attachReporter(ExtentManager.getHTMLReporter(filePath));
			extentReports.setAnalysisStrategy(AnalysisStrategy.CLASS);
			return extentReports;
		}
	}
	
	public static ExtentHtmlReporter getHTMLReporter(String filePath)
	{
		extentHTMLReporter = new ExtentHtmlReporter(filePath);
		extentHTMLReporter.loadXMLConfig(System.getProperty("user.dir") + "\\src\\test\\resources" +
				"\\reportConfigs\\ReportConfig.xml");
		return extentHTMLReporter;
	}
}