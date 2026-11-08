
package com.enterprise.automation.tests;

import com.enterprise.automation.base.BaseTest;
import com.enterprise.automation.config.ConfigReader;
import com.enterprise.automation.pages.CartPage;
import com.enterprise.automation.pages.InventoryPage;
import com.enterprise.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void verifyProductCanBeAddedToCart() {

        LoginPage loginPage = new LoginPage(driver);

        InventoryPage inventoryPage = loginPage.login(
                ConfigReader.getProperty("sauce.username"),
                ConfigReader.getProperty("sauce.password")
        );

        String productName = "Sauce Labs Backpack";

        inventoryPage.addProductToCart(productName);

        CartPage cartPage = inventoryPage.openCart();

        Assert.assertTrue(
                cartPage.isCartPageDisplayed(),
                "Cart page was not displayed"
        );

        List<String> cartProducts =
                cartPage.getCartProductNames();

        Assert.assertTrue(
                cartProducts.contains(productName),
                "Expected product was not found in cart"
        );

        System.out.println("Add-to-cart functionality verified!");
    }
}
