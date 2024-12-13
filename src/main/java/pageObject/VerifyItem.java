package pageObject;

import appLocator.LoginScreenLocatorKPOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        String actualText = getPriceItem(barcode); // Thử lấy giá trị bằng phương pháp đầu tiên

        if (actualText == null) {
            System.out.println("getPriceItem trả về null, chuyển sang dùng getPriceItem2...");
            actualText = getPriceItem2(barcode); // Chuyển sang phương pháp dự phòng
        }
        System.out.println("Giá trị thực tế: của Barcode '" + barcode + "'là " + actualText);

        // Thực hiện kiểm tra
        if (actualText != null && actualText.equals(priceExpected)) {
            System.out.println("Xác minh thành công: Số tiền chính xác của Barcode '" + barcode + "' là '" + priceExpected + "'.");
        } else {
            System.out.println("Xác minh thất bại: Số tiền không đúng");
            throw new AssertionError(
                    "Xác minh thất bại: Giá trị mong đợi của '" + barcode + "' là '" + priceExpected +
                            "'. Giá trị thực tế: " + (actualText == null ? "null" : actualText)
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
    public By getPromotionTextByBarcode(String barcode,String promoText) {
        return MobileBy.xpath("//android.view.View[contains(@text, '" + barcode + "')]/ancestor::android.view.View/descendant::android.view.View[contains(@content-desc, '" + promoText + "')]");
    }

    // Hàm lấy giá trị `content-desc` của phần tử dựa trên Barcode
    public String getPromotionText(String barcode,String promoText) {
        return mobileDriver.findElement(getPromotionTextByBarcode(barcode,promoText)).getAttribute("content-desc");
    }

    // Hàm xác minh giá trị `promotionText`
    public void verifyPromotionText(String barcode, String promotionText) {
        String actualText = getPromotionText(barcode,promotionText); // Lấy nội dung thực tế

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

    public By getItemGift(String barcode) {
        return MobileBy.xpath("//android.view.View[contains(@text, '" + barcode + "') and contains(@text, '" + barcode + "')]");
    }

    public boolean verifyPromotionItem(String barcode, String priceText) {
        try {
            // Lấy element bằng barcode
            By promotionItem = getItemGift(barcode);

            // Chờ element xuất hiện
            WebDriverWait wait = new WebDriverWait(mobileDriver, 2);
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(promotionItem));

            // Lấy text của element
            String elementText = element.getText();

            // So sánh text với priceText
            if (elementText.contains(priceText)) {
                System.out.println("Verification passed: Text '" + priceText + "' exists in the element.");
                return true;
            } else {
                System.err.println("Verification failed: Text '" + priceText + "' does not exist in the element.");
                return false;
            }
        } catch (TimeoutException e) {
            System.err.println("Timeout: Element with barcode '" + barcode + "' not found.");
            return false;
        } catch (Exception e) {
            System.err.println("Error occurred during verification: " + e.getMessage());
            return false;
        }
    }






}
