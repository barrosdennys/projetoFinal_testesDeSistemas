package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

public class BasePage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions action;

    public BasePage (WebDriver driver){
        this.driver = driver;
        wait = DriverFactory.getWait();
        action = new Actions(driver);
    }

    public void waitAndClick(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public void waitAndSendKeys(By locator, String keys){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(keys);
    }

    public void mouseOverElement(WebElement element){
        action.moveToElement(element).perform();
    }


}
