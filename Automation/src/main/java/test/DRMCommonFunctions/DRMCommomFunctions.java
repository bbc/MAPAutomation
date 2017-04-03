package main.java.test.DRMCommonFunctions;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class DRMCommomFunctions {
	
	
	public void swipe(WebElement element, AppiumDriver<WebElement> driver) throws Exception
	{
		
//	Dimension size =element.getSize();
//	
//	System.out.println("Size is" +size);
//	
//	int endx = (int) (size.width * 0.10);
//	System.out.println("endx is" +endx);
//	
//	int startx = (int) (size.width * 0.90);
//	System.out.println("startx is" +startx);
//	
//	int starty = size.height ;
//	System.out.println("Height is" +startx);	
//	System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);
//	Thread.sleep(5000);
//	driver.swipe(startx, starty, endx, starty, 4000);
//	
//	System.out.println("*****************************************");
//	
//	System.out.println("Size is" +size);
//	
//	int endx1 = (int) (size.width * 0.10);
//	
//	System.out.println("endx is" +endx1);
//	
//	int startx1 =size.width;
//	System.out.println("startx is" +startx1);
//	
//	int starty1 = size.height ;
//	System.out.println("Height is" +starty1);	
//	
//	System.out.println("startx1 = " + startx1 + " ,endx1 = " + endx1 + " , starty1 = " + starty1);
//	Thread.sleep(5000);
//	driver.swipe(startx1, starty, startx1, starty, 4000);
//	Thread.sleep(5000);
//	
//	
//	System.out.println("*****************************************");
//	
//	
//	int height = size.getHeight();
//	int endHeight = height / 4;
//	int width = size.getWidth();
//	int endWidth = width / 4;
//	System.out.println("endWidth = " + endWidth + " ,endHeight = " + endHeight + " , height = " + height);
//	
//	driver.swipe(endWidth, height, endWidth, endHeight, 1000);
//	Thread.sleep(8000);
	
	
	int startX = element.getLocation().getX();
	System.out.println("Startx :" + startX);

	// Get end point of seekbar.
	int endX = element.getSize().getWidth();
	System.out.println("Endx  :" + endX);

	// Get vertical location of seekbar.
	int yAxis = element.getLocation().getY();
	System.out.println("Yaxis  :" + yAxis);

	// Set sllebar move to position.
	// endX * 0.6 means at 60% of seek bar width.
	int moveToXDirectionAt = (int) (endX * 0.5);
	System.out.println("Moving seek bar at " + moveToXDirectionAt + " In X direction.");

	// Moving seekbar using TouchAction class.
	// TouchAction act=new TouchAction(driver);
	// act.press(startX,yAxis).moveTo(moveToXDirectionAt,yAxis).release().perform();
	Thread.sleep(4000);
	driver.swipe(endX, yAxis, startX, yAxis, 5000);
	
	}

}
