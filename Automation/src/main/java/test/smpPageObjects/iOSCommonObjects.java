package main.java.test.smpPageObjects;

import org.openqa.selenium.WebElement;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOSCommonObjects {

	public IOSDriver<WebElement> driver;
	private StringBuffer verificationErrors = new StringBuffer();

	public iOSCommonObjects() {
		// TODO Auto-generated constructor stub
	}

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[6]")
	public WebElement playback_start_button;

	@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeTableView[1]/XCUIElementTypeCell[3")
	public WebElement BBC_two_Live;
	

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAStaticText[1]")
	public WebElement playback_title;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[1]")
	public WebElement playback_close;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAImage[1]")
	public WebElement playback_transport_control;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]")
	public WebElement playback_transportcontrol;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[6]")
	public WebElement playback_stop;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIASlider[1]")
	public WebElement playback_volume;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[9]")
	public WebElement playback_airplay;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[11]")
	public WebElement playback_PIP; // picture in picture button

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[10]")
	public WebElement playback_subtitle_button;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAStaticText[11]")
	public WebElement playback_duration;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAElement[2]")
	public WebElement playback_progressbar;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAStaticText[2]")
	public WebElement playback_errorMessage;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[3]")
	public WebElement playback_errorDismiss_Button;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[2]/UIAButton[4]")
	public WebElement playback_errorTryagain_Button;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIASegmentedControl[1]/UIAButton[1]")
	public WebElement mediated_tab;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIASegmentedControl[1]/UIAButton[2]")
	public WebElement unmediated_tab;
	
	@iOSFindBy(accessibility="Dismiss")
	public WebElement error_dimiss_button;

	
	@iOSFindBy(xpath="//UIAApplication[1]/UIAWindow[1]/UIATabBar[1]/UIAButton[1]")
	public WebElement avTestHarness;
	
	@iOSFindBy(xpath="//UIAApplication[1]/UIAWindow[1]/UIATabBar[1]/UIAButton[2]")
	public WebElement VPID_Browser;
	
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[7]/UIAScrollView[1]/UIAElement[1]")
	public WebElement airplane_mode;
	
	//@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[7]/UIAScrollView[1]/UIAElement[2]")
	@iOSFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther[5]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther")
	public WebElement wifi_mode;
	
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]")
	public WebElement Alert;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[1]")
	public WebElement update_title;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[2]")
	public WebElement update_message;

	@iOSFindBy(xpath = "//XCUIElementTypeCell[1]/XCUIElementTypeUIAButton[1]")
			//"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[4]/XCUIElementTypeAlert[1]/XCUIElementTypeView[1]/XCUIElementTypeCell[1]/XCUIElementTypeUIAButton[1]")
	public WebElement update_ignoreButton;
	

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[2]")
	public WebElement update_showButton;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[19]/UIAStaticText[2]")
	public WebElement OnDemand_Video;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[7]/UIAButton[1]")
	public WebElement dismiss_controlcentre;

	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[7]/UIAStaticText[1]")
	public WebElement down_chevron;

	@iOSFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther")
//	@iOSFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[2]")
//	@iOSFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow[7]")
	public WebElement iOS9_dismiss_wholewindow;
	
	
	@iOSFindBy(xpath = "//XCUIElementTypeApplication/XCUIElementTypeWindow[6]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[2]")
	//XCUIElementTypeApplication/XCUIElementTypeWindow[7]")
	public WebElement iOS10_dismiss_wholewindow;
	
	
	@iOSFindBy(xpath="//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[5]")
    public WebElement transport_controls; 
	
	public String error_message = "An unknown error occurred";
	
	public String Live_Channel = "MF - Video Live - Production (BBC Two, HD Simulcast)";


}
