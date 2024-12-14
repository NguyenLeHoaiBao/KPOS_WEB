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
import pageObject.KposPageObject;
import pageObject.WebPageObject;
import pageObject.VerifyItem;

import static commons.PageGeneratorManager.getLoginPage;

public class KM_giamgia04 extends AbstractPage {
    private WebDriver webDriver;
    private AppiumDriver mobileDriver;
    private WebPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private VerifyItem verifyItem;
    private KposPageObject kposPageObject;

    private String Barcode1 = "8935360200161";
    private String Customer = "0938612787";
    private String CustomerOL = "210818694874416373";
    private String promotionText = "KM giam gia 10% Không giới hạn AUTO";
    private String priceExpected = "45.000";
    private String priceItemline = "50,000";
    private String priceExpectedKDB = "45,000";


    @BeforeClass
    public void beforeClass() {
        // Initialize drivers
        mobileDriver = config.DriverFactory.getMobileDriver();
        webDriver = config.DriverFactory.getWebDriver();
        kposPageObject = new KposPageObject(mobileDriver);

        // Initialize page objects
        verifyItem = new VerifyItem(mobileDriver);

    }

    @Test
    public void TC01_KM_giamgia_unlimit() throws InterruptedException {
        mobileDriver.launchApp();
//  Đăng nhập KPOS:
        kposPageObject.loginToKposApp();

//  Click tạo bill mới:
        kposPageObject.clickTaodon();
//  Click search box và thêm sản phẩm:
        kposPageObject.themBarcode(Barcode1);
//        Click chon KH OL
        kposPageObject.processCustomerOL(CustomerOL);
        sleepInSeconds(5);

        String InvoiceCode = getTextFromKP(mobileDriver, LoginScreenLocatorKPOS.billNumber);
        System.out.println("Hóa đơn: " + InvoiceCode);

//  Kiểm tra đơn giá của Line được KM:
        verifyItem.verifyPriceItem(Barcode1, priceExpected);
        sleepInSeconds(3);
        verifyItem.verifyPromotionText(Barcode1, promotionText);

//  Chon PTTT tien mat:
        kposPageObject.cashCharge();

//      Kiem tra hoa don tren web
        openUrl(webDriver, GlobalConstants.URL);
        loginPage = getLoginPage(webDriver);
        dashboardPage = loginPage.loginFlow();
        sleepInSeconds(5);

        loginPage.gotoInvoicelist();
        sleepInSeconds(10);
        loginPage.detailInvoice(InvoiceCode);
        sleepInSeconds(3);

        loginPage.verifyPriceInvoiceline(Barcode1, priceItemline);
        loginPage.verifyTotalPriceItem(priceExpectedKDB, "Khách cần trả");
        sleepInSeconds(2);
    }

    @AfterClass
    public void afterClass() {
        // Cleanup drivers
        config.DriverFactory.quitMobileDriver();
        config.DriverFactory.quitWebDriver();
    }
}




