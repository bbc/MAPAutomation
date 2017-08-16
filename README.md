# MAPAutomation
Automating of MAP Component using Appium , testNG framework

Components Automated are :

SMP-AN
SMP-iOS
DRM-iOS
DRM-AN


# Executing Scripts :

## SMP-AN : with TestNG ( need to manually pass the device info's)

To run scripts , run the SMP-AN-MainTest.xml file .  
**Note:**  Change the device ID, OS version  to the devices connected in your system in the XML file , before you run it .

## SMP-AN : with TestNG ( no need to pass device info's)

If you want to just connect the devices and run Automation, 

Then run the Class “ MAPAutomation/Automation/src/main/java/test/smpMainTest/SMPAndroidParallelExecution.java” . It just run’s on the 

devices which are connected to the system. Here you no need to change device id/OS/device name etc

# Report
After execution a ExtentReport will be created under Result folder with DeviceName+TestName

# iOS 

```Please use the Appium iOS Setup document attached```

You need a provisioned device

iOS dev certs

Latest Xcode

# Running the Android PUMA Test on your local machine

Test can be found here :- MAPAutomation/Automation/src/SMPANPUMA/PUMATest/Android_PumaTests.java

All the Functions and Page Objects can be found in Folder : MAPAutomation/Automation/src/SMPANPUMA

Steps to run :
- goto thebin Folder and remove the files. To do that run below command
```rm -rf *```

- Have your all library into a shell script , to do that run the below command
```lib=$(find Automation/lib -name "*jar" | tr "\n" ":")```
Type echo $lib, will show the library files

- Then compile the source by running below command To create class/bin file

```javac -d bin -sourcepath Automation/src -cp $lib:bin Automation/src/SMPANPUMA/PUMATest/Android_PumaTests.java```

- run the appium server from local by just typing 'appium' form terminal

- To run the SMP-AN-Puma test , run below command

 ```java -cp bin:$LIB -DappiumPort="pass appium port" -DdeviceID="pass deviceID -DdeviceOS="pass device OS -DdeviceName="pass device name"  -DappPath="pass APK path"  org.testng.TestNG /Automation/SMP-AN-PUMA.xml```

- Once the exceution done, goto the MAPAutomation/Result folder to view the HTMLreport and MAPAutomation/Screenshots for all the screenshot taken from the test

