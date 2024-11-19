package edu.rit.swen253.test.hoursAndLocations;

import static edu.rit.swen253.utils.TimingUtils.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.hoursAndLocations.HoursAndLocationsPage;
import edu.rit.swen253.page.hoursAndLocations.HoursAndLocationsView;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HoursAndLocationsTest extends AbstractWebTest {

    private static final Logger LOG = Logger.getLogger(HoursAndLocationsTest.class.getName());
    private TigerCenterHomePage homePage;
    private HoursAndLocationsPage hoursAndLocationsPage;

    @Test
    @Order(1)
    public void navigateToHomePage() {
        LOG.info("Navigating to TigerCenter home page");
        homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
        assertNotNull(homePage);
    }

    @Test
    @Order(2)
    public void navigateToHoursAndLocations() {
        LOG.info("Navigating to Hours and Locations page");
        homePage.selectHoursAndLocations();
        final SimplePage simplePage = assertNewPage(SimplePage::new);

        sleep(1);

        assertEquals("https://tigercenter.rit.edu/tigerCenterApp/api/hours-and-locations", simplePage.getURL());
        LOG.info("Current URL: " + simplePage.getURL());
        hoursAndLocationsPage = new HoursAndLocationsPage();
    }

    @Test
    @Order(3)
    public void testDiningHours() {
        LOG.info("Testing dining hours");
        hoursAndLocationsPage.clickSortOpenNow();

        List<HoursAndLocationsView> allLocations = hoursAndLocationsPage.getAllDiningLocations();
        List<HoursAndLocationsView> diningLocations = hoursAndLocationsPage.getOpenDiningLocations();

        // Ensure that it's not grabbing all locations
        assertNotEquals(allLocations.size(), diningLocations.size());
    }

    @Test
    @Order(4)
    public void testComputerLabsWithPrinters() {
        LOG.info("Testing computer labs with printers");
        hoursAndLocationsPage.clickComputerIcon();

        // Apply filter for labs that have printers
        hoursAndLocationsPage.applyFilterLabs("Printer");
        List<HoursAndLocationsView> allLocations = hoursAndLocationsPage.getAllComputerLabLocations();

        // // There should only be 12 locations with printers
        assertEquals(12, allLocations.size());
    }

    @Test
    @Order(5)
    public void testStudentAffairs() {
        LOG.info("Testing student affairs");
        hoursAndLocationsPage.clickStudentAffairsIcon();

        List<HoursAndLocationsView> allLocations = hoursAndLocationsPage.getAllStudentAffairsLocations();
        assertEquals(8, allLocations.size());

        // Check if title and description matches with first location
        String expectedTitle = "Case Management";
        String expectedDescription = "The Case Management team assists students in navigating health and wellness services both on and off campus.";

        HoursAndLocationsView firstLocation = allLocations.get(0);
        assertEquals(expectedTitle, firstLocation.getTitle());
        assertEquals(expectedDescription, firstLocation.getDescription());
    }
}