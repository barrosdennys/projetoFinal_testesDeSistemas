package util;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            Capabilities capability = ((RemoteWebDriver) driver).getCapabilities();
            if (capability.getPlatform().is(Platform.WINDOWS)) {
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            }
            else {
                System.setProperty("webdriver.chrome.driver", "chromedriver");
            }
        }
        return driver;
    }

    public static WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver, 10);
        }
        return wait;
    }


    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            wait = null;
            driver = null;

        }
    }
}
