package SMPANPUMA.CommonFunction;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class AppiumDriverBuilder<DRIVER extends AppiumDriver<?>> {

    private static final String appiumEndPoint = "http://0.0.0.0:%s/wd/hub";
    private static final String appiumHiveEndPoint = "http://127.0.1.1:%s/wd/hub";
    private static final String defaultAppiumPort = "4723";

    private static URL getEndpoint(Environment environment) throws MalformedURLException {
        if (environment != Environment.HIVE) {
            return new URL(String.format(appiumEndPoint, environment == Environment.LOCAL ? defaultAppiumPort : System.getenv("APPIUM_PORT")));
        } else {
            return new URL(String.format(appiumHiveEndPoint, System.getenv("APPIUM_PORT")));
        }
    }

    public static AndroidDriverBuilder forAndroid() {
        return new AndroidDriverBuilder();
    }

    public static IOSDriverBuilder forIOS() {
        return new IOSDriverBuilder();
    }

    public static class AndroidDriverBuilder extends AppiumDriverBuilder<AndroidDriver<?>> {
        public AndroidDriver<?> build(Environment environment) throws IOException {
            DesiredCapabilities capabilities = DesiredCapabilitiesBuilder.forAndroid().build(environment);
            return new AndroidDriver<>(getEndpoint(environment), capabilities);
        }
    }

    public static class IOSDriverBuilder extends AppiumDriverBuilder<IOSDriver<?>> {
        public IOSDriver<?> build(Environment environment) throws IOException {
            DesiredCapabilities capabilities = DesiredCapabilitiesBuilder.forIOS().build(environment);
            return new IOSDriver<>(getEndpoint(environment), capabilities);
        }
    }

    public abstract DRIVER build(Environment environment) throws IOException;
}
