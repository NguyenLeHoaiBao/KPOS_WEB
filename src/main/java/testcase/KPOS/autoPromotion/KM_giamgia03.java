package testcase.KPOS.autoPromotion;

import appLocator.LoginScreenLocatorKPOS;
import commons.AbstractPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.DashboardPageObject;
import pageObject.KposPageObject;
import pageObject.WebPageObject;
import pageObject.VerifyItem;

public class KM_giamgia03 extends AbstractPage {
    private WebDriver webDriver;
    private AppiumDriver mobileDriver;
    private WebPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private VerifyItem verifyItem;
    private KposPageObject kposPageObject;

    private String Barcode1 = "8934588012112";
    private String Barcode2 = "ev7ly0w1igyyc";
    private String Customer = "0938612787";
    private String CustomerOL = "210817903459583221";
    private String promotionText = "KM giam gia 10% pepsi";
    private String Khachcantra = "13.959";
    private String priceExpected = "5.049";
    private String priceExpectedKDB = "86,190";


    @BeforeClass
    public void beforeClass() {
        mobileDriver = config.DriverFactory.getMobileDriver();
        verifyItem = new VerifyItem(mobileDriver);
        kposPageObject = new KposPageObject(mobileDriver);
//
    }

    @Test
    public void TC01_KM_giamgiapercent_temcandate() throws InterruptedException {
        mobileDriver.launchApp();
//  Đăng nhập KPOS:
       kposPageObject.loginToKposApp();

//  Click tạo bill mới:
kposPageObject.clickTaodon();
//  Click search box và thêm sản phẩm Barcode 1:
        kposPageObject.themBarcode(Barcode1);

        kposPageObject.themBarcode(Barcode2);

//        Click chon KH OL
        kposPageObject.processCustomerOL(CustomerOL);

        String textFromKP = getTextFromKP(mobileDriver, LoginScreenLocatorKPOS.billNumber);
        System.out.println("Hóa đơn: " + textFromKP);


//  Kiểm tra đơn giá của Line được KM:
        verifyItem.verifyPriceItem(Barcode1, priceExpected);
        sleepInSeconds(3);

        verifyItem.verifyKhachCanTra(Khachcantra);
        sleepInSeconds(4);

    }


    @AfterClass
    public void afterClass() {
        // Cleanup drivers
        config.DriverFactory.quitMobileDriver();
        config.DriverFactory.quitWebDriver();
    }
}




