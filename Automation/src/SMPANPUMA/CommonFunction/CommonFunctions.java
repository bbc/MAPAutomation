package SMPANPUMA.CommonFunction;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileAlreadyExistsException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.google.common.base.Predicate;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import SMPANPUMA.SMPPageObjects.SMPANPageObjects_Common;
import main.java.test.smpUtilityFunctions.CommandPrompt;
import main.java.test.smpUtilityFunctions.PortFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.Connection;
import io.appium.java_client.ios.IOSDriver;

public class CommonFunctions {

	WebDriverWait wait;
	public ExtentReports extent;
	public ExtentTest logger;
	public  final long TIME_OUT_IN_SECONDS = 30L;
	public  Environment environment = Environment.LOCAL;

	String sdkPath = System.getenv("ANDROID_HOME") +"/platform-tools/";;
	String adbPath = sdkPath + File.separator + "adb";
	String[] commandDevices = new String[] { adbPath, "devices" };
	 String clearlogs = sdkPath + File.separator + "adb logcat " + "-c";
	CommandPrompt cmd = new CommandPrompt();

	 String AndroidsdkPath = System.getenv("ANDROID_HOME") +"/platform-tools/";
	 String mediaselector = sdkPath + File.separator + "adb shell logcat"+"|"+"grep " + "http://open.*";
	 String rdot = sdkPath + File.separator + "adb shell logcat"+"|"+"grep " + "https://r.bbc.*";
	
	public List<String> deviceIDs = new ArrayList<String>();
	public List<String> deviceOSs = new ArrayList<String>();
	public List<String> deviceNames = new ArrayList<String>();

	String osVersion;
	Process process;
	String output;

//	AndroidDriver<WebElement> driver;
//	IOSDriver<WebElement> iosdriver;
	File file;

	PortFactory portFactory = new PortFactory();

	LiveRewindFunctions liverewindFunctions = new LiveRewindFunctions();
	
	public AppiumDriver<WebElement> driver=null;
	public char[][] DRM;
	private static AppiumDriver<?> drivers = null;

	/*
	 * 
	 * Pausing the playback
	 * 
	 */
	public void CommonFunction() {

	}




	public void menuOption(String device_type, AndroidDriver<WebElement> driver, By locatorKey) throws Exception {
		if (device_type.equals("Mobile")) {
			driver.findElement(locatorKey);

			Thread.sleep(1000);
		} else if (device_type.equals("Tablet")) {
			driver.findElement(locatorKey);
			Thread.sleep(1000);
		}
	}

	public void Playback_assertion(AndroidDriver<WebElement> driver, String element)

	{

		// Assert.assertFalse(isElementPresent(driver,
		// By.id("uk.co.bbc.avtestharnesssmp:id/stop_button")));
		assertTrue(isElementPresent(driver, By.id(element)));
	}

	public boolean assertNotEquals(String str, String str1) {
		// TODO Auto-generated method stub
		return false;
	}

	public void Playback_Stop(WebElement element, AndroidDriver<WebElement> driver) throws InterruptedException {
		wait = new WebDriverWait(driver, 2000);
		element.click();
		Thread.sleep(5000);
	}

	public void tapbutton(String testname, WebElement element,  AppiumDriver<WebElement> driver, String path)
			throws Exception {
		try
		{
		
		logger = extent.startTest(testname);
		//waitForElementToBeDisplayed(element);
		element.click();
		
		logger.log(LogStatus.INFO,
				testname + logger.addScreenCapture(capture_ScreenShot(driver, path, testname)));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void turnSubtitleON(String testname, String contentType, WebElement element,AppiumDriver<WebElement> driver ,String path)
	{
		
		if (contentType == "Video") 
		try
			{
		logger = extent.startTest(testname);
		element.click();
		logger.log(LogStatus.INFO,
				testname + logger.addScreenCapture(capture_ScreenShot(driver, path, testname)));
			}catch(Exception e)
				{
					e.printStackTrace();
				}
	}
	
	public void CheckMediaSelector()
	{
		try
		{
		logger = extent.startTest("Checking MediaSelector URL");
		String MediaS = getMediaSelectorURL(mediaselector,"http://");
		System.out.println("MdiaSelector:-"+MediaS);
		logger.log(LogStatus.PASS, MediaS);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public void waitForScreenToLoad(AppiumDriver<WebElement> driver, WebElement element, int seconds) {

		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForScreen_ToLoad(AppiumDriver<WebElement> driver, WebElement element, int seconds) {

		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitforElement(AppiumDriver<WebElement> driver, WebElement element, int seconds) {

	//	driver.get("http://somedomain/url_that_delays_loading");
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("myDynamicElement")));
	}

	public Connection networkconnection() {
		return Connection.AIRPLANE;
	}

	public void getScreen_orientation(AppiumDriver<WebElement> driver) {
		System.out.println(" Current screen orientation Is : " + driver.getOrientation());
	}

	public boolean isElementPresent(AppiumDriver<WebElement> driver, By locatorKey) {
		try {
			driver.findElement(locatorKey);
			return true;
		} catch (NoSuchElementException e) {
			// e.printStackTrace();
			return false;
		}
		// return false;
	}

	public String capture_ScreenShot(AppiumDriver<WebElement> driver, String ScreenshotPath, String Screenshotname) {

		try {
			// String
			// report_path=absoluteFilePath;//"//Users//ramakh01//Downloads//RadioApp_Automation//RadioApp//Appium_Test//test-output//AVTestHarness.html";
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest = ScreenshotPath + File.separator + Screenshotname + ".png";
			System.out.println("Screenshot path name:------" + dest);
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
			System.out.println("ScreenShot Taken");
			return dest;
		} catch (Exception e) {
			System.out.println("Exception While Taking screenshot" + e.getMessage());
			return e.getMessage();
		}

	}

	public void PlaybackContinue(String text,WebElement element, AppiumDriver<WebElement> adriver, String path)
			throws Exception {

		logger = extent.startTest(text);


		String startduration = element.getText();

		System.out.println("*****Time When Playback Started*******" + startduration);

		logger.log(LogStatus.INFO, "Playback time " + startduration);

		logger.log(LogStatus.INFO, "Time After Some Playback:--" + startduration
				+ logger.addScreenCapture(capture_ScreenShot(adriver, path, "Time_Played_Before")));

		logger.log(LogStatus.INFO, "Live Text  Present: "
				+ logger.addScreenCapture(capture_ScreenShot(adriver, path, "LiveText_Present")));
		System.out.println("Live Text  Present");

		int etime = 0;
		String durationCount;
		do {
			// timecount = liverewind.live_simulcat_rewind_time.getText();
			durationCount = element.getText();
			
			etime++;
		} while (etime < 240);
		//logger.log(LogStatus.PASS, getRDOTURL(rdot));
		// String Time_Played_After =
		// liverewind.live_simulcat_rewind_time.getText();
		String durationElapsed = element.getText();

		// logger.log(LogStatus.INFO, "Playback time After some period " +
		// Time_Played_After);
		System.out.println("Time After Some Playback :-------" + durationElapsed);
		logger.log(LogStatus.INFO, "Time After Some Playback:--" + durationElapsed
				+ logger.addScreenCapture(capture_ScreenShot(adriver, path, "Time_Played_After")));
		boolean time_match = assertNotEquals(startduration, durationElapsed);
		if (!startduration.equals(durationElapsed)) {
			logger.log(LogStatus.PASS, "Time Duration Din't matched: ");
			System.out.println("Time Before and After Din't Matcahed :-------" + time_match);
		} else {
			logger.log(LogStatus.FAIL, "Time Duration  matched: ");
			System.out.println("Time Before and After  Matcahed :-------" + time_match);
		}


	}
	
	 protected static AppiumDriver<?> getDriver() {
	        return drivers;
	    }
	
	
//	public void waitForElementToBeDisplayed(WebElement element) {
//        new WebDriverWait(getDriver(), TIME_OUT_IN_SECONDS).until(new Predicate<WebDriver>() {
//            @Override
//            public boolean apply(WebDriver webDriver) {
//                return element != null && element.isDisplayed();
//            }
//        });
//    }
	

	public void playback_orientation(AppiumDriver<WebElement> driver , ScreenOrientation orientation,
			String message,
			String path) throws Exception {
		logger = extent.startTest(message);
		driver.rotate(orientation);
		Thread.sleep(5000);
		logger.log(LogStatus.INFO,
				message + logger.addScreenCapture(capture_ScreenShot(driver, path, message)));
	}

	public void OnDemandplayback_duration(WebElement element, WebElement element1, WebElement element2, String path,
			AppiumDriver<WebElement> driver) throws Exception {
		
		
		logger = extent.startTest("Checking the On-Demand Video Playing");

		//driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
		element.click();
		Thread.sleep(3000);

		// playingback_orientation(adriver, ScreenOrientation.LANDSCAPE,
		// "Playing in Landscape", path);
		// adriver.rotate(ScreenOrientation.PORTRAIT);

		//driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
		element.click();
		Thread.sleep(3000);

		// funct.waitForScreenToLoad(driver,
		// ondemand_page.vod_play_total_duration, 100);
     
		String Total_Duration = element1.getText(); // ondemand_page.vod_play_total_duration.getText();
		logger.log(LogStatus.INFO, "Total Duration" + Total_Duration);
		System.out.println("Total Duration" + Total_Duration);

		int etime = 0;
		String Elapsed_Time;
		String Elapsed_Time_Start = element2.getText(); // ondemand_page.vod_play_elasped_duration.getText();
		logger.log(LogStatus.INFO, "Elapsed Duration Start" + Elapsed_Time_Start);
		System.out.println("ElapsedTime  When Playback Started:----   " + Elapsed_Time_Start);
		do {

			Elapsed_Time = element2.getText(); // ondemand_page.vod_play_elasped_duration.getText();
			// driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/elapsed")).getText();
			etime++;
		} while (etime < 40);

		// driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
		// Thread.sleep(3000);
		element.click();
		Thread.sleep(3000);

		// driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
		// Thread.sleep(3000);
		element.click();
		Thread.sleep(3000);

		Elapsed_Time = element2.getText(); // ondemand_page.vod_play_elasped_duration.getText();
		logger.log(LogStatus.INFO, "Elapsed Duration End" + Elapsed_Time);

		System.out.println("Total ElapsedTime After Playback Stoped:----   " + Elapsed_Time);

		if (!Elapsed_Time_Start.equals(Elapsed_Time)) {
			logger.log(LogStatus.PASS, "Elapsed time doesn't Match");
			System.out.println("Not Matched");
		} else {
			logger.log(LogStatus.FAIL, "Elapsed time  Match");
			System.out.println(" Matched");
		}
		logger.log(LogStatus.INFO,
				"Snapshot below: " + logger.addScreenCapture(capture_ScreenShot(driver, path, "Playback-Duration")));

	}

	public void Checkplayback_duration(WebElement element1, WebElement element2, String path,
			AndroidDriver<WebElement> adriver) throws Exception {

		logger = extent.startTest("Checking the Playback");

		String Total_Duration = element1.getText(); 
		logger.log(LogStatus.INFO, "Total Duration" + Total_Duration);
		System.out.println("Total Duration" + Total_Duration);

		int etime = 0;
		String Elapsed_Time;
		String Elapsed_Time_Start = element2.getText(); 
		logger.log(LogStatus.INFO, "Elapsed Duration Start" + Elapsed_Time_Start);
		System.out.println("ElapsedTime  When Playback Started:----   " + Elapsed_Time_Start);
		do {

			Elapsed_Time = element2.getText(); 
			// driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/elapsed")).getText();
			etime++;
		} while (etime < 120);

		Elapsed_Time = element2.getText(); // ondemand_page.vod_play_elasped_duration.getText();
		logger.log(LogStatus.INFO, "Elapsed Duration End" + Elapsed_Time);

		System.out.println("Total ElapsedTime After Playback Stoped:----   " + Elapsed_Time);

		if (!Elapsed_Time_Start.equals(Elapsed_Time)) {
			logger.log(LogStatus.PASS, "Elapsed time doesn't Match");
			System.out.println("Not Matched");
		} else {
			logger.log(LogStatus.FAIL, "Elapsed time  Match");
			System.out.println(" Matched");
		}
		logger.log(LogStatus.INFO,
				"Snapshot below: " + logger.addScreenCapture(capture_ScreenShot(adriver, path, "Playback-Duration")));

	}


	public void seek_bar_swipe(AppiumDriver<WebElement> driver, WebElement seekbar, int start, double d) {
		int startX = seekbar.getLocation().getX();
		System.out.println("Startx :" + startX);

		// Get end point of seekbar.
		int endX = seekbar.getSize().getWidth();
		System.out.println("Endx  :" + endX);

		// Get vertical location of seekbar.
		int yAxis = seekbar.getLocation().getY();
		System.out.println("Yaxis  :" + yAxis);

		// Set sllebar move to position.
		// endX * 0.6 means at 60% of seek bar width.
		int moveToXDirectionAt = (int) (endX * d);
		System.out.println("Moving seek bar at " + moveToXDirectionAt + " In X direction.");

		// Moving seekbar using TouchAction class.
		// TouchAction act=new TouchAction(driver);
		// act.press(startX,yAxis).moveTo(moveToXDirectionAt,yAxis).release().perform();
		// Thread.sleep(3000);
		driver.swipe(startX, yAxis, moveToXDirectionAt, yAxis, 9000);
	}

	public void GenerateReport() throws Exception{
		try
		{
		extent.endTest(logger);
		extent.flush();
		}catch(NullPointerException e)
		{
			e.printStackTrace();
		}
	}

	public void CreateReport(String absoluteFilePath, String deviceID, String deviceOS, String Port,
			String deviceName) throws Exception, FileAlreadyExistsException, InterruptedException {
		try {

			extent = new ExtentReports(absoluteFilePath + "_" + deviceName + ".html", true, DisplayOrder.NEWEST_FIRST);

			// extent = new ExtentReports(absoluteFilePath, true,
			// DisplayOrder.NEWEST_FIRST);

			HashMap<String, String> sysInfo = new HashMap<String, String>();

			sysInfo.put("Device ID", deviceID);
			sysInfo.put("Firmware version", deviceOS);
			sysInfo.put("Appium Port", Port);
			sysInfo.put("Device Name ", deviceName);

			extent.addSystemInfo(sysInfo);

			// System.out.println("Final filepath : " +
			// absoluteFilePath+"_"+filename+".html" );
			System.out.println("Final filepath : " + absoluteFilePath + "_" + deviceName + ".html");
			file = new File(absoluteFilePath + "_" + deviceName + ".html");
			// file = new File(absoluteFilePath);

			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File is already existed!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	

	public void Assert_TransportControls(AppiumDriver<WebElement> driver, String path, String[] assertionsPresent,
			String[] assertions_text, String element) throws Exception {
		// String assertions_text[] = { "Pause Button present", "Seek Bar
		// present", "Live Icon present",
		// "Volume button present", "Pause Button present" };

		logger = extent.startTest("Checking the Playback Transport Controls");

		logger.log(LogStatus.INFO, "TransportContols "
				+ logger.addScreenCapture(capture_ScreenShot(driver, path, "Assertion")));

		// Assert.assertFalse(funct.isElementPresent(driver,
		// By.id("uk.co.bbc.avtestharnesssmp:id/stop_button")));
	//	logger.log(LogStatus.PASS, "Stop button not present");

		for (int j = 0; j < assertions_text.length; j++) {
			logger.log(LogStatus.PASS, assertions_text[j]);
		}
		// for (int i = 0; i < funct.Rewind_assertions.length; i++)
		for (int i = 0; i < assertionsPresent.length; i++) {
			System.out.println(assertionsPresent[i]);
			assertTrue(isElementPresent(driver, By.id(assertionsPresent[i])));
			

		}

		Assert.assertFalse(isElementPresent(driver, By.id(element)));
	}
	
	
	
	
	public void Assert_TransportControlsNotPresent(AppiumDriver<WebElement> driver, String path, String[] assertionsNotPresent,
			String[] assertions_text) throws Exception {
		// String assertions_text[] = { "Pause Button present", "Seek Bar
		// present", "Live Icon present",
		// "Volume button present", "Pause Button present" };

		logger = extent.startTest("Checking the Transport Controls Not Present");

		logger.log(LogStatus.INFO, "TransportContols "
				+ logger.addScreenCapture(capture_ScreenShot(driver, path, "Assertion")));

		// Assert.assertFalse(funct.isElementPresent(driver,
		// By.id("uk.co.bbc.avtestharnesssmp:id/stop_button")));
	

		for (int j = 0; j < assertions_text.length; j++) {
			logger.log(LogStatus.PASS, assertions_text[j]);
		}
		// for (int i = 0; i < funct.Rewind_assertions.length; i++)
		for (int i = 0; i < assertionsNotPresent.length; i++) {
			System.out.println(assertionsNotPresent[i]);
			//assertTrue(isElementPresent(driver, By.id(assertionsNotPresent[i])));
			Assert.assertFalse(isElementPresent(driver, By.id(assertionsNotPresent[i])), "Element Not Found");
			//assertTrue(is(driver, By.id(assertionsNotPresent[i])));
			

		}

		
	}
	
	
	

	

	public void seekingRandomly(WebElement element, AppiumDriver<WebElement> driver, String path, double d)
			throws Exception {
		int seekposition= (int) d;

		logger = extent.startTest("seekingRandomly",
				"Seeking randomly to check whether playback resumes from new point");

		int startX = element.getLocation().getX();
				//liverewind.live_rewind_progressbar.getLocation().getX();

		seek_bar_swipe(driver, element, startX, d);// 0.5);
		Thread.sleep(3000);

		logger.log(LogStatus.INFO,
				"Seeking" + d + "%"
						+ logger.addScreenCapture(capture_ScreenShot(driver, path, "Multipel Seeking")));

		//LiveText_Checking(adriver, path);

		Thread.sleep(5000);

	}


	public void Seekingbackwardforward(WebElement element, String playbackposition, AppiumDriver<WebElement> driver,
			String path) throws Exception {

		logger = extent.startTest("Seekingbackwardforward", "Seeking backward and forward");

		int startX = element.getLocation().getX();// liverewind.live_rewind_progressbar.getLocation().getX();
		System.out.println("Startx" + startX);
		int position = 100;
		int xposition = startX + position;


		// Get end point of seekbar.
		int endX = element.getSize().getWidth();// liverewind.live_rewind_progressbar.getSize().getWidth();
		System.out.println("Endx" + endX);

		// Get vertical location of seekbar.
		int yAxis = element.getLocation().getY();// liverewind.live_rewind_progressbar.getLocation().getY();
		System.out.println("Yaxis  :" + yAxis);

		if (playbackposition.equalsIgnoreCase("Beginning")) {

			driver.swipe(startX, yAxis, endX, yAxis, 9000);
			logger.log(LogStatus.INFO, "Seeking right to Beginning "
					+ logger
					.addScreenCapture(capture_ScreenShot(driver, path, "Seeking to Beginning")));
		} else if (playbackposition.equalsIgnoreCase("End"))

		{

			driver.swipe(endX, yAxis, startX, yAxis, 9000);
			logger.log(LogStatus.INFO, "Seeking right to End "
					+ logger.addScreenCapture(capture_ScreenShot(driver, path, "Seeking to End")));

		}

		Thread.sleep(3000);

	}
	
	
	public void MenuClick(String testname,String DeviceOSName, WebElement menuelement, AndroidDriver driver,String screenshotpaths) throws Exception
	{
		
		logger = extent.startTest(testname);
		
		
		NumberFormat numberformat = NumberFormat.getInstance();
		Double Device_OSversion = numberformat.parse(DeviceOSName).doubleValue();
		System.out.println("DeviceOS"+Device_OSversion);
		if(Device_OSversion >= 6.0)
		{	
			menuelement.click();
			logger.log(LogStatus.INFO, testname
					+ logger.addScreenCapture(capture_ScreenShot(driver, screenshotpaths, testname)));
		Thread.sleep(3000);
		}
		else
		{
			menuelement.click();
			logger.log(LogStatus.INFO, testname
					+ logger.addScreenCapture(capture_ScreenShot(driver, screenshotpaths, testname)));
		Thread.sleep(3000);
		}

		((AndroidDeviceActionShortcuts) driver).pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(3000);
	}
	

	public void LiveText_Checking(AppiumDriver<WebElement> driver, String path)
			throws Exception, NullPointerException {

		try {
			// String elements = element.getText();
			boolean livetext = isElementPresent(driver, By.id("uk.co.bbc.avtestharnesssmp:id/live_icon"));
			System.out.println("LiveText Value is" + livetext);

			if (livetext == true) {
				logger.log(LogStatus.INFO, "Live Text  Present: ");
				//logger.log(LogStatus.INFO, "Live Text  Present: "
				// + logger.addScreenCapture(capture_ScreenShot(adriver, path,
				// "LiveText_Present")));
				System.out.println("Live Text  Present");
			}

			else {
				logger.log(LogStatus.INFO, "Live Text Not Present: ");
				// + logger.addScreenCapture(capture_ScreenShot(adriver, path,
				// "LiveText_Present")));
				System.out.println("Live Text Not Present");

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void open_Menu(WebElement element, String testname, AppiumDriver<WebElement> driver, String path)
			throws Exception {
		logger = extent.startTest(testname);
		element.click();
		logger.log(LogStatus.INFO, testname
				+ logger.addScreenCapture(capture_ScreenShot(driver, path, testname)));

		
		
		Thread.sleep(3000);


	}

	public void EnableAudio(String Mediatype, WebElement element) throws Exception
	{
		if (Mediatype == "Audio") 
		{
				element.click();
				Thread.sleep(1000);
		}
	}

	public void VpidBrowser(String testname, WebElement element, WebElement element1, String vpid, String Mediatype,
			WebElement element3, AppiumDriver<WebElement> driver, String path)
			throws Exception {

		logger = extent.startTest(testname);
		element.click();
		Thread.sleep(1000);

		element1.sendKeys(vpid);
		Thread.sleep(1000);

		if (Mediatype == "Audio") {
			driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/isAudioSwitch")).click();
			Thread.sleep(1000);

			element3.click();
			Thread.sleep(2000);
		} else {

		element3.click();
		Thread.sleep(2000);
		}

		logger.log(LogStatus.INFO,
				testname + logger.addScreenCapture(capture_ScreenShot(driver, path, testname)));

	}
	
	
	
	
	public void playback_retry(String Testname, WebElement element, String path, WebElement element1,
			String errormessage, String errorbutton, WebElement element2, AndroidDriver<WebElement> driver)
			throws Exception
	{
		logger = extent.startTest(Testname);
		
		driver.setConnection(networkconnection().AIRPLANE);
		
		// driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
		element.click();
		Thread.sleep(6000);
		
		// playing_orientation(ScreenOrientation.PORTRAIT, "Playing in
		// Landscape", path);
		// playingback_orientation(adriver, ScreenOrientation.PORTRAIT, "Playing
		// in PORTRAIT", path);
		 
		element.click();
		Thread.sleep(6000);

		logger.log(LogStatus.INFO, "Playing in PORTRAIT Mode"
				+ logger.addScreenCapture(capture_ScreenShot(driver, path, "PORTRAIT")));
		
		logger.log(LogStatus.INFO,
				"Airplane Mode ON" + logger.addScreenCapture(capture_ScreenShot(driver, path, "Airplane")));
		System.out.println("Airplane Mode ON");
		
		
		

		logger.log(LogStatus.INFO, "Checking Playback retry");	

		waitForScreenToLoad(driver, element1, 1200);// ondemand_page.vod_error_message,
													// 1200);
		String error = element1.getText(); // ondemand_page.vod_error_message.getText();//driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/error_message")).getText();
		System.out.println("Error Message :-------"+error);
		logger.log(LogStatus.INFO, "Error Message "
				+ logger.addScreenCapture(capture_ScreenShot(driver, path, "Error Message")));
		
//		String Elapsed_Time_Beforeretry = ondemand_page.vod_play_elasped_duration.getText();
//		
//		System.out.println("Elapsed Time Before Retry" + Elapsed_Time_Beforeretry);
	    
		boolean error_message = isElementPresent(driver, By.id(errormessage));// "uk.co.bbc.avtestharnesssmp:id/error_message"));
		if(error_message == true)
		{
			driver.setConnection(networkconnection().DATA);
			logger.log(LogStatus.INFO,
					"Wifi Mode ON" + logger.addScreenCapture(capture_ScreenShot(driver, path, "Wifi")));
			System.out.println("Connected to Mobile WiFi");
			Thread.sleep(10000);
			AssertJUnit.assertTrue("Okay Button Present", isElementPresent(driver, By.id(errorbutton))); // "uk.co.bbc.avtestharnesssmp:id/error_button")));
			Thread.sleep(10000);
			element2.click();
			//ondemand_page.vod_try_again_button.click();
			Thread.sleep(5000);
			
//			driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
//			Thread.sleep(3000);
//			
//			String Elapsed_Time_Afterretry = ondemand_page.vod_play_elasped_duration.getText();
//			System.out.println("Elapsed Time After Retry" + Elapsed_Time_Afterretry);
			
			logger.log(LogStatus.INFO, "Playback Started"
					+ logger.addScreenCapture(capture_ScreenShot(driver, path, "Playback_AfterRetry")));
		}
		else
		{
			System.out.println("No error message");
			Thread.sleep(3000);	
		}
		//waitForScreenToLoad(driver,ondemand_page.vod_buffer,180);
	
		
	}

	public void check_playbackbuffer(WebElement element, String bufferelement, String path,
			AndroidDriver<WebElement> adriver)
			throws InterruptedException {
		logger = extent.startTest("Checking the On-Demand Playback Buffering");


		// driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/subtitles_view")).click();
		element.click();
		Thread.sleep(3000);

		logger.log(LogStatus.INFO, "Check For Playback Buffer");
		wait = new WebDriverWait(adriver, 2000);
		// funct.waitForScreenToLoad(driver,ondemand_page.vod_play_seekbar,100);
		Boolean buffer = isElementPresent(adriver, By.id(bufferelement)); // "uk.co.bbc.avtestharnesssmp:id/buffering_spinner"));
		System.out.println(buffer);
		if (buffer == true) {

			System.out.println("Buffering Video");
			logger.log(LogStatus.INFO, "Buffer Shown : "
					+ logger.addScreenCapture(capture_ScreenShot(adriver, path, "Buffering Video")));
			Thread.sleep(1000);

		} else {
			System.out.println("No Buffering Video");
			logger.log(LogStatus.INFO, "Buffer not Shown : "
					+ logger.addScreenCapture(capture_ScreenShot(adriver, path, "NoBuffering Video")));
			Thread.sleep(1000);
		}

	}

	public void swipingVertical(AppiumDriver<WebElement> driver) throws InterruptedException {
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
		// driver.swipe(startx, starty, startx, endy, 3000);
		// Thread.sleep(2000);
		// Swipe from Top to Bottom.
		driver.swipe(startx, endy, startx, starty, 13000);
		Thread.sleep(2000);
	}

	public void RunAppinBackground(AppiumDriver<WebElement> driver, int seconds, String elemnetText) {
		driver.runAppInBackground(seconds);
		assertTrue(isElementPresent(driver, By.id(elemnetText)));

	}

	public void Navigateback_MainMenu(AppiumDriver<WebElement> driver, String path) throws Exception {

		logger = extent.startTest("Navigate back to Main Menu");

		((AndroidDeviceActionShortcuts) driver).pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(4000);

		driver.rotate(ScreenOrientation.PORTRAIT);
		Thread.sleep(4000);

		logger.log(LogStatus.INFO,
				"AVTestharness Mediated Menu" + logger.addScreenCapture(capture_ScreenShot(driver, path, "AVTestharness_Menu")));
	}

	public void selectItemforPlayback(String item, String message, List<WebElement> ele,
			AndroidDriver<WebElement> driver, String listview, String path)
			throws Exception {

		logger = extent.startTest("Selecting the Item for Playback from List View");
		logger.log(LogStatus.INFO, message + logger.addScreenCapture(capture_ScreenShot(driver, path, message)));

		try {

			String itemtoFound = item;
			Boolean found_result = false;
			Boolean found = false;
			
			System.out.println("itemtoFound" + itemtoFound);

			int counter = 0;

			while (!found_result) {

				ele = driver.findElements(By.id(listview));

				int size = 0;
				size = size + ele.size();
				System.out.println("Size ARE AS " + size);

				for (int i = 0; i < 4; i++)  {

					String s = ele.get(i).getText();
					System.out.println("The Assets are:---" + s);
					System.out.println("The SubSTring of Assets are:---" + s.substring(0, 56));

					// if (s.substring(0, 56).equals(itemtoFound))
				//	if (s.substring(0, 56).equalsIgnoreCase(a1)) 
					if (s.substring(0, 56).equalsIgnoreCase(itemtoFound)) {

						found_result = true;
						System.out.println("Matched");
						System.out.println("Size Item is " + i);
						System.out.println("Size is " + size);

						int sizes = size - 1;
						ele.get(i).click();
						Thread.sleep(5000);
						break;

					} else if (counter == 4) 
					{
						if (!found_result && !(s.substring(0, 56).equalsIgnoreCase(itemtoFound)))
						{
							counter = 0;
							System.out.println("NotMatched");
							scrolling(driver);

						}

					}
				}
				counter++;
					}

		} catch (Exception e) {
			e.printStackTrace();
				}

			}



	public void scrolling(AppiumDriver<WebElement> driver) throws InterruptedException {
		// Get the size of screen.
		Dimension size = driver.manage().window().getSize();
		System.out.println(size);

		// Find swipe start and end point from screen's with and height.
		// Find starty point which is at bottom side of screen.
		int starty = (int) (size.height * 0.60);
		// Find endy point which is at top side of screen.
		int endy = (int) (size.height * 0.40);
		// Find horizontal point where you wants to swipe. It is in middle of
		// screen width.
		int startx = size.width / 2;
		System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

		// Swipe from Bottom to Top.
		Thread.sleep(7000);
		driver.swipe(startx, starty, startx, endy, 12000);

		// Swipe from Top to Bottom.
		// driver.swipe(startx, endy, startx, starty, 13000);
		Thread.sleep(2000);
	}

	
	
	public void resultFolder(String path, String screenspath)
	{
		try{
			
			  String strDirectoy = path;  //"MAPAutomation/Automation/Result";
			  String strManyDirectories=screenspath; //"iOS-DRM/Screenshot";

			  // Create one directory
			  boolean success = (
			  new File(strDirectoy)).mkdir();
			  if (success) {
			  System.out.println("Directory: " 
			   + strDirectoy + " created");
			  }  
			  // Create multiple directories
			  success = (new File(strManyDirectories)).mkdirs();
			  if (success) {
			  System.out.println("Directories: " 
			   + strManyDirectories + " created");
			  }

			  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
			  }
	
	
	public String ResultFolder(String path)
	{
		String strManyDirectories=null;
		 try{
			//  String strDirectoy = path;
			  strManyDirectories= path;

			  // Create one directory
			  boolean  
			  // Create multiple directories
			  success = (new File(strManyDirectories)).mkdirs();
			  if (success) {
			  System.out.println("Directories: " 
			   + strManyDirectories + " created");
			  }

			  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		 return strManyDirectories;
			  }
	
	
	public  String getMediaSelectorURL(String requestselector, String filter)
	{
		String MURL = null;
	   
	    try {
	        Process p = Runtime.getRuntime().exec(requestselector);
	        InputStream is = p.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader	br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line = "";
	        int count=0;
	     	while((line= br.readLine()) != null)
	     	{
	     		String datas[]=line.split(filter); //("http://");
	     		for(int i=0;i<datas.length;i++)
	     		{
	     			 System.out.println("MediaSelector URL:-"+datas[i]);
	     			 MURL=datas[i];
	     		}
	     		if(count==0)
	            {
	            	break;
	            }
	            count++; 
	     	}
	    }catch(Exception e)
	     	{
	     		e.printStackTrace();
	     	}
	        return MURL;
	}

	
	public String getRDOTURL(String requestselector)
	{
		String RDOTURL = null;
	   
	    try {
	        Process p = Runtime.getRuntime().exec(requestselector);
	        InputStream is = p.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader	br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line = "";
	        int count=0;
	     	while((line= br.readLine()) != null)
	     	{
	     		String datas[]=line.split("https://");
	     		for(int i=0;i<datas.length;i++)
	     		{
	     			System.out.println("RDOT URL:-"+datas[i]);
	     			RDOTURL=datas[i];
	     		}
	     		if(count==0)
	            {
	            	break;
	            }
	            count++; 
	           
	     	}
	    }catch(Exception e)
	     	{
	     		e.printStackTrace();
	     	}
	        return RDOTURL;
	}

	
	public  void clearLog(){
	    try {
	    	
	            Process p = Runtime.getRuntime().exec(clearlogs);

	   } catch (IOException e) 
	    {
	    e.printStackTrace();
	    }
	   
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public void UnlockDevice()
	{
		
	           if (((AndroidDriver) driver).isLocked())
	           {
	                   ((AndroidDriver) driver).unlockDevice();
	           }
	           else
	           {
	                   ((AndroidDriver) driver).lockDevice();
	           }
	   
	}
	
	
	public void creatingResultDir(String reportName, String dirName,String screenshots,String screenpathDir, String Deviceid, int appiumport, String DeviceosName, String Devicename ) throws FileAlreadyExistsException, InterruptedException, Exception
	{
		 try
			{
			String filename = reportName;
			String workingDirectorys =  ResultFolder(dirName);  
			String absoluteFilePaths = workingDirectorys + File.separator + filename;
			screenshots = ResultFolder(screenpathDir);    //"/../Automation/Results/iOSDRM";
			//screenhotfiles = new File(ScreenshotPaths);
			
			CreateReport(absoluteFilePaths, Deviceid, Integer.toString(appiumport),
					DeviceosName,
					Devicename);
			}catch(NullPointerException e)
			{
				e.printStackTrace();
			}
	}
	
	/* public void clickOnButtonWithText(SMPANPageObjects_Common pageObject, String text) {
	        List<MobileElement> buttonsWithText = pageObject.getButtonsWithText(text);

	        assertNotNull(buttonsWithText);
	        assertEquals(1, buttonsWithText.size());

	        buttonsWithText.get(0).click();
	    }
	    */
	
	protected void pressBack() {
        if (getDriver() instanceof AndroidDriver) {
            ((AndroidDriver) getDriver()).pressKeyCode(AndroidKeyCode.BACK);
        }
        else if (getDriver() instanceof IOSDriver) {
            ((IOSDriver) getDriver()).navigate().back();
        }
        else {
         //   fail("Driver is neither an Android or iOS driver!");
        }
    }
	
	
	
	    public  void setUp() throws Exception {
	        if (getDriver() == null) {
	   //         setUpEnvironment();
	            setUpDriverForOS();
	        }
	    }

	   
	    public static void tearDown() throws Exception {
	        if (getDriver() != null) {
	            getDriver().quit();
	            drivers = null;
	        }
	    }


//	public  void setUpEnvironment() {
//	        String env = System.getProperty("env");
//
//	        try {
//	            environment = Environment.valueOf(env.toUpperCase());
//	        } catch (RuntimeException e) {
//	            fail("-Denv=local|ci|hive must be specified.");
//	        }
//	    }

	    public void setUpDriverForOS() throws IOException {
	        String os = System.getProperty("os");

	        if (os != null && os.contains("android") && !os.contains("ios")) {
	            drivers = AppiumDriverBuilder.forAndroid().build(environment);
	        } else if (os != null && !os.contains("android") && os.contains("ios")) {
	            drivers = AppiumDriverBuilder.forIOS().build(environment);
	        } else {
	           // fail("-Dos=android|ios must be specified.");
	        }
	    }
	
	
	
}
