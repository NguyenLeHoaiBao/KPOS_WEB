package pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyItem {

    private AppiumDriver<MobileElement> mobileDriver;

    public VerifyItem(AppiumDriver<MobileElement> driver) {
        this.mobileDriver = driver;
    }

    // Hàm xây dựng XPath dựa trên Barcode
    public By getPriceItemByBarcode(String barcode) {
        return MobileBy.xpath("(//android.view.View[contains(@text,'" + barcode + "')]//android.view.View)[3]");
    }

    // Hàm lấy giá trị `content-desc` của phần tử dựa trên Barcode
    public String getPriceItem(String barcode) {
        return mobileDriver.findElement(getPriceItemByBarcode(barcode)).getAttribute("content-desc");
    }

    // Hàm xác minh giá trị `priceExpected`
    public void verifyPriceItem(String barcode, String priceExpected) {
        String actualText = getPriceItem(barcode); // Lấy nội dung thực tế

        System.out.println("Actual Text: " + actualText);

        // Kiểm tra xem nội dung thực tế có chứa `priceExpected` hay không
        if (actualText.contains(priceExpected)) {
            System.out.println("Verification passed: Text contains price '" + priceExpected + "'.");
        } else {
            System.out.println("Verification failed: Text does not contain expected price.");
            throw new AssertionError(
                    "Verification failed: Expected text to contain price '" + priceExpected +
                            "'. Actual text: " + actualText
            );
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
        return MobileBy.xpath("//android.view.View[contains(@content-desc, '" + barcode + "')]");
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

}