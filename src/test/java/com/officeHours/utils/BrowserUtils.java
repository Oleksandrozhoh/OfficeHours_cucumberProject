package com.cydeo.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {
    /*
    This class will be storing only the utility methods that can be used across the project
    */

    /*
    This method will accept int (in seconds)
    and execute Thread.sleep method for given duration
    */
    public static void sleep(int second){
        second *=1000;
        try{
            Thread.sleep(second);
        }catch (InterruptedException e){

        }
    }

    /*This method is switching windows and verifying the title of the newly opened window*/
    public static void switchWindowAndVerify(String expectedInTitle){
        //4. Create a logic to switch to the tab where Etsy.com is open
        for (String each : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(each);
            if (Driver.getDriver().getTitle().contains(expectedInTitle)) {
                break;
            }
        }
        //5. Assert: Title contains “Etsy”
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));
    }

    /*This method will verify the title of the webPage*/
    public static void verifyTitle(WebDriver driver, String expectedTitle){
        Assert.assertEquals(driver.getTitle(),expectedTitle,"Title verification failed!");
    }

    /*This method will verify the title of the webPage contains expectedInTitle*/
    public static void verifyTitleContains(String expectedInTitle){
        Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedInTitle));
    }

    public static void waitForInvicibilityOf(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitFOrTitleCOntains(String str){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains(str));
    }

    public static void verifySelectDropdownOptions( WebElement dropdown, List<String> expectedOptions) {
        List<String> actualOptions = new ArrayList<>();

        Select select = new Select(dropdown);
        List<WebElement> actualMonthWE = select.getOptions();

        for (WebElement each : actualMonthWE) {
            actualOptions.add(each.getText());
        }
        Assert.assertEquals(actualOptions, expectedOptions);
    }




}
