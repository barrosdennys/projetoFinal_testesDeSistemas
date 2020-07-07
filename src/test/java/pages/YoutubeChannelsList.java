package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

import java.util.List;

public class YoutubeChannelsList {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public YoutubeChannelsList(WebDriver driver) {
        this.driver = driver;
        wait = DriverFactory.getWait();

    }

    private By subscribeBtn;
    private final By manageBtn = By.cssSelector("#button[aria-label=Manage]");
    private final By subscriptionAddedToast = By.xpath("//span[contains(text(),'Subscription added')]");
    private final By subscriptionRemovedToast = By.xpath("//span[contains(text(),'Subscription removed')]");
    private final By unsubscriptionConfirmation = By.cssSelector("#button[aria-label=Unsubscribe]");

    public void subscribeToChannel(String channelName) {
        subscribeBtn = By.xpath("(//yt-formatted-string[contains(text(),'" + channelName + "')]" +
                "//ancestor::a[@id='main-link'])[1]//following-sibling::div[@id='subscribe-button']" +
                "//paper-button//yt-formatted-string");

        wait.until(ExpectedConditions.visibilityOfElementLocated(subscribeBtn));

        driver.findElement(subscribeBtn).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionAddedToast));
    }

    public String getSubscriptionStatus(String channelName) {
        subscribeBtn = By.xpath("(//yt-formatted-string[contains(text(),'" + channelName + "')]" +
                "//ancestor::a[@id='main-link'])[1]//following-sibling::div[@id='subscribe-button']" +
                "//paper-button//yt-formatted-string");

        return driver.findElement(subscribeBtn).getText();
    }

    public void clickManageBtn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(manageBtn));

        driver.findElement(manageBtn).click();

        driver.navigate().refresh();
    }

    public void unsubscribeToChannel(String channelName) {
        subscribeBtn = By.xpath("(//yt-formatted-string[contains(text(),'" + channelName + "')]" +
                "//ancestor::a[@id='main-link'])[1]//following-sibling::div[@id='subscribe-button']" +
                "//paper-button//yt-formatted-string");

        driver.findElement(subscribeBtn).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(unsubscriptionConfirmation));

        driver.findElement(unsubscriptionConfirmation).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionRemovedToast));
    }

    public List<WebElement> listOfSubscribedChannels() {
        By listOfChannels = By.cssSelector("#main-link #text");

        wait.until(ExpectedConditions.visibilityOfElementLocated(listOfChannels));

        return driver.findElements(listOfChannels);
    }

    public void unsubscribeToAllChannels() {
        YoutubeMainPage youtubeMainPage = new YoutubeMainPage(driver);

        youtubeMainPage.clickLateralMenu("Subscriptions");

        clickManageBtn();

        List<WebElement> listOfSubscribedChannels = listOfSubscribedChannels();

        if (listOfSubscribedChannels != null) {
            for (WebElement channel : listOfSubscribedChannels) unsubscribeToChannel(channel.getText());
        }
    }
}
