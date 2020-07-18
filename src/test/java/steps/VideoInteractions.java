package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.*;
import util.DriverFactory;
import java.util.ArrayList;
import java.util.List;

public class VideoInteractions {
    private final WebDriver driver = DriverFactory.getDriver();
    private final YoutubeVideoSearchPage youtubeVideoSearchPage = new YoutubeVideoSearchPage(driver);
    private final YoutubeLikedVideosListPage youtubeLikedVideosListPage = new YoutubeLikedVideosListPage(driver);
    private final YoutubeVideoPage youtubeVideoPage = new YoutubeVideoPage(driver);
    private final YoutubeWatchLaterListPage youtubeWatchLaterListPage = new YoutubeWatchLaterListPage(driver);
    private final YoutubeHistoryListPage youtubeHistoryListPage = new YoutubeHistoryListPage(driver);
    private final YoutubePlaylistPage youtubePlaylistPage = new YoutubePlaylistPage(driver);


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
        youtubeVideoPage.likeVideo();
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

    @When("I select {string} menu option from the video {string}")
    public void selectMenuOptionFromVideoInList(String option, String videoTitle) {
        youtubeVideoSearchPage.selectVideoMenuOption(videoTitle, option);
    }

    @Then("I should see the video title {string} in the miniplayer")
    public void assertVideoInMiniplayer(String videoTitle) {
        String actualResult = youtubeVideoSearchPage.getMiniplayerVideoTitle();
        Assert.assertEquals(videoTitle, actualResult);
    }

    @When("I add video to playlist {string}")
    public void addVideoToWatchLater(String playlistName) {
        youtubeVideoPage.addVideoToPlaylist(playlistName);
    }

    @Then("I should see the video {string} in the Watch later videos list")
    public void verifyWatchLaterList(String videoTitle) {
        List<WebElement> watchLaterList = youtubeWatchLaterListPage.getListOfWatchLater();
        List<String> listItems = new ArrayList<String>();

        for (WebElement listItem : watchLaterList) {
            listItems.add(listItem.getText());
        }
        Assert.assertTrue(listItems.contains(videoTitle));
    }

    @Then("I should see the video {string} in the History list")
    public void verifyHistoryList(String videoTitle) {
        List<WebElement> historyList = youtubeHistoryListPage.getListOfHistory();
        List<String> listItems = new ArrayList<String>();

        for (WebElement listItem : historyList) {
            listItems.add(listItem.getText());
        }
        Assert.assertTrue(listItems.contains(videoTitle));
    }

    @Given("I save the video in a new playlist named {string}")
    public void saveVideoInNewPlaylist(String playlistName) {
        youtubeVideoPage.createNewPlaylist(playlistName);
    }

    @Then("I should see the video {string} in the playlist")
    public void verifyVideoInPlaylist(String videoTitle) {
        List<WebElement> listPlaylist = youtubePlaylistPage.getPlaylistVideos();
        List<String> listItems = new ArrayList<String>();

        for (WebElement listItem : listPlaylist) {
            listItems.add(listItem.getText());
        }

        Assert.assertTrue(listItems.contains(videoTitle));
    }

    @When("I make a comment {string} in the video")
    public void makeCommentVideo(String comment) {
        youtubeVideoPage.makeComments(comment);
    }

    @When("I change the History type to Comments")
    public void selectCommentHistoryType() {
        youtubeHistoryListPage.checkCommentHistory();
    }

    @Then("I should see the comment {string} in the Comments  list")
    public void verifyCommentsHistoryList(String comment) {
        List<WebElement> commentHistoryList = youtubeHistoryListPage.getListOfCommentsHistory();
        List<String> listItems = new ArrayList<String>();

        for (WebElement listItem : commentHistoryList) {
            listItems.add(listItem.getText());
        }

        Assert.assertTrue(listItems.contains(comment));
    }

    @And("I click on Pause watch history")
    public void clickOnPause() {
        youtubeHistoryListPage.clickOnPauseHistory();
    }

    @Then("I should see {string}")
    public void verifyEmptyHistory(String emptyMessage) {
        String actualResult = youtubeHistoryListPage.getEmptyListMessage();
        Assert.assertEquals(emptyMessage, actualResult);
    }

    @And("I should see {string} is displayed")
    public void verifyTurnOnMsg(String turnOnMsg) {
        String actualResult = youtubeHistoryListPage.getTurnOnText();
        Assert.assertEquals(turnOnMsg, actualResult);
    }

    @And("I clear All Watched history videos")
    public void clearAllWatchedHistory() {
        youtubeHistoryListPage.clearAllWatchHistory();
    }
}
