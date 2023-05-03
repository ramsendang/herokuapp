package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import static org.testng.AssertJUnit.assertTrue;

public class HomePage extends LoadableComponent<HomePage> {
    private WebDriverWait wait;
    private WebDriver driver;
    private BasePage base;
    private Properties prop;
    private FileInputStream io;
    private final String configpath = "configuration/config.properties";
    private final String addRemoveElementUrl = "http://the-internet.herokuapp.com/add_remove_elements/";


    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        base = new BasePage(driver);
    }

    By ContactUsBtn = By.cssSelector(".contactus");
    By addRemoveElementBtn = By.xpath("//a[text()='Add/Remove Elements']");
    @Override
    protected void load() {
        readProperties();
        String base_url = prop.getProperty("baseurl");
        this.driver.get(base_url);
    }

    @Override
    protected void isLoaded() throws Error {
        readProperties();
        String base_url = prop.getProperty("baseurl");
        assertTrue("HomePage is not loaded!", driver.getCurrentUrl().contains(base_url));
    }
    protected Properties readProperties(){
        try{
            prop = new Properties();
            io = new FileInputStream(configpath);
            prop.load(io);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return prop;
    }
    public AddRemoveElementsPage goToAddRemoveElements(){
        base.click(addRemoveElementBtn);
        return new AddRemoveElementsPage(this.driver, this);
    }
}
