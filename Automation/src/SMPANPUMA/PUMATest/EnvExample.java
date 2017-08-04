package SMPANPUMA.PUMATest;

public class EnvExample {

	public static void main(String args[])
	{
		String javaHome = System.getProperty("java.class.path");
		System.out.println("The values:-"+javaHome);
		
		String varValue = System.getenv("ANDROID_HOME");
		System.out.println("Value: " + varValue);
		
		
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
