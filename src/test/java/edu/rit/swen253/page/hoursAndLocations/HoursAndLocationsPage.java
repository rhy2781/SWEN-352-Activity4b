package edu.rit.swen253.page.hoursAndLocations;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

/**
 * Page object for the Hours and Locations page.
 * 
 * @author Austyn Wright
 */
public class HoursAndLocationsPage extends AbstractAngularPage {

    public HoursAndLocationsPage() {
        super("hours-and-locations");
    }

    /**
     * Clicks the 'Sort Open Now' button to sort the locations by those that are
     * open.
     */
    public void clickSortOpenNow() {
        findOnPage(By.className("diningTabSortOpenButton")).click();

        SeleniumUtils.getShortWait()
                .until(d -> d.findElement(By.xpath("//img[contains(@src,'sortOpen.svg')]")));
    }

    /**
     * Gets all dining locations on the page.
     * 
     * @return a list of HoursAndLocationsView objects representing each dining
     *         location.
     */
    public List<HoursAndLocationsView> getAllDiningLocations() {
        List<DomElement> allLocations = findAllOnPage(
                By.cssSelector(".diningTabVerticalLine .col-lg-12.ng-star-inserted"));

        return allLocations.stream().map(HoursAndLocationsView::new).toList();
    }

    /**
     * Gets all dining locations that are currently open.
     * 
     * @return a list of HoursAndLocationsView objects representing each open dining
     *         location.
     */
    public List<HoursAndLocationsView> getOpenDiningLocations() {
        List<HoursAndLocationsView> allLocations = getAllDiningLocations();

        // Filter locations that have the 'Open' image
        return allLocations.stream()
                .filter(location -> {
                    DomElement openIcon = location.getDomElement()
                            .findChildBy(By.cssSelector("div.diningTabHrFix img"));
                    return openIcon.getAttribute("src").contains("open");
                })
                .toList();
    }

    /**
     * Clicks on a section of the page based on the section given.
     * 
     * @param section the section to click on (Dining Locations, Computer Labs, or
     *                Student Affairs).
     */
    public void clickSection(String section) {
        if (section.equals("Computer Labs"))
            findOnPage(By.id("mat-tab-label-0-1")).click();
        else if (section.equals("Student Affairs"))
            findOnPage(By.id("mat-tab-label-0-2")).click();
        else
            findOnPage(By.id("mat-tab-label-0-0")).click(); // Dining Locations

        SeleniumUtils.getShortWait().until(d -> d.findElement(By.className("mat-tab-label-active")));
    }

    /**
     * Applies a filter to the computer labs.
     * 
     * @param filter the filter to apply.
     */
    public void applyFilterLabs(String filter) {
        DomElement filterSection = findOnPage(By.xpath("//div[.//span[text()='Filters:']]"));

        List<DomElement> filterCheckBox = filterSection.findChildrenBy(By.className("labsTabCheckboxes"));

        DomElement filterInput = filterCheckBox.stream()
                .filter(e -> e.getWebElement().isDisplayed())
                .filter(e -> e.getWebElement().getText().contains(filter))
                .collect(Collectors.toList()).get(0);

        SeleniumUtils.getLongWait()
                .until(ExpectedConditions.elementToBeClickable(
                        filterInput.findChildBy(By.className("mat-checkbox-inner-container")).getWebElement()))
                .click();

    }

    /**
     * Gets all computer lab locations on the page.
     * 
     * @return a list of HoursAndLocationsView objects representing each computer
     *         lab.
     */
    public List<HoursAndLocationsView> getAllComputerLabLocations() {
        // Wait for the computer labs section to load before getting locations
        SeleniumUtils.getShortWait()
                .until(ExpectedConditions.presenceOfElementLocated(By.className("labsTabVerticalLine")));

        List<DomElement> allLocations = findAllOnPage(
                By.cssSelector(".labsTabVerticalLine .col-lg-12.ng-star-inserted"));

        return allLocations.stream().map(HoursAndLocationsView::new).toList();
    }

    /**
     * Gets all student affairs locations on the page.
     * 
     * @return a list of HoursAndLocationsView objects representing each student
     *         affairs location.
     */
    public List<HoursAndLocationsView> getAllStudentAffairsLocations() {
        // Wait for the student affairs section to load before getting locations
        SeleniumUtils.getShortWait()
                .until(ExpectedConditions.presenceOfElementLocated(By.className("student-affairsTabVerticalLine")));

        List<DomElement> allLocations = findAllOnPage(
                By.cssSelector(".student-affairsTabVerticalLine .col-lg-12.ng-star-inserted"));

        return allLocations.stream().map(HoursAndLocationsView::new).toList();
    }
}