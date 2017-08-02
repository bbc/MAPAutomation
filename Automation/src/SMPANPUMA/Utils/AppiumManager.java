package SMPANPUMA.Utils;

import java.util.Iterator;
import java.util.Map;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumManager {

		
		public static Map<String, String> allDeviceIds = null;
	   public AppiumDriverLocalService appiumService;
	   public String appiumServiceUrl;
	   public AppiumDriverLocalService service;
	   AvailabelPorts ap = new AvailabelPorts();
	   CommandPrompt cp = new CommandPrompt();
	   
	   /**
	 		 * start appium with default port available
	 	
	 		 */
	   public void startAppium() throws Exception
		{
		int port = 1337; // ap.getPort();
	
			  appiumService=AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
		        		.withIPAddress("127.0.0.1")
		        		.usingPort(port));
				appiumService.start();
		   
		}
	   
	   
//	   public void startAppium1() throws Exception
//		{
//		    int port = ap.getPort();
//	
//			  int portint;
//			appiumService=AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
//		        		.withIPAddress("127.0.0.1")
//		        		.usingPort(port)
//		        		.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, (portint)
//		        		.withArgument(Sess)
//		        		.withLogFile(new File("build/${device}.log")));
//				appiumService.start();
//		   
//		}
	   
	   
	   /**
		 * start appium with modified arguments : appium port, chrome port, and bootstap port as user pass port number
		 * @param appium port
		 */
		
		public void startAppium(int port)throws Exception
		{
			appiumService=AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
	        		.withIPAddress("127.0.0.1")
	        		.usingPort(port));
			appiumService.start();
		}
		
		public String AppiumURL()
		{
		return appiumServiceUrl = appiumService.getUrl().toString();
		}
		
		public void stopappium()
		{
			System.out.println("Stop appium service");
			appiumService.stop();

		}
		

		
		
		public void GetConnectedDevices()
		{
			
				
				DeviceConfiguration devices = new DeviceConfiguration();
				Map<String, String> list = null;
				try {
					list = devices.getDivces();
					allDeviceIds = devices.getDivces();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try
				{
				Iterator i = list.entrySet().iterator();
				while(i.hasNext())
				{
				    String key = i.next().toString();  
				    String value = i.next().toString();
				    System.out.println("The device connected are "+" "+key + " " + value);
				}
				}catch ( Exception e)
				{
					e.printStackTrace();
					}
				
				
			}
		
	   
		public static void main(String[] args) throws Exception
		{
			AppiumManager ap = new AppiumManager();
			
			//ap.startAppium();
//			ap.startAppium("127.0.0.2",4780);
//			ap.startAppium("127.0.0.4",4781);
//			ap.AppiumURL();
			ap.GetConnectedDevices();
			
//			String id1 = ap.allDeviceIds.;
//			System.out.println("The Device "+id1);
			
//			ap.stopappium();

		}
	   



}
