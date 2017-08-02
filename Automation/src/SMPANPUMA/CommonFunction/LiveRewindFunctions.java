package SMPANPUMA.CommonFunction;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class LiveRewindFunctions {
	CommonFunctions commonfunctions;
	ExtentReports extent;
	ExtentTest logger;
	WebDriverWait wait;

	public void LiveText_Checking(String element, AndroidDriver<WebElement> adriver, String path) throws Exception {

		try
		{
		boolean livetext = commonfunctions.isElementPresent(adriver, By.id(element));
			System.out.println("LiveText Value is" + livetext);

		if (livetext == true) {
			logger.log(LogStatus.INFO, "Live Text  Present: "
					+ logger.addScreenCapture(
							commonfunctions.capture_ScreenShot(adriver, path, "LiveText_Present")));
			System.out.println("Live Text  Present");
		}

		else {
			logger.log(LogStatus.INFO, "Live Text Not Present: "
					+ logger.addScreenCapture(
							commonfunctions.capture_ScreenShot(adriver, path, "LiveText_Present")));
			System.out.println("Live Text Not Present");

		}
		}catch(Exception e)
		{e.printStackTrace();

		}

	}

	/*
	 * // WebElement [] elements = {"seekbar","","",""};
	 * 
	 * public void Seekto_beggining(AndroidDriver<WebElement> adriver,
	 * WebElement element, String elements, String path, String message) throws
	 * Exception {
	 * 
	 * logger = extent.startTest("Seekto_beggining",
	 * "Checking the Live Simuclast Rewind Seeking Back");
	 * 
	 * int startX1 = element.getLocation().getX(); //
	 * liverewind.live_rewind_progressbar.getLocation().getX();
	 * 
	 * // SeektoLivePosition("Left");
	 * 
	 * // SeektoLivePosition("Right");
	 * 
	 * // seek_bar_swipe(adriver, liverewind.live_rewind_progressbar, startX1,
	 * // 0.5); commonfunction.seek_bar_swipe(adriver, element, startX1, 0.5);
	 * 
	 * logger.log(LogStatus.INFO, "Seeking 50%" +
	 * logger.addScreenCapture(commonfunction.capture_ScreenShot(adriver, path,
	 * message)));// "Seeking // Back")));
	 * 
	 * LiveText_Checking(adriver, elements, path, message);
	 * 
	 * // SeektoLivePosition("Right");
	 * 
	 * int currentX = element.getLocation().getX();//
	 * liverewind.live_rewind_progressbar.getLocation().getX();
	 * System.out.println("currentX position  :" + currentX);
	 * 
	 * Thread.sleep(3000);
	 * 
	 * }
	 * 
	 * public void SeektoLivePosition(AndroidDriver<WebElement> adriver, int
	 * deviceOS, WebElement element, String elements, String path, String
	 * message) throws InterruptedException, Exception {
	 * 
	 * logger = extent.startTest("Seeking Forward ",
	 * "Checking the Live Text Present after Seeking Forward ");
	 * 
	 * // System.out.println("Seeking Forward Checking the Live Text Present //
	 * after Seeking Forward "); int position = 300; int startX =
	 * element.getLocation().getX();//
	 * liverewind.live_rewind_progressbar.getLocation().getX(); int xposition =
	 * startX + position;
	 * System.out.println("Startx After Before Adding  the Value  :" + startX);
	 * System.out.println("Startx After After Adding  the Value  :" +
	 * xposition);
	 * 
	 * // Get end point of seekbar. int endX =
	 * element.getLocation().getY();//liverewind.live_rewind_progressbar.getSize
	 * ().getWidth(); System.out.println("Endx Forward  :" + endX);
	 * 
	 * // Get vertical location of seekbar. int yAxis =
	 * element.getLocation().getY();//liverewind.live_rewind_progressbar.
	 * getLocation().getY(); System.out.println("Yaxis  :" + yAxis);
	 * 
	 * // Set sllebar move to position. // endX * 0.6 means at 60% of seek bar
	 * width. int moveToXDirectionAt = (int) (endX * 0.99);
	 * System.out.println("Moving seek bar at " + moveToXDirectionAt +
	 * " In X direction.");
	 * 
	 * if(deviceOS >= 5.1) {
	 * 
	 * 
	 * adriver.swipe(endX, yAxis, startX, yAxis, 9000); Thread.sleep(3000);
	 * logger.log(LogStatus.INFO, "Seeking Left to Beginning " + logger
	 * .addScreenCapture(cfunction.capture_ScreenShot(adriver, path,
	 * "Seeking_from_Right-to-Left"))); LiveText_Checking(adriver, elements,
	 * path, message);
	 * 
	 * } else { Thread.sleep(3000); int position1 = 30; int yposition = endX +
	 * position1;
	 * 
	 * adriver.swipe(startX, yAxis, yposition, yAxis, 9000);
	 * 
	 * Thread.sleep(3000);
	 * 
	 * LiveText_Checking(adriver, elements, path, message);
	 * 
	 * logger.log(LogStatus.INFO, "Seeking Right to End " + logger
	 * .addScreenCapture(cfunction.capture_ScreenShot(adriver, path,
	 * "Seeking_from_Left-to-Right"))); wait = new WebDriverWait(adriver, 3500);
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
}


