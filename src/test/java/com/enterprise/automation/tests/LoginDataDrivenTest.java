
package com.enterprise.automation.tests;

import com.enterprise.automation.base.BaseTest;
import com.enterprise.automation.pages.InventoryPage;
import com.enterprise.automation.pages.LoginPage;
import com.enterprise.automation.utilities.ExcelReader;
import com.enterprise.automation.utilities.JsonDataReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class LoginDataDrivenTest extends BaseTest {

    @DataProvider(name = "jsonLoginData")
    public Object[][] jsonLoginData() {

        List<Map<String, String>> records =
                JsonDataReader.readTestData(
                        "testdata/testdata.json");

        Object[][] data = new Object[records.size()][4];

        for (int i = 0; i < records.size(); i++) {

            Map<String, String> record = records.get(i);

            data[i][0] = record.get("username");
            data[i][1] = record.get("password");
            data[i][2] = record.get("expectedResult");
            data[i][3] = record.get("expectedError");
        }

        return data;
    }

    @DataProvider(name = "excelLoginData")
    public Object[][] excelLoginData() {

        return ExcelReader.readSheet(
                "testdata/login-data.xlsx",
                "LoginData"
        );
    }

    @Test(dataProvider = "jsonLoginData")
    public void verifyLoginUsingJsonData(
            String username,
            String password,
            String expectedResult,
            String expectedError) {

        verifyLogin(
                username,
                password,
                expectedResult,
                expectedError
        );
    }

    @Test(dataProvider = "excelLoginData")
    public void verifyLoginUsingExcelData(
            String username,
            String password,
            String expectedResult,
            String expectedError) {

        verifyLogin(
                username,
                password,
                expectedResult,
                expectedError
        );
    }

    private void verifyLogin(
            String username,
            String password,
            String expectedResult,
            String expectedError) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.submitCredentials(username, password);

        if ("SUCCESS".equalsIgnoreCase(expectedResult)) {

            InventoryPage inventoryPage =
                    new InventoryPage(driver);

            Assert.assertTrue(
                    inventoryPage.isInventoryDisplayed(),
                    "Expected successful login for: " + username
            );

        } else if ("FAILURE".equalsIgnoreCase(expectedResult)) {

            Assert.assertTrue(
                    loginPage.isErrorMessageDisplayed(),
                    "Expected login error for: " + username
            );

            Assert.assertTrue(
                    loginPage.getErrorMessage()
                            .toLowerCase()
                            .contains(expectedError.toLowerCase()),
                    "Login error did not contain expected text"
            );

        } else {
            Assert.fail(
                    "Unsupported expectedResult: " + expectedResult);
        }
    }
}
