package testcase.KPOS.autoPromotion;

import appLocator.LoginScreenLocatorKPOS;
import commons.AbstractPage;
import commons.GlobalConstants;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.DashboardPageObject;
import pageObject.LoginPageObject;
import pageObject.VerifyItem;
import pageUI.LoginPageUI;

import static commons.PageGeneratorManager.getLoginPage;

public class KM_giamgiatien02 extends AbstractPage {
    WebDriver webDriver;
    AppiumDriver mobileDriver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    VerifyItem verifyItem;

    String Barcode = "8935302300485";
    String Customer = "01236555446";
    String CustomerOL = "210818694874416373";
    String promotionText = "KM giam gia 10% pepsi";
    String priceExpected = "28.500";
    String priceExpectedKDB = "28,500";


    @BeforeClass
    public void beforeClass() {
        mobileDriver = config.DriverFactory.getMobileDriver();
        verifyItem = new VerifyItem(mobileDriver);
    }

    @Test
    public void TC01_KM_giaban() throws InterruptedException {
        mobileDriver.launchApp();
//  Đăng nhập KPOS:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.USERNAME);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.USERNAME, GlobalConstants.USERNAME);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.PASSWORD);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.PASSWORD, GlobalConstants.PASSWORD);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.LOGIN_BUTTON);

//  Click tạo bill mới:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.NEWBILL_BUTTON);

//  Click search box và thêm sản phẩm:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode);
        sleepInSeconds(5);
        new Actions(mobileDriver).sendKeys(Keys.ENTER).perform();

//        Click chon KH OL
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_OL);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ID);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ID, CustomerOL);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ACEPTED);

//        verifyItem.verifyPromotionText(Barcode,promotionText);

        String textFromKP = getTextFromKP(mobileDriver, LoginScreenLocatorKPOS.billNumber);
        System.out.println("Hóa đơn: " + textFromKP);

////        Kiểm tra don gia:
        verifyItem.verifyPriceItem(Barcode, priceExpected);

//  Kiểm tra đơn giá của Line được KM:
        sleepInSeconds(3);

//  Chon PTTT tien mat:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CASHBUTTON);
        sleepInSeconds(2);

//      Click button thanh toan:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.PAYBUTTON);

//      Kiểm tra elemement con hien thi hay khong:
        verifyItem.verifyPriceItemDisable(Barcode);

//      Kiem tra hoa don tren web
        webDriver = config.DriverFactory.getWebDriver();
        openUrl(webDriver, GlobalConstants.URL);
        loginPage = getLoginPage(webDriver);
        dashboardPage = loginPage.loginFlow();
        sleepInSeconds(5);

        loginPage.searchAndSubmitInvoice(textFromKP);

        sleepInSeconds(2);
        clickToElement(webDriver, LoginPageUI.totalPriceCell);
        loginPage.verifyTotalPriceItem(priceExpectedKDB);
        sleepInSeconds(3);
    }

    @AfterClass
    public void afterClass() {
        if (webDriver != null) {
            webDriver.close();
        }
        if (mobileDriver != null) {
            mobileDriver.closeApp();
        }
    }
}



