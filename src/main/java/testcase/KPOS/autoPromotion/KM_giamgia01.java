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
import pageObject.LoginPageObject;
import pageObject.VerifyItem;

public class KM_giamgia01 extends AbstractPage {
    private WebDriver webDriver;
    private AppiumDriver mobileDriver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private VerifyItem verifyItem;
    private KposPageObject kposPageObject;
    private String Barcode1 = "8934588012112";
    private String Customer = "01236555446";
    private String CustomerOL = "210817903459583221";
    private String promotionText = "KM giam gia 10% pepsi";
    private String priceExpected = "8.910";
    private String priceExpectedKDB = "8,910";


    @BeforeClass
    public void beforeClass() {
        mobileDriver = config.DriverFactory.getMobileDriver();
        verifyItem = new VerifyItem(mobileDriver);
        kposPageObject = new KposPageObject(mobileDriver);
    }

    @Test
    public void TC01_KM_giamgia() throws InterruptedException {
        mobileDriver.launchApp();
//  Đăng nhập KPOS:
        kposPageObject.loginToKposApp();

//  Click tạo bill mới:
        kposPageObject.clickTaodon();

//  Click search box và thêm sản phẩm:
        kposPageObject.themBarcode(Barcode1);
//        Click chon KH OL
        kposPageObject.processCustomerOL(CustomerOL);


        String InvoiceCode = getTextFromKP(mobileDriver, LoginScreenLocatorKPOS.billNumber);
        System.out.println("Hóa đơn: " + InvoiceCode);

//        Kiểm tra text KM:
        verifyItem.verifyPromotionText(Barcode1, promotionText);


//  Kiểm tra đơn giá của Line được KM:
        verifyItem.verifyPriceItem(Barcode1, priceExpected);
        sleepInSeconds(3);

    }

    @AfterClass
    public void afterClass() {
        // Cleanup drivers
        config.DriverFactory.quitMobileDriver();
        config.DriverFactory.quitWebDriver();
    }
}




