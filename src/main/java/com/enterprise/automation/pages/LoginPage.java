
package com.enterprise.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField =
            By.id("user-name");

    private final By passwordField =
            By.id("password");

    private final By loginButton =
            By.id("login-button");

    private final By errorMessage =
            By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void submitCredentials(
            String username,
            String password) {

        actions.type(usernameField, username);
        actions.type(passwordField, password);
        actions.click(loginButton);
    }

    public InventoryPage login(
            String username,
            String password) {

        submitCredentials(username, password);
        return new InventoryPage(driver);
    }

    public boolean isErrorMessageDisplayed() {
        return actions.isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return actions.getText(errorMessage);
    }
}
