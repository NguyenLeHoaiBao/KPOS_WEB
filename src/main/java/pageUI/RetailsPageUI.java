package pageUI;

public class RetailsPageUI {

    public static final String PRODUCT_TAB = "//div[@class='ant-tabs-tab-btn' and text() = 'Product']";

    public static final String FILTER_BUTTON = "//div[@class='ant-col ant-col-12 flex css-zhbbuq']//button[2]";


    //Filter
//    public static final String FILTER_PRODUCT = "//span[text()='Product']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//span[@class='ant-select-selection-placeholder']";
    public static final String FILTER_PRODUCT = "//span[text()='Product']/ancestor::div[@class='ant-col ant-form-item-label css-zhbbuq']/following-sibling::div//div[@class='ant-select-selection-overflow']";

    public static final String APPLY_FILTER_BUTTON = "//div[text()='Áp dụng']";
    public static final String EDIT_BUTTON_BY_BARCODE = "//span[contains(text(),'%s')]/div/*[local-name()='svg'][4]";
}
