package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

public class GoogleAccount {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public GoogleAccount (WebDriver driver){
        this.driver = driver;
        wait = DriverFactory.getWait();
    }

    private final By emailInput = By.cssSelector("input[type=email]");
    private final By emailPassword = By.cssSelector("input[type=password]");
    private final By nextButton = By.xpath("//span[contains(text(),'Next')]");

    public void fillLoginInfo(String email, String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(nextButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailPassword));
        driver.findElement(emailPassword).sendKeys(password);
        driver.findElement(nextButton).click();
    }
}
