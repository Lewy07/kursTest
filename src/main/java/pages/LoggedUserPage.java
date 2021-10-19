package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtil;

public class LoggedUserPage {

    private WebDriver driver;

    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement heading;

    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getHeading() {
        WaitUtil.waitForElementVisible(driver, heading);
        return heading.getText();
    }
}
