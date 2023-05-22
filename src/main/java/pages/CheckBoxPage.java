package pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.io.FileInputStream;
import java.util.Properties;

import static org.testng.AssertJUnit.assertTrue;

public class CheckBoxPage extends LoadableComponent<CheckBoxPage> {
    protected WebDriver driver;
    protected LoadableComponent<HomePage> parent;
    protected BasePage basePage;
    protected ExtentTest checkedTest;
    protected ExtentTest clickingOnCheckBox;
    protected Properties prop;
    protected FileInputStream io;
    protected String configpath ="configuration/config.properties";
    public CheckBoxPage(WebDriver driver, LoadableComponent<HomePage> parent){
        this.driver = driver;
        this.parent = parent;
        basePage = new BasePage(driver);
    }
    By firstCheckBox = By.cssSelector("#checkboxes>input:nth-child(1)");
    By secondCheckBox = By.xpath("//input[@type='checkbox'][2]");

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

    @Override
    protected void load() {
        parent.get().goToCheckBoxPage();
    }

    @Override
    protected void isLoaded() throws Error {
        readProperties();
        String url = prop.getProperty("checkboxUrl");
        assertTrue("add remove element page is not loaded", driver.getCurrentUrl().contains(url));
    }

    public void verifyIfCheckBoxIsChecked(ExtentReports extent){
        checkedTest = extent.createTest("Verify if the check box is checked");
        boolean firstcheckedresult = basePage.isChecked(firstCheckBox);
        boolean secondCheckedResult = basePage.isChecked(secondCheckBox);
        if(firstcheckedresult == true){
            checkedTest.info("the first checkbox is checked");
        }else {
            checkedTest.info("the first checkbox is not checked");
        }
        if(secondCheckedResult == true){
            checkedTest.info("the second checkbox is checked");
        }else{
            checkedTest.info("the second checkbox is not checked");
        }
    }

    public void clickingOnFirstCheckBox(ExtentReports extent){
        clickingOnCheckBox = extent.createTest("Clicking on First CheckBox");
        basePage.click(firstCheckBox);
        clickingOnCheckBox.info("Clicking on the first check box");
        boolean clickStatus = basePage.isChecked(firstCheckBox);
        clickingOnCheckBox.info("verifing the status of hte first action");
        if (clickStatus==true){
            clickingOnCheckBox.pass("the checkbox is clicked successfully");
        }else{
            clickingOnCheckBox.info("the checkbox is not clicked");
            TakesScreenshot ts1 = (TakesScreenshot) driver;
            String image1 = ts1.getScreenshotAs(OutputType.BASE64);
            clickingOnCheckBox.fail("Test Failled", MediaEntityBuilder.createScreenCaptureFromBase64String(image1).build());
        }
    }



}
