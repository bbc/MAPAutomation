package main.java.test.smpMainTest;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Example {
	
	public static void main(String args[]) throws IOException
	{
	
	String[] arguments = new String[] {"/usr/local/bin/ideviceinfo"};
	Process p =Runtime.getRuntime().exec(arguments);
	BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	String line = "";
	String allLine = "";
	PrintStream out = new PrintStream(new FileOutputStream("/Users/ramakh01/Desktop/MAPAutomation/iPhone.txt"));
	while((line=r.readLine()) != null)
	{
	System.out.println(line);
    //out.println(line);
	//out.close();
	System.setOut(out);

	}
	
}
}
