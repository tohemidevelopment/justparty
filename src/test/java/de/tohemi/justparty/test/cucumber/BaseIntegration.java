package de.tohemi.justparty.test.cucumber;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

import static org.openqa.selenium.remote.DesiredCapabilities.*;

/**
 * Created by Heiko on 07.12.2015.
 */
public class BaseIntegration {
    public static final int TIMEOUT = 10;

    public static FirefoxDriver firefoxDriver() {
        DesiredCapabilities capabilities = firefox();
        capabilities.setJavascriptEnabled(true);
        FirefoxDriver driver = new FirefoxDriver(capabilities);
        driver.manage().window().maximize();
        return driver;
    }

    public static ChromeDriver chromeDriver() {
        File file = null;
        if (SystemUtils.IS_OS_LINUX) {
            file = new File("src/test/resources/driver/linux/chromedriver");
        }
        if (SystemUtils.IS_OS_WINDOWS) {
            file = new File("src/test/resources/driver/windows/chromedriver.exe");
        }
        if (file == null) {
            throw new RuntimeException("ChromeDriver is not supported by your operating system");
        }
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        DesiredCapabilities capabilities = chrome();
        capabilities.setJavascriptEnabled(true);
        ChromeDriver driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver mobileDriver() {
        return new RemoteWebDriver(android());
    }
}