package main.java.test.smpMainTest;

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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.java.test.smpFunctions.CommonFunction;
import main.java.test.smpFunctions.LiveRewindFunctions;
import main.java.test.smpPageObjects.CommonObjects;
import main.java.test.smpPageObjects.LiveRewindPageObjects;
import main.java.test.smpPageObjects.OnDemandPageObjects;
import main.java.test.smpUtilityFunctions.AppiumManager;
import main.java.test.smpUtilityFunctions.PortFactory;

public class OnDemandDemo {

	/*
	 * Page Object Class
	 */
	CommonObjects cobjects;
	OnDemandPageObjects ondemandobjects;

	public List<String> deviceID = new ArrayList<String>();
	public List<String> deviceOS = new ArrayList<String>();
	public List<String> deviceName = new ArrayList<String>();

	public AndroidDriver<WebElement> driver = null;
	public DesiredCapabilities capa;
	String message;

	public WebDriverWait wait;

	String filename = "OnDemandPlayback";
	String workingDirectory = "/Users/ramakh01/Desktop/AvTestHarness/AvTestHarness/Results"; /// System.getProperty("user.dir");
	String absoluteFilePath = workingDirectory + File.separator + filename;
	public String ScreenshotPath = "/Users/ramakh01/Desktop/AvTestHarness/AvTestHarness/Results/LiveSimulcast";

	File file;// = new File(absoluteFilePath);

	AppiumManager ap = new AppiumManager();
	CommonFunction commonfunction = new CommonFunction();
	LiveRewindPageObjects liverewindobject = new LiveRewindPageObjects();
	LiveRewindFunctions liverewindFunctions = new LiveRewindFunctions();

	CommonObjects commonobjects = new CommonObjects();

	PortFactory portFactory = new PortFactory();

	@BeforeClass
	@Parameters({ "AppiumPort", "DeviceID", "DeviceOS" })
	public void setUp(int port, String deviceId, String OS) throws Exception, MalformedURLException {
		ap.startAppium(port);
		ap.AppiumURL();
		String appiul_url = ap.AppiumURL();
		System.out.println("Appium Service Address : - " + appiul_url);

		capa = new DesiredCapabilities();
		capa.setCapability("appium-version", "1.0");
		capa.setCapability("deviceName", deviceId);
		capa.getCapability("udid");

		capa.setCapability("platformName", "Android");
		capa.setCapability("platformVersion", OS);
		capa.setCapability("app", "/Users/ramakh01/Desktop/AvTestHarness/BuildsSMP-AN/smp-an-24.4081.apk");
		capa.setCapability("platformName", "Android");
		capa.setCapability("appPackage", "uk.co.bbc.avtestharnesssmp");
		capa.setCapability("appActivity", "uk.co.bbc.avtestharnesssmp.MainActivity");
		try {
			driver = new AndroidDriver<>(new URL(appiul_url), capa);
			// capa.setCapability("newCommandTimeout", timeout);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	@Parameters({ "DeviceID", "AppiumPort", "DeviceOS", "DeviceName" })
	public void OpenAvtest(String deviceID, String Port, String deviceOS, String deviceName) throws Exception {
		try {
			commonobjects = new CommonObjects();
			PageFactory.initElements(new AppiumFieldDecorator(driver), commonobjects);

			liverewindobject = new LiveRewindPageObjects();
			PageFactory.initElements(new AppiumFieldDecorator(driver), liverewindobject);

			ondemandobjects = new OnDemandPageObjects();
			PageFactory.initElements(new AppiumFieldDecorator(driver), ondemandobjects);

			commonfunction.CreateReport(absoluteFilePath, deviceID, Port, deviceOS, deviceName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test(dependsOnMethods = { "OpenAvtest" })
	// public void listview() throws Exception, ArrayIndexOutOfBoundsException {
	// try {
	// commonfunction.open_Menu(commonobjects.menu, "Opening the Menu ", driver,
	// absoluteFilePath);
	//
	// driver.pressKeyCode(AndroidKeyCode.BACK);
	//
	// String a = "b081ztv2";
	// Boolean found_result = false;
	// Boolean found = false;
	//
	// while (!found_result) {
	//
	// List<WebElement> ele =
	// driver.findElements(By.id("uk.co.bbc.avtestharnesssmp:id/name"));
	//
	// int size = 0;
	// size = size + ele.size();
	// System.out.println("Size ARE AS " + size);
	//
	// for (int i = 0; i < size; i++) {
	//
	// String s = ele.get(i).getText();
	// System.out.println("The Assets are:---" + s);
	// System.out.println("The SubSTring of Assets are:---" + s.substring(48,
	// 56));
	//
	// if (s.substring(48, 56).equals(a)) {
	//
	// found = true;
	// found_result = true;
	// System.out.println("Matched");
	// System.out.println("Size is " + size);
	// int sizes = size - 2;
	// ele.get(sizes).click();
	// break;
	//
	// }
	// }
	//
	// if (!found) {
	// // find startx,starty, and Endy
	// System.out.println("NotMatched");
	// // swipingVertical();
	// }
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

	// @Test(dependsOnMethods = { "OpenAvtest" })
	public void listview1() throws Exception, ArrayIndexOutOfBoundsException {
		try {
			commonfunction.open_Menu(commonobjects.menu, "Opening the Menu ", driver, absoluteFilePath);

			driver.pressKeyCode(AndroidKeyCode.BACK);

			String a = "MF - VOD - Production (Combined profiles, HD) : b081ztv2 mediasel";

			String a1 = "MF - AOD - Production - (Audio Combined Profiles, 5 live Breakfas";

			Boolean found_result = false;
			Boolean found = false;

			while (!found_result) {

				List<WebElement> ele = driver.findElements(By.id("uk.co.bbc.avtestharnesssmp:id/name"));

				int size = 0;
				size = size + ele.size();
				System.out.println("Size ARE AS " + size);

				for (int i = 0; i < size; i++) {

					String s = ele.get(i).getText();
					System.out.println("The Assets are:---" + s);
					System.out.println("The SubSTring of Assets are:---" + s.substring(48, 56));

					if (s.substring(0, 56).equals(a1)) {

						found_result = true;
						System.out.println("Matched");
						System.out.println("Size Item is " + i);

						System.out.println("Size is " + size);

						int sizes = size - 1;
						ele.get(i).click();
						break;

					} else {

						System.out.println("NotMatched");
						swipingVertical();

				}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(dependsOnMethods = { "OpenAvtest" })
	public void listview2() throws Exception, ArrayIndexOutOfBoundsException {

		String a = "MF - VOD - Production (Combined profiles, HD) : b081ztv2";

		String a1 = "MF - AOD - Production (Audio international with progress";

		String liveEpsiode = "MF - Video Live - Production (BBC Two, HD Simulcast) : b";
		// MF - AOD - Production - (Audio Combined Profiles, 5 live

		try {


			Boolean found_result = false;
			Boolean found = false;

			System.out.println("itemtoFound" + a);

			int counter = 0;

			while (!found_result) {

				List<WebElement> ele = driver.findElements(By.id("uk.co.bbc.avtestharnesssmp:id/name"));

				int size = 0;
				size = size + ele.size();
				System.out.println("Size ARE AS " + size);

				for (int i = 0; i < size; i++) {

					String s = ele.get(i).getText();
					System.out.println("The Assets are:---" + s);
					System.out.println("The SubSTring of Assets are:---" + s.substring(0, 56));

					if (s.substring(0, 56).equals(liveEpsiode)) {

						found_result = true;
						System.out.println("Matched");
						System.out.println("Size Item is " + i);
						System.out.println("Size is " + size);
						ele.get(i).click();
						Thread.sleep(5000);
						break;

					} else if (counter == 5) {
						if (!found_result) {
							counter = 0;
							System.out.println("NotMatched");
							swipingVertical();

						}

					}
				}
				counter++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(dependsOnMethods = { "listview2" })
	public void play() throws Exception {

		commonobjects.play_button.click();
		Thread.sleep(4000);

		// driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/placeholder_play_button")).click();
		// Thread.sleep(2000);

		commonobjects.fullscreen_button.click();
		Thread.sleep(3000);
	}



	@AfterClass
	public void End() {
		commonfunction.GenerateReport();
		driver.resetApp();
		driver.quit();
		ap.stopappium();

	}

	public void swipingVertical() throws InterruptedException {
		// Get the size of screen.
		Dimension size = driver.manage().window().getSize();
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
		driver.swipe(startx, starty, startx, endy, 13000);

		// Swipe from Top to Bottom.
		// driver.swipe(startx, endy, startx, starty, 13000);
		Thread.sleep(2000);
	}

}
