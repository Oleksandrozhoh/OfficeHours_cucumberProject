package com.cydeo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {

    // create a private constructor to remove access to this object
    private Driver(){}

    // declare WevDriver private and static, so it becomes hidden and there can be only one copy of driver at the time
    private static WebDriver driver;

    public static WebDriver getDriver(){
        if (driver == null){
            // reading the browserType from configuration.properties file
            String browserType = ConfigurationReader.getProperty("browser");

            switch (browserType){
                case "chrome":
                    //WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    //WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    //WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.err.println("Provided browser name either doesnt exist or not currently supported");
                    System.err.println("driver = null");
                    return null;
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void closeDriver(){
        if (driver!=null){
            driver.quit();
            // must assign null to driver instead of driver to be non-existent
            driver = null;
        }
    }
}

