package testcase.DoiTraHangNCC;

import appLocator.LoginScreenLocatorKPOS;
import appLocator.NavigationMenuLocator;
import appLocator.ReturnVendorLocator;
import appLocator.TransferScreenLocator;
import commons.AbstractPage;
import commons.GlobalConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.DashboardPageObject;
import pageObject.LoginPageObject;
import pageObject.PurchaseReturnListPageObject;
import pageObject.ReturnRequestPageObject;

import java.net.MalformedURLException;

import static commons.PageGeneratorManager.getLoginPage;

public class DoiTraHangNCC extends AbstractPage {
    WebDriver webDriver;
    AppiumDriver mobileDriver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    ReturnRequestPageObject returnRequestPage;
    PurchaseReturnListPageObject purchaseReturnListPage;

    String fromBranchName = "Q07 - 436 Nguyễn Thị Thập";
    String toBranchName = "Trung Tâm";

    String loai = "Đổi";
    String barcode = "QAZ103";
    String soLuong = "3";
    String hsd = "02/12/2024";
    String liDoDoiTra = "NCC thu hồi do ngưng kinh doanh/hợp tác HSD";
    String moTa = "Ghi mo ta";
    String chiNhanhTaoYCDT = "NTT - KFM_HCM_Q07 - 436 Nguyễn Thị Thập - MART";
    String noiDoiTra = "KTT - KHO TRUNG TÂM";
    String transferCode;
    @BeforeClass
     public void beforeClass() {
        webDriver =  config.DriverFactory.getWebDriver();
        mobileDriver = config.DriverFactory.getMobileDriver();
        openUrl(webDriver, GlobalConstants.URL);
        loginPage = getLoginPage(webDriver);
        dashboardPage = loginPage.loginFlow();
        //Mở menu yeu cau doi tra
        returnRequestPage = dashboardPage.clickToReturnRequestPage(webDriver);
    }

//    @Test
    public void TC01_flow_doi_hang_ncc() throws MalformedURLException {
//         Tao moi yeu cau đoi hang ncc

        returnRequestPage.clickToAddNewBtn();
        returnRequestPage.clickToElement(webDriver, "//span[text()='Thêm mới']");
        switchTab(webDriver);
        sleepInSeconds(2);
//        log.info("Dien thong tin");
        returnRequestPage.inputTextToTable("Loại", 1, loai);
        returnRequestPage.inputTextToTable("Barcode", 1, barcode);
        returnRequestPage.inputTextToTable("Số lượng", 1, soLuong);
        returnRequestPage.inputHSD("HSD", 1, hsd);
        returnRequestPage.inputTextToTable("Lí do đổi trả", 1, liDoDoiTra);
        returnRequestPage.inputTextToTable("Mô tả", 1, moTa);
        returnRequestPage.inputTextToTable("Chi nhánh tạo yêu cầu đổi/trả", 1, chiNhanhTaoYCDT);
        returnRequestPage.inputTextToTable("Nơi đổi/trả", 1, noiDoiTra);
        sleepInSeconds(1);
        returnRequestPage.clickToCreateRequest();
        sleepInSeconds(1);
        returnRequestPage.clickToFistItemCheckbox();
        returnRequestPage.clickToThaoTacBtn();
        returnRequestPage.clickToTaoPhieuChuyenBtn();
        sleepInSeconds(3);
    }

//    @Test
    public void TC02_Hoan_tat_chuyen_hang(){
        mobileDriver.launchApp();
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.USERNAME);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.USERNAME, GlobalConstants.USERNAME);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.PASSWORD);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.PASSWORD, GlobalConstants.PASSWORD);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.LOGIN_BUTTON);

        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, fromBranchName);
//       clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.RESULT_SEARCH_BRANCH);

        // Vào menu chuyển hàng xác nhận chuyển hàng bên chuyển
//        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.NAVIGATION_MENU);
        clickToMobileElement(mobileDriver, NavigationMenuLocator.TRANSFER_MENU);
//        clickToMobileElem(mobileDriver, TransferScreenLocator.SEARCH_BOX);
//        sendKeyToMobileTextBox(mobileDriver, TransferScreenLocator.SEARCH_BOX, transferCode);
        clickToMobileElement(mobileDriver, By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]"));
        clickToMobileElement(mobileDriver, TransferScreenLocator.BUTTON_HOAN_THANH);
        clickToMobileElement(mobileDriver, TransferScreenLocator.BUTTON_DONG_Y);
        clickToMobileElement(mobileDriver, TransferScreenLocator.HOAN_TAT_CHUYEN_BACK_BUTTON);

        // Vào nơi nhận
//        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.NAVIGATION_MENU);
        clickToMobileElement(mobileDriver, NavigationMenuLocator.SELECT_BRANCH_BUTTON);
        clickToMobileElement(mobileDriver, NavigationMenuLocator.CURRENT_BRANCH);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, toBranchName);
        clickToMobileElement(mobileDriver, By.xpath("(//android.view.View[@content-desc='Chi nhánh']/following-sibling::android.view.View//android.view.View)[2]"));
        clickToMobileElement(mobileDriver, NavigationMenuLocator.BUTTON_LUU_LAI);
        //Vào phiếu chuyển nhận hàng
//        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.NAVIGATION_MENU);
        clickToMobileElement(mobileDriver, NavigationMenuLocator.TRANSFER_MENU);
        clickToMobileElement(mobileDriver, By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]"));
        clickToMobileElement(mobileDriver, TransferScreenLocator.BUTTON_NHAN_NGUYEN_THÙNG);
        clickToMobileElement(mobileDriver, TransferScreenLocator.NHAN_NGUYEN_THUNG_CHỌN_TAT_CA);
        clickToMobileElement(mobileDriver, TransferScreenLocator.BUTTON_NHAN_THUNG);
        clickToMobileElement(mobileDriver, TransferScreenLocator.DONG_Y_NHAN_NGUYEN_THUNG);
        sleepInSeconds(10);
        mobileDriver.closeApp();
    }

//    @Test
    public void TC03_Tao_phieu_doi_tra(){
//        webDriver =  config.DriverFactory.getWebDriver();
        openUrl(webDriver, GlobalConstants.URL);
        //Mở menu yeu cau doi tra
        returnRequestPage = clickToReturnRequestPage(webDriver);
        sleepInSeconds(60);
        returnRequestPage.refresh(webDriver);
        returnRequestPage.clickToFistItemCheckbox();
        returnRequestPage.clickToTaoPhieuVaGuiMail();
        sleepInSeconds(5);
    }

    @Test
    public void TC04_Hoan_thanh_doi_tra(){
//        mobileDriver = config.DriverFactory.getMobileDriver();
        mobileDriver.launchApp();
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.USERNAME);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.USERNAME, GlobalConstants.USERNAME);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.PASSWORD);
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.PASSWORD, GlobalConstants.PASSWORD);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.LOGIN_BUTTON);

        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);
        //chọn nơi đổi tra
        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, toBranchName);
        clickToMobileElement(mobileDriver, MobileBy.xpath("//android.view.View[contains(@content-desc, 'KHO TRUNG TÂM')]"));
//        sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, fromBranchName);
//        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.RESULT_SEARCH_BRANCH);

//        clickToMobileElem(mobileDriver, LoginScreenLocatorKPOS.NAVIGATION_MENU);
        clickToMobileElement(mobileDriver, NavigationMenuLocator.DOI_HANG_NCC_MENU);
        //chọn THN đầu tiên
        clickToMobileElement(mobileDriver, MobileBy.xpath("//android.view.View[contains(@content-desc,'DHN')][1]"));
        // nhập sl
        clickToMobileElement(mobileDriver, ReturnVendorLocator.SO_LUONG_TRA_TEXTBOX);
        sendKeyToMobileTextBox(mobileDriver, ReturnVendorLocator.SO_LUONG_TRA_TEXTBOX, soLuong);
        clickToMobileElement(mobileDriver, ReturnVendorLocator.BUTTON_HOAN_THANH);
        clickToMobileElement(mobileDriver, ReturnVendorLocator.BUTTON_DONG_Y);
    }


    @Test
    public void TC05_Kiem_tra_ket_qua(){
        webDriver =  config.DriverFactory.getWebDriver();
        openUrl(webDriver, "https://hada-dev.kingfoodmart.net/operation/vendor-purchase-return/list-purchase-return");
//        //Mở menu yeu cau doi tra
//        purchaseReturnListPage = clickToPurchaseReturnListPage(webDriver);
//        clickToPurchaseReturnListPage(webDriver);
//        Assert.assertEquals(getElementText(webDriver, "//div[@class='ag-center-cols-container']/div[@row-index='0']/div[@col-id='status_name']//span[@class='kf-badge-text']"), "Hoàn tất");

    }

//    @AfterMethod
//    public void afterMethod(){
//        if (webDriver != null) {
//            webDriver.close();
//        }
//
//    }
    @AfterClass
    public void afterClass(){
        if (webDriver != null) {
            webDriver.close();
        }
        if (mobileDriver != null) {
            mobileDriver.closeApp();
        }
    }
    }




