package testRunners;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrokenImagePage;
import pages.CheckBoxPage;
import pages.HomePage;

public class CheckBoxTest extends BaseTest{
    private HomePage homePage;
    private CheckBoxPage checkBoxPage;


    @BeforeMethod(description = "Method Level Setup!")
    public void methodLevelSetup() {
        //*************PAGE INSTANTIATIONS*************
        homePage = new HomePage(driver);
        checkBoxPage = new CheckBoxPage(driver, homePage);
        //*************PAGE METHODS********************
        //Open N11 HomePage and Go to Login Page
        checkBoxPage.get();
    }

    @Test
    public void checkboxTest(){
        checkBoxPage.verifyIfCheckBoxIsChecked(extent);
    }

    @Test
    public void clickingOnCheckbox(){
        checkBoxPage.clickingOnFirstCheckBox(extent);
    }
}
