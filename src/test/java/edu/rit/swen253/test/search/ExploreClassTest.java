package edu.rit.swen253.test.search;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.search.SearchPage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.DomElement;

/**
 * UI test for Tiger Center's Search page
 * @author <a href='mailto:tsf2802@rit.edu'>Takumi Fukuzawa</a>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExploreClassTest extends AbstractWebTest{
    private TigerCenterHomePage homePage;
    private SearchPage searchPage;
    private static final Logger LOGGER = Logger.getLogger(SearchPage.class.getName());

    //Initial class Exporation Test 1
    
    @BeforeEach
    @DisplayName("First, navigate to the Tiger Center Search Page")
    void navigateToHomePage(){
            homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
            assertNotNull(homePage);
            //resets to homepage
            homePage.selectClassSearch();
            searchPage = assertNewPage(SearchPage::new);
            assertNotNull(searchPage, "Search Page should not be null after navigation");
            //ensures that search page is accessed
    }


    private static String classname = "SWEN 261";
    
    @Test
    @DisplayName("Class avalibility and search")
    void selectCourse(){
        LOGGER.info("Running Class avalibility test and class name:");
        searchPage.insertCourseName(classname);
        searchPage.selectFirstTerm();
        searchPage.clickSearchButton();
        String resultsText = searchPage.getCourseFirstName(); 
        assertNotNull(resultsText, "Search results should not be null");
        LOGGER.info(resultsText);
        assertEquals(true, resultsText.contains("Introduction to Software Engineering"));
        String openstatus = searchPage.getFirstCourseStatus();
        LOGGER.info(openstatus);
        assertEquals(true, openstatus.contains("Open"));
    }
}

