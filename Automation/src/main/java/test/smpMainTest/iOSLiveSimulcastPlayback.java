package main.java.test.smpMainTest;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.test.smpFunctions.CommonFunction;
import main.java.test.smpFunctions.iOSCommonFunctions;
import main.java.test.smpPageObjects.CommonObjects;
import main.java.test.smpPageObjects.iOSCommonObjects;
import main.java.test.smpUtilityFunctions.AppiumManager;

public class iOSLiveSimulcastPlayback {

	IOSDriver<WebElement> iosdriver;
	DesiredCapabilities capabilities;
	CommonFunction commonfunction = new CommonFunction();
	AppiumManager appiummanager = new AppiumManager();

	iOSCommonObjects iospageobjects;
	CommonObjects commonobjects;

	iOSCommonFunctions ioscommonfunction = new iOSCommonFunctions();

	String filename = "iOSLivePlaybck";
	String workingDirectory = "/Users/ramakh01/Desktop/Automation/Automation/Results"; /// System.getProperty("user.dir");
	String absoluteFilePath = workingDirectory + File.separator + filename;
	public String ScreenshotPath = "/Users/ramakh01/Desktop/Automation/Automation/Results/iOS/";
	File screenhotfiles = new File(ScreenshotPath);

	@BeforeClass
	public void Setup() throws Exception
	

		{
		appiummanager.startAppium(4723);
		appiummanager.AppiumURL();
		String appiul_url = appiummanager.AppiumURL();
		System.out.println("Appium Service Address : - " + appiul_url);
			
			
		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.6.4");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPadAir2");
		capabilities.setCapability(MobileCapabilityType.UDID, "df43e12f4ba40c8763eb37dc17195717e094ee96");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3.5");
	//	capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		capabilities.setCapability(MobileCapabilityType.APP,
				"/Users/ramakh01/Desktop/AvTestHarness/iOSApp/AVTestHarness.ipa");

		capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "uk.co.mediaat.mediaplayer-testharness-ios");
		// capabilities.setCapability("bundleId",
		// "uk.co.mediaat.mediaplayer-testharness-ios");
		capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
		capabilities.setCapability(IOSMobileCapabilityType.AUTO_DISMISS_ALERTS, true);
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

			ioscommonfunction.CreateReport(absoluteFilePath, "df43e12f4ba40c8763eb37dc17195717e094ee96", "4723",
					"9.3.5", "iPad-Air2");

			ioscommonfunction.tapbutton(iospageobjects.update_ignoreButton, "Dismissing the Update Alert", iosdriver,ScreenshotPath);



		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "OpenAvtest" })
	public void PlaybackStart() throws Exception {

		// iospageobjects.BBC_two_Live.click();
		iosdriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]")).click();
		Thread.sleep(5000);

	}
	
	
	

	@Test(dependsOnMethods = { "PlaybackStart" })
	public void turnWifiOff() throws Exception {


//		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, iospageobjects.wifi_mode,
//				iospageobjects.dismiss_wholewindow,ScreenshotPath, "Wifi Off");

	}

	@Test(dependsOnMethods = { "turnWifiOff" })
	public void playback_ErrorMessage() throws Exception {

		commonfunction.waitForScreenToLoad(iosdriver,
				iosdriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[3]")), 300);

//		assertTrue(ioscommonfunction.isElementPresent(iosdriver,
//				By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[3]")));
//
//		assertTrue(ioscommonfunction.isElementPresent(iosdriver,
//				By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[4]")));


		assertTrue("The Text Matched",
				iospageobjects.error_message.equalsIgnoreCase(iospageobjects.playback_errorMessage.getText()));
		
		iosdriver.rotate(ScreenOrientation.LANDSCAPE);
		// WebElement error_message =
		// iosdriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAStaticText[2]"));
		// String errormessage = error_message.getText();
		// System.out.println("The Error message is" + errormessage);

		Thread.sleep(3000);

	}


	@Test(dependsOnMethods = { "playback_ErrorMessage" })
	public void TurnWiFi_ON() throws Exception {

		iospageobjects.playback_errorDismiss_Button.click();
		Thread.sleep(5000);

//		ioscommonfunction.turnWifiON("Turning ON WiFi Connection", iosdriver, iospageobjects.wifi_mode,
//				iospageobjects.dismiss_wholewindow,ScreenshotPath, "Wifi ON");

		// WebElement playagain =
		// iosdriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[6]"));
		// playagain.click();
//		assertTrue(ioscommonfunction.isElementPresent(iosdriver,
//				By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[6]")));
		ioscommonfunction.tapbutton(iospageobjects.playback_start_button, "Starting the Playback", iosdriver, ScreenshotPath);


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
		ioscommonfunction.tapbutton(iospageobjects.playback_stop, "Stopping the Playback", iosdriver,ScreenshotPath);


		// iospageobjects.playback_stop.click();
		// Thread.sleep(3000);
	}

	@Test(dependsOnMethods = { "StopPlayback" })
	public void ClosePlayback() throws Exception {


		iospageobjects.playback_close.click();
		Thread.sleep(300);

	}

	@AfterClass
	public void tearDown() {
		ioscommonfunction.GenerateReport();
		iosdriver.quit();
		iosdriver.resetApp();
		appiummanager.stopappium();
	}






}
