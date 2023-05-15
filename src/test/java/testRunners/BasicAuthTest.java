package testRunners;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasicAuthPage;
import pages.HomePage;

public class BasicAuthTest extends BaseTest{
    private HomePage homePage;
    private BasicAuthPage basicAuthPage;

    @BeforeMethod(description = "Method Level Setup!")
    public void methodLevelSetup() {
        //*************PAGE INSTANTIATIONS*************
        homePage = new HomePage(driver);
        basicAuthPage = new BasicAuthPage(driver, homePage);
        //*************PAGE METHODS********************
        //Open N11 HomePage and Go to Login Page
        basicAuthPage.get();
    }

    @Test
    public void login(){
        basicAuthPage.signin(extent);
    }

}
