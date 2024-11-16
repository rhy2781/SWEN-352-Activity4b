package edu.rit.swen253.page.calculator;

import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class Course {
    private DomElement container;

    public Course(final DomElement courseContainer){
        this.container = courseContainer;
    }

    
    public void deleteCourse(){

    }
    public void insertCourseName(String courseName){
        DomElement input = container.findChildBy(By.className("courseInputWidth"));
        input.enterText(courseName);
        System.out.println(input.getInputValue());


    }
    public void insertCourseCredits(int credits){

    }
    public void insertCourseGrade(String grade){

    }

    


    
}
