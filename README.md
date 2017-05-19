# MAPAutomation
Mobile Application Platforms Automation Using Appium Framework
Automating of MAP Component using Appium , testNG framework

Components Automated are :

SMP-AN
SMP-iOS
DRM-iOS
DRM-AN


Executing Scripts :

SMP-AN :

	To run scripts , run the SMP-AN-MainTest.xml file .   It uses the TestNG framework
	Change the device ID, OS version  to the devices connected in your system in the XML file , before you run it .


       If you want to just connect the devices and run Automation, Then run the Class “ MAPAutomation/Automation/src/main/java/test/smpMainTest/SMPAndroidParallelExecution.java” . It just run’s on the devices which are connected to the system. Here you no need to change device id/OS/device name etc

      After execution a ExtentReport will be created under Result folder with DeviceName+TestName
