package main.java.test.DRMPageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindAll;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOSDRMPageObjects {
	
	public IOSDriver<WebElement> iosdriver;

	public iOSDRMPageObjects() {

	}

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")
	public WebElement addDownload_Button;

	@iOSFindBy(xpath = " //XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeTextField")
	public WebElement enterVPIDField;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[1]/UIATextField[1]/UIAButton[1]")
	public WebElement vpidClearButton;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[2]/UIATextField[1]")
	public WebElement mediaTypeField;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[2]/UIATextField[1]/UIAButton[1]")
	public WebElement mediaTypeField_Clear;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[2")
	public WebElement cancelButton;

	@iOSFindBy(xpath = " //UIAApplication[1]/UIAWindow[1]/UIANavigationBar[2]/UIAButton[3]")
	public WebElement downloadButton;

	@iOSFindBy(xpath = " //UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")
	public WebElement downloadingStatus;

	@iOSFindBy(xpath = " //UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]")
	public WebElement downloadingVPID;

	@iOSFindBy(xpath = " //UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[3]")
	public WebElement downloadingVPID_FileSize;

	@iOSFindBy(xpath = " //UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[4]")
	public WebElement downloadingVPID_Expiry;

	@iOSFindBy(xpath = " //UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[5]")
	public WebElement downloadingVPID_MediaType;

	@iOSFindBy(xpath = "  //UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAProgressIndicator[1] ")
	public WebElement downloadingVPID_Progress;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]")
	public WebElement pause_resume_button;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1][contains(@name, 'Download Complete')]")
	public WebElement downloadcomplete;

	// @iOSFindBy(xpath="//UIAStaticText[@text='Download Complete']")
	// public WebElement downloadcomplete;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAProgressIndicator[1]")
	public WebElement progressStatus;
	
	@WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSFindBy(accessibility = "Download Complete")
	public WebElement iOSDownload_Complete;
	
	@WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "/XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[/XCUIElementTypeStaticText")
	public WebElement iOSDownload_SecondComplete;
	
	@WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell")
	public WebElement iOSDownloadFirst_Complete;
	
	@WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public WebElement iOSDownloadSecond_Complete;
	
	
	@iOSFindAll({
        @iOSFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[11]XCUIElementTypeOther"),
        @iOSFindBy(accessibility = "Playback position")
})
	public WebElement seekbar;
	
	@iOSFindBy(accessibility = "Playback position")
	public WebElement playbackseekbar;
	
	@iOSFindBy(accessibility = "Remove")
	public WebElement iOSDownload_Remove;
	
	@iOSFindBy(accessibility = "Pause")
	public WebElement iOSDownload_Pause;
	
	@iOSFindBy(accessibility = "Paused")
	public WebElement iOSDownload_Paused;
	
	@iOSFindBy(xpath="//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText")
	public WebElement Download_Status1;
	
	@iOSFindBy(xpath="//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeStaticText")
	public WebElement Download_Status2;
	
	@iOSFindBy(accessibility = "Resume")
	public WebElement iOSDownload_Resume;

	@iOSFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public WebElement audio_downloadcomplete_button;
	
	
	@iOSFindBy(xpath="//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell")
	public WebElement downloadInProgress_button;
	
	@iOSFindBy(xpath="//XCUIElementTypeCel")
	public WebElement cell_element;
	
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")
	public WebElement video_downloadcomplete_button;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAElement[2]") // Citizen
																			// b0378svs,
																			// Identity
																			// Crisis
	public WebElement playbacktitle;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
	public WebElement playbackClose;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[5]")
	public WebElement play_pause_button;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASlider[1]")
	public WebElement volume_slider;

	@iOSFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[11]/XCUIElementTypeOther[3]/XCUIElementTypeStaticText")
	public WebElement playbackduration;

	@iOSFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[11]/XCUIElementTypeOther")
	public WebElement playback_progressbar;
	
	@iOSFindBy(accessibility = "Playback position")
	public WebElement playbackprogressbar;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[9]")
	public WebElement playback_subtitle;

	public String audio_Vpid = "b08l64xc";

	public String video_Vpid = "b08l42h9"; // "p04y8x4y";
	
	public String[] vpids ={"b08th1rx","b08rz09d"};
	
	public String[] vpids_list ={"b08rypgn","b008m5t2","b08t3kfd","b08tc3k8"};
	
	public String[] mediatype ={"Video","Video"};


	WebElement elements[] = { downloadingStatus, downloadingVPID, downloadingVPID_FileSize, downloadingVPID_Expiry,
			downloadingVPID_MediaType };

	public String text[] = { "Vpid_Status", "Vpid_Name", "Vpid_FileSize", "Vpid_Expiry", "Vpid_MediaType" };
	
	@iOSFindBy(xpath="//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[1]")
	public WebElement remove_downloaded_button;
	
	public String ParentDirectoy = "Results/iOS-DRM";
	
	public String SubDirectory =  "Screenshot";
	
}
