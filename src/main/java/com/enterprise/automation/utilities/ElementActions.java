
package com.enterprise.automation.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementActions {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ElementActions(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(timeout));
    }

    public void click(By locator) {
        wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        ).click();
    }

    public void type(By locator, String value) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        element.clear();
        element.sendKeys(value);
    }

    public String getText(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).getText();
    }

    public boolean isDisplayed(By locator) {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            ).isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public void selectByVisibleText(By locator, String text) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        new Select(element).selectByVisibleText(text);
    }

    public List<String> getTexts(By locator) {
        List<WebElement> elements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
        );

        return elements.stream()
                .map(WebElement::getText)
                .toList();
    }

    public int getElementCount(By locator) {
        return driver.findElements(locator).size();
    }
}
