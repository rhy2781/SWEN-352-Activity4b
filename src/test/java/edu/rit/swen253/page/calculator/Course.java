package edu.rit.swen253.page.calculator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import edu.rit.swen253.utils.DomElement;

public class Course {
    private DomElement container;

    public Course(final DomElement courseContainer){
        this.container = courseContainer;
    }
    public void deleteCourse(){
        DomElement delete = container.findChildBy(By.className("hoverPointer"));
        delete.click();
    }
    public void insertCourseName(String courseName){
        DomElement input = container.findChildBy(By.className("courseInputWidth"));
        input.enterText(courseName);
    }
    public void insertCourseCredits(int credits){
        DomElement input = container.findChildBy(By.className("inputBox"));
        input.enterText(String.valueOf(credits));
    }
    public void insertCourseGrade(String grade){
        DomElement input = container.findChildBy(By.className("gradeDropDown"));
        Select s = new Select(input.getWebElement());
        s.selectByIndex(0);
        s.selectByVisibleText(grade);
    }

    /** Getters */
    public String getCourseName(){
        DomElement input = container.findChildBy(By.className("courseInputWidth"));
        return input.getInputValue();
    }

    public String getCourseCredits(){
        DomElement input = container.findChildBy(By.className("inputBox"));
        return input.getInputValue();
    }

    public String getCourseGrade(){
        DomElement input = container.findChildBy(By.className("gradeDropDown"));
        return input.getInputValue();
    }
}
