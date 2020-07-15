package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.YoutubeChannelsListPage;
import util.DriverFactory;

public class Recommendations {

    private final WebDriver driver = DriverFactory.getDriver();
    private final YoutubeChannelsListPage youtubeChannelsListPage = new YoutubeChannelsListPage(driver);

    @And("I access the video submenu")
    public void i_click_access_the_video_submenu() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("I click on Don't recommend channel")
    public void i_click_on_Don_t_recommend_channel() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I should see the message {string}")
    public void i_should_see_the_message(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
