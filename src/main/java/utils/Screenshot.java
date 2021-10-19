package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Screenshot {

    public static String takeScreenshot(WebDriver driver) throws IOException {

        String dataFormat = "yyyy/MM/dd'T'HH mm ss";
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern(dataFormat)).toString();
        String path = "src/test/resources/screenshot/" + fileName + ".png";

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(path));

        return path;
    }

    public static MediaEntityModelProvider takeScreenshotForReport(WebDriver driver) throws IOException {

        String path = takeScreenshot(driver);
        return MediaEntityBuilder.createScreenCaptureFromPath(path).build();
    }
}
