package main.java.test.smpMainTest;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.test.smpFunctions.CommonFunction;
import main.java.test.smpFunctions.LiveRewindFunctions;
import main.java.test.smpFunctions.iOSCommonFunctions;
import main.java.test.smpPageObjects.CommonObjects;
import main.java.test.smpPageObjects.LiveRewindPageObjects;
import main.java.test.smpPageObjects.iOSCommonObjects;
import main.java.test.smpUtilityFunctions.AppiumManager;
import main.java.test.smpUtilityFunctions.PortFactory;

public class SMPiOSOnDemand_VideoPlayback {

	iOSCommonObjects iospageobjects;
	CommonObjects commonobjects;
	public AndroidDriver<WebElement> driver = null;
	public DesiredCapabilities capa;
	String message;
	public WebDriverWait wait;

	String filename = "iOS_OnDemandVideoPlayback";
	String workingDirectory = "/Users/ramakh01/Desktop/AvTestHarness/AvTestHarness/Results"; /// System.getProperty("user.dir");
	String absoluteFilePath = workingDirectory + File.separator + filename;
	public String ScreenshotPath = "/Users/ramakh01/Desktop/AvTestHarness/AvTestHarness/Results/iOS/";
	File screenhotfiles = new File(ScreenshotPath);
	File file;// = new File(absoluteFilePath);
	AppiumManager appiummanager = new AppiumManager();

	iOSCommonFunctions ioscommonfunction = new iOSCommonFunctions();
	LiveRewindPageObjects liverewindobject = new LiveRewindPageObjects();
	LiveRewindFunctions liverewindFunctions = new LiveRewindFunctions();

	CommonFunction commonfunction = new CommonFunction();

	PortFactory portFactory = new PortFactory();

	DesiredCapabilities capabilities;

	IOSDriver<WebElement> iosdriver;
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
		capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
		capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, false);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");//"XCUITest");
		capabilities.setCapability(MobileCapabilityType.APP,
				"/Users/ramakh01/Desktop/AvTestHarness/iOSApp/AVTestHarness.ipa");
		capabilities.setCapability("useNewWDA", true);
		capabilities.setCapability("wdaLaunchTimeout", 3000);
		capabilities.setCapability("wdaLocalPort", 8100);


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

			ioscommonfunction.CreateReport(absoluteFilePath, deviceUDID, "4723",
					deviceOS, deviceName);
			
			Thread.sleep(3000);
			
			ioscommonfunction.tapAccessabilityButton("Dismiss APP Update", iosdriver,"Ignore" ,ScreenshotPath);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "OpenAvtest" })
	public void OnDemand_PlaybackStart() throws Exception
	{
		try
		{
			ioscommonfunction.tapAccessabilityButton("Playing Video", iosdriver,"MF - VOD - Production (Combined profiles, HD)" ,ScreenshotPath);
			
			//assertTrue(ioscommonfunction.isAccessabilityElementPresent(iosdriver, "In progress"));
			assertTrue(ioscommonfunction.isAccessabilityElementPresent(iosdriver, "play_pause_button"));
			assertTrue(ioscommonfunction.isAccessabilityElementPresent(iosdriver, "smp_subtitles_button"));
			assertTrue(ioscommonfunction.isAccessabilityElementPresent(iosdriver, "Playback position"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test(dependsOnMethods = { "OnDemand_PlaybackStart" })
	public void Turn_WiFiOff() throws Exception {
	    
//		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, "Wi-Fi",
//				iospageobjects.dismiss_wholewindow,ScreenshotPath, "Wifi Off");
		
		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, "Wi-Fi",
				ScreenshotPath, "Wifi Off",deviceOS);
	}

	@Test(dependsOnMethods = { "Turn_WiFiOff" })
	public void playback_ErrorMessage() throws Exception {

		commonfunction.waitForScreen_ToLoad(iosdriver,
				iosdriver.findElementByAccessibilityId("Dismiss"), 2600);
		//assertTrue(ioscommonfunction.isAccessabilityElementPresent(iosdriver, "In progress"));
		
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
		Thread.sleep(500);
		
//		ioscommonfunction.turnWifiON("Turning ON WiFi Connection", iosdriver, "Wi-Fi",
//				iospageobjects.dismiss_wholewindow,ScreenshotPath, "Wifi ON");
		
		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, "Wi-Fi",
				ScreenshotPath, "Wifi Off",deviceOS);
		
		ioscommonfunction.tapAccessabilityButton("Hitting the Play again", iosdriver,"play_pause_button" ,ScreenshotPath);
		Thread.sleep(200);
		


	}

	@Test(dependsOnMethods = { "TurnWiFi_ON" })
	public void OnDemand_PlaybackStop() throws Exception {
		try {

			ioscommonfunction.tapAccessabilityButton("Stop Video playback", iosdriver,"play_pause_button" ,ScreenshotPath);
//			iospageobjects.playback_stop.click();
			Thread.sleep(100);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "OnDemand_PlaybackStop" })
	public void ClosePlayback() throws Exception {


		//iospageobjects.playback_close.click();
		ioscommonfunction.tapAccessabilityButton("Close the Playback", iosdriver,"smp_close_button" ,ScreenshotPath);
		Thread.sleep(300);

	}
	
	/*

/*@Test(dependsOnMethods = { "OnDemand_PlaybackStart" })
	public void playback_seeking() {
		try {

		    iospageobjects.transport_controls.click();
		    Thread.sleep(50);
		    
		    WebElement progressbar = iosdriver.findElement(By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[7]/XCUIElementTypeOther"));
		    		//iosdriver.findElementByAccessibilityId("Playback position");
			int startX = progressbar.getLocation().getX();
			
			iospageobjects.transport_controls.click();
			Thread.sleep(50);
			
			ioscommonfunction.seekbar_swipe(iosdriver, progressbar, 0.5);// 0.5);
			screenhotfiles = ((TakesScreenshot) iosdriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenhotfiles, new File("Seeking 50%%"));

		}catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	public void playbackseeking() {
		try {

			iospageobjects.transport_controls.click();
		    Thread.sleep(50);
		    
//			iospageobjects.playback_transportcontrol.click();
//			Thread.sleep(100);

			int startX = iospageobjects.playback_progressbar.getLocation().getX();
			
			iospageobjects.playback_transportcontrol.click();
			Thread.sleep(100);
			ioscommonfunction.seek_bar_swipe(iosdriver, iospageobjects.playback_progressbar, startX, 0.5);// 0.5);
			screenhotfiles = ((TakesScreenshot) iosdriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenhotfiles, new File("Seeking 50%%"));


			iospageobjects.playback_transportcontrol.click();
			Thread.sleep(100);
			int startX1 = iospageobjects.playback_progressbar.getLocation().getX();
			//ioscommonfunction.seekingRandomly(iospageobjects.playback_progressbar, iosdriver, ScreenshotPath, 0.60);
			ioscommonfunction.seek_bar_swipe(iosdriver, iospageobjects.playback_progressbar, startX1, 0.95);
			screenhotfiles = ((TakesScreenshot) iosdriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenhotfiles, new File("Seeking 95%"));

			iospageobjects.playback_transportcontrol.click();
			Thread.sleep(100);
			int startX2 = iospageobjects.playback_progressbar.getLocation().getX();

			//ioscommonfunction.seekingRandomly(iospageobjects.playback_progressbar, iosdriver, ScreenshotPath, 0.90);
			ioscommonfunction.seek_bar_swipe(iosdriver, iospageobjects.playback_progressbar, startX2, 0.3);
			screenhotfiles = ((TakesScreenshot) iosdriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenhotfiles, new File("Seeking 30%"));

			iospageobjects.playback_transportcontrol.click();



		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	

	@AfterClass
	public void tearDown() {

		ioscommonfunction.GenerateReport();
		iosdriver.closeApp();
		iosdriver.quit();
		appiummanager.stopappium();
		
	}


}
