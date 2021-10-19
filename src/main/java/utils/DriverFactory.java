package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class DriverFactory {

    public static WebDriver getDriver() throws IOException {
        switch (PropertiesLoader.loadProperty("browser.name")){
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            default: {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
        }
    }
}
