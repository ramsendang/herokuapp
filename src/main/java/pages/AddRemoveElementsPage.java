package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.AssertJUnit.assertTrue;

public class AddRemoveElementsPage extends LoadableComponent<AddRemoveElementsPage>{
    private WebDriver driver;
    private BasePage page;
    private LoadableComponent<HomePage> parent;

    private final String addRemoveElementUrl = "http://the-internet.herokuapp.com/add_remove_elements/";

    public AddRemoveElementsPage(WebDriver driver, LoadableComponent<HomePage> parent){
        this.driver = driver;
        page = new  BasePage(this.driver);
        this.parent = parent;
    }

    @Override
    protected void load() {
        parent.get().goToAddRemoveElements();
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue("add remove element page is not loaded", driver.getCurrentUrl().contains(addRemoveElementUrl));
    }

    public void firstTest (){
        System.out.println("first Test");
    }
}
