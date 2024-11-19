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
import pageUI.LoginPageUI;

public class KM_giaban01 extends AbstractPage {
    WebDriver webDriver;
    AppiumDriver mobileDriver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;


    String Barcode = "8938502118157";
    String Customer = "0938612787";
    String CustomerOL = "210817903459583221";
    String promotionText = "KM gia ban 0d Tom";
    String priceExpected = "0";
    String priceExpectedKDB = "0";

    @BeforeClass
    public void beforeClass() {
//      webDriver = config.DriverFactory.getWebDriver();
        mobileDriver = config.DriverFactory.getMobileDriver();

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
//        sleepInSeconds(2);
//        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_SEARCH);
//        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_SEARCH, Customer);
//        sleepInSeconds(2);
//        new Actions(mobileDriver).sendKeys(Keys.ENTER).perform();

//        Click chon KH OL
        clickToMobileElem(mobileDriver,LoginScreenLocatorKPOS.CUSTOMER_OL);
        clickToMobileElem(mobileDriver,LoginScreenLocatorKPOS.CUSTOMER_ID);
        sendkeyEntertoElement(mobileDriver,LoginScreenLocatorKPOS.CUSTOMER_ID,CustomerOL);
        clickToMobileElem(mobileDriver,LoginScreenLocatorKPOS.CUSTOMER_ACEPTED);



        sleepInSeconds(4);

//  Kiểm tra text KM :
        verifyTextKm();


//  Kiểm tra đơn giá của Line được KM:
        verifyPriceItem();

    }

    public By getPriceItemByBarcode() {
        return MobileBy.xpath("(//android.view.View[contains(@text,'" + Barcode + "')]//android.view.View)[3]");
    }

    public String getPriceitem() {
        return mobileDriver.findElement(getPriceItemByBarcode()).getAttribute("content-desc");
    }

    // Hàm kiểm tra xem text có chứa đoạn mong muốn hay không
    public void verifyPriceItem() {
        String actualText = getPriceitem();
        if (actualText.contains(priceExpected)) {
            System.out.println("Text contains '" + priceExpected + "'. Verification passed.");
        } else {
            System.out.println("Text does not contain '" + priceExpected + "'. Verification failed.");
            throw new AssertionError("Text verification failed: Expected '" + priceExpected + "' not found.");
        }
    }

    public String getTotalPriceInvoice() {
        return webDriver.findElement(By.xpath(LoginPageUI.totalPriceCell)).getText();
    }

    // Hàm kiểm tra so sanh gia o chi tiet hoa don
    public void verifytotalPriceitem() {
        String actualText = getTotalPriceInvoice();
        if (actualText.contains(priceExpectedKDB)) {
            System.out.println("Tong gia tri don '" + priceExpectedKDB + "'. Verification passed.");
        } else {
            System.out.println("Khong dung so tien '169,000'. Verification failed.");
            throw new AssertionError("Text verification failed: Expected '\"+priceExpectedKDB+\"'dong not found.");
        }
    }

    public String getTextKm() {
        return mobileDriver.findElement(LoginScreenLocatorKPOS.giftTextpromotion).getAttribute("content-desc");
    }

    // Hàm kiểm tra xem text có chứa đoạn mong muốn hay không
    public void verifyTextKm() {
        String actualText = getTextKm();
        if (actualText.contains(promotionText)) {
            System.out.println("Text contains '" + promotionText + "'. Verification passed.");
        } else {
            System.out.println("Text does not contain '\"+promotionText+\"'. Verification failed.");
            throw new AssertionError("Text verification failed: Expected '\"+promotionText+\"' not found.");
        }

    }

    //    Ham kiem tra hoa don co thanh toan thanh cong hay chua
    public void verifyPriceitemdisable() {
        try {
            WebDriverWait wait = new WebDriverWait(mobileDriver, 3);
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




