package main.java.test.DRMCommonFunctions;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.text.NumberFormat;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class DRMCommomFunctions {
	
	public ExtentReports extent = null;
	public ExtentTest logger = null;
	File file;
	
	
	public void swipe(WebElement element, AppiumDriver<WebElement> driver) throws Exception
	{
	
	int startX = element.getLocation().getX();
	System.out.println("Startx :" + startX);

	// Get end point of seekbar.
	int endX = element.getSize().getWidth();
	System.out.println("Endx  :" + endX);

	// Get vertical location of seekbar.
	int yAxis = element.getLocation().getY();
	System.out.println("Yaxis  :" + yAxis);

	Thread.sleep(1000);
	//driver.swipe(endX, yAxis, startX, yAxis, 2000);
	driver.swipe(endX, yAxis, startX, yAxis-300, 200);
	
	}
	
	public void CreateReport(String absoluteFilePath, String deviceID, String deviceOS, String Port, String deviceName)
			throws Exception, FileAlreadyExistsException, InterruptedException {
		try {
			
			  

			extent = new ExtentReports(absoluteFilePath + "_" + deviceName + ".html", true, DisplayOrder.NEWEST_FIRST);

			// extent = new ExtentReports(absoluteFilePath, true,
			// DisplayOrder.NEWEST_FIRST);

			HashMap<String, String> sysInfo = new HashMap<String, String>();

			sysInfo.put("Device ID", deviceID);
			sysInfo.put("Firmware version", deviceOS);
			sysInfo.put("Appium Port", Port);
			sysInfo.put("Device Name ", deviceName);

			extent.addSystemInfo(sysInfo);

			// System.out.println("Final filepath : " +
			// absoluteFilePath+"_"+filename+".html" );
			System.out.println("Final filepath : " + absoluteFilePath + "_" + deviceName + ".html");
			file = new File(absoluteFilePath + "_" + deviceName + ".html");
			// file = new File(absoluteFilePath);

			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File is already existed!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void tapAccessabilityButton(String testname, AppiumDriver<WebElement> idriver,String accessabilityText ,String path)
			throws InterruptedException {
		
		logger = extent.startTest(testname);
		idriver.findElementByAccessibilityId(accessabilityText).click();
		Thread.sleep(1000);
		logger.log(LogStatus.INFO,
				testname + logger.addScreenCapture(capture_ScreenShot(idriver, path, testname)));
	}
	
	public String capture_ScreenShot(AppiumDriver<WebElement> driver, String ScreenshotPath, String Screenshotname) 
	{

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;;
//			if(platform == "iOS")
//			{
//				IOSDriver<WebElement> iosdriver = null;
//				ts = (TakesScreenshot) iosdriver;
//			}else
//			{
//				AndroidDriver<WebElement> androiddriver = null;
//				ts = (TakesScreenshot) androiddriver;
//			}
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest = ScreenshotPath + File.separator + Screenshotname + ".png";
			System.out.println("Screenshot path name:------" + dest);
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
			System.out.println("ScreenShot Taken");
			return dest;
		} catch (Exception e) {
			System.out.println("Exception While Taking screenshot" + e.getMessage());
			return e.getMessage();
		}

	}
	
	public void swipe_seekbar1(WebElement element, AppiumDriver<WebElement> driver, int seekposition) throws Exception
	{
				
		int startX = element.getLocation().getX();
		//System.out.println("Startx :" + startX);

		// Get end point of seekbar.
		int endX = element.getSize().getWidth();
		//System.out.println("Endx  :" + endX);

		// Get vertical location of seekbar.
		int yAxis = element.getLocation().getY();
		//System.out.println("Yaxis  :" + yAxis);

		//Thread.sleep(500);
		//driver.swipe(endX, yAxis, startX, yAxis, 2000);
		driver.swipe(startX, yAxis, endX-seekposition, yAxis, 100);
		//driver.swipe(0, 936, 300, 936, 300);
		
		
		}
	
	public String ResultFolder(String path)
	{
		String strManyDirectories=null;
		 try{
			//  String strDirectoy = path;
			  strManyDirectories= path;

			  // Create one directory
			  boolean  
			  // Create multiple directories
			  success = (new File(strManyDirectories)).mkdirs();
			  if (success) {
			  System.out.println("Directories: " 
			   + strManyDirectories + " created");
			  }

			  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		 return strManyDirectories;
			  }
	
	public void GenerateReport() {
		extent.endTest(logger);
		extent.flush();
	}
	
	public void enterText(WebElement element, String testname, String text,IOSDriver<WebElement> idriver, String path)
			throws InterruptedException {
		
		logger = extent.startTest(testname);
		element.sendKeys(text);
		Thread.sleep(1000);
		logger.log(LogStatus.INFO,
				testname + logger.addScreenCapture(capture_ScreenShot(idriver, path, testname)));
	}
	
	public boolean isAccessabilityElementPresent(AppiumDriver<WebElement> iosdriver, String Name) 
	{

		logger = extent.startTest("Checking Element Present");
		try {
			iosdriver.findElementByAccessibilityId(Name);
			return true;
		} catch (NoSuchElementException e) {
			// e.printStackTrace();
			return false;
		}
	}
	
	
	public void turnWifiON(String TestName, AppiumDriver<WebElement> driver, String networkConnection,
			String path, String message, String deviceOS)
			throws Exception {

		logger = extent.startTest(TestName);

		final int height = driver.findElementByClassName("UIAWindow").getSize().getHeight();
		final int width = driver.findElementByClassName("UIAWindow").getSize().getWidth();
		System.out.println("height"+height);
		System.out.println("width"+width);
		driver.swipe(width-100, height-5, width-100,0, 50);
		
		driver.findElementByAccessibilityId(networkConnection).click();
		Thread.sleep(800);
		

		logger.log(LogStatus.INFO, message + logger.addScreenCapture(capture_ScreenShot(driver, path, message)));
		
		NumberFormat numberformat = NumberFormat.getInstance();
		Double Device_OSversion = numberformat.parse(deviceOS).doubleValue();
		Double device_OS = 10.0;

		if(Device_OSversion < device_OS)
		{
		System.out.println("Device OS is :"+Device_OSversion);
		//ioscommobjects.iOS9_dismiss_wholewindow.click();
		//driver.findElement(By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther")).click();
		//driver.findElement(By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther")).click();
		driver.findElement(By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther")).click();
		driver.findElementByAccessibilityId("Dismiss side app");
		Thread.sleep(500); 
		}if(Device_OSversion >= device_OS)
		{
		System.out.println("Device OS is :"+Device_OSversion);
		driver.findElement(By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow")).click();
		
		//ioscommobjects.iOS10_dismiss_wholewindow.click();
		Thread.sleep(500); 
		}
		
	}
	
	
	public void swipe_seekbar(WebElement element, AppiumDriver<WebElement> idriver, String seekdirection,int seekposition) throws Exception
		{
					
			int startX = element.getLocation().getX();
			//System.out.println("Startx :" + startX);

			// Get end point of seekbar.
			int endX = element.getSize().getWidth();
			//System.out.println("Endx  :" + endX);

			// Get vertical location of seekbar.
			int yAxis = element.getLocation().getY();
			//System.out.println("Yaxis  :" + yAxis);

			if(seekdirection.equalsIgnoreCase("forward" ))
			{
			//Thread.sleep(500);
			//driver.swipe(endX, yAxis, startX, yAxis, 2000);
				idriver.swipe(startX, yAxis, endX-seekposition, yAxis, 50);
			//driver.swipe(0, 936, 300, 936, 300);
			}else if(seekdirection.equalsIgnoreCase("backward"))
			{
				idriver.swipe(endX-seekposition, yAxis, startX+seekposition, yAxis, 50);
			}
		}

	
	
	
	public void tapbutton(WebElement element, String testname, AppiumDriver<WebElement> idriver, String path)
			throws InterruptedException {
		
		logger = extent.startTest(testname);
		element.click();
		Thread.sleep(2000);
		logger.log(LogStatus.INFO,
				testname + logger.addScreenCapture(capture_ScreenShot(idriver, path, testname)));
	}
	
}
