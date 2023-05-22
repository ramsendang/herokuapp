package testRunners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public ExtentReports extent;
    public ExtentSparkReporter spark;
    @BeforeSuite(description = "Suite Level Setup")
    public void setup(){
        String dateName = new SimpleDateFormat("yyyMMddhhmmss").format(new Date());
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("src/main/resources/reports/"+dateName+"report.html");
        extent.attachReporter(spark);
    }
    @BeforeClass(description = "Class Level Setup!")
    public void classLevelSetup () {
        //Create a Chrome driver. All test classes use this.
        driver = new ChromeDriver();
        //Create a wait. All test classes use this.
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //Maximize Window
        driver.manage().window().maximize();
    }
    @AfterClass (description = "Class Level Teardown!")
    public void classLevelTeardown () {
        driver.quit();
    }
    @AfterSuite
    public void tearDown(){
        extent.flush();
    }
}