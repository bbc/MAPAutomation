package main.java.test.DRMPageObjects;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidDRMPageObjects {

	public AndroidDriver<WebElement> driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	public  AndroidDRMPageObjects() 
	{
		// TODO Auto-generated constructor stub
	}
	

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public WebElement menuoption;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Downloads']") 
	public WebElement download_option;
	
	@AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/switchEnableOn3G")
	public WebElement mobile_data;
	
	
	
 // @AndroidFindBy(xpath="//android.widget.TextView[contains(@resource-id,'android:id/message') and @text ='Validating'")	
 
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Validating'")
    public WebElement vpid_validator1;
	
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/entry_vpid")
   public WebElement vpid_enter;
   
  // uk.co.bbc.drmtestapp.android:id/entry_vpid
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/buttonDownload")
   public WebElement download_button;
	
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/buttonRemove")
   public WebElement download_remove;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/play_button")
   public WebElement download_play;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/volume_button")
   public WebElement download_volume;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/turn_subtitles_on_button")
   public WebElement download_subtitle;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/duration")
   public WebElement download_total_duration;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/elapsed")
   public WebElement download_elapsed_duration;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/primary_title")
   public WebElement download_title;
   
  // @AndroidFindBy(xpath="//android.widget.TextView[contains(@resource-id,'android:id/message') and @text ='Validating'")
   @AndroidFindBy(id="android:id/message")
   public WebElement vpid_validator;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Paused']")
   public WebElement download_paused;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='In Progress']")
   public WebElement download_progress;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Failed']")
   public WebElement download_Failed;
   
   @AndroidFindBy(xpath="//android.widget.Button[@text='Retry']")
   public WebElement download_Retry_button;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Download Completed']")
   public WebElement download_complete;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Queued']")
   public WebElement download_Queued;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/seek_progress_bar")
   public WebElement download_seek_bar;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/elapsed")
   public WebElement download_vpid_elapsedtime;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/duration")
   public WebElement download_vpid_totalduration;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/subtitles_button")//uk.co.bbc.drmtestapp.android:id/subtitles_button
   public WebElement download_video_subtitle;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/primary_title")
   public WebElement download_playback_vpid_title;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/play_button")
   public WebElement download_vpid_playbutton;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/play_pause_container")
   public WebElement download_vpid_pausebutton;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/volume_button")
   public WebElement download_vpid_volume;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/subtitles_flag")
  // @AndroidFindBy(how = How.ID, using="uk.co.bbc.drmtestapp.android:id/subtitles_flag")
   public WebElement download_vpid_subtitle_flag;
   
   @AndroidFindBy(xpath="//android.widget.TextView[contains(@resource-id,'uk.co.bbc.drmtestapp.android:id/textViewMediaType') and @text='Video']")
   public WebElement media_type;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Video']")
   public WebElement media_type1;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Audio']")
   public WebElement media_type_audio;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/textViewVpid")
   public WebElement download_vpid;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/textViewFileSize")
   public WebElement filesize;
   
   @AndroidFindBy(xpath="//android.widget.Switch[@text='Pause All ON']")
   public WebElement pauseall;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/switchPauseAll")
   public WebElement pause_all;
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[2]/android.widget.ImageView[@index='0']")
   public WebElement download2;

   @AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.ImageView[@index='0']")
   public WebElement download;
   
   
   
   @AndroidFindBy(xpath="//android.widget.FrameLayout[contains[5](@resource-id,'uk.co.bbc.drmtestapp.android:id/subtitles_button') and @content-desc ='enable subtitles'")
   public WebElement download_vpid_subtitle_OFF;
   
   @AndroidFindBy(xpath="//android.widget.FrameLayout[contains[5](@resource-id,'uk.co.bbc.drmtestapp.android:id/subtitles_button') and @content-desc ='disable subtitles'")
   public WebElement download_vpid_subtitle_ON;
   
   @AndroidFindBy(id="uk.co.bbc.drmtestapp.android:id/entry_mediaset")
   public WebElement mediaset_type;

   @AndroidFindBy(xpath="//android.widget.EditText[@text='mobile-download-med' and @index='0']")
   public WebElement mediaset;
   
   @AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
   public WebElement closeall;
   
   
   @AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.Button[@index='1']")
   public WebElement removeButton1;
   
   
   @AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[@index='1']")
   public WebElement removeButton2;
   
    public String ParentDirectoy = "Results/AndroidDRM";
	
	public String SubDirectory =  "Screenshots";

@AndroidFindBy(id = "uk.co.bbc.drmtestapp.android:id/buttonRemove")
public WebElement downloadRemove;
}

