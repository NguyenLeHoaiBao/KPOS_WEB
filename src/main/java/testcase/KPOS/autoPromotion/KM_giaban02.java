package testcase.KPOS.autoPromotion;

import appLocator.LoginScreenLocatorKPOS;
import commons.AbstractPage;
import commons.GlobalConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.DashboardPageObject;
import pageObject.LoginPageObject;
import pageObject.PurchaseReturnListPageObject;
import pageObject.ReturnRequestPageObject;

import static commons.PageGeneratorManager.getLoginPage;

public class KM_giaban02 extends AbstractPage {
    WebDriver webDriver;
    AppiumDriver mobileDriver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    ReturnRequestPageObject returnRequestPage;
    PurchaseReturnListPageObject purchaseReturnListPage;

    private final By giftText = MobileBy.xpath("//android.view.View[@content-desc=\"KM gia ban 0d Tom: Giá bán 0 đồng (Tối đa 1 lần 1 khách hàng)\n" + "KM gia ban 0d Tom: Giá bán 0 đồng (Tối đa ...\"]");
    private final By billNumber = MobileBy.xpath("//android.view.View[contains(@content-desc, 'Hoá đơn 1')]");

    public By getPriceItemByBarcode() {
        return MobileBy.xpath("(//android.view.View[contains(@text,'" + Barcode + "')]//android.view.View)[3]");
    }

    String Barcode = "8938502118157";
    String Customer = "0938612787";

    @BeforeClass
    public void beforeClass() {
//      webDriver = config.DriverFactory.getWebDriver();
        mobileDriver = config.DriverFactory.getMobileDriver();
        // Gọi web và chạy login
//        openUrl(webDriver, GlobalConstants.URL);
//        loginPage = getLoginPage(webDriver);
//        dashboardPage = loginPage.loginFlow();
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
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode);
        sleepInSeconds(5);
        new Actions(mobileDriver).sendKeys(Keys.ENTER).perform();

//  Click khách hàng và thêm khách hàng mới
//        sleepInSeconds(2);
//        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_SEARCH);
//        sendkeyEntertoElement(mobileDriver,LoginScreenLocatorKPOS.CUSTOMER_SEARCH,Customer);
//        sleepInSeconds(10);

        String textFromKP = getTextFromKP(mobileDriver, billNumber);
        System.out.println("Text từ 'KP' trở đi: " + textFromKP);



//  Kiểm tra đơn giá của Line được KM:
        verifyPriceitem();
        sleepInSeconds(3);

//  Chon PTTT tien mat:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CASHBUTTON);
        sleepInSeconds(2);

//  Click button thanh toan:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.PAYBUTTON);

//  Kiểm tra elemement con hien thi hay khong:
        verifyPriceitemdisable();

    }

    public String getPriceitem() {
        return mobileDriver.findElement(getPriceItemByBarcode()).getAttribute("content-desc");
    }

    // Hàm kiểm tra xem text có chứa đoạn mong muốn hay không
    public void verifyPriceitem() {
        String actualText = getPriceitem();
        if (actualText.contains("169.000")) {
            System.out.println("Text contains '169.000'. Verification passed.");
        } else {
            System.out.println("Text does not contain '169.000'. Verification failed.");
            throw new AssertionError("Text verification failed: Expected '169.000'dong not found.");
        }
    }

    public void verifyPriceitemdisable() {
        try {
            WebDriverWait wait = new WebDriverWait(mobileDriver, 8);
            boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(getPriceItemByBarcode()));
            if (!isInvisible) {
                throw new AssertionError("Element 'priceItem' vẫn đang hiển thị khi đã mong đợi biến mất.");
            }
        } catch (TimeoutException e) {
            throw new AssertionError("Element 'priceItem' vẫn hiển thị sau thời gian chờ 3 giây.");
        } catch (Exception e) {
            throw new AssertionError("Đã xảy ra lỗi khi kiểm tra element: " + e.getMessage());
        }
    }

    @Test
    public void TC05_Kiem_tra_ket_qua() {
        webDriver = config.DriverFactory.getWebDriver();
        openUrl(webDriver, "https://kdb-staging.kingfoodmart.net/dashboard");
        loginPage = getLoginPage(webDriver);
        dashboardPage = loginPage.loginFlow();

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




