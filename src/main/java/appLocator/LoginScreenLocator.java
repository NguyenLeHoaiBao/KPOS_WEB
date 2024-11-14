package appLocator;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginScreenLocator {
    public static final By USERNAME = MobileBy.xpath("//android.widget.EditText[@text='Mã nhân viên / Email']");
    public static final By PASSWORD = MobileBy.xpath("//android.widget.EditText[@text='Mật khẩu']");
    public static final By LOGIN_BUTTON = MobileBy.xpath("//android.widget.Button[@content-desc='ĐĂNG NHẬP']");
    public static final By SEARCH_BRANCH_TEXTBOX = MobileBy.xpath("//android.widget.EditText[@text='Tìm kiếm chi nhánh']");
    public static final By RESULT_SEARCH_BRANCH = MobileBy.xpath("//android.view.View[contains(@content-desc, 'Q07 - 436 Nguyễn Thị Thập')]");
    public static final By NAVIGATION_MENU = MobileBy.xpath("//android.widget.Button[@content-desc='Open navigation menu']");
}
