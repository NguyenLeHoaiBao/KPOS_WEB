package testcase.KPOS.Promotion;

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

public class KM_hanghoa_muacombotanghang01 extends AbstractPage {
    private WebDriver webDriver;
    private AppiumDriver mobileDriver;
    private WebPageObject webPageObject;
    private KposPageObject kposPageObject;
    private DashboardPageObject dashboardPage;
    private VerifyItem verifyItem;

    private String Barcode1 = "8936135440034";
    private String priceBarcode1 = "20.300";
    private String Barcode2 = "8936135445015";
    private String priceBarcode2 = "9.500";
    private String Barcode3 = "8934588013133";
    private String priceBarcode3 = "7.500";
    private String Barcode4 = "8934717275098";
    private String priceBarcode4 = "75.500";
    private String Barcode5 = "27SPAUTOTEST";
    private String priceBarcode5 = "43.500";
    private String CustomerOL = "210817903459583221";
    private String promotionText = "Hàng hoá - mua combo tặng hàng theo giá trị AUTOTEST";

    // Gia tri khai bao cho web
    private String Tongsoluongsp = "11";
    private String Khachcantra = "268.200";
    private String Tienkhachdua = "268.000";
    private String giamtienle = "200";


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
        verifyItem.verifyPriceItem(Barcode1, priceBarcode1);
        kposPageObject.nhapSoLuongBarcode(Barcode1,"4");

        kposPageObject.themBarcode(Barcode2);
        sleepInSeconds(5);
        verifyItem.verifyPriceItem(Barcode2, priceBarcode2);
        kposPageObject.nhapSoLuongBarcode(Barcode2,"3");

        kposPageObject.themBarcode(Barcode3);
        sleepInSeconds(5);
        verifyItem.verifyPriceItem(Barcode3, priceBarcode3);

        kposPageObject.themBarcode(Barcode4);
        sleepInSeconds(5);
        verifyItem.verifyPriceItem(Barcode4, priceBarcode4);
        kposPageObject.nhapSoLuongBarcode(Barcode4,"2");


        //Lay ma hoa don KPOS
        String InvoiceCode = kposPageObject.getInvoicecode();

//  Nhap ID dang nhap OL
//        kposPageObject.processCustomerOL(CustomerOL);

        sleepInSeconds(6);
//  Click chon hop qua KM theo hoa don
        kposPageObject.clickLinePromo(Barcode4);

//  Click chon Hàng hoá - mua combo tặng hàng theo giá trị AUTOTEST
        kposPageObject.clickcheckGiftpromotion(promotionText,Barcode5);
        sleepInSeconds(2);

//  Kiểm tra sản phẩm KM đã hiển thị hay chưa và đơn giá sản phẩm tặng có đúng hay không?
        verifyItem.verifyPromotionItem(Barcode5,priceBarcode5);
        verifyItem.verifyKhachCanTra(Khachcantra);

        verifyItem.verifyPromotionText(Barcode4, promotionText);


//  click chon tien mat va thanh toan
        kposPageObject.cashCharge();

        openUrl(webDriver, GlobalConstants.URL);
        webPageObject = getLoginPage(webDriver);
        dashboardPage = webPageObject.loginFlow();
        sleepInSeconds(5);

        webPageObject.gotoInvoicelist();
        sleepInSeconds(10);
        webPageObject.detailInvoice(InvoiceCode);
        sleepInSeconds(3);
        webPageObject.verifyPriceInvoiceline(Barcode1, priceBarcode1);
        webPageObject.verifyPriceInvoiceline(Barcode2, priceBarcode2);
        webPageObject.verifyPriceInvoiceline(Barcode3, priceBarcode3);
        webPageObject.verifyPriceInvoiceline(Barcode4, priceBarcode4);
        webPageObject.verifyPriceInvoiceline(Barcode5, priceBarcode5);


        webPageObject.verifyTotalPriceItem(Tongsoluongsp, "Tổng số lượng");
        webPageObject.verifyTotalPriceItem(Khachcantra, "Khách cần trả");
        webPageObject.verifyTotalPriceItem(giamtienle,"Giảm tiền lẻ");
        webPageObject.verifyTotalPriceItem(Tienkhachdua, "Tiền khách đưa");
        sleepInSeconds(2);
    }

    @AfterClass
    public void afterClass() {
        // Cleanup drivers
        config.DriverFactory.quitMobileDriver();
        config.DriverFactory.quitWebDriver();
    }
}




