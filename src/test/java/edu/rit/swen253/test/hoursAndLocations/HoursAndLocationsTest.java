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
import org.openqa.selenium.WebElement;

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
        homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
        assertNotNull(homePage);
    }

    @Test
    @Order(2)
    public void navigateToHoursAndLocations() {
        homePage.selectHoursAndLocations();
        final SimplePage hoursAndLocationsPage = assertNewPage(SimplePage::new);

        sleep(1);

        assertEquals("https://tigercenter.rit.edu/tigerCenterApp/api/hours-and-locations", hoursAndLocationsPage.getURL());
        LOG.info("Current URL: " + hoursAndLocationsPage.getURL());
    }

    // TODO: Rewrite this test
    @Test
    @Order(3)
    public void testDiningHours() {
        hoursAndLocationsPage = new HoursAndLocationsPage();
        hoursAndLocationsPage.clickSortOpenNow();

        List<HoursAndLocationsView> diningLocations = hoursAndLocationsPage.getOpenDiningLocations();
        for (HoursAndLocationsView location : diningLocations) {
            LOG.info("Location: " + location.getTitle());
        }
    }
}
