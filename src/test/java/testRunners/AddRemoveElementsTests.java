package testRunners;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddRemoveElementsPage;
import pages.HomePage;

public class AddRemoveElementsTests extends BaseTest{
    private HomePage homePage;
    private AddRemoveElementsPage addRemoveElementsPage;


    @BeforeMethod(description = "Method Level Setup!")
    public void methodLevelSetup() {
        //*************PAGE INSTANTIATIONS*************
        homePage = new HomePage(driver);
        addRemoveElementsPage = new AddRemoveElementsPage(driver, homePage);
        //*************PAGE METHODS********************
        //Open N11 HomePage and Go to Login Page
        addRemoveElementsPage.get();
    }

    @Test(priority = 1, description="Invalid Login Scenario with wrong username and password.")
    public void addElementTest () {
        addRemoveElementsPage.addingElement(extent);
    }
    @Test(priority = 2, description = "removing element test")
    public void removeElementTest(){
        addRemoveElementsPage.removingElement(extent);
    }
}
