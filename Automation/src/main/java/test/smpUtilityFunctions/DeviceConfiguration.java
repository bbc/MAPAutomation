package main.java.test.smpUtilityFunctions;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * DeviceConfiguration - this class contains methods to start adb server, to get connected devices and their information.   
 */
public class DeviceConfiguration {

	CommandPrompt cmd = new CommandPrompt();
	Map<String, String> devices = new HashMap<String, String>();
	String devices1[];
	String path="/Users/ramakh01/Downloads/android-sdk/platform-tools/";
	
	private static String sdkPath = "/Users/ramakh01/Downloads/android-sdk/";
	private static String adbPath = sdkPath + "platform-tools" + File.separator + "adb";
	
	/**
	 * This method start adb server
	 */
	public void startADB() throws Exception
	{
		String output = cmd.runCommand(path + "./adb start-server");
		String[] lines = output.split("\n");
		if(lines.length==1)
			System.out.println("adb service already started");
		else if(lines[1].equalsIgnoreCase("* daemon started successfully *"))
			System.out.println("adb service started");
		else if(lines[0].contains("internal or external command")){
			System.out.println("adb path not set in system varibale");
			System.exit(0);
		}
	}
	
	/**
	 * This method stop adb server
	 */
	public void stopADB() throws Exception{
		cmd.runCommand(path + "./adb kill-server");
	}
	
	/**
	 * This method return connected devices
	 * @return hashmap of connected devices information
	 */
	public Map<String, String> getDivces() throws Exception
	{
		
		  String[] commandDevices = new String[] { adbPath, "devices" };
		  Process process = new ProcessBuilder(commandDevices).start();
		  
		String output = cmd.runCommand(path+"./adb devices");
		String[] lines = output.split("\n");

		if(lines.length<=1)
		{
			System.out.println("No Device Connected");
			stopADB();
			System.exit(0);	// exit if no connected devices found
		}
		
		for(int i=1;i<lines.length;i++)
		{
			lines[i]=lines[i].replaceAll("\\s+", "");
			
			if(lines[i].contains("device"))
			{
				lines[i]=lines[i].replaceAll("device", "");
				String deviceID = lines[i];
				String model = cmd.runCommand(path+"./adb -s "+deviceID+" shell getprop ro.product.model").replaceAll("\\s+", "");
				String brand = cmd.runCommand(path+"./adb -s "+deviceID+" shell getprop ro.product.brand").replaceAll("\\s+", "");
				String osVersion = cmd.runCommand(path+"./adb -s "+deviceID+" shell getprop ro.build.version.release").replaceAll("\\s+", "");
				String deviceName = brand+" "+model;
				
//				devices.put("deviceID"+i, deviceID+"\n");
//				devices.put("deviceName"+i, deviceName+"\n");
//				devices.put("osVersion"+i, osVersion+"\n");
				
			 	System.out.println("Following device is connected");
				//System.out.println(deviceID+"  "+osVersion+"\n");
				System.out.println(deviceID+" "+deviceName+" "+osVersion+"\n");
			}
			
			

		}
		return devices;
	}
	
	
	
	
	
	
	public static void main(String[] args) throws Exception
	{
		DeviceConfiguration gd = new DeviceConfiguration();
		gd.startADB();	
		
	    Map<String, String> dev = gd.getDivces();
//		Iterator i = dev.entrySet().iterator();
//		while(i.hasNext())
//		{
//		    String key = i.next().toString();  
//		    String value = i.next().toString();
//		   System.out.println("The Device attached are listed below"+" "+key + " " + value);    
//		}
		gd.stopADB();
	}
}
