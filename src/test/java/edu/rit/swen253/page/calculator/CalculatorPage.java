package edu.rit.swen253.page.calculator;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

/**
 * Page object for Tiger Center's GPA Calculator page
 * @author <a href='mailto:rhy2781@rit.edu'>Robert Yamasaki</a>
 */
public class CalculatorPage extends AbstractAngularPage {

    public CalculatorPage(){
        super("gpa-calc");
    }

    /** 
     * Reset the page
     * This button is the first child with the className "secondaryButton"
     */
    public void resetPage(){
        DomElement button = angularView.findChildBy(By.className("secondaryButton"));
        button.click();
        WebDriver driver = SeleniumUtils.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> getCourseList().size() == 1);
    }
    
    /**
     * Add more courses to the GPA calculator
     * @param counter The number of courses to add
     */
    public void addCourse(int counter){
        DomElement buttonParent = angularView.findChildBy(By.className("buttonRow"));
        DomElement button = buttonParent.findChildBy(By.className("secondaryButton"));
        for(int i = 0; i < counter; i++){
            button.click();
        }
    }

    /**
     * @return the list of associated Course Objects
     */
    public List<Course> getCourseList(){
        List<DomElement> courses = angularView.findChildrenBy(By.className("courseRow"));

        return courses.stream()
            .filter(e -> e.getWebElement().isDisplayed())
            .map(domElement -> new Course(domElement))
            .collect(Collectors.toList());
    }

    /**
     * The Term GPA is stored within the "results" className, for which there are two values
     * The first element corresponds to the Term GPA
     * @return the GPA for the term
     */
    public String getTermGPA(){
        List<DomElement> results = angularView.findChildrenBy(By.className("results"));
        return results.get(0).getText();
    }

    /**
     * The Term GPA is stored within the "results" className, for which there are two values
     * The second element corresponds to the Cumulative GPA
     * @return the GPA for the term
     */
    public String getCumulativeGPA(){
        List<DomElement> results = angularView.findChildrenBy(By.className("results"));
        return results.get(1).getText();
    }

    /**
     * Calculate the GPA
     */
    public void calculateGPA(){
        DomElement button = angularView.findChildBy(By.className("primaryButton"));
        button.click();
    }


}
