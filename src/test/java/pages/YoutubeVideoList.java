package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

public class YoutubeVideoList {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public YoutubeVideoList (WebDriver driver) {
        this.driver = driver;
        wait = DriverFactory.getWait();
    }

    private final By noResultsTitle = By.cssSelector(".promo-message .promo-title");
    private final By noResultsBody = By.cssSelector(".promo-message .promo-title ~ .promo-body-text");

    public String getNoResultsTitleText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsTitle));
        return driver.findElement(noResultsTitle).getText();
    }

    public String getNoResultsBodyText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsBody));
        return driver.findElement(noResultsBody).getText();
    }

}
