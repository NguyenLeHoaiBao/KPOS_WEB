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

public class KM_giamgiatien03 extends AbstractPage {
    WebDriver webDriver;
    AppiumDriver mobileDriver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    VerifyItem verifyItem;

    String Barcode1 = "8935302300485";
    String Barcode2 = "7il3mzw07jl2j";
    String Customer = "0938612787";
    String CustomerOL = "210817903459583221";
    String promotionText = "KM ";
    String Khachcantra = "44.875";
    String priceExpected = "21.375";
    String priceExpectedKDB = "86,190";


    @BeforeClass
    public void beforeClass() {
        mobileDriver = config.DriverFactory.getMobileDriver();
        verifyItem = new VerifyItem(mobileDriver);
//
    }

    @Test
    public void TC01_KM_giamgiatien_temcandate() throws InterruptedException {
        mobileDriver.launchApp();
//  Đăng nhập KPOS:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.USERNAME);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.USERNAME, GlobalConstants.USERNAME);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.PASSWORD);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.PASSWORD, GlobalConstants.PASSWORD);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.LOGIN_BUTTON);

//  Click tạo bill mới:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.NEWBILL_BUTTON);

//  Click search box và thêm sản phẩm Barcode 1:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode1);
        sleepInSeconds(2);


        //  Click search box và thêm sản phẩm Barcode 2:
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode2);
        sleepInSeconds(2);

//        Click chon KH OL
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_OL);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ID);
        sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ID, CustomerOL);
        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ACEPTED);

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
        if (webDriver != null) {
            webDriver.close();
        }
        if (mobileDriver != null) {
            mobileDriver.closeApp();
        }
    }
}




