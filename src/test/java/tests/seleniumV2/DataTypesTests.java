package tests.seleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_9.DataTypesPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_9.DataTypesPage.DATA_TYPES_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataTypesTests extends BaseTest {

    @Test
    @DisplayName("Check Data Types page")
    void openDataTypesTest() {
        HomePage homePage = new HomePage(driver);
        DataTypesPage dataTypesPage = homePage.openDataTypesPage();
        String currentUrl = dataTypesPage.getCurrentUrl();
        WebElement title = dataTypesPage.getTitle();
        String dataTypesPageFormUrl = dataTypesPage.getUrl();

        assertEquals(BASE_URL + dataTypesPageFormUrl, currentUrl);
        assertEquals(DATA_TYPES_FORM_TITLE, title.getText());
    }
}
