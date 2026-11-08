
package com.enterprise.automation.base;

import com.enterprise.automation.config.ConfigReader;
import com.enterprise.automation.driver.DriverFactory;
import com.enterprise.automation.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);

        driver.manage().timeouts().implicitlyWait(
                Duration.ZERO
        );

        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(
                        ConfigReader.getIntProperty("pageLoadTimeout")
                )
        );

        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
