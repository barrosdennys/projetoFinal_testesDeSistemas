package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

public class YoutubeMainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final BasePage page;
    private final By signInBtn = By.cssSelector("#masthead[slot=masthead] #end #text");
    private final By avatarImg = By.cssSelector("img[alt='Avatar image']");
    private final By searchInput = By.cssSelector("input#search");
    private final By searchBtn = By.cssSelector("button#search-icon-legacy");

    public YoutubeMainPage(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = DriverFactory.getWait();
    }

    public void login(String email, String password) {
        GoogleAccountPage googleAccountPage = new GoogleAccountPage(driver);

        page.waitAndClick(signInBtn);

        googleAccountPage.fillLoginInfo(email, password);

        wait.until(ExpectedConditions.visibilityOfElementLocated(avatarImg));
    }

    public void searchVideo(String videoName) {
        page.waitAndSendKeys(searchInput, videoName);
        page.waitAndClick(searchBtn);

    }

    /**
     * Method to select an option from the lateral menu
     * located in the left of the screen
     *
     * @param option: Name of the channel
     * @author Dennys Barros
     */
    public void clickLateralMenu(String option) {
        By menuIcon = By.cssSelector("#button[aria-label=Guide]");
        By menuOption = By.xpath("//div[@id='sections']//div[@id='items']" +
                "//a[contains(@title,'" + option + "')]");

        if (!driver.findElement(menuOption).isDisplayed()) {
            page.waitAndClick(menuIcon);
        }

        page.waitAndClick(menuOption);
        driver.navigate().refresh();
    }


    /**
     * Method to select an option from the profile menu
     * located in the top right of the screen
     *
     * @param option: Name of the channel
     * @author Dennys Barros
     */
    public void clickProfileMenu(String option) {
        By menuOption = By.xpath("//iron-dropdown//paper-item//" +
                "yt-formatted-string[@id='label'][contains(text(),'" + option + "')]");

        page.waitAndClick(avatarImg);
        page.waitAndClick(menuOption);

    }

}
