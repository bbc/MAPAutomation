package SMPANPUMA.PUMATest;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
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
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.test.smpUtilityFunctions.AppiumManager;
import main.java.test.smpUtilityFunctions.CommandPrompt;
import main.java.test.smpUtilityFunctions.DeviceList;
import main.java.test.smpUtilityFunctions.PortFactory;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;

public class Android_PumaTests {

	public AndroidDriver<WebElement> driver;
	public DesiredCapabilities capa;
	public WebDriverWait wait;
	
	File file;
	
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
	public String ScreenshotPaths;    
	
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
				setUp();
				OpenAvtest();	
			}	catch (Exception e) {
					e.printStackTrace();
				}						
	}
			
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
				
			} 	catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
	}
	
	public void setUp() throws Exception {
		try {
				
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
				
			 }	catch (NullPointerException e) {
					e.printStackTrace();
				}
	}
	
	/*
	 * initializing the page objects 
	 */
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
				ScreenshotPaths = commonfunct.ResultFolder(commonobjects.SubDirectory); 
				file = new File(ScreenshotPaths);	
				commonfunct.CreateReport(absoluteFilePaths, Deviceid, appiumport,
							DeviceosName,
							Devicename);
			}	catch(NullPointerException e){
						e.printStackTrace();
				 }
	}
	
	@Test(priority = 0)
	public void playLiveRewindTest() throws Exception {
		//setting the default rotation to potratit 
		driver.rotate(ScreenOrientation.PORTRAIT);
		commonfunct.selectItemforPlayback(commonobjects.liveEpsiode, "LiveSimulcast_Video", commonobjects.element, driver, commonobjects.listview,
					ScreenshotPaths);
		commonfunct.tapbutton("Clicking on Play Button", commonobjects.play_button, driver, ScreenshotPaths);
		commonfunct.waitforElementById(driver, commonobjects.playPauseId, 40);
		
		// Verify that Pause button is present after clicking the Play button
		Assert.assertTrue("Live Video Pause button is not displayed",commonobjects.Playback_Pause.isDisplayed());
			
		// Verify that media Title is present
		Assert.assertTrue("Live video Embedded view Title is not displayed",commonobjects.assetTitle.isDisplayed());
		commonfunct.tapbutton("Entering Full Screen",commonobjects.playback_fullscreen,
					driver, ScreenshotPaths);
	}
		
	@Test(dependsOnMethods={"playLiveRewindTest"})
	public void checkingPlaybackLiveRewind(){
		try {
				boolean status;
					status = commonfunct.verify_PlaybackContinue("Live Rewind Playback",liverewindobject.live_simulcat_rewind_time, driver, ScreenshotPaths);
					Assert.assertTrue("Live video play back is not started",status);
			}	catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
			
	@Test(dependsOnMethods={"checkingPlaybackLiveRewind"})
	public void playbackRotation() throws Exception {
		try {
				driver.rotate(ScreenOrientation.LANDSCAPE);
				String screenOrientation = commonfunct.playbackOrientation(driver, ScreenOrientation.LANDSCAPE, "Playing in LANDSCAPE",
								ScreenshotPaths);
				Assert.assertEquals("Mobile screen is not rotated to Landscape mode", screenOrientation, "LANDSCAPE");		
			}	catch (Exception e) {
				e.printStackTrace();
				}
		try {
	
				String screenOrientation = commonfunct.playbackOrientation(driver, ScreenOrientation.PORTRAIT, "Playing in PORTRAIT",
								ScreenshotPaths);
				Assert.assertEquals("Mobile screen is not rotated to Portrait mode", screenOrientation, "PORTRAIT");
				//Click on the full screen button
				commonfunct.tapbutton("Video-Entering Full Screen",commonobjects.vod_play_fullscreen_exit,
								driver, file.getAbsolutePath());			
				driver.pressKeyCode(AndroidKeyCode.BACK);
						
			}	catch (Exception e) {
						e.printStackTrace();
				}
	}
		
	
//	
	/**
	 * 
	 * Puma Tests for VOD
	 * @throws Exception 
	 *
	 */
	
	//Select a a Panorama Video form the Mediated List and plays it in embedded view and then switches to Full Screen view
	@Test(dependsOnMethods={"playbackRotation"})
	public void playVideoOnDemand() throws Exception {
		
		commonfunct.selectItemforPlayback(commonobjects.videoEpisode, "OnDemandVideo", commonobjects.element, driver, commonobjects.listview,
					file.getAbsolutePath());
		commonfunct.tapbutton("Video-Clicking on Play Button", commonobjects.play_button, driver, file.getAbsolutePath());
		commonfunct.waitforElementById(driver, commonobjects.playPauseId, 10);
			
		// Verify that Pause button is present after clicking the Play button
		Assert.assertTrue("VOD Pause button is not displayed",commonobjects.Playback_Pause.isDisplayed());
			
		// Verify that media Title is present
		Assert.assertTrue("VOD embedded view Tittle is not displayed",commonobjects.assetTitle.isDisplayed());
			
		//Click on the full screen button
		commonfunct.tapbutton("Video-Entering Full Screen",commonobjects.playback_fullscreen,
					driver, file.getAbsolutePath());     
		// Verify the Media Title on the Full-screen
		Assert.assertTrue("VOD Portrait mode Primary Title is not displayed",commonobjects.primaryTitle.isDisplayed());
		Assert.assertTrue("VOD Portrait mode Secondary Title is not displayed",commonobjects.secondaryTitle.isDisplayed());
			
		// Clicking the SubTitle Button
		commonfunct.turnSubtitleON("Video-Subtitle ON","Video", commonobjects.subtitlesBtn, driver,file.getAbsolutePath());
			
		// Verify that Disable-Subtitle button is enabled.
		Assert.assertTrue("Disabled Subtitle button is not displayed",commonobjects.disbledSubTitleBtn.isDisplayed());
			
		// Clicking the Disable SubTitle Button
		commonfunct.turnSubtitleON("Video-Subtitle Off","Video", commonobjects.disbledSubTitleBtn, driver,file.getAbsolutePath());   
		   
		// Verify that Subtitle button is enabled.
		Assert.assertTrue("Enable Subtitle button is not displayed ",commonobjects.subtitlesBtn.isDisplayed());
		
	}
	
	//Plays a video for few minutes and compares the time difference from start of playback with the elapsed time
	@Test(dependsOnMethods={"playVideoOnDemand"})
	public void checkingPlaybackVOD() throws Exception {
		try {
				boolean playback_status;
				playback_status = commonfunct.verifyPlaybackDuration(ondemandobjects.vod_play_total_duration,
							ondemandobjects.vod_play_elasped_duration, file.getAbsolutePath(), driver);
					
				// Verify that the playback started.
				Assert.assertTrue("VOD Play back is not started",playback_status);
					
				//Click on the full screen button
				commonfunct.tapbutton("Video-Entering Full Screen",commonobjects.vod_play_fullscreen_exit,
							driver, file.getAbsolutePath());
					
				driver.pressKeyCode(AndroidKeyCode.BACK);
					
			}	catch (Exception e) {
					e.printStackTrace();
			}
	}
	
	
	
	/**
	 * 
	 * Puma Tests for AOD
	 *
	 */
	
	//Select a a Audio Asset form the Mediated List and plays it in embedded view and then switches to Full Screen view
	@Test(dependsOnMethods={"playVideoOnDemand"})
	public void playAudioOnDemand() throws Exception 
	{
	
		commonfunct.selectItemforPlayback(commonobjects.audioEpisode, "OnDemandAudio", commonobjects.element, driver, commonobjects.listview,
				file.getAbsolutePath());
	
		commonfunct.tapbutton("Audio-Clicking on Play Button", commonobjects.play_button, driver, file.getAbsolutePath());
		commonfunct.waitforElementById(driver, commonobjects.playPauseId, 10);
		
		// Verify that Pause button is present after clicking the Play button
		Assert.assertTrue("AOD Stop button is not displayed ",commonobjects.Playback_Pause.isDisplayed());
		
		// Verify that media Title is present
		Assert.assertTrue("AOD enbedded view Title is not displayed",commonobjects.assetTitle.isDisplayed());
		
		commonfunct.tapbutton("Audio-Entering Full Screen",commonobjects.playback_fullscreen,
				driver, file.getAbsolutePath());
		
		// Verify the Media Title on the Full-screen
		Assert.assertTrue("AOD Portrait view Primary Title is not displayed",commonobjects.primaryTitle.isDisplayed());
		Assert.assertTrue("AOD Protrait view secondary Title is not displayed",commonobjects.secondaryTitle.isDisplayed());
	}
	
	//Plays a Audio for few minutes and compares the time difference from start of playback with the elapsed time
	@Test(dependsOnMethods={"playAudioOnDemand"})
	public void checkingPlaybackAOD() throws Exception {
		try {
				boolean audio_palyback_status;
				audio_palyback_status = commonfunct.verifyAudioPlaybackDuration(ondemandobjects.vod_play_total_duration,
						ondemandobjects.vod_play_elasped_duration, file.getAbsolutePath(), driver);
				Assert.assertTrue("AOD playback is not started", audio_palyback_status);
				driver.pressKeyCode(AndroidKeyCode.BACK);

			}	catch (Exception e) {
					e.printStackTrace();
		}
	}
		
	@AfterClass
	public void End() throws Exception{
		try
		{
			commonfunct.GenerateReport();
			driver.closeApp();
			driver.quit();
			//ap.stopappium();
		}	catch(NullPointerException e){
				e.printStackTrace();
		}
	}
}

