package main.java.test.smpFunctions;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class iOSCommonFunctions {

	WebDriverWait wait;
	public ExtentReports extent = null;
	public ExtentTest logger = null;
	File file;

	public void tapbutton(WebElement element, String testname, IOSDriver<WebElement> idriver)
			throws InterruptedException {

		logger = extent.startTest(testname);
		element.click();
		Thread.sleep(1000);
		logger.log(LogStatus.INFO, testname);
		// logger.log(LogStatus.INFO, testname +
		// logger.addScreenCapture(capture_ScreenShot(idriver, path,
		// testname)));
	}

	public boolean isElementPresent(IOSDriver<WebElement> iosdriver, By xpath) {

		logger = extent.startTest("Checking Element Present");
		try {
			iosdriver.findElement(xpath);
			return true;
		} catch (NoSuchElementException e) {
			// e.printStackTrace();
			return false;
		}
	}

	public String capture_ScreenShot(IOSDriver<WebElement> iosdriver, String ScreenshotPath, String Screenshotname) 
	{

		try {
			TakesScreenshot ts = (TakesScreenshot) iosdriver;;
//			if(platform == "iOS")
//			{
//				IOSDriver<WebElement> iosdriver = null;
//				ts = (TakesScreenshot) iosdriver;
//			}else
//			{
//				AndroidDriver<WebElement> androiddriver = null;
//				ts = (TakesScreenshot) androiddriver;
//			}
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

	public void CreateReport(String absoluteFilePath, String deviceID, String deviceOS, String Port, String deviceName)
			throws Exception, FileAlreadyExistsException, InterruptedException {
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

	public void GenerateReport() {
		extent.endTest(logger);
		extent.flush();
	}

	public void seekingRandomly(WebElement element, IOSDriver<WebElement> idriver, String path, double d)
			throws Exception {
		int seekposition = (int) d;

		logger = extent.startTest("seekingRandomly",
				"Seeking randomly to check whether playback resumes from new point");

		int startX = element.getLocation().getX();
		// liverewind.live_rewind_progressbar.getLocation().getX();

		seek_bar_swipe(idriver, element, startX, d);// 0.5);
		Thread.sleep(500);

		
		logger.log(LogStatus.INFO,
				"Seeking" + seekposition + "%" + logger.addScreenCapture(capture_ScreenShot(idriver, path,"Seeking Random")));

		// LiveText_Checking(idriver, path);

		Thread.sleep(500);

	}

	public void seek_bar_swipe(IOSDriver<WebElement> idriver, WebElement seekbar, int start, double d) {
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
		idriver.swipe(startX, yAxis, moveToXDirectionAt, yAxis, 1000);
	}

	public void turnWifiON(String TestName, IOSDriver<WebElement> idriver, WebElement Wifi,
			WebElement Hide_ControlCentre,String path, String message)
			throws Exception {

		logger = extent.startTest(TestName);

		org.openqa.selenium.Dimension d = idriver.manage().window().getSize();

		int height = d.getHeight();
		int endHeight = height / 4;
		int width = d.getWidth();
		int endWidth = width / 4;
		idriver.swipe(endWidth, height, endWidth, endHeight, 1000);

		// WebElement wifi =
		// iosdriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[7]/UIAScrollView[1]/UIAElement[2]"));

		// Hide_ControlCentre.click();
		// Thread.sleep(6000);

		System.out.println("The Wifi Text is " + Wifi.getText());
		Wifi.click(); // clicking wifi button from control centre
		Thread.sleep(800);

		// logger.log(LogStatus.INFO, "Turned WiFi OFF");

		logger.log(LogStatus.INFO, message + logger.addScreenCapture(capture_ScreenShot(idriver, path, message)));

		Hide_ControlCentre.click();
		Thread.sleep(1000); // the
							// control
							// centre

	}
	
	
	public void tapbutton(WebElement element, String testname, IOSDriver<WebElement> idriver, String path)
			throws InterruptedException {
		
		logger = extent.startTest(testname);
		element.click();
		Thread.sleep(2000);
		logger.log(LogStatus.INFO,
				testname + logger.addScreenCapture(capture_ScreenShot(idriver, path, testname)));
	}
	
	
	public void enterText(WebElement element, String testname, String text,IOSDriver<WebElement> idriver, String path)
			throws InterruptedException {
		
		logger = extent.startTest(testname);
		element.sendKeys(text);
		Thread.sleep(1000);
		logger.log(LogStatus.INFO,
				testname + logger.addScreenCapture(capture_ScreenShot(idriver, path, testname)));
	}
}
