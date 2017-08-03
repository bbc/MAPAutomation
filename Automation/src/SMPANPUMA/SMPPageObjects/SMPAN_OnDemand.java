package SMPANPUMA.SMPPageObjects;



import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SMPAN_OnDemand {

	public AndroidDriver<WebElement> driver;
	public String vod_vpid = "p04rklby";
	public String Total_Duration;

	public SMPAN_OnDemand() {
		// TODO Auto-generated constructor stub
	}


	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/hide_transport_panel_button")
	public WebElement transport;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/subtitles_view")
	public WebElement subtitle_view;


	// @AndroidFindBy(xpath="//android.widget.ListView/android.widget.LinearLayout[10]/android.widget.RelativeLayout/android.widget.TextView[@index='0'
	// and @text='VPID Browser']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='VPID Browser']")
	public WebElement vpid_browser;


	// @AndroidFindBy(xpath="//android.widget.EditText[@text='Enter vpid']")
	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/textVpid")
	public WebElement vpid_enter;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/buttonLoad")
	public WebElement vpid_load;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/seek_progress_bar")
	public WebElement vod_play_seekbar;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/primary_title")
	public WebElement vod_play_title;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/volume_button")
	public WebElement vod_play_volume;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/duration")
	public WebElement vod_play_total_duration;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/elapsed")
	public WebElement vod_play_elasped_duration;

	

	

}
