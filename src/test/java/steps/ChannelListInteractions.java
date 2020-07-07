package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.YoutubeChannelsList;
import util.DriverFactory;
import java.util.ArrayList;
import java.util.List;

public class ChannelListInteractions {

    private final WebDriver driver = DriverFactory.getDriver();
    private final YoutubeChannelsList youtubeChannelsList = new YoutubeChannelsList(driver);

    @When("I subscribe to the channel {string}")
    public void subscribeToChannel(String channelName) {
        youtubeChannelsList.subscribeToChannel(channelName);
    }

    @When("I click on the manage button")
    public void clickManageButton() {
        youtubeChannelsList.clickManageBtn();
    }

    @Then("I should see the channel {string} in the channel list")
    public void verifyChannelInList(String channelName) {
        List<WebElement> channelList = youtubeChannelsList.listOfSubscribedChannels();
        List<String> listItems = new ArrayList<String>();

        for (WebElement listItem : channelList) {
            listItems.add(listItem.getText());
        }

        Assert.assertTrue(listItems.contains(channelName));
    }

    @Then("the subscribe button of the channel {string} has the text {string}")
    public void checkSubscriptionStatus(String channelName, String expectedStatus) {
        String actualSubscriptionStatus = youtubeChannelsList.getSubscriptionStatus(channelName);

        Assert.assertEquals(expectedStatus, actualSubscriptionStatus);
    }
}
