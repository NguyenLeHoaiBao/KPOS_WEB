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
import pageObject.LoginPageObject;
import pageObject.VerifyItem;

import java.io.IOException;

import static commons.PageGeneratorManager.getLoginPage;

public class KM_hanghoa_Giabantheocombo02 extends AbstractPage {
    private WebDriver webDriver;
    private AppiumDriver mobileDriver;
    private LoginPageObject loginPage;
    private KposPageObject kposPageObject;
    private DashboardPageObject dashboardPage;
    private VerifyItem verifyItem;

    private String Barcode1 = "8936135440058";
    private String priceBarcode1 = "10.900";

    private String Barcode2 = "8936135440027";
    private String priceBarcode2 = "10.900";

    private String Barcode3 = "8936135445008";
    private String priceBarcode3 = "5.300";

    private String Barcode4 = "8936135445015";
    private String priceBarcode4 = "9.500";

    private String Barcode5 = "8936135441024";
    private String priceBarcode5 = "11.000";

    private String Barcode6 = "8936135440249";
    private String priceBarcode6 = "28.000";

    private String Barcode7 = "8934588013133";
    private String priceBarcode7 = "7.500";

    private String Barcode8 = "8934588673733";
    private String priceBarcode8 = "230.000";

    private String soluongBarcode1 = "7";
    private String soluongBarcode2 = "30";
    private String Customer = "0938612787";
    private String CustomerOL = "210817903459583221";
    private String promotionText = "KM gia ban 0d Tom";

    // Gia tri khai bao cho web
    private String KhachcantraKP = "24.000";
    private String Tongsoluongsp = "4";
    private String Khachcantra = "24.000";
    private String Tienkhachdua = "24.000";
    private String giamtienle = "800";


    @BeforeClass
    public void beforeClass() {
        webDriver = config.DriverFactory.getWebDriver();
        mobileDriver = config.DriverFactory.getMobileDriver();
        verifyItem = new VerifyItem(mobileDriver);
        kposPageObject = new KposPageObject(mobileDriver);
    }

    @Test
    public void TC01_KM_hanghoa_giabantheoslmua1sp() throws IOException {
        mobileDriver.launchApp();
//  Đăng nhập KPOS:
        kposPageObject.loginToKposApp();

//  Click tạo bill mới:
        kposPageObject.clickTaodon();

//  Click search box và thêm sản phẩm:
        kposPageObject.themBarcode(Barcode1);
        sleepInSeconds(5);
        verifyItem.verifyPriceItem(Barcode1,priceBarcode1);

        kposPageObject.themBarcode(Barcode2);
        sleepInSeconds(5);
        verifyItem.verifyPriceItem(Barcode2,priceBarcode2);

        kposPageObject.themBarcode(Barcode8);
        sleepInSeconds(5);
        verifyItem.verifyPriceItem(Barcode8,priceBarcode8);

        kposPageObject.themBarcode(Barcode7);
        sleepInSeconds(5);
        verifyItem.verifyPriceItem(Barcode7,priceBarcode7);


        //Lay ma hoa don KPOS
        String InvoiceCode = kposPageObject.getInvoicecode();

//  Nhap ID dang nhap OL
//        kposPageObject.processCustomerOL(CustomerOL);

        sleepInSeconds(6);
//  Click chon hop qua KM theo hoa don
        kposPageObject.clickLinePromo(Barcode8);

//  Click chon KM hoadon_giamgiahang_tặng sản phẩm Barcode2
        kposPageObject.clickcheckBoxpromotion("KM Hàng Hóa - Giá bán theo combo AUTOTEST combo multi 2/2 (Giá bán)");

        sleepInSeconds(10);

        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.apdungButton);


        verifyItem.verifyKhachCanTra(KhachcantraKP);
        verifyItem.verifyPromotionText(Barcode1,"KM Hàng Hóa - Giá bán theo combo AUTOTEST combo multi 2/2 (Giá bán)");
        verifyItem.verifyPromotionText(Barcode2,"KM Hàng Hóa - Giá bán theo combo AUTOTEST combo multi 2/2 (Giá bán)");

        verifyItem.verifyPromotionText(Barcode7,"KM Hàng Hóa - Giá bán theo combo AUTOTEST combo multi 2/2 (Giá bán)");
        verifyItem.verifyPromotionText(Barcode8,"KM Hàng Hóa - Giá bán theo combo AUTOTEST combo multi 2/2 (Giá bán)");



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
        loginPage.verifyPriceInvoiceline(Barcode1,priceBarcode1);
        loginPage.verifyPriceInvoiceline(Barcode2,priceBarcode2);
        loginPage.verifyPriceInvoiceline(Barcode7,priceBarcode7);
        loginPage.verifyPriceInvoiceline(Barcode8,priceBarcode8);

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




