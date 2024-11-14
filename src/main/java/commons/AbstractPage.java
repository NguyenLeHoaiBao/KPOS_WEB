package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.PurchaseReturnListPageObject;
import pageObject.ReturnRequestPageObject;
import pageUI.AbstractPageUI;


public abstract class AbstractPage {
    private Select select;
    private JavascriptExecutor js;
    private WebDriverWait explicitWait;
    private WebElement element;
    private List<WebElement> elements;
    private Actions action;
    private AppiumDriver<MobileElement> mobileDriver;

    public void openUrl(WebDriver driver, String urlValue) {
        driver.get(urlValue);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void back(WebDriver driver) {
        driver.navigate().back();
    }

    public void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void forward(WebDriver driver) {
        driver.navigate().forward();

    }

    public void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public void cancelAler(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public void senkeyToAlear(WebDriver driver, String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    public String getTextInAlert(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    public void waitAlertPresent(WebDriver driver) {
        WebDriverWait explicitWait;
//        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
//        explicitWait.until(ExpectedConditions.alertIsPresent());
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void switchWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String runWindows : allWindowIDs) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
            }
        }
    }

    public void switchWindowByTitle(WebDriver driver, String targetTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String runWindows : allWindowIDs) {
            driver.switchTo().window(runWindows);
            String currentWindowTitle = driver.getTitle();
            if (currentWindowTitle.equals(targetTitle)) {
                break;
            }
        }
    }

    public boolean areAllWindowsCloseWithoutParent(WebDriver driver, String parentWindow) {

        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String runWindows : allWindowIDs) {
            if (!runWindows.equals(parentWindow)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        if (driver.getWindowHandles().size() == 1) {
            return true;
        }
        return false;
    }

    public By byXpath(String locator) {
        return By.xpath(locator);
    }

    public WebElement findElementByXpath(WebDriver driver, String locator) {
        return driver.findElement(byXpath(locator));
    }
    public WebElement findElementByXpath(WebDriver driver, String locator, String...values) {
        return driver.findElement(byXpath(castToObject(locator, values)));
    }

    public String castToObject(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
        return driver.findElements(byXpath(locator));
    }
    public List<WebElement> findElementsByXpath(WebDriver driver, String locator, String...values) {
        return driver.findElements(byXpath(castToObject(locator, values)));
    }

    public void sleepInSeconds(long timeOut) {
        try {
            Thread.sleep(timeOut * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickToElement(WebDriver driver, String locator) {
        waitForElementClickable(driver, locator);
        highlightElement(driver,locator);
        if(driver.toString().toLowerCase().contains("internet explorer")) {
            clickToElementByJS(driver, locator);
            sleepInSeconds(5);
        }else {
            scrollToElement(driver,locator);
            findElementByXpath(driver, locator).click();
        }
    }

    public void clickToElement(WebDriver driver, String locator, String... values) {
        if(driver.toString().toLowerCase().contains("internet explorer")) {
            clickToElementByJS(driver, locator, values);
        }else {
            scrollToElement(driver,locator,values);
            findElementByXpath(driver, locator, values).click();

        }
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value) {
        waitForElementVisible(driver, locator);
        scrollToElement(driver,locator);
        highlightElement(driver,locator);
        element = findElementByXpath(driver, locator);
        element.clear();
        element.sendKeys(value);
    }

    public void senKeyToElementByActionMethod(WebDriver driver, String locator, String value) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(locator));
        scrollToElement(driver,locator,value);
        highlightElement(driver,locator);
        actions.moveToElement(element).click().sendKeys(value).build().perform();
        sleepInSeconds(1);
        actions.sendKeys(Keys.ENTER).build().perform();
    }
    public void selectOptionInAGTable(WebDriver driver, String locator, String value) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(locator));
        scrollToElement(driver,locator,value);
        highlightElement(driver,locator);
        actions.moveToElement(element).doubleClick().sendKeys(value).build().perform();
        sleepInSeconds(1);
        actions.sendKeys(Keys.ENTER).build().perform();
    }


    public void senKeyToTextboxAGTable(WebDriver driver, String locator, String value) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(locator));
        scrollToElement(driver,locator);
        highlightElement(driver,locator);
        sendKeyboardToElement(driver, locator, Keys.DELETE);
        actions.moveToElement(element).doubleClick().sendKeys(value).build().perform();
        sleepInSeconds(1);
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public void senKeyToTextboxAGTable(WebDriver driver, String locator, String value, String ...values) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(byXpath(castToObject(locator, values)));
//        scrollToElement(driver,locator);
        actions.moveToElement(element).doubleClick().sendKeys(value).build().perform();
        sleepInSeconds(1);
        actions.sendKeys(Keys.ENTER).build().perform();
    }


    public void sendkeyToElement(WebDriver driver, String locator, String value, String...values) {
//        waitForElementVisible(driver, locator, castToObject(locator, values));
        element = findElementByXpath(driver, castToObject(locator, values));
//        element.clear();
        element.sendKeys(value);
    }

    public String getElementText(WebDriver driver, String locator) {
        return findElementByXpath(driver, locator).getText().trim();
    }

    public String getElementText(WebDriver driver, String locator, String...values) {
        return findElementByXpath(driver, castToObject(locator, values)).getText().trim();
    }

    public String getElementAttribute(WebDriver driver, String locator, String AttributeName) {
        waitForElementInvisible(driver,locator);
        return findElementByXpath(driver, locator).getAttribute(AttributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator, String AttributeName, String...values) {
        return findElementByXpath(driver, castToObject(locator, values)).getAttribute(AttributeName);
    }

    public void selectValueInDropdown(WebDriver driver, String locator, String value) {
        select = new Select(findElementByXpath(driver, locator));
        select.selectByVisibleText(value);
    }

    public String getSelectedIteminDropdown(WebDriver driver, String locator) {
        select = new Select(findElementByXpath(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String allItemsLocator,
                                           String targetValue) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, parentLocator);
        js.executeScript("arguments[0].click();", element);
//        element.click();
        sleepInSeconds(1);
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(allItemsLocator)));
        elements = findElementsByXpath(driver, allItemsLocator);
        for (WebElement item : elements) {
            if (item.getText().equals(targetValue)) {
                if (item.isDisplayed()) {
                    js.executeScript("arguments[0].click();", item);
                } else {
                    js.executeScript("arguments[0].scrollIntoView(true);", item);
                    sleepInSeconds(1);
                    js.executeScript("arguments[0].click();", item);
                }
                sleepInSeconds(1);
                break;
            }
        }
    }

    public int countElementNumber(WebDriver driver, String locator) {
        return findElementsByXpath(driver, locator).size();
    }
    public int countElementNumber(WebDriver driver, String locator, String...values) {
        return findElementsByXpath(driver, castToObject(locator, values)).size();
    }

    public void checkToCheckbox(WebDriver driver, String locator) {
        element = findElementByXpath(driver, locator);
        scrollToElement(driver,locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        element = findElementByXpath(driver, locator);
        scrollToElement(driver,locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplay(WebDriver driver, String locator) {
        try {
            return findElementByXpath(driver, locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementUndisplay(WebDriver driver, String locator) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = findElementsByXpath(driver, locator);
        if(elements.size() == 0) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        }
        else if(elements.size() > 0 && !elements.get(0).isDisplayed()) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        }
        else {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return false;
        }
    }

    private void overrideGlobalTimeout(WebDriver driver, int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

    }

    public boolean isElementDisplay(WebDriver driver, String locator, String... values) {
        return findElementByXpath(driver, castToObject(locator, values)).isDisplayed();

    }

    public boolean isElementUnDisplay(WebDriver driver, String locator, String... values) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = findElementsByXpath(driver, castToObject(locator, values));
        if(elements.size() == 0) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        }
        else if(elements.size() > 0 && !elements.get(0).isDisplayed()) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        }
        else {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return false;
        }
    }

    public boolean isElementEnable(WebDriver driver, String locator) {
        return findElementByXpath(driver, locator).isEnabled();

    }

    public boolean isElementselected(WebDriver driver, String locator) {
        return findElementByXpath(driver, locator).isSelected();

    }

    public void switchToFrameorIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(findElementByXpath(driver, locator));
    }

    public void switchToDefault(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverMouseToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(findElementByXpath(driver, locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        scrollToElement(driver,locator);
        action.doubleClick(findElementByXpath(driver, locator)).perform();
    }
    public void doubleClickToElement(WebDriver driver, String locator, String...values) {
        action = new Actions(driver);
        scrollToElement(driver,locator,values);
        action.doubleClick(findElementByXpath(driver, castToObject(locator, values))).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.contextClick(findElementByXpath(driver, locator)).perform();
    }

    public void DragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
        action = new Actions(driver);
        action.dragAndDrop(findElementByXpath(driver, sourceLocator), findElementByXpath(driver, targetLocator))
                .perform();
    }

    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
        scrollToElement(driver,locator);
        action.sendKeys(findElementByXpath(driver, locator), key).perform();
    }

    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String...values) {
        action = new Actions(driver);
        scrollToElement(driver,locator);
        action.sendKeys(findElementByXpath(driver, castToObject(locator, values)), key).perform();
    }


    public void upload1FileBySenkey(WebDriver driver, String locator, String imagepath) {
        findElementByXpath(driver, locator).sendKeys(imagepath);

    }

    public void upload3FilesBySenkey(WebDriver driver, String locator, String imagepath1, String imagepath2,
                                     String imagepath3) {
        findElementByXpath(driver, locator).sendKeys(imagepath1 + "\n" + imagepath2 + "\n" + imagepath3);
    }

    public Object executeForBrowser(WebDriver driver, String javaSript) {
        js = (JavascriptExecutor) driver;
        return js.executeScript(javaSript);
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        element = findElementByXpath(driver, locator);
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
//		js.executeScript("arguments[0].setAttribute('value', '')", element);

    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value, String...values) {
        element = findElementByXpath(driver, castToObject(locator,values));
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
//		js.executeScript("arguments[0].setAttribute('value', '')", element);

    }

    public boolean verifyTextInInnerText(WebDriver driver, String expectedText) {
        js = (JavascriptExecutor) driver;
        String textActual = (String) js
                .executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
        return textActual.equals(expectedText);
    }

    public void scrollToBottomPage(WebDriver driver) {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
    public void scrollToTopPage(WebDriver driver) {
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }


    public void highlightElement(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
                "border: 5px solid red; border-style: dashed;");
//        sleepInSeconds(1);
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

    };
    public void highlightElement(WebDriver driver, String locator, String...values) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator, values);
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
                "border: 5px solid red; border-style: dashed;");
//        sleepInSeconds(1);
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        highlightElement(driver,locator);
        scrollToElement(driver,locator);
        js.executeScript("arguments[0].click();", element);
    }
    public void clickToElementByJS(WebDriver driver, String locator, String...values) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, castToObject(locator, values));
        highlightElement(driver,locator);
        scrollToElement(driver,locator);
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        while (!findElementByXpath(driver, locator).isDisplayed()) {
            js.executeScript("window.scrollBy(1000, 0);"); // Cuộn ngang 100px mỗi lần
            // Kiểm tra lại phần tử có hiển thị không
            if (element.isDisplayed()) {
                break;
            }
        }
    }

    public void scrollHorizontal(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);    }

    public void scrollToElement(WebDriver driver, String locator, String...values) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, castToObject(locator, values));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        js = (JavascriptExecutor) driver;
        element = findElementByXpath(driver, locator);
        boolean status = (boolean) js.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                element);
        if (status) {
            return true;
        }
        return false;
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
    }

    public void waitForElementsVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        List<WebElement> elements = findElementsByXpath(driver, locator);
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... values) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(locator, values))));
    }


    public void waitForElementInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
    }

    public void waitForElementsInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        List<WebElement> elements = findElementsByXpath(driver, locator);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    public void waitForElementInvisible(WebDriver driver, String locator, String... values) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToObject(locator, values))));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
    }


    public void waitForElementClickable(WebDriver driver, String locator, String...values) {
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(locator, values))));
    }

    public void switchTab(WebDriver driver){
// Lấy handle của cửa sổ hiện tại
        String currentTab = driver.getWindowHandle();

// Mở tab mới (giả sử có hành động nào đó mở tab mới, ví dụ: click vào link)
// Lấy tất cả các window handles
        Set<String> allTabs = driver.getWindowHandles();

// Duyệt qua các window handles
        for (String tab : allTabs) {
            // Kiểm tra nếu không phải là tab hiện tại thì chuyển đổi sang tab đó
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }

    public void hoverToTexboxAndClickIconX(WebDriver driver, String textBoxLocator, String iconXLocator){
        // Tạo đối tượng Actions
        Actions actions = new Actions(driver);

        // Hover vào textbox
        actions.moveToElement(findElementByXpath(driver,textBoxLocator)).perform();

        // Chờ icon "x" xuất hiện (dùng WebDriverWait)
        WebDriverWait wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        WebElement closeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(iconXLocator))); // hoặc By.xpath
        clickToElement(driver,iconXLocator);
    }

//    public ProductListPageObject clickToProductListMenu(WebDriver driver){
//        clickToElement(driver, AbstractPageUI.PRODUCT_MENU);
//        clickToElement(driver, AbstractPageUI.PRODUCT_LIST_MENU);
//        return new ProductListPageObject(driver);
//    }
//
//    public RetailPageObject clickToRetailOperationsMenu(WebDriver driver) {
//        clickToElement(driver, AbstractPageUI.RETAILS_MENU);
//        clickToElement(driver, AbstractPageUI.RETAIL_OPERATIONS_MENU);
//        return new RetailPageObject(driver);
//    }

    public String getElementTextByJS(WebDriver driver, String locator){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = findElementByXpath(driver,locator);
        return (String) js.executeScript("return arguments[0].innerText;", element);
    }

    public ReturnRequestPageObject clickToReturnRequestPage(WebDriver driver) {
        clickToElement(driver, AbstractPageUI.MENU_VAN_HANH);
        clickToElement(driver, AbstractPageUI.MENU_VAN_HANH_TRA_HANG_NCC);
        clickToElement(driver, AbstractPageUI.MENU_VAN_HANH_TRA_HANG_NCC_DS_YEU_CAU_DOITRA);
        return new ReturnRequestPageObject(driver);
    }
    public PurchaseReturnListPageObject clickToPurchaseReturnListPage(WebDriver driver) {
        clickToElement(driver, AbstractPageUI.MENU_VAN_HANH);
        clickToElement(driver, AbstractPageUI.MENU_VAN_HANH_TRA_HANG_NCC);
        clickToElement(driver, AbstractPageUI.MENU_VAN_HANH_TRA_HANG_NCC_DS_PHIEU_DOITRA);
        return new PurchaseReturnListPageObject(driver);
    }




    //================================Open pages=====================================





    //================================MOBILE APP====================================

    public MobileElement findElement(AppiumDriver mobileDriver, By by) {
        return (MobileElement) mobileDriver.findElement(by);
    }

    public void sendKeyToMobileTextBox(AppiumDriver mobileDriver, By by, String value) {
        waitForMobileElementDisplayed(mobileDriver, by);
        findElement(mobileDriver, by).clear();
        findElement(mobileDriver, by).sendKeys(value);
    }

    public void waitForMobileElementDisplayed(AppiumDriver mobileDriver, By by) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForMobileElementIsClickable(AppiumDriver mobileDriver, By by) {
        WebDriverWait wait = new WebDriverWait(mobileDriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void clickToMobileElem(AppiumDriver mobileDriver, By by) {
        waitForMobileElementIsClickable(mobileDriver, by);
        findElement(mobileDriver, by).click();
    }

    public String getMobileElementText(AppiumDriver mobileDriver,By by) {
//        waitForElementDisplayed(by);
        return findElement(mobileDriver, by).getText();
    }

    public void swipeVerticallyToMobileElement(AppiumDriver mobileDriver,List<MobileElement> elements) {
        int width = mobileDriver.manage().window().getSize().getWidth();
        int height = mobileDriver.manage().window().getSize().getHeight();
        int xStartPoint = (width * 50) / 100;
        int xEndPoint = xStartPoint;
        int yStartPoint = (height * 50) / 100;
        int yEndPoint = (height * 10) / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
        while (elements.size() == 0) {
            TouchAction action = new TouchAction(mobileDriver);
            action.press(startPoint).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(endPoint).release().perform();
            List<MobileElement> elems = elements;
            if (elems.size() > 0)
                break;
        }
    }


    public void swipeMobileHorizontally(AppiumDriver mobileDriver) {
        int width = mobileDriver.manage().window().getSize().getWidth();
        int height = mobileDriver.manage().window().getSize().getHeight();
        int xStartPoint = (width * 70) / 100;
        int xEndPoint = (width * 20) / 100;
        int yStartPoint = (height * 70) / 100;
        int yEndPoint = yStartPoint;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
        TouchAction action = new TouchAction(mobileDriver);
        action.longPress(startPoint).moveTo(endPoint).release().perform();

    }

    public void clickOnMobileCenterScreenIOS(AppiumDriver mobileDriver) {
        String platformName = mobileDriver.getPlatformName();
        if (platformName.equalsIgnoreCase("ios")) {
            int width = mobileDriver.manage().window().getSize().getWidth();
            int height = mobileDriver.manage().window().getSize().getHeight();
            int xStartPoint = (width * 50) / 100;
            int yStartPoint = xStartPoint;
            PointOption touchPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            TouchAction action = new TouchAction(mobileDriver);
            action.press(touchPoint).release().perform();
        }

    }



}
