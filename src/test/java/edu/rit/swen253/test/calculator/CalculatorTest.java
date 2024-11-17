package edu.rit.swen253.test.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.calculator.CalculatorPage;
import edu.rit.swen253.page.calculator.Course;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.BrowserWindow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest extends AbstractWebTest{

    private TigerCenterHomePage homePage;
    private BrowserWindow<TigerCenterHomePage> homeWindow;

    private BrowserWindow<CalculatorPage> window;
    private CalculatorPage calculatorPage;

    @Test
    @Order(1)
    @DisplayName("First, navigate to the Tiger Center Calculator Page")
    void navigateToHomePage(){
            homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
            assertNotNull(homePage);
            homeWindow = getCurrentWindow();
    }

    @Test
    @Order(2)
    @DisplayName("Click on the GPA calculator")
    void navigateToCalculator(){
        homePage.selectGPACalculator();
        calculatorPage = assertNewPage(CalculatorPage::new);
    }


    private static String c1Name = "SWEN 352";
    private static String c1Credits = "3";
    private static String c1Grade = "A";
    private static String expected = "1: 4.00";


    @Test
    @Order(3)
    void addCourseDetails(){
        List<Course> courses = calculatorPage.getCourseList();
        Course first = courses.get(0);
        first.insertCourseName("SWEN 352");
        assertEquals("SWEN 352", first.getCourseName());

        first.insertCourseGrade(c1Grade);
        assertEquals(expected, first.getCourseGrade());

        calculatorPage.calculateGPA();
        assertEquals("4.00", calculatorPage.getTermGPA());
        assertEquals("4.00", calculatorPage.getCumulativeGPA());
    }
}
