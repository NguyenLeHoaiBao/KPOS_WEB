package pageUI;

public class RetailsProductDetailPageUI {

    public static final String PRODUCT_NAME_TEXTBOX = "//input[@name='name']";
    public static final String CATEGORY_BOX = "//span[text()='Danh mục']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//div[@class='ant-select-selector']";
    public static final String CATEGORY_ICON_X = "(//span[text()='Danh mục']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//*[local-name()='svg'])[2]";
    public static final String CATEGORY_NAME_IN_DROPDOWN = "//span[text()='%s']";
    public static final String ITEM_CLASS_BOX = "//span[text()='Phân loại sản phẩm']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//div[@class='ant-select-selector']/span[1]";
    public static final String ITEM_CLASS_BOX_ICON_X = "(//span[text()='Phân loại sản phẩm']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//*[local-name()='svg'])[2]";
    public static final String ITEM_CLASS_BOX_DROPDOWN_LIST = "//span[text()='Phân loại sản phẩm']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//div[@class='ant-select-selector']//input";
    public static final String BUSINESS_TYPE_BOX = "//span[text()='Hình thức kinh doanh']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//div[@class='ant-select-selector']//span[1]";
    public static final String BUSINESS_TYPE_OPTION = "//div[@title='%s']";
    public static final String ORDER_FORMULA_BOX = "//span[text()='Công thức đặt hàng']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//div[@class='ant-select-selector']/span[1]";
    public static final String ORDER_FORMULA_OPTION = "//span[text()='Công thức đặt hàng']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//div[@class='ant-select-selector']//input";
    public static final String ORIGIN_BOX = "//span[text()='Xuất xứ']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//span[@class='ant-select-selection-search']";
    public static final String ORIGIN_BOX_INPUT = "//span[text()='Xuất xứ']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//span[@class='ant-select-selection-search']/input";
    public static final String TAG_BOX = "//span[text()='Tag']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//div[@class='ant-select-selector']";
    public static final String TAG_DROPDOWN_INPUT = "//span[text()='Tag']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//div//input";
    public static final String VARIANT_NAME = "//div[@col-id='name']/div[@class='flex h-full w-full items-center']/span";
    public static final String VARIANT_NAME_ICON_X = "//input[@placeholder='Tên sản phẩm']/following-sibling::span/span";
    public static final String VARIANT_PRICE = "//div[@col-id='price']/div[@class='flex h-full w-full items-center']/span";
    public static final String SELL_CHECKBOX = "(//div[@col-id='status']//input)[2]";
    public static final String ROLE_SKU_FIELD = "//div[@col-id='role_sku']/div[@class='flex h-full w-full items-center']/span";
    public static final String SAVE_BUTTON = "//button/div[text()='Lưu']";
    public static final String TRANSIT_CHECKBOX = "//span[text()='Hàng quá cảnh']/preceding-sibling::span/input";
    public static final String SEASONAL_CHECKBOX = "//span[text()='Kinh doanh mùa vụ']/preceding-sibling::span/input";
    public static final String SEASON_RANGE_TEXTBOX = "//div[@col-id='seasonal_sale_type' and @role='gridcell']";
}
