package main.java.test.DRMCommonFunctions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.test.smpFunctions.*;
import main.java.test.smpFunctions.LiveRewindFunctions;
import main.java.test.smpFunctions.iOSCommonFunctions;
import main.java.test.smpPageObjects.CommonObjects;
import main.java.test.smpPageObjects.LiveRewindPageObjects;
import main.java.test.smpPageObjects.OnDemandPageObjects;
import main.java.test.smpPageObjects.iOSCommonObjects;
import main.java.test.smpUtilityFunctions.AppiumManager;
import main.java.test.smpUtilityFunctions.PortFactory;

public class iOSDemoApp {

	/*
	 * Page Object Class
	 */
	CommonObjects cobjects;
	OnDemandPageObjects ondemandobjects;

	public List<String> deviceID = new ArrayList<String>();
	public List<String> deviceOS = new ArrayList<String>();
	public List<String> deviceName = new ArrayList<String>();

	public IOSDriver<WebElement> iosdriver = null;
	public DesiredCapabilities capabilities;
	String message;

	iOSCommonObjects iospageobjects;
	CommonObjects commonobjects;

	public WebDriverWait wait;

	String filename = "OnDemandPlayback";
	String workingDirectory = "/Users/ramakh01/Desktop/AvTestHarness/AvTestHarness/Results"; /// System.getProperty("user.dir");
	String absoluteFilePath = workingDirectory + File.separator + filename;
	public String ScreenshotPath = "/Users/ramakh01/Desktop/AvTestHarness/AvTestHarness/Results/LiveSimulcast";

	File file;// = new File(absoluteFilePath);

	CommonFunction commonfunction = new CommonFunction();
	AppiumManager appiummanager = new AppiumManager();
	LiveRewindPageObjects liverewindobject = new LiveRewindPageObjects();
	LiveRewindFunctions liverewindFunctions = new LiveRewindFunctions();

	iOSCommonFunctions ioscommonfunction = new iOSCommonFunctions();



	PortFactory portFactory = new PortFactory();

	@BeforeClass
	public void setUp() throws Exception, MalformedURLException
	{
		appiummanager.startAppium(4723);
		appiummanager.AppiumURL();
		String appiul_url = appiummanager.AppiumURL();
		System.out.println("Appium Service Address : - " + appiul_url);

		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.6");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "MCP's iPad");
		capabilities.setCapability(MobileCapabilityType.UDID, "df43e12f4ba40c8763eb37dc17195717e094ee96");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3.5");
		capabilities.setCapability(MobileCapabilityType.APP,
				"/Users/ramakh01/Desktop/AvTestHarness/iOSApp/AVTestHarness.ipa");
		capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
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

			// ioscommonfunction.tapbutton(iospageobjects.update_ignoreButton,
			// "Dismissing the Update Alert", iosdriver);

			iospageobjects.update_ignoreButton.click();
			Thread.sleep(1000);



		} catch (Exception e) {
			e.printStackTrace();
		}
	}





	@Test(dependsOnMethods = { "OpenAvtest" })
	public void listview2() throws Exception, ArrayIndexOutOfBoundsException {

		String iosa = "Audio Factory Live to AOD";

		String item = "MPS - VOD - Production (News sample)";

		String item2 = "Personalisation sign in video";

		String item3 = "MF - Video Live - Stage (BBC News 24, HD Simulcast)";

		String item4 = "MF - VOD - Download regression test";

		String iosa1 = "MF - Video Live - Stage (BBC Radio One Video, SD Webcast";

		String iosliveEpsiode = "MF - VOD - Production (Combined profiles, HD)";
		// MF - AOD - Production - (Audio Combined Profiles, 5 live

		try {

			Boolean found_result = false;
			Boolean found = false;

			int counter = 0;

			while (!found_result) {

				List<WebElement> ele1 = iosdriver
						.findElements(By.xpath(
								"//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell/*"));

				// List<WebElement> ele1 =
				// iosdriver.findElementsByClassName("UIAStaticText");
				int size = 0;
				size = size + ele1.size();
				System.out.println("Size ARE AS " + size);

				for (int i = 0; i < size; i++) {

					String s = ele1.get(i).getText();

					System.out.println("The iOS Assets are:---" + s);

					System.out.println("The SubSTring of Assets are:---" + s.substring(0));

					if (s.substring(0).equals(iosa)) {

						found_result = true;
						System.out.println("Matched");
						System.out.println("Size Item is " + i);
						System.out.println("Size is " + size);
						ele1.get(i).click();
						Thread.sleep(5000);
						break;

					} else if (counter == 8) {
						if (!found_result) {
							System.out.println("NotMatched");
							counter = 0;
							swipingVertical();

						}

					}
					counter++;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	@Test(dependsOnMethods = { "listview2" })
	public void play() throws Exception {

		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, iospageobjects.wifi_mode,
				iospageobjects.dismiss_wholewindow,ScreenshotPath, "Wifi Off");

	}

	@AfterClass
	public void End() {
		commonfunction.GenerateReport();
		iosdriver.resetApp();
		iosdriver.quit();
		appiummanager.stopappium();

	}

	public void swipingVertical() throws InterruptedException {
		// Get the size of screen.
		Dimension size = iosdriver.manage().window().getSize();
		System.out.println(size);

		// Find swipe start and end point from screen's with and height.
		// Find starty point which is at bottom side of screen.
		int starty = (int) (size.height * 0.80);
		// Find endy point which is at top side of screen.
		int endy = (int) (size.height * 0.20);
		// Find horizontal point where you wants to swipe. It is in middle of
		// screen width.
		int startx = size.width / 2;
		System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

		// Swipe from Bottom to Top.
		Thread.sleep(8000);
		iosdriver.swipe(startx, starty, startx, endy, 13000);

		// Swipe from Top to Bottom.
		// driver.swipe(startx, endy, startx, starty, 13000);
		Thread.sleep(2000);
	}

}
