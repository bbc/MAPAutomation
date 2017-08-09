package SMPANPUMA.PUMATest;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

public class EnvExample {

	public static void main(String args[])
	{
		String javaHome = System.getProperty("java.class.path");
		System.out.println("The values:-"+javaHome);
		
		String varValue = System.getenv("ANDROID_HOME");
		System.out.println("Android Value: " + varValue);
		
		
		//getting username using System.getProperty in Java
	       String user = System.getProperty("user.name") ;
	       System.out.println("Username using system property: "  + user);
	    
	  
	        
	     //name and value of all environment variable in Java  program
//	      Map<String, String> env = System.getenv();
//	        for (String envName : env.keySet()) {
//	            System.out.format("%s=%s%n", envName, env.get(envName));
//	        }

	
	        Properties p = System.getProperties();
	        Enumeration keys = p.keys();
	        while (keys.hasMoreElements()) {
	            String key = (String)keys.nextElement();
	            String value = (String)p.get(key);
	            System.out.println(key + ": " + value);
	        }
	        
		
		
//		java.util.Properties p = System.getProperties();
//	     java.util.Enumeration keys = p.keys();
//	     while( keys.hasMoreElements() ) 
//	     {
//	         System.out.println( keys.nextElement() );
//	     }
		/*for (String env: args) {
            String value = System.getenv(env);
            if (value != null) {
                System.out.format("%s=%s%n",
                                  env, value);
            } else {
                System.out.format("%s is"
                    + " not assigned.%n", env);
            }
        }*/
	}
	
}
