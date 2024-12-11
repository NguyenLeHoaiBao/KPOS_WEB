package retryTestFailed;

import config.DriverFactory;
import org.openqa.selenium.WebDriverException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestFailed implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test: " + result.getName() + " | Attempt: " + retryCount);

            // Handle Session ID null case
            if (result.getThrowable() instanceof WebDriverException &&
                    result.getThrowable().getMessage().contains("Session ID is null")) {
                System.out.println("Session ID is null. Resetting driver and retrying...");
                resetDriver(result);
            }
            return true;
        }
        return false;
    }

    /**
     * Reset the driver to handle cases where the session ID becomes null.
     */
    private void resetDriver(ITestResult result) {
        try {
            // Example: Assume WebDriver instance is managed via a DriverFactory
            DriverFactory.quitWebDriver();
            DriverFactory.quitMobileDriver();

            if (result.getTestClass().getRealClass().getDeclaredField("webDriver") != null) {
                result.getTestClass().getRealClass()
                        .getDeclaredField("webDriver")
                        .setAccessible(true);
                result.getTestClass().getRealClass()
                        .getDeclaredField("webDriver")
                        .set(result.getInstance(), DriverFactory.getWebDriver());
            }

            if (result.getTestClass().getRealClass().getDeclaredField("mobileDriver") != null) {
                result.getTestClass().getRealClass()
                        .getDeclaredField("mobileDriver")
                        .setAccessible(true);
                result.getTestClass().getRealClass()
                        .getDeclaredField("mobileDriver")
                        .set(result.getInstance(), DriverFactory.getMobileDriver());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to reset driver during retry.");
        }
    }
}
