package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

public class YoutubeMainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public YoutubeMainPage(WebDriver driver) {
        this.driver = driver;
        wait = DriverFactory.getWait();
    }

    private final By signInBtn = By.cssSelector("#masthead[slot=masthead] #end #text");
    private final By avatarImg = By.cssSelector("img[alt='Avatar image']");
    private final By searchInput = By.cssSelector("input#search");
    private final By searchBtn = By.cssSelector("button#search-icon-legacy");

    public void login(String email, String password) {
        GoogleAccount googleAccount = new GoogleAccount(driver);

        driver.findElement(signInBtn).click();

        googleAccount.fillLoginInfo(email, password);

        wait.until(ExpectedConditions.visibilityOfElementLocated(avatarImg));
    }

    public void searchVideo(String videoName) {
        driver.findElement(searchInput).sendKeys(videoName);

        driver.findElement(searchBtn).click();
    }

    public void clickLateralMenu(String option) {
        By menuOption = By.xpath("//div[@id='sections']//div[@id='items']//a[contains(@title,'" + option + "')]");
        driver.findElement(menuOption).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(avatarImg));
    }

    public void clickProfileMenu(String option) {
        By menuOption = By.xpath("//iron-dropdown//paper-item//" +
                "yt-formatted-string[@id='label'][contains(text(),'" + option + "')]");

        driver.findElement(avatarImg).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(menuOption));

        driver.findElement(menuOption).click();

    }

}
