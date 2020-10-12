package com.qa.linkedin.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.log4testng.Logger;
import com.qa.linkedin.base.TestBase;

public class TestUtil extends TestBase{
	
private static Logger log = Logger.getLogger(TestUtil.class);

/**
 * this method captures the screenshot
 * @param methodName
 * @return
 * @throws IOException
 */
public static String captureScreenshot(String methodName) throws IOException {
		log.debug("get the screenshotname");
		String fileName=getScreenshotName(methodName);
		log.debug("set the screenshot file location");
	    String directory="target/surefire-reports/failedTestScreenshots/";
		//String directory=System.getProperty("user.dir")+"/target/surefire-reports/failedTestScreenshots/";
		log.debug("create the directories under target folder");
	    new File(directory).mkdirs();
		String path=directory + fileName;
		try {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,	new File(path));
		log.debug("********************************************************************************");
		log.debug("Screenshot stored at path: "+path);
		log.debug("********************************************************************************");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	private static String getScreenshotName(String methodName) {
		log.debug("create Date class Object");
		Date d = new Date();
		log.debug("convert the file into current date and time format with png extension");
		String fileName = methodName+"-"+ d.toString().replace(":", "_").replace(" ", "_") + ".png";
		return fileName;
	}

}
