package main.java.test.smpUtilityFunctions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

	public class DeviceList 
	{
		private static String sdkPath = "/Users/ramakh01/Downloads/android-sdk/platform-tools/";
		private static String adbPath = sdkPath + File.separator + "./adb";
		String[] commandDevices = new String[] { adbPath, "devices" };
		CommandPrompt cmd = new CommandPrompt();
	
		public List<String> deviceID = new ArrayList<String>();
		public List<String> deviceOS = new ArrayList<String>();
		public List<String> deviceName = new ArrayList<String>();

		String osVersion;
		Process process;
		String output;
	
		AppiumManager ap = new AppiumManager();

		public void getAllDetails() 
		{
			PortFactory portFactory = new PortFactory();

			for (int i = 0; i < deviceOS.size(); i++) {
				try {
					String osName = deviceOS.get(i);
					String id = deviceID.get(i);
					String dname = deviceName.get(i);
					int port = portFactory.create();
					System.out.println("The Device OS is " + osName);
					System.out.println("The Device ID is " + id);
					System.out.println("The Device port is " + port);
					System.out.println("The Device Name is " + dname);

				// setUp(port, id, osName);

				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}

			}
			// ap.stopappium();
		}



	public void populateDevices_IDs() throws Exception {
		process = new ProcessBuilder(commandDevices).start();

		output = cmd.runCommand(sdkPath + "./adb devices");
		String[] lines = output.split("\n");

		for (int i = 1; i < lines.length; i++) {
			lines[i] = lines[i].replaceAll("\\s+", "");

			if (lines[i].contains("device")) {
				lines[i] = lines[i].replaceAll("device", "");
				String deviceID1 = lines[i];

				// System.out.println("Following device is connected");
				// System.out.println("deviceID" + deviceID1);
				deviceID.add(deviceID1);
			}

		}

	}

	public void populateDevices_OS() throws Exception {
		process = new ProcessBuilder(commandDevices).start();
		output = cmd.runCommand(sdkPath + "./adb devices");
		String[] lines = output.split("\n");

		for (int i = 1; i < lines.length; i++) {
			lines[i] = lines[i].replaceAll("\\s+", "");

			if (lines[i].contains("device")) {
				lines[i] = lines[i].replaceAll("device", "");
				String deviceID1 = lines[i];
				// String model = cmd.runCommand(sdkPath + "./adb -s " +
				// deviceID1 + " shell getprop ro.product.model")
				// .replaceAll("\\s+", "");
				// String brand = cmd.runCommand(sdkPath + "./adb -s " +
				// deviceID1 + " shell getprop ro.product.brand")
				// .replaceAll("\\s+", "");
				osVersion = cmd
						.runCommand(sdkPath + "./adb -s " + deviceID1 + " shell getprop ro.build.version.release")
						.replaceAll("\\s+", "");
				// String devicenames = brand + " " + model;

				// System.out.println("Following device OS");
				// // System.out.println(deviceID+" "+osVersion+"\n");
				// System.out.println("osVersion***** :" + osVersion);
				deviceOS.add(osVersion);
			}

		}

	}

	public void populateDevices_Names() throws Exception {
		process = new ProcessBuilder(commandDevices).start();
		output = cmd.runCommand(sdkPath + "./adb devices");
		String[] lines = output.split("\n");

		for (int i = 1; i < lines.length; i++) {
			lines[i] = lines[i].replaceAll("\\s+", "");

			if (lines[i].contains("device")) {
				lines[i] = lines[i].replaceAll("device", "");
				String deviceID1 = lines[i];
				String model = cmd.runCommand(sdkPath + "./adb -s " + deviceID1 + " shell getprop ro.product.model")
						.replaceAll("\\s+", "");
				String brand = cmd.runCommand(sdkPath + "./adb -s " + deviceID1 + " shell getprop ro.product.brand")
						.replaceAll("\\s+", "");
				osVersion = cmd
						.runCommand(sdkPath + "./adb -s " + deviceID1 + " shell getprop ro.build.version.release")
						.replaceAll("\\s+", "");
				String devicenames = brand + " " + model;

				// System.out.println("Following device OS");
				// // System.out.println(deviceID+" "+osVersion+"\n");
				// System.out.println("osVersion***** :" + osVersion);
				deviceName.add(devicenames);
			}

		}

	}

	public static void main(String args[]) throws Exception {

		DeviceList devicelist = new DeviceList();
		devicelist.populateDevices_IDs();
		devicelist.populateDevices_OS();
		devicelist.populateDevices_Names();
		devicelist.getAllDetails();

	}

	}



