package dataProvider;

import org.testng.annotations.DataProvider;
import utils.DataReader;

import java.io.IOException;

public class HotelSearchDataProvider {

    @DataProvider(name = "hotelDataProvider")
    public static Object[][] searchHotelDataProvider() throws IOException {
        return DataReader.readExcel("Travellers.xlsx");
    }
}
