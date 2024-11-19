package edu.rit.swen253.page.hoursAndLocations;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class HoursAndLocationsView {

    private final DomElement element;

    public HoursAndLocationsView(DomElement element) {
        this.element = element;
    }

    public DomElement getDomElement() {
        return element;
    }

    public String getTitle() {
        return element.findChildBy(By.xpath(".//div[contains(@class, 'TabEateryName')]")).getText();
    }

    // public String getHours() {
    //     DomElement locationDetails = element
    //             .findChildBy(By.xpath(".//div[contains(@class, 'TabHours')]"));
    //     return locationDetails.findChildBy(By.xpath(".//span[@class='greenText']")).getText();
    // }

    public String getDescription() {
        // Click on the element to expand to see the description
        element.click();
        
        DomElement descriptionTab = element.findChildBy(By.className("mat-tab-body-content"));
        return descriptionTab.getText();
    }
}
