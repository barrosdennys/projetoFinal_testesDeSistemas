package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

public class YoutubeVideoSearchPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final BasePage page;
    private final By noResultsTitle = By.cssSelector(".promo-message .promo-title");
    private final By noResultsBody = By.cssSelector(".promo-message .promo-title ~ .promo-body-text");
    private final By videoOptions = By.cssSelector("#info #menu #top-level-buttons ~ #button");
    private final By addedToLikedVideosToast = By.xpath("//span[contains(text(),'Added to Liked videos')]");
    private final By likeButton = By.cssSelector("#info #menu #top-level-buttons " +
            "ytd-toggle-button-renderer:nth-child(1)");
    private final By dislikeButton = By.cssSelector("#info #menu #top-level-buttons " +
            "ytd-toggle-button-renderer:nth-child(2)");
    private final By shareButton = By.cssSelector("#info #menu #top-level-buttons " +
            "ytd-button-renderer:nth-child(3)");
    private final By saveButton = By.cssSelector("#info #menu #top-level-buttons " +
            "ytd-button-renderer:nth-child(4)");


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
        By videoTitle = By.cssSelector("a#video-title[title='"+videoName+"']");
        page.waitAndClick(videoTitle);

    }

    public void likeVideo(){
        page.waitAndClick(likeButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addedToLikedVideosToast));
    }

    public void dislikeVideo(){
        page.waitAndClick(dislikeButton);
    }
}
