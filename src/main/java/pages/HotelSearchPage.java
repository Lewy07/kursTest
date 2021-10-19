package pages;

import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtil;

import java.util.List;

@Log
public class HotelSearchPage {

    @FindBy(xpath = "//span[text() = 'Search by Hotel or City Name']")
    private WebElement searchHotel;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    @FindBy(xpath = "//input[@name='checkin']")
    private WebElement checkInInput;

    @FindBy(xpath = "//input[@name='checkout']")
    private WebElement checkOutInput;

    @FindBy(xpath = "//input[@id='travellersInput']")
    private WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultInput;

    @FindBy(id = "childPlusBtn")
    private WebElement childInput;

    @FindBy(xpath = "//button[@type=\"submit\"  and text()=\" Search\"]")
    private WebElement submitButton;

    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> loginList;

    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> registryButton;

    private final WebDriver driver;

    public HotelSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setCity(String cityName) {
        searchHotel.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = "//span[@class='select2-match' and text()='" + cityName + "']";
        WaitUtil.waitForElementExist(driver, By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
        log.info("Setting city done");
    }

    public void setDates(String checkIn, String checkOut) {
        checkInInput.sendKeys(checkIn);
        checkOutInput.sendKeys(checkOut);
        log.info("Setting dates done");
    }

    public void setPeople(int childNumb, int adultNumb) {
        travellersInput.click();

        WaitUtil.waitForElementVisible(driver, childInput);

        for (int i = 0; i < childNumb; i++) {
            childInput.click();
        }

        for (int i = 0; i < adultNumb; i++) {
            adultInput.click();
        }

        log.info("Setting people done");
    }

    public ResultsPage performSearch() {
        submitButton.click();
        return new ResultsPage(driver);
    }

    public void openRegistryPage() {
        loginList.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        registryButton.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
    }
}
