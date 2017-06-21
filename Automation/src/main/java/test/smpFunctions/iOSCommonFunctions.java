package main.java.test.smpFunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.FileAlreadyExistsException;
import java.text.NumberFormat;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import main.java.test.smpPageObjects.iOSCommonObjects;

public class iOSCommonFunctions {

	WebDriverWait wait;
	public ExtentReports extent = null;
	public ExtentTest logger = null;
	File file;
	
	iOSCommonObjects ioscommobjects = new iOSCommonObjects();

//	public void tapbutton(WebElement element, String testname, IOSDriver<WebElement> idriver)
//			throws InterruptedException {
//
//		logger = extent.startTest(testname);
//		element.click();
//		Thread.sleep(1000);
//		logger.log(LogStatus.INFO, testname);
//		// logger.log(LogStatus.INFO, testname +
//		// logger.addScreenCapture(capture_ScreenShot(idriver, path,
//		// testname)));
//	}

	public boolean isElement_Present(AppiumDriver<WebElement> idriver,  String subtitletext) {

		logger = extent.startTest("Checking Element Present");
		
		try {
			idriver.findElementsByXPath(subtitletext);
			return true;
		} catch (NoSuchElementException e) {
			// e.printStackTrace();
			return false;
		}
		// return false;
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
	
	
	/*public void ResultFolder(String rName, String appFolder, String testFolder)
	{
		try
		{
			  String strDirectoy ="/../../rName";
			  String strManyDirectories="/appFolder/testFolder";
			  boolean success = (
					  new File(strDirectoy)).mkdir();
					  if (success) {
					  System.out.println("Directory: " 
					   + strDirectoy + " created");
					  }  
					  // Create multiple directories
					  success = (new File(strManyDirectories)).mkdirs();
					  if (success) {
					  System.out.println("Directories: " 
					   + strManyDirectories + " created");
					  }

					  }catch (Exception e){//Catch exception if any
					  System.err.println("Error: " + e.getMessage());
					  }
			}*/
	
	

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

	public void GenerateReport() {
		extent.endTest(logger);
		extent.flush();
	}

	public void seeking_Randomly(WebElement element, AppiumDriver<WebElement> idriver, int seek_position ,String path)
			throws Exception {
	//	int seekposition = (int) d;

		logger = extent.startTest("seekingRandomly",
				"Seeking randomly to check whether playback resumes from new point");

		int startX = element.getLocation().getX();
		// liverewind.live_rewind_progressbar.getLocation().getX();

		//seek_bar_swipe(idriver, element, startX, d);// 0.5);
		
		swipe_seekbar(element, idriver, "forward" ,seek_position);
		Thread.sleep(500);

		
		logger.log(LogStatus.INFO,
				"Seeking" + seek_position + "%" + logger.addScreenCapture(capture_ScreenShot(idriver, path,"Seeking Random")));

		// LiveText_Checking(idriver, path);

		Thread.sleep(500);

	}

	public void seek_bar_swipe(AppiumDriver<WebElement> idriver, WebElement seekbar)
	{
		int startX = seekbar.getLocation().getX();
		System.out.println("Progress bar Startx :" + startX);

		// Get end point of seekbar.
		int endX = seekbar.getSize().getWidth();
		System.out.println(" Progress bar Endx  :" + endX);

		// Get vertical location of seekbar.
		int yAxis = seekbar.getLocation().getY();
		System.out.println("Progress bar Yaxis  :" + yAxis);

		// Set sllebar move to position.
		// endX * 0.6 means at 60% of seek bar width.
	//	int moveToXDirectionAt = (int) (endX * d);
		//System.out.println("Moving seek bar at " + moveToXDirectionAt + " In X direction.");

		// Moving seekbar using TouchAction class.
		// TouchAction act=new TouchAction(driver);
		// act.press(startX,yAxis).moveTo(moveToXDirectionAt,yAxis).release().perform();
		// Thread.sleep(3000);
	//	idriver.swipe(startX, yAxis, moveToXDirectionAt, yAxis, 2000);
		idriver.swipe(startX, yAxis-50, startX,yAxis-50, 100);
	}
	
	
	public void swipe_seekbar(WebElement element, AppiumDriver<WebElement> driver, String seekdirection,int seekposition) throws Exception
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
		driver.swipe(startX, yAxis, endX-seekposition, yAxis, 50);
		//driver.swipe(0, 936, 300, 936, 300);
		}else if(seekdirection.equalsIgnoreCase("backward"))
		{
		driver.swipe(endX-seekposition, yAxis, startX+seekposition, yAxis, 50);
		}
	}
	
	
	
	
	public void seekbar_swipe(AppiumDriver<WebElement> idriver, WebElement seekbar, double d) {
		int startX = seekbar.getLocation().getX();
		System.out.println("Progress bar Startx :" + startX);

		// Get end point of seekbar.
		int endX = seekbar.getSize().getWidth();
		System.out.println(" Progress bar Endx  :" + endX);

		// Get vertical location of seekbar.
		int yAxis = seekbar.getLocation().getY();
		System.out.println("Progress bar Yaxis  :" + yAxis);

		// Set sllebar move to position.
		// endX * 0.6 means at 60% of seek bar width.
		int moveToXDirectionAt = (int) (endX * d);
		System.out.println("Moving seek bar at " + moveToXDirectionAt + " In X direction.");

		// Moving seekbar using TouchAction class.
		// TouchAction act=new TouchAction(driver);
		// act.press(startX,yAxis).moveTo(moveToXDirectionAt,yAxis).release().perform();
		// Thread.sleep(3000);
	//	idriver.swipe(startX, yAxis, moveToXDirectionAt, yAxis, 2000);
		idriver.swipe(startX-100, yAxis-5, startX-100,0, 100);
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
		Thread.sleep(500);
		

		logger.log(LogStatus.INFO, message + logger.addScreenCapture(capture_ScreenShot(driver, path, message)));
		
		NumberFormat numberformat = NumberFormat.getInstance();
		Double Device_OSversion = numberformat.parse(deviceOS).doubleValue();
		Double device_OS = 10.0;

		if(Device_OSversion < device_OS)
		{
		System.out.println("Device OS is :"+Device_OSversion);
		//ioscommobjects.iOS9_dismiss_wholewindow.click();
		driver.findElement(By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther")).click();
		Thread.sleep(500); 
		}if(Device_OSversion >= device_OS)
		{
		System.out.println("Device OS is :"+Device_OSversion);
		driver.findElement(By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow")).click();
		
		//ioscommobjects.iOS10_dismiss_wholewindow.click();
		Thread.sleep(500); 
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
	
	public void tapAccessabilityButton(String testname, AppiumDriver<WebElement> idriver,String accessabilityText ,String path)
			throws InterruptedException {
		
		logger = extent.startTest(testname);
		idriver.findElementByAccessibilityId(accessabilityText).click();
		Thread.sleep(1000);
		logger.log(LogStatus.INFO,
				testname + logger.addScreenCapture(capture_ScreenShot(idriver, path, testname)));
	}
	
	
	
	public void playback_orientation(String message, AppiumDriver<WebElement> driver , ScreenOrientation orientation,
			String path) throws Exception {
		logger = extent.startTest(message);
		driver.rotate(orientation);
		Thread.sleep(5000);
		logger.log(LogStatus.INFO,
				message + logger.addScreenCapture(capture_ScreenShot(driver, path, message)));
	}
	
	
	public void enterText(WebElement element, String testname, String text,IOSDriver<WebElement> idriver, String path)
			throws InterruptedException {
		
		logger = extent.startTest(testname);
		element.sendKeys(text);
		Thread.sleep(1000);
		logger.log(LogStatus.INFO,
				testname + logger.addScreenCapture(capture_ScreenShot(idriver, path, testname)));
	}
	
	
	public void seekingRandomly(WebElement element, AppiumDriver<WebElement> adriver, int seek_position,String path)
			throws Exception {
		//int seekposition= (int) d;

		logger = extent.startTest("seekingRandomly",
				"Seeking randomly to check whether playback resumes from new point");

		int startX = element.getLocation().getX();
				//liverewind.live_rewind_progressbar.getLocation().getX();

		//seek_bar_swipe(adriver, element, startX, d);// 0.5);
		swipe_seekbar(element, adriver,"backward" ,seek_position);
		Thread.sleep(3000);

		logger.log(LogStatus.INFO,
				"Seeking" + seek_position + "%"
						+ logger.addScreenCapture(capture_ScreenShot(adriver, path, "Multipel Seeking")));

		//LiveText_Checking(adriver, path);

		Thread.sleep(5000);

	}
	
	/*
	 * method to get the iOS device info like DeviceName, DeviceOS and Devices ID
	 * @param devicename 
	 * 
	 */
	
	 public String retrunDeviceInfo(String deviceName)
	    {
	    	String deviceName_ios=null;
	    	String match =deviceName;
	    
	    	 try {
	    		 String[] arguments = new String[] {"/usr/local/bin/ideviceinfo"};
	    	     Process p =Runtime.getRuntime().exec(arguments);
	    	     BufferedReader	br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	         	String line = "";
	         	while((line= br.readLine()) != null)
	         	{
	         	line = line.toLowerCase();
	 		    match = match.toLowerCase();
	         	if(line.indexOf(match) !=-1)
	     		{
	         		String OS[]=line.split(":");     		
	         		for(int i=1;i<OS.length;i++)
	         		{
	         			OS[i] = OS[i].replaceAll("\\s+", "");
	         			 //System.out.println("OS versionis:-"+OS[i]);
	         			deviceName_ios=OS[i];}   	
	     		     }
	         	}        	
	    	 }catch(Exception e)
	    	 {
	    		 e.printStackTrace();
	    	 }
	    	 return deviceName_ios;
	    	 }  
	
	
	 public boolean isElementPresent(WebElement elementName, AppiumDriver<WebElement> idriver,int timeout){
	        try{
	            WebDriverWait wait = new WebDriverWait(idriver, timeout);
	            wait.until(ExpectedConditions.visibilityOf(elementName));
//	            if(elementName.isDisplayed())
//	            {
//	            	elementName.click();
//	            }
	            return true;
	           
	        }catch(Exception e){
	            return false;
	        }
	    }
}
