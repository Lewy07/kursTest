import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HotelSearchPage;
import pages.LoggedUserPage;
import pages.SignUpPage;

import java.util.List;

public class SingUpTest extends BaseTest {

    @Test
    public void signUpTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openRegistryPage();

        SignUpPage signUpPage = new SignUpPage(driver);

        int random = (int) (Math.random() * 1000);
        String email = "mail" + random + "@wp.pl";

        signUpPage.setFistName("Bartek");
        signUpPage.setLastName("Lewandowski");
        signUpPage.setPhone("666555444");
        signUpPage.setEmail(email);
        signUpPage.setPassword("pass123");
        signUpPage.setConfirmPassword("pass123");
        signUpPage.sendForm();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeading().contains("Bartek Lewandowski"));
    }

    @Test
    public void signUpOneMethodTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openRegistryPage();

        int random = (int) (Math.random() * 1000);
        String email = "mail" + random + "@gmail.pl";

        SignUpPage signUpPage = new SignUpPage(driver);
        User user = User.builder()
                .firstName("Bartek")
                .lastName("Lewandowski")
                .phone("123456789")
                .email(email)
                .password("password123")
                .build();

        signUpPage.fullForm(user);

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeading().contains("Bartek Lewandowski"));
    }

    @Test
    public void noContentSignUpTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openRegistryPage();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.sendForm();

        List<String> errors = signUpPage.getErrorMessages();

        SoftAssert softAssert = new SoftAssert();

        errors.forEach(System.out::println);
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));

        softAssert.assertAll();
    }

    @Test
    public void invalidEmailTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openRegistryPage();

        SignUpPage signUpPage = new SignUpPage(driver);

        signUpPage.setFistName("Bartek");
        signUpPage.setLastName("Lewandowski");
        signUpPage.setPhone("666555444");
        signUpPage.setEmail("email");
        signUpPage.setPassword("pass123");
        signUpPage.setConfirmPassword("pass123");
        signUpPage.sendForm();

        List<String> errors = signUpPage.getErrorMessages();

        Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));
    }
}
