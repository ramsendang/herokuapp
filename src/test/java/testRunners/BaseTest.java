package testRunners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
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
}