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

public class KM_giamgiatien02 extends AbstractPage {
    private WebDriver webDriver;
    private AppiumDriver mobileDriver;
    private WebPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private VerifyItem verifyItem;
    private KposPageObject kposPageObject;

    private String Barcode = "8935302300485";
    private String Customer = "01236555446";
    private String CustomerOL = "210818694874416373";
    private String promotionText = "KM giam gia 10% pepsi";
    private String priceExpected = "28.500";
    private String priceExpectedKDB = "28,500";


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
    public void TC01_KM_giamgiatien_limit() throws InterruptedException {
        mobileDriver.launchApp();
//  Đăng nhập KPOS:
        kposPageObject.loginToKposApp();

//  Click tạo bill mới:
        kposPageObject.clickTaodon();

//  Click search box và thêm sản phẩm:
        kposPageObject.themBarcode(Barcode);

//        Click chon KH OL
        kposPageObject.processCustomerOL(CustomerOL);

//        verifyItem.verifyPromotionText(Barcode,promotionText);

        String Invoicecode = getTextFromKP(mobileDriver, LoginScreenLocatorKPOS.billNumber);
        System.out.println("Hóa đơn: " + Invoicecode);

////        Kiểm tra don gia:
        verifyItem.verifyPriceItem(Barcode, priceExpected);

//  Chon PTTT tien mat:
        kposPageObject.cashCharge();

//      Kiem tra hoa don tren web
        openUrl(webDriver, GlobalConstants.URL);
        loginPage = getLoginPage(webDriver);
        dashboardPage = loginPage.loginFlow();
        sleepInSeconds(5);

        loginPage.gotoInvoicelist();

        sleepInSeconds(2);
        loginPage.detailInvoice(Invoicecode);
        loginPage.verifyTotalPriceItem(priceExpectedKDB,"Khách cần trả");
        sleepInSeconds(10);
    }

    @AfterClass
    public void afterClass() {
        // Cleanup drivers
        config.DriverFactory.quitMobileDriver();
        config.DriverFactory.quitWebDriver();
    }
}




