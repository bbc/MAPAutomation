package SMPANPUMA.CommonFunction;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class DesiredCapabilitiesBuilder 
{
	public static String PLATFORM_VERSION;
    public static String PLATFORM_NAME;
    public static String DEVICE_NAME;
    public static String APP_LOCAL;
    public static String APP_CI;
    public static String AVD;

    
    public static void Configuration(String filename) throws IOException
    {
        FileInputStream input = new FileInputStream(filename);
        Properties prop = new Properties();
        prop.load(input);

        PLATFORM_VERSION = prop.getProperty("platform.version");
        PLATFORM_NAME = prop.getProperty("platform.name");
        DEVICE_NAME = prop.getProperty("device.name");
        APP_LOCAL = prop.getProperty("app.path.local");
        APP_CI = prop.getProperty("app.path.ci");
        AVD = prop.getProperty("avd");

        input.close();
    }
	
	
	
	
	
    public static AndroidDesiredCapabilitiesBuilder forAndroid() throws IOException {
        Configuration("./Android.properties");
        return new AndroidDesiredCapabilitiesBuilder();
    }

    public static IOSDesiredCapabilitiesBuilder forIOS() throws IOException {
        Configuration("./iOS.properties");
        return new IOSDesiredCapabilitiesBuilder();
    }

    public static class AndroidDesiredCapabilitiesBuilder {
        public DesiredCapabilities build(Environment environment) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            setUpCommonCapabilities(capabilities, environment);
            addAndroidSpecificCapabilities(capabilities, environment);

            return capabilities;
        }
    }

    public static class IOSDesiredCapabilitiesBuilder {
        public DesiredCapabilities build(Environment environment) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            setUpCommonCapabilities(capabilities, environment);
            addIOSSpecificCapabilities(capabilities, environment);

            return capabilities;
        }
    }

    private static void setUpCommonCapabilities(DesiredCapabilities capabilities, Environment environment) {
        if (environment != Environment.HIVE) {
            File absoluteAppPath = new File(System.getProperty("user.dir"), getAppPath(environment));
            capabilities.setCapability(MobileCapabilityType.APP, absoluteAppPath);
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
    }

    private static void addAndroidSpecificCapabilities(DesiredCapabilities capabilities, Environment environment) {
        if (environment == Environment.HIVE) {
            capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("APK_PATH"));
        }

        if (environment == Environment.LOCAL) {
            capabilities.setCapability(AndroidMobileCapabilityType.AVD, AVD);
        }
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, ".MainActivity");
    }

    private static void addIOSSpecificCapabilities(DesiredCapabilities capabilities, Environment environment) {
        if (environment == Environment.HIVE) {
            capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("APP_BUNDLE_PATH"));
        }
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(IOSMobileCapabilityType.NATIVE_INSTRUMENTS_LIB, true);
    }

    private static String getAppPath(Environment environment) {
        String appPath = "";

        if (environment == Environment.LOCAL) {
            appPath = APP_LOCAL;
        } else if (environment == Environment.CI) {
            appPath = APP_CI;
        }
        return appPath;
    }
}
