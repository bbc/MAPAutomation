package main.java.test.smpMainTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import main.java.test.smpUtilityFunctions.AppiumManager;
import main.java.test.smpUtilityFunctions.PortFactory;

public class SMPAndroidLiveSimulcast {
	
	/*
	 * Page Object Class
	 */
	CommonObjects cobjects;
	LiveRewindPageObjects lobjects;

	public List<String> deviceID = new ArrayList<String>();
	public List<String> deviceOS = new ArrayList<String>();
	public List<String> deviceName = new ArrayList<String>();

	public AndroidDriver<WebElement> driver = null;
	public DesiredCapabilities capa;
	String message;

	public WebDriverWait wait;



	String filename = "LiveSimulcast";
	String workingDirectory = "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/Results"; /// System.getProperty("user.dir");
	String absoluteFilePath = workingDirectory + File.separator + filename;
	public String ScreenshotPath = "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/Results/LiveSimulcast";

	File file;// = new File(absoluteFilePath);

	AppiumManager ap = new AppiumManager();
	CommonFunction commonfunction = new CommonFunction();
	LiveRewindPageObjects liverewindobject = new LiveRewindPageObjects();
	LiveRewindFunctions liverewindFunctions = new LiveRewindFunctions();

	CommonObjects commonobjects = new CommonObjects();


	PortFactory portFactory = new PortFactory();


	@BeforeClass
	@Parameters({ "deviceID", "deviceOS", "AppiumPort" })
	public void setUp(String deviceID, String deviceOS, int appiumPort)
			throws Exception, MalformedURLException {
		ap.startAppium(appiumPort);
		ap.AppiumURL();
		String appiul_url = ap.AppiumURL();
		System.out.println("Appium Service Address : - " + appiul_url);

		capa = new DesiredCapabilities();
		capa.setCapability("appium-version", "1.0");
		capa.setCapability("deviceName", deviceID);
		capa.setCapability("platformName", "Android");
		capa.setCapability("platformVersion", deviceOS);
		capa.setCapability("app", "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/BuildsSMP-AN/SMP-AN-27.4327.apk");
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
	@Parameters({ "deviceID", "AppiumPort", "deviceOS", "deviceName" }) 
	public void OpenAvtest(String deviceID, String Port, String deviceOS, String deviceName) throws Exception
	{
		
		commonobjects = new CommonObjects();
		PageFactory.initElements(new AppiumFieldDecorator(driver), commonobjects);

		liverewindobject = new LiveRewindPageObjects();
		PageFactory.initElements(new AppiumFieldDecorator(driver), liverewindobject);


		commonobjects.menu.click();
		Thread.sleep(3000);
		
		commonobjects.liverewind_button.click();
		Thread.sleep(5000);

//		driver.pressKeyCode(AndroidKeyCode.BACK);
//
//		Thread.sleep(3000);

		commonfunction.CreateReport(absoluteFilePath, deviceID, Port, deviceOS, deviceName);

	}
	
	@Test(dependsOnMethods = { "OpenAvtest" })
	public void PlaybackStart() throws Exception {


		commonfunction.selectItemforPlayback(commonobjects.liveEpsiode, "Live TV Simulcast", commonobjects.element,
				driver, commonobjects.listview, ScreenshotPath);
	}
	
	
	@Test(dependsOnMethods = { "PlaybackStart" })
	public void PlaybackContinues() {
		// logger = extent.startTest("Live rewind Playback", "Checking the Live
		// Simuclast Rewind Playback");
		try {
	
			commonfunction.tapbutton("Clicking on PlayButton", commonobjects.vpidPlay_button, driver, ScreenshotPath);

			commonfunction.tapbutton("Clicking on Full Screen button", commonobjects.fullscreen_button, driver,
					ScreenshotPath);

			commonfunction.PlaybackContinue("Live Channel Simulcast Playback",liverewindobject.live_simulcat_rewind_time, driver, ScreenshotPath);
			
			Thread.sleep(6000);
		}
		 catch (Exception e) {

			e.printStackTrace();
		}
		

	}
	
	
	@Test(dependsOnMethods = { "PlaybackContinues" })
	public void liveSimulcast_Assertions_Present() {
		try
		{
			commonfunction.Assert_TransportControls(driver, absoluteFilePath, commonobjects.Simulcast_assertions,
					commonobjects.liveSimulcast_assertions_text, "uk.co.bbc.avtestharnesssmp:id/pause_button");
			
			commonfunction.Assert_TransportControlsNotPresent(driver, absoluteFilePath, commonobjects.Simulcast_assertions_buttonNotPresent, commonobjects.liveSimulcast_assertionsNotPresent_text);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods = { "liveSimulcast_Assertions_Present" })
	public void liveSimulcast_Assertions_NotPresent() {
		try
		{
				commonfunction.Assert_TransportControlsNotPresent(driver, absoluteFilePath, commonobjects.Simulcast_assertions_buttonNotPresent, commonobjects.liveSimulcast_assertionsNotPresent_text);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	@Test(dependsOnMethods = { "liveSimulcast_Assertions_NotPresent" })
	public void LiveplaybackStop() throws Exception {
		try {

//		commonfunction.playback_pause_resume(driver, commonobjects.simulcast_stop, "Live Playback Stop",
//						absoluteFilePath);
		commonfunction.tapbutton("Live Playback Stop", commonobjects.simulcast_stop, driver, absoluteFilePath);
			
			Thread.sleep(3000);
			
			driver.pressKeyCode(AndroidKeyCode.BACK);
			Thread.sleep(3000);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



	}
	
	
	
	@AfterClass
	public void End() {
		commonfunction.GenerateReport();
	//	driver.resetApp();
		driver.quit();
		ap.stopappium();

	}

}
