package main.java.test.DRMMainTests;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.relevantcodes.extentreports.ExtentReports;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.test.DRMCommonFunctions.DRMCommomFunctions;
import main.java.test.DRMPageObjects.iOSDRMPageObjects;
import main.java.test.smpFunctions.CommonFunction;
import main.java.test.smpFunctions.iOSCommonFunctions;
import main.java.test.smpPageObjects.CommonObjects;
import main.java.test.smpPageObjects.iOSCommonObjects;
import main.java.test.smpUtilityFunctions.AppiumManager;
import main.java.test.smpUtilityFunctions.PortFactory;

public class iOSDRMTest {
	
	public iOSDRMPageObjects iosdrmpageobjects;
	public CommonObjects commonobjects;

	AppiumManager appiummanager = new AppiumManager();

	iOSCommonFunctions ioscommonfunction = new iOSCommonFunctions();
	
	CommonFunction smpcommonfunctions = new CommonFunction();
	
	DRMCommomFunctions drmcommonfunction = new DRMCommomFunctions();

	PortFactory portFactory = new PortFactory();

	DesiredCapabilities capabilities;

	IOSDriver<WebElement> iosdriver;
	
	iOSCommonObjects iospageobjects;
	
	String playbackduration_beforeseek;
	
	String playbackduration_afterseek;

	public String deviceName=null;
	public String deviceOS=null;
	public String deviceUDID=null;
	
	public DesiredCapabilities capa;
	String message;

	public WebDriverWait wait;


	File file;// = new File(absoluteFilePath);

	public String filename;
	public String workingDirectory;  
	public String absoluteFilePath;
	public String ScreenshotPath;    //"/../Automation/Results/iOSDRM";
	File screenhotfiles;
	

	@BeforeClass
	public void getDeviceDetails() throws Exception
	{
		deviceName = ioscommonfunction.retrunDeviceInfo("DeviceName");
		deviceOS = ioscommonfunction.retrunDeviceInfo("ProductVersion");
		deviceUDID = ioscommonfunction.retrunDeviceInfo("UniqueDeviceID");
	    System.out.println("The Device Name is :-"+  deviceName);
	    System.out.println("The Device OS version is :-"+  deviceOS);
	    System.out.println("The Device UDID is :-"+  deviceUDID);
	    
	    Setup(deviceName,deviceUDID,deviceOS);
	
	}
	
	public void Setup(String dName,String dUDID, String dOS) throws Exception
	{
		appiummanager.startAppium(4723);
		appiummanager.AppiumURL();
		String appiul_url = appiummanager.AppiumURL();
		System.out.println("Appium Service Address : - " + appiul_url);

		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.6");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, dName);
		capabilities.setCapability(MobileCapabilityType.UDID, dUDID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, dOS);
		capabilities.setCapability(MobileCapabilityType.APP,
				"/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/DRM_iOS/DRMSampleApp.ipa");
		capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
		capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, false);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");//"XCUITest");
		capabilities.setCapability("useNewWDA", true);
		capabilities.setCapability("wdaLaunchTimeout", 3000);
		capabilities.setCapability("wdaLocalPort", 8100);

		try {

			iosdriver = new IOSDriver<>(new URL(appiul_url), capabilities);
			iosdriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void openDRMApp() throws Exception {
		try {


			iosdrmpageobjects = new iOSDRMPageObjects();
			PageFactory.initElements(new AppiumFieldDecorator(iosdriver), iosdrmpageobjects);
			
			iospageobjects = new iOSCommonObjects();
			PageFactory.initElements(new AppiumFieldDecorator(iosdriver), iospageobjects);
			
			
			commonobjects = new CommonObjects();
			PageFactory.initElements(new AppiumFieldDecorator(iosdriver), commonobjects);
			
			 try
				{
				filename = "iOS_DRM";
				workingDirectory =  drmcommonfunction.ResultFolder(iosdrmpageobjects.ParentDirectoy);  
				absoluteFilePath = workingDirectory + File.separator + filename;
				ScreenshotPath =  workingDirectory+File.separator+drmcommonfunction.ResultFolder(iosdrmpageobjects.SubDirectory);    //"/../Automation/Results/iOSDRM";
			//	screenhotfiles = new File(ScreenshotPath);
				
				drmcommonfunction.CreateReport(absoluteFilePath, deviceUDID, "4723",
						deviceOS,
						deviceName);
				}catch(NullPointerException e)
				{
					e.printStackTrace();
				}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/*
	 * Adding  the VPID's to download, Two Video VPID's are added
	 * 
	 */
	
	@Test(dependsOnMethods={"openDRMApp"})
	public void vpid_Download() throws Exception
	{
		
		for(int i=0;i<iosdrmpageobjects.vpids.length;i++)
		{
			
			drmcommonfunction.tapAccessabilityButton("Download Button", iosdriver, "Add", ScreenshotPath);		
		Thread.sleep(1000);
		
		drmcommonfunction.tapAccessabilityButton("Clear Default VPID ", iosdriver, "Clear text", ScreenshotPath);
		Thread.sleep(600);
		
		drmcommonfunction.enterText(iosdrmpageobjects.enterVPIDField, "Entering the VPID's", iosdrmpageobjects.vpids[i], iosdriver, ScreenshotPath);
		
		drmcommonfunction.tapAccessabilityButton("Downloading ", iosdriver, "Download", ScreenshotPath);
		
		drmcommonfunction.isAccessabilityElementPresent(iosdriver, "Waiting for Media Selector");
		Thread.sleep(4000);
		}
		Thread.sleep(10000);
	}
	
	
	/*
	 * Checking Pause and Resume Functionality
	 * Asserting the download status
	 * 
	 */
	
	@Test(dependsOnMethods={"vpid_Download"})
	public void Download_Pause() throws Exception, ArrayIndexOutOfBoundsException
	{
		//Thread.sleep(4000);
	    if(iosdrmpageobjects.iOSDownload_Pause.isDisplayed())
	    {
	    	drmcommonfunction.tapAccessabilityButton("Pause Download",iosdriver,"Pause",ScreenshotPath);
		Assert.assertEquals("Resume", iosdriver.findElementByAccessibilityId("Resume").getText(), "Text Matched");
	    }
		
	//	Assert.assertEquals("Paused", iosdriver.findElementByAccessibilityId("Pause").getText(), "Text Matched");
		Assert.assertEquals("Paused", iosdrmpageobjects.Download_Status1.getText(), "Text Matched");
		Assert.assertEquals("Paused", iosdrmpageobjects.Download_Status2.getText(), "Text Matched");
		
		drmcommonfunction.tapAccessabilityButton("Resume Download",iosdriver,"Resume",ScreenshotPath);
		Assert.assertEquals("Pause", iosdriver.findElementByAccessibilityId("Pause").getText(), "Text Matched");
		
		Assert.assertNotEquals("Paused",iosdrmpageobjects.Download_Status1.getText(), "Text Doesn't Matched");
		Assert.assertNotEquals("Paused",iosdrmpageobjects.Download_Status1.getText(), "Text Doesn't Matched");		
	}
	
	
	/*
	 * Checking the VPID's MetaData
	 * 
	 */
	
	@Test(dependsOnMethods={"Download_Pause"})
	public void check_Vpid() throws Exception, ArrayIndexOutOfBoundsException
	{
		try
		{
		for(int i=0;i<iosdrmpageobjects.vpids.length;i++)
		{
			drmcommonfunction.isAccessabilityElementPresent(iosdriver, iosdrmpageobjects.vpids[i]);
		}
		for(int i=0;i<iosdrmpageobjects.mediatype.length;i++)
		{
			drmcommonfunction.isAccessabilityElementPresent(iosdriver, iosdrmpageobjects.mediatype[i]);
			Assert.assertEquals(iosdrmpageobjects.mediatype[i], iosdriver.findElementByAccessibilityId(iosdrmpageobjects.mediatype[i]).getText());
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/*@Test(dependsOnMethods={"check_Vpid"})
	public void turnWiFi_Off() throws Exception
	{
		
		ioscommonfunction.turnWifiON("Turning ON WiFi Connection", iosdriver, "Wi-Fi",
				ScreenshotPath, "Wifi Off",deviceOS);
		
		Thread.sleep(1000);
	}
	
	@Test(dependsOnMethods={"turnWiFi_Off"})
	public void turnWiFi_ON() throws Exception
	{

		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, "Wi-Fi",
				ScreenshotPath, "Wifi Off",deviceOS);
			
	}*/
	
	/*
	 * Checking all the VPID's are download progress 
	 * 
	 */
	
	@Test(dependsOnMethods={"check_Vpid"})
	public void DownloadProgress() throws Exception
	{
		
		isElementPresent(iosdrmpageobjects.iOSDownload_Complete,80);
		
		String downloadingstatus = iosdriver.findElementByAccessibilityId("Download Complete").getText();
		
		System.out.println("After Download Completes "+downloadingstatus);

		
	}
	
	/*
	 * Checking all the VPID's are downloaded successfully
	 * 
	 */
	
	@Test(dependsOnMethods={"DownloadProgress"})
	public void downloads_VPIDS_Completed() throws Exception
	{
		isElementPresent(iosdrmpageobjects.iOSDownload_SecondComplete,40);
		
		for(int i=2;i<6;i++)
		{
				
		    WebElement Downloaded_text= iosdriver.findElement(By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText["+i+"]"));	
		    System.out.println("The First Downloaded VPID ----"+ Downloaded_text.getText());
		}
		for(int j=2;j<6;j++)
		{
		for(int i=2;i<3;i++)
		{		
		    WebElement Downloaded_text= iosdriver.findElement(By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell["+i+"]/XCUIElementTypeStaticText["+j+"]"));	
		    System.out.println("The Second Downloaded VPID ----"+ Downloaded_text.getText());
		}
		}
		
	}
	
	/*
	 * Turn Off WiFi 
	 * Playing the Downloading VPID's offline and Seeking Forward
	 * Turn ON Wifi
	 */
	
	@Test(dependsOnMethods = { "downloads_VPIDS_Completed" })
	public void Playback_offline() throws Exception
	{
		drmcommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, "Wi-Fi",
				ScreenshotPath, "Wifi Off",deviceOS);
		
		drmcommonfunction.tapbutton(iosdrmpageobjects.iOSDownloadFirst_Complete, "Tapping on Downlaoded COntent", iosdriver, ScreenshotPath);
		
		drmcommonfunction.swipe_seekbar(iosdrmpageobjects.playbackseekbar, iosdriver, "Forward" ,300);
		
		drmcommonfunction.tapAccessabilityButton("Playing Downlaoded", iosdriver, "Play Video", ScreenshotPath);
		
		playbackduration_beforeseek = iosdrmpageobjects.playbackduration.getText();
		System.out.println("Duration when playback Started:- "+ playbackduration_beforeseek);
		
		Thread.sleep(10000);
		
		iospageobjects.playback_transportcontrol.click();
		Thread.sleep(50);
		
		drmcommonfunction.swipe_seekbar(iosdrmpageobjects.playbackseekbar, iosdriver, "backward" ,200);
		
		playbackduration_afterseek = iosdrmpageobjects.playbackduration.getText();
		System.out.println("Duration After playback seeked:- "+ playbackduration_afterseek);
		
		drmcommonfunction.tapAccessabilityButton("Playing Downlaoded", iosdriver, "smp_close_button", ScreenshotPath);
		
		Assert.assertNotEquals(playbackduration_beforeseek,playbackduration_afterseek, "Duration Doesn't Matched");	
		
		drmcommonfunction.tapbutton(iosdrmpageobjects.iOSDownloadSecond_Complete, "Tapping on Downloaded Content", iosdriver, ScreenshotPath);
		
		drmcommonfunction.tapAccessabilityButton("Playing Downlaoded", iosdriver, "Play Video", ScreenshotPath);
		
		drmcommonfunction.swipe_seekbar(iosdrmpageobjects.playbackseekbar, iosdriver, "Forward" ,300);
		
		playbackduration_beforeseek = iosdrmpageobjects.playbackduration.getText();
		System.out.println("Duration when playback Started:- "+ playbackduration_beforeseek);
		
		Thread.sleep(10000);
		
		iospageobjects.playback_transportcontrol.click();
		Thread.sleep(50);
		
		drmcommonfunction.swipe_seekbar(iosdrmpageobjects.playbackseekbar, iosdriver, "Forward" ,700);
		
		playbackduration_afterseek = iosdrmpageobjects.playbackduration.getText();
		System.out.println("Duration After playback seeked:- "+ playbackduration_afterseek);
		
		//drmcommonfunction.swipe(iosdrmpageobjects.seekbar, iosdriver);
		
		drmcommonfunction.tapAccessabilityButton("Playing Downlaoded", iosdriver, "smp_close_button", ScreenshotPath);
		
		Assert.assertNotEquals(playbackduration_beforeseek,playbackduration_afterseek, "Duration Doesn't Matched");
		
		/*
		 * Deleting the downloaded VPID's one after the other
		 */
		for(int i=0;i<iosdrmpageobjects.vpids.length;i++)
		{
			if(iosdrmpageobjects.iOSDownloadFirst_Complete.isDisplayed())
			{
				drmcommonfunction.swipe(iosdrmpageobjects.iOSDownloadFirst_Complete, iosdriver);
				drmcommonfunction.tapAccessabilityButton("Playing Downlaoded", iosdriver, "Remove", ScreenshotPath);
			}else
			{
				break;
			}
		}
	/*	drmcommonfunction.swipe(iosdrmpageobjects.iOSDownloadFirst_Complete, iosdriver);
		ioscommonfunction.tapAccessabilityButton("Playing Downlaoded", iosdriver, "Remove", ScreenshotPath);
		
		drmcommonfunction.swipe(iosdrmpageobjects.iOSDownloadFirst_Complete, iosdriver);
		ioscommonfunction.tapAccessabilityButton("Playing Downlaoded", iosdriver, "Remove", ScreenshotPath);*/
		
		
		drmcommonfunction.turnWifiON("Turning ON WiFi Connection", iosdriver, "Wi-Fi",
				ScreenshotPath, "Wifi Off",deviceOS);
		
	}
	
	/*
	 * Adding Multipel Vpid's And
	 * Deleting all the VPID's which are In-progress, Queued
	 * 
	 */
	
	@Test(dependsOnMethods={"Playback_offline"})
	public void deleteAllQueued() throws Exception
	{
		
		for(int i=0;i<iosdrmpageobjects.vpids_list.length;i++)
		{
			
			drmcommonfunction.tapAccessabilityButton("Download Button", iosdriver, "Add", ScreenshotPath);		
		Thread.sleep(1000);
		
		drmcommonfunction.tapAccessabilityButton("Clear Default VPID ", iosdriver, "Clear text", ScreenshotPath);
		Thread.sleep(600);
		
		drmcommonfunction.enterText(iosdrmpageobjects.enterVPIDField, "Entering the VPID's", iosdrmpageobjects.vpids_list[i], iosdriver, ScreenshotPath);
		
		drmcommonfunction.tapAccessabilityButton("Downloading ", iosdriver, "Download", ScreenshotPath);
		Thread.sleep(2000);
		drmcommonfunction.isAccessabilityElementPresent(iosdriver, "Waiting for Media Selector");
		Thread.sleep(6000);
		}
		
		for(int i=0;i<iosdrmpageobjects.vpids_list.length;i++)
		{
			if(iosdrmpageobjects.downloadInProgress_button.isDisplayed())
			{
				drmcommonfunction.swipe(iosdrmpageobjects.downloadInProgress_button, iosdriver);
				drmcommonfunction.tapAccessabilityButton("Playing Downlaoded", iosdriver, "Remove", ScreenshotPath);	
			}else
			{
				break;
			}
			
		}
		
	}
	
	@AfterClass
	public void tearDown()
	{
		drmcommonfunction.GenerateReport();
		iosdriver.quit();
		iosdriver.resetApp();
		iosdriver.closeApp();
		appiummanager.stopappium();
	}
	
	
	
	
	public void waitForScreenToLoad(IOSDriver<WebElement> lDriver, WebElement element, int seconds)
	{

	    WebDriverWait wait = new WebDriverWait(lDriver,seconds);
	    wait.until(ExpectedConditions.visibilityOf(element));
	   
	}
	
	
	public boolean isElementPresent(WebElement elementName, int timeout){
        try{
            WebDriverWait wait = new WebDriverWait(iosdriver, timeout);
            wait.until(ExpectedConditions.visibilityOf(elementName));
//            if(elementName.isDisplayed())
//            {
//            	elementName.click();
//            }
            return true;
           
        }catch(Exception e){
            return false;
        }
    }

}
