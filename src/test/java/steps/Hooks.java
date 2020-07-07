package steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import pages.YoutubeChannelsList;
import pages.YoutubeMainPage;
import util.Constants;
import util.DriverFactory;

public class Hooks {

    private static final WebDriver driver = DriverFactory.getDriver();
    private static final YoutubeChannelsList youtubeChannelsList = new YoutubeChannelsList(driver);
    private static final YoutubeMainPage mainPage = new YoutubeMainPage(driver);

    @Before
    public static void setUp() {
        //TO-DO

    }

    @After(order = 10, value = "@channels")
    public static void unsubscribeToChannels() {
        driver.get(Constants.YOUTUBE_MAIN_URL);
        driver.manage().window().maximize();

        youtubeChannelsList.unsubscribeToAllChannels();
    }

    @After(order = 1)
    public static void quitDriver(){
        DriverFactory.quitDriver();
    }
}
