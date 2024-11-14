package appLocator;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class NavigationMenuLocator {
    public static final By TRANSFER_MENU = MobileBy.xpath("//android.view.View[@content-desc='Chuyển/Nhận hàng']");
    public static final By DOI_HANG_NCC_MENU = MobileBy.xpath("//android.view.View[@content-desc='Đổi hàng NCC']");
    public static final By SELECT_BRANCH_BUTTON = MobileBy.xpath("//android.view.View[@content-desc='Nhân viên bán hàng']/following-sibling::android.view.View[1]");
    public static final By CURRENT_BRANCH = MobileBy.xpath("(//android.view.View[@content-desc='Chi nhánh']/following-sibling::android.view.View)[1]");
    public static final By BUTTON_LUU_LAI = MobileBy.xpath("//android.view.View[@content-desc='Lưu lại']");
   }
