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

/**
 * UI test for Tiger Center's GPA Calculator page
 * @author <a href='mailto:rhy2781@rit.edu'>Robert Yamasaki</a>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OneCourseGPATest extends AbstractWebTest{

    private TigerCenterHomePage homePage;
    private CalculatorPage calculatorPage;

    @Test
    @Order(1)
    @DisplayName("First, navigate to the Tiger Center Calculator Page")
    void navigateToHomePage(){
            homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
            assertNotNull(homePage);
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
    private static String c1GradeOption = "A";
    private static String c1GradeValue = "1: 4.00";

    @Test
    @Order(3)
    @DisplayName("Test w/ one course")
    /** Insert one course and verify that the GPA calculator is correct */
    void addCourseDetails(){
        List<Course> courses = calculatorPage.getCourseList();
        assertEquals(1, courses.size());
        Course first = courses.get(0);

        first.insertCourseName(c1Name);
        assertEquals(c1Name, first.getCourseName());
        
        first.insertCourseCredits(c1Credits);
        assertEquals(c1Credits, first.getCourseCredits());
        
        first.insertCourseGrade(c1GradeOption);
        assertEquals(c1GradeValue, first.getCourseGrade());

        calculatorPage.calculateGPA();
        assertEquals("4.00", calculatorPage.getTermGPA());
        assertEquals("4.00", calculatorPage.getCumulativeGPA());
        
        // prepare for next test by resetting courses
        calculatorPage.resetPage();
        courses = calculatorPage.getCourseList();
        assertEquals(1, courses.size());
    }
}
