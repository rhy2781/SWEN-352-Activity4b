package edu.rit.swen253.page.search;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

public class SearchPage extends AbstractAngularPage {
    private static final Logger LOGGER = Logger.getLogger(SearchPage.class.getName());
    public SearchPage() {
        super("class-search");
    }

    /**
     * Insert the course name into the search field.
     * @param courseName - The name of the course to search for.
     */
    public void insertCourseName(String courseName) {
        // Locate the input field directly by its class name
        DomElement inputField = angularView.findChildBy(By.className("completer-input"));
        // Ensure the input field is found
        if (inputField == null) {
            throw new RuntimeException("Search input field not found");
        }
        // Enter the course name into the search field
        inputField.enterText(courseName);
    }

    /**
     * Get the first course name to validate result
     */
    public String getCourseFirstName() {
        WebDriver driver = SeleniumUtils.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // Locate the element with the class "classSearchResultsDisplayName"
        List<DomElement> results = angularView.findChildrenBy(By.className("classSearchResultsDisplayName"));
        // Return the text content of the located element
        return results.get(0).getText();
    }

    /**
     * Click the search button to execute the search.
     */
    public void clickSearchButton() {
        WebElement searchButton = angularView.findChildBy(By.className("classSearchSearchButton")).getWebElement();
        searchButton.click();
    }

    /**
     * clicks course catalog and displays overlay
     */
    public void clickCourseCatalog() {
        WebElement searchButton = angularView.findChildBy(By.className("classSearchCourseCatalogText")).getWebElement();
        searchButton.click();
        WebDriver driver = SeleniumUtils.getDriver(); // Ensure WebDriver is initialized
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait
        WebElement gccisElement = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//mat-expansion-panel-header//mat-panel-title//span[text()='GCCIS']")
        ));
        wait.until(ExpectedConditions.elementToBeClickable(gccisElement)).click();  
        try {
            WebElement overlay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cdk-overlay-pane")));
            System.out.println("Overlay is visible.");
        } catch (TimeoutException e) {
            System.out.println("Overlay did not load within timeout.");
        }
    }

    /**
     * Navigates different fields to find algorithms class
     * @return String value of credit count
     */
    public String clickCSCI() {
        LOGGER.info(" CSCI method called: ");
        // Obtain the WebDriver instance
        WebDriver driver = SeleniumUtils.getDriver(); 
    
        LOGGER.info(" GCCIS Clicked! ");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement CSCIwait= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-expansion-panel-header//mat-panel-title//span[text()='CSCI']")));
        wait.until(ExpectedConditions.elementToBeClickable(CSCIwait)).click();
        
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//span[contains(text(),'Analysis of Algorithms')]/ancestor::div[@class='col-xs-12 courseCatalogDivHover']")));
        element.click();

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//button//span[text()='View Sections']")
        ));

        button.click();
        WebElement descriptionElement = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.className("courseCatalogExpandedDetailsBodyText")
        ));
        String courseDescription = descriptionElement.getText();
        return courseDescription;

    }

    /**
     * Clicks Dropdown button
     */
    public void clickFilterDropButton() {
    // Locate the span element using the CSS selector
        WebElement searchButton = angularView.findChildBy(By.cssSelector("span.col-xs-12.classSearchFilterResultsText")).getWebElement();
        searchButton.click();
    }

    /**
     * Returns a list of result information which also includes the instructor name
     */
    public List<DomElement> getFirstInstructor() {
        WebDriver driver = SeleniumUtils.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        List<DomElement> results = angularView.findChildrenBy(By.className("classSearchBasicResultsText"));
        if (results.size() > 0) {
            LOGGER.info("Names");
            for (DomElement option : results) {
                LOGGER.info(" Name: " + option.getText());
            }
        } 
        return results;
        
    }

    /**
     * Gets information about the status open/wait of the first class
     */
    public String getFirstCourseStatus() {
        WebDriver driver = SeleniumUtils.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        List<DomElement> results = angularView.findChildrenBy(By.className("classSearchResultsStatusText"));
        return results.get(0).getText();
    }

    /**
     * Inserts the instructor name in field
     * @param instructor - The name of the instructor to enter in search
     */
    public void insertInstructorName(String instructor){
        List<DomElement> input = angularView.findChildrenBy(By.className("mat-input-element"));
        DomElement insert = input.get(0);
        insert.clear();
        insert.enterText(instructor);
    }
    
    /**
     * Selects the first term within options
     */
    public void selectFirstTerm() {
        DomElement input = angularView.findChildBy(By.className("bigTermPickerWidthFix"));
        WebElement termDropdown = input.getWebElement();
        // Find all the options within the dropdown
        List<WebElement> options = termDropdown.findElements(By.tagName("option"));
        // Verify that there are multiple options
        if (options.size() > 1) {
            LOGGER.info("Multiple options");
            for (WebElement option : options) {
                LOGGER.info("- Option: " + option.getText());
            }
        } else {
            LOGGER.info("Dropdown does not contain multiple options or is empty.");
        }
        Select s = new Select(input.getWebElement());
        s.selectByIndex(1); // Index 0 is usually "Select a term" which is disabled
        s.selectByVisibleText("2024-25 Fall (2241)");
    }
}
