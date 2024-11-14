package pageUI;

public class AbstractPageUI {
    public static final String MENU_VAN_HANH= "//li[@name='Vận hành']";
    public static final String MENU_VAN_HANH_TRA_HANG_NCC= "(//li[@name='Trả hàng nhà cung cấp'])[1]";
    public static final String MENU_VAN_HANH_TRA_HANG_NCC_DS_YEU_CAU_DOITRA= "//span[text()='DS yêu cầu đổi/trả']/ancestor::a";
    public static final String MENU_VAN_HANH_TRA_HANG_NCC_DS_PHIEU_DOITRA= "//span[text()='DS phiếu đổi/trả']/ancestor::a";

    public static final String PRODUCT_MENU = "//li[@name='Sản phẩm']";
    public static final String PRODUCT_LIST_MENU = "//li[@name='DS sản phẩm']";
    public static final String NO_DATA_ICON = "//img[@class='ant-image-img css-zhbbuq']";
    public static final String AG_TABLE = "//div[@class='ag-root-wrapper ag-ltr ag-layout-normal']";
    public static final String ICON_X_DELETE_TEXT = "//span[@class='ant-input-clear-icon']";
    public static final String AG_TABLE_INDEX_BY_COLUMN_NAME = "//span[text()='%s']/ancestor::div[@class='ag-header-cell ag-focus-managed']";
    public static final String TARGET_CELL = "(//div[@class='ag-body ag-layout-normal']//div[@aria-colindex='%s']//span)[%s]";
    public static final String RETAILS_MENU = "//li[@name='Retails']";
    public static final String RETAIL_OPERATIONS_MENU = "//li[@name='Retail Operations']";
}
