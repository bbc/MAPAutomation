package main.java.test.smpMainTest;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import main.java.test.smpFunctions.CommonFunction;
import main.java.test.smpFunctions.LiveRewindFunctions;
import main.java.test.smpPageObjects.CommonObjects;
import main.java.test.smpPageObjects.LiveRewindPageObjects;
import main.java.test.smpPageObjects.OnDemandPageObjects;
import main.java.test.smpUtilityFunctions.AppiumManager;
import main.java.test.smpUtilityFunctions.CommandPrompt;
import main.java.test.smpUtilityFunctions.DeviceList;
import main.java.test.smpUtilityFunctions.PortFactory;

public class SMPAndroidParallelExecution {

	public AndroidDriver<WebElement> driver;
	public DesiredCapabilities capa;
	String message;
	//LiveSimulcast_POM simulcastpom;
	public WebDriverWait wait;
	private StringBuffer verificationErrors = new StringBuffer();

	// String filename = "SMPParallelTest";
	String workingDirectory = "/Users/ramakh01/Desktop/AvTestHarness/AvTestHarness/Results"; /// System.getProperty("user.dir");
	String absoluteFilePath = workingDirectory;
	public String ScreenshotPath = "/Users/ramakh01/Desktop/AvTestHarness/AvTestHarness/Results/ParallelTests";
	File file;// = new File(absoluteFilePath);
	ExtentReports extent;
	ExtentTest logger;

	AppiumManager ap = new AppiumManager();
	CommonFunction funct = new CommonFunction();
	//DeviceConfiguration device_list = new DeviceConfiguration();



	OnDemandPageObjects ondemandobjects;
	CommonFunction commonfunction = new CommonFunction();
	LiveRewindPageObjects liverewindobject = new LiveRewindPageObjects();
	LiveRewindFunctions liverewindFunctions = new LiveRewindFunctions();

	CommonObjects commonobjects = new CommonObjects();

	private static String sdkPath = "/Users/ramakh01/Downloads/android-sdk/platform-tools/";
	private static String adbPath = sdkPath + File.separator + "./adb";
	String[] commandDevices = new String[] { adbPath, "devices" };
	CommandPrompt cmd = new CommandPrompt();

	public List<String> deviceID = new ArrayList<String>();
	public List<String> deviceOS = new ArrayList<String>();
	public List<String> deviceName = new ArrayList<String>();

	String DeviceosName;
	String Deviceid;
	String Devicename;
	int appiumport;

	Process process;
	String output;

	DeviceList devicelist = new DeviceList();

	PortFactory portFactory = new PortFactory();

	@BeforeClass
	public void RunTest() throws Exception {

		try {

			getAllDetails();
			devicelist.populateDevices_IDs();
			devicelist.populateDevices_OS();
			devicelist.populateDevices_Names();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test // (dependsOnMethods = { "RunTest" })
	public void getAllDetails() throws Exception {

		for (int i = 0; i < devicelist.deviceOS.size(); i++) {
			try {
				DeviceosName = devicelist.deviceOS.get(i);
				Deviceid = devicelist.deviceID.get(i);
				Devicename = devicelist.deviceName.get(i);
				appiumport = portFactory.create();
				System.out.println("The Device OS is " + DeviceosName);
				System.out.println("The Device ID is " + Deviceid);
				System.out.println("The Device port is " + Devicename);
				System.out.println("The Device Name is " + appiumport);

				setUp(appiumport, Deviceid, DeviceosName);



				OpenAvtest();

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		}
		// ap.stopappium();
	}

	public void setUp(int port, String deviceId, String OS) throws Exception {
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
	//	capa.setCapability(AndroidMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
		try {
			driver = new AndroidDriver<>(new URL(appiul_url), capa);
			// capa.setCapability("newCommandTimeout", timeout);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test // (dependsOnMethods = { "setUp" })
	public void OpenAvtest() throws Exception {
		try {
		

			commonobjects = new CommonObjects();
			PageFactory.initElements(new AppiumFieldDecorator(driver), commonobjects);

			liverewindobject = new LiveRewindPageObjects();
			PageFactory.initElements(new AppiumFieldDecorator(driver), liverewindobject);

			ondemandobjects = new OnDemandPageObjects();
			PageFactory.initElements(new AppiumFieldDecorator(driver), ondemandobjects);
			
			commonobjects.menu.click();
			Thread.sleep(3000);

			driver.pressKeyCode(AndroidKeyCode.BACK);

			Thread.sleep(3000);
			
			
			/*
			 * Live Simulcast Tests
			 * 
			 */
			
			/*
			 * Live Rewind Tests
			 * 
			 */

			commonfunction.CreateReport(absoluteFilePath + File.separator + "LiveRewind", Deviceid, DeviceosName,
					Integer.toString(appiumport), Devicename);

			startPlayback(commonobjects.liveEpsiode, "LiveSimulcast Programme");

			OnDemandPlayback("LiveRewind");

			PlaybackContinues();

			liveRewind_Assertions();

			PlaybackRotation();

			seeking_randomly();

			playbackseeking_backard_forward();

			LiveRewindplaybackpause();

			LiveRewindplaybackresume();

			commonfunction.GenerateReport();


			/*
			 * OnDemand Video Tests
			 * 
			 */

			commonfunction.CreateReport(absoluteFilePath + File.separator + "OnDemandVideo", Deviceid, DeviceosName,
					Integer.toString(appiumport), Devicename);

			startPlayback(commonobjects.videoEpisode, "OnDemand Video programme");

			OnDemandPlayback("Video");

			CheckingPlayback();

			PlaybackRotation();

			OnDemandPlayback_retry("OnDemand Video Playback Retry");

			seeking_randomly();// playbackseeking();

			playback_pause();

			Playback_SeektoEnd();

			playback_resume();

			commonfunction.GenerateReport();

			/*
			 * 
			 * Ondemand Audio Tests
			 */

			commonfunction.CreateReport(absoluteFilePath + File.separator + "OnDemandAudio", Deviceid, DeviceosName,
					Integer.toString(appiumport), Devicename);

			startPlayback(commonobjects.audioEpisode, "OnDemand Audio Programme");

			OnDemandPlayback("Audio");

			// openNotification();

			CheckingPlayback();

			// OnDemandPlayback_retry("OnDemand Audio Playback Retry");

			seeking_randomly();// playbackseeking();

			playback_pause();

			Playback_SeektoEnd();

			End();

		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void PlaybackStart() throws Exception {

		try {
			commonfunction.tapbutton("Playback Started", commonobjects.live_rewind_playback, driver, ScreenshotPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void PlaybackContinues() {

		try {


			commonfunction.PlaybackContinue("Live Rewind Playback",liverewindobject.live_simulcat_rewind_time, driver, ScreenshotPath);
		} catch (Exception e) {

			e.printStackTrace();
		}


	}


	public void liveRewind_Assertions() {
		try {
			commonfunction.Assert_TransportControls(driver, absoluteFilePath, commonobjects.LiveRewdinRewind_assertions,
					commonobjects.liveRewind_assertions_text, commonobjects.stop_button);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void seeking_randomly() throws Exception {

		try {
			commonfunction.seekingRandomly(liverewindobject.live_rewind_progressbar, driver, ScreenshotPath, 0.50);

			commonfunction.seekingRandomly(liverewindobject.live_rewind_progressbar, driver, ScreenshotPath, 0.80);

			commonfunction.seekingRandomly(liverewindobject.live_rewind_progressbar, driver, ScreenshotPath, 0.30);

			// liverewindFunctions.LiveText_Checking(commonobjects.LiveIcon_text,
			// driver, absoluteFilePath);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void playbackseeking_backard_forward() throws NullPointerException, Exception {

		try {
			commonfunction.Seekingbackwardforward(liverewindobject.live_rewind_progressbar, "Beginning", driver,
					ScreenshotPath);

			commonfunction.LiveText_Checking(driver, ScreenshotPath);

			commonfunction.Seekingbackwardforward(liverewindobject.live_rewind_progressbar, "End", driver,
					ScreenshotPath);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void LiveRewindplaybackpause() throws Exception {
		try {

			commonfunction.playback_pause_resume(driver, commonobjects.Playback_Pause, "LiveRewind Playback Pause",
					ScreenshotPath);

			driver.manage().timeouts().implicitlyWait(580, TimeUnit.SECONDS);

			System.out.println("Waited for 580 Secds");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void LiveRewindplaybackresume() throws Exception {
		try {



			commonfunction.tapbutton("Existing the Full Screen button", commonobjects.vod_play_fullscreen_exit, driver,
					ScreenshotPath);

			commonfunction.Navigateback_MainMenu(driver, ScreenshotPath);

			commonfunction.selectItemforPlayback(commonobjects.liveEpsiode, "LiveRewding Simulcast replayed",
					commonobjects.element, driver,
					commonobjects.listview, ScreenshotPath);

			commonfunction.tapbutton("Playing the Simulcast to check Live Text displayed",
					commonobjects.vpidPlay_button, driver, ScreenshotPath);

			commonfunction.LiveText_Checking(driver, absoluteFilePath);

			commonfunction.Navigateback_MainMenu(driver, ScreenshotPath);

		} catch (Exception e) {
			e.printStackTrace();
		}



	}

	/*
	 * On-Demand Functions
	 * 
	 */


	public void startPlayback(String vpid, String message) throws Exception {

		try {

			
			commonfunction.selectItemforPlayback(vpid, message, commonobjects.element, driver, commonobjects.listview,
					ScreenshotPath);
			


		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void OnDemandPlayback(String contentType) throws Exception {

		commonfunction.tapbutton("Clicking on Play Button", commonobjects.vpidPlay_button, driver, ScreenshotPath);
		Thread.sleep(1000);

		commonfunction.playback_enter_exitFullScreen(driver, commonobjects.vpid_playback_fullscreen,
				"Entering Full Screen", ScreenshotPath);

		if (contentType == "Video") {
			commonfunction.tapbutton("Turning subtitle ON", ondemandobjects.vod_play_subtitle, driver, ScreenshotPath);
		}

	}


	public void CheckingPlayback() throws Exception {

		try {

			commonfunction.Checkplayback_duration(ondemandobjects.vod_play_total_duration,
					ondemandobjects.vod_play_elasped_duration, ScreenshotPath, driver);



		} catch (Exception e) {

			e.printStackTrace();
		}

	}


	public void PlaybackRotation() throws Exception {

		try {

			commonfunction.playback_orientation(driver, ScreenOrientation.LANDSCAPE, "Playing in LANDSCAPE",
					ScreenshotPath);
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {

			commonfunction.playback_orientation(driver, ScreenOrientation.PORTRAIT, "Playing in PORTRAIT",
					ScreenshotPath);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}


	public void OnDemandPlayback_retry(String testname) throws Exception {

		try {

			commonfunction.playback_retry(testname, commonobjects.transport,
					ScreenshotPath,
					commonobjects.vod_error_message, commonobjects.errormessage, commonobjects.errorbutton,
					commonobjects.vod_try_again_button, driver);

			commonfunction.check_playbackbuffer(commonobjects.transport, commonobjects.buffermessage,
					ScreenshotPath, driver);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}


	public void playbackseeking() {
		try {
			commonfunction.seekingRandomly(ondemandobjects.vod_play_seekbar, driver, ScreenshotPath, 0.30);
			String seekingduration_30 = ondemandobjects.vod_play_elasped_duration.getText();

			commonfunction.seekingRandomly(ondemandobjects.vod_play_seekbar, driver, ScreenshotPath, 0.60);
			String seekingduration_60 = ondemandobjects.vod_play_elasped_duration.getText();

			commonfunction.seekingRandomly(ondemandobjects.vod_play_seekbar, driver, ScreenshotPath, 0.90);
			String seekingduration_90 = ondemandobjects.vod_play_elasped_duration.getText();

			if (!seekingduration_30.equals(seekingduration_60)) {
				System.out.println("Duration didn't Matched");
			} else if (!seekingduration_60.equals(seekingduration_90)) {
				System.out.println("Duration didn't Matched");
			}

		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void playback_pause() throws Exception {

		commonfunction.playback_pause_resume(driver, ondemandobjects.vod_play_pause, "Playback Paused",
				ScreenshotPath);
		Thread.sleep(3000);
	}


	public void Playback_SeektoEnd() throws Exception {

		commonfunction.Seekingbackwardforward(ondemandobjects.vod_play_seekbar, "End", driver, ScreenshotPath);

		Thread.sleep(3000);
	}


	public void playback_resume() throws Exception {

		commonfunction.playback_pause_resume(driver, ondemandobjects.vod_play_pause, "Playback Resumed",
				ScreenshotPath);
		Thread.sleep(3000);

		commonfunction.playback_enter_exitFullScreen(driver, commonobjects.vod_play_fullscreen_exit,
				"Exiting Full Screen", ScreenshotPath);
		Thread.sleep(4000);

		commonfunction.Navigateback_MainMenu(driver, ScreenshotPath);
	}

	/*
	 * 
	 * OnDemand Audio Playback Functions
	 * 
	 */

	public void openNotification() throws Exception {

		driver.openNotifications();

		Thread.sleep(4000);



		commonobjects.notificationtitle = commonobjects.notification_title.getText();

		System.out.println("title name under notification :-   " + commonobjects.notificationtitle);
		//

		commonobjects.notificationSutitle = commonobjects.notification_Subtitle.getText();

		System.out.println("Station name under notification:-   " + commonobjects.notificationSutitle);

		Assert.assertEquals(commonobjects.notificationSutitle, commonobjects.notification_Subtitle.getText());

		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(3000);

	}

	public void audioPlaying() throws Exception {

		try {

			commonfunction.Checkplayback_duration(ondemandobjects.vod_play_total_duration,
					ondemandobjects.vod_play_elasped_duration, ScreenshotPath, driver);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void CheckPlayback_retry() throws Exception {

		try {

			commonfunction.playback_retry("Checking the OnDemand Audio Retry", commonobjects.transport,
					ScreenshotPath, commonobjects.vod_error_message, commonobjects.errormessage,
					commonobjects.errorbutton, commonobjects.vod_try_again_button, driver);

			commonfunction.check_playbackbuffer(commonobjects.transport, commonobjects.buffermessage, ScreenshotPath,
					driver);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}



	// @AfterClass
	public void End()

	{
		commonfunction.GenerateReport();
		driver.closeApp();
		driver.quit();
		ap.stopappium();
		}

}
