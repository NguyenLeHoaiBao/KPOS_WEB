package pageObjectKPOS;

import appLocator.LoginScreenLocatorKPOS;
import commons.AbstractPage;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.appium.java_client.AppiumDriver;
import pageObject.DashboardPageObject;
import pageObject.KposPageObject;
import pageUI.LoginPageUI;

public class LoginKPOSObject extends AbstractPage {
    AppiumDriver driver;

    public LoginKPOSObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public LoginKPOSObject inputUserName() {
        sendkeyToElement(driver, String.valueOf(LoginScreenLocatorKPOS.USERNAME), GlobalConstants.USERNAME);
        return this;
    }


    public LoginKPOSObject inputPassword() {
        sendkeyToElement(driver, String.valueOf(LoginScreenLocatorKPOS.PASSWORD), GlobalConstants.PASSWORD);
        return this;
    }

    public void clickLoginButton() {
        clickToElement(driver, String.valueOf(LoginScreenLocatorKPOS.LOGIN_BUTTON));
    }


    public KposPageObject loginFlow() {
        LoginKPOSObject loginPage = new LoginKPOSObject(driver);
        loginPage.inputUserName()
                .inputPassword()
                .clickLoginButton();
        return PageGeneratorManager.getKposPage(driver);
    }

//    public DashboardPageObject loginFlowWithInvalidUsernanePassword(String userName, String password) {
//        LoginKPOSObject loginPage = new LoginKPOSObject(driver);
//        loginPage.sendkeyToElement(driver,LoginPageUI.USERNAME,userName);
//        loginPage.sendkeyToElement(driver,LoginPageUI.PASSWORD,password);
//        loginPage.clickLoginButton();
//        return PageGeneratorManager.getDashboardPage(driver);
//    }

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
