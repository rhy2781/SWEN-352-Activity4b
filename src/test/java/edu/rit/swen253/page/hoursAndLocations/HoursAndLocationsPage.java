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

    public List<HoursAndLocationsView> getDiningLocations() {
        List<DomElement> allLocations = findAllOnPage(By.className(".diningTabVerticalLine .ng-star-inserted"));

        return allLocations.stream()
            .filter(location -> {
                String locationStatus = location.findChildBy(By.className("location-status")).getText();
                return "Open".equalsIgnoreCase(locationStatus.trim());
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
