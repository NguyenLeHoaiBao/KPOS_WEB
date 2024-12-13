package pageObject;

import appLocator.LoginScreenLocatorKPOS;
import commons.AbstractPage;
import commons.GlobalConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KposPageObject extends AbstractPage {
    AppiumDriver mobileDriver;

    public KposPageObject(AppiumDriver mobileDriver) {
        this.mobileDriver = mobileDriver;
    }

    public By getPriceItemByBarcode(String barcode) {
        return MobileBy.xpath("(//android.view.View[contains(@text, '" + barcode + "') and contains(@text, '" + barcode + "')]//android.view.View)[3]");
    }

    // So luong cua barcode duoc truyen vao
    public By getquanityByBarcode(String barcode) {
        return MobileBy.xpath("(//android.view.View[contains(@text, '" + barcode + "') and contains(@text, '" + barcode + "')]//android.view.View)[2]/android.widget.EditText");
    }

    public By getquanityByBarcode2(String barcode) {
        return MobileBy.xpath("(//android.view.View[contains(@text, '" + barcode + "') and contains(@text, '" + barcode + "')]//android.view.View)[3]/android.widget.EditText");
    }

    // Hàm checkbox chọn khuyen mai cua KM theo hoa don
    public By promotioncheckBox(String Spduockhuyenmai) {
        return MobileBy.xpath("//android.view.View[contains(@content-desc, '" + Spduockhuyenmai + "')]/android.widget.CheckBox\n");
    }

    //Ha click ln elemetn sau khi truyen vao barcode
    public void clickcheckBoxpromotion(String Spduockhuyenmai) {
        By elementBy = promotioncheckBox(Spduockhuyenmai);
        mobileDriver.findElement(elementBy).click();
    }

    // Ham click vao hop qua o line duoc khuyenmai theo hang hoa
    public By promoBoxinline (String barcode){
        return MobileBy.xpath("//android.view.View[contains(@text, '"+ barcode+"') and contains(@text, '"+barcode+"')]/android.view.View[2]");
    }
    public void clickLinePromo(String barcode) {
        By elementBy = promoBoxinline(barcode);

        try {
            // Chờ tối đa 15 giây cho đến khi element có thể click được
            WebDriverWait wait = new WebDriverWait(mobileDriver, 15);
            wait.until(ExpectedConditions.elementToBeClickable(elementBy));

            // Click vào element
            mobileDriver.findElement(elementBy).click();
            System.out.println("Successfully clicked on promo box for barcode: " + barcode);
        } catch (Exception e) {
            System.err.println("Failed to click on promo box for barcode: " + barcode);
            e.printStackTrace();
            throw e; // Ném ngoại lệ để báo lỗi
        }
    }

    // Ham click tao don moi
    public void clickTaodon (){
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.NEWBILL_BUTTON);

    }

    public void chonKMHoadon(){
        clickToMobileElement(mobileDriver,LoginScreenLocatorKPOS.invoicePromotionBox);
    }

    // Hàm lấy giá trị `content-desc` của phần tử dựa trên Barcode
    public String getPriceItem(String barcode) {
        return mobileDriver.findElement(getPriceItemByBarcode(barcode)).getAttribute("content-desc");
    }

    // Hàm click lên element sau khi truyền vào barcode
    public void clickPriceItemByBarcode(String barcode) {
        By elementBy = getPriceItemByBarcode(barcode);
        mobileDriver.findElement(elementBy).click();
    }

    //Ham lay ma hoa don KPOS
    public String getInvoicecode(){
        String InvoiceCode = getTextFromKP(mobileDriver, LoginScreenLocatorKPOS.billNumber);
        System.out.println("Hóa đơn: " + InvoiceCode);
        return InvoiceCode;
    }

    // Ham chon PTTT tien mat va click button thanh toan
    public void cashCharge(){
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.CASHBUTTON);
        sleepInSeconds(2);
        clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.PAYBUTTON);
        sleepInSeconds(4);
    }

    // Ham truyen vao so % can giam gia
    public By getdiscountpercent(String discount) {
        return MobileBy.xpath("//android.view.View[@content-desc='" + discount + "']");
    }

    public void clickgetdiscountpercent(String discount) {
        By elementBy = getdiscountpercent(discount);
        mobileDriver.findElement(elementBy).click();
    }

    public KposPageObject processCustomerOL(String customerOL) {
        try {
            // Bước 1: Nhấp vào input OL
            System.out.println("Step 1: Clicking input OL field.");
            clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_OL);

            // Bước 2: Nhấp vào trường nhập OL
            System.out.println("Step 2: Clicking Customer ID input field.");
            clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ID);

            // Bước 3: Nhập giá trị Customer OL
            System.out.println("Step 3: Sending Customer OL value: " + customerOL);
            sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ID, customerOL);

            // Bước 4: Nhấp nút OK để xác nhận
            System.out.println("Step 4: Clicking OK button to confirm.");
            clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.CUSTOMER_ACEPTED);

            System.out.println("Customer OL process completed successfully.");
        } catch (Exception e) {
            System.err.println("Error occurred during Customer OL process: " + e.getMessage());
            throw new RuntimeException("Failed to process Customer OL.", e);
        }
        return this;
    }

    public KposPageObject loginToKposApp() {
        try {
            // Bước 1: Nhấp vào ô nhập Username
            System.out.println("Step 1: Clicking on the Username field.");
            clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.USERNAME);

            // Bước 2: Nhập giá trị Username

            sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.USERNAME, GlobalConstants.USERNAME);

            // Bước 3: Nhấp vào ô nhập Password
            System.out.println("Step 3: Clicking on the Password field.");
            clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.PASSWORD);

            // Bước 4: Nhập giá trị Password
            System.out.println("Step 4: Entering Password.");
            sendKeyToMobileTextBox(mobileDriver, LoginScreenLocatorKPOS.PASSWORD, GlobalConstants.PASSWORD);

            // Bước 5: Nhấp vào nút Đăng nhập
            System.out.println("Step 5: Clicking on the Login button.");
            clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.LOGIN_BUTTON);

            System.out.println("Login process completed successfully.");
        } catch (Exception e) {
            System.err.println("Error occurred during login process: " + e.getMessage());
            throw new RuntimeException("Failed to login to the KPOS app.", e);
        }
        return this;
    }

    // Hàm send key tới element được lấy từ getPriceItemByBarcode
    public void nhapSoLuongBarcode(String barcode, String soLuong) {
        try {
            // Thử lấy locator bằng phương thức getquanityByBarcode
            By locator = getquanityByBarcode(barcode);

            // Đợi cho element sẵn sàng và có thể click
            WebDriverWait wait = new WebDriverWait(mobileDriver, 2);
            wait.until(ExpectedConditions.elementToBeClickable(locator));

            // Tìm element
            WebElement element = mobileDriver.findElement(locator);

            // Click vào element để focus
            element.click();
            sleepInSeconds(2);
            element.clear();
            sleepInSeconds(2);

            // Gửi ký tự mới vào element
            element.sendKeys(soLuong);

            // Log kết quả
            System.out.println("Sent text '" + soLuong + "' to element with barcode: " + barcode);
        } catch (Exception e1) {
            // Nếu thất bại, chuyển sang phương thức getquanityByBarcode2
            try {
                System.err.println("Failed to locate with getquanityByBarcode, retrying with getquanityByBarcode2...");

                // Lấy locator từ phương thức getquanityByBarcode2
                By locator = getquanityByBarcode2(barcode);

                // Đợi cho element sẵn sàng và có thể click
                WebDriverWait wait = new WebDriverWait(mobileDriver, 3);
                wait.until(ExpectedConditions.elementToBeClickable(locator));

                // Tìm element
                WebElement element = mobileDriver.findElement(locator);

                // Click vào element để focus
                element.click();
                sleepInSeconds(2);
                element.clear();
                sleepInSeconds(2);

                // Gửi ký tự mới vào element
                element.sendKeys(soLuong);

                // Log kết quả
                System.out.println("Sent text '" + soLuong + "' to element with barcode: " + barcode);
            } catch (Exception e2) {
                // Nếu cả hai cách đều thất bại, ném lỗi
                System.err.println("Failed to nhập số lượng for barcode using both methods: " + barcode);
                e2.printStackTrace();
                throw new RuntimeException("Unable to nhập số lượng for barcode: " + barcode, e2);
            }
        }
    }


    public KposPageObject themBarcode(String Barcode) {
        try {
            // Nhấp vào element
            clickToMobileElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX);

            // Gửi giá trị vào element
            sendkeyEntertoElement(mobileDriver, LoginScreenLocatorKPOS.SEARCH_BRANCH_TEXTBOX, Barcode);
        } catch (Exception e) {
            System.err.println("Error occurred while interacting with element: " + e.getMessage());
            throw new RuntimeException("Failed to interact with element.", e);
        }
        return this;
    }

    public void clickCheckboxByCoordinates(AppiumDriver driver) {
        try {
            // Tính toán tọa độ trung tâm của checkbox
            int startX = 15;
            int startY = 102;
            int endX = 1009;
            int endY = 153;

            int centerX = (startX + endX) / 2;
            int centerY = (startY + endY) / 2;

            // Thực hiện click vào tọa độ trung tâm
            TouchAction touchAction = new TouchAction(driver);
            touchAction.tap(TapOptions.tapOptions().withPosition(PointOption.point(centerX, centerY))).perform();

            System.out.println("Đã click vào checkbox thành công.");
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra khi click vào checkbox: " + e.getMessage());
        }
    }





}
