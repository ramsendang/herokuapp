package testRunners;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrokenImagePage;
import pages.HomePage;

public class BrokenImageTest extends BaseTest{
    private HomePage homePage;
    private BrokenImagePage brokenImageTest;


    @BeforeMethod(description = "Method Level Setup!")
    public void methodLevelSetup() {
        //*************PAGE INSTANTIATIONS*************
        homePage = new HomePage(driver);
        brokenImageTest = new BrokenImagePage(driver, homePage);
        //*************PAGE METHODS********************
        //Open N11 HomePage and Go to Login Page
        brokenImageTest.get();
    }

    @Test
    public void BrokenImageTest(){
        brokenImageTest.brokenImageVerification(extent);
    }
}
