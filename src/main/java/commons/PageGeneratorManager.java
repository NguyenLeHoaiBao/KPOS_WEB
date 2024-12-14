package commons;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import pageObject.DashboardPageObject;
import pageObject.KposPageObject;
import pageObject.WebPageObject;

public class PageGeneratorManager extends AbstractPage {

    public static WebPageObject getLoginPage(WebDriver driver) {
        return new WebPageObject(driver);
    }

    public static DashboardPageObject getDashboardPage(WebDriver driver) {
        return new DashboardPageObject(driver);
    }

    public static KposPageObject getKposPage(AppiumDriver driver) {
        return new KposPageObject(driver);
    }



//    public static ProductListPageObject getProductListPage(WebDriver driver) {
//        return new ProductListPageObject(driver);
//    }

//    public ProductListPageObject openProductListPage(WebDriver driver) {
//        clickToElement(driver, AbstractPageUI.PRODUCT_MENU);
//        clickToElement(driver, AbstractPageUI.PRODUCT_LIST_MENU);
//        return new ProductListPageObject(driver);
//    }
}
