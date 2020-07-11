package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

import java.util.List;

public class YoutubeChannelsListPage {
    private final WebDriverWait wait;
    private final WebDriver driver;
    private final BasePage page;
    private final By manageBtn = By.cssSelector("#button[aria-label=Manage]");
    private final By subscriptionAddedToast = By.xpath("//span[contains(text(),'Subscription added')]");
    private final By subscriptionRemovedToast = By.xpath("//span[contains(text(),'Subscription removed')]");
    private final By unsubscriptionConfirmation = By.cssSelector("#button[aria-label=Unsubscribe]");

    public YoutubeChannelsListPage(WebDriver driver) {
        this.driver = driver;
        wait = DriverFactory.getWait();
        page = new BasePage(driver);
    }

    /**
     * Method to get the WebElement and reuse in other methods
     * to avoid declaring the subscribeBtn repeatedly
     *
     * @param channelName: Name of the channel
     * @return the WebElement of the subscribe button
     * @author Dennys Barros
     */
    public WebElement getSubscribeBtnElement(String channelName) {
        By subscribeBtn = By.xpath("(//yt-formatted-string[contains(text(),'" + channelName + "')]" +
                "//ancestor::a[@id='main-link'])[1]//following-sibling::div[@id='subscribe-button']" +
                "//paper-button//yt-formatted-string");

        wait.until(ExpectedConditions.visibilityOfElementLocated(subscribeBtn));
        return driver.findElement(subscribeBtn);
    }

    public void subscribeToChannel(String channelName) {
        getSubscribeBtnElement(channelName).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionAddedToast));
    }

    /**
     * @param channelName: Name of the channel
     * @return the String with the value SUBSCRIBED or UNSUBSCRIBED
     * @author Dennys Barros
     */
    public String getSubscriptionStatus(String channelName) {
        return getSubscribeBtnElement(channelName).getText();
    }

    public void clickManageBtn() {
        page.waitAndClick(manageBtn);
        driver.navigate().refresh();
    }

    public void unsubscribeToChannel(String channelName) {
        getSubscribeBtnElement(channelName).click();
        page.waitAndClick(unsubscriptionConfirmation);
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
