package edu.rit.swen253.page.calculator;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;

public class CalculatorPage extends AbstractAngularPage {

    public CalculatorPage(){
        super("gpa-calc");
    }

    public void addCourse(){
        DomElement buttonParent = angularView.findChildBy(By.className("buttonRow"));
        DomElement button = buttonParent.findChildBy(By.className("secondaryButton"));
        button.click();
    }

    public List<Course> getCourseList(){
        List<DomElement> courses = angularView.findChildrenBy(By.className("courseRow"));

        return courses.stream()
            .filter(e -> e.getWebElement().isDisplayed())
            .map(domElement -> new Course(domElement))
            .collect(Collectors.toList());
    }
    public String getTermGPA(){
        List<DomElement> results = angularView.findChildrenBy(By.className("results"));
        return results.get(0).getText();
    }

    public String getCumulativeGPA(){
        List<DomElement> results = angularView.findChildrenBy(By.className("results"));
        return results.get(1).getText();
    }

    public void calculateGPA(){
        DomElement button = angularView.findChildBy(By.className("primaryButton"));
        button.click();
    }


}
