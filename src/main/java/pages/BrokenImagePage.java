package pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.io.FileInputStream;
import java.util.Properties;

import static org.testng.AssertJUnit.assertTrue;

public class BrokenImagePage extends LoadableComponent<BrokenImagePage> {
    protected WebDriver driver;
    protected LoadableComponent<HomePage> parent;
    protected BasePage basePage;
    protected ExtentTest imageTest;
    protected Properties prop;
    protected FileInputStream io;
    protected String configpath ="configuration/config.properties";
    public BrokenImagePage(WebDriver driver, LoadableComponent<HomePage> parent){
        this.driver = driver;
        this.parent = parent;
        basePage = new BasePage(driver);
    }
    By firstImage = By.xpath("//div[@class=\"example\"]//img[1]");
    By secondImage = By.xpath("//div[@class=\"example\"]//img[2]");
    By thirdImg = By.xpath("//div[@class=\"example\"]//img[3]");

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
        parent.get().goToBrokenImage();
    }

    @Override
    protected void isLoaded() throws Error {
        readProperties();
        String url = prop.getProperty("brokenImgUrl");
        assertTrue("add remove element page is not loaded", driver.getCurrentUrl().contains(url));
    }

    public void brokenImageVerification(ExtentReports extent){
        imageTest = extent.createTest("Broken Image Test");
        imageTest.info("Checking the first image");
        WebElement firstImagelocator = basePage.element(firstImage);
        int firstWidth = Integer.parseInt(firstImagelocator.getAttribute("naturalWidth"));
        if(firstWidth == 0){
            imageTest.info("The image is not displayed");
            TakesScreenshot ts1 = (TakesScreenshot) driver;
            String image1 = ts1.getScreenshotAs(OutputType.BASE64);
            imageTest.fail("Test Failled", MediaEntityBuilder.createScreenCaptureFromBase64String(image1).build());
        }else{
            imageTest.info("Image is vissible");
            imageTest.pass("Test Passed");
        }
        imageTest.info("Checking the second image");
        WebElement secondImagelocator = basePage.element(firstImage);
        int secondWidth = Integer.parseInt(secondImagelocator.getAttribute("naturalWidth"));
        if(secondWidth == 0){
            imageTest.info("The image is not displayed");
            TakesScreenshot ts2 = (TakesScreenshot) driver;
            String image2 = ts2.getScreenshotAs(OutputType.BASE64);
            imageTest.fail("Test Failled", MediaEntityBuilder.createScreenCaptureFromBase64String(image2).build());

        }else{
            imageTest.info("Image is vissible");
            imageTest.pass("Test Passed");
        }
        imageTest.info("Checking the third image");
        WebElement thirdImagelocator = basePage.element(firstImage);
        int thirdWidth = Integer.parseInt(thirdImagelocator.getAttribute("naturalWidth"));
        if(thirdWidth == 0){
            imageTest.info("The image is not displayed");
            TakesScreenshot t3 = (TakesScreenshot) driver;
            String image3 = t3.getScreenshotAs(OutputType.BASE64);
            imageTest.fail("Test Failled", MediaEntityBuilder.createScreenCaptureFromBase64String(image3).build());
        }else{
            imageTest.info("Image is vissible");
            imageTest.pass("Test Passed");
        }
    }

}
