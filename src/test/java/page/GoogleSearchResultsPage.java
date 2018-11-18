package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Google Search Results page 1 object class
 */

public class GoogleSearchResultsPage{
    WebDriver driver;

    @FindBy(xpath = "//div[@class='srg']/div[@class='g']")
    private List<WebElement> searchResultList;

    @FindBy(xpath = "//a[@aria-label='Page 2']")
    private WebElement secondPageButton;

    @FindBy(xpath = "//div[@id='navcnt']")
    private WebElement pagination;

    /**
     * Google Searcy Results page 1 class constructor
     * @param driver - Web Driver
     */

    public GoogleSearchResultsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that verifies that Google Search Results page 1 is loaded
     * @return true if page is loaded
     */

    public boolean isPageLoaded(){
       waitUntilElementIsClickable(pagination);
       return driver.getCurrentUrl().contains("https://www.google.com/search")
               && driver.getTitle().contains("Selenium")
               && (searchResultList.size() > 0);
    }

    /**
     * Counting of search results number on first search page
     * @return number of search results on first search page
     */

    public int getSearchResultsNumber() {
        return searchResultList.size();
    }

    /**
     * Method that @return list of text of every search item on first search page
     */

    public List<String> getSearchResultsStrings(){
        List<String> searchResultStringList = new ArrayList<String>();
        for (WebElement searchResult : searchResultList) {
            String searchResultText = searchResult.getText();
            searchResultStringList.add(searchResultText);
        }
        return searchResultStringList;
    }

    /**
     * Method that stops code executing until web element is clickable
     * @param webElement - web element that is being checked to be clickable
     */

    public void waitUntilElementIsClickable(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Transition to the second search page
     * @return Google Search Results second page object
     */

    public GoogleSearchResultsPageSecond goToSecondSearchPage(){
        secondPageButton.click();
        return new GoogleSearchResultsPageSecond(driver);
    }
}
