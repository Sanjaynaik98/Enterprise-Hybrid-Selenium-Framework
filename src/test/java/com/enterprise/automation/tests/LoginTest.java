
package com.enterprise.automation.tests;

import com.enterprise.automation.base.BaseTest;
import com.enterprise.automation.config.ConfigReader;
import com.enterprise.automation.pages.InventoryPage;
import com.enterprise.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void verifySuccessfulLogin() {

        LoginPage loginPage = new LoginPage(driver);

        InventoryPage inventoryPage = loginPage.login(
                ConfigReader.getProperty("sauce.username"),
                ConfigReader.getProperty("sauce.password")
        );

        Assert.assertTrue(
                inventoryPage.isInventoryDisplayed(),
                "Inventory page was not displayed after login"
        );

        System.out.println("Successful login verified!");
    }
}
