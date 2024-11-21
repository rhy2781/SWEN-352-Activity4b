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

/**
 * Test class for the Hours and Locations page.
 * 
 * @author Austyn Wright
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HoursAndLocationsTest extends AbstractWebTest {

    private static final Logger LOG = Logger.getLogger(HoursAndLocationsTest.class.getName());
    private TigerCenterHomePage homePage;
    private HoursAndLocationsPage hoursAndLocationsPage;

    /**
     * Navigates to the TigerCenter home page.
     */
    @Test
    @Order(1)
    public void navigateToHomePage() {
        LOG.info("Navigating to TigerCenter home page");
        homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
        assertNotNull(homePage);
    }

    /**
     * Navigates to the Hours and Locations page.
     */
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
    public void testDiningLocations() {
        LOG.info("Testing dining location");
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
        hoursAndLocationsPage.clickSection("Computer Labs");

        // Apply filter for labs that have printers
        hoursAndLocationsPage.applyFilterLabs("Printer");
        List<HoursAndLocationsView> allLocations = hoursAndLocationsPage.getAllComputerLabLocations();

        // There should only be 12 locations with printers
        assertEquals(12, allLocations.size());

        sleep(1);
    }

    @Test
    @Order(5)
    public void testStudentAffairs() {
        LOG.info("Testing student affairs");
        hoursAndLocationsPage.clickSection("Student Affairs");

        String expectedTitle = "Red Barn Climbing Gym";
        String expectedDescription = "RBC is a bouldering-only gym, where climbs are shorter and falls are protected with padding. Climbing routes are set and maintained to accommodate all abilities and skill levels, from beginner to expert.All non-RIT visitors must be 18 years of age or older.The Red Barn is a 100 year old \"Wells\" style dairy barn that lacks many modern amenities such as heat and plumbing. Please dress based on the outside temperature. There is a \"Port-A-John\" located in the main parking lot that is available to all visitors.RBC is open when RIT semester classes are in session and closed during exam weeks and breaks.";

        HoursAndLocationsView result = hoursAndLocationsPage.getStudentAffair(expectedTitle);
        assertEquals(expectedTitle, result.getTitle());
        assertEquals(expectedDescription, result.getDescription());
    }
}