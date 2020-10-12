package com.qa.linkedin.listeners;

import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager {

private static Logger log = Logger.getLogger(ExtentManager.class);
private static ExtentReports extent;
/**
 * This method is used to set the extent report information	
 * @return
 */
public static ExtentReports createInstance() {
	String fileName=getReportName();
	//String directory=System.getProperty("user.dir")+ "/target/extentreports/";
	log.debug("created extent report directory under target folder");
	String directory="target/extentreports/";
	new File(directory).mkdirs();
	String path=directory + fileName;
	log.debug("create object for ExtentHtmlReporter class ");
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
    htmlReporter.config().setTheme(Theme.STANDARD);
    htmlReporter.config().setDocumentTitle("Linkedin Automation Test Reports");
    htmlReporter.config().setEncoding("UTF-8");
    htmlReporter.config().setReportName("LinkedinAutomation Test Results");
    extent = new ExtentReports();
    extent.setSystemInfo("Automation Tester", "Ramesh");
    extent.setSystemInfo("Organization", "linkedin");
    extent.setSystemInfo("Build no", "QA-1234");
    extent.attachReporter(htmlReporter);
    
    return extent;
}

public static String getReportName() {
	log.debug("create Date class object inside the ExtentManager");
	Date d = new Date();
	log.debug("setting the extent report with current date and time with .html extension");
	String fileName = "ExtentReport"+"-"+ d.toString().replace(":", "_").replace(" ", "_") + ".html";
	return fileName;
}


}
