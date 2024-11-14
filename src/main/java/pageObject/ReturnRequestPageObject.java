package pageObject;

import commons.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pageUI.ReturnRequestPageUI;

public class ReturnRequestPageObject extends AbstractPage {
    WebDriver driver;

    public ReturnRequestPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToAddNewBtn() {
        clickToElement(driver, ReturnRequestPageUI.BUTTON_THEM_MOI);
        switchTab(driver);
    }

    public void inputTextToTable(String columnName, int soDong, String value) {
        //get column index by columnName
        String columnIndex = getElementAttribute(driver, ReturnRequestPageUI.AG_COLUMN_NAME, "aria-colindex",columnName);
        // Lay row-index khi khai bao so dong can nhap
        String rowIndex = String.valueOf(soDong - 1);
        senKeyToTextboxAGTable(driver, ReturnRequestPageUI.INPUT_CELL,value, rowIndex, columnIndex);
    }

    public void clickToCreateRequest() {
        clickToElement(driver,ReturnRequestPageUI.CREATE_BUTTON);
    }

    public void inputHSD(String columnName, int soDong, String value) {
        String columnIndex = getElementAttribute(driver, ReturnRequestPageUI.AG_COLUMN_NAME, "aria-colindex",columnName);
        // Lay row-index khi khai bao so dong can nhap
        String rowIndex = String.valueOf(soDong - 1);
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(byXpath(castToObject(ReturnRequestPageUI.INPUT_CELL, rowIndex, columnIndex)));
//        scrollToElement(driver,locator);
        actions.moveToElement(element).doubleClick().sendKeys(value).build().perform();
        sleepInSeconds(1);
        actions.sendKeys(Keys.TAB).build().perform();
    }

    public void clickToFistItemCheckbox() {
        clickToElement(driver, "//div[@class='ag-pinned-left-cols-container']//div[@row-index='0']/div[@col-id='checkbox_id']");
    }

    public void clickToThaoTacBtn() {
        clickToElement(driver,"//button/div[text()='Thao tác']");
    }

    public void clickToTaoPhieuChuyenBtn() {
        clickToElement(driver, "//button//span[text()='Tạo phiếu chuyển']");
    }


    public void clickToTaoPhieuVaGuiMail() {
        clickToElement(driver, ReturnRequestPageUI.BUTTON_TAO_PHIEU_VA_GUI_MAIL);
    }
}
