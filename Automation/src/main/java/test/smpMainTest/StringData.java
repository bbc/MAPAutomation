package main.java.test.smpMainTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.test.smpUtilityFunctions.PortFactory;

public class StringData {
    public static void main(String[] args) throws Exception {
        BufferedReader br = null;
        String deviceName = null;
	    String deviceOS[] = null;
	    String DeviceID=null;
	    
	    List<String> deviceID = new ArrayList<String>();
		List<String> DeviceOS = new ArrayList<String>();
		List<String> DeviceName = new ArrayList<String>();
	    
	    String match="ProductVersion"; 
	    String match1="UniqueDeviceID";
	    String match2 = "DeviceName";
        try {
        	String[] arguments = new String[] {"/usr/local/bin/ideviceinfo"};
        	Process p =Runtime.getRuntime().exec(arguments);
        	BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        	String line = "";
        	while((line=r.readLine()) != null)
        	{
        	line = line.toLowerCase();
		    match = match.toLowerCase();
		    match1 = match1.toLowerCase();
		    match2 = match2.toLowerCase();
        
        	if(line.indexOf(match) !=-1)
    		{
        		String OS[]=line.split(":");     		
        		for(int i=1;i<OS.length;i++)
        		{
        			OS[i] = OS[i].replaceAll("\\s+", "");
        			 System.out.println("OS versionis:-"+OS[i]);
        			 DeviceOS.add(OS[i]);
        		}   	
    		 
    		}  if(line.indexOf(match1) !=-1)
    		{
//    			  deviceName=line;
//    			  System.out.println(deviceName);
    			String OS[]=line.split(":");     		
        		for(int i=1;i<OS.length;i++)
        		{
        			OS[i] = OS[i].replaceAll("\\s+", "");
        			 System.out.println("Device ID is:-"+OS[i]);
        			 deviceID.add(OS[i]);
        		} 
    			  
    		}if(line.indexOf(match2) !=-1)
    		{
//    			DeviceID=line;
//    			System.out.println(DeviceID);
    			String OS[]=line.split(":");     		
        		for(int i=1;i<OS.length;i++)
        		{
        			 OS[i] = OS[i].replaceAll("\\s+", "");
        			 System.out.println("Device Name is:-"+OS[i]);
        			 DeviceName.add(OS[i]);
        		} 
        	}
        	}
        }catch(Exception e)
        	{
        		e.printStackTrace();
        	}

       
 
    		for (int i = 0; i < DeviceOS.size(); i++) {
    			try {
    				String osName = DeviceOS.get(i);
    				String id = deviceID.get(i);
    				String dname = DeviceName.get(i);
    				
    				System.out.println("The Device OS is " + osName);
    				System.out.println("The Device ID is " + id);
    				System.out.println("The Device Name is " + dname);

    			// setUp(port, id, osName);

    			} catch (Exception e) {
    				e.printStackTrace();
    				throw new RuntimeException(e);
    			}

    		}
    		// ap.stopappium();
    		String deviceNames = retrunDeviceInfo("DeviceName");
    		String deviceOSs = retrunDeviceInfo("ProductVersion");
    		String deviceUDID = retrunDeviceInfo("UniqueDeviceID");
    	    System.out.println("The Device Name is :-"+  deviceNames);
    	    System.out.println("The Device OS version is :-"+  deviceOSs);
    	    System.out.println("The Device UDID is :-"+  deviceUDID);
        
    	    String device_OS="9.3.5";
    	    String device_OS1="9.3.5";
    	    double OSversion = 0;
    	    Double device_OS11 = 10.0;
    	    
    	    String OSversions[] = {"9.3.5","9.0","10.2.1","10.3.1","10.2","10.3.2"};
    	    
    	    NumberFormat nf = NumberFormat.getInstance();
    	    Double format = nf.parse(deviceOSs).doubleValue();
    	    
    	    if(format < device_OS11)
    	    {
    	    System.out.println("Integer Value is Matched");
    	   
    	    }else    if(format >= device_OS11)
    	    {
    	    System.out.println("Integer Value is Not Matched");
    	 
    	   
    	    }
    }
    	    	
    

    
    public static String retrunDeviceInfo(String deviceName)
    {
    	String deviceName_ios=null;
    	String match =deviceName;
    
    	 try {
    		 String[] arguments = new String[] {"/usr/local/bin/ideviceinfo"};
    	     Process p =Runtime.getRuntime().exec(arguments);
    	     BufferedReader	br = new BufferedReader(new InputStreamReader(p.getInputStream()));
         	String line = "";
         	while((line= br.readLine()) != null)
         	{
         	line = line.toLowerCase();
 		    match = match.toLowerCase();
         	if(line.indexOf(match) !=-1)
     		{
         		String OS[]=line.split(":");     		
         		for(int i=1;i<OS.length;i++)
         		{
         			OS[i] = OS[i].replaceAll("\\s+", "");
         			 System.out.println("OS versionis:-"+OS[i]);
         			deviceName_ios=OS[i];}   	
     		     }
         	}        	
    	 }catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
    	 return deviceName_ios;
    	 }  
    
    
    public static void iDeviceInfo() throws IOException
    {
    	String[] arguments = new String[] {"/usr/local/bin/ideviceinfo"};
     	Process p =Runtime.getRuntime().exec(arguments);
     	BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
     
    		try{
    			  String strDirectoy = "/Users/ramakh01/Desktop/MAP_Automation/MAPAutomation/Automation/HarishDRM";
    			//  String strManyDirectories=screenspath; //"iOS-DRM/Screenshot";

    			  // Create one directory
    			  boolean success = (
    			  new File(strDirectoy)).mkdir();
    			  if (success)
    			  {
    			  System.out.println("Directory: " 
    			   + strDirectoy + " created");
    			  }  
    			 
    			  }catch (Exception e){//Catch exception if any
    			  System.err.println("Error: " + e.getMessage());
    			  }
    		
    			  }
     	
     	
     	
     	
    }
    
     	
