package pages;
import cleanTest.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Page Object encapsulates the Sign-in page.
 */
public class BooksPage {
    protected RemoteWebDriver driver;

    private By linkCreated = By.id("created");
    private By labelFullName = By.id("userName-label");

    public String devolverFullName(){
        return driver.findElement(labelFullName).getText();
    }

    public String devolverLinkCreated(){
        return driver.findElement(linkCreated).getText();
    }
    public void navigate(String url){
        driver.get(url);
    }
    public BooksPage(){
        this.driver = Hooks.getDriver();
    }

    public void navigateUrl(String url){
        driver.get(url);
    }
}

