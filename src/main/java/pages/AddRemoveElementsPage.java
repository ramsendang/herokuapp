package pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.io.FileInputStream;
import java.util.Properties;

import static org.testng.AssertJUnit.assertTrue;

public class AddRemoveElementsPage extends LoadableComponent<AddRemoveElementsPage>{
    private WebDriver driver;
    private BasePage page;
    private LoadableComponent<HomePage> parent;
    private ExtentTest test1;
    private ExtentTest test2;
    private ExtentReports extent;
    private Properties prop;
    private FileInputStream io;
    private final String configpath = "configuration/config.properties";

    public AddRemoveElementsPage(WebDriver driver, LoadableComponent<HomePage> parent){
        this.driver = driver;
        page = new  BasePage(this.driver);
        this.parent = parent;
    }

    By addElementBtn = By.xpath("//button[text()='Add Element']");
    By deleteButton = By.xpath("//div[@id=\"elements\"]//button[1]");

    By deleteButtons = By.cssSelector("div#elements>button");


    @Override
    protected void load() {
        parent.get().goToAddRemoveElements();
    }

    @Override
    protected void isLoaded() throws Error {
        readProperties();
        String addRemoveElementUrl = prop.getProperty("addRemoveElementUrl");
        assertTrue("add remove element page is not loaded", driver.getCurrentUrl().contains(addRemoveElementUrl));
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
    public void addingElement (ExtentReports extent){
        readProperties();
        test1 = extent.createTest("Adding Element Test");
        test1.info("Clicking on Add Element Button");
        page.click(addElementBtn);
        page.click(addElementBtn);
        test1.info("Verifing the delet button");
        String btnText = page.read(deleteButton);
        if(btnText.equals("Delete")) {
            test1.pass("working as expected: the expected result is 'Delete' and the actual result is " + btnText);
        }else{

            TakesScreenshot ts = (TakesScreenshot) driver;
            String image = ts.getScreenshotAs(OutputType.BASE64);
            test1.fail("Test failed. The acutal result is " + btnText, MediaEntityBuilder.createScreenCaptureFromBase64String(image).build());

        }
    }
    public void removingElement (ExtentReports extent){
        test2 = extent.createTest("Deleting added Elements Test");
        test2.info("verifying the initial number of added items...");
        int initialSize = page.getListSize(deleteButtons);
        test2.info("the number of added item is " +initialSize);
        page.click(deleteButton);
        test2.info("clicking on delete button");
        int finalSize = page.getListSize(deleteButtons);
        test2.info("the number of remaining item is " +finalSize);
        if(finalSize == (initialSize-1)){
            test2.pass("Test passed.");
        }else {
            TakesScreenshot ts = (TakesScreenshot) driver;
            String image = ts.getScreenshotAs(OutputType.BASE64);
            test1.fail("Test failed." , MediaEntityBuilder.createScreenCaptureFromBase64String(image).build());
        }

    }
}
