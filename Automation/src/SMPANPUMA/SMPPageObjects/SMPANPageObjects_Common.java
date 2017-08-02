package SMPANPUMA.SMPPageObjects;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class SMPANPageObjects_Common {

	public void SMPANPageObjects_Common() {

	}
	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/turn_subtitles_on_button")
	public WebElement vod_play_subtitle;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Enable Live Rewind']")
	public WebElement liverewind_button;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc'More options']")
	public WebElement avtest_menu; 

	@AndroidFindBy(xpath = "//android.support.v7.widget.ao[@index='1']")
	public WebElement menu;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public WebElement menuoptions;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView[@index='0']")
	public WebElement home;

	@AndroidFindBy(xpath = "//android.widget.ListView/android.widget.LinearLayout[1]/android.widget.TextView[@index='0']")
	public WebElement bbc_one;

	@AndroidFindBy(xpath = "//android.widget.ListView/android.widget.LinearLayout[2]/android.widget.TextView[@index='0']")
	public WebElement bbc_one_london;

	@AndroidFindBy(xpath = "//android.widget.ListView/android.widget.LinearLayout[3]/android.widget.TextView[@index='0']")
	public WebElement bbc_two;

	@AndroidFindBy(xpath = "//android.widget.ListView/android.widget.LinearLayout[2]/android.widget.ImageButton[@index='1']")
	public WebElement live_rewind_playback;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/stop_button")
	public WebElement simulcast_stop;
	public String stop_button = "uk.co.bbc.avtestharnesssmp:id/stop_button";

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/volume_button")
	public WebElement volume_button;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/live_icon")
	public WebElement live_icon;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/error_message")
	public WebElement error_message;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/retry_button")
	public WebElement try_again_button;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/buffering_spinner")
	public WebElement buffer_spinner;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/pause_button")
	public WebElement Playback_Pause;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/play_button")
	public WebElement LiveRewind_Playbutton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='VPID Browser']")
	public WebElement vpid_browser;

	// @AndroidFindBy(xpath="//android.widget.EditText[@text='Enter vpid']")
	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/textVpid")
	public WebElement vpid_enter;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/buttonLoad")
	public WebElement vpid_load;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/placeholder_play_button")
	public WebElement play_button;
	
	@AndroidFindBy(xpath= "//android.widget.TextView[@text='Use Live RDot Environment' and @index='0']")
	public WebElement enable_liveRdot;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/placeholder_play_button")
	public WebElement playbutton;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/fullscreen_button")
	public WebElement fullscreen_button;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/fullscreen_button")
	public WebElement playback_fullscreen;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/buffering_spinner")
	public WebElement vod_buffer;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/error_message")
	public WebElement vod_error_message;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/error_button")
	public WebElement vod_error_OK_button;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/retry_button")
	public WebElement vod_tryagain_button;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/retry_button")
	public WebElement vod_try_again_button;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/exit_fullscreen_button")
	public WebElement vod_play_fullscreen_exit;

	public String Simulcast_assertions[] = {

			"uk.co.bbc.avtestharnesssmp:id/stop_button",
			"uk.co.bbc.avtestharnesssmp:id/volume_button",
			"uk.co.bbc.avtestharnesssmp:id/live_icon",
			"uk.co.bbc.avtestharnesssmp:id/exit_fullscreen_button"
			};
	
	public String Simulcast_assertions_buttonNotPresent[] = {

			"uk.co.bbc.avtestharnesssmp:id/seek_progress_bar",
			"uk.co.bbc.avtestharnesssmp:id/pause_button",
			"uk.co.bbc.avtestharnesssmp:id/thumb_container"
			};

	public String LiveRewdinRewind_assertions[] = { "uk.co.bbc.avtestharnesssmp:id/seek_progress_bar",
			"uk.co.bbc.avtestharnesssmp:id/pause_button", "uk.co.bbc.avtestharnesssmp:id/live_icon",
			"uk.co.bbc.avtestharnesssmp:id/volume_button", "uk.co.bbc.avtestharnesssmp:id/exit_fullscreen_button",
			"uk.co.bbc.avtestharnesssmp:id/play_pause_container", "uk.co.bbc.avtestharnesssmp:id/thumb_container"
	};
	
	

	public String OnDemand_assertions[] = { "uk.co.bbc.avtestharnesssmp:id/seek_progress_bar",
			"uk.co.bbc.avtestharnesssmp:id/thumb_container",
			"uk.co.bbc.avtestharnesssmp:id/play_pause_container", "uk.co.bbc.avtestharnesssmp:id/volume_button",
			"uk.co.bbc.avtestharnesssmp:id/duration", "uk.co.bbc.avtestharnesssmp:id/subtitles_button",
			"uk.co.bbc.avtestharnesssmp:id/exit_fullscreen_button", "uk.co.bbc.avtestharnesssmp:id/elapsed",
			"uk.co.bbc.avtestharnesssmp:id/primary_title" };

	public String LiveStation[] = { "uk.co.bbc.avtestharnesssmp:id/play_pause_container",
			"uk.co.bbc.avtestharnesssmp:id/volume_button", "uk.co.bbc.avtestharnesssmp:id/live_icon",
			"uk.co.bbc.avtestharnesssmp:id/simulcast_time", "uk.co.bbc.avtestharnesssmp:id/exit_fullscreen_button",
			"uk.co.bbc.avtestharnesssmp:id/primary_title"

	};

	public String iOSLiveSimulcast[] = { "//UIAApplication[1]/UIAWindow[2]/UIAStaticText[1]",
			"//UIAApplication[1]/UIAWindow[2]/UIAButton[1]", "//UIAApplication[1]/UIAWindow[2]/UIAStaticText[11]",
			" //UIAApplication[1]/UIAWindow[2]/UIAButton[11]", "//UIAApplication[1]/UIAWindow[2]/UIAButton[6]"

	};

	public String DRM[] = { "uk.co.bbc.drmtestapp.android:id/play_pause_container",
			"uk.co.bbc.drmtestapp.android:id/volume_button", "uk.co.bbc.drmtestapp.android:id/seek_bar",
			"uk.co.bbc.drmtestapp.android:id/subtitles_button", "uk.co.bbc.drmtestapp.android:id/primary_title" };


	public String liveRewind_assertions_text[] = { "Pause Button present", "Seek Bar present", "Live Icon present",
			"Volume button present", "Pause Button present" };
	
	public String liveSimulcast_assertions_text[] = { "Stop Button present",  "Live Icon present","Volume button present","Full Screen Button present" };
	
	public String liveSimulcast_assertionsNotPresent_text[] = { "Pause Button Notpresent",  "Seekbar NotPresent" };
	

	public String LiveIcon_text = "uk.co.bbc.avtestharnesssmp:id/live_icon";

	public String Video_vpid = "p04vj1dg"; // available for 11 months

	public String Audio_Vpid = "b005h8x1";

	public String errormessage = "uk.co.bbc.avtestharnesssmp:id/error_message";

	public String buffermessage = "uk.co.bbc.avtestharnesssmp:id/buffering_spinner";

	public String errorbutton = "uk.co.bbc.avtestharnesssmp:id/error_button";

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/isAudioSwitch")
	public WebElement toggle_audio_on;

	@AndroidFindBy(id = "uk.co.bbc.avtestharnesssmp:id/plugin_middle_layer")
	public WebElement transportControls;

	@AndroidFindBy(className = "android.view.View")
	public WebElement transport;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[1]/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView[@index='0']")
	public WebElement notification_title;

	public String notificationtitle;// = notification_title.getText();

	public String notificationSutitle;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[1]/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView[@index='0']")
	public WebElement notification_Subtitle;

	public String listview = "uk.co.bbc.avtestharnesssmp:id/name";

	public String videoEpisode = "MF - VOD - Production (Combined profiles, HD) : b081ztv2";

	public String audioEpisode = "MF - AOD - Production (Audio international with progress";

	public String liveEpsiode = "MF - Video Live - Production (BBC Two, HD Simulcast) : b";

	public List<WebElement> element;
	
	public String ParentDirectoy = "Results/PUMA";
	
	public String SubDirectory =  "Screenshots";
	
	public String Android_Path= "/../../../MAP_Automation/MAPAutomation/Automation/BuildsSMP-AN/SMP-AN-28.4452-dev.apk";

}
