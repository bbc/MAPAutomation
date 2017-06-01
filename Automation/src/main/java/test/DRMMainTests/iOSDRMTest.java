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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
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


	
	public DesiredCapabilities capa;
	String message;

	public WebDriverWait wait;

	String filename = "iOSDRM";
	String workingDirectory = "/Users/ramakh01/Desktop/MAPAutomation/Automation/Results"; /// System.getProperty("user.dir");
	String absoluteFilePath = workingDirectory + File.separator + filename;
	public String ScreenshotPath = "/Users/ramakh01/Desktop/MAPAutomation/Automation/Results/iOSDRM/";
	File screenhotfiles = new File(ScreenshotPath);

	File file;// = new File(absoluteFilePath);

	AppiumManager appiummanager = new AppiumManager();

	iOSCommonFunctions ioscommonfunction = new iOSCommonFunctions();
	
	CommonFunction smpcommonfunctions = new CommonFunction();
	
	DRMCommomFunctions drmcommonfunction = new DRMCommomFunctions();

	PortFactory portFactory = new PortFactory();

	DesiredCapabilities capabilities;

	IOSDriver<WebElement> iosdriver;
	
	iOSCommonObjects iospageobjects;

	public String deviceName=null;
	public String deviceOS=null;
	public String deviceUDID=null;

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
				"/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/DRM_iOS/DRMTestApp.ipa");
		capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
		capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, false);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");//"XCUITest");
		//capabilities.setCapability("useNewWDA", true);
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

			ioscommonfunction.CreateReport(absoluteFilePath, deviceUDID, "4723",
					deviceOS,
					deviceName);

			


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods={"openDRMApp"})
	public void vpid_Download() throws Exception
	{
		
		iosdriver.findElementByAccessibilityId("Add").click();
		Thread.sleep(3000);
		
		iosdriver.findElementByAccessibilityId("Clear text").click();
		Thread.sleep(3000);
		
		WebElement VPID_Field = iosdriver.findElement(By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeTextField"));
		VPID_Field.sendKeys("b08th1rx");
		
		iosdriver.findElementByAccessibilityId("Download").click();
		Thread.sleep(3000);
		

		
		for(int i=0;i<iosdrmpageobjects.vpids.length;i++)
		{
		iosdrmpageobjects.addDownload_Button.click();
		Thread.sleep(1000);
		
		iosdrmpageobjects.vpidClearButton.click();
		Thread.sleep(600);
		
		ioscommonfunction.enterText(iosdrmpageobjects.enterVPIDField, "Entering the VPID's", iosdrmpageobjects.vpids[i], iosdriver, ScreenshotPath);
		
		ioscommonfunction.tapbutton(iosdrmpageobjects.downloadButton, "Adding a VPID's to Download",iosdriver, ScreenshotPath);
		}
		
//		smpcommonfunctions.tapbutton("Adding a Audio VPID to Download", iosdrmpageobjects.downloadButton, iosdriver, ScreenshotPath);
		
	}
	
	@Test(dependsOnMethods={"vpid_Download"})
	public void check_Vpid() throws Exception, ArrayIndexOutOfBoundsException
	
	{
		
//		drmcommonfunction.swipe(iosdrmpageobjects.downloadcomplete_button, iosdriver);		
		
		WebElement elements[] = {
				//iosdrmpageobjects.downloadingStatus,
				iosdrmpageobjects.downloadingVPID,
				//iosdrmpageobjects.downloadingVPID_FileSize,
				iosdrmpageobjects.downloadingVPID_Expiry,
				iosdrmpageobjects.downloadingVPID_MediaType
			};

		try
		{
				for(int i1=0;i1<elements.length;i1++)
			{
							System.out.println("Downloading "+ elements[i1].getText());
							Assert.assertEquals(elements[i1].getText(), elements[i1].getText());
							//Assert.assertNotEquals(elements[i1].getText(), elements[i1].getText());
						//	logger.log(LogStatus.PASS, elements[i1]);
					
			}	
		
		Thread.sleep(2000);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
//	@Test(dependsOnMethods={"check_Vpid"})
//	public void turnWiFi_Off() throws Exception
//	{
//		//
//		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, iospageobjects.wifi_mode,
//				iospageobjects.dismiss_wholewindow,ScreenshotPath, "Download Status After Network OFF "+ iosdrmpageobjects.progressStatus.getText());
//		
//		System.out.println("Download Status After Network OFF:- "+ iosdrmpageobjects.downloadingStatus.getText());
//		AssertJUnit.assertEquals("Paused", iosdrmpageobjects.downloadingStatus.getText());
//		Thread.sleep(3000);
//	}
//	
//	@Test(dependsOnMethods={"turnWiFi_Off"})
//	public void turnWiFi_ON() throws Exception
//	{
//		ioscommonfunction.turnWifiON("Turning ON WiFi Connection", iosdriver, iospageobjects.wifi_mode,
//				iospageobjects.dismiss_wholewindow,ScreenshotPath, "Download Status After Network ON"+ iosdrmpageobjects.progressStatus.getText());
//		
//		System.out.println("Download Status After Network ON:- "+ iosdrmpageobjects.downloadingStatus.getText());
//	//	AssertJUnit.assertEquals(iosdrmpageobjects.downloadingStatus.getText(), iosdrmpageobjects.downloadingStatus.getText());
//		String inprogressText = iosdrmpageobjects.downloadingStatus.getText();
//		AssertJUnit.assertEquals("In Progress",inprogressText.substring(0, 11));
//		Thread.sleep(3000);
//	}
	
//	@Test(dependsOnMethods={"check_Vpid"})
//	public void downloadPause() throws Exception
//	{
//		ioscommonfunction.tapbutton(iosdrmpageobjects.pause_resume_button, "Pausing the Download", iosdriver, ScreenshotPath);
//		
//		
//	}
//	
//	@Test(dependsOnMethods={"downloadPause"})
//	public void downloadResume() throws Exception
//	{
//		ioscommonfunction.tapbutton(iosdrmpageobjects.pause_resume_button, "Resume the Download", iosdriver, ScreenshotPath);
//		
//	}
	
	
	@Test(dependsOnMethods={"check_Vpid"})
	public void DownloadProgress() throws Exception
	{
		
		
	//	WebElement progress_status = iosdriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAProgressIndicator[1]"));
		String downloadingstatus = iosdrmpageobjects.progressStatus.getText();
		
		System.out.println("Before Download Completes"+downloadingstatus);
		
		waitForScreenToLoad(iosdriver, iosdrmpageobjects.downloadcomplete, 520);
		
		String downloadingstatus1 = iosdrmpageobjects.downloadcomplete.getText();
		
		System.out.println("After Download Completes "+downloadingstatus1);
		
	}
	
	
//	@Test(dependsOnMethods={"DownloadProgress"})
//	public void DownloadComplete() throws Exception
//	{
//		{
//			WebElement elements[] = {
//					iosdrmpageobjects.downloadingStatus,
//					iosdrmpageobjects.downloadingVPID,
//					iosdrmpageobjects.downloadingVPID_FileSize,
//					iosdrmpageobjects.downloadingVPID_Expiry,
//					iosdrmpageobjects.downloadingVPID_MediaType
//				};
//
//			try
//			{
//					for(int i1=0;i1<elements.length;i1++)
//				{
//								System.out.println("Downloaded "+ elements[i1].getText());
//								Assert.assertEquals(elements[i1].getText(), elements[i1].getText(), "The Values Matched");
//							//	logger.log(LogStatus.PASS, elements[i1]);
//						
//				}	
//			
//			}catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//		
//		Thread.sleep(1000);
//		}
	
	@Test(dependsOnMethods={"DownloadProgress"})
	public void downloads_VPIDS_Completed() throws Exception
	{
		for(int i=1;i<6;i++)
		{
			for(int j=1;j<3;j++)
			{	
		    String Downloaded_text = iosdriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell["+j+"]/UIAStaticText["+i+"]")).getText();
		    Assert.assertEquals(Downloaded_text, Downloaded_text, "The Values Matched");
		    System.out.println("The Downloaded VPID ----"+ Downloaded_text);
		    
		}
		}
		
	}
	
	@Test(dependsOnMethods = { "downloads_VPIDS_Completed" })
	public void Playbackoffline() throws Exception {
		//
		/*ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, iospageobjects.wifi_mode,
				iospageobjects.dismiss_wholewindow, ScreenshotPath,
				"Download Status After Network OFF " + iosdrmpageobjects.progressStatus.getText());*/
		
		ioscommonfunction.turnWifiON("Turning Off WiFi Connection", iosdriver, "Wi-Fi",
				ScreenshotPath, "Wifi Off",deviceOS);
		
		Thread.sleep(2000);

		// drmcommonfunction.swipe(iosdrmpageobjects.downloadcomplete_button,
		// iosdriver);

		ioscommonfunction.tapbutton(iosdrmpageobjects.audio_downloadcomplete_button, "Clicking on Downloaded Audio",
				iosdriver, ScreenshotPath);

		ioscommonfunction.tapbutton(iosdrmpageobjects.play_pause_button, "tapping Play button", iosdriver,
				ScreenshotPath);

		Assert.assertTrue(ioscommonfunction.isElementPresent(iosdriver,
				By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[5]")));

		Assert.assertTrue(ioscommonfunction.isElementPresent(iosdriver,
				By.xpath("//UIAApplication[1]/UIAWindow[1]/UIASlider[1]")));

		Assert.assertTrue(ioscommonfunction.isElementPresent(iosdriver,
				By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAElement[4]")));

		ioscommonfunction.seekingRandomly(iosdrmpageobjects.playback_progressbar, iosdriver, ScreenshotPath, 0.30);
		String seekingduration_30 = iosdrmpageobjects.playbackduration.getText();

		System.out.println("seekingduration_30 is" + seekingduration_30);

		ioscommonfunction.seekingRandomly(iosdrmpageobjects.playback_progressbar, iosdriver, ScreenshotPath, 0.60);
		String seekingduration_60 = iosdrmpageobjects.playbackduration.getText();

		System.out.println("seekingduration_60 is" + seekingduration_60);

		ioscommonfunction.seekingRandomly(iosdrmpageobjects.playback_progressbar, iosdriver, ScreenshotPath, 0.90);
		String seekingduration_90 = iosdrmpageobjects.playbackduration.getText();

		System.out.println("seekingduration_90 is" + seekingduration_90);

		Thread.sleep(6000);

		ioscommonfunction.tapbutton(iosdrmpageobjects.playbackClose, "Closing the Playback", iosdriver, ScreenshotPath);

	}
	
	@Test(dependsOnMethods = { "Playbackoffline" })
	public void Delete_Downloaded() throws Exception
	{
		Thread.sleep(3000);
		drmcommonfunction.swipe(iosdrmpageobjects.audio_downloadcomplete_button, iosdriver);
		ioscommonfunction.tapbutton(iosdrmpageobjects.remove_downloaded_button, "Deleting the Downloaded Audio VPID", iosdriver, ScreenshotPath);
		
		
		Thread.sleep(3000);
		drmcommonfunction.swipe(iosdrmpageobjects.audio_downloadcomplete_button, iosdriver);
		ioscommonfunction.tapbutton(iosdrmpageobjects.remove_downloaded_button, "Deleting the Downloaded Vido VPID", iosdriver, ScreenshotPath);

	
	}
	
	
	
	
	@AfterClass
	public void tearDown()
	{
		ioscommonfunction.GenerateReport();
		iosdriver.quit();
		iosdriver.resetApp();
		appiummanager.stopappium();
	}
	
	
	
	
	public void waitForScreenToLoad(IOSDriver<WebElement> lDriver, WebElement element, int seconds)
	{

	    WebDriverWait wait = new WebDriverWait(lDriver,seconds);
	    wait.until(ExpectedConditions.visibilityOf(element));
	   
	}

}
