package pageUI;

public class LoginPageUI {

    public static final String USERNAME = "//input[@name='account']";
    public static final String PASSWORD = "//input[@name='password']";
    public static final String LOGIN_BUTTON = "//button[@type='submit']";
    public static final String REGISTER_LINK = "//*[text()='Đăng ký ngay']";
    public static final String WRONG_PASSWORD_WARNING = "//div[text()='Mật khẩu không đúng']";
    public static final String INVALID_USERNAME_WARNING = "//div[text()='Mã nhân viên không tồn tại']";
    public static final String INVALID_PASSWORD = "//div[text()='Mật khẩu không hợp lệ']";
    public static final String Sell = "//span[contains(text(),'Bán hàng')]";
    public static final String Invoice = "//span[contains(text(),'Hóa đơn')]";
    public static final String InvoiceSearch = "//input[@placeholder='Tìm kiếm theo Tìm kiếm theo mã hóa đơn']\n";
    public static final String firstCell = "(//table[@class=\"htCore\"]//tbody/tr[1]/td[1])[1]\n";
    public static final String totalPriceCell = "(//table[@class=\"htCore\"]//tbody/tr[1]/td[12])[1]";
    public static final String mahoadon = "//table[@class='htCore']//tbody//tr[1]//td[2]//div[contains(text(), 'KP010718691')]\n";
    public static final String KhachcantraKDB = "//div[contains(@class, 'pl-5') and contains(text(), 'Khách cần trả:')]/following-sibling::div[contains(@class, 'pr-2')]";
    public static final String ThoitienleKDB = "//div[contains(@class, 'pl-5') and contains(text(), 'Thối tiền lẻ:')]/following-sibling::div[contains(@class, 'pr-2')]";
    public static final String Tienkhachdua = "//div[contains(@class, 'pl-5') and contains(text(), 'Tiền khách đưa:')]/following-sibling::div[contains(@class, 'pr-2')]";
    public static final String Khachdatra = "//div[contains(@class, 'pl-5') and contains(text(), 'Khách đã trả')]/following-sibling::div[contains(@class, 'pr-2')]";

}
