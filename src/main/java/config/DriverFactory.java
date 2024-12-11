package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static ThreadLocal<AppiumDriver> mobileDriver = new ThreadLocal<>();
    private static boolean isHeadlessMode = Boolean.parseBoolean(System.getProperty("headless", "false"));

    // Initialize and return WebDriver for Selenium for (headless mode use mvn test -Dheadless=true)
    public static WebDriver getWebDriver() {
        if (webDriver.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            if (isHeadlessMode) {
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            } else {
                options.addArguments("--start-maximized");
            }

            WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            webDriver.set(driver);
        }
        return webDriver.get();
    }

    // Initialize and return AppiumDriver for Appium
    public static AppiumDriver getMobileDriver() {
        if (mobileDriver.get() == null) {
            try {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("autoGrantPermissions", true);
                caps.setCapability("platformName", "Android");
                caps.setCapability("automationName", "UiAutomator2");
                caps.setCapability("deviceName", "emulator-5554");
                caps.setCapability("appPackage", "kingfood.kpos.app.staging");
                caps.setCapability("appActivity", "kingfood.co.kpos.MainActivity");
                caps.setCapability("autoGrantPermissions", true);

                AppiumDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                mobileDriver.set(driver);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize Appium driver");
            }
        }
        return mobileDriver.get();
    }

    // Cleanup WebDriver
    public static void quitWebDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }

    // Cleanup AppiumDriver
    public static void quitMobileDriver() {
        if (mobileDriver.get() != null) {
            mobileDriver.get().quit();
            mobileDriver.remove();
        }
    }
}
