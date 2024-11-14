package testcase;

import config.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class App {
    private static AppiumDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = DriverFactory.getMobileDriver();
        // Initialize page objects for mobile if needed
    }

//    @Test
    public void testMobileLogin() {
        System.out.println("app");
    }

    @AfterClass
    public static void tearDown() {
        DriverFactory.quitDrivers();
    }
}
