package testcase.KPOS.Regessiontest;

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

public class OnelifeBill extends AbstractPage {
    WebDriver webDriver;
    AppiumDriver mobileDriver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;

    String Barcode = "8935302300485";
    String Barcode2 = "8938502118157";
    String Customer = "01236555446";
    String CustomerOL = "210818694874416373";
    String promotionText = "KM giam gia 10% pepsi";
    String priceExpected1 = "28.500";
    String priceExpected2 = "169.000";
    String priceExpectedKDB = "197,500";
    VerifyItem verifyItem;


    @BeforeClass
    public void beforeClass() {
//        webDriver = config.DriverFactory.getWebDriver();
        mobileDriver = config.DriverFactory.getMobileDriver();
        verifyItem = new VerifyItem(mobileDriver);
    }

    @Test
    public void TC01_onelifeInvivoice() throws InterruptedException {
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
        // Barcode 1
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode);
        sleepInSeconds(5);
        verifyItem.verifyPriceItem(Barcode, priceExpected1);

        //barcode 2
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode2);
        sleepInSeconds(5);
        verifyItem.verifyPriceItem(Barcode2,priceExpected2);

//  Click khách hàng và thêm khách hàng mới
//        sleepInSeconds(2);
//        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_SEARCH);
//        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_SEARCH, Customer);
//        sleepInSeconds(1);


//        Click chon KH OL
        clickToMobileElem(mobileDriver,LoginScreenLocatorKPOS.CUSTOMER_OL);
        clickToMobileElem(mobileDriver,LoginScreenLocatorKPOS.CUSTOMER_ID);
        sendkeyEntertoElement(mobileDriver,LoginScreenLocatorKPOS.CUSTOMER_ID,CustomerOL);
        clickToMobileElem(mobileDriver,LoginScreenLocatorKPOS.CUSTOMER_ACEPTED);

        String textFromKP = getTextFromKP(mobileDriver, LoginScreenLocatorKPOS.billNumber);
        System.out.println("Hóa đơn: " + textFromKP);
        sleepInSeconds(3);

//  Chon PTTT OneLife:
        clickToMobileElem(mobileDriver,LoginScreenLocatorKPOS.CHOSENPAYMENT_BUTTON);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.ONELIFE_BUTTON);
        clickToMobileElem(mobileDriver,LoginScreenLocatorKPOS.OK_BUTTON);
        sleepInSeconds(3);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.PAYBUTTON);

        sleepInSeconds(11);
        clickToMobileElem(mobileDriver,LoginScreenLocatorKPOS.Accepted_OL);
        clickToMobileElem(mobileDriver,LoginScreenLocatorKPOS.OK_OL);

////      Click button thanh toan:
//        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.PAYBUTTON);

//      Kiểm tra elemement con hien thi hay khong:
        verifyItem.verifyPriceItemDisable(Barcode);

//      Kiem tra hoa don tren web
        webDriver = config.DriverFactory.getWebDriver();
        openUrl(webDriver, GlobalConstants.URL);
        loginPage = getLoginPage(webDriver);
        dashboardPage = loginPage.loginFlow();
        sleepInSeconds(5);

        clickToElement(webDriver, LoginPageUI.Sell);
        clickToElement(webDriver, LoginPageUI.Invoice);
        clickToElement(webDriver, LoginPageUI.InvoiceSearch);
        sendKeyboardToElement(webDriver, LoginPageUI.InvoiceSearch, textFromKP);
        sendKeyboardToElement(webDriver, LoginPageUI.InvoiceSearch, "ENTER");
        sleepInSeconds(2);
        clickToElement(webDriver, LoginPageUI.totalPriceCell);
        sleepInSeconds(3);
        loginPage.verifyTotalPriceItem(priceExpectedKDB);
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




