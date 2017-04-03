package main.java.test.smpMainTest;

import java.io.File;

import org.openqa.selenium.By;

public class SubStringExample{
	   public static void main(String args[]) {
		String str = new String("MF - VOD - Production (Combined profiles, HD) : b081ztv2 mediaselector:live  ");
		
		String str1 = new String(
				"MF - AOD - Production (Audio international with progressive download) : p03syf49  mediaselector:live  ");

		String liveEpsiode = "MF - Video Live - Production (BBC Two, HD Simulcast) : bbc_two_hd";

		String iosa = "Audio Factory Live to AOD";

		String iosa1 = "MF - Video Live - Stage (BBC Radio One Video, SD Webcast";

		String iosliveEpsiode = "MF - VOD - Production (Combined profiles, HD)";

		// System.out.println("Substring starting from index 15:");
		// System.out.println(str.substring(15));
		// System.out.println("Substring starting from index 15 and ending at
		// 20:");
		//
		// String requiredstring = str.substring(0,55);
	       
		// System.out.println("Substring iosa:- " + iosa.substring(0, 25));
	       
		System.out.println("Substring iosa1 is:- " + iosa1.substring(0, 45));

		System.out.println("Substring iosliveEpsiode is:- " + iosliveEpsiode.substring(0, 45));
		
		String userHome = System.getProperty("user.home");
		
		String filePath = new File("").getAbsolutePath();
	
		System.out.println("File  path is :- "+userHome);
	       
	       
		System.out.println("Second File  path is :- "+filePath);
		
		String progress = "In Progress - 13.5%";
		
		System.out.println("progress text is "+progress.substring(0, 12));
		
		
		
		for(int i=1;i<6;i++)
		{
			for(int j=1;j<3;j++)
			{
		
		//String text = iosdriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell['"+i+"']/UIAStaticText['"+i+"']")).getText();
			System.out.println("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell["+j+"]/UIAStaticText["+i+"]");
		}
		}

	}
}