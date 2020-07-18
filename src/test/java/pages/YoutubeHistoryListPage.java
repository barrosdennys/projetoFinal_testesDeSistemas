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
    private final By commentRadioButton = By.cssSelector("#options > ytd-sub-feed-option-renderer:nth-child(3) > a > paper-radio-button");
    private final By commentName = By.cssSelector("#contents #contents");
    private final By commentSettings = By.cssSelector("#menu #button yt-icon");
    private final By deleteCommentButton = By.cssSelector("paper-listbox  ytd-menu-navigation-item-renderer:nth-child(2)");
    private final By confirmDeleteCommentButton = By.cssSelector(".buttons #confirm-button");

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

    public List<WebElement> getListOfCommentsHistory() {
        By listOfCommentsHistory = By.cssSelector(".ytd-comment-history-entry-renderer #content > yt-formatted-string");
        this.waitCommentHistoryListUpdate(listOfCommentsHistory, 3);

        return driver.findElements(listOfCommentsHistory);
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

    public void waitCommentHistoryListUpdate(By listOfComment, int refreshTime){
        int retry = 0;

        while (!page.isElementPresent(listOfComment) && retry < refreshTime){
            driver.navigate().refresh();
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(listOfComment));
            } catch (TimeoutException e) {
                System.out.println("Comment list is empty. Trying again.");
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

    public void clearCommentsHistory() {
        youtubeMainPage.clickLateralMenu("History");
        page.waitAndClick(commentRadioButton);
        By listOfCommentsHistory = By.cssSelector(".ytd-comment-history-entry-renderer #content > yt-formatted-string");
        this.waitCommentHistoryListUpdate(listOfCommentsHistory, 3);
        page.mouseOverElement(commentName);
        page.waitAndClick(commentSettings);
        page.waitAndClick(deleteCommentButton);
        page.waitAndClick(confirmDeleteCommentButton);
    }

    public void checkCommentHistory (){
        page.waitAndClick(commentRadioButton);
    }
}