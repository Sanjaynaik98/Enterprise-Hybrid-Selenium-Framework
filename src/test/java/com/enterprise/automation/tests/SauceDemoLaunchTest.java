package com.enterprise.automation.tests;

import com.enterprise.automation.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoLaunchTest extends BaseTest {

    @Test
    public void verifySauceDemoApplicationLaunch() {

        String actualTitle = driver.getTitle();

        Assert.assertEquals(
                actualTitle,
                "Swag Labs",
                "SauceDemo application title does not match");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("saucedemo.com"),
                "SauceDemo URL was not loaded");

        System.out.println(
                "SauceDemo application launched successfully!");
    }
}