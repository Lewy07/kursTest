package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage {

    @FindBy(xpath = "//a[@class='go-right ellipsisFIX go-text-right mob-fs14']")
    private List<WebElement> results;

    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> getHotelCityNames() {
        return results.stream().map(WebElement -> WebElement.getAttribute("title")).collect(Collectors.toList());
    }
}
