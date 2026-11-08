
package com.enterprise.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartPage extends BasePage {

    private final By pageTitle =
            By.cssSelector(".title");

    private final By cartItems =
            By.cssSelector(".cart_item .inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPageDisplayed() {
        return actions.isDisplayed(pageTitle)
                && actions.getText(pageTitle).equals("Your Cart");
    }

    public List<String> getCartProductNames() {
        return actions.getTexts(cartItems);
    }
}
