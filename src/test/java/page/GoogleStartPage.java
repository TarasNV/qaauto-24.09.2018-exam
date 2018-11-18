package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.Key;

/**
 * Google Home page object class
 */

public class GoogleStartPage {

    WebDriver driver;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchLine;

    @FindBy(xpath = "//input[@name='btnK']")
    private WebElement searchButton;

    /**
     * Google Start page object class constructor
     * @param driver - Web Driver
     */

    public GoogleStartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that performs search with searchTerm
     * @param searchTerm - search term that is entered into search line
     * @return Google Search Results page 1 page object
     */

    public GoogleSearchResultsPage search(String searchTerm) {
        searchLine.sendKeys(searchTerm);
        //searchButton.click();
        searchLine.sendKeys(Keys.ENTER);
        return new GoogleSearchResultsPage(driver);
    }

    /**
     * Method that verifies that Google Start page is loaded
     * @return true if page is loaded
     */

    public boolean isPageLoaded(){
        return driver.getCurrentUrl().equals("https://www.google.com/")
                && driver.getTitle().equals("Google")
                && searchLine.isDisplayed();
    }
}
