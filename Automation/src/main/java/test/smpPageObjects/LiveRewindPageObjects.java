package main.java.test.smpPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LiveRewindPageObjects {


		
		public AndroidDriver<WebElement> driver;
		public String retry_message;
		String bbc_two_text;
		public String home_title;
		public String bbc_one_title;
		public String bbc_one_london_title;
		public String simulcasttitle;
		public String simulcasttime;
		public String erromessage;
		public String bbc_two_title;
		
		
		
		public LiveRewindPageObjects()
		{
	    }
		

		@AndroidFindBy(xpath="//android.support.v7.widget.am[@index='1']")
		public WebElement menu_tablet;
		
		//@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
		@AndroidFindBy(xpath="//android.support.v7.widget.am[@index='1']")
		public WebElement menu_phone;
		
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Use Live RDot Environment']")
		public WebElement enable_rDot;
		
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Enable Live Rewind']")
		public WebElement live_rewind_text;
		
		@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='live content']")
		public WebElement live_icon_text;
		//uk.co.bbc.avtestharnesssmp:id/live_icon
		
		@AndroidFindBy(xpath="//android.widget.LinearLayout[6]/android.widget.CheckBox[@index='1']")      ////"//android.widget.CheckBox[contains(@resource-id,'uk.co.bbc.avtestharnesssmp:id/checkbox')]")
		public WebElement Live_rewind_Check;
		
		@AndroidFindBy(id="uk.co.bbc.avtestharnesssmp:id/simulcast_time")
		public WebElement live_simulcat_rewind_time;
		
		@AndroidFindBy(id="uk.co.bbc.avtestharnesssmp:id/seek_bar")
		public WebElement live_rewind_seekbar;
		
		@AndroidFindBy(id="uk.co.bbc.avtestharnesssmp:id/thumb_container")
		public WebElement live_rewind_thumb;
		
	// @AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/seek_progress_bar")
	@AndroidFindBy(xpath = "//android.widget.ProgressBar[@index='1']")
		public WebElement live_rewind_progressbar;
		

		
		


}
