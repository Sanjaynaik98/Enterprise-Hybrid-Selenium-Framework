
package com.enterprise.automation.pages;

import com.enterprise.automation.config.ConfigReader;
import com.enterprise.automation.utilities.ElementActions;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    protected ElementActions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;

        this.actions = new ElementActions(
                driver,
                ConfigReader.getIntProperty("timeout")
        );
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
