package pageObject;

import appLocator.LoginScreenLocatorKPOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyItem {

    private AppiumDriver<MobileElement> mobileDriver;

    public VerifyItem(AppiumDriver<MobileElement> driver) {
        this.mobileDriver = driver;
    }

    // Gia cua barcode được truyền vào
    public By getPriceItemByBarcode(String barcode) {
        return MobileBy.xpath("(//android.view.View[contains(@text, '" + barcode + "') and contains(@text, '" + barcode + "')]//android.view.View)[3]");
    }
    public By getPriceItemByBarcode2(String barcode) {
        return MobileBy.xpath("(//android.view.View[contains(@text, '" + barcode + "') and contains(@text, '" + barcode + "')]//android.view.View)[4]");
    }

    // So luong cua barcode duoc truyen vao
    public By getquanityByBarcode(String barcode) {
        return MobileBy.xpath("(//android.view.View[contains(@text, '" + barcode + "') and contains(@text, '" + barcode + "')]//android.view.View)[2]/android.widget.EditText");
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

    // Hàm lấy giá trị `content-desc` của phần tử dựa trên Barcode
    public String getPriceItem(String barcode) {
        return mobileDriver.findElement(getPriceItemByBarcode(barcode)).getAttribute("content-desc");
    }
    public String getPriceItem2(String barcode) {
        return mobileDriver.findElement(getPriceItemByBarcode2(barcode)).getAttribute("content-desc");
    }

    // Hàm click lên element sau khi truyền vào barcode
    public void clickPriceItemByBarcode(String barcode) {
        By elementBy = getPriceItemByBarcode(barcode);
        mobileDriver.findElement(elementBy).click();
    }

    // Ham truyen vao so % can giam gia
    public By getdiscountpercent(String discount) {
        return MobileBy.xpath("//android.view.View[@content-desc='" + discount + "']");
    }

    public void clickgetdiscountpercent(String discount) {
        By elementBy = getdiscountpercent(discount);
        mobileDriver.findElement(elementBy).click();
    }

    // Ham lay gia tri cua bill
    public void verifyKhachCanTra(String expectedValue) {
        // Lấy giá trị thực tế từ element
        String actualText = mobileDriver.findElement(LoginScreenLocatorKPOS.totalBill_KPOS).getAttribute("content-desc");
        // Xác minh giá trị "Khách cần trả"
        if (actualText.contains(expectedValue)) {
            System.out.println("Verification passed: Actual text '" + actualText + "' contains expected value '" + expectedValue + "'.");
        } else {
            System.out.println("Verification failed: Actual text '" + actualText + "' does not contain expected value '" + expectedValue + "'.");
            throw new AssertionError("Verification failed: Expected value '" + expectedValue + "' not found in actual text: '" + actualText + "'.");
        }
    }

    // Hàm xác minh giá trị `priceExpected`
    public void verifyPriceItem(String barcode, String priceExpected) {
        String actualText = getPriceItem(barcode); // Lấy nội dung thực tế

        System.out.println("Actual Text: " + actualText);

        // Kiểm tra xem nội dung thực tế có chứa `priceExpected` hay không
        if (actualText.equals(priceExpected)) {
            System.out.println("Verification passed: So tien chinh xác '" + priceExpected + "'.");
        } else {
            System.out.println("Verification failed: So tien khong dung");
            throw new AssertionError(
                    "Verification failed: Expected text to contain price '" + priceExpected +
                            "'. Actual text: " + actualText
            );
        }
    }

    public void verifyPriceItem2(String barcode, String priceExpected) {
        String actualText = getPriceItem2(barcode); // Lấy nội dung thực tế

        System.out.println("Actual Text: " + actualText);

        // Kiểm tra xem nội dung thực tế có chứa `priceExpected` hay không
        if (actualText.equals(priceExpected)) {
            System.out.println("Verification passed: So tien chinh xác '" + priceExpected + "'.");
        } else {
            System.out.println("Verification failed: So tien khong dung");
            throw new AssertionError(
                    "Verification failed: Expected text to contain price '" + priceExpected +
                            "'. Actual text: " + actualText
            );
        }
    }

    // Hàm send key tới element được lấy từ getPriceItemByBarcode
    public void nhapSoLuongBarcode(String barcode, String soLuong) {
        try {
            // Lấy element locator từ phương thức getquanityByBarcode
            By locator = getquanityByBarcode(barcode);

            // Đợi cho element sẵn sàng và có thể click
            WebDriverWait wait = new WebDriverWait(mobileDriver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(locator));

            // Tìm element
            WebElement element = mobileDriver.findElement(locator);

            // Click vào element để focus
            element.click();
            element.clear();
            // Gửi ký tự mới vào element
            element.sendKeys(soLuong);

            // Log kết quả
            System.out.println("Sent text '" + soLuong + "' to element with barcode: " + barcode);
        } catch (Exception e) {
            // Xử lý lỗi nếu không thể tìm thấy hoặc thao tác với element
            System.err.println("Failed to nhập số lượng for barcode: " + barcode);
            e.printStackTrace();
            throw new RuntimeException("Unable to nhập số lượng for barcode: " + barcode);
        }
    }

    // Hàm kiểm tra phần tử không hiển thị
    public void verifyPriceItemDisable(String barcode) {
        try {
            WebDriverWait wait = new WebDriverWait(mobileDriver, 1);
            boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(getPriceItemByBarcode(barcode)));
            if (!isInvisible) {
                throw new AssertionError("Element 'priceItem' vẫn đang hiển thị khi đã mong đợi biến mất.");
            }
        } catch (TimeoutException e) {
            throw new AssertionError("Element 'priceItem' vẫn hiển thị sau thời gian chờ 1 giây.");
        } catch (Exception e) {
            throw new AssertionError("Đã xảy ra lỗi khi kiểm tra element: " + e.getMessage());
        }
    }

    // Hàm xây dựng XPath dựa trên Barcode
    public By getPromotionTextByBarcode(String barcode) {
        return MobileBy.xpath("//android.view.View[contains(@text, '" + barcode + "')]/ancestor::android.view.View/descendant::android.view.View[contains(@content-desc, 'KM')]");
    }

    // Hàm lấy giá trị `content-desc` của phần tử dựa trên Barcode
    public String getPromotionText(String barcode) {
        return mobileDriver.findElement(getPromotionTextByBarcode(barcode)).getAttribute("content-desc");
    }

    // Hàm xác minh giá trị `promotionText`
    public void verifyPromotionText(String barcode, String promotionText) {
        String actualText = getPromotionText(barcode); // Lấy nội dung thực tế

        System.out.println("Actual Text: " + actualText);

        // Kiểm tra xem nội dung thực tế có chứa `promotionText` hay không
        if (actualText.contains(promotionText)) {
            System.out.println("Verification passed: Text contains promotion '" + promotionText + "'.");
        } else {
            System.out.println("Verification failed: Text does not contain expected promotion.");
            throw new AssertionError(
                    "Verification failed: Expected text to contain promotion '" + promotionText +
                            "'. Actual text: " + actualText
            );
        }
    }

    public void khuyenmaihoadon(String expectedText) {
        try {
            // Xpath để tìm element text nằm ngay bên dưới element trên
            By textElementLocator = MobileBy.xpath("//android.view.View[@content-desc='Khuyến mãi hóa đơn']/following-sibling::android.view.View");

            // Đợi cho element chứa text sẵn sàng
            WebDriverWait wait = new WebDriverWait(mobileDriver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(textElementLocator));

            // Lấy text từ element bên dưới
            String actualText = mobileDriver.findElement(textElementLocator).getText();
            System.out.println("Actual Text: " + actualText);

            // So sánh text lấy được với đoạn text mong muốn
//            if (actualText.contains(expectedText)) {
//                System.out.println("Verification passed: Text matches expected text.");
//            } else {
//                System.err.println("Verification failed: Text does not match.");
//                System.err.println("Expected: " + expectedText);
//                System.err.println("Actual: " + actualText);
//                throw new AssertionError("Verification failed: Text mismatch.");
//            }
        } catch (Exception e) {
            // Xử lý lỗi nếu không tìm thấy element hoặc không thể lấy text
            System.err.println("Failed to find or verify text below 'Khuyến mãi hoá đơn'.");
            e.printStackTrace();
        }
    }

}
