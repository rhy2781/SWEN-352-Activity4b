package edu.rit.swen253.page.calculator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import edu.rit.swen253.utils.DomElement;

/**
 * Object representing courses used to calculate GPA on Tiger Center's GPA calculator page
 * @author <a href='mailto:rhy2781@rit.edu'>Robert Yamasaki</a>
 */
public class Course {
    public DomElement container;

    public Course(final DomElement courseContainer){
        this.container = courseContainer;
    }

    /**
     * Delete the course from the list
     */
    public void deleteCourse(){
        DomElement delete = container.findChildBy(By.className("hoverPointer"));
        delete.click();
    }

    /**
     * @param courseName The name associated with the course
     */
    public void insertCourseName(String courseName){
        DomElement input = container.findChildBy(By.className("courseInputWidth"));
        input.enterText(courseName);
    }

    /**
     * The input field for course credits is associated with two instance of input.
     * The second instance refers to the one where credits is inputted
     * @param credits The number of credits a course is worth
     */
    public void insertCourseCredits(String credits){
        List<DomElement> input = container.findChildrenBy(By.className("inputBox"));
        DomElement insert = input.get(1);
        insert.clear();
        insert.enterText(credits);
    }

    /**
     * @param grade The grade associated with the course
     */
    public void insertCourseGrade(String grade){
        DomElement input = container.findChildBy(By.className("gradeDropDown"));
        Select s = new Select(input.getWebElement());
        s.selectByIndex(0);
        s.selectByVisibleText(grade);
    }

    /** Getters */
    /**
     * @return The name associated with the course object
     */
    public String getCourseName(){
        DomElement input = container.findChildBy(By.className("courseInputWidth"));
        return input.getInputValue();
    }

    /**
     * The marker for the credits input is associated with the "inputBox" class which has refers
     * to two instances
     * @return the number of credits associated with a course
     */
    public String getCourseCredits(){
        List<DomElement> input = container.findChildrenBy(By.className("inputBox"));
        return input.get(1).getInputValue();
    }

    /**
     * @return The grade associated with the course
     */
    public String getCourseGrade(){
        DomElement input = container.findChildBy(By.className("gradeDropDown"));
        return input.getInputValue();
    }
}
