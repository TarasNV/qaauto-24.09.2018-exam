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
 * Google Search Results page two object class
 */

public class GoogleSearchResultsPageSecond{
    WebDriver driver;

    @FindBy(xpath = "//div[@class='srg']/div[@class='g']")
    private List<WebElement> searchResultList;

    @FindBy(xpath = "//div[@id='navcnt']")
    private WebElement pagination;

    /**
     * Google Searcy Results page 2 class constructor
     * @param driver - Web Driver
     */

    public GoogleSearchResultsPageSecond(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method that verifies that Google Search Results page 2 is loaded
     * @return true if page is loaded
     */

    public boolean isPageLoaded(){
        waitUntilElementIsClickable(pagination);
        return driver.getCurrentUrl().contains("https://www.google.com/search")
                && driver.getTitle().contains("Selenium")
                && (searchResultList.size() > 0);
    }

    /**
     * Counting of search results number on second search page
     * @return number of search results on second search page
     */

    public int getSearchResultsNumberPageTwo() {
        return searchResultList.size();
    }

    /**
     * Method that @return list of text of every search item on second search page
     */

    public List<String> getSearchResultsStringsPageTwo(){
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
}
