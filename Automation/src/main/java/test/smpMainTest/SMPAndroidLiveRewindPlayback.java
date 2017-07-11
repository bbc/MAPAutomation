package main.java.test.smpMainTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class SMPAndroidLiveRewindPlayback {

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



	String filename = "LiveRewind";
	String workingDirectory = "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/Results"; /// System.getProperty("user.dir");
	String absoluteFilePath = workingDirectory + File.separator + filename;
	public String ScreenshotPath = "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/Results/LiveSimulcast_Rewind";

	File file;// = new File(absoluteFilePath);

	AppiumManager ap = new AppiumManager();
	CommonFunction commonfunction = new CommonFunction();
	LiveRewindPageObjects liverewindobject = new LiveRewindPageObjects();
	LiveRewindFunctions liverewindFunctions = new LiveRewindFunctions();

	CommonObjects commonobjects = new CommonObjects();


	PortFactory portFactory = new PortFactory();


	@BeforeClass
	@Parameters({ "AppiumPort", "deviceID", "deviceOS" })
	public void setUp(int port, String deviceId, String OS)
			throws Exception, MalformedURLException {
		ap.startAppium(port);
		ap.AppiumURL();
		String appiul_url = ap.AppiumURL();
		System.out.println("Appium Service Address : - " + appiul_url);

		capa = new DesiredCapabilities();
		capa.setCapability("appium-version", "1.0");
		capa.setCapability("deviceName", deviceId);
		capa.setCapability("platformName", "Android");
		capa.setCapability("platformVersion", OS);
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
	@Parameters({ "AppiumPort", "deviceID", "deviceOS", "deviceName" })
	public void OpenAvtest(String deviceID, String Port, String deviceOS, String deviceName) throws Exception
	{
		
		commonobjects = new CommonObjects();
		PageFactory.initElements(new AppiumFieldDecorator(driver), commonobjects);

		liverewindobject = new LiveRewindPageObjects();
		PageFactory.initElements(new AppiumFieldDecorator(driver), liverewindobject);
		
	//	driver.findElement(By.xpath("//android.widget.TextView[@text='Use Live RDot Environment' and @index='0']")).click();
	//	Thread.sleep(1000);
		

		commonfunction.CreateReport(absoluteFilePath, deviceID, Port, deviceOS, deviceName);

	}


	@Test(dependsOnMethods={ "OpenAvtest" })
	public void enableRDot_live() throws Exception
		{
		
			commonobjects.menu.click();
			Thread.sleep(2000);
			
			commonfunction.tapbutton("Enabling the LiveRdot", commonobjects.enable_liveRdot, driver, ScreenshotPath);
			
			commonfunction.tapbutton("Checking the LiveRdot is enabled", commonobjects.menu, driver, ScreenshotPath);
			
			driver.pressKeyCode(AndroidKeyCode.BACK);
			Thread.sleep(2000);
		}

	@Test(dependsOnMethods = { "enableRDot_live" })
	public void PlaybackStart() throws Exception {


		commonfunction.selectItemforPlayback(commonobjects.liveEpsiode, "LiveRewind Simulcast", commonobjects.element,
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

			commonfunction.PlaybackContinue("Live Rewind Playback",liverewindobject.live_simulcat_rewind_time, driver, ScreenshotPath);
		}
		 catch (Exception e) {

			e.printStackTrace();
		}
		

	}


	@Test(dependsOnMethods = { "PlaybackContinues" })
	public void liveRewind_Assertions() {
		try
		{
			commonfunction.Assert_TransportControls(driver, absoluteFilePath, commonobjects.LiveRewdinRewind_assertions,
					commonobjects.liveRewind_assertions_text, commonobjects.stop_button);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "liveRewind_Assertions" })
	public void seeking_randomly() throws Exception {

		try {
			commonfunction.seekingRandomly(liverewindobject.live_rewind_progressbar, driver, absoluteFilePath, 0.50);

			commonfunction.seekingRandomly(liverewindobject.live_rewind_progressbar, driver, absoluteFilePath, 0.80);

			commonfunction.seekingRandomly(liverewindobject.live_rewind_progressbar, driver, absoluteFilePath, 0.30);

			// liverewindFunctions.LiveText_Checking(commonobjects.LiveIcon_text,
			// driver, absoluteFilePath);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}



	@Test(dependsOnMethods = { "seeking_randomly" })
	public void playbackseeking_backard_forward() throws NullPointerException, Exception {

		try {
			commonfunction.Seekingbackwardforward(liverewindobject.live_rewind_progressbar, "End", driver,
					absoluteFilePath);

			commonfunction.LiveText_Checking(driver, absoluteFilePath);

			commonfunction.Seekingbackwardforward(liverewindobject.live_rewind_progressbar, "Beginning", driver,
					absoluteFilePath);


		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}

	@Test(dependsOnMethods = { "playbackseeking_backard_forward" })
	public void LiveRewindplaybackpause() throws Exception {
		try {

			commonfunction.playback_pause_resume(driver, commonobjects.Playback_Pause, "LiveRewind Playback Pause",
						absoluteFilePath);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



	}

	@Test(dependsOnMethods = { "LiveRewindplaybackpause" })
	public void LiveRewindplaybackresume() throws Exception {
		try {

			driver.manage().timeouts().implicitlyWait(580, TimeUnit.SECONDS);

			System.out.println("Waited for 580 Secds");

			commonfunction.tapbutton("Existing the Full Screen button", commonobjects.vod_play_fullscreen_exit, driver,
					ScreenshotPath);

			commonfunction.Navigateback_MainMenu(driver, ScreenshotPath);

			commonfunction.selectItemforPlayback(commonobjects.liveEpsiode, "LiveRewind Simulcat",
					commonobjects.element, driver, commonobjects.listview, ScreenshotPath);

			commonfunction.tapbutton("Playing the Simulcast to check Live Text displayed",
					commonobjects.vpidPlay_button, driver, ScreenshotPath);

			commonfunction.LiveText_Checking(driver, absoluteFilePath);

			commonfunction.Navigateback_MainMenu(driver, ScreenshotPath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void End() {
		commonfunction.GenerateReport();
		driver.resetApp();
		driver.quit();
		ap.stopappium();

	}

}
