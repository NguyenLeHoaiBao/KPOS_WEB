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

import static commons.PageGeneratorManager.getLoginPage;

public class KM_multi extends AbstractPage {
    private WebDriver webDriver;
    private AppiumDriver mobileDriver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private VerifyItem verifyItem;

    private String Barcode1 = "SPAUTOTEST07";
    private String Barcode2 = "SPAUTOTEST05";
    private String Barcode3 = "SPAUTOTEST04";
    private String Customer = "0938612787";
    private String CustomerOL = "210818694874416373";
    private String promotionText = "KM AUTO MULTi SP GIAM GIA";
    private String priceExpected1 = "25.500";
    private String priceExpected2 = "34.800";
    private String priceExpected3 = "23.500";
    private String priceItemline1 = "25,500";
    private String priceItemline2 = "43,500";
    private String priceItemline3 = "33,500";
    private String priceExpectedKDBtotal = "83,800";
    private String tienkhachdua = "84,000";
    private String thoitienle = "200 (44 điểm)";


    @BeforeClass
    public void beforeClass() {
        // Initialize drivers
        mobileDriver = config.DriverFactory.getMobileDriver();
        webDriver = config.DriverFactory.getWebDriver();

        // Initialize page objects
        verifyItem = new VerifyItem(mobileDriver);

    }

    @Test
    public void TC01_KM_multitotal() throws InterruptedException {
        mobileDriver.launchApp();
//  Đăng nhập KPOS:
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.USERNAME);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.USERNAME, GlobalConstants.USERNAME);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.PASSWORD);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.PASSWORD, GlobalConstants.PASSWORD);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.LOGIN_BUTTON);

//  Click tạo bill mới:
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.NEWBILL_BUTTON);

//  Click search box và thêm sản phẩm 1:
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode1);
        sleepInSeconds(2);

        //  Click search box và thêm sản phẩm 2:
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode2);
        sleepInSeconds(2);

        //  Click search box và thêm sản phẩm 3:
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode3);
        sleepInSeconds(2);

//        Click chon KH OL
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_OL);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ID);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ID, CustomerOL);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ACEPTED);
        sleepInSeconds(6);

        String InvoiceCode = getTextFromKP(mobileDriver, LoginScreenLocatorKPOS.billNumber);
        System.out.println("Hóa đơn: " + InvoiceCode);
        sleepInSeconds(3);

//  Kiểm tra đơn giá của Line được KM:
        verifyItem.verifyPriceItem(Barcode1, priceExpected1);
        sleepInSeconds(3);
        verifyItem.verifyPromotionText(Barcode1,promotionText);

        verifyItem.verifyPriceItem(Barcode2, priceExpected2);
        sleepInSeconds(3);
        verifyItem.verifyPromotionText(Barcode2,promotionText);

        verifyItem.verifyPriceItem(Barcode3, priceExpected3);
        sleepInSeconds(3);
        verifyItem.verifyPromotionText(Barcode3,promotionText);

//  Chon PTTT tien mat:
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.CASHBUTTON);
        sleepInSeconds(2);

//  Click button thanh toan:
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.PAYBUTTON);

//      Kiem tra hoa don tren web
        openUrl(webDriver, GlobalConstants.URL);
        loginPage = getLoginPage(webDriver);
        dashboardPage = loginPage.loginFlow();
        sleepInSeconds(5);

        loginPage.gotoInvoicelist();
        sleepInSeconds(10);
        loginPage.detailInvoice(InvoiceCode);
        sleepInSeconds(3);

        loginPage.verifyPriceInvoiceline(Barcode1,priceItemline1);
        loginPage.verifyPriceInvoiceline(Barcode2,priceItemline2);
        loginPage.verifyPriceInvoiceline(Barcode3,priceItemline3);
        loginPage.verifyTotalPriceItem(priceExpectedKDBtotal, "Khách cần trả");
        loginPage.verifyTotalPriceItem(tienkhachdua,"Tiền khách đưa");
        loginPage.verifyTotalPriceItem(thoitienle,"Thối tiền lẻ");
        sleepInSeconds(2);
    }

    @AfterClass
    public void afterClass() {
        // Cleanup drivers
        config.DriverFactory.quitMobileDriver();
        config.DriverFactory.quitWebDriver();
    }
}




