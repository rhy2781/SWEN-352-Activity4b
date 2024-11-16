package edu.rit.swen253.page.hoursAndLocations;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

public class HoursAndLocationsView {

    private final DomElement element;

    public HoursAndLocationsView(DomElement element) {
        this.element = element;
    }

    public String getTitle() {
        return element.findChildBy(By.className("diningTabEateryName")).getText();
    }

    public String getHours() {
        DomElement locationDetails = element
                .findChildBy(By.cssSelector(".diningTabTabGroupFix .col-lg-4.ng-star-inserted"));
        return locationDetails.findChildBy(By.xpath(".//span[@class='greenText']")).getText();
    }

    public String getDescription() {
        DomElement descriptionTab = element.findChildBy(
                By.xpath(".//div[contains(@class, 'mat-tab-label-content')] //*[contains(text(), 'Description')]"));

        SeleniumUtils.getShortWait().until(
                driver -> element.findChildBy(By.cssSelector("div.mat-tab-body-active span")).isEnabled());

        return descriptionTab.findChildBy(By.tagName("span")).getText();
    }
}
