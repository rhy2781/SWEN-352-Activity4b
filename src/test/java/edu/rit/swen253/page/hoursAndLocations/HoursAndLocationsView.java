package edu.rit.swen253.page.hoursAndLocations;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

/**
 * View object for a location on the Hours and Locations page.
 * 
 * @author Austyn Wright
 */
public class HoursAndLocationsView {

    private final DomElement element;

    public HoursAndLocationsView(DomElement element) {
        this.element = element;
    }

    /**
     * @return the DomElement representing the location.
     */
    public DomElement getDomElement() {
        return element;
    }

    /**
     * @return the title of the location.
     */
    public String getTitle() {
        return element.findChildBy(By.xpath(".//div[contains(@class, 'TabEateryName')]")).getText();
    }

    /**
     * @return the hours of the location.
     */
    public String getHours() {
        DomElement locationDetails = element
                .findChildBy(By.xpath(".//div[contains(@class, 'TabHours')]"));
        return locationDetails.findChildBy(By.xpath(".//span[@class='greenText']")).getText();
    }

    /**
     * @return the description of the location.
     */
    public String getDescription() {
        // Click on the element to expand to see the description
        element.click();

        DomElement descriptionTab = element.findChildBy(By.className("mat-tab-body-content"));
        return descriptionTab.getText();
    }
}
