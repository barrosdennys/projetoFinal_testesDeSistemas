package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.BasePage;
import util.DriverFactory;

public class YoutubeVideoPage {
    private final WebDriverWait wait;
    private final BasePage page;
    private final By videoToast = By.cssSelector("paper-toast#toast");
    private final By likeButton = By.cssSelector("#info #menu #top-level-buttons " +
            "ytd-toggle-button-renderer:nth-child(1)");
    private final By saveButton = By.cssSelector("#info #menu #top-level-buttons " +
            "ytd-button-renderer:nth-child(4)");
    private final By saveToCloseButton = By.cssSelector("#header #close-button");
    private final By newPlaylistButton = By.cssSelector("a#endpoint paper-item yt-formatted-string#label");
    private final By namePlaylistInput = By.cssSelector("#name-input paper-input#input input");
    private final By createPlaylistButton = By.cssSelector("#actions paper-button#button");
    private final By commentInputTwo = By.cssSelector("#comment-dialog #creation-box #contenteditable-root");
    private final By commentInputOne = By.cssSelector("#simple-box #placeholder-area");
    private final By commentButton = By.cssSelector("#buttons #submit-button");

    public YoutubeVideoPage(WebDriver driver) {
        wait = DriverFactory.getWait();
        page = new BasePage(driver);
    }

    public void likeVideo() {
        page.waitAndClick(likeButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(videoToast));
    }

    public void addVideoToPlaylist(String playlistName) {
        By playlist = By.xpath("//div[contains(@id,'checkbox')]//yt-formatted-string[text()='" + playlistName + "']");
        page.waitAndClick(saveButton);
        page.waitAndClick(playlist);
        wait.until(ExpectedConditions.visibilityOfElementLocated(videoToast));
        page.waitAndClick(saveToCloseButton);
    }

    public void createNewPlaylist(String playlistName) {
        page.waitAndClick(saveButton);
        By addedToPlaylistToast = By.xpath("//span[contains(text(),'" + playlistName + "')]");
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
