package pageObject;

import commons.AbstractPage;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUI.LoginPageUI;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageObject inputUserName() {
        sendkeyToElement(driver, LoginPageUI.USERNAME, GlobalConstants.USERNAME);
        return this;
    }


    public LoginPageObject inputPassword() {
        sendkeyToElement(driver, LoginPageUI.PASSWORD, GlobalConstants.PASSWORD);
        return this;
    }

    public void clickLoginButton() {
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }


    public DashboardPageObject loginFlow() {
        LoginPageObject loginPage = new LoginPageObject(driver);
        loginPage.inputUserName()
                .inputPassword()
                .clickLoginButton();

        return PageGeneratorManager.getDashboardPage(driver);
    }

    public DashboardPageObject loginFlowWithInvalidUsernanePassword(String userName, String password) {
        LoginPageObject loginPage = new LoginPageObject(driver);
        loginPage.sendkeyToElement(driver,LoginPageUI.USERNAME,userName);
        loginPage.sendkeyToElement(driver,LoginPageUI.PASSWORD,password);
        loginPage.clickLoginButton();
        return PageGeneratorManager.getDashboardPage(driver);
    }

    public boolean wrongPasswordWarning() {
        waitForElementVisible(driver, LoginPageUI.WRONG_PASSWORD_WARNING);
        return isElementDisplay(driver, LoginPageUI.WRONG_PASSWORD_WARNING);
    }

    public boolean invalidUserNameWarning() {
        waitForElementVisible(driver, LoginPageUI.INVALID_USERNAME_WARNING);
        return isElementDisplay(driver, LoginPageUI.INVALID_USERNAME_WARNING);
    }

    public boolean notEnoughPasswordLetter() {
        waitForElementVisible(driver, LoginPageUI.INVALID_PASSWORD);
        return isElementDisplay(driver, LoginPageUI.INVALID_PASSWORD);
    }
}
