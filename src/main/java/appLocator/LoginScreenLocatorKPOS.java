package appLocator;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class LoginScreenLocatorKPOS {
//    Button dang nhap
    public static final By USERNAME = MobileBy.xpath("//android.widget.EditText[@text='Mã nhân viên / Email']");
    public static final By PASSWORD = MobileBy.xpath("//android.widget.EditText[@text='Mật khẩu']");
    public static final By LOGIN_BUTTON = MobileBy.xpath("//android.widget.Button[@content-desc='ĐĂNG NHẬP']");

    //Tao bill moi
    public static final By NEWBILL_BUTTON = MobileBy.xpath("//android.widget.Button[@content-desc=\"     TẠO ĐƠN MỚI\"]");
//  Search san pham
    public static final By SEARCH_BRANCH_TEXTBOX = MobileBy.xpath("(//android.widget.EditText[contains(@text, 'Tìm mặt hàng (F3)')])");

    //Search khach hang
    public static final By CUSTOMER_SEARCH = MobileBy.xpath("//android.widget.EditText[@text='Tìm khách hàng (F4)']");
    public static final By CUSTOMER_OL = MobileBy.xpath("//android.widget.ScrollView/android.view.View[2]");
    public static final By CUSTOMER_ID = MobileBy.xpath("//android.widget.EditText[@text=\"Mã khách hàng OneLife\"]");
    public static final By CUSTOMER_ACEPTED = MobileBy.xpath("//android.widget.Button[@content-desc=\"     Áp dụng\"]");

//    Button PTTT
    public static final By CHOSENPAYMENT_BUTTON = MobileBy.xpath("//android.view.View[@content-desc=\"Khách thanh toán (F8)\"]/following-sibling::android.view.View[1]");
    public static final By OK_BUTTON = MobileBy.xpath("//android.widget.Button[@content-desc=\"     Xong \"]");
    public static final By ONELIFE_BUTTON = MobileBy.xpath("//android.widget.ImageView[@content-desc=\"     Thẻ OneLife\"]");
    public static final By Accepted_OL = MobileBy.xpath("//android.view.View[@content-desc=\"Xác nhận đã thanh toán\"]");
    public static final By OK_OL = MobileBy.xpath("//android.widget.Button[@content-desc=\"     CÓ\"]");
    public static final By MSB_BUTTON = MobileBy.xpath("//android.widget.ImageView[@content-desc=\"     MSB QR\"]");
    public static final By Accepted_QRCODE = MobileBy.xpath("//android.widget.Button[contains(@content-desc, 'Xác nhận')]\n");
    public static final By QRCODE_BUTTON = MobileBy.xpath("//android.widget.ImageView[@content-desc=\"     QR Code\"]");
    public static final By TCB_BUTTON = MobileBy.xpath("//android.widget.Button[@content-desc=\"     Techcombank QR\"]");
    public static final By PAYBUTTON = MobileBy.xpath("//android.widget.Button[@content-desc='Thanh toán (F9)']");
    public static final By CASHBUTTON = MobileBy.xpath("//android.widget.RadioButton");
    public static final By ONELIFEBUTTON = MobileBy.xpath("//android.view.View[@content-desc=\"Thẻ OneLife\"]/android.widget.RadioButton");
    public static final By MSBBUTTON = MobileBy.xpath("//android.view.View[@content-desc=\"MSB QR\"]/android.widget.RadioButton");
    public static final By QRBUTTON = MobileBy.xpath("//android.view.View[@content-desc=\"QR Code\"]/android.widget.RadioButton");

    // Ma hoa don KPOS
    public static final By billNumber = MobileBy.xpath("//android.view.View[contains(@content-desc, 'Hoá đơn 1')]");
    public static final By totalBill_KPOS = MobileBy.xpath("//android.view.View[@content-desc=\"Khách cần trả\"]/following-sibling::android.view.View[1]");

}
