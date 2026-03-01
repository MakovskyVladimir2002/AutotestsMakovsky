package tests.SeleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_5.GeolocationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_5.GeolocationPage.GEOLOCATION_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GeolocationTests extends BaseTest {

    @Test
    @DisplayName("Check Geolocation page")
    void openGeolocationTest() {
        HomePage homePage = new HomePage(driver);
        GeolocationPage geolocationPage = homePage.openGeolocationPage();
        String currentUrl = geolocationPage.getCurrentUrl();
        WebElement title = geolocationPage.getTitle();
        String geolocationPageFormUrl = geolocationPage.getUrl();

        assertEquals(BASE_URL + geolocationPageFormUrl, currentUrl);
        assertEquals(GEOLOCATION_FORM_TITLE, title.getText());
    }
}
