package main.java.test.smpMainTest;

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
import io.appium.java_client.remote.AutomationName;
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

public class iOSOnDemand_VideoPlayback {

	iOSCommonObjects iospageobjects;
	CommonObjects commonobjects;

	public List<String> deviceID = new ArrayList<String>();
	public List<String> deviceOS = new ArrayList<String>();
	public List<String> deviceName = new ArrayList<String>();

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
		capabilities.setCapability(MobileCapabilityType.APP,
				"//Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/iOSApp/AVTestHarness.ipa");
		capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, false);


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
					"9.3.5",
					"iPad-Air2");

			ioscommonfunction.tapbutton(iospageobjects.update_ignoreButton, "Dismissing the Update Alert",
					iosdriver,ScreenshotPath);



		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "OpenAvtest" })
	public void OnDemand_PlaybackStart() throws Exception
	{
		try
		{

			ioscommonfunction.tapbutton(iospageobjects.OnDemand_Video, "Staring the Video OnDemand Playback",
					iosdriver,ScreenshotPath);

			iospageobjects.playback_transportcontrol.click();
			Thread.sleep(100);

			iospageobjects.playback_subtitle_button.click();
			Thread.sleep(200);

			// ioscommonfunction.tapbutton(iospageobjects.playback_subtitle_button,
			// "Turning on Subtitle for Video OnDemand Playback",
			// iosdriver, ScreenshotPath);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "OnDemand_PlaybackStart" })
	public void turn_WiFiOff() throws Exception {
//		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, iospageobjects.wifi_mode,
//				iospageobjects.dismiss_wholewindow,ScreenshotPath, "Wifi Off");
	}

	@Test(dependsOnMethods = { "turn_WiFiOff" })
	public void playback_ErrorMessage() throws Exception {

		commonfunction.waitForScreenToLoad(iosdriver,
				iosdriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[3]")), 1300);

		System.out.println("Dismiss Button:-  " + iospageobjects.playback_errorDismiss_Button.getText());
		// +
		// iosdriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[3]")).getText());

		System.out.println("Try again Button:-  " + iospageobjects.playback_errorTryagain_Button.getText());
		// +
		// iosdriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAButton[4]")).getText());

		// WebElement error_message =
		// iosdriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAStaticText[2]"));
		String errormessage = iospageobjects.playback_errorMessage.getText();// error_message.getText();
		System.out.println("The Error message is" + errormessage);
		Thread.sleep(8000);

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
		iospageobjects.playback_start_button.click();
		Thread.sleep(100);

	}

	@Test(dependsOnMethods = { "TurnWiFi_ON" })
	public void OnDemand_PlaybackPause() throws Exception {
		try {

			// boolean elementpresent =
			// iospageobjects.playback_subtitle_button.isDisplayed();
			// System.out.println("ElementPresent value " + elementpresent);

			iospageobjects.playback_stop.click();
			Thread.sleep(100);

			// ioscommonfunction.tapbutton(iospageobjects.playback_stop,
			// "Pausing the Video OnDemand Playback", iosdriver,
			// ScreenshotPath);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "OnDemand_PlaybackPause" })
	public void playbackseeking() {
		try {

			iospageobjects.playback_transportcontrol.click();
			Thread.sleep(100);

			int startX = iospageobjects.playback_progressbar.getLocation().getX();
			
			iospageobjects.playback_transportcontrol.click();
			Thread.sleep(100);
//			ioscommonfunction.seek_bar_swipe(iosdriver, iospageobjects.playback_progressbar, startX, 0.5);// 0.5);
			screenhotfiles = ((TakesScreenshot) iosdriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenhotfiles, new File("Seeking 50%%"));


			iospageobjects.playback_transportcontrol.click();
			Thread.sleep(100);
			int startX1 = iospageobjects.playback_progressbar.getLocation().getX();
			//ioscommonfunction.seekingRandomly(iospageobjects.playback_progressbar, iosdriver, ScreenshotPath, 0.60);
//			ioscommonfunction.seek_bar_swipe(iosdriver, iospageobjects.playback_progressbar, startX1, 0.95);
			screenhotfiles = ((TakesScreenshot) iosdriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenhotfiles, new File("Seeking 95%"));

			iospageobjects.playback_transportcontrol.click();
			Thread.sleep(100);
			int startX2 = iospageobjects.playback_progressbar.getLocation().getX();

			//ioscommonfunction.seekingRandomly(iospageobjects.playback_progressbar, iosdriver, ScreenshotPath, 0.90);
//			ioscommonfunction.seek_bar_swipe(iosdriver, iospageobjects.playback_progressbar, startX2, 0.3);
			screenhotfiles = ((TakesScreenshot) iosdriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenhotfiles, new File("Seeking 30%"));

			iospageobjects.playback_transportcontrol.click();



		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void tearDown() {

		ioscommonfunction.GenerateReport();
		iosdriver.quit();
		appiummanager.stopappium();
		
	}


}
