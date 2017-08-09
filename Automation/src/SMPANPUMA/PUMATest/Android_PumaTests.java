package SMPANPUMA.PUMATest;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import SMPANPUMA.CommonFunction.CommonFunctions;
import SMPANPUMA.SMPPageObjects.SMPANPageObjects_Common;
import SMPANPUMA.SMPPageObjects.SMPAN_LiveRewind;
import SMPANPUMA.SMPPageObjects.SMPAN_OnDemand;
import SMPANPUMA.CommonFunction.LiveRewindFunctions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.test.smpUtilityFunctions.AppiumManager;
import main.java.test.smpUtilityFunctions.CommandPrompt;
import main.java.test.smpUtilityFunctions.DeviceList;
import main.java.test.smpUtilityFunctions.PortFactory;

public class Android_PumaTests {

	public AndroidDriver<WebElement> driver;
	public DesiredCapabilities capa;
	public WebDriverWait wait;
	
	File file;// = new File(absoluteFilePath);
	
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


//	private static String sdkPath = System.getenv("ANDROID_HOME") +"/platform-tools/";
//	private static String adbPath = sdkPath + "/adb";
//	String[] commandDevices = new String[] { adbPath, "devices" };
	CommandPrompt cmd = new CommandPrompt();

	public List<String> deviceID = new ArrayList<String>();
	public List<String> deviceOS = new ArrayList<String>();
	public List<String> deviceName = new ArrayList<String>();

	String DeviceosName;
	String Deviceid;
	String Devicename;
	String appiumport;
	String appPath;

	DeviceList devicelist = new DeviceList();
	PortFactory portFactory = new PortFactory();

	@BeforeClass
	public void RunTest() throws Exception , InterruptedException{

			try {

				getDeviceDetails();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		/*try {

			getDeviceDetails();
			devicelist.populateDevices_IDs();
			devicelist.populateDevices_OS();
			devicelist.populateDevices_Names();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	

	@Test // (dependsOnMethods = { "RunTest" })
	public void getDeviceDetails() throws Exception {

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
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		}
		
	}*/
	
	
	@Test // (dependsOnMethods = { "RunTest" })
	public void getDeviceDetails() throws Exception {

		
			try {
				DeviceosName = System.getProperty("deviceOS");
				Deviceid = System.getProperty("deviceID");
				appiumport = System.getProperty("appiumPort");
				Devicename = System.getProperty("deviceName");
				appPath = System.getProperty("appPath");
				System.out.println("The Device OS is " + DeviceosName);
				System.out.println("The Device ID is " + Deviceid);
				System.out.println("The Device Name is " + Devicename);
				System.out.println("The Appium Port Name is " + appiumport);
				System.out.println("The App path  is " + appPath);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		
		
	}
	

	@Test(dependsOnMethods = {"getDeviceDetails"})
	public void setUp() throws Exception {
		try
		{
		//ap.startAppium(Integer.parseInt(appiumport));
		//ap.AppiumURL();
		//String appiul_url = ap.AppiumURL();
		String appiul_url = "http://localhost:"+appiumport+"/wd/hub";
		System.out.println("Appium Service Address : - " + appiul_url);

		capa = new DesiredCapabilities();
		capa.setCapability("appium-version", "1.0");
		capa.setCapability("deviceName", Deviceid);
		capa.setCapability("platformName", "Android");
		capa.setCapability("platformVersion", DeviceosName);
		capa.setCapability("app", appPath);
		capa.setCapability("appPackage", "uk.co.bbc.avtestharnesssmp");
		capa.setCapability("appActivity", "uk.co.bbc.avtestharnesssmp.MainActivity");
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

	@Test(dependsOnMethods = {"setUp"})
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
				
				commonfunct.CreateReport(absoluteFilePaths, Deviceid, appiumport,
						DeviceosName,
						Devicename);
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
			 
			 
	
		
		
	/**
	 * 
	 * Puma Tests for VOD
	 *
	 */
	
	//Select a a Panorama Video form the Mediated List and plays it in embedded view and then switches to Full Screen view
	@Test(dependsOnMethods={"OpenAvtest"})
	public void Play_VideoOnDemand() throws Exception 
	{
	
		commonfunct.selectItemforPlayback(commonobjects.videoEpisode, "OnDemandVideo", commonobjects.element, driver, commonobjects.listview,
				ScreenshotPaths);
		

		commonfunct.tapbutton("Clicking on Play Button", commonobjects.play_button, driver, ScreenshotPaths);
		Thread.sleep(1000);

		
		commonfunct.tapbutton("Entering Full Screen",commonobjects.playback_fullscreen,
				driver, ScreenshotPaths);

	
	}
	
	//Turn's the Subtitle On for the video playing
	@Test(dependsOnMethods={"Play_VideoOnDemand"})
	public void TurningSubtilte_ON() throws Exception 
	{
		commonfunct.turnSubtitleON("Truning Subtitle ON","Video", commonobjects.vod_play_subtitle, ScreenshotPaths);
	}
	
	
	//Plays a video for few minutes and compares the time difference from start of playback with the elapsed time
	@Test(dependsOnMethods={"TurningSubtilte_ON"})
	public void CheckingPlayback_VOD() throws Exception {

		try {

			commonfunct.Checkplayback_duration(ondemandobjects.vod_play_total_duration,
					ondemandobjects.vod_play_elasped_duration, ScreenshotPaths, driver);



		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	
	//Pause a video , exits the full screen and navigates back to Mediated Menu List
	@Test(dependsOnMethods={"CheckingPlayback_VOD"})
	public void Pause_VideoOnDemand() throws Exception 
	{
	
		commonfunct.tapbutton("Pause OnDemand_Video playback", commonobjects.Playback_Pause, driver, ScreenshotPaths);
		
		commonfunct.tapbutton("Exiting Full Screen",commonobjects.vod_play_fullscreen_exit,
				driver, ScreenshotPaths);
		
		commonfunct.Navigateback_MainMenu(driver, ScreenshotPaths);
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
	
		commonfunct.selectItemforPlayback(commonobjects.audioEpisode, "OnDemandAudio", commonobjects.element, driver, commonobjects.listview,
				ScreenshotPaths);
	
		commonfunct.tapbutton("Clicking on Play Button", commonobjects.play_button, driver, ScreenshotPaths);
		Thread.sleep(1000);
		
		commonfunct.tapbutton("Entering Full Screen",commonobjects.playback_fullscreen,
				driver, ScreenshotPaths);

		
	}
	
	//Plays a Audio for few minutes and compares the time difference from start of playback with the elapsed time
	@Test(dependsOnMethods={"Play_AudioOnDemand"})
	public void CheckingPlayback_AOD() throws Exception {

		try {

			commonfunct.Checkplayback_duration(ondemandobjects.vod_play_total_duration,
					ondemandobjects.vod_play_elasped_duration, ScreenshotPaths, driver);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	//Pause a Audio , exits the full screen and navigates back to Mediated Menu List
	@Test(dependsOnMethods={"CheckingPlayback_AOD"})
	public void Pause_AudiooOnDemand() throws Exception 
	{
	
		commonfunct.tapbutton("Pause OnDemand_Video playback", commonobjects.Playback_Pause, driver, ScreenshotPaths);
		
		commonfunct.tapbutton("Exiting Full Screen",commonobjects.vod_play_fullscreen_exit,
				driver, ScreenshotPaths);
		
		commonfunct.Navigateback_MainMenu(driver, ScreenshotPaths);
	}
	
	
	@AfterClass
	public void End() throws Exception
			{
		try
		{
			commonfunct.GenerateReport();
			driver.closeApp();
			driver.quit();
			ap.stopappium();
			}catch(NullPointerException e)
		{
				e.printStackTrace();
		}
			}
}


/*
 * @Test(dependsOnMethods={"OpenAvtest"})
	public void Play_LiveRewindTest() throws Exception 
	{
		
		commonfunct.selectItemforPlayback(commonobjects.liveEpsiode, "LiveSimulcast_Video", commonobjects.element, driver, commonobjects.listview,
				ScreenshotPaths);
		commonfunct.tapbutton("Clicking on Play Button", commonobjects.play_button, driver, ScreenshotPaths);
		
		commonfunct.tapbutton("Entering Full Screen",commonobjects.playback_fullscreen,
				driver, ScreenshotPaths);
	}
	
	@Test(dependsOnMethods={"Play_LiveRewindTest"})
	public void CheckingPlayback_LiveRewind()
	{
		try {
			commonfunct.PlaybackContinue("Live Rewind Playback",liverewindobject.live_simulcat_rewind_time, driver, ScreenshotPaths);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Test(dependsOnMethods={"CheckingPlayback_LiveRewind"})
	public void PlaybackRotation() throws Exception {

		try {

		driver.rotate(ScreenOrientation.LANDSCAPE);
			commonfunct.playback_orientation(driver, ScreenOrientation.LANDSCAPE, "Playing in LANDSCAPE",
					ScreenshotPaths);
		
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {

			commonfunct.playback_orientation(driver, ScreenOrientation.PORTRAIT, "Playing in PORTRAIT",
					ScreenshotPaths);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	@Test(dependsOnMethods={"PlaybackRotation"})
	public void Pause_LiveRewindTest() throws Exception 
	{
	
		commonfunct.tapbutton("Pause LiveRewind_playback", commonobjects.Playback_Pause, driver, ScreenshotPaths);
		
		commonfunct.tapbutton("Exiting Full Screen",commonobjects.vod_play_fullscreen_exit,
				driver, ScreenshotPaths);
		
		commonfunct.Navigateback_MainMenu(driver, ScreenshotPaths);
	}*/
