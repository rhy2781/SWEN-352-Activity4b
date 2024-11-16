package edu.rit.swen253.page.calculator;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.openqa.selenium.By;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;

public class CalculatorPage extends AbstractAngularPage {

    public CalculatorPage(){
        super("gpa-calc");
    }

    public void addCourse(){
        DomElement buttonParent = angularView.findChildBy(By.className("buttonRow"));
        System.out.println(buttonParent);
        // System.out.println(buttonParent);
        DomElement button = buttonParent.findChildBy(By.className("secondaryButton"));
        button.click();
        System.out.println("success");
        // button.click();
    }

    // public List<Course> getCourseList(){
    //     List<DomElement> courses = mainContentPanel.findChildrenBy(By.className("w3-animate-opacity"));
    //     return courses.stream()
    //         .map(domElement -> new Course(domElement))
    //         .collect(Collectors.toList());
    // }


}
