package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.YoutubeLikedVideosListPage;
import pages.YoutubeVideoSearchPage;
import util.DriverFactory;
import java.util.ArrayList;
import java.util.List;

public class VideoListInteractions {
    private final WebDriver driver = DriverFactory.getDriver();
    private final YoutubeVideoSearchPage youtubeVideoSearchPage = new YoutubeVideoSearchPage(driver);
    private final YoutubeLikedVideosListPage youtubeLikedVideosListPage = new YoutubeLikedVideosListPage(driver);

    @Then("I should see the No results found messages")
    public void noResultsFoundMessage() {
        String actualResultTitle = youtubeVideoSearchPage.getNoResultsTitleText();
        String expectedResultTitle = "No results found";
        Assert.assertEquals(expectedResultTitle, actualResultTitle);

        String actualResultBody = youtubeVideoSearchPage.getNoResultsBodyText();
        String expectedResultBody = "Try different keywords or remove search filters";
        Assert.assertEquals(expectedResultBody, actualResultBody);
    }

    @When("I like the video")
    public void likeVideo() {
        youtubeVideoSearchPage.likeVideo();
    }

    @When("I click on the video called {string}")
    public void selectVideoToWatch(String videoTitle) {
        youtubeVideoSearchPage.clickOnVideoTitle(videoTitle);
    }

    @Then("I should see the video {string} in the Liked videos list")
    public void verifyLikedVideoList(String videoTitle) {
        List<WebElement> likedVideosList = youtubeLikedVideosListPage.getListOfLikedVideos();
        List<String> listItems = new ArrayList<String>();

        for (WebElement listItem : likedVideosList) {
            listItems.add(listItem.getText());
        }

        Assert.assertTrue(listItems.contains(videoTitle));
    }
}
