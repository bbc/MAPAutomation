package main.java.test.smpUtilityFunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class rDotlogs
{
	private static String sdkPath = "/Users/ramakh01/Downloads/android-sdk/platform-tools/";
	private static String mediaselector = sdkPath + File.separator + "adb shell logcat"+"|"+"grep " + "http://open.*";
	private static String rdot = sdkPath + File.separator + "adb shell logcat"+"|"+"grep " + "https://r.bbc.*";
	private static String rdots = sdkPath + File.separator + "adb shell logcat"+"|"+"grep " + "open.live.*"+"'\'"+"|"+"r.bbc.*";
	private static String clearlogs = sdkPath + File.separator + "adb shell logcat " + "-c";
	static String rsdot_stats=null;

public static String[] getAdbLogCat(String requestselector) {

    try {
        Process p = Runtime.getRuntime().exec(requestselector);
        InputStream is = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        final StringBuffer output = new StringBuffer();
        String line;
        ArrayList<String> arrList = new ArrayList<String>();
        int count=0;
        while ((line = br.readLine()) != null)
        {
            System.out.println(line);
            try
            {
            if(count==3)
            {
            	break;
            }
            count++;   
            }catch(ArrayIndexOutOfBoundsException e)
            {
            	e.printStackTrace();
            }
       // count++;
        System.out.println("Count value is"+count);
        }
        
        return (String[])arrList.toArray(new String[0]);
    } catch (IOException e) {
        System.err.println(e);
        e.printStackTrace();
        return new String[]{};
    }
}


public static String[] getAdbLogCat2(String requestselector) {

    try {
        Process p = Runtime.getRuntime().exec(requestselector);
        InputStream is = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        final StringBuffer output = new StringBuffer();
        String line;
        ArrayList<String> arrList = new ArrayList<String>();
        int count=0;
        while ((line = br.readLine()) != null)
        {
            System.out.println(line);
            try
            {
            if(count==0)
            {
            	break;
            }
            count++;   
            }catch(ArrayIndexOutOfBoundsException e)
            {
            	e.printStackTrace();
            }
       // count++;
        System.out.println("Count value is"+count);
        }
        
        return (String[])arrList.toArray(new String[0]);
    } catch (IOException e) {
        System.err.println(e);
        e.printStackTrace();
        return new String[]{};
    }
}

public static String getMediaSelectorURL(String requestselector)
{
	String MURL = null;
   
    try {
        Process p = Runtime.getRuntime().exec(requestselector);
        InputStream is = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader	br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        int count=0;
     	while((line= br.readLine()) != null)
     	{
     		String datas[]=line.split("http://");
     		for(int i=0;i<datas.length;i++)
     		{
     			// System.out.println("MediaSelector URL:-"+datas[i]);
     			 MURL=datas[i];
     		}
     		if(count==0)
            {
            	break;
            }
            count++; 
     	}
    }catch(Exception e)
     	{
     		e.printStackTrace();
     	}
        return MURL;
}

public static String getRDOTURL(String requestselector) throws IOException, InterruptedException
{
	String RDOTURL = null;
   
	Runtime.getRuntime().exec(sdkPath + File.separator +"adb shell input keyevent KEYCODE_POWER");
	Thread.sleep(2000);
	Runtime.getRuntime().exec(sdkPath + File.separator +"adb shell input swipe 572 235 1260 1564");
	Thread.sleep(2000);
	
	
	
    try {
    	Runtime.getRuntime().exec(sdkPath + File.separator +"adb shell input text 1234");
    	Thread.sleep(2000);
    	Runtime.getRuntime().exec(sdkPath + File.separator +"adb shell input keyevent KEYCODE_ENTER");
        Process p = Runtime.getRuntime().exec(requestselector);
        InputStream is = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader	br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        int count=0;
     	while((line= br.readLine()) != null)
     	{
     		String datas[]=line.split("https://");
     		for(int i=0;i<datas.length;i++)
     		{
     			System.out.println("RDOT URL:-"+datas[i]);
     			RDOTURL=datas[i];
     		}
     		if(count==4)
            {
            	break;
            }
            count++; 
     	}
    }catch(Exception e)
     	{
     		e.printStackTrace();
     	}
        return RDOTURL;
}

public static void clearLog(){
    try {
    	
            Process p = Runtime.getRuntime().exec(clearlogs);

   } catch (IOException e) 
    {
    e.printStackTrace();
    }
   
}

public static void main(String args[]) throws IOException, InterruptedException
{
	/*
	 String[] mediaselector1 = getAdbLogCat(mediaselector);
	for(int i=0;i<=mediaselector1.length;i++)
	{
		System.out.println("The MediaSelector Request is "+mediaselector1[i]);
	}
	*/
	
	// clearLog();
	 try {
		getRDOTURL(rdot);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 String rs = getRDOTURL(rdot);
     //System.out.println("The RDOT Request is "+rs);
	 
	 
	 getMediaSelectorURL(mediaselector);
	 String ms = getMediaSelectorURL(mediaselector);
	 System.out.println("The MediaSelector Request is "+ms);
	
//	 getRDOTURL(rdot);
	 
	 
//	getAdbLogCat2(mediaselector);
//	getAdbLogCat(rdot);

	
	 
	}

	
}
