import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import dataProvider.HotelSearchDataProvider;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HotelSearchPage;
import utils.Screenshot;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("11/05/2021", "14/05/2021");
        hotelSearchPage.setPeople(1, 1);
        hotelSearchPage.performSearch()
                .getHotelCityNames()
                .forEach(name -> Assert.assertTrue(name.contains("dubai")));
    }

    @SneakyThrows
    @Test(dataProvider = "hotelDataProvider", dataProviderClass = HotelSearchDataProvider.class)
    public void searchHotelTestWitDP(String city, String hotel) {
        ExtentTest test = extentReports.createTest("Search Hotel Test");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
        test.log(Status.PASS, "Setting city done", Screenshot.takeScreenshotForReport(driver));
        hotelSearchPage.setDates("11/05/2021", "14/05/2021");
        test.log(Status.PASS, "Setting dates done", Screenshot.takeScreenshotForReport(driver));
        hotelSearchPage.setPeople(1, 1);
        test.log(Status.PASS, "Setting travelers done", Screenshot.takeScreenshotForReport(driver));
        hotelSearchPage.performSearch()
                .getHotelCityNames()
                .forEach(name -> Assert.assertTrue(name.contains(hotel)));
        test.log(Status.PASS, "Assert done", Screenshot.takeScreenshotForReport(driver));
    }
}
