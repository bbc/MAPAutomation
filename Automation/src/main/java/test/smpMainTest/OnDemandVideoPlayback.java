package main.java.test.smpMainTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.java.test.smpFunctions.CommonFunction;
import main.java.test.smpFunctions.LiveRewindFunctions;
import main.java.test.smpPageObjects.CommonObjects;
import main.java.test.smpPageObjects.LiveRewindPageObjects;
import main.java.test.smpPageObjects.OnDemandPageObjects;
import main.java.test.smpUtilityFunctions.AppiumManager;
import main.java.test.smpUtilityFunctions.PortFactory;

public class OnDemandVideoPlayback {

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

	String filename = "OnDemandVideoPlayback";
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
		capa.setCapability("platformName", "Android");
		capa.setCapability("platformVersion", OS);
		capa.setCapability("app", "/../../../../..-AN/SMP-AN-25.4108-dev.apk");
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
	


	@Test(dependsOnMethods = { "OpenAvtest" })
	public void openVpidBrowser() throws Exception {

		try {
			// commonfunction.open_Menu(commonobjects.menu, "Opening the Menu ",
			// driver, absoluteFilePath);

			// commonfunction.VpidBrowser("Entering the VPID in Vpid browser",
			// commonobjects.vpid_browser,
			// commonobjects.vpid_enter, commonobjects.Video_vpid, "Video",
			// commonobjects.vpid_load, driver,
			// absoluteFilePath);

			// commonfunction.open_Menu(commonobjects.menu, "Opening the Menu ",
			// driver, ScreenshotPath);
			// driver.pressKeyCode(AndroidKeyCode.BACK);

			commonfunction.selectItemforPlayback(commonobjects.videoEpisode, "OnDemand Video Programme",
					commonobjects.element, driver, commonobjects.listview, ScreenshotPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(dependsOnMethods = { "openVpidBrowser" })
	 public void vpideoPlayback() throws Exception {
	
	 commonfunction.tapbutton("Clicking onPlayButton", commonobjects.vpidPlay_button, driver,
				ScreenshotPath);
	 Thread.sleep(1000);
	
	 // commonfunction.open_Menu(commonobjects.vpid_playback_fullscreen,
	 // "Clicking on Full Screen Button", driver,
	 // absoluteFilePath);
	
	 commonfunction.playback_enter_exitFullScreen(driver,
	 commonobjects.vpid_playback_fullscreen,
				"Entering Full Screen", ScreenshotPath);
	
	
		commonfunction.tapbutton("Turningsubtitle ON", ondemandobjects.vod_play_subtitle, driver,
				ScreenshotPath);
	
	 }

	@Test(dependsOnMethods = { "vpideoPlayback" })
	 public void videoPlaying() throws Exception {
	
	 try {
	
	
	
	 commonfunction.Checkplayback_duration(ondemandobjects.vod_play_total_duration,
					ondemandobjects.vod_play_elasped_duration, ScreenshotPath, driver);
	
	 } catch (Exception e) {
	
	 e.printStackTrace();
	 }
	

	

	 }

	@Test(dependsOnMethods = { "videoPlaying" })
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

	@Test(dependsOnMethods = { "PlaybackRotation" })
	 public void Playback_retry() throws Exception {
	
	
	
	
	 try {
	
	 commonfunction.playback_retry("Checking the OnDemand Video Retry",
	 commonobjects.transport,
					ScreenshotPath,
	 commonobjects.vod_error_message, commonobjects.errormessage,
	 commonobjects.errorbutton,
	 commonobjects.vod_try_again_button, driver);
	
	 commonfunction.check_playbackbuffer(commonobjects.transport,
	 commonobjects.buffermessage,
					ScreenshotPath, driver);
	
	 } catch (Exception e) {
	
	 e.printStackTrace();
	 }
	
	 }

	@Test(dependsOnMethods = { "Playback_retry" })
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

	@Test(dependsOnMethods = { "playbackseeking" })
	public void playback_pause() throws Exception {

		commonfunction.playback_pause_resume(driver, ondemandobjects.vod_play_pause, "Playback Paused",
				ScreenshotPath);
		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = { "playback_pause" })
	public void Playback_SeektoEnd() throws Exception {

		commonfunction.Seekingbackwardforward(ondemandobjects.vod_play_seekbar, "End", driver, ScreenshotPath);

		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = { "Playback_SeektoEnd" })
	public void playback_resume() throws Exception {

		commonfunction.playback_pause_resume(driver, ondemandobjects.vod_play_pause, "Playback Resumed",
				ScreenshotPath);
		Thread.sleep(3000);

		commonfunction.playback_enter_exitFullScreen(driver, commonobjects.vod_play_fullscreen_exit,
				"Exiting Full Screen", ScreenshotPath);
		Thread.sleep(3000);

		commonfunction.Navigateback_MainMenu(driver, ScreenshotPath);
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
		driver.swipe(startx, starty, startx, endy, 20000);
		Thread.sleep(13000);
		// Swipe from Top to Bottom.
		// driver.swipe(startx, endy, startx, starty, 13000);
		// Thread.sleep(2000);
	}
}
