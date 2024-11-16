package edu.rit.swen253.test.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.calculator.CalculatorPage;
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
        // calculatorPage = navigateToPage("https://tigercenter.rit.edu/tigerCenterApp/api/gpa-calc", CalculatorPage::new);
        // assertNotNull(calculatorPage);
        // window = getCurrentWindow();

        // calculatorPage.addCourse();
    }

    @Test
    @Order(2)
    @DisplayName("Click on the GPA calculator")
    void navigateToCalculator(){
        homePage.selectGPACalculator();
        // calculatorPage = assertNewWindowAndSwitch(CalculatorPage::new);
        calculatorPage = assertNewPage(CalculatorPage::new);
    }

    @Test
    @Order(3)
    void testClick(){
        // window = getCurrentWindow();
        calculatorPage.addCourse();
    }

    
}
