package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleAccountPage {
    private final BasePage base;
    private final By emailInput = By.cssSelector("input[type=email]");
    private final By emailPassword = By.cssSelector("input[type=password]");
    private final By nextButton = By.xpath("//div[@role='button'][*='Next'] | //button[*='Next']");

    public GoogleAccountPage(WebDriver driver){
        base = new BasePage(driver);
    }

    public void fillLoginInfo(String email, String password){
        base.waitAndSendKeys(emailInput, email);
        base.waitAndClick(nextButton);
        base.waitAndSendKeys(emailPassword, password);
        base.waitAndClick(nextButton);

    }
}
