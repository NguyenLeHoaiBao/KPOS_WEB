package testcase.KPOS.autoPromotion;

import appLocator.LoginScreenLocatorKPOS;
import commons.AbstractPage;
import commons.GlobalConstants;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.DashboardPageObject;
import pageObject.LoginPageObject;
import pageObject.VerifyItem;
import pageUI.LoginPageUI;

import static commons.PageGeneratorManager.getLoginPage;

public class KM_giaban02 extends AbstractPage {
    private WebDriver webDriver;
    private AppiumDriver mobileDriver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private VerifyItem verifyItem;

    private String Barcode1 = "8938502118157";
    private String Customer = "0938612787";
    private String CustomerOL = "210818694874416373";
    private String promotionText = "KM giam gia 10% pepsi";
    private String priceExpected = "169.000";
    private String priceExpectedKDB = "169,000";


    @BeforeClass
    public void beforeClass() {
        // Initialize drivers
        mobileDriver = config.DriverFactory.getMobileDriver();
        webDriver = config.DriverFactory.getWebDriver();

        // Initialize page objects
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
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode1);
        sleepInSeconds(2);
//
//        Click chon KH OL
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_OL);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ID);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ID, CustomerOL);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ACEPTED);


        String Invoicecode = getTextFromKP(mobileDriver, LoginScreenLocatorKPOS.billNumber);
        System.out.println("Hóa đơn: " + Invoicecode);


//  Kiểm tra đơn giá của Line được KM:
        verifyItem.verifyPriceItem(Barcode1, priceExpected);
        sleepInSeconds(3);

//  Chon PTTT tien mat:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CASHBUTTON);
        sleepInSeconds(2);

//  Click button thanh toan:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.PAYBUTTON);

//  Kiểm tra elemement con hien thi hay khong:
//        verifyItem.verifyPriceItemDisable(Barcode1);


//      Kiem tra hoa don tren web
        openUrl(webDriver, GlobalConstants.URL);
        loginPage = getLoginPage(webDriver);
        dashboardPage = loginPage.loginFlow();
        sleepInSeconds(5);

        clickToElement(webDriver, LoginPageUI.Sell);
        clickToElement(webDriver, LoginPageUI.Invoice);
        clickToElement(webDriver, LoginPageUI.InvoiceSearch);
        sleepInSeconds(2);
        loginPage.detailInvoice(Invoicecode);
        loginPage.verifyTotalPriceItem(priceExpectedKDB);
        sleepInSeconds(10);
    }


    @AfterClass
    public void afterClass() {
        // Cleanup drivers
        config.DriverFactory.quitMobileDriver();
        config.DriverFactory.quitWebDriver();
    }
}




