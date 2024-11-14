package testcase;


import config.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Web extends DriverFactory {
    WebDriver webDriver;
    AppiumDriver mobileDriver;

    @BeforeClass
    public void beforeClass() {


    }

    @Test
    public void TC01_testWeb(){
      config.DriverFactory.getWebDriver().get("https://www.tinhte.vn");
        System.out.println("Web");

    }

   @Test
    public void TC02_testApp(){
       mobileDriver = config.DriverFactory.getMobileDriver();
       System.out.println("app");

   }

    @Test
    public void TC03_testWeb1(){
        config.DriverFactory.getWebDriver().get("https://www.google.com");
        System.out.println("Web");
    }

    @Test
    public void TC04_testWeb1(){
        mobileDriver = config.DriverFactory.getMobileDriver();
        mobileDriver.launchApp();
        System.out.println("app");
    }
    @Test
    public void TC05_testWeb1(){
        mobileDriver = config.DriverFactory.getMobileDriver();
        System.out.println("web");
    }

}
