package pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.AssertJUnit.assertTrue;

public class AddRemoveElementsPage extends LoadableComponent<AddRemoveElementsPage>{
    private WebDriver driver;
    private BasePage page;
    private LoadableComponent<HomePage> parent;
    private ExtentTest test;
    private ExtentReports extent;

    private final String addRemoveElementUrl = "http://the-internet.herokuapp.com/add_remove_elements/";

    public AddRemoveElementsPage(WebDriver driver, LoadableComponent<HomePage> parent){
        this.driver = driver;
        page = new  BasePage(this.driver);
        this.parent = parent;
    }

    By addElementBtn = By.xpath("//button[text()='Add Element']");
    By deleteButton = By.xpath("//div[@id=\"elements\"]//button[1]");

    @Override
    protected void load() {
        parent.get().goToAddRemoveElements();
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue("add remove element page is not loaded", driver.getCurrentUrl().contains(addRemoveElementUrl));
    }

    public void addingElement (ExtentReports extent){
        test = extent.createTest("Adding Element Test");
        test.info("Clicking on Add Element Button");
        page.click(addElementBtn);
        test.info("Verifing the delet button");
        String btnText = page.read(deleteButton);
        if(btnText.equals("Deletes")) {
            test.pass("working as expected: the expected result is 'Delete' and the actual result is " + btnText);
        }else{
            String dateName = new SimpleDateFormat("yyyMMddhhmmss").format(new Date());
            TakesScreenshot ts = (TakesScreenshot) driver;
            String image = ts.getScreenshotAs(OutputType.BASE64);
            test.fail("Test failed. The acutal result is " + btnText, MediaEntityBuilder.createScreenCaptureFromBase64String(image).build());

        }
        page.click(deleteButton);
        test.info("clicking on the delete button");

    }
}
