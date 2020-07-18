package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.YoutubeMainPage;
import util.Constants;
import util.DriverFactory;

public class BasicInteractions {
    private final WebDriver driver = DriverFactory.getDriver();
    private final YoutubeMainPage youtubeMainPage = new YoutubeMainPage(driver);

    @Given("I open the youtube main page")
    public void openYoutubeMainPage() {
        driver.get(Constants.YOUTUBE_MAIN_URL);
        driver.manage().window().maximize();
    }

    @Given("I login with email {string} and password {string}")
    public void youtubeLogin(String email, String password) {
        youtubeMainPage.login(email, password);
    }

    @When("I search for a video called {string}")
    public void videoSearch(String videoName) {
        youtubeMainPage.searchVideo(videoName);
    }

    @When("I select the option {string} in the lateral menu")
    public void selectOptionLateralMenu(String option) {
        youtubeMainPage.clickLateralMenu(option);
    }

}
