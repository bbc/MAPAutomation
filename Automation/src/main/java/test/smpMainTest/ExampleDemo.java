package main.java.test.smpMainTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ExampleDemo {
	
	public static void main(String args[]) throws FileNotFoundException
	{
		try {
		    FileInputStream fstream = new FileInputStream("/Users/ramakh01/Desktop/MAPAutomation/iPadAir.txt");
		    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		    String strLine;         
		    int lineNumber = 0;
		   
		    String deviceName = null;
		    String deviceOS = null;
		    String DeviceID=null;
		    String DeviceName=null;
		    
		    String match="ProductVersion"; 
		    String match1="UniqueDeviceID";
		    String match2 = "DeviceName";
		    // Read File Line By Line
		    while ((strLine = br.readLine()) != null) 
		    {
		    	strLine = strLine.toLowerCase();
		    	match = match.toLowerCase();
		    	match1 = match1.toLowerCase();
		    	match2 = match2.toLowerCase();
		    	
		        lineNumber++;
		       /* if( lineNumber == 12 ){
		        	deviceName = strLine;
		        }else if( lineNumber == 33 ){
		        	deviceOS = strLine;
		        }  else if( lineNumber == 51 ){
		        	UniqueDeviceID = strLine;
		        }*/ 
		          if( strLine.indexOf(match) != -1)  
		        {
                    deviceOS = strLine;
                   
                } if (strLine.indexOf(match1) != -1) 
			        {
	                    DeviceID = strLine;
	                }if (strLine.indexOf(match2) != -1) 
			        {
	                    DeviceName = strLine;
	                }
	                strLine = br.readLine();
		    }
		    // Close the input stream
		    fstream.close();
		    //print the contents of a
		    
		      
		        System.out.println(deviceOS);
		        System.out.println(DeviceID);
		        System.out.println(DeviceName);
		               
		} catch (Exception e) {// Catch exception if any
		    System.err.println("Error: " + e.getMessage());
		}
		
		/*Scanner reader = new Scanner(new File("/Users/ramakh01/Desktop/MAPAutomation/iPhone.txt"));
		String deviceName=null;
		String deviceUDID = null;
		 String str=null;
		while (reader.hasNext())
		{
		   str = reader.nextLine();
		   if(str.equalsIgnoreCase("DeviceName:"))
		   {
			   deviceName = str;
		   }else if(str.equalsIgnoreCase("UniqueDeviceID:"))
		   {
			    deviceUDID = str;
		   }
		   System.out.println(str);
		   System.out.println(deviceName);
		   System.out.println(deviceUDID);
		}*/
	}

}
