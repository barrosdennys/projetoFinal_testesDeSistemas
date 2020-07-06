package steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.YoutubeVideoList;
import util.DriverFactory;

public class VideoListInteractions {
    private final WebDriver driver = DriverFactory.getDriver();
    private final YoutubeVideoList youtubeVideoList = new YoutubeVideoList(driver);

    @Then("I should see the No results found messages")
    public void noResultsFoundMessage() {
        String actualResultTitle = youtubeVideoList.getNoResultsTitleText();
        String expectedResultTitle = "No results found";
        Assert.assertEquals(expectedResultTitle, actualResultTitle);

        String actualResultBody = youtubeVideoList.getNoResultsBodyText();
        String expectedResultBody = "Try different keywords or remove search filters";
        Assert.assertEquals(expectedResultBody, actualResultBody);

    }
}
