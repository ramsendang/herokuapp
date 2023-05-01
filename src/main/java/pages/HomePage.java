package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class HomePage extends LoadableComponent<HomePage> {
    private WebDriverWait wait;
    private WebDriver driver;
    private BasePage base;

    private String base_url = "http://the-internet.herokuapp.com/";

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        base = new BasePage(driver);
    }

    By ContactUsBtn = By.cssSelector(".contactus");
    By addRemoveElementBtn = By.xpath("//a[text()='Add/Remove Elements']");
    @Override
    protected void load() {
        this.driver.get(base_url);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue("HomePage is not loaded!", driver.getCurrentUrl().contains(base_url));
    }

    public AddRemoveElementsPage goToAddRemoveElements(){
        base.click(addRemoveElementBtn);
        return new AddRemoveElementsPage(this.driver, this);
    }
}
