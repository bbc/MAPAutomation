package SMPANPUMA.PUMATest;

import java.io.File;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.ScenarioType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import SMPANPUMA.CommonFunction.CommonFunctions;
import SMPANPUMA.SMPPageObjects.SMPANPageObjects_Common;
import SMPANPUMA.SMPPageObjects.SMPAN_LiveRewind;
import SMPANPUMA.SMPPageObjects.SMPAN_OnDemand;
import SMPANPUMA.CommonFunction.LiveRewindFunctions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.java.test.smpUtilityFunctions.AppiumManager;
import main.java.test.smpUtilityFunctions.CommandPrompt;
import main.java.test.smpUtilityFunctions.DeviceList;
import main.java.test.smpUtilityFunctions.PortFactory;

public class Android_PumaTests {

	public AndroidDriver<WebElement> driver;
	public DesiredCapabilities capa;
	public WebDriverWait wait;
	
	String workingDirectory = "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/Results";   // System.getProperty("user.dir");
	String absoluteFilePath = workingDirectory;
	public String ScreenshotPath = "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/Results/PUMA/Screenshots";
	public String Android_Path= "/../../../MAP_Automation/MAPAutomation/Automation/BuildsSMP-AN/SMP-AN-28.4452-dev.apk";
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


	DeviceList devicelist = new DeviceList();

	PortFactory portFactory = new PortFactory();

	
	@BeforeClass
	public void RunTest() throws Exception , InterruptedException{

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
				System.out.println("App Path:-"+Android_Path);

			
				NumberFormat numberformat = NumberFormat.getInstance();
				Double Device_OSversion = numberformat.parse(DeviceosName).doubleValue();
				System.out.println("DeviceOS"+Device_OSversion);	

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		}
		// ap.stopappium();
	}

	@Test(dependsOnMethods = {"getAllDetails"})
//	public void setUp(int port, String deviceId, String OS, String appPath) throws Exception {
	public void setUp() throws Exception {
		try
		{
		ap.startAppium(appiumport);
		ap.AppiumURL();
		String appiul_url = ap.AppiumURL();
		System.out.println("Appium Service Address : - " + appiul_url);

		capa = new DesiredCapabilities();
		capa.setCapability("appium-version", "1.0");
		capa.setCapability("deviceName", Deviceid);
		capa.setCapability("platformName", "Android");
		capa.setCapability("platformVersion", DeviceosName);
		capa.setCapability("app", "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/BuildsSMP-AN/SMP-AN-28.4452-dev.apk");
		capa.setCapability("appPackage", "uk.co.bbc.avtestharnesssmp");
		capa.setCapability("appActivity", "uk.co.bbc.avtestharnesssmp.MainActivity");
	//	capa.setCapability(AndroidMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
		driver = new AndroidDriver<>(new URL(appiul_url), capa);
			// capa.setCapability("newCommandTimeout", timeout);
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

	@Test(dependsOnMethods = {"setUp"})
	public void OpenAvtest() throws Exception {
		try {
		

			commonobjects = new SMPANPageObjects_Common();
			PageFactory.initElements(new AppiumFieldDecorator(driver), commonobjects);

			liverewindobject = new SMPAN_LiveRewind();
			PageFactory.initElements(new AppiumFieldDecorator(driver), liverewindobject);

			ondemandobjects = new SMPAN_OnDemand();
			PageFactory.initElements(new AppiumFieldDecorator(driver), ondemandobjects);
			
			 try
				{
				filename = "SMPAN_PUMATest";
				workingDirectorys =  commonfunct.ResultFolder(commonobjects.ParentDirectoy);  
				absoluteFilePaths = workingDirectorys + File.separator + filename;
				ScreenshotPaths =  workingDirectorys+ File.separator+commonfunct.ResultFolder(commonobjects.SubDirectory);    //"/../Automation/Results/iOSDRM";
				//screenhotfiles = new File(ScreenshotPaths);
				
				commonfunct.CreateReport(absoluteFilePaths, Deviceid, Integer.toString(appiumport),
						DeviceosName,
						Devicename);
				}catch(NullPointerException e)
				{
					e.printStackTrace();
				}
		
//			commonfunct.CreateReport(absoluteFilePath + File.separator + "SMPAN_PUMATest", Deviceid, DeviceosName,
//					Integer.toString(appiumport), Devicename);
			
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
			Thread.sleep(3000);
		}catch(Exception e)
		{
		e.printStackTrace();
		}
	}	
		
	/**
	 * 
	 * Puma Tests for VOD
	 * @throws Exception
	 */
	
	@Test(dependsOnMethods={"OpenAvtest"})
	public void Play_VideoOnDemand() throws Exception 
	{
	
		commonfunct.selectItemforPlayback(commonobjects.videoEpisode, "OnDemandVideo", commonobjects.element, driver, commonobjects.listview,
				ScreenshotPaths);
		

		commonfunct.tapbutton("Clicking on Play Button", commonobjects.play_button, driver, ScreenshotPaths);
		Thread.sleep(1000);

		
		commonfunct.tapbutton("Entering Full Screen",commonobjects.playback_fullscreen,
				driver, ScreenshotPaths);

		commonfunct.turnSubtitleON("Video", commonobjects.vod_play_subtitle, ScreenshotPaths);
	}
	
	
	@Test(dependsOnMethods={"Play_VideoOnDemand"})
	public void CheckingPlayback_VOD() throws Exception {

		try {

			commonfunct.Checkplayback_duration(ondemandobjects.vod_play_total_duration,
					ondemandobjects.vod_play_elasped_duration, ScreenshotPaths, driver);



		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	
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
	 * @throws Exception
	 */
	
	
	@Test(dependsOnMethods={"Pause_VideoOnDemand"})
	public void Play_AudioOnDemand() throws Exception 
	{
	
		commonfunct.selectItemforPlayback(commonobjects.audioEpisode, "OnDemandAudio", commonobjects.element, driver, commonobjects.listview,
				ScreenshotPaths);
	
		commonfunct.tapbutton("Clicking on Play Button", commonobjects.play_button, driver, ScreenshotPaths);
		Thread.sleep(1000);
		
		commonfunct.tapbutton("Entering Full Screen",commonobjects.playback_fullscreen,
				driver, ScreenshotPaths);

		commonfunct.turnSubtitleON("Video", commonobjects.vod_play_subtitle, ScreenshotPaths);
	}
	
	
	@Test(dependsOnMethods={"Play_AudioOnDemand"})
	public void CheckingPlayback_AOD() throws Exception {

		try {

			commonfunct.Checkplayback_duration(ondemandobjects.vod_play_total_duration,
					ondemandobjects.vod_play_elasped_duration, ScreenshotPaths, driver);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	

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
