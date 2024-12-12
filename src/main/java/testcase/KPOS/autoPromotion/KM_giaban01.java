package testcase.KPOS.autoPromotion;

import commons.AbstractPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.DashboardPageObject;
import pageObject.KposPageObject;
import pageObject.LoginPageObject;
import pageObject.VerifyItem;

public class KM_giaban01 extends AbstractPage {
    private WebDriver webDriver;
    private AppiumDriver mobileDriver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    KposPageObject kposPageObject;
    private VerifyItem verifyItem;

    private String Barcode1 = "8938502118157";
    private String Customer = "0938612787";
    private String CustomerOL = "210817903459583221";
    private String promotionText = "KM gia ban 0d Tom";
    private String priceExpected = "0";
    private String priceExpectedKDB = "0";

    @BeforeClass
    public void beforeClass() {
//      webDriver = config.DriverFactory.getWebDriver();
        mobileDriver = config.DriverFactory.getMobileDriver();
        verifyItem = new VerifyItem(mobileDriver);
        kposPageObject = new KposPageObject(mobileDriver);
    }

    @Test
    public void TC01_KM_giaban() {
        mobileDriver.launchApp();
//  Đăng nhập KPOS:
        kposPageObject.loginToKposApp();

//  Click tạo bill mới:
        kposPageObject.clickTaodon();
//  Click search box và thêm sản phẩm:
        kposPageObject.themBarcode(Barcode1);

//        Click chon KH OL
        kposPageObject.processCustomerOL(CustomerOL);

//  Kiểm tra text KM :
        verifyItem.verifyPromotionText(Barcode1, promotionText);

//  Kiểm tra đơn giá của Line được KM:
        verifyItem.verifyPriceItem(Barcode1, priceExpected);
    }

    @AfterClass
    public void afterClass() {
        // Cleanup drivers
        config.DriverFactory.quitMobileDriver();
        config.DriverFactory.quitWebDriver();
    }
}




