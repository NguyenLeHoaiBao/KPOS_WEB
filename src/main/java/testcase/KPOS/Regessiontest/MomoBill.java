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
import pageObject.WebPageObject;
import pageObject.VerifyItem;

import static commons.PageGeneratorManager.getLoginPage;

public class MomoBill extends AbstractPage {
    private WebDriver webDriver;
    private AppiumDriver mobileDriver;
    private WebPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private VerifyItem verifyItem;

    private String Barcode = "8935302300485";
    private String Barcode2 = "8938502118157";
    private String Customer = "01236555446";
    private String CustomerOL = "210818694874416373";
    private String promotionText = "KM giam gia 10% pepsi";
    private String priceExpected1 = "28.500";
    private String priceExpected2 = "169.000";
    private String priceExpectedKDB = "197,500";


    @BeforeClass
    public void beforeClass() {
        webDriver = config.DriverFactory.getWebDriver();
        mobileDriver = config.DriverFactory.getMobileDriver();
        verifyItem = new VerifyItem(mobileDriver);
    }

    @Test
    public void TC01_onelifeInvivoice() throws InterruptedException {
        mobileDriver.launchApp();
//  Đăng nhập KPOS:
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.USERNAME);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.USERNAME, GlobalConstants.USERNAME);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.PASSWORD);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.PASSWORD, GlobalConstants.PASSWORD);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.LOGIN_BUTTON);

//  Click tạo bill mới:
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.NEWBILL_BUTTON);

//  Click search box và thêm sản phẩm:
        // Barcode 1
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode);
        sleepInSeconds(5);
        verifyItem.verifyPriceItem(Barcode, priceExpected1);

        //barcode 2
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode2);
        sleepInSeconds(5);
        verifyItem.verifyPriceItem(Barcode2, priceExpected2);

//  Click khách hàng và thêm khách hàng mới
//        sleepInSeconds(2);
//        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_SEARCH);
//        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_SEARCH, Customer);
//        sleepInSeconds(1);


//        Click chon KH OL
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_OL);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ID);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ID, CustomerOL);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ACEPTED);

        String Invoicecode = getTextFromKP(mobileDriver, LoginScreenLocatorKPOS.billNumber);
        System.out.println("Hóa đơn: " + Invoicecode);
        sleepInSeconds(3);

////        Kiểm tra text KM:
//        verifyTextKm();


//  Chon PTTT OneLife:
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.CHOSENPAYMENT_BUTTON);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.QRCODE_BUTTON);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.OK_BUTTON);
        sleepInSeconds(3);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.PAYBUTTON);

        sleepInSeconds(10);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.Accepted_QRCODE);
        sleepInSeconds(5);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.Accepted_QRCODE);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.OK_OL);
//

//      Kiem tra hoa don tren web
        openUrl(webDriver, GlobalConstants.URL);
        loginPage = getLoginPage(webDriver);
        dashboardPage = loginPage.loginFlow();
        sleepInSeconds(5);

        loginPage.goInvoicelist();
        loginPage.detailInvoice(Invoicecode);
        loginPage.verifyTotalPriceItem(priceExpectedKDB,"Khách cần trả");
    }


    @AfterClass
    public void afterClass() {
        // Cleanup drivers
        config.DriverFactory.quitMobileDriver();
        config.DriverFactory.quitWebDriver();
    }
}




