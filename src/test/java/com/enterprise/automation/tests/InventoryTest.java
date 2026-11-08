
package com.enterprise.automation.tests;

import com.enterprise.automation.base.BaseTest;
import com.enterprise.automation.config.ConfigReader;
import com.enterprise.automation.pages.InventoryPage;
import com.enterprise.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class InventoryTest extends BaseTest {

    private InventoryPage loginToInventory() {

        return new LoginPage(driver).login(
                ConfigReader.getProperty("sauce.username"),
                ConfigReader.getProperty("sauce.password")
        );
    }

    @Test
    public void verifyProductListing() {

        InventoryPage inventoryPage = loginToInventory();

        Assert.assertTrue(
                inventoryPage.isInventoryDisplayed()
        );

        Assert.assertEquals(
                inventoryPage.getProductCount(),
                6,
                "Expected six products in inventory"
        );

        System.out.println("Product listing verified!");
    }

    @Test
    public void verifyPriceLowToHighSorting() {

        InventoryPage inventoryPage = loginToInventory();

        inventoryPage.sortProducts("Price (low to high)");

        List<String> prices = inventoryPage.getProductPrices();

        double firstPrice = Double.parseDouble(
                prices.get(0).replace("$", "")
        );

        double lastPrice = Double.parseDouble(
                prices.get(prices.size() - 1).replace("$", "")
        );

        Assert.assertTrue(
                firstPrice <= lastPrice,
                "Products are not sorted by ascending price"
        );

        Assert.assertEquals(firstPrice, 7.99,
                "The cheapest product should appear first");

        System.out.println("Price sorting verified!");
    }
}
