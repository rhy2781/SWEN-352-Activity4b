package edu.rit.swen253.page.hoursAndLocations;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

public class HoursAndLocationsPage extends AbstractAngularPage {

    private static final Logger LOG = Logger.getLogger(HoursAndLocationsPage.class.getName());

    public HoursAndLocationsPage() {
        super("hours-and-locations");
    }

    public void clickSortOpenNow() {
        findOnPage(By.className("diningTabSortOpenButton")).click();

        SeleniumUtils.getShortWait()
                .until(d -> d.findElement(By.xpath("//img[contains(@src,'sortOpen.svg')]")));
    }

    public List<HoursAndLocationsView> getAllDiningLocations() {
        List<DomElement> allLocations = findAllOnPage(
                By.cssSelector(".diningTabVerticalLine .col-lg-12.ng-star-inserted"));

        return allLocations.stream().map(HoursAndLocationsView::new).toList();
    }

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

    public void clickComputerIcon() {
        findOnPage(By.id("mat-tab-label-0-1")).click();

        SeleniumUtils.getShortWait().until(d -> d.findElement(By.className("mat-tab-label-active")));
    }

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

    public List<HoursAndLocationsView> getAllComputerLabLocations() {
        List<DomElement> allLocations = findAllOnPage(
                By.cssSelector(".labsTabVerticalLine .col-lg-12.ng-star-inserted"));

        return allLocations.stream().map(HoursAndLocationsView::new).toList();
    }

    public void clickStudentAffairsIcon() {
        findOnPage(By.id("mat-tab-label-0-2")).click();

        SeleniumUtils.getShortWait().until(d -> d.findElement(By.className("mat-tab-label-active")));
    }

    public List<HoursAndLocationsView> getAllStudentAffairsLocations() {
        List<DomElement> allLocations = findAllOnPage(
                By.cssSelector(".student-affairsTabVerticalLine .col-lg-12.ng-star-inserted"));

        return allLocations.stream().map(HoursAndLocationsView::new).toList();
    }
}