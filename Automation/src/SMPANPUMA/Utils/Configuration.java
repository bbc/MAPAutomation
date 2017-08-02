package SMPANPUMA.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    public static String PLATFORM_VERSION;
    public static String PLATFORM_NAME;
    public static String DEVICE_NAME;
    public static String APP_LOCAL;
    public static String APP_CI;
    public static String AVD;

    public static void load(String filename) throws IOException {
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
}
