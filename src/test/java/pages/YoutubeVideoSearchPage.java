package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.BasePage;
import util.DriverFactory;

public class YoutubeVideoSearchPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final BasePage page;
    private final By noResultsTitle = By.cssSelector(".promo-message .promo-title");
    private final By noResultsBody = By.cssSelector(".promo-message .promo-title ~ .promo-body-text");
    private final By miniplayerVideoTitle = By.cssSelector("div.ytd-miniplayer[role=dialog]" +
            " div#info-bar yt-formatted-string[title]");
    private final By skipButton = By.xpath("//button[contains(@class,'skip-button')]");

    public YoutubeVideoSearchPage(WebDriver driver) {
        this.driver = driver;
        wait = DriverFactory.getWait();
        page = new BasePage(driver);
    }

    public String getNoResultsTitleText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsTitle));
        return driver.findElement(noResultsTitle).getText();
    }

    public String getNoResultsBodyText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsBody));
        return driver.findElement(noResultsBody).getText();
    }

    public void clickOnVideoTitle(String videoName) {
        By videoTitle = By.cssSelector("a#video-title[title='" + videoName + "']");
        page.waitAndClick(videoTitle);
        waitForSkipButtonToAppear(1);
    }

    public void waitForSkipButtonToAppear(int retryTimes) {
        int retry = 0;
        while (retry < retryTimes) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(skipButton));
                if (page.isElementPresent(skipButton)) {
                    page.waitAndClick(skipButton);
                    break;
                }
            } catch (TimeoutException e) {
                System.out.println("Skip button not displayed.");
            }
            retry++;
        }
    }

    public void selectVideoMenuOption(String videoName, String option) {
        By videoSettings = By.xpath("//ytd-video-renderer//a[@id='video-title']" +
                "[contains(@title,'" + videoName + "')]//ancestor::h3//following-sibling::div");
        By videoSettingsOption = By.xpath("//ytd-menu-popup-renderer//yt-formatted-string[contains" +
                "(text(),'" + option + "')]");

        page.mouseOverElement(videoSettings);
        page.waitAndClick(videoSettings);
        page.waitAndClick(videoSettingsOption);
    }

    public String getMiniplayerVideoTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(miniplayerVideoTitle));
        return driver.findElement(miniplayerVideoTitle).getText();
    }
}
