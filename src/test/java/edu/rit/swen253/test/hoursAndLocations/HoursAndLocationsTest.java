package edu.rit.swen253.test.hoursAndLocations;

import static edu.rit.swen253.utils.TimingUtils.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HoursAndLocationsTest extends AbstractWebTest {
    private TigerCenterHomePage homePage;

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
    }
}
