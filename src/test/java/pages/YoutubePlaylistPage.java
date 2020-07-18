package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.BasePage;
import util.DriverFactory;

import java.util.List;

public class YoutubePlaylistPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final BasePage page;
    private final YoutubeMainPage youtubeMainPage;
    private final By playlistSettingsButton = By.cssSelector("#items button#button[aria-label='Action menu']");
    private final By deletePlaylistButton = By.cssSelector("paper-listbox ytd-menu-service-item-renderer:last-child");
    private final By confirmDeleteButton = By.cssSelector(".buttons #confirm-button");

    public YoutubePlaylistPage (WebDriver driver){
        this.driver = driver;
        wait = DriverFactory.getWait();
        page = new BasePage(driver);
        youtubeMainPage = new YoutubeMainPage(driver);
    }

    public List<WebElement> getPlaylistVideos() {
        By listOfPlaylistVideos = By.cssSelector("span#video-title");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(listOfPlaylistVideos));
        } catch (TimeoutException e) {
            System.out.println("The Liked videos list is empty!");
        }
        return driver.findElements(listOfPlaylistVideos);
    }

    public void deletePlaylist(String playlistName){
        youtubeMainPage.clickLateralMenu(playlistName);
        page.waitAndClick(playlistSettingsButton);
        page.waitAndClick(deletePlaylistButton);
        page.waitAndClick(confirmDeleteButton);
    }
}
