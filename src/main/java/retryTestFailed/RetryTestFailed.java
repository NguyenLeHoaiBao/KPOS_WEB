package retryTestFailed;

import config.DriverFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestFailed implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            System.out.println("Retrying test: " + result.getName() + " | Attempt: " + (retryCount + 1));

            // Check for specific exceptions
            Throwable throwable = result.getThrowable();
            if (throwable != null && throwable.getMessage() != null && throwable.getMessage().contains("Session ID is null")) {
                System.out.println("Detected Session ID is null. Restarting driver...");
                restartDrivers(); // Restart WebDriver or MobileDriver
            }

            retryCount++;
            return true;
        }
        return false;
    }

    /**
     * Restart WebDriver and MobileDriver
     */
    private void restartDrivers() {
        try {
            // Quit existing WebDriver if active
            if (DriverFactory.getWebDriver() != null) {
                DriverFactory.quitWebDriver();
                System.out.println("WebDriver session closed.");
            }

            // Quit existing MobileDriver if active
            if (DriverFactory.getMobileDriver() != null) {
                DriverFactory.quitMobileDriver();
                System.out.println("MobileDriver session closed.");
            }

            // Reinitialize drivers
            DriverFactory.getWebDriver(); // Reinitialize WebDriver
            DriverFactory.getMobileDriver(); // Reinitialize MobileDriver
            System.out.println("Drivers restarted successfully.");
        } catch (Exception e) {
            System.err.println("Error while restarting drivers: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
