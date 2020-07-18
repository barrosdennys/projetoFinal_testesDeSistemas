package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.BasePage;
import util.DriverFactory;

public class YoutubeMainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final BasePage page;
    private final By signInBtn = By.cssSelector("#masthead[slot=masthead] #end #text");
    private final By searchInput = By.cssSelector("input#search");
    private final By searchBtn = By.cssSelector("button#search-icon-legacy");
    private final By videoSubMenu = By.cssSelector("#dismissable #menu button");
    private final By dontRecommend = By.cssSelector("#items [role=menuitem]:nth-child(5)");
    private final By wontRecommendMsg = By.cssSelector("#content #dismissed #text");

    public YoutubeMainPage(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = DriverFactory.getWait();
    }

    public void login(String email, String password) {
        GoogleAccountPage googleAccountPage = new GoogleAccountPage(driver);
        page.waitAndClick(signInBtn);
        googleAccountPage.fillLoginInfo(email, password);
    }

    public void searchVideo(String videoName) {
        page.waitAndSendKeys(searchInput, videoName);
        page.waitAndClick(searchBtn);
    }

    public void clickLateralMenu(String option) {
        By menuIcon = By.cssSelector("#button[aria-label=Guide]");
        By menuOption = By.xpath("//div[@id='sections']//div[@id='items']" +
                "//a[contains(@title,'" + option + "')]");

        wait.until(ExpectedConditions.presenceOfElementLocated(menuIcon));

        if (!driver.findElement(menuOption).isDisplayed()) {
            page.waitAndClick(menuIcon);
        }

        page.waitAndClick(menuOption);
        driver.navigate().refresh();
    }

    public void clickSubMenu(){
        page.mouseOverElement(By.id("video-title-link"));
        page.waitAndClick(videoSubMenu);
    }

    public void clickDontRecommend(){
        page.waitAndClick(dontRecommend);
    }

    public String getWontRecommendMsg() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(wontRecommendMsg));
        return driver.findElement(wontRecommendMsg).getText();
    }
}
