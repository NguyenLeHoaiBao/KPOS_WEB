package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUI.DashboardPageUI;

public class DashboardPageObject extends AbstractPage {
    WebDriver driver;

    public DashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginSuccess() {
        waitForElementVisible(driver, DashboardPageUI.AVATAR_ICON);
        return isElementDisplay(driver, DashboardPageUI.AVATAR_ICON);
    }


}
