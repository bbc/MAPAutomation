package main.java.test.smpUtilityFunctions;

import org.openqa.selenium.WebElement;

import io.appium.java_client.ios.IOSDriver;

public class DeviceDemo {

	IOSDriver<WebElement> idriver;

	public void getdevicedetails() {

		Object deviceID = idriver.getCapabilities().getCapability("UDID");
		Object deviceName = idriver.getCapabilities().getCapability("deviceName");

		System.out.println("DeviceID" + deviceID);
		System.out.println("DeviceName" + deviceName);

	}

	public static void main(String arg[])
	{
		DeviceDemo gdevice = new DeviceDemo();
		gdevice.getdevicedetails();
		
	}

}
