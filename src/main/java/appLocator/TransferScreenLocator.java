package appLocator;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class TransferScreenLocator {
    public static final By SEARCH_BOX = MobileBy.xpath("//android.widget.EditText[@text='Nhập mã phiếu / thùng / barcode']");
    public static final By BUTTON_HOAN_THANH = MobileBy.xpath("//android.view.View[@content-desc='HOÀN THÀNH']");
    public static final By BUTTON_DONG_Y = MobileBy.xpath("//android.view.View[@content-desc='ĐỒNG Ý']");
    public static final By BUTTON_NHAN_NGUYEN_THÙNG = MobileBy.xpath("//android.widget.Button[@content-desc='NHẬN NGUYÊN THÙNG']");
    public static final By NHAN_NGUYEN_THUNG_CHỌN_TAT_CA = MobileBy.xpath("(//android.widget.CheckBox)[1]");

    public static final By BUTTON_NHAN_THUNG = MobileBy.xpath("//android.view.View[@content-desc='NHẬN THÙNG']");
    public static final By DONG_Y_NHAN_NGUYEN_THUNG = MobileBy.xpath("//android.view.View[@content-desc='ĐỒNG Ý']");
    public static final By HOAN_TAT_CHUYEN_BACK_BUTTON = MobileBy.xpath("//android.widget.Button[@content-desc='Back']");
}
