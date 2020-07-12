package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

import java.util.List;

public class YoutubeHistoryListPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final YoutubeMainPage youtubeMainPage;
    private final BasePage page;

    public YoutubeHistoryListPage(WebDriver driver) {
        this.driver = driver;
        wait = DriverFactory.getWait();
        youtubeMainPage = new YoutubeMainPage(driver);
        page = new BasePage(driver);
    }

    public List<WebElement> getListOfHistory() {
        By listOfHistory = By.cssSelector("#video-title > yt-formatted-string");

        this.waitHistoryListUpdate(listOfHistory, 3);
        return driver.findElements(listOfHistory);
    }

    public void waitHistoryListUpdate(By listOfHistory, int refreshTime){
        int retry = 0;

        while (!page.isElementPresent(listOfHistory) && retry < refreshTime){
            driver.navigate().refresh();
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(listOfHistory));
            } catch (TimeoutException e) {
                System.out.println("History list is empty. Trying again.");
            }
            retry++;
        }
    }

    public void clearAllWatchHistory() {
        By clearHistoryButton = By.xpath("//paper-button[contains(@aria-label,'Clear all watch history')]");
        By clearHistoryConfirmButton = By.xpath("//yt-formatted-string[contains(text(),'CLEAR WATCH HISTORY')]");
        By clearedHistoryToast = By.xpath("//span[contains(text(),'Watch history cleared')]");

        youtubeMainPage.clickLateralMenu("History");

        if (getListOfHistory().size() != 0) {
            page.waitAndClick(clearHistoryButton);
            page.waitAndClick(clearHistoryConfirmButton);
            wait.until(ExpectedConditions.visibilityOfElementLocated(clearedHistoryToast));
        }
    }
}
