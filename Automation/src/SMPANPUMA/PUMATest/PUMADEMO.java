package SMPANPUMA.PUMATest;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import SMPANPUMA.CommonFunction.CommonFunctions;
import SMPANPUMA.SMPPageObjects.SMPANPageObjects_Common;
import SMPANPUMA.SMPPageObjects.SMPAN_LiveRewind;
import SMPANPUMA.SMPPageObjects.SMPAN_OnDemand;
import SMPANPUMA.CommonFunction.LiveRewindFunctions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.test.smpUtilityFunctions.AppiumManager;
import main.java.test.smpUtilityFunctions.CommandPrompt;
import main.java.test.smpUtilityFunctions.DeviceList;
import main.java.test.smpUtilityFunctions.PortFactory;

public class PUMADEMO {

	public AndroidDriver<WebElement> driver;
	public DesiredCapabilities capa;
	public WebDriverWait wait;
	
	File file;// = new File(absoluteFilePath);
	
	public ExtentReports extent = null;
	public ExtentTest logger = null;
	
	/*
	 * 
	 * initializing the objects
	 */
	AppiumManager ap = new AppiumManager();
	CommonFunctions commonfunct = new CommonFunctions();
	SMPAN_OnDemand ondemandobjects;
	SMPAN_LiveRewind liverewindobject;
	LiveRewindFunctions liverewindFunctions = new LiveRewindFunctions();
	SMPANPageObjects_Common commonobjects;
	
	public String filename;
	public String workingDirectorys;  
	public String absoluteFilePaths;
	public String ScreenshotPaths;    //"/../Automation/Results/iOSDRM";
	File screenhotfiles;


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

	public WebElement playbutton ;
	DeviceList devicelist = new DeviceList();
	PortFactory portFactory = new PortFactory();
	
		@DataProvider(name = "DeviceConfigs")
	  public static Object[][] device()
		{
		String appiumport = System.getProperty("appiumPort");
		String deviceID = System.getProperty("deviceID");
		String deviceOS = System.getProperty("deviceOS");
		
		System.out.println("appiumport is:-"+appiumport);
		System.out.println("Device ID:-"+deviceID);
		System.out.println("Device OS:-"+deviceOS);
	 
		 return new Object[][] {{appiumport,deviceID,deviceOS}};
				 
	 
	  }
		
	


	@Test(dataProvider = "DeviceConfigs")
	public void deviceSetup(String appiumport, String deviceID, String deviceOS) throws Exception {
		try
		{
		ap.startAppium(Integer.parseInt(appiumport));
		ap.AppiumURL();
		String appiul_url = ap.AppiumURL();
		System.out.println("Appium Service Address : - " + appiul_url);

		capa = new DesiredCapabilities();
		capa.setCapability("appium-version", "1.0");
		capa.setCapability("deviceName", deviceID);
		capa.setCapability("platformName", "Android");
		capa.setCapability("platformVersion", deviceOS);
		capa.setCapability("app", "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/BuildsSMP-AN/SMP-AN-28.4452-dev.apk");
		capa.setCapability("appPackage", "uk.co.bbc.avtestharnesssmp");
		capa.setCapability("appActivity", "uk.co.bbc.avtestharnesssmp.MainActivity");
	//	capa.setCapability(AndroidMobileCapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, true);
	//	capa.setCapability("autoAcceptAlerts", false);
	//	capa.setCapability("autoDismissAlerts", true);
		driver = new AndroidDriver<>(new URL(appiul_url), capa);
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		
		/*try
		{
		String sdkPath = "/Users/ramakh01/Downloads/android-sdk/platform-tools/";
			Runtime.getRuntime().exec(sdkPath + File.separator +"adb shell input keyevent KEYCODE_POWER");
			Thread.sleep(2000);
			Runtime.getRuntime().exec(sdkPath + File.separator +"adb shell input swipe 572 235 1260 1564");
			Thread.sleep(3000);
			
			Runtime.getRuntime().exec(sdkPath + File.separator +"adb shell input text 1234");
			Thread.sleep(3000);
			Runtime.getRuntime().exec(sdkPath + File.separator +"adb shell input keyevent KEYCODE_ENTER");
			}catch(NullPointerException e)
		{
			e.printStackTrace();
		}*/

	}
	
	/*
	 * initializing the page objects 
	 */

	@Test
	public void OpenAvtest() throws Exception {
		try {
		

			commonobjects = new SMPANPageObjects_Common();
			PageFactory.initElements(new AppiumFieldDecorator(driver), commonobjects);

			liverewindobject = new SMPAN_LiveRewind();
			PageFactory.initElements(new AppiumFieldDecorator(driver), liverewindobject);

			ondemandobjects = new SMPAN_OnDemand();
			PageFactory.initElements(new AppiumFieldDecorator(driver), ondemandobjects);
			
			filename = "SMPAN_PUMATest";
				workingDirectorys =  commonfunct.ResultFolder(commonobjects.ParentDirectoy);  
				absoluteFilePaths = workingDirectorys + File.separator + filename;
				ScreenshotPaths = commonfunct.ResultFolder(commonobjects.SubDirectory);    //"/../Automation/Results/iOSDRM";
				//screenhotfiles = new File(ScreenshotPaths);
				
//				commonfunct.CreateReport(absoluteFilePaths, deviceID, Integer.parseInt(appiumPort),
//						deviceOS,
//						deviceName);
				}catch(NullPointerException e)
				{
					e.printStackTrace();
				}
		}
		
		/*
		 * Check the device OS version if OS version less then 6.0, use a different Element ID to click Menu  
		 * 	blocking this code for execution for PUMA
		 
	
			NumberFormat numberformat = NumberFormat.getInstance();
			Double Device_OSversion = numberformat.parse(DeviceosName).doubleValue();
			System.out.println("DeviceOS"+Device_OSversion);
			if(Device_OSversion >= 6.0)
			{	
			commonobjects.menuoptions.click();
			Thread.sleep(3000);
			}
			else
			{
			commonobjects.menu.click();
			Thread.sleep(3000);
			}

			driver.pressKeyCode(AndroidKeyCode.BACK);
			Thread.sleep(3000);*/
			 
	
	
	/*
	 *
	 */
	@Test(dependsOnMethods={"OpenAvtest"})
		public void Play_LiveRewindTest() throws Exception 
		{
			try
			{
			selectItemforPlayback(commonobjects.liveEpsiode, "LiveSimulcast_Video", commonobjects.element, driver, commonobjects.listview);
			
			Mtapbutton(commonobjects.playbutton, driver);
			
			tapbutton(commonobjects.playback_fullscreen,driver);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		@Test(dependsOnMethods={"Play_LiveRewindTest"})
		public void CheckingPlayback_LiveRewind()
		{
			try {
				//PlaybackContinue(liverewindobject.live_simulcat_rewind_time, driver);
				Checkplayback_duration(ondemandobjects.vod_play_total_duration,
						ondemandobjects.vod_play_elasped_duration,  driver);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		@Test(dependsOnMethods={"CheckingPlayback_LiveRewind"})
		public void PlaybackRotation() throws Exception {

			try {

				driver.rotate(ScreenOrientation.LANDSCAPE);
				
			
			} catch (Exception e) {

				e.printStackTrace();
			}
			try {

				driver.rotate(ScreenOrientation.LANDSCAPE);

			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		
		@Test(dependsOnMethods={"PlaybackRotation"})
		public void Pause_LiveRewindTest() throws Exception 
		{
		
			tapbutton(commonobjects.Playback_Pause, driver);
			
			tapbutton(commonobjects.vod_play_fullscreen_exit,
					driver);
			
			driver.pressKeyCode(AndroidKeyCode.BACK);
		}
	
	
		
		
	/**
	 * 
	 * Puma Tests for VOD
	 *
	 */
	
	//Select a a Panorama Video form the Mediated List and plays it in embedded view and then switches to Full Screen view
	@Test(dependsOnMethods={"OpenAvtest"})
	public void Play_VideoOnDemand() throws Exception 
	{
	
		selectItemforPlayback(commonobjects.videoEpisode, "OnDemandVideo", commonobjects.element, driver, commonobjects.listview);
		

		playbutton = driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/placeholder_play_button"));
		tapbutton(playbutton,driver);
		Thread.sleep(1000);

		
		tapbutton(commonobjects.playback_fullscreen,driver);

	
	}
	
	//Turn's the Subtitle On for the video playing
	@Test(dependsOnMethods={"Play_VideoOnDemand"})
	public void TurningSubtilte_ON() throws Exception 
	{
		turnSubtitleON("video", driver, commonobjects.vod_play_subtitle);
	}
	
	
	//Plays a video for few minutes and compares the time difference from start of playback with the elapsed time
	@Test(dependsOnMethods={"TurningSubtilte_ON"})
	public void CheckingPlayback_VOD() throws Exception {

		try {

			Checkplayback_duration(ondemandobjects.vod_play_total_duration,
					ondemandobjects.vod_play_elasped_duration,  driver);



		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	
	//Pause a video , exits the full screen and navigates back to Mediated Menu List
	@Test(dependsOnMethods={"CheckingPlayback_VOD"})
	public void Pause_VideoOnDemand() throws Exception 
	{
	
		tapbutton(commonobjects.Playback_Pause, driver);
		
		tapbutton(commonobjects.vod_play_fullscreen_exit,driver);
		
//		commonfunct.Navigateback_MainMenu(driver, ScreenshotPaths);
		driver.pressKeyCode(AndroidKeyCode.BACK);
	}
	
	
	
	/**
	 * 
	 * Puma Tests for AOD
	 *
	 */
	
	//Select a a Audio Asset form the Mediated List and plays it in embedded view and then switches to Full Screen view
	@Test(dependsOnMethods={"Pause_VideoOnDemand"})
	public void Play_AudioOnDemand() throws Exception 
	{
	
		selectItemforPlayback(commonobjects.audioEpisode, "OnDemandAudio", commonobjects.element, driver, commonobjects.listview);
	
		tapbutton(commonobjects.play_button,driver);
		Thread.sleep(1000);
		
		tapbutton(commonobjects.playback_fullscreen,driver);

		
	}
	
	//Plays a Audio for few minutes and compares the time difference from start of playback with the elapsed time
	@Test(dependsOnMethods={"Play_AudioOnDemand"})
	public void CheckingPlayback_AOD() throws Exception {

		try {

			Checkplayback_duration(ondemandobjects.vod_play_total_duration,
					ondemandobjects.vod_play_elasped_duration,driver);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	//Pause a Audio , exits the full screen and navigates back to Mediated Menu List
	@Test(dependsOnMethods={"CheckingPlayback_AOD"})
	public void Pause_AudiooOnDemand() throws Exception 
	{
	
		tapbutton(commonobjects.Playback_Pause, driver);
		
		tapbutton(commonobjects.vod_play_fullscreen_exit,driver);
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
	}
	
	
	@AfterClass
	public void End() throws Exception
			{
		try
		{
			extent.endTest(logger);
			extent.flush();
			driver.closeApp();
			driver.quit();
			ap.stopappium();
			}catch(NullPointerException e)
		{
				e.printStackTrace();
		}
			}
	
	
	
	
	
	public void selectItemforPlayback(String item, String message, List<WebElement> ele,
			AndroidDriver<WebElement> driver, String listview)
			throws Exception {

//		logger = extent.startTest("Selecting the Item for Playback from List View");
//		logger.log(LogStatus.INFO, message + logger.addScreenCapture(capture_ScreenShot(driver, path, message)));

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
	
	
	public void turnSubtitleON(String contentType, AndroidDriver<WebElement> driver,WebElement vod_play_subtitle)
	{
//		logger = extent.startTest(testname);
		if (contentType == "Video") {
			try {
				tapbutton(vod_play_subtitle, driver);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void tapbutton(WebElement play_button,AndroidDriver<WebElement> driver)
			throws Exception {
		try
		{
		play_button.click();
//		Thread.sleep(2000);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void Mtapbutton(MobileElement play_button,AndroidDriver<WebElement> driver)
			throws Exception {
		try
		{
		play_button.click();
//		Thread.sleep(2000);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void scrolling(AndroidDriver<WebElement> driver) throws InterruptedException {
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
	
	
	
	public void Checkplayback_duration(WebElement element1, WebElement element2,
			AndroidDriver<WebElement> adriver) throws Exception {


		String Total_Duration = element1.getText(); 
		System.out.println("Total Duration" + Total_Duration);

		int etime = 0;
		String Elapsed_Time;
		String Elapsed_Time_Start = element2.getText(); 
		System.out.println("ElapsedTime  When Playback Started:----   " + Elapsed_Time_Start);
		do {

			Elapsed_Time = element2.getText(); 
			// driver.findElement(By.id("uk.co.bbc.avtestharnesssmp:id/elapsed")).getText();
			etime++;
		} while (etime < 120);

		Elapsed_Time = element2.getText(); // ondemand_page.vod_play_elasped_duration.getText();

		System.out.println("Total ElapsedTime After Playback Stoped:----   " + Elapsed_Time);

		if (!Elapsed_Time_Start.equals(Elapsed_Time)) {
			System.out.println("Not Matched");
		} else {
			System.out.println(" Matched");
		}


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
	
	
}






