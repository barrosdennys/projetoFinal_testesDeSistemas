package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.YoutubeMainPage;
import util.DriverFactory;


public class Recommendations {
    private final WebDriver driver = DriverFactory.getDriver();
    private final YoutubeMainPage youtubeMainPage = new YoutubeMainPage(driver);

    @And("I access the first video submenu")
    public void accessVideoSubmenu() {

        youtubeMainPage.clickSubMenu();

    }

    @And("I click on Don't recommend channel")
    public void clickDontRecommendChannel() {

        youtubeMainPage.clickDontRecommend();

    }

    @Then("I should see the message {string}")
    public void verifyMessage(String wontMessage) {
        String actualResult = youtubeMainPage.getWontRecommendMsg();
        Assert.assertEquals(wontMessage, actualResult);

    }
}
