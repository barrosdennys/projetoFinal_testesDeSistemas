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

public class YoutubeLikedVideosListPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final YoutubeMainPage youtubeMainPage;
    private final BasePage page;

    public YoutubeLikedVideosListPage(WebDriver driver) {
        this.driver = driver;
        wait = DriverFactory.getWait();
        youtubeMainPage = new YoutubeMainPage(driver);
        page = new BasePage(driver);
    }

    public List<WebElement> getListOfLikedVideos() {
        By listOfLikedVideos = By.cssSelector("span#video-title");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(listOfLikedVideos));
        } catch (TimeoutException e) {
            System.out.println("The Liked videos list is empty!");
        }
        return driver.findElements(listOfLikedVideos);
    }

    public void selectLikedVideoMenuOption(String video, String option) {
        By videoMenu = By.xpath("//span[@title='" + video + "']//ancestor::" +
                "a//following-sibling::div[@id='menu']//button[@aria-label='Action menu']");

        By menuOption = By.xpath("//yt-formatted-string[@class='style-scope" +
                " ytd-menu-service-item-renderer'][contains(text(),'" + option + "')]");

        page.waitAndClick(videoMenu);
        page.waitAndClick(menuOption);
    }

    public void dislikeAllVideos() {
        youtubeMainPage.clickLateralMenu("Liked videos");
        List<WebElement> listOfLikedVideos = getListOfLikedVideos();

        if (listOfLikedVideos != null) {
            for (WebElement video : listOfLikedVideos) {
                page.mouseOverElement(video);
                selectLikedVideoMenuOption(video.getText(), "Remove from Liked videos");
            }
        }
    }
}
