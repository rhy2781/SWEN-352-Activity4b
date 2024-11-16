package edu.rit.swen253.page.hoursAndLocations;

import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

public class HoursAndLocationsPage extends AbstractAngularPage {

    public HoursAndLocationsPage() {
        super("hours-and-locations");
    }

    public void clickSortOpenNow() {
        findOnPage(By.className("diningTabSortOpenButton")).click();

        SeleniumUtils.getShortWait().until(driver -> driver.findElements(By.className("location-status")).stream()
                .allMatch(element -> element.getText().contains("Open")));
    }

    public List<HoursAndLocationsView> getAllDiningLocations() {
        List<DomElement> allLocations = findAllOnPage(
                By.cssSelector(".diningTabVerticalLine .col-lg-12.ng-star-inserted"));
        return allLocations.stream().map(HoursAndLocationsView::new).toList();
    }

    public List<HoursAndLocationsView> getOpenDiningLocations() {
        List<DomElement> allLocations = findAllOnPage(
                By.cssSelector(".diningTabVerticalLine .col-lg-12.ng-star-inserted"));

        // Filter locations that have the "diningTabOpenIcon" icon
        return allLocations.stream()
                .filter(location -> {
                    DomElement openIcon = location.findChildBy(By.cssSelector("img.diningTabOpenIcon"));
                    return openIcon.getAttribute("src").contains("open");
                })
                .map(HoursAndLocationsView::new)
                .toList();
    }

    public void clickComputerIcon() {

    }

    public void applyFilter(By filter) {

    }

    public List<HoursAndLocationsView> getFilteredComputerLab() {
        return null;
    }
}
