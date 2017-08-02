package main.java.test.smpMainTest;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.test.smpFunctions.CommonFunction;
import main.java.test.smpFunctions.iOSCommonFunctions;
import main.java.test.smpPageObjects.CommonObjects;
import main.java.test.smpPageObjects.iOSCommonObjects;
import main.java.test.smpUtilityFunctions.AppiumManager;

public class SMPiOSLiveSimulcastPlayback {

	IOSDriver<WebElement> iosdriver;
	DesiredCapabilities capabilities;
	CommonFunction commonfunction = new CommonFunction();
	AppiumManager appiummanager = new AppiumManager();

	iOSCommonObjects iospageobjects;
	CommonObjects commonobjects;

	iOSCommonFunctions ioscommonfunction = new iOSCommonFunctions();

	public String filename;
	public String workingDirectory;  
	public String absoluteFilePath;
	public String ScreenshotPath;    //"/../Automation/Results/iOSDRM";
	File screenhotfiles;
	
	
	public String deviceName=null;
	public String deviceOS=null;
	public String deviceUDID=null;

	@BeforeClass
	public void getDeviceDetails() throws Exception
	{
		deviceName = ioscommonfunction.retrunDeviceInfo("DeviceName");
		deviceOS = ioscommonfunction.retrunDeviceInfo("ProductVersion");
		deviceUDID = ioscommonfunction.retrunDeviceInfo("UniqueDeviceID");
	    System.out.println("The Device Name is :-"+  deviceName);
	    System.out.println("The Device OS version is :-"+  deviceOS);
	    System.out.println("The Device UDID is :-"+  deviceUDID);
	    
	    Setup(deviceName,deviceUDID,deviceOS);
	}
	
	
	public void Setup(String dName,String dUDID, String dOS) throws Exception
		{
		appiummanager.startAppium(4723);
		appiummanager.AppiumURL();
		String appiul_url = appiummanager.AppiumURL();
		System.out.println("Appium Service Address : - " + appiul_url);		
		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.6.4");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, dName);
		capabilities.setCapability(MobileCapabilityType.UDID, dUDID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, dOS);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");//"XCUITest");
		capabilities.setCapability(MobileCapabilityType.APP,
				"/Users/ramakh01/Desktop/AvTestHarness/iOSApp/AVTestHarness.ipa");
		capabilities.setCapability("useNewWDA", true);
		capabilities.setCapability("wdaLaunchTimeout", 3000);
		capabilities.setCapability("wdaLocalPort", 8100);
	
		//capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "uk.co.mediaat.mediaplayer-testharness-ios");
		// capabilities.setCapability("bundleId",
		// "uk.co.mediaat.mediaplayer-testharness-ios");
		// capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS,
		// true);
	//	capabilities.setCapability("xcodeConfigFile", "/usr/local/lib/node_modules/appium/"
		// +
		// "node_modules/appium-xcuitest-driver/WebDriverAgent/Config.xcconfig");
		// capabilities.setCapability("realDeviceLogger",
		// "/usr/local/lib/node_modules/deviceconsole/deviceconsole");
		// capabilities.setCapability("realDeviceLogger", "idevicesyslog");

		// caps.setCapability("realDeviceLogger",
		// "/usr/local/lib/node_modules/deviceconsole/deviceconsole");
		// capabilities.setCapability(MobileCapabilityType.w, value);

		// "dfe87d2ae7f9224c360572bafd81eb37498ac128");//
																						// "5c9fd7a971905728abccb7290122bef959a4b3b5");

		try {

			iosdriver = new IOSDriver<>(new URL(appiul_url), capabilities);
			iosdriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void OpenAvtest() throws Exception {
		try {

			commonobjects = new CommonObjects();
			PageFactory.initElements(new AppiumFieldDecorator(iosdriver), commonobjects);

			iospageobjects = new iOSCommonObjects();
			PageFactory.initElements(new AppiumFieldDecorator(iosdriver), iospageobjects);

			 try
				{
				filename = "SMP_iOS_LivePlayback";
				workingDirectory =  commonfunction.ResultFolder(commonobjects.ParentDirectoy);  
				absoluteFilePath = workingDirectory + File.separator + filename;
				ScreenshotPath =  workingDirectory+ File.separator+commonfunction.ResultFolder(commonobjects.SubDirectory);    //"/../Automation/Results/iOSDRM";
				screenhotfiles = new File(ScreenshotPath);
				
				ioscommonfunction.CreateReport(absoluteFilePath, deviceUDID, "4723",
						deviceOS,
						deviceName);
				}catch(NullPointerException e)
				{
					e.printStackTrace();
				}
			
			//update ignore
			//iosdriver.findElementByAccessibilityId("Ignore").click();
			
			ioscommonfunction.tapAccessabilityButton("Dismiss APP Update", iosdriver,"Ignore" ,ScreenshotPath);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "OpenAvtest" })
	public void PlaybackStart() throws Exception {

		iosdriver.findElementByAccessibilityId(iospageobjects.Live_Channel).click();
		Thread.sleep(2000);

	}
	
	
	

	@Test(dependsOnMethods = { "PlaybackStart" })
	public void turnWifiOff() throws Exception {
		

//		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, "Wi-Fi",
//				iospageobjects.dismiss_wholewindow,ScreenshotPath, "Wifi Off");
		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, "Wi-Fi",
				ScreenshotPath, "Wifi Off",deviceOS);

	}

	@Test(dependsOnMethods = { "turnWifiOff" })
	public void playback_ErrorMessage() throws Exception {

		//commonfunction.waitForScreenToLoad(iosdriver,iosdriver.findElementByAccessibilityId("An unknown error occurred"), 2600);
		
		ioscommonfunction.isElementPresent(iospageobjects.errormessage,iosdriver,80);
		
		assertTrue(ioscommonfunction.isAccessabilityElementPresent(iosdriver, "Dismiss"));
		
		assertTrue(ioscommonfunction.isAccessabilityElementPresent(iosdriver, "Try again"));

		assertTrue("The Text Matched",
				iospageobjects.error_message.equalsIgnoreCase(iosdriver.findElementByAccessibilityId("An unknown error occurred").getText()));
		
		//iosdriver.rotate(ScreenOrientation.LANDSCAPE);
	
		Thread.sleep(3000);

	}


	@Test(dependsOnMethods = { "playback_ErrorMessage" })
	public void TurnWiFi_ON() throws Exception {

		
		ioscommonfunction.tapAccessabilityButton("Dismiss Error Message", iosdriver,"Dismiss" ,ScreenshotPath);
		Thread.sleep(2000);

//		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, "Wi-Fi",
//				iospageobjects.dismiss_wholewindow,ScreenshotPath, "Wifi ON");

		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, "Wi-Fi",
				ScreenshotPath, "Wifi Off",deviceOS);
		
		assertTrue(ioscommonfunction.isAccessabilityElementPresent(iosdriver, "play_pause_button"));
	
//		assertTrue(ioscommonfunction.isElementPresent(iosdriver,
//				By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[6]")));
		
		ioscommonfunction.tapAccessabilityButton("Starting the Playback", iosdriver,"play_pause_button" ,ScreenshotPath);
		Thread.sleep(2000);
		
	//	ioscommonfunction.tapbutton(iospageobjects.playback_start_button, "Starting the Playback", iosdriver, ScreenshotPath);


	}
	
	
	@Test(dependsOnMethods = { "TurnWiFi_ON" })
	public void PlaybackRotation() throws Exception {

		try {

			ioscommonfunction.playback_orientation("Playing in LANDSCAPE",iosdriver, ScreenOrientation.LANDSCAPE, 
					ScreenshotPath);
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {

			ioscommonfunction.playback_orientation("Playing in PORTRAIT",iosdriver, ScreenOrientation.PORTRAIT, 
					ScreenshotPath);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}



	@Test(dependsOnMethods = { "PlaybackRotation" })
	public void StopPlayback() throws Exception {


		iospageobjects.playback_transportcontrol.click();
		Thread.sleep(100);
//		assertTrue(ioscommonfunction.isElementPresent(iosdriver,
//				By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[6]")));
		
		ioscommonfunction.tapAccessabilityButton("Stopping the Playback", iosdriver,"play_pause_button" ,ScreenshotPath);
		Thread.sleep(2000);
		
		//ioscommonfunction.tapbutton(iospageobjects.playback_stop, "Stopping the Playback", iosdriver,ScreenshotPath);


		// iospageobjects.playback_stop.click();
		// Thread.sleep(3000);
	}

	@Test(dependsOnMethods = { "StopPlayback" })
	public void ClosePlayback() throws Exception {


		//iospageobjects.playback_close.click();
		ioscommonfunction.tapAccessabilityButton("Stopping the Playback", iosdriver,"smp_close_button" ,ScreenshotPath);
		Thread.sleep(300);

	}

	@AfterClass
	public void tearDown() {
		ioscommonfunction.GenerateReport();
		iosdriver.quit();
		//iosdriver.resetApp();
		appiummanager.stopappium();
	}

	
	
	public int getX(int x)
	{
		 Dimension winSize = null;
		 return (int) ((winSize.width * x) / 100);
	}
	
	public int getY(int y)
	{
		Dimension winSize = null;
		return (int) ((winSize.height * y) / 100);
	}

	
	public void scrolling()
	{
		// Declare variable that contains the dimensions of the device screen
		Dimension winSize;
		// Retrieve the actual device dimensions
		winSize = iosdriver.manage().window().getSize();
		// define two methods that compute the actual coordinates given the percentage
		// use the methods to perform the swipe from (20%,62%) to (22%, 35%) and scroll up
		int startX = getX(62); 
		int endX = getX(22);
		int startY = getY(20); 
		int endY = getY(35);
		TouchAction touchAction4 = new TouchAction(iosdriver);
		touchAction4.press(startX, startY).waitAction(3000).moveTo(endX, endY).release();
		iosdriver.performTouchAction(touchAction4);
	}




}
