package pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.io.FileInputStream;
import java.util.Properties;

import static org.testng.AssertJUnit.assertTrue;

public class BasicAuthPage extends LoadableComponent<BasicAuthPage> {
    private WebDriver driver;
    private BasePage page;
    private LoadableComponent<HomePage> parent;
    private Properties prop;
    private FileInputStream io;
    private final String configpath = "configuration/config.properties";

    public BasicAuthPage(WebDriver driver, LoadableComponent<HomePage> parent){
        this.driver = driver;
        page = new BasePage(this.driver);
        this.parent = parent;
    }
    @Override
    protected void load() {
        parent.get().goToBasicAuth();
    }

    @Override
    protected void isLoaded() throws Error {
        readProperties();
        String basicAuthUrl = prop.getProperty("basicAuthUrl");
        assertTrue("add remove element page is not loaded", driver.getCurrentUrl().contains(basicAuthUrl));
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

    public void signin(ExtentReports extent){
        ExtentTest signintest1 = extent.createTest("Signin Test");
        String username="ram";
        String password="ram";
        String url= "the-internet.herokuapp.com";
        driver.navigate().to("https://"+username+":"+password+"@"+url);
        signintest1.info("passing username and password therough url");
        String currentUrl = driver.getCurrentUrl();
        if(currentUrl.contains(url)){
            signintest1.pass("Test Passed");
        }else {
            signintest1.fail("Test failed");
        }
    }



}
