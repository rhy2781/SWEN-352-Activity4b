package edu.rit.swen253.test.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
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
public class CalculatorTest extends AbstractWebTest{

    private TigerCenterHomePage homePage;
    private CalculatorPage calculatorPage;
    private Logger logger = Logger.getLogger(CalculatorTest.class.getName());

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

    private static String c4Name = "MATH 190";
    private static String c4Credits = "3";
    private static String c4GradeOption = "C+";
    private static String c4GradeValue = "6: 2.33";


    @BeforeEach
    void navigateToHomePage(){
        this.logger.info("Navigate to base Calculator page");
        homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
        assertNotNull(homePage);
        homePage.selectGPACalculator();
        calculatorPage = assertNewPage(CalculatorPage::new);
        assertNotNull(calculatorPage);
    }

    @Test
    @Order(1)
    @DisplayName("Test Add/Delete Functionality")
    void addDeleteCourses(){
        this.logger.info("Testing Add/Delete Functionality");
        List<Course> courses = calculatorPage.getCourseList();
        assertEquals(1, courses.size());

        this.logger.info("adding two courses for a total of three courses");
        calculatorPage.addCourse(2);
        courses = calculatorPage.getCourseList();
        assertEquals(3, courses.size());

        // insert details for courses 
        this.logger.info("adding details for all three courses");
        Course first = courses.get(0);
        first.insertCourseName(c1Name);
        Course second = courses.get(1);
        second.insertCourseName(c2Name);
        Course third = courses.get(2);
        third.insertCourseName(c3Name);

        // delete the first two courses
        this.logger.info("deleting course one and two");
        first.deleteCourse();
        second.deleteCourse();

        // get updated list of courses, and verify that the single one that remains 
        // is the third course that was added and it is the only one too
        courses = calculatorPage.getCourseList();
        assertEquals(1, courses.size());
        assertEquals(c3Name, courses.get(0).getCourseName());
        this.logger.info("Assert that the number of courses is 1, and the name is correlated to the third course");
        this.logger.info("Number of courses: " + courses.size() + "Course Name: " + courses.get(0).getCourseName());

        // reset the page for the next test
        calculatorPage.resetPage();
        courses = calculatorPage.getCourseList();
        assertEquals(1, courses.size());
        this.logger.info("Resetting the page");
        this.logger.info("");
    }

    @Test
    @Order(2)
    @DisplayName("Test w/ one course")
    /** Insert one course and verify that the GPA calculator is correct */
    void addCourseDetails(){
        this.logger.info("Testing Insertion of Details for one course");

        List<Course> courses = calculatorPage.getCourseList();
        assertEquals(1, courses.size());
        Course first = courses.get(0);

        first.insertCourseName(c1Name);
        assertEquals(c1Name, first.getCourseName());
        
        first.insertCourseCredits(c1Credits);
        assertEquals(c1Credits, first.getCourseCredits());
        
        first.insertCourseGrade(c1GradeOption);
        assertEquals(c1GradeValue, first.getCourseGrade());
        this.logger.info("Course Inserted - Name: " + first.getCourseName() + " Credits: " + first.getCourseCredits() + " Grade: " + first.getCourseGrade());
 
        calculatorPage.calculateGPA();
        assertEquals("4.00", calculatorPage.getTermGPA());
        assertEquals("4.00", calculatorPage.getCumulativeGPA());
        
        // prepare for next test by resetting courses
        this.logger.info("Resetting Page");
        calculatorPage.resetPage();
        courses = calculatorPage.getCourseList();
        assertEquals(1, courses.size());
        this.logger.info("");
    }

    @Test
    @Order(4)
    @DisplayName("Test w/ full course schedule")
    void addCourseDetailsFull(){
        // add courses until 4 total
        calculatorPage.addCourse(3);
        List<Course> courses = calculatorPage.getCourseList();

        // insert the first course details
        Course first = courses.get(0);
        first.insertCourseName(c1Name);
        first.insertCourseGrade(c1GradeOption);
        first.insertCourseCredits(c1Credits);
        assertEquals(c1Name, first.getCourseName());
        assertEquals(c1GradeValue, first.getCourseGrade());
        assertEquals(c1Credits, first.getCourseCredits());
        this.logger.info("Course Inserted - Name: " + first.getCourseName() + " Credits: " + first.getCourseCredits() + " Grade: " + first.getCourseGrade());

        // insert the second course details
        Course second = courses.get(1);
        second.insertCourseName(c2Name);
        second.insertCourseGrade(c2GradeOption);
        second.insertCourseCredits(c2Credits);
        assertEquals(c2Name, second.getCourseName());
        assertEquals(c2GradeValue, second.getCourseGrade());
        assertEquals(c2Credits, second.getCourseCredits());
        this.logger.info("Course Inserted - Name: " + second.getCourseName() + " Credits: " + second.getCourseCredits() + " Grade: " + second.getCourseGrade());

        // insert the third course details
        Course third = courses.get(2);
        third.insertCourseName(c3Name);
        third.insertCourseGrade(c3GradeOption);
        third.insertCourseCredits(c3Credits);
        assertEquals(c3Name, third.getCourseName());
        assertEquals(c3GradeValue, third.getCourseGrade());
        assertEquals(c3Credits, third.getCourseCredits());
        this.logger.info("Course Inserted - Name: " + third.getCourseName() + " Credits: " + third.getCourseCredits() + " Grade: " + third.getCourseGrade());

        // insert the fourth course details
        Course fourth = courses.get(3);
        fourth.insertCourseName(c4Name);
        fourth.insertCourseGrade(c4GradeOption);
        fourth.insertCourseCredits(c4Credits);
        assertEquals(c4Name, fourth.getCourseName());
        assertEquals(c4GradeValue, fourth.getCourseGrade());
        assertEquals(c4Credits, fourth.getCourseCredits());
        this.logger.info("Course Inserted - Name: " + fourth.getCourseName() + " Credits: " + fourth.getCourseCredits() + " Grade: " + fourth.getCourseGrade());
 
        // check the term and cumulative gpa
        calculatorPage.calculateGPA();
        assertEquals("1.83", calculatorPage.getTermGPA());
        assertEquals("1.83", calculatorPage.getCumulativeGPA());
        this.logger.info("Term GPA: " + calculatorPage.getTermGPA() + " Cumulative GPA: " + calculatorPage.getCumulativeGPA());
        
        // reset to one empty course
        this.logger.info("Resetting Page");
        calculatorPage.resetPage();
        courses = calculatorPage.getCourseList();
        assertEquals(1, courses.size());
        this.logger.info("");
    }
    

}
