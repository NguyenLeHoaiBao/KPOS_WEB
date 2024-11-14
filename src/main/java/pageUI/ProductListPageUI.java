package pageUI;

public class ProductListPageUI {
    public static final String SEARCH_BAR = "//input[@name='search_text']";
    public static final String NO_DATA_ICON = "//img[@class='ant-image-img css-zhbbuq']";
    public static final String BARCODE_IN_ROW = "//div[@col-id='barcode' and @role='gridcell']//span";
    public static final String PRODUCT_NAME_IN_ROW = "//div[@col-id='name' and @role='gridcell']//span";
    public static final String PRODUCT_NAME_BY_BARCODE = "//div[@col-id='barcode' and @role='gridcell']//span[text()='%s']/ancestor::div[@role = 'row']/div[@col-id='name']//span";


}
