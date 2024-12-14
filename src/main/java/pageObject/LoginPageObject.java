package pageObject;

import commons.AbstractPage;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUI.LoginPageUI;

import java.time.Duration;

import static config.DriverFactory.getWebDriver;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageObject inputUserName() {
        sendkeyToElement(driver, LoginPageUI.USERNAME, GlobalConstants.USERNAME);
        return this;
    }

    public LoginPageObject inputPassword() {
        sendkeyToElement(driver, LoginPageUI.PASSWORD, GlobalConstants.PASSWORD);
        return this;
    }

    public void clickLoginButton() {
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }

    public DashboardPageObject loginFlow() {
        LoginPageObject loginPage = new LoginPageObject(driver);
        loginPage.inputUserName()
                .inputPassword()
                .clickLoginButton();

        return PageGeneratorManager.getDashboardPage(driver);
    }

    public LoginPageObject goSelllist() {
        clickToElement(driver, LoginPageUI.Sell);
        return this;
    }

    public LoginPageObject goInvoicelist() {
        clickToElement(driver, LoginPageUI.Invoice);
        return this;
    }

    public LoginPageObject detailInvoice() {
        clickToElement(driver, LoginPageUI.InvoiceSearch);
        return this;
    }

    //    Ham search hoa don tren KDB
    public LoginPageObject searchAndSubmitInvoice(String textFromKP) {
        LoginPageObject loginpage = new LoginPageObject(driver);
        loginpage.goSelllist();
        loginpage.goInvoicelist();
        loginpage.detailInvoice();
        sendkeyToElement(driver, LoginPageUI.InvoiceSearch, textFromKP);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.RETURN).perform();
        return this;
    }

    public LoginPageObject gotoInvoicelist() {
        LoginPageObject loginpage = new LoginPageObject(driver);
        sleepInSeconds(3);
        loginpage.goSelllist();
        System.out.println("Step 1: Clicking mo trang ban hang.");
        sleepInSeconds(10);
        System.out.println("Step 2: Clicking mo trang hoa don");
        loginpage.goInvoicelist();
        return this;
    }


    public String getTotalPriceInvoice(String Noidungcankiemtra) {
        return getWebDriver().findElement(By.xpath("//div[contains(@class, 'pl-5') and contains(text(), '"+Noidungcankiemtra+"')]/following-sibling::div[contains(@class, 'pr-2')]")).getText();
    }

    public void verifyTotalPriceItem(String priceExpectedKDB, String Noidungcankiemtra) {
        // Chuyển đổi dấu `.` thành dấu `,` trong giá trị truyền vào
        String formattedPriceExpected = priceExpectedKDB.replace(".", ",");

        // Lấy giá trị thực tế từ hàm getTotalPriceInvoice
        String actualText = getTotalPriceInvoice(Noidungcankiemtra);

        // Thực hiện kiểm tra
        if (actualText.contains(formattedPriceExpected)) {
            System.out.println(Noidungcankiemtra + " '" + formattedPriceExpected + "'. Verification passed.");
        } else {
            System.out.println(Noidungcankiemtra + " '" + formattedPriceExpected + "'. Verification failed.");
            throw new AssertionError("Text verification failed: " + Noidungcankiemtra + " '" + formattedPriceExpected + "' not found. Actual: " + actualText);
        }
    }


    public LoginPageObject detailInvoice(String Invoicecode) {
        System.out.println("Mo trang chi tiet hoa don '"+ Invoicecode +"'");
        clickToElement(driver, "//table[@class='htCore']//tbody//tr[1]//td[2]//div[contains(text(), '"+Invoicecode+"')]");
        return this;
    }

    public String priceInvoice(String Barcode) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            // Đợi element chứa Barcode xuất hiện
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='wtHolder']//div[contains(text(),'"+Barcode+"')]/ancestor::tr//td[position()=8]")
            ));

            // Lấy text bằng JavascriptExecutor nếu Selenium không lấy được text trực tiếp
            String priceText = element.getText();
            if (priceText == null || priceText.trim().isEmpty()) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                priceText = (String) js.executeScript("return arguments[0].textContent;", element);
            }
            return priceText.trim();
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Không tìm thấy dòng chứa Barcode: " + Barcode, e);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy giá trị cột Đơn giá từ Barcode: " + Barcode, e);
        }
    }

    public void verifyPriceInvoiceline(String Barcode, String priceExpectedKDB) {
        // Chuyển đổi dấu `.` thành dấu `,` trong giá trị truyền vào
        String formattedPriceExpected = priceExpectedKDB.replace(".", ",");

        // Lấy giá trị thực tế từ hàm priceInvoice
        String actualText = priceInvoice(Barcode);

        // Thực hiện kiểm tra
        if (actualText.contains(formattedPriceExpected)) {
            System.out.println("Đơn giá của Barcode '" + Barcode + "' là '" + formattedPriceExpected + "'. Verification passed.");
        } else {
            System.out.println(" '" + Barcode + "'Không đúng số tiền '" + formattedPriceExpected + "'. Verification failed.");
            throw new AssertionError("Text verification failed: Expected '" + formattedPriceExpected + "' not found. Actual: " + actualText);
        }
    }


    public DashboardPageObject loginFlowWithInvalidUsernanePassword(String userName, String password) {
        LoginPageObject loginPage = new LoginPageObject(driver);
        loginPage.sendkeyToElement(driver, LoginPageUI.USERNAME, userName);
        loginPage.sendkeyToElement(driver, LoginPageUI.PASSWORD, password);
        loginPage.clickLoginButton();
        return PageGeneratorManager.getDashboardPage(driver);
    }

    public boolean wrongPasswordWarning() {
        waitForElementVisible(driver, LoginPageUI.WRONG_PASSWORD_WARNING);
        return isElementDisplay(driver, LoginPageUI.WRONG_PASSWORD_WARNING);
    }

    public boolean invalidUserNameWarning() {
        waitForElementVisible(driver, LoginPageUI.INVALID_USERNAME_WARNING);
        return isElementDisplay(driver, LoginPageUI.INVALID_USERNAME_WARNING);
    }

    public boolean notEnoughPasswordLetter() {
        waitForElementVisible(driver, LoginPageUI.INVALID_PASSWORD);
        return isElementDisplay(driver, LoginPageUI.INVALID_PASSWORD);
    }
}
