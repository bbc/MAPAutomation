package main.java.test.smpUtilityFunctions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;

public class StartAppium_Devices {

	AppiumManager appiummanager = new AppiumManager();
	DeviceList devicelist = new DeviceList();
	AndroidDriver<WebElement> driver;

	public String osName;
	public String id;
	public String dname;
	public int port;
	String output;
	Process process;

	public List<String> deviceID = new ArrayList<String>();
	public List<String> deviceOS = new ArrayList<String>();
	public List<String> deviceName = new ArrayList<String>();

	private static String sdkPath = "/Users/ramakh01/Downloads/android-sdk/platform-tools/";
	private static String adbPath = sdkPath + File.separator + "./adb";
	String[] commandDevices = new String[] { adbPath, "devices" };
	CommandPrompt cmd = new CommandPrompt();

	@BeforeClass
	public void RunTest() throws Exception {

		try {

			devicelist.populateDevices_IDs();
			devicelist.populateDevices_OS();
			devicelist.populateDevices_Names();
			getAllDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAllDetails() {
		PortFactory portFactory = new PortFactory();

		for (int i = 0; i < devicelist.deviceOS.size(); i++) {
			try {
				osName = devicelist.deviceOS.get(i);
				id = devicelist.deviceID.get(i);
				dname = devicelist.deviceName.get(i);
				port = portFactory.create();
				System.out.println("The Device OS is " + osName);
				System.out.println("The Device ID is " + id);
				System.out.println("The Device port is " + port);
				System.out.println("The Device Name is " + dname);

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		}
		// ap.stopappium();
	}

}
