package main.java.test.smpMainTest;

import java.io.*;
class CreateDirectory 
{
	
	public static String ResultFolder()
	{
		String strManyDirectories=null;
		 try{
			  String strDirectoy ="Result";
			  strManyDirectories= strDirectoy+ File.separator+"iOS_DRM/report/Harish";

			  // Create one directory
			  boolean success = (
			  new File(strDirectoy)).mkdir();
			  if (success) {
			  System.out.println("Directory: " 
			   + strDirectoy + " created");
			  }  
			  // Create multiple directories
			  success = (new File(strManyDirectories)).mkdirs();
			  if (success) {
			  System.out.println("Directories: " 
			   + strManyDirectories + " created");
			  }

			  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		 return strManyDirectories;
			  }
	
	

 public static void main(String args[])
  {
 
	 String folders = ResultFolder();
}

 }