package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static java.sql.DriverManager.getDriver;


public class DriverFactory {
        private static WebDriver webDriver;
        private static AppiumDriver mobileDriver;

        public static WebDriver getWebDriver() {
            if (webDriver == null) {
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                webDriver.manage().window().maximize();
            }
            return webDriver;
        }

        public static AppiumDriver getMobileDriver() {
            if (mobileDriver == null) {
                try {
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability("autoGrantPermissions", true);
                    caps.setCapability("platformName", "Android");
                    caps.setCapability("automationName", "UiAutomator2");
                    caps.setCapability("deviceName", "emulator-5554");
                    caps.setCapability("appPackage", "kingfood.kpos.app.staging");
                    caps.setCapability("appActivity", "kingfood.co.kpos.MainActivity");
                    caps.setCapability("autoGrantPermissions", true);
//                    caps.setCapability("newCommandTimeout", 300);

                    mobileDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
                    getMobileDriver().manage().timeouts().implicitlyWait(50L, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return mobileDriver;
        }

        public static void quitDrivers() {
            if (webDriver != null) {
                webDriver.quit();
                webDriver = null;
            }
            if (mobileDriver != null) {
                mobileDriver.quit();
                mobileDriver = null;
            }
        }
    }

