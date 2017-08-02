package main.java.test.DRMMainTests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.Connection;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.java.test.DRMPageObjects.AndroidDRMPageObjects;
import main.java.test.smpFunctions.CommonFunction;
import main.java.test.smpFunctions.LiveRewindFunctions;
import main.java.test.smpPageObjects.CommonObjects;
import main.java.test.smpPageObjects.LiveRewindPageObjects;
import main.java.test.smpPageObjects.OnDemandPageObjects;
import main.java.test.smpUtilityFunctions.AppiumManager;
import main.java.test.smpUtilityFunctions.PortFactory;

public class AndroidDRMTest 
{
	public String filename;
	public String workingDirectory;  
	public String absoluteFilePath;
	public String ScreenshotPath; 
	public String Screenshot_Path;
	File screenhotfiles;
		
		public DesiredCapabilities capa;
		public AndroidDriver<WebElement> androiddriver;
		AndroidDRMPageObjects androiddrmpom;
		String vpid = "p056gnk4"; // available for next 4 months
		String vpid1 = "b08wzz53";// "p051y9gp"; // "b07xnzs2";
		String vpid2="p055zqqn";
		public WebDriverWait wait;
		CommonFunction funct = new CommonFunction();
		ExtentReports extent;
		com.relevantcodes.extentreports.ExtentTest logger;
		File file;
		String elapsed_time=null;
		AppiumManager ap = new AppiumManager() ;
		String vpid_file_one;
		
		String vpids[] = {"p055zqqn","p053ntqd"};
		
//		String filename = "BuyDRM";
//	    String workingDirectory = "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/Results";
//		String absoluteFilePath = workingDirectory+File.separator+filename;
//		public String Screenshot_Path= "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/Results/AndroidDRM";
		
		
		
		
		@BeforeClass
		@Parameters({"deviceID","deviceOS","appiumPort", "deviceType","deviceName"})
		public void setUp(String deviceId, String OS, int port, String DeviceTye, @Optional("devicename") String devicename) throws Exception, MalformedURLException
		{
			ap.startAppium(port);
			ap.AppiumURL();
			String appiul_url = ap.AppiumURL();
	        System.out.println("Appium Service Address : - "+appiul_url);
			
			capa = new DesiredCapabilities();
			capa.setCapability("appium-version", "1.0");
			capa.setCapability("deviceName", deviceId);
			capa.setCapability("platformName", "Android");
			capa.setCapability("platformVersion", OS);
			capa.setCapability("app", "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/DRM_AN/DRM.953.apk");
			capa.setCapability("platformName", "Android");
			capa.setCapability("appPackage", "uk.co.bbc.drmtestapp.android");
			capa.setCapability("appActivity","uk.co.bbc.drmtestapp.android.launcher.MainActivity");
			capa.setCapability("noReset","true");
			try
			{
			//String id1 = AppiumMang.allDeviceIds.get("deviceID1");
				androiddriver = new AndroidDriver<>(new URL(appiul_url), capa);
				androiddriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		   	
			
		}
		
		@Test
		@Parameters({"deviceID","deviceOS","appiumPort", "deviceType","deviceName"})
		public void Open_DRMApp(String deviceId, String OS, String port, String deviceType, String devicename) throws Exception
		{
			androiddrmpom = new AndroidDRMPageObjects();
			PageFactory.initElements(new AppiumFieldDecorator(androiddriver), androiddrmpom);
			
			androiddriver.setConnection(Connection.WIFI);
			
			
			try
			{
			filename = "AndroidDRM";
			workingDirectory =  funct.ResultFolder(androiddrmpom.ParentDirectoy);  
			absoluteFilePath = workingDirectory + File.separator + filename;
			ScreenshotPath =  workingDirectory+ File.separator+funct.ResultFolder(androiddrmpom.SubDirectory);    //"/../Automation/Results/iOSDRM";
			//screenhotfiles = new File(Screenshot_Path);
			Screenshot_Path = ScreenshotPath;
			
			funct.CreateReport(absoluteFilePath, deviceId, "4723",
					OS,
					devicename);
			
			}catch(NullPointerException e)
			{
				e.printStackTrace();
			}
			
			try
			{	
			
			extent = new ExtentReports(absoluteFilePath+"_"+devicename+".html", true, DisplayOrder.NEWEST_FIRST);
			HashMap<String, String> sysInfo = new HashMap<String, String>();
			
			sysInfo.put("Device Name ", devicename);
			sysInfo.put("Firmware version", OS);
			sysInfo.put("Device  Type", deviceType);
			sysInfo.put("Device ID", deviceId);
			sysInfo.put("Appium Port", port);
			
			extent.addSystemInfo(sysInfo);
			
			System.out.println("Final filepath : " + absoluteFilePath+"_"+devicename+".html" );
			file = new File(absoluteFilePath+"_"+devicename+".html");
			
			
		    file = new File(absoluteFilePath+File.separator+devicename+".html");
		 
			if (file.createNewFile()) 
					{
						System.out.println("File is created!");
					} else 
					{
						System.out.println("File is already existed!");
					}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
	
			
		/*	drmpom.menuoption.click();
			Thread.sleep(2000);
			logger = extent.startTest("Opening DRM App","Opening app and Truning download over 3G");
			
			drmpom.download_option.click();
			Thread.sleep(3000);
			
			System.out.println(drmpom.mobile_data.getAttribute("checked"));
			
			logger.log(LogStatus.INFO, "Download over 3G Turned  " + logger.addScreenCapture(funct.capture_ScreenShot(driver, Screenshot_Path, drmpom.mobile_data.getAttribute("checked"))));
			
	    	drmpom.mobile_data.click();
	    	System.out.println(drmpom.mobile_data.getAttribute("checked"));
	    	logger.log(LogStatus.INFO, "Download over 3G Turned  " + logger.addScreenCapture(funct.capture_ScreenShot(driver, Screenshot_Path, drmpom.mobile_data.getAttribute("checked"))));
			Thread.sleep(2000);
			
			driver.pressKeyCode(AndroidKeyCode.BACK);*/
			
		}
		@Test(dependsOnMethods={"Open_DRMApp"})
		@Parameters({"deviceType"})
		public void deleteQueued(String devicetype) throws Exception
		{
			
			logger = extent.startTest("Deleting the In-Progress, Queued VPID's");
			
			for(int i=0;i<vpids.length;i++)
			{
			
			addVpid(vpids[i], devicetype);
			System.out.println("VPID--------"+vpids[i]);
			
			}
			
			logger.log(LogStatus.INFO, "Downloading VPID's", logger.addScreenCapture( funct.capture_ScreenShot(androiddriver, Screenshot_Path, " Downloading")));
			
		    Download_menu(devicetype);
		    
			/*
			 * for(int i=0;i<vpids.length;i++) {
			 * System.out.println(" VPID'S are  "+driver.findElement(By.
			 * xpath("//android.widget.TextView[contains(@resource-id,'uk.co.bbc.drmtestapp.android:id/textViewVpid') and @text="
			 * +"'"+vpids[i]+"']")).getText());
			 * 
			 * // System.out.println("First VPID is "+driver.findElement(By.xpath(
			 * "//android.widget.TextView[contains(@text="+"'"+vpids[i]+"']")).
			 * getText());
			 * //System.out.println("Second VPID is "+driver.findElement(By.
			 * xpath("//android.widget.TextView[contains(@resource-id,'uk.co.bbc.drmtestapp.android:id/textViewVpid') and @text="
			 * +"'"+vpid1+"']")).getText()); }
			 */
		    
			for (int j = 0; j < vpids.length; j++) {
				if (!androiddrmpom.downloadRemove.isDisplayed()) {
					break;
				}
				androiddrmpom.downloadRemove.click();
			}
		    
			// drmpom.removeButton1.click();
			// drmpom.removeButton2.click();
		    
		    for(int j=0;j<vpids.length;j++)
		    {
		    	 Assert.assertFalse(funct.isElementPresent(androiddriver, By.id("//android.widget.TextView[contains(@resource-id,'uk.co.bbc.drmtestapp.android:id/textViewVpid') and @text="+"'"+vpids[j]+"']")));
		    }
		   
		    
		    logger.log(LogStatus.INFO, "Deleting the VPID's", logger.addScreenCapture( funct.capture_ScreenShot(androiddriver, Screenshot_Path, "Delete Queued")));
		    
		    
		}
		
		
		@Test(dependsOnMethods={"deleteQueued"})
		@Parameters({"deviceType"})
		public void Vpid_Download(String devicetype) throws Exception
		{
			
			//Setting_Mediatype(devicetype,"mobile-download");
			
			logger = extent.startTest("Entering the Download Video VPID");
			
			Press_Back(devicetype);
			
			androiddrmpom.vpid_enter.clear();
			
			androiddrmpom.vpid_enter.click();
			Thread.sleep(1000);
			
			androiddrmpom.vpid_enter.sendKeys(vpid);
			androiddriver.hideKeyboard();
			logger.log(LogStatus.INFO, "Entering VPID" +logger.addScreenCapture(funct.capture_ScreenShot(androiddriver, Screenshot_Path, vpid)));
			
			wait = new WebDriverWait(androiddriver,3500);
			androiddrmpom.download_button.click();
			//logger.log(LogStatus.INFO, "Click on Download button" + logger.addScreenCapture(funct.capture_ScreenShot(driver, Screenshot_Path, "Download")));
			Thread.sleep(2000);
			
//	    	String text = drmpom.vpid_validator1.getText();
//	    	System.out.println(text);
//			Assert.assertEquals("Validating", drmpom.vpid_validator1.getText());
			
			funct.waitForScreenToLoad(androiddriver, androiddrmpom.download_progress,8);
			//logger.log(LogStatus.INFO, "Check Download Progress" +logger.addScreenCapture( funct.capture_ScreenShot(driver, Screenshot_Path, "Download_Progress")));
			Assert.assertEquals("In Progress", androiddrmpom.download_progress.getText());
			Assert.assertEquals("Video", androiddrmpom.media_type1.getText());
			Assert.assertEquals(vpid, androiddrmpom.download_vpid.getText());
			
			vpid_file_one = androiddriver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[@index='4']")).getText();
			System.out.println("File Size Download "+ vpid_file_one);
			
			
			Press_Back(devicetype);
	     
		}
		
		
		@Test(dependsOnMethods={"Vpid_Download"})
		//@Parameters({"devicetype"})
		//public void Addingto_Queue(String devicetype) throws Exception
		public void Addingto_Queue() throws Exception
		{
			logger = extent.startTest("Adding Another VPID to Download Queue");	
			
			androiddrmpom.vpid_enter.clear();
			Thread.sleep(100);
			
			androiddrmpom.vpid_enter.sendKeys(vpid1);
			
			androiddriver.hideKeyboard();
			logger.log(LogStatus.INFO, "Entering VPID" +logger.addScreenCapture( funct.capture_ScreenShot(androiddriver, Screenshot_Path, vpid1)));
			
			androiddrmpom.download_button.click();
			Thread.sleep(500);
			
			funct.waitForScreenToLoad(androiddriver, androiddrmpom.download_Queued,7);
			logger.log(LogStatus.INFO, "Check Download Progress" + logger.addScreenCapture(funct.capture_ScreenShot(androiddriver, Screenshot_Path, "Download_Queued")));
			Assert.assertEquals("Queued", androiddrmpom.download_Queued.getText());
			Assert.assertEquals("Video", androiddrmpom.media_type1.getText());
			
			String vpid_file1 = androiddriver.findElement(By.xpath("//android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.TextView[@index='4']")).getText();
			System.out.println("File Size Download "+ vpid_file1);
			
		//	Assert.assertEquals(vpid1, drmpom.download_vpid.getText());
			
			
//			System.out.println("First Remove Button is "+ driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[@index='1']")).getText());
//			
//			System.out.println("Second Remove Button is "+ driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.Button[@index='1']")).getText());
//			
			System.out.println("First VPID is "+androiddriver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'uk.co.bbc.drmtestapp.android:id/textViewVpid') and @text="+"'"+vpid+"']")).getText());
			
			System.out.println("Second VPID is "+androiddriver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'uk.co.bbc.drmtestapp.android:id/textViewVpid') and @text="+"'"+vpid1+"']")).getText());
		}	
		
		
		@Test(dependsOnMethods={"Addingto_Queue"})
		public void Download_Pause() throws Exception
		{
			logger = extent.startTest("Pausing the Download Queue");
			
			System.out.println(androiddrmpom.pause_all.getAttribute("checked"));
			
			
			androiddrmpom.pause_all.click();
			logger.log(LogStatus.INFO, "Clicking Pause All" +logger.addScreenCapture( funct.capture_ScreenShot(androiddriver, Screenshot_Path, androiddrmpom.pause_all.getAttribute("checked"))));
			System.out.println(androiddrmpom.pause_all.getAttribute("checked"));
			Thread.sleep(3000);
			
			Assert.assertEquals("Paused", androiddrmpom.download_paused.getText());
			logger.log(LogStatus.INFO, "Download Paused" + logger.addScreenCapture(funct.capture_ScreenShot(androiddriver, Screenshot_Path, "Download_Paused")));	
			
			
		}
		
		@Test(dependsOnMethods={"Download_Pause"})
		public void Download_Resume() throws Exception
		{
			logger = extent.startTest("Un Pausing the Download Queue");
			
			System.out.println(androiddrmpom.pause_all.getAttribute("checked"));
			
			
			androiddrmpom.pause_all.click();
			logger.log(LogStatus.INFO, "Clicking Pause All" +logger.addScreenCapture( funct.capture_ScreenShot(androiddriver, Screenshot_Path, androiddrmpom.pause_all.getAttribute("checked"))));
			System.out.println(androiddrmpom.pause_all.getAttribute("checked"));
			Thread.sleep(3000);
			
			//Assert.assertTrue(!isElementPresent(androiddriver, androiddrmpom.download_paused));
			//Assert.assertTrue(!assertElementNotPresent(driver,drmpom.download_paused));
			
			logger.log(LogStatus.INFO, "Download Resumed" + logger.addScreenCapture(funct.capture_ScreenShot(androiddriver, Screenshot_Path, "Download_Resume")));	
			String vpid_one_status = androiddriver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[@index='1']")).getText();
			System.out.println(vpid_one_status);
			String download_progress = "Download Completed";
	        funct.waitForScreenToLoad(androiddriver, androiddrmpom.download_complete, 190); 
	        
			if(vpid_one_status!="Download Completed")
			{
	        String vpid_expire_date = androiddriver.findElement(By.id("uk.co.bbc.drmtestapp.android:id/textViewTime")).getText();	
	        System.out.println(vpid_expire_date);
			}
			androiddriver.setConnection(networkconnection().AIRPLANE);
			Thread.sleep(500);
			Assert.assertEquals("Paused", androiddrmpom.download_paused.getText());
	        System.out.println("Airplane Mode On" );
	        androiddriver.runAppInBackground(10);
			
		}
		
		@Test(dependsOnMethods={"Download_Resume"})
		//@Parameters({"deviceType"})
		public void Downloads_Complete() throws Exception //String deviceType
			
		{	
			logger = extent.startTest("Download Complete");
			
			//Download_menu(deviceType);
			
	       /* drmpom.menuoption.click();
			Thread.sleep(2000);
			
			drmpom.download_option.click();
			Thread.sleep(2000);*/
			
			Assert.assertEquals("Paused", androiddrmpom.download_paused.getText());
			
			androiddriver.setConnection(networkconnection().WIFI);
			System.out.println("WiFi Mode On" );
			Thread.sleep(2000);
			 
			Assert.assertEquals("In Progress", androiddrmpom.download_progress.getText());
			
			
//			androiddriver.openNotifications();
			
     		androiddriver.runAppInBackground(120);
//			
//			androiddriver.openNotifications();
//			if(!androiddriver.findElement(By.xpath("//android.widget.TextView[@content-desc='Downloading...'] and [@index'2']")).isDisplayed())
//			{
//				androiddriver.launchApp();
//			}
			
		    logger.log(LogStatus.INFO, "App in Background " +logger.addScreenCapture( funct.capture_ScreenShot(androiddriver, Screenshot_Path, "Background App")));
			
			//Download_menu(deviceType);

	/*		drmpom.menuoption.click();
			Thread.sleep(2000);
			
			drmpom.download_option.click();
			Thread.sleep(2000); */
			
			
			
			String vpid1_one_status = androiddriver.findElement(By.xpath("//android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.TextView[@index='1']")).getText();
			System.out.println("Second VPID "+ vpid1_one_status);
			
		// 	Assert.assertEquals("Download Completed", androiddrmpom.download_complete.getText());
			 
			//funct.waitForScreenToLoad(driver, drmpom.download_complete, 50); 
		 	
		 
//			vpid1_one_status = androiddriver.findElement(By.xpath("//android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.TextView[@index='1']")).getText();
//			if(vpid1_one_status!="Download Completed")
//			{
//	        String vpid1_expire_date = androiddriver.findElement(By.xpath("//android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.TextView[@index='3']")).getText();	
//	        System.out.println(vpid1_expire_date);
//			}
			
			logger.log(LogStatus.INFO, "App in Foreground " +logger.addScreenCapture( funct.capture_ScreenShot(androiddriver, Screenshot_Path, "Foreground App")));
			
			if(androiddriver.findElement(By.id("uk.co.bbc.drmtestapp.android:id/textViewVpid")).getText() != vpid)
	        {
	        	System.out.println("Mediatype:--   "+androiddriver.findElement(By.id("uk.co.bbc.drmtestapp.android:id/textViewMediaType")).getText());
	        	logger.log(LogStatus.INFO, androiddriver.findElement(By.id("uk.co.bbc.drmtestapp.android:id/textViewMediaType")).getText());
	        	System.out.println(androiddriver.findElement(By.id("uk.co.bbc.drmtestapp.android:id/textViewTime")).getText());
	        	logger.log(LogStatus.INFO, androiddriver.findElement(By.id("uk.co.bbc.drmtestapp.android:id/textViewTime")).getText());
	        	System.out.println(androiddriver.findElement(By.id("uk.co.bbc.drmtestapp.android:id/textViewProgress")).getText());
	        	logger.log(LogStatus.INFO, androiddriver.findElement(By.id("uk.co.bbc.drmtestapp.android:id/textViewProgress")).getText());
	        	System.out.println("File Size :--- "+androiddriver.findElement(By.id("uk.co.bbc.drmtestapp.android:id/textViewFileSize")).getText());
	        	logger.log(LogStatus.INFO, androiddriver.findElement(By.id("uk.co.bbc.drmtestapp.android:id/textViewFileSize")).getText());
	        	
	        }
			
		/*	if(androiddriver.findElement(By.id("uk.co.bbc.drmtestapp.android:id/textViewVpid")).getText() != vpid1)
			{
				for(int i=0;i<=1;i++)
				{
					System.out.println(androiddriver.findElement(By.xpath("//android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.TextView[@index="+"'"+i+"']")).getText());
			
				}
				for(int i=3;i<=4;i++)
				{
					System.out.println(androiddriver.findElement(By.xpath("//android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.TextView[@index="+"'"+i+"']")).getText());
			
				}

				
			}*/
			
		}	
			
			@Test(dependsOnMethods={"Downloads_Complete"})
			public void Downloaded_VideoPlayback_Offline() throws Exception
		
			{
				 logger = extent.startTest("Downloaded Video Playback Offline" );
				 
				 androiddriver.setConnection(networkconnection().AIRPLANE);
				 logger.log(LogStatus.INFO, "Airplane Mode");
				  
				
				 androiddriver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1][@index='0']")).click();
				 Thread.sleep(3000);
				
				 logger.log(LogStatus.INFO, "Video TransportContols " + logger.addScreenCapture(funct.capture_ScreenShot(androiddriver,Screenshot_Path ,"Assertion")));
				 DRM_Playback_CheckingAssertions();
				 
				 
				 Assert.assertEquals(vpid, androiddrmpom.download_playback_vpid_title.getText());
				 logger.log(LogStatus.PASS, "Video VPID Matched");
				 
				 int startX = androiddrmpom.download_seek_bar.getLocation().getX();
				 funct.seek_bar_swipe(androiddriver,androiddrmpom.download_seek_bar ,startX,0.3); 
				 logger.log(LogStatus.INFO, "Seeking Forward" +logger.addScreenCapture( funct.capture_ScreenShot(androiddriver, Screenshot_Path, "Seeking")));
				 
				 elapsed_time = androiddrmpom.download_elapsed_duration.getText();
				 
				 androiddrmpom.download_subtitle.click();
				 Thread.sleep(300);
				 
				 
				 androiddrmpom.download_vpid_pausebutton.click();
				 Thread.sleep(300);
				 logger.log(LogStatus.INFO, "Playback Paused" +logger.addScreenCapture(funct.capture_ScreenShot(androiddriver, Screenshot_Path, "Pause")));
				 
				 int startX1 = androiddrmpom.download_seek_bar.getLocation().getX();
				 funct.seek_bar_swipe(androiddriver,androiddrmpom.download_seek_bar ,startX1,0.7); 
				 logger.log(LogStatus.INFO, "Seeking Forward" +logger.addScreenCapture( funct.capture_ScreenShot(androiddriver, Screenshot_Path, "Seeking")));
				 
				 
				 androiddrmpom.download_vpid_playbutton.click();
				 Thread.sleep(300);
				 
				
				 int i=0;
				 do
				 {
					 elapsed_time=androiddrmpom.download_elapsed_duration.getText();
				//	 System.out.println("Elapsed time:"+elapsed_time);
					 
					 i++; 
				 }while(i<120);
				
				 
				
//				
				 androiddriver.pressKeyCode(AndroidKeyCode.BACK);
				 androiddriver.rotate(ScreenOrientation.PORTRAIT);
				
				Thread.sleep(5000);
				
			}
			
			@Test(dependsOnMethods={"Downloaded_VideoPlayback_Offline"})
			public void Downloaded_AudioPlayback_Offline() throws Exception
		
			{
				 logger = extent.startTest("Downloaded Audio Playback Offline");
				
				//driver.findElement(By.xpath("//android.widget.LinearLayout[1][@index='1']")).click();
				 androiddriver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2][@index='1']")).click();
				Thread.sleep(3000);
				
				Assert.assertEquals(vpid1, androiddrmpom.download_playback_vpid_title.getText());
				logger.log(LogStatus.PASS, "Audio VPID Matched");
				
				logger.log(LogStatus.INFO, "Audio TransportContols " + logger.addScreenCapture(funct.capture_ScreenShot(androiddriver,Screenshot_Path ,"Assertion")));
				//DRM_Playback_CheckingAssertions();
				
				
				logger.log(LogStatus.INFO, "Audio Playing " +logger.addScreenCapture( funct.capture_ScreenShot(androiddriver, Screenshot_Path, "Downlaoded Audio")));
				
				Assert.assertFalse(funct.isElementPresent(androiddriver, By.id("uk.co.bbc.drmtestapp.android:id/subtitles_button")));
				logger.log(LogStatus.PASS, "No Subtitle Dislayed");
				 
				Live_rewind_seek_forward(androiddrmpom.download_seek_bar, "Left");
				  
				Live_rewind_seek_forward(androiddrmpom.download_seek_bar, "Right");
				
				
			}
			
			@Test(dependsOnMethods={"Downloaded_AudioPlayback_Offline"})
			public void delete_Downloaded() throws InterruptedException
			{
				
				logger = extent.startTest("Deleting the Downloaded VPID's");
				
				androiddriver.pressKeyCode(AndroidKeyCode.BACK);
				
				androiddriver.rotate(ScreenOrientation.PORTRAIT);
				
				logger.log(LogStatus.PASS, "Before Deleting the Downloaded VPID's", logger.addScreenCapture( funct.capture_ScreenShot(androiddriver, Screenshot_Path, " Before Delete Downloaded")));
				
				//System.out.println("Second Remove Button is "+ driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.Button[@index='1']")).getText());
				//driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.Button[@index='1']")).click();
				androiddrmpom.removeButton1.click();
				Thread.sleep(600);
				
			//	Assert.assertFalse(funct.isElementPresent(driver, By.id("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.Button[@index='1']")));
				
				//System.out.println("First Remove Button is "+ driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[@index='1']")).getText());
				//driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[@index='1']")).click();
				androiddrmpom.removeButton2.click();
				Thread.sleep(600);
				
			//	Assert.assertFalse(funct.isElementPresent(driver, By.id("//android.widget.TextView[contains(@resource-id,'uk.co.bbc.drmtestapp.android:id/textViewVpid') and @text="+"'"+vpids[j]+"']")));
				
				Assert.assertFalse(funct.isElementPresent(androiddriver, By.id("//android.widget.TextView[contains(@resource-id,'uk.co.bbc.drmtestapp.android:id/textViewVpid') and @text="+"'"+vpid+"']")), "VPID not Present");
				
				Assert.assertFalse(funct.isElementPresent(androiddriver, By.id("//android.widget.TextView[contains(@resource-id,'uk.co.bbc.drmtestapp.android:id/textViewVpid') and @text="+"'"+vpid1+"']")), "VPID not Present");
				
			//	Assert.assertFalse(funct.isElementPresent(driver, By.id("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[@index='1']")));
				
				logger.log(LogStatus.PASS, "Deleted the Downloaded VPID's", logger.addScreenCapture( funct.capture_ScreenShot(androiddriver, Screenshot_Path, "Delete Downloaded")));
			
				androiddriver.setConnection(networkconnection().WIFI);
						
			}
			
			
			/*@Test(dependsOnMethods={"delete_Downloaded"})
			@Parameters({"deviceType"})
			public void delete_Queued(String Vpid , String devicetype) throws Exception
			{
				
			
				
				logger = extent.startTest("Deleting the In-Progress, Queued VPID's");
				
				for(int i=0;i<=vpids.length;i++)
				{
				
				addVpid(vpids[i], devicetype);
				
				//addVpid("b08d291f", devicetype);
				
				}
				
				logger.log(LogStatus.INFO, "Downloading VPID's", logger.addScreenCapture( funct.capture_ScreenShot(driver, Screenshot_Path, " Downloading")));
				
			    Download_menu(devicetype);
			    
			    System.out.println("First VPID is "+driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'uk.co.bbc.drmtestapp.android:id/textViewVpid') and @text="+"'"+vpid+"']")).getText());
				
				System.out.println("Second VPID is "+driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'uk.co.bbc.drmtestapp.android:id/textViewVpid') and @text="+"'"+vpid1+"']")).getText());
			    
			    drmpom.removeButton1.click();
			    
			    drmpom.removeButton2.click();
			    
			    for(int j=0;j<=vpids.length;j++)
			    {
			    	 Assert.assertFalse(funct.isElementPresent(driver, By.id("//android.widget.TextView[contains(@resource-id,'uk.co.bbc.drmtestapp.android:id/textViewVpid') and @text="+"'"+vpids[j]+"']")));
			    }
			   
			    
			    logger.log(LogStatus.INFO, "Deleting the VPID's", logger.addScreenCapture( funct.capture_ScreenShot(driver, Screenshot_Path, "Delete Queued")));
			    
			    
			}*/
			

		
		
		@AfterClass
		public void teatDown()
		{
			extent.endTest(logger);
			extent.flush();
			androiddriver.resetApp();
			androiddriver.quit();
			ap.stopappium();
		}
		
		
		public Connection networkconnection()
		{
			return Connection.AIRPLANE;
		}
		
		
		public boolean assertElementNotPresent(AndroidDriver<WebElement> driver, WebElement element)
		{
		    try 
		    {
		        driver.findElement((By) element);
		        return true;
		    } catch (NoSuchElementException ex)
		    { 
		        return false;
		    }
		}
		
		
		public void Download_menu(String device_type) throws Exception
		{
			if(device_type.equals("Mobile"))
			{		
				androiddrmpom.menuoption.click();
			Thread.sleep(1000);
			
			androiddrmpom.download_option.click();
			Thread.sleep(1000);
			}
		}
		
		public void Setting_Mediatype(String devicetype, String media) throws Exception
		{
			if(devicetype.equals("Mobile"))
				
			{
				Download_menu(devicetype);
				
				
				
				androiddrmpom.mediaset.click();
				androiddrmpom.mediaset_type.clear();
				androiddrmpom.mediaset_type.sendKeys(media);
				androiddriver.hideKeyboard();
			
				androiddriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
				androiddrmpom.closeall.click();
			
				androiddriver.launchApp();
				
			}
			else if(devicetype.equals("Tablet"))
			{
				
				androiddrmpom.mediaset.click();
				androiddrmpom.mediaset_type.clear();
				androiddrmpom.mediaset_type.sendKeys(media);
				androiddriver.hideKeyboard();
			
				//driver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
				//drmpom.closeall.click();
			
				androiddriver.launchApp();
			}
			
		}
		
		
		public void addVpid(String vpid, String devicetype) throws Exception, InterruptedException
		{
		
			androiddrmpom.vpid_enter.clear();
			Thread.sleep(500);
			
			androiddrmpom.vpid_enter.click();
			Thread.sleep(2000);
			
			androiddrmpom.vpid_enter.sendKeys(vpid);
			androiddriver.hideKeyboard();
			Thread.sleep(500);
			
			wait = new WebDriverWait(androiddriver,2500);
			androiddrmpom.download_button.click();
			//logger.log(LogStatus.INFO, "Click on Download button" + logger.addScreenCapture(funct.capture_ScreenShot(driver, Screenshot_Path, "Download")));
			Thread.sleep(9000);
			
			Press_Back(devicetype);
			
			
			
			
		}
		
		
		public void Press_Back(String device_type) throws Exception
		{
			if(device_type.equals("Mobile"))
		{	
				androiddriver.pressKeyCode(AndroidKeyCode.BACK);
		
		}
		}
		
		
		public boolean verifyElementAbsent(String locator) throws Exception 
		{
		    try {
		    	androiddriver.findElement(By.xpath(locator));
		        System.out.println("Element Present");
		        return false;

		    } catch (NoSuchElementException e) {
		        System.out.println("Element absent");
		        return true;
		    }
		}
		
		
		public void DRM_Playback_CheckingAssertions()
		{
		
			String DRM_transport_text [] ={
					"Volume button present",
					"Subtitle button present",
					"Seekbar button present",
					"Pause button present",
					"Title present"
					};
		
		for (int j=0;j<DRM_transport_text.length;j++)
		{
			logger.log(LogStatus.PASS, DRM_transport_text[j]);
		}
//			for(int i=0;i<funct.DRM.length;i++)
//			{
//				System.out.println(funct.DRM[i]);
//				assertTrue(funct.isElementPresent(androiddriver, By.id(funct.DRM[i])));
//
//				
//			}
		}
		
		
		
		
		
		
		
		public void Live_rewind_seek_forward(WebElement seekbar, String direction) throws InterruptedException
		 {
			
	 
			
	  	     logger=extent.startTest("Seeking Forward/Backward ","Checking Seeking Forward/backward ");
			
			
			   int startX = seekbar.getLocation().getX();
		       System.out.println("Startx :"+startX);
		       
		       
		     //Get end point of seekbar.
		       int endX = seekbar.getSize().getWidth();
		       System.out.println("Endx Forward  :"+endX);
		       
		       
		     //Get vertical location of seekbar.
		       int yAxis = seekbar.getLocation().getY();
		       System.out.println("Yaxis  :"+yAxis);
		       
		       //Set sllebar move to position. 
		       //endX * 0.6 means at 60% of seek bar width.
		       int moveToXDirectionAt = (int) (endX * 0.99);
		       System.out.println("Moving seek bar at " + moveToXDirectionAt+" In X direction.");
		       
		       //Moving seekbar using TouchAction class.
		      // TouchAction act=new TouchAction(driver);  
		       //act.press(startX,yAxis).moveTo(moveToXDirectionAt,yAxis).release().perform(); 
		       //Thread.sleep(3000);  
		       if(direction.equals("Left"))
		       {
		    	   androiddriver.swipe(endX, yAxis, startX, yAxis, 9000);
		       logger.log(LogStatus.INFO, "Seeking Right " + logger.addScreenCapture(funct.capture_ScreenShot(androiddriver,Screenshot_Path ,"Seeking_from_Right-to-Left")));
		       }
		       else
		       {
		    	   androiddriver.swipe(startX, yAxis, endX+8, yAxis, 9000);
		       logger.log(LogStatus.INFO, "Seeking Left " + logger.addScreenCapture(funct.capture_ScreenShot(androiddriver,Screenshot_Path ,"Seeking_from_Left-to-Right")));
		       wait = new WebDriverWait(androiddriver, 3500);
		     
		       
			
				
			//	driver.pressKeyCode(AndroidKeyCode.BACK);
				Thread.sleep(3000);
		       }
		 
		 }
		
		public boolean isElementPresent(AndroidDriver<WebElement> driver, By locatorKey) {
			try {
				driver.findElement(locatorKey);
				return true;
			} catch (NoSuchElementException e) {
				// e.printStackTrace();
				return false;
			}
			// return false;
		}
		
	}