package appLocator;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class LoginScreenLocatorKPOS {
    public static final By USERNAME = MobileBy.xpath("//android.widget.EditText[@text='Mã nhân viên / Email']");
    public static final By PASSWORD = MobileBy.xpath("//android.widget.EditText[@text='Mật khẩu']");
    public static final By LOGIN_BUTTON = MobileBy.xpath("//android.widget.Button[@content-desc='ĐĂNG NHẬP']");
    public static final By NEWBILL_BUTTON = MobileBy.xpath("//android.widget.Button[@content-desc=\"     TẠO ĐƠN MỚI\"]");

    public static final By SEARCH_BRANCH_TEXTBOX = MobileBy.xpath("(//android.widget.EditText[contains(@text, 'Tìm mặt hàng (F3)')])");
    public static final By CUSTOMER_SEARCH = MobileBy.xpath("//android.widget.EditText[@text='Tìm khách hàng (F4)']");
    public static final By CUSTOMER_OL = MobileBy.xpath("//android.widget.ScrollView/android.view.View[2]");
    public static final By CUSTOMER_ID = MobileBy.xpath("//android.widget.EditText[@text=\"Mã khách hàng OneLife\"]");
    public static final By CUSTOMER_ACEPTED = MobileBy.xpath("//android.widget.Button[@content-desc=\"     Áp dụng\"]");
    public static final By giftTextpromotion = MobileBy.xpath("//android.view.View[contains(@content-desc, 'KM')]");
    public static final By PAYBUTTON = MobileBy.xpath("//android.widget.Button[@content-desc='Thanh toán (F9)']");
    public static final By CASHBUTTON = MobileBy.xpath("//android.widget.RadioButton");
    public static final By ONELIFEBUTTON = MobileBy.xpath("//android.view.View[@content-desc=\"Thẻ OneLife\"]/android.widget.RadioButton");
    public static final By MSBBUTTON = MobileBy.xpath("//android.view.View[@content-desc=\"MSB QR\"]/android.widget.RadioButton");
    public static final By QRBUTTON = MobileBy.xpath("//android.view.View[@content-desc=\"QR Code\"]/android.widget.RadioButton");
    public static final By billNumber = MobileBy.xpath("//android.view.View[contains(@content-desc, 'Hoá đơn 1')]");

}
