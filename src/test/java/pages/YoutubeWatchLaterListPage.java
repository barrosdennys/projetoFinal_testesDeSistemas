package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

import java.util.List;

public class YoutubeWatchLaterListPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final YoutubeMainPage youtubeMainPage;
    private final BasePage page;

    public YoutubeWatchLaterListPage(WebDriver driver) {
        this.driver = driver;
        wait = DriverFactory.getWait();
        youtubeMainPage = new YoutubeMainPage(driver);
        page = new BasePage(driver);
    }

    public List<WebElement> getListOfWatchLater() {
        By listOfWatchLater = By.cssSelector("span#video-title");

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(listOfWatchLater));
        } catch (TimeoutException e) {
            System.out.println("The Watch later list is empty!");
        }
        return driver.findElements(listOfWatchLater);
    }

    public void selectWatchLaterMenuOption(String video, String option) {
        By videoMenu = By.xpath("//span[@title='" + video + "']//ancestor::" +
                "a//following-sibling::div[@id='menu']//button[@aria-label='Action menu']");

        By menuOption = By.xpath("//yt-formatted-string[@class='style-scope" +
                " ytd-menu-service-item-renderer']//span[contains(text(),'" + option + "')]");

        page.waitAndClick(videoMenu);
        page.waitAndClick(menuOption);
    }

    public void removeAllWatchLater() {
        youtubeMainPage.clickLateralMenu("Watch later");

        List<WebElement> listOfWatchLater = getListOfWatchLater();

        if (listOfWatchLater != null) {
            for (WebElement video : listOfWatchLater) {
                page.mouseOverElement(video);
                selectWatchLaterMenuOption(video.getText(), "Remove");
            }
        }
    }
}
