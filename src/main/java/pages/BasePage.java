package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BasePage {

    private WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void click(By elementLocation){
        driver.findElement(elementLocation).click();
    }

    public int getListSize(By elementLocation){
        List<WebElement> elements = driver.findElements(elementLocation);
        return elements.size();
    }

    public void write(By elementLocation, String text){
        driver.findElement(elementLocation).sendKeys(text);
    }

    public String read(By elementLocation){
        return driver.findElement(elementLocation).getText();
    }

    public void switchToAlert(){
        driver.switchTo().alert();
    }

    public boolean isDisplayed(By elementLocation){
        return driver.findElement(elementLocation).isDisplayed();
    }

    public WebElement element(By elementLocation){
        return driver.findElement(elementLocation);
    }

    public boolean isChecked(By elementLocation){
        return driver.findElement(elementLocation).isSelected();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }
}
