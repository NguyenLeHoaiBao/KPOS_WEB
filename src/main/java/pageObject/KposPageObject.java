package pageObject;

import commons.AbstractPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import pageUI.DashboardPageUI;

public class KposPageObject extends AbstractPage {
    AppiumDriver driver;

    public KposPageObject(AppiumDriver driver) {
        this.driver = driver;
    }


}
