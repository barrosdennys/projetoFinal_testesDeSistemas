package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

public class YoutubeVideoPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final BasePage page;
    private final By addedToLikedVideosToast = By.xpath("//span[contains(text(),'Added to Liked videos')]");
    private final By likeButton = By.cssSelector("#info #menu #top-level-buttons " +
            "ytd-toggle-button-renderer:nth-child(1)");
    private final By dislikeButton = By.cssSelector("#info #menu #top-level-buttons " +
            "ytd-toggle-button-renderer:nth-child(2)");
    private final By saveButton = By.cssSelector("#info #menu #top-level-buttons " +
            "ytd-button-renderer:nth-child(4)");
    private final By saveToCloseButton = By.cssSelector("#header #close-button");
    private final By savedToWatchLaterToast = By.xpath("//span[contains(text(),'Saved to Watch later')]");
    private final By newPlaylistButton = By.cssSelector("a#endpoint paper-item yt-formatted-string#label");
    private final By namePlaylistInput = By.cssSelector("#name-input paper-input#input input");
    private final By createPlaylistButton = By.cssSelector("#actions paper-button#button");
    private final By commentInputTwo = By.cssSelector("#comment-dialog #creation-box #contenteditable-root");
    private final By commentInputOne = By.cssSelector("#simple-box #placeholder-area");
    private final By commentButton = By.cssSelector("#buttons #submit-button");

    public YoutubeVideoPage(WebDriver driver) {
        this.driver = driver;
        wait = DriverFactory.getWait();
        page = new BasePage(driver);
    }

    public void likeVideo() {
        page.waitAndClick(likeButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addedToLikedVideosToast));
    }

    public void addVideoToPlaylist(String playlistName) {
        By playlist = By.xpath("//div[contains(@id,'checkbox')]//yt-formatted-string[text()='" + playlistName + "']");
        page.waitAndClick(saveButton);
        page.waitAndClick(playlist);
        wait.until(ExpectedConditions.visibilityOfElementLocated(savedToWatchLaterToast));
        page.waitAndClick(saveToCloseButton);
    }

    public void createNewPlaylist(String playlistName) {
        page.waitAndClick(saveButton);
        By addedToPlaylistToast = By.xpath("//span[contains(text(),'Added to " + playlistName + "')]");
        page.waitAndClick(newPlaylistButton);
        page.waitAndSendKeys(namePlaylistInput, playlistName);
        page.waitAndClick(createPlaylistButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addedToPlaylistToast));
    }

    public void makeComments(String comment) {
        page.scrollDown();
        page.waitAndClick(commentInputOne);
        page.waitAndSendKeys(commentInputTwo, comment);
        page.waitAndClick(commentButton);
    }
}
