package testcase.KPOS.autoPromotion;

import appLocator.LoginScreenLocatorKPOS;
import commons.AbstractPage;
import commons.GlobalConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.*;
import pageObjectKPOS.LoginKPOSObject;

public class KM_giaban01 extends AbstractPage {
    WebDriver webDriver;
    AppiumDriver mobileDriver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    ReturnRequestPageObject returnRequestPage;
    PurchaseReturnListPageObject purchaseReturnListPage;

    private final By giftText = MobileBy.xpath("//android.view.View[@content-desc=\"KM gia ban 0d Tom: Giá bán 0 đồng (Tối đa 1 lần 1 khách hàng)\n" +
            "KM gia ban 0d Tom: Giá bán 0 đồng (Tối đa ...\"]");

    private final By priceItem = MobileBy.xpath("(//android.view.View[@content-desc=\"0\"])[1]");

    String Barcode = "8938502118157";
    String Customer = "0333888777";

    @BeforeClass
    public void beforeClass() {
//      webDriver = config.DriverFactory.getWebDriver();
        mobileDriver = config.DriverFactory.getMobileDriver();
        // Gọi web và chạy login
//        openUrl(webDriver, GlobalConstants.URL);
//        loginPage = getLoginPage(webDriver);
//        dashboardPage = loginPage.loginFlow();
        //Mở menu yeu cau doi tra
//        returnRequestPage = dashboardPage.clickToReturnRequestPage(webDriver);
    }

    @Test
    public void TC01_KM_giaban() {
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
        sleepInSeconds(3);
        new Actions(mobileDriver).sendKeys(Keys.ENTER).perform();

//  Click khách hàng và thêm khách hàng mới
        sleepInSeconds(2);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_SEARCH);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_SEARCH, Customer);
        sleepInSeconds(2);
        new Actions(mobileDriver).sendKeys(Keys.ENTER).perform();


        sleepInSeconds(4);

//  Kiểm tra text KM :
        verifyTextKm();


//  Kiểm tra đơn giá của Line được KM:
        verifyPriceitem();

    }

    public String getTextKm() {
        return mobileDriver.findElement(giftText).getAttribute("content-desc");
    }

    // Hàm kiểm tra xem text có chứa đoạn mong muốn hay không
    public void verifyTextKm() {
        String actualText = getTextKm();
        if (actualText.contains("KM gia ban 0d")) {
            System.out.println("Text contains 'KM gia ban 0d'. Verification passed.");
        } else {
            System.out.println("Text does not contain 'KM gia ban 0d'. Verification failed.");
            throw new AssertionError("Text verification failed: Expected 'Test KM tang khach' not found.");
        }

    }

    public String getPriceitem() {
        return mobileDriver.findElement(priceItem).getAttribute("content-desc");
    }

    // Hàm kiểm tra xem text có chứa đoạn mong muốn hay không
    public void verifyPriceitem() {
        String actualText = getPriceitem();
        if (actualText.contains("0")) {
            System.out.println("Text contains '0'. Verification passed.");
        } else {
            System.out.println("Text does not contain '0'. Verification failed.");
            throw new AssertionError("Text verification failed: Expected '0'dong not found.");
        }
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




