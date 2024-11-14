package commons;

import org.openqa.selenium.WebDriver;
import pageObject.DashboardPageObject;
import pageObject.LoginPageObject;

public class PageGeneratorManager extends AbstractPage{

    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static DashboardPageObject getDashboardPage(WebDriver driver) {
        return new DashboardPageObject(driver);
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
