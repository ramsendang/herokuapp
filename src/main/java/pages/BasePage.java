package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    private WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void click(By elementLocation){
        driver.findElement(elementLocation).click();
    }

    public void write(By elementLocation, String text){
        driver.findElement(elementLocation).sendKeys(text);
    }

    public String read(By elementLocation){
        return driver.findElement(elementLocation).getText();
    }
}
