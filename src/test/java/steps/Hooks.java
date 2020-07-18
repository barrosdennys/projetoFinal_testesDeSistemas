package steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.YoutubeChannelsListPage;
import pages.YoutubeHistoryListPage;
import pages.YoutubeLikedVideosListPage;
import pages.YoutubePlaylistPage;
import pages.YoutubeWatchLaterListPage;
import util.Constants;
import util.DriverFactory;

public class Hooks {

    private static WebDriver driver;
    private static YoutubeLikedVideosListPage youtubeLikedVideosListPage;
    private static YoutubeWatchLaterListPage youtubeWatchLaterListPage;
    private static YoutubeHistoryListPage youtubeHistoryListPage;
    private static YoutubePlaylistPage youtubePlaylistPage;

    @Before
    public static void setUp() {
        //TO-DO

    }

    @After(order = 1, value = "@channels")
    public static void unsubscribeToChannels() {
        driver = DriverFactory.getDriver();
        YoutubeChannelsListPage youtubeChannelsListPage = new YoutubeChannelsListPage(driver);

        driver.get(Constants.YOUTUBE_MAIN_URL);
        driver.manage().window().maximize();

        youtubeChannelsListPage.unsubscribeToAllChannels();
    }

    @After(order = 0)
    public static void quitDriver() {
        driver = DriverFactory.getDriver();
        youtubeLikedVideosListPage = new YoutubeLikedVideosListPage(driver);

        DriverFactory.quitDriver();

    }

    @After(order = 2, value = "@videos")
    public static void dislikeAllVideosFromLikedList() {
        driver = DriverFactory.getDriver();
        youtubeLikedVideosListPage = new YoutubeLikedVideosListPage(driver);

        driver.get(Constants.YOUTUBE_MAIN_URL);
        driver.manage().window().maximize();

        youtubeLikedVideosListPage.dislikeAllVideos();
    }

    @After(order = 3, value = "@videos")
    public static void removeAllWatchLater() {
        driver = DriverFactory.getDriver();
        youtubeWatchLaterListPage = new YoutubeWatchLaterListPage(driver);

        driver.get(Constants.YOUTUBE_MAIN_URL);
        driver.manage().window().maximize();

        youtubeWatchLaterListPage.removeAllWatchLater();
    }

    @After(order = 4, value = "@videos")
    public static void clearAllWatchHistory() {
       driver = DriverFactory.getDriver();
        youtubeHistoryListPage = new YoutubeHistoryListPage(driver);

        driver.get(Constants.YOUTUBE_MAIN_URL);
        driver.manage().window().maximize();

        youtubeHistoryListPage.clearAllWatchHistory();
    }

    @After (order =5, value ="@playlist")
    public static void removePlaylist(){
        driver = DriverFactory.getDriver();
        youtubePlaylistPage = new YoutubePlaylistPage(driver);

        driver.get(Constants.YOUTUBE_MAIN_URL);
        driver.manage().window().maximize();
        youtubePlaylistPage.deletePlaylist("playlist - test");
    }

    @After(order = 6, value = "@playlist")
    public static void clearCommentHistory() {
        driver = DriverFactory.getDriver();
        youtubeHistoryListPage = new YoutubeHistoryListPage(driver);

        driver.get(Constants.YOUTUBE_MAIN_URL);
        driver.manage().window().maximize();

        youtubeHistoryListPage.clearCommentsHistory();
    }
}
