package tests.SeleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_4.WebStoragePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_4.WebStoragePage.WEB_STORAGE_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WebStorageTests extends BaseTest {

    @Test
    @DisplayName("Check Web storage page")
    void openWebStorageTest() {
        HomePage homePage = new HomePage(driver);
        WebStoragePage webStoragePage = homePage.openWebStoragePage();
        String currentUrl = webStoragePage.getCurrentUrl();
        WebElement title = webStoragePage.getTitle();
        String webStoragePageFormUrl = webStoragePage.getUrl();

        assertEquals(BASE_URL + webStoragePageFormUrl, currentUrl);
        assertEquals(WEB_STORAGE_FORM_TITLE, title.getText());
    }
}
