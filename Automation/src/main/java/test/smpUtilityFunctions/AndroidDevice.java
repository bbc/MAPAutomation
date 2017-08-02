package main.java.test.smpUtilityFunctions;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AndroidDevice {

	 public String retrunDeviceInfo(String deviceName)
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
	         			 //System.out.println("OS versionis:-"+OS[i]);
	         			deviceName_ios=OS[i];}   	
	     		     }
	         	}        	
	    	 }catch(Exception e)
	    	 {
	    		 e.printStackTrace();
	    	 }
	    	 return deviceName_ios;
	    	 }  
}
