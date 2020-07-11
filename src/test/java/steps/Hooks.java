package steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.YoutubeChannelsListPage;
import pages.YoutubeLikedVideosListPage;
import pages.YoutubeWatchLaterListPage;
import util.Constants;
import util.DriverFactory;

public class Hooks {

    private static WebDriver driver;
    private static YoutubeLikedVideosListPage youtubeLikedVideosListPage;
    private static YoutubeWatchLaterListPage YoutubeWatchLaterListPage;

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
        YoutubeWatchLaterListPage = new YoutubeWatchLaterListPage(driver);

        driver.get(Constants.YOUTUBE_MAIN_URL);
        driver.manage().window().maximize();

        YoutubeWatchLaterListPage.removeAllWatchLater();
    }
}
