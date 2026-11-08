package com.enterprise.automation.driver;

import com.enterprise.automation.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver() {

        String browser = ConfigReader
                .getProperty("browser")
                .toLowerCase();

        boolean headless =
                ConfigReader.getBooleanProperty("headless");

        switch (browser) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }

                chromeOptions.addArguments("--start-maximized");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }

                return new FirefoxDriver(firefoxOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();

                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                }

                edgeOptions.addArguments("--start-maximized");
                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser);
        }
    }
}