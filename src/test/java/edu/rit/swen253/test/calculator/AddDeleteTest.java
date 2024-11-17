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
public class AddDeleteTest extends AbstractWebTest{

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

    private static String c2Name = "CSCI 261";
    private static String c2Credits = "3";
    private static String c2GradeOption = "D";
    private static String c2GradeValue = "10: 1.00";

    private static String c3Name = "BIOL 140";
    private static String c3Credits = "3";
    private static String c3GradeOption = "F";
    private static String c3GradeValue = "12: 0.00";

    @Test
    @Order(3)
    @DisplayName("Test Add/Delete Functionality")
    void addDeleteCourses(){
        List<Course> courses = calculatorPage.getCourseList();
        assertEquals(1, courses.size());

        calculatorPage.addCourse(2);
        courses = calculatorPage.getCourseList();
        assertEquals(3, courses.size());

        // insert details for courses 
        Course first = courses.get(0);
        first.insertCourseName(c1Name);
        Course second = courses.get(1);
        second.insertCourseName(c2Name);
        Course third = courses.get(2);
        third.insertCourseName(c3Name);

        // delete the first two courses
        first.deleteCourse();
        second.deleteCourse();

        // get updated list of courses, and verify that the single one that remains 
        // is the third course that was added and it is the only one too
        courses = calculatorPage.getCourseList();
        assertEquals(1, courses.size());
        assertEquals(c3Name, courses.get(0).getCourseName());

        // reset the page for the next test
        calculatorPage.resetPage();
        courses = calculatorPage.getCourseList();
        assertEquals(1, courses.size());
    }
}
