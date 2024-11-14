package appLocator;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class ReturnVendorLocator {
    public static final By SO_LUONG_TRA_TEXTBOX = MobileBy.xpath("//android.widget.EditText[contains(@text,'Nhập SL đổi')]");
    public static final By BUTTON_HOAN_THANH = MobileBy.xpath("//android.widget.Button[@content-desc='HOÀN THÀNH']");
    public static final By BUTTON_DONG_Y = MobileBy.xpath("//android.view.View[@content-desc='ĐỒNG Ý']");

}
