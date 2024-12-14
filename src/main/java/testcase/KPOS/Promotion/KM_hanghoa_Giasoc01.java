package testcase.KPOS.Promotion;

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

import java.io.IOException;

import static commons.PageGeneratorManager.getLoginPage;

public class KM_hanghoa_Giasoc01 extends AbstractPage {
    private WebDriver webDriver;
    private AppiumDriver mobileDriver;
    private WebPageObject loginPage;
    private KposPageObject kposPageObject;
    private DashboardPageObject dashboardPage;
    private VerifyItem verifyItem;

    private String Barcode1 = "27SPAUTOTEST";
    private String Barcode2 = "22SPAUTO";
    private String soluongBarcode1 = "2";
    private String soluongBarcode2 = "30";
    private String Customer = "0938612787";
    private String CustomerOL = "210817903459583221";
    private String promotionText = "KM gia ban 0d Tom";
    private String priceBarcode1 = "43.500";
    private String priceBarcode2 = "33.500";
    private String priceitemline1= "43,500";
    private String priceitemline2= "33,500";
    private String priceExpectedKDB = "111";
    private String KhachcantraKP = "80.000";
    private String Tongsoluongsp = "2";
    private String Khachcantra = "80,000";
    private String Tienkhachdua = "80,000";
//    private String giamtienle = "500";


    @BeforeClass
    public void beforeClass() {
        webDriver = config.DriverFactory.getWebDriver();
        mobileDriver = config.DriverFactory.getMobileDriver();
        verifyItem = new VerifyItem(mobileDriver);
        kposPageObject = new KposPageObject(mobileDriver);
    }

    @Test
    public void TC01_KM_hanghoa_giasocmua1sp() throws IOException {
        mobileDriver.launchApp();
//  Đăng nhập KPOS:
        kposPageObject.loginToKposApp();

//  Click tạo bill mới:
        kposPageObject.clickTaodon();

//  Click search box và thêm sản phẩm:
        kposPageObject.themBarcode(Barcode1);
        sleepInSeconds(5);
        verifyItem.verifyPriceItem(Barcode1,priceBarcode1);
        kposPageObject.nhapSoLuongBarcode(Barcode1, soluongBarcode1);

        //Lay ma hoa don KPOS
        String InvoiceCode = kposPageObject.getInvoicecode();

//  Nhap ID dang nhap OL
        kposPageObject.processCustomerOL(CustomerOL);

        sleepInSeconds(6);
//  Click chon hop qua KM theo hoa don
        kposPageObject.clickLinePromo(Barcode1);

//  Click chon KM hoadon_giamgiahang_tặng sản phẩm Barcode2
        kposPageObject.clickcheckBoxpromotion("KM Hàng hóa - Giá sốc unlimit 27SPAUTOTEST");

        sleepInSeconds(10);

        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.apdungButton);

        verifyItem.verifyPromotionText(Barcode1,"KM Hàng hóa - Giá sốc unlimit 27SPAUTOTEST");

        verifyItem.verifyKhachCanTra(KhachcantraKP);


//  click chon tien mat va thanh toan
        kposPageObject.cashCharge();

        openUrl(webDriver, GlobalConstants.URL);
        loginPage = getLoginPage(webDriver);
        dashboardPage = loginPage.loginFlow();
        sleepInSeconds(5);

        loginPage.gotoInvoicelist();
        sleepInSeconds(10);
        loginPage.detailInvoice(InvoiceCode);
        sleepInSeconds(3);
        loginPage.verifyPriceInvoiceline(Barcode1,priceitemline1);

        loginPage.verifyTotalPriceItem(Tongsoluongsp, "Tổng số lượng");
        loginPage.verifyTotalPriceItem(Khachcantra, "Khách cần trả");
//        loginPage.verifyTotalPriceItem(giamtienle,"Giảm tiền lẻ");
        loginPage.verifyTotalPriceItem(Tienkhachdua,"Tiền khách đưa");
        sleepInSeconds(2);
    }

    @AfterClass
    public void afterClass() {
        // Cleanup drivers
        config.DriverFactory.quitMobileDriver();
        config.DriverFactory.quitWebDriver();
    }
}




