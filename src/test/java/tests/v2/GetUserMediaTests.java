package tests.v2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_5.GetUserMediaPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_5.GetUserMediaPage.GET_USER_MEDIA_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GetUserMediaTests extends BaseTest {

    @Test
    @DisplayName("Check Get user media page")
    void openGetUserMediaTest() {
        HomePage homePage = new HomePage(driver);
        GetUserMediaPage getUserMediaPage= homePage.openGetUserMediaPage();
        String currentUrl = getUserMediaPage.getCurrentUrl();
        WebElement title = getUserMediaPage.getTitle();
        String getUserMediaPageFormUrl = getUserMediaPage.getUrl();

        assertEquals(BASE_URL + getUserMediaPageFormUrl, currentUrl);
        assertEquals(GET_USER_MEDIA_FORM_TITLE, title.getText());
    }
}
