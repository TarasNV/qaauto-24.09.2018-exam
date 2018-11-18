package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.GoogleSearchResultsPage;
import page.GoogleSearchResultsPageSecond;
import page.GoogleStartPage;
import org.testng.Assert;

import java.util.List;


public class GoogleSearchTest {
    /**
     * Pre-conditions:
     * - Open Firefox browser
     * - Navigate to google.com in web browser
     *
     * Scenario:
     * - Verify that GoogleStartPage is loaded
     * - Enter "Selenium" into search field
     * - Click on "Search" button
     * - Verify that first GoogleSearchResultsPage is loaded
     * - Assert number of Search results is 10 on page 1
     * - Assert that each result in a list contains searchTerm
     * - Click on "2' link to switch to next page
     * - Verify that second GoogleSearchResultsPage is loaded
     * - Assert number of Search results is 10 on page 2
     * - Assert that each result in a list contains searchTerm
     *
     * Post-conditions:
     *  - Close Firefox browser
     */

    private WebDriver driver;
    public String searchTerm = "Selenium";
    GoogleStartPage googleStartPage;
    int searchPagesNumber;

    @BeforeMethod
    public void beforeMethod(){
        System.setProperty("webdriver.gecko.driver", "C:\\Windows\\System32\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.google.com/");
        googleStartPage = new GoogleStartPage(driver);
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    @Test
    public void SearchResultsNumberOnFirstTwoPagesTest(){
        Assert.assertTrue(googleStartPage.isPageLoaded(), "Google Start page is not loaded.");


        GoogleSearchResultsPage googleSearchResultsPageFirst = googleStartPage.search(searchTerm);
        Assert.assertTrue(googleSearchResultsPageFirst.isPageLoaded(), "Google Search Results first page is not loaded.");

        Assert.assertEquals(googleSearchResultsPageFirst.getSearchResultsNumber(), 10, "Search results number on first page is not 10.");

        List<String> searchResultListOnFirstPage = googleSearchResultsPageFirst.getSearchResultsStrings();

        for (String searchResult : searchResultListOnFirstPage) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "Search Term " + searchTerm + " not found in " + searchResult);
        }

        GoogleSearchResultsPageSecond googleSearchResultsPageSecond = googleSearchResultsPageFirst.goToSecondSearchPage();
        Assert.assertTrue(googleSearchResultsPageSecond.isPageLoaded(), "Google Search Results second page is not loaded.");

        Assert.assertEquals(googleSearchResultsPageSecond.getSearchResultsNumberPageTwo(), 10, "Search results number on second page is not 10.");

        List<String> searchResultListOnSecondPage = googleSearchResultsPageSecond.getSearchResultsStringsPageTwo();

        for (String searchResult : searchResultListOnSecondPage) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "Search Term " + searchTerm + " not found in " + searchResult);
        }

    }

}
