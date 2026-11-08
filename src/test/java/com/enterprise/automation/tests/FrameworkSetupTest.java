
package com.enterprise.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FrameworkSetupTest {

    @Test
    public void verifyFrameworkSetup() {

        String frameworkName =
                "Enterprise Hybrid Selenium Framework";

        Assert.assertEquals(
                frameworkName,
                "Enterprise Hybrid Selenium Framework"
        );

        System.out.println(
                "Java 21 + Maven + TestNG setup successful!"
        );
    }
}
