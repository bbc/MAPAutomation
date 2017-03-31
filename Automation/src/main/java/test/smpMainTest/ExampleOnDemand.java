//package main.java.test.smpMainTest;
//
//import static org.testng.AssertJUnit.assertTrue;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.ScreenOrientation;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.AssertJUnit;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.relevantcodes.extentreports.DisplayOrder;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
//import com.test.Appium.AppiumManager.AppiumMang;
//import com.test.Appium.CommonFunctions.CommonFunctions;
//import com.test.Appium.LiveRewind.LiveRewind_POM;
//import com.test.Appium.LiveRewind.LiveRewind_Playback;
//import com.test.Appium.LiveSimulcast.LiveSimulcast_POM;
//import com.test.Appium.OnDemand.OnDemand_POM;
//import com.test.Appium.Util.CommandPrompt;
//import com.test.Appium.Util.DeviceConfiguration;
//import com.test.Appium.Util.PortFactory;
//
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.AndroidKeyCode;
//import io.appium.java_client.pagefactory.AppiumFieldDecorator;
//import main.java.test.smpFunctions.CommonFunction;
//import main.java.test.smpUtilityFunctions.AppiumManager;
//
//public class ExampleOnDemand {
//
//	public AndroidDriver driver;
//	public DesiredCapabilities capa;
//	String message;
//	//LiveSimulcast_POM simulcastpom;
//	public WebDriverWait wait;
//	private StringBuffer verificationErrors = new StringBuffer();
//
//	String filename = "AvTestParallelTest";
//	String workingDirectory = "/Users/ramakh01/Desktop/AvTestHarness/AvTestHarness/Results"; /// System.getProperty("user.dir");
//	String absoluteFilePath = workingDirectory + File.separator + filename;
//	public String ScreenshotPath = "/Users/ramakh01/Desktop/AvTestHarness/AvTestHarness/Results/ParallelTests";
//	File file;// = new File(absoluteFilePath);
//	ExtentReports extent;
//	ExtentTest logger;
//
//	AppiumManager ap = new AppiumManager();
//	CommonFunction funct = new CommonFunction();
//	main.java.test.smpUtilityFunctions.DeviceConfiguration device_list = new main.java.test.smpUtilityFunctions.DeviceConfiguration();
//	LiveRewind_POM liverewind;
//	LiveRewind_Playback lrp = new LiveRewindPlayback();
//	OnDemand_POM ondemand_page = new OnDemand_POM();
//
//	private static String sdkPath = "/Users/ramakh01/Downloads/android-sdk/platform-tools/";
//	private static String adbPath = sdkPath + File.separator + "./adb";
//	String[] commandDevices = new String[] { adbPath, "devices" };
//	CommandPrompt cmd = new CommandPrompt();
//
//	public List<String> deviceID = new ArrayList<String>();
//	public List<String> deviceOS = new ArrayList<String>();
//	public List<String> deviceName = new ArrayList<String>();
//
//	String osVersion;
//	Process process;
//	String output;
//
//	PortFactory portFactory = new PortFactory();
//
//
//	@BeforeClass
//	public void RunTest() throws Exception {
//
//		try {
//
//			getAllDetails();
//			populateDevices_IDs();
//			populateDevices_OS();
//			populateDevices_Names();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	@Test // (dependsOnMethods = { "RunTest" })
//	public void getAllDetails() throws Exception {
//
//
//		for (int i = 0; i < deviceOS.size(); i++) {
//			try {
//				String osName = deviceOS.get(i);
//				String id = deviceID.get(i);
//				String dname = deviceName.get(i);
//				int port = portFactory.create();
//				System.out.println("The Device OS is " + osName);
//				System.out.println("The Device ID is " + id);
//				System.out.println("The Device port is " + port);
//				System.out.println("The Device Name is " + dname);
//
//				setUp(port, id, osName);
//
//				AVTestApp_Intialise(id, osName, dname, Integer.toString(port));
//				// dname);
//
//				End();
//
//				// ap.stopappium();
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException(e);
//			}
//
//		}
//		// ap.stopappium();
//	}
//
//
//	// (dependsOnMethods = { "getAllDetails" })
//	public void setUp(int port, String deviceId, String OS) throws Exception {
//		ap.startAppium(port);
//		ap.AppiumURL();
//		String appiul_url = ap.AppiumURL();
//		System.out.println("Appium Service Address : - " + appiul_url);
//
//		capa = new DesiredCapabilities();
//		capa.setCapability("appium-version", "1.0");
//		capa.setCapability("deviceName", deviceId);
//		capa.setCapability("platformName", "Android");
//		capa.setCapability("platformVersion", OS);
//		capa.setCapability("app", "/Users/ramakh01/Desktop/AvTestHarness/BuildsSMP-AN/smp-an-24.4081.apk");
//		capa.setCapability("platformName", "Android");
//		capa.setCapability("appPackage", "uk.co.bbc.avtestharnesssmp");
//		capa.setCapability("appActivity", "uk.co.bbc.avtestharnesssmp.MainActivity");
//		try {
//			driver = new AndroidDriver<>(new URL(appiul_url), capa);
//			// capa.setCapability("newCommandTimeout", timeout);
//			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	// @Test // (dependsOnMethods = { "setUp" })
//	public void AVTestApp_Intialise(String deviceid, String os, String devicename, String port)
//			throws Exception, IOException, ParseException {
//		try {
//
//			extent = new ExtentReports(absoluteFilePath + "_" + devicename + ".html", true, DisplayOrder.NEWEST_FIRST);
//
//			HashMap<String, String> sysInfo = new HashMap<String, String>();
//
//			sysInfo.put("Device ID", deviceid);
//			sysInfo.put("Firmware version", os);
//
//			sysInfo.put("Device Name ", devicename);
//			sysInfo.put("Appium Port", port);
//			extent.addSystemInfo(sysInfo);
//
//			file = new File(absoluteFilePath + "_" + devicename + ".html");
//			System.out.println("Final filepath : " + absoluteFilePath + "_" + devicename + ".html");
//
//			if (file.createNewFile()) {
//				System.out.println("File is created!");
//			} else {
//				System.out.println("File is already existed!");
//			}
//
//		} // try
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		simulcastpom = new LiveSimulcast_POM();
//		PageFactory.initElements(new AppiumFieldDecorator(driver), simulcastpom);
//
//		liverewind = new LiveRewind_POM();
//		PageFactory.initElements(new AppiumFieldDecorator(driver), liverewind);
//
//		ondemand_page = new OnDemand_POM();
//		PageFactory.initElements(new AppiumFieldDecorator(driver), ondemand_page);
//
//		/*
//		 * 
//		 * Live Rewind
//		 * 
//		 */
//
//		try {
//
//			Enable_LiveRewind();
//
//			LiveRewind_Playback();
//
//			Assert_TransportControls();
//
//			Seekto_beggining();
//
//			// CheckLiveText_Seekto_beggining();
//
//			// Seekto_LivePosition();
//
//			// CheckLiveText_Seekto_LivePosition();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		/*
//		 * 
//		 * Live Simulcast
//		 */
//
//		try {
//			// disableLiveRewind();
//			//
//			// SimulcastPlayback();
//			//
//			// CheckingAssertions();
//			//
//			// // Playback_retry();
//			//
//			// // Playback_Buffer();
//			//
//			// Simulcast_Stop();
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		/*
//		 * On-demand Playback
//		 * 
//		 */
//		try {
//			// playback_initiate();
//			//
//			// assert_transportcontrols();
//			//
//			// playback_duration();
//			//
//			// // playback_retry();
//			//
//			// check_playbackbuffer();
//			//
//			// playback_multipleseek();
//			//
//			// playback_seekforward();
//			//
//			// playback_pause();
//			//
//			// playback_seekBackward();
//			//
//			// playback_resume();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	// @AfterClass
//	public void End()
//
//	{
//		extent.endTest(logger);
//		extent.flush();
//		driver.closeApp();
//		driver.quit();
//		ap.stopappium();
//	}
//
//	/*
//	 * 
//	 * Live Simulcast Playback Features
//	 * 
//	 * 
//	 */
//
//	// @Parameters({"deviceType"})
//	public void disableLiveRewind() throws Exception {
//		logger = extent.startTest("Disabling the Live Rewind ");
//
//		// liverewind.bbc_two_title=liverewind.bbc_two.getText();
//		// System.out.println("BBC TWO :="+liverewind.bbc_two_title);
//
//		// menuOption(deviceType);
//		simulcastpom.menu_phone.click();
//		Thread.sleep(2000);
//
//		logger.log(LogStatus.INFO, "Clicking the Menu"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Menu")));
//		Thread.sleep(4000);
//
//		String LiveRewind_Checked_Status = liverewind.Live_rewind_Check.getAttribute("checked");
//		logger.log(LogStatus.INFO, "LiveRewind Status" + LiveRewind_Checked_Status);
//		logger.log(LogStatus.INFO, "LiveRewind Checked Status"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, LiveRewind_Checked_Status)));
//		// System.out.println("LiveRewind Checked
//		// Status--------"+LiveRewind_Checked_Status);
//
//		// String LiveRewind_text = liverewind.live_rewind_text.getText();
//		// System.out.println(" Live Rewind text :------"+LiveRewind_text);
//
//		wait = new WebDriverWait(driver, 5500);
//		liverewind.Live_rewind_Check.click();
//
//		Thread.sleep(3000);
//
//	}
//
//	// @Test(dependsOnMethods={"disableLiveRewind"})
//	public void SimulcastPlayback() throws Exception {
//		logger = extent.startTest("Live Simulcast");
//		// child1.log(LogStatus.INFO, "Info");
//
//		// simulcastpom.bbc_two_title=simulcastpom.bbc_two.getText();
//		// System.out.println("BBC TWO :="+simulcastpom.bbc_two_title);
//
//		funct.waitForScreenToLoad(driver, simulcastpom.simulcast_bbcone_live, 15);
//		funct.Playback_Start(simulcastpom.simulcast_bbcone_live);
//
//		String Elapsed_Time_Before = simulcastpom.simulcast_time.getText();
//		logger.log(LogStatus.INFO, "Duration when Playback Started" + Elapsed_Time_Before);
//		System.out.println(" ElapsedTime Before Playback :------" + Elapsed_Time_Before);
//		logger.log(LogStatus.INFO, "Time When Playback Started"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, Elapsed_Time_Before)));
//		int etime = 0;
//		String Elapsed_Times;
//		do {
//			Elapsed_Times = simulcastpom.simulcast_time.getText();
//			// System.out.println("*****Total ElapsedTime After
//			// Playback*******"+Elapsed_Times);
//			etime++;
//		} while (etime < 260);
//
//		String Elapsed_Time_After = simulcastpom.simulcast_time.getText();
//		logger.log(LogStatus.INFO, "Duration when Playback Finished" + Elapsed_Time_After);
//
//		logger.log(LogStatus.INFO, "Time When Playback After"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, Elapsed_Time_After)));
//
//		System.out.println("Total ElapsedTime After Playback :-------" + Elapsed_Times);
//		boolean time = funct.assertNotEquals(Elapsed_Time_Before, Elapsed_Time_After);
//		System.out.println("time------------" + time);
//		System.out.println("Duration returned : " + time);
//		if (time == true) {
//			logger.log(LogStatus.PASS, "Duration Din Matched " + time);
//			System.out.println("Playback Duration  Matched");
//
//			// logger.log(LogStatus.PASS, "Duration Din Matched "+ time);
//			// logger.log(LogStatus.INFO, "Snapshot below: " +
//			// logger.addScreenCapture(funct.capture_ScreenShot(driver,ScreenshotPath
//			// ,"Duration")));
//		} else {
//			logger.log(LogStatus.PASS, "Duration Din't Matched " + time);
//			System.out.println("Playback Duration Doesn't Matched");
//
//		}
//
//		// logger.appendChild(child1);
//
//	}
//
//	// @Test(dependsOnMethods={"SimulcastPlayback"})
//	public void CheckingAssertions() throws Exception {
//		logger = extent.startTest("Checking the Live Simulcast Playback Controls");
//
//		String present[] = { "Stop button present", "Volume button present", "Live Icon present" };
//
//		logger.log(LogStatus.INFO, "TransportContols "
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Assertion")));
//
//		for (int i = 0; i < funct.Simulcast_assertions.length; i++) {
//			System.out.println(funct.Simulcast_assertions[i]);
//			assertTrue(funct.isElementPresent(driver, By.id(funct.Simulcast_assertions[i])));
//			for (int j = 0; j < present.length; j++) {
//				logger.log(LogStatus.PASS, present[j]);
//			}
//
//		}
//	}
//
//	/*
//	 * Function for turning off the Network connection Checks the error message
//	 * displayed and connects back to network and check whether playback resumes
//	 * 
//	 */
//	// @Test(dependsOnMethods={"CheckingAssertions"})
//	public void Playback_retry() throws Exception {
//
//		logger = extent.startTest("Checking the Live Simuclast Retry Scenario");
//
//		// child2 = extent.startTest("Live Simulcast Retry");
//		// child2.log(LogStatus.INFO, "Info");
//		// logger.appendChild(child2);
//
//		driver.setConnection(funct.networkconnection().AIRPLANE);
//		logger.log(LogStatus.INFO, "Airplane Mode Turned On"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Airplane Mode")));
//		System.out.println("Airplane Mode ON");
//		Thread.sleep(4000);
//
//		// wait = new WebDriverWait(driver, 12000);
//		funct.waitForScreenToLoad(driver, simulcastpom.error_message, 12000);
//		String error = driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/error_message")).getText();
//
//		System.out.println("Error Message :-------" + error);
//		logger.log(LogStatus.INFO, "Retry Error Message: "
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Error_Message")));
//		// extent.endTest(logger);
//		Thread.sleep(3000);
//
//		boolean error_message = funct.isElementPresent(driver, By.id("uk.co.bbc.avtestharnesssmp:id/error_message"));
//		if (error_message == true) {
//			driver.setConnection(funct.networkconnection().WIFI);
//			logger.log(LogStatus.INFO, "Wifi Mode Turned On");
//			System.out.println("Connected to Mobile WiFi");
//			AssertJUnit.assertTrue("Okay Button Present",
//					funct.isElementPresent(driver, By.id("uk.co.bbc.avtestharnesssmp:id/error_button")));
//			simulcastpom.tryagain_button.click();
//			Thread.sleep(3000);
//			logger.log(LogStatus.INFO, "Playback After retry"
//					+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Playback_Retry")));
//		} else {
//			System.out.println("No error message");
//			Thread.sleep(3000);
//		}
//
//	}
//
//	/*
//	 * Function to check whether any Buffering Spinner during Playback
//	 * 
//	 */
//	// @Test(dependsOnMethods={"Playback_retry"})
//	public void Playback_Buffer() throws InterruptedException {
//		logger = extent.startTest("Checking the Live Simuclast Playback Buffering");
//
//		// child3 = extent.startTest("Live Simulcast Buffering");
//		// child3.log(LogStatus.INFO, "Info");
//		// logger.appendChild(child3);
//
//		driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/playback_surface_view")).click();
//		Thread.sleep(5000);
//
//		wait = new WebDriverWait(driver, 2000);
//		// funct.waitForScreenToLoad(driver,simulcastpom.buffer_spinner,100);
//		Boolean buffer = funct.isElementPresent(driver, By.id("uk.co.bbc.avtestharnesssmp:id/buffering_spinner"));
//		System.out.println(buffer);
//		if (buffer == true) {
//
//			System.out.println("Buffering Video");
//			logger.log(LogStatus.INFO, "Buffering Shown: "
//					+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Buffering")));
//			// extent.endTest(logger);
//			Thread.sleep(3000);
//
//		} else {
//			System.out.println("No Buffering Video");
//			logger.log(LogStatus.INFO, "No Buffering: "
//					+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "No_Buffering")));
//			// extent.endTest(logger);
//			Thread.sleep(2000);
//			driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/playback_surface_view")).click();
//			Thread.sleep(6000);
//		}
//		driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/playback_surface_view")).click();
//		Thread.sleep(6000);
//
//	}
//
//	/*
//	 * 
//	 * Function Stoping the Live Simulcast Playback
//	 */
//	// @Test(dependsOnMethods={"Playback_Buffer"})
//	public void Simulcast_Stop() throws Exception {
//		funct.Playback_Stop(simulcastpom.simulcast_stop, driver);
//
//		// funct.device_rotation(ScreenOrientation.PORTRAIT);
//
//		driver.rotate(ScreenOrientation.PORTRAIT);
//
//		Thread.sleep(1000);
//
//	}
//
//	/*
//	 * 
//	 * On-demand Playback Features
//	 * 
//	 */
//
//	// @Test(dependsOnMethods={"AvTestHarness_Open"})
//	// @Parameters({"deviceType"})
//	public void playback_initiate() throws Exception {
//
//		logger = extent.startTest("Click Menu and Selecting VPID Browser");
//
//		// menuOption(deviceType);
//		simulcastpom.menu_phone.click();
//		Thread.sleep(2000);
//		simulcastpom.menu_phone.click();
//		Thread.sleep(2000);
//
//		// funct.menuOption(String device_type, driver, ondemand_page);
//
//		logger.log(LogStatus.INFO,
//				"Menu Clicked" + logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Menu")));
//
//		Thread.sleep(3000);
//
//		ondemand_page.vpid_browser.click();
//		Thread.sleep(3000);
//
//		String vpid = "b07vbg3v";
//
//		// entervpid.sendKeys(vpid);
//		// vpid_enter.click();
//		ondemand_page.vpid_enter.sendKeys(ondemand_page.vod_vpid);
//		Thread.sleep(500);
//		logger.log(LogStatus.INFO,
//				"VPID Enter" + logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "VPID Enter")));
//
//		ondemand_page.vpid_load.click();
//		logger.log(LogStatus.INFO,
//				"Loading VPID" + logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Loading")));
//		Thread.sleep(3000);
//
//		ondemand_page.vod_play.click();
//		logger.log(LogStatus.INFO,
//				"Click PLAY" + logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "PLAYING")));
//		Thread.sleep(3000);
//
//		ondemand_page.vod_play_fullscreen.click();
//		logger.log(LogStatus.INFO, "Full Screen Play"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "FullScreen")));
//		Thread.sleep(4000);
//
//	}
//
//	// @Test(dependsOnMethods={"playback_initiate"})
//	public void assert_transportcontrols() throws Exception {
//		logger = extent.startTest("Checking theOn-Demand Playback Controls");
//
//		String transport_text[] = { "Volume button present", "Subtitle button present", "Seekbar button present",
//				"Duration button present", "Exit Full Screen button present" };
//
//		logger = extent.startTest("Checking theOn-Demand Playback Controls");
//
//		logger.log(LogStatus.INFO, "TransportContols "
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Assertion")));
//
//		for (int j = 0; j < transport_text.length; j++) {
//			logger.log(LogStatus.PASS, transport_text[j]);
//		}
//		for (int i = 0; i < funct.OnDemand_assertions.length; i++) {
//			System.out.println(funct.OnDemand_assertions[i]);
//			assertTrue(funct.isElementPresent(driver, By.id(funct.OnDemand_assertions[i])));
//
//		}
//	}
//
//	// @Test(dependsOnMethods={"assert_transportcontrols"})
//	public void playback_duration() throws Exception {
//		logger = extent.startTest("Checking the On-Demand Playback Duration");
//
//		ondemand_page.vod_play_subtitle.click();
//		Thread.sleep(500);
//		logger.log(LogStatus.INFO, "Subtitle Turned ON"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Subtitle ON")));
//
//		driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
//		Thread.sleep(3000);
//
//		driver.rotate(ScreenOrientation.LANDSCAPE);
//
//		driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
//		Thread.sleep(3000);
//
//		logger.log(LogStatus.INFO, "Playing in Landscape Mode"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Landscape")));
//
//		funct.waitForScreenToLoad(driver, ondemand_page.vod_play_total_duration, 100);
//
//		String Total_Duration = ondemand_page.vod_play_total_duration.getText();
//		logger.log(LogStatus.INFO, "Total Duration" + ondemand_page.Total_Duration);
//		System.out.println("Total Duration" + Total_Duration);
//
//		int etime = 0;
//		String Elapsed_Time;
//		String Elapsed_Time_Start = ondemand_page.vod_play_elasped_duration.getText();
//		logger.log(LogStatus.INFO, "Elapsed Duration Start" + Elapsed_Time_Start);
//		System.out.println("ElapsedTime  When Playback Started:----   " + Elapsed_Time_Start);
//		do {
//
//			Elapsed_Time = ondemand_page.vod_play_elasped_duration.getText();
//			// driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/elapsed")).getText();
//			etime++;
//		} while (etime < 40);
//
//		driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
//		Thread.sleep(3000);
//
//		driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
//		Thread.sleep(3000);
//
//		Elapsed_Time = ondemand_page.vod_play_elasped_duration.getText();
//		logger.log(LogStatus.INFO, "Elapsed Duration End" + Elapsed_Time);
//
//		System.out.println("Total ElapsedTime After Playback Stoped:----   " + Elapsed_Time);
//
//		if (!Elapsed_Time_Start.equals(Elapsed_Time)) {
//			logger.log(LogStatus.PASS, "Elapsed time doesn't Match");
//			System.out.println("Not Matched");
//		} else {
//			logger.log(LogStatus.FAIL, "Elapsed time  Match");
//			System.out.println(" Matched");
//		}
//		logger.log(LogStatus.INFO, "Snapshot below: "
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Playback-Duration")));
//
//	}
//
//	// @Test(dependsOnMethods={"playback_duration"})
//	public void playback_retry() throws Exception {
//		logger = extent.startTest("Checking the On-Demand Playback Retry Scenario");
//
//		driver.setConnection(funct.networkconnection().AIRPLANE);
//
//		driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
//		Thread.sleep(6000);
//
//		driver.rotate(ScreenOrientation.PORTRAIT);
//
//		logger.log(LogStatus.INFO, "Playing in PORTRAIT Mode"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "PORTRAIT")));
//
//		logger.log(LogStatus.INFO, "Airplane Mode ON"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Airplane")));
//		System.out.println("Airplane Mode ON");
//
//		Thread.sleep(4000);
//
//		logger.log(LogStatus.INFO, "Checking Playback retry");
//
//		funct.waitForScreenToLoad(driver, ondemand_page.vod_error_message, 1200);
//		String error = ondemand_page.vod_error_message.getText();// driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/error_message")).getText();
//		System.out.println("Error Message :-------" + error);
//		logger.log(LogStatus.INFO, "Error Message "
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Error Message")));
//		boolean error_message = funct.isElementPresent(driver, By.id("uk.co.bbc.avtestharnesssmp:id/error_message"));
//		if (error_message == true) {
//			driver.setConnection(funct.networkconnection().DATA);
//			logger.log(LogStatus.INFO,
//					"Wifi Mode ON" + logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Wifi")));
//			System.out.println("Connected to Mobile WiFi");
//			AssertJUnit.assertTrue("Okay Button Present",
//					funct.isElementPresent(driver, By.id("uk.co.bbc.avtestharnesssmp:id/error_button")));
//			ondemand_page.vod_try_again_button.click();
//
//			Thread.sleep(3000);
//			logger.log(LogStatus.INFO, "Playback Started"
//					+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Playback_AfterRetry")));
//		} else {
//			System.out.println("No error message");
//			Thread.sleep(3000);
//		}
//		// waitForScreenToLoad(driver,ondemand_page.vod_buffer,180);
//
//	}
//
//	// @Test(dependsOnMethods={"playback_retry"})
//	public void check_playbackbuffer() throws InterruptedException {
//		driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
//		Thread.sleep(6000);
//
//		logger = extent.startTest("Checcking the On-Demand Playback Buffering");
//
//		driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
//		Thread.sleep(3000);
//
//		logger.log(LogStatus.INFO, "Check For Playback Buffer");
//		wait = new WebDriverWait(driver, 2000);
//		// funct.waitForScreenToLoad(driver,ondemand_page.vod_play_seekbar,100);
//		Boolean buffer = funct.isElementPresent(driver, By.id("uk.co.bbc.avtestharnesssmp:id/buffering_spinner"));
//		System.out.println(buffer);
//		if (buffer == true) {
//
//			System.out.println("Buffering Video");
//			logger.log(LogStatus.INFO, "Buffer Shown : "
//					+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Buffering Video")));
//			Thread.sleep(1000);
//
//		} else {
//			System.out.println("No Buffering Video");
//			logger.log(LogStatus.INFO, "Buffer not Shown : "
//					+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "NoBuffering Video")));
//			Thread.sleep(1000);
//		}
//
//		driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
//		Thread.sleep(6000);
//
//	}
//
//	// @Test(dependsOnMethods={"check_playbackbuffer"})
//	public void playback_multipleseek() throws InterruptedException {
//		driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
//		Thread.sleep(6000);
//
//		logger = extent.startTest("Checcking the On-Demand Playback Seeking");
//
//		logger.log(LogStatus.INFO, "Checking Seeking 30% Video ");
//		funct.waitForScreenToLoad(driver, ondemand_page.vod_play_seekbar, 100);
//		int startX = ondemand_page.vod_play_seekbar.getLocation().getX();
//		funct.seek_bar_swipe(driver, ondemand_page.vod_play_seekbar, startX, 0.3);
//		logger.log(LogStatus.INFO, "Seek 30% "
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Seeking 30% Video")));
//		Thread.sleep(2000);
//
//		ondemand_page.vod_playback_pause();
//
//		logger.log(LogStatus.INFO, "Checking Seeking 50% Video ");
//		int startX1 = ondemand_page.vod_play_seekbar.getLocation().getX();
//		funct.seek_bar_swipe(driver, ondemand_page.vod_play_seekbar, startX1, 0.5);
//		logger.log(LogStatus.INFO, "Seek 50% "
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Seeking 50% Video")));
//		Thread.sleep(2000);
//
//		logger.log(LogStatus.INFO, "Checking Seeking 80% Video ");
//		int startX2 = ondemand_page.vod_play_seekbar.getLocation().getX();
//		funct.seek_bar_swipe(driver, ondemand_page.vod_play_seekbar, startX2, 0.8);
//		logger.log(LogStatus.INFO, "Seek 80% "
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Seeking 80% Video")));
//		Thread.sleep(2000);
//	}
//
//	// @Test(dependsOnMethods={"playback_multipleseek"})
//	public void playback_seekforward() throws Exception {
//
//		logger = extent.startTest("Seeking Forward ", "Checking Seeking Forward ");
//
//		int startX = ondemand_page.vod_play_seekbar.getLocation().getX();
//		System.out.println("Startx :" + startX);
//
//		// Get end point of seekbar.
//		int endX = ondemand_page.vod_play_seekbar.getSize().getWidth();
//		System.out.println("Endx Forward  :" + endX);
//
//		// Get vertical location of seekbar.
//		int yAxis = ondemand_page.vod_play_seekbar.getLocation().getY();
//		System.out.println("Yaxis  :" + yAxis);
//
//		// Set sllebar move to position.
//		// endX * 0.6 means at 60% of seek bar width.
//		int moveToXDirectionAt = (int) (endX * 0.99);
//		System.out.println("Moving seek bar at " + moveToXDirectionAt + " In X direction.");
//
//		// Moving seekbar using TouchAction class.
//		// TouchAction act=new TouchAction(driver);
//		// act.press(startX,yAxis).moveTo(moveToXDirectionAt,yAxis).release().perform();
//		// Thread.sleep(3000);
//
//		driver.swipe(endX, yAxis, startX, yAxis, 9000);
//		logger.log(LogStatus.INFO, "Seeking Right " + logger
//				.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Seeking_from_Right-to-Left")));
//
//		Thread.sleep(3000);
//
//	}
//
//	/*
//	 * 
//	 * Pausing the playback of on-demand programme
//	 */
//
//	// @Test(dependsOnMethods={"playback_seekforward"})
//	public void playback_pause() throws Exception {
//		logger = extent.startTest("Playback Pause ", "Checking Playback Pause ");
//		ondemand_page.vod_play_pause.click();
//		Thread.sleep(3000);
//	}
//
//	// @Test(dependsOnMethods={"playback_pause"})
//	public void playback_seekBackward() throws Exception {
//
//		logger = extent.startTest("Seeking Backward ", "Checking Seeking backward ");
//
//		int startX = ondemand_page.vod_play_seekbar.getLocation().getX();
//		System.out.println("Startx :" + startX);
//
//		// Get end point of seekbar.
//		int endX = ondemand_page.vod_play_seekbar.getSize().getWidth();
//		System.out.println("Endx Forward  :" + endX);
//
//		// Get vertical location of seekbar.
//		int yAxis = ondemand_page.vod_play_seekbar.getLocation().getY();
//		System.out.println("Yaxis  :" + yAxis);
//
//		// Set sllebar move to position.
//		// endX * 0.6 means at 60% of seek bar width.
//		int moveToXDirectionAt = (int) (endX * 0.99);
//		System.out.println("Moving seek bar at " + moveToXDirectionAt + " In X direction.");
//
//		// Moving seekbar using TouchAction class.
//		// TouchAction act=new TouchAction(driver);
//		// act.press(startX,yAxis).moveTo(moveToXDirectionAt,yAxis).release().perform();
//		// Thread.sleep(3000);
//
//		driver.swipe(startX, yAxis, endX + 10, yAxis, 9000);
//		logger.log(LogStatus.INFO, "Seeking Left " + logger
//				.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Seeking_from_Left-to-Right")));
//		wait = new WebDriverWait(driver, 3500);
//
//	}
//
//	// @Test(dependsOnMethods={"playback_seekBackward"})
//	public void playback_resume() throws Exception {
//		logger = extent.startTest("Playback Pause ", "Checking Playback Resume ");
//
//		ondemand_page.vod_play_pause.click();
//		Thread.sleep(3000);
//
//		ondemand_page.vod_exit_FullScreen();
//
//		driver.pressKeyCode(AndroidKeyCode.BACK);
//		Thread.sleep(1000);
//
//		driver.rotate(ScreenOrientation.PORTRAIT);
//		Thread.sleep(1000);
//
//	}
//
//	/*
//	 * Live Rewind Functions
//	 * 
//	 * 
//	 */
//
//	// @Test(dependsOnMethods={"AVTestApp_Intialise"})
//	// @Parameters({"deviceType"})
//	public void Enable_LiveRewind() throws Exception {
//		logger = extent.startTest("Checking Live Rewind enabled by Default");
//
//		// liverewind.bbc_two_title=liverewind.bbc_two.getText();
//		// System.out.println("BBC TWO :="+liverewind.bbc_two_title);
//
//		// menuOption(deviceType);
//		simulcastpom.menu_phone.click();
//		Thread.sleep(2000);
//		logger.log(LogStatus.INFO, "Clicking the Menu"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Menu")));
//		Thread.sleep(4000);
//
//		String LiveRewind_Checked_Status = liverewind.Live_rewind_Check.getAttribute("checked");
//		// logger.log(LogStatus.INFO, "LiveRewind Status" +
//		// LiveRewind_Checked_Status);
//		logger.log(LogStatus.INFO, "LiveRewind Checked Status"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, LiveRewind_Checked_Status)));
//		System.out.println("LiveRewind Checked Status--------" + LiveRewind_Checked_Status);
//
//		driver.pressKeyCode(AndroidKeyCode.BACK);
//	}
//
//	// @Test(dependsOnMethods={"Enable_LiveRewind"})
//	public void LiveRewind_Playback() throws Exception {
//		logger = extent.startTest("Live rewind Playback", "Checking the Live Simuclast Rewind Playback");
//
//		liverewind.live_rewind_playback.click();
//		Thread.sleep(3000);
//		String Time_Played = liverewind.live_simulcat_rewind_time.getText();
//		System.out.println("*****Time When Playback Started*******" + Time_Played);
//
//		logger.log(LogStatus.INFO, "Playback time " + Time_Played);
//
//		logger.log(LogStatus.INFO, "Time After Some Playback:--" + Time_Played
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Time_Played_Before")));
//
//		logger.log(LogStatus.INFO, "Live Text  Present: "
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "LiveText_Present")));
//		System.out.println("Live Text  Present");
//
//		int etime = 0;
//		String timecount;
//		do {
//			timecount = liverewind.live_simulcat_rewind_time.getText();
//			// System.out.println("*****Total ElapsedTime After
//			// Playback*******"+Elapsed_Times);
//			etime++;
//		} while (etime < 200);
//
//		String Time_Played_After = liverewind.live_simulcat_rewind_time.getText();
//		// logger.log(LogStatus.INFO, "Playback time After some period " +
//		// Time_Played_After);
//		System.out.println("Time After Some Playback :-------" + Time_Played_After);
//		logger.log(LogStatus.INFO, "Time After Some Playback:--" + Time_Played_After
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Time_Played_After")));
//		boolean time_match = assertNotEquals(Time_Played, Time_Played_After);
//		if (!Time_Played.equals(Time_Played_After)) {
//			logger.log(LogStatus.PASS, "Time Duration Din't matched: ");
//			System.out.println("Time Before and After Din't Matcahed :-------" + time_match);
//		} else {
//			logger.log(LogStatus.FAIL, "Time Duration  matched: ");
//			System.out.println("Time Before and After  Matcahed :-------" + time_match);
//		}
//
//	}
//
//	// @Test(dependsOnMethods={"LiveRewind_Playback"})
//	public void Assert_TransportControls() throws Exception {
//		String assertions_text[] = { "Pause Button present", "Seek Bar present", "Live Icon present",
//				"Volume button present", "Pause Button present" };
//
//		logger = extent.startTest("Checking the Playback Transport Controls");
//
//		logger.log(LogStatus.INFO, "TransportContols "
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Assertion")));
//
//		// Assert.assertFalse(funct.isElementPresent(driver,
//		// By.id("uk.co.bbc.avtestharnesssmp:id/stop_button")));
//		logger.log(LogStatus.PASS, "Stop button not present");
//
//		for (int j = 0; j < assertions_text.length; j++) {
//			logger.log(LogStatus.PASS, assertions_text[j]);
//		}
//		for (int i = 0; i < funct.Rewind_assertions.length; i++) {
//			System.out.println(funct.Rewind_assertions[i]);
//			assertTrue(funct.isElementPresent(driver, By.id(funct.Rewind_assertions[i])));
//
//		}
//
//		Assert.assertFalse(funct.isElementPresent(driver, By.id("uk.co.bbc.avtestharnesssmp:id/stop_button")));
//	}
//
//	// @Test(dependsOnMethods={"Assert_TransportControls"})
//	public void Seekto_beggining() throws Exception {
//
//		logger = extent.startTest("Seekto_beggining", "Checking the Live Simuclast Rewind Seeking Back");
//
//		int startX1 = liverewind.live_rewind_progressbar.getLocation().getX();
//
//		SeektoLivePosition("Left");
//
//		SeektoLivePosition("Right");
//
//		funct.seek_bar_swipe(driver, liverewind.live_rewind_progressbar, startX1, 0.5);
//
//		logger.log(LogStatus.INFO, "Seeking 50%"
//				+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Seeking Back")));
//
//		LiveText_Checking();
//
//		SeektoLivePosition("Right");
//
//		int currentX = liverewind.live_rewind_progressbar.getLocation().getX();
//		System.out.println("currentX position  :" + currentX);
//
//		Thread.sleep(3000);
//
//	}
//
//	// @Test(dependsOnMethods={"Seekto_beggining"})
//	public void CheckLiveText_Seekto_beggining() throws Exception
//
//	{
//
//		// logger = extent.startTest("Live Text ", "Checking the Live Text
//		// Present After Seek to beggining");
//		wait = new WebDriverWait(driver, 2500);
//		LiveText_Checking();
//
//		// TouchAction touch = new TouchAction(driver);
//	}
//
//	// @Test(dependsOnMethods={"CheckLiveText_Seekto_beggining"})
//	public void Seekto_LivePosition() throws InterruptedException, Exception {
//
//		logger = extent.startTest("Seekto_LivePosition", "Checking the Live Text Present after Seeking Forward ");
//
//		/*
//		 * System.out.
//		 * println("Seeking Forward Checking the Live Text Present after Seeking Forward "
//		 * );
//		 * 
//		 * int startX = liverewind.live_rewind_progressbar.getLocation().getX();
//		 * System.out.println("Startx :" + startX);
//		 * 
//		 * // Get end point of seekbar. int endX =
//		 * liverewind.live_rewind_progressbar.getSize().getWidth();
//		 * System.out.println("Endx Forward  :" + endX);
//		 * 
//		 * // Get vertical location of seekbar. int yAxis =
//		 * liverewind.live_rewind_progressbar.getLocation().getY();
//		 * System.out.println("Yaxis  :" + yAxis);
//		 * 
//		 * // Set sllebar move to position. // endX * 0.6 means at 60% of seek
//		 * bar width. int moveToXDirectionAt = (int) (endX * 0.99);
//		 * System.out.println("Moving seek bar at " + moveToXDirectionAt +
//		 * " In X direction.");
//		 * 
//		 * // Moving seekbar using TouchAction class. // TouchAction act=new
//		 * TouchAction(driver); //
//		 * act.press(startX,yAxis).moveTo(moveToXDirectionAt,yAxis).release().
//		 * perform(); // Thread.sleep(3000);
//		 * 
//		 * // driver.swipe(endX, yAxis, startX, yAxis, 9000);
//		 * 
//		 * // Thread.sleep(4000);
//		 * 
//		 * // driver.swipe(startX, yAxis, endX + 60, yAxis, 9000);
//		 * 
//		 * /* driver.swipe(startX, yAxis, endX + 120, yAxis, 9000);
//		 * logger.log(LogStatus.INFO, "Seeking Right to End " + logger
//		 * .addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath,
//		 * "Seeking_from_Left-to-Right"))); wait = new WebDriverWait(driver,
//		 * 3500);
//		 * 
//		 * 
//		 * LiveText_Checking();
//		 * 
//		 * logger.log(LogStatus.INFO, "Seeking Right " + logger
//		 * .addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath,
//		 * "Seeking_from_Right-to-Left")));
//		 */
//
//		SeektoLivePosition("Right");
//
//		Thread.sleep(2000);
//
//	}
//
//	public void SeektoLivePosition(String direction) throws InterruptedException, Exception {
//
//		// logger=extent.startTest("Seeking Forward ","Checking the Live Text
//		// Present after Seeking Forward ");
//
//		// System.out.println("Seeking Forward Checking the Live Text Present
//		// after Seeking Forward ");
//		int position = 300;
//		int startX = liverewind.live_rewind_progressbar.getLocation().getX();
//		int xposition = startX + position;
//		System.out.println("Startx After Before Adding  the Value  :" + startX);
//		System.out.println("Startx After After Adding  the Value  :" + xposition);
//
//		// Get end point of seekbar.
//		int endX = liverewind.live_rewind_progressbar.getSize().getWidth();
//		System.out.println("Endx Forward  :" + endX);
//
//		// Get vertical location of seekbar.
//		int yAxis = liverewind.live_rewind_progressbar.getLocation().getY();
//		System.out.println("Yaxis  :" + yAxis);
//
//		// Set sllebar move to position.
//		// endX * 0.6 means at 60% of seek bar width.
//		int moveToXDirectionAt = (int) (endX * 0.99);
//		System.out.println("Moving seek bar at " + moveToXDirectionAt + " In X direction.");
//
//		// Moving seekbar using TouchAction class.
//		// TouchAction act=new TouchAction(driver);
//		// act.press(startX,yAxis).moveTo(moveToXDirectionAt,yAxis).release().perform();
//		// Thread.sleep(3000);
//
//		// driver.swipe(endX, yAxis, startX, yAxis, 9000);
//		// logger.log(LogStatus.INFO, "Seeking Right " +
//		// logger.addScreenCapture(funct.capture_ScreenShot(driver,ScreenshotPath
//		// ,"Seeking_from_Right-to-Left")));
//
//		// driver.swipe(startX, yAxis, endX + 120, yAxis, 9000);
//
//		if (direction.equals("Left")) {
//			Thread.sleep(3000);
//			driver.swipe(endX, yAxis, startX, yAxis, 9000);
//			Thread.sleep(3000);
//			logger.log(LogStatus.INFO, "Seeking Left to Beginning " + logger
//					.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Seeking_from_Right-to-Left")));
//			LiveText_Checking();
//		} else if (direction.equals("Right")) {
//			Thread.sleep(3000);
//			int position1 = 30;
//			int yposition = endX + position1;
//
//			int endX1 = liverewind.live_rewind_progressbar.getSize().getWidth();
//
//			System.out.println("Endx After Before Adding  the Value  :" + endX);
//			System.out.println("Endx1 After Before Adding  the Value  :" + endX1);
//			System.out.println("Endx After After Adding  the Value  :" + yposition);
//			driver.swipe(startX, yAxis, yposition, yAxis, 9000);
//
//			Thread.sleep(3000);
//
//			LiveText_Checking();
//
//			logger.log(LogStatus.INFO, "Seeking Right to End " + logger
//					.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "Seeking_from_Left-to-Right")));
//			wait = new WebDriverWait(driver, 3500);
//
//		}
//		// driver.pressKeyCode(AndroidKeyCode.BACK);
//		// Thread.sleep(3000);
//		// driver.rotate(ScreenOrientation.PORTRAIT);*/
//
//	}
//
//	// @Test(dependsOnMethods={"Seekto_LivePosition"})
//	public void CheckLiveText_Seekto_LivePosition() throws Exception
//
//	{
//
//		logger = extent.startTest("Live Text ", "Checking the Live Text Present After Seek to Live Position");
//		wait = new WebDriverWait(driver, 2500);
//		LiveText_Checking();
//
//		driver.pressKeyCode(AndroidKeyCode.BACK);
//		Thread.sleep(3000);
//
//		driver.rotate(ScreenOrientation.PORTRAIT);
//		Thread.sleep(1000);
//
//	}
//
//	/*
//	 * 
//	 * Common Functions
//	 */
//
//	private boolean assertNotEquals(String elapsed_Time_Before, String elapsed_Time_After) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public void LiveText_Checking() {
//		// logger = extent.startTest("Live Text ", "Checking the Live Text
//		// Present ");
//		// wait = new WebDriverWait(driver, 3500);
//
//		boolean livetext = funct.isElementPresent(driver, By.id("uk.co.bbc.avtestharnesssmp:id/live_icon"));
//		if (livetext == true) {
//			logger.log(LogStatus.INFO, "Live Text  Present: "
//					+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "LiveText_Present")));
//			System.out.println("Live Text  Present");
//		}
//
//		else {
//			logger.log(LogStatus.INFO, "Live Text Not Present: "
//					+ logger.addScreenCapture(funct.capture_ScreenShot(driver, ScreenshotPath, "LiveText_NotPresent")));
//			System.out.println("Live Text Not Present");
//
//		}
//
//	}
//
//	public void menuOption(String device_type) throws Exception {
//		if (device_type.equals("Mobile")) {
//			simulcastpom.menu_phone.click();
//			// driver.findElement(By.xpath("xpath=//android.widget.ImageView[@content-desc='More
//			// options']")).click();
//			Thread.sleep(1000);
//		} else if (device_type.equals("Tablet")) {
//			simulcastpom.menu_tablet.click();
//			Thread.sleep(1000);
//		}
//	}
//
//	/*
//	 * 
//	 * Function to display all the Connected Android Devices
//	 * 
//	 */
//
//	public void populateDevices_IDs() throws Exception {
//		process = new ProcessBuilder(commandDevices).start();
//
//		output = cmd.runCommand(sdkPath + "./adb devices");
//		String[] lines = output.split("\n");
//
//		for (int i = 1; i < lines.length; i++) {
//			lines[i] = lines[i].replaceAll("\\s+", "");
//
//			if (lines[i].contains("device")) {
//				lines[i] = lines[i].replaceAll("device", "");
//				String deviceID1 = lines[i];
//
//				// System.out.println("Following device is connected");
//				// System.out.println("deviceID" + deviceID1);
//				deviceID.add(deviceID1);
//			}
//
//		}
//
//	}
//
//	/*
//	 * 
//	 * Function to display all the Connected Android Devices , firmware versions
//	 * 
//	 */
//
//	public void populateDevices_OS() throws Exception {
//		process = new ProcessBuilder(commandDevices).start();
//		output = cmd.runCommand(sdkPath + "./adb devices");
//		String[] lines = output.split("\n");
//
//		for (int i = 1; i < lines.length; i++) {
//			lines[i] = lines[i].replaceAll("\\s+", "");
//
//			if (lines[i].contains("device")) {
//				lines[i] = lines[i].replaceAll("device", "");
//				String deviceID1 = lines[i];
//				// String model = cmd.runCommand(sdkPath + "./adb -s " +
//				// deviceID1 + " shell getprop ro.product.model")
//				// .replaceAll("\\s+", "");
//				// String brand = cmd.runCommand(sdkPath + "./adb -s " +
//				// deviceID1 + " shell getprop ro.product.brand")
//				// .replaceAll("\\s+", "");
//				osVersion = cmd
//						.runCommand(sdkPath + "./adb -s " + deviceID1 + " shell getprop ro.build.version.release")
//						.replaceAll("\\s+", "");
//				// String devicenames = brand + " " + model;
//
//				// System.out.println("Following device OS");
//				// // System.out.println(deviceID+" "+osVersion+"\n");
//				// System.out.println("osVersion***** :" + osVersion);
//				deviceOS.add(osVersion);
//			}
//
//		}
//
//	}
//
//	/*
//	 * 
//	 * Function to display all the Connected Android Devices Names
//	 * 
//	 */
//	public void populateDevices_Names() throws Exception {
//		process = new ProcessBuilder(commandDevices).start();
//		output = cmd.runCommand(sdkPath + "./adb devices");
//		String[] lines = output.split("\n");
//
//		for (int i = 1; i < lines.length; i++) {
//			lines[i] = lines[i].replaceAll("\\s+", "");
//
//			if (lines[i].contains("device")) {
//				lines[i] = lines[i].replaceAll("device", "");
//				String deviceID1 = lines[i];
//				String model = cmd.runCommand(sdkPath + "./adb -s " + deviceID1 + " shell getprop ro.product.model")
//						.replaceAll("\\s+", "");
//				String brand = cmd.runCommand(sdkPath + "./adb -s " + deviceID1 + " shell getprop ro.product.brand")
//						.replaceAll("\\s+", "");
//				osVersion = cmd
//						.runCommand(sdkPath + "./adb -s " + deviceID1 + " shell getprop ro.build.version.release")
//						.replaceAll("\\s+", "");
//				String devicenames = brand + " " + model;
//
//				// System.out.println("Following device OS");
//				// // System.out.println(deviceID+" "+osVersion+"\n");
//				// System.out.println("osVersion***** :" + osVersion);
//				deviceName.add(devicenames);
//			}
//
//		}
//
//	}
//
//}
