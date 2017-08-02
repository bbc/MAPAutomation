package SMPANPUMA.Utils;

import main.java.test.smpUtilityFunctions.PortFactory;

public class AvailabelPorts {
	
	public static void main(String[] args) throws Exception 
	{
		PortFactory portFactory = new PortFactory();
		for (int i = 0; i < 10; i++)
			System.out.println(portFactory.create());
	}

}
