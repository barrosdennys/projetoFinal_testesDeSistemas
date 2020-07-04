package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage (WebDriver driver){
        this.driver = driver;
        this.wait = DriverFactory.getWait();
    }

    public void test(){
        System.out.println("imprime qualquer coisa");
    }
}
