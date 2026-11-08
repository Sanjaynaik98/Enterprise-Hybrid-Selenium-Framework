
package com.enterprise.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class InventoryPage extends BasePage {

    private final By pageTitle =
            By.cssSelector(".title");

    private final By inventoryItems =
            By.cssSelector(".inventory_item");

    private final By productNames =
            By.cssSelector("[data-test='inventory-item-name']");

    private final By productPrices =
            By.cssSelector(".inventory_item_price");

    private final By sortDropdown =
            By.cssSelector("[data-test='product-sort-container']");

    private final By shoppingCart =
            By.cssSelector(".shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInventoryDisplayed() {
        return actions.isDisplayed(pageTitle)
                && actions.getText(pageTitle).equals("Products");
    }

    public int getProductCount() {
        return actions.getElementCount(inventoryItems);
    }

    public List<String> getProductNames() {
        return actions.getTexts(productNames);
    }

    public List<String> getProductPrices() {
        return actions.getTexts(productPrices);
    }

    public void sortProducts(String visibleText) {
        actions.selectByVisibleText(sortDropdown, visibleText);
    }

    public void addProductToCart(String productName) {

        By addButton = By.xpath(
                "//div[@class='inventory_item' and " +
                        ".//div[@data-test='inventory-item-name' " +
                        "and normalize-space()='" + productName + "']]" +
                        "//button"
        );

        actions.click(addButton);
    }

    public CartPage openCart() {
        actions.click(shoppingCart);
        return new CartPage(driver);
    }
}
