package pages;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtil;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement phone;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@name='confirmpassword']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement submitBtn;

    @FindBy(xpath = "//div[@class=\"alert alert-danger\"]/p")
    private List<WebElement> errors;

    private WebDriver driver;

    public SignUpPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setFistName(String fn) {
        firstName.sendKeys(fn);
    }

    public void setLastName(String ln) {
        lastName.sendKeys(ln);
    }

    public void setPhone(String pn) {
        phone.sendKeys(pn);
    }

    public void setEmail(String mail) {
        email.sendKeys(mail);
    }

    public void setPassword(String pass) {
        password.sendKeys(pass);
    }

    public void setConfirmPassword(String pass) {
        confirmPassword.sendKeys(pass);
    }

    public void sendForm(){
        submitBtn.click();
    }

    public List<String> getErrorMessages() {
        WaitUtil.waitForElementList(driver, By.xpath("//div[@class=\"alert alert-danger\"]/p"));
        return errors.stream().map(error -> error.getText()).collect(Collectors.toList());
    }

    public void fullForm(User user) {
        firstName.sendKeys(user.getFirstName());
        lastName.sendKeys(user.getLastName());
        phone.sendKeys(user.getPhone());
        email.sendKeys(user.getEmail());
        password.sendKeys(user.getPassword());
        confirmPassword.sendKeys(user.getPassword());
        submitBtn.click();
    }
}
