package tests.seleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_5.MultilanguagePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_5.MultilanguagePage.MULTILANGUAGE_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MultilanguageTests extends BaseTest {

    @Test
    @DisplayName("Check Multilanguage page")
    void openMultilanguageTest() {
        HomePage homePage = new HomePage(driver);
        MultilanguagePage multilanguagePage = homePage.openMultilanguagePage();
        String currentUrl = multilanguagePage.getCurrentUrl();
        WebElement title = multilanguagePage.getTitle();
        String multilanguagePageFormUrl = multilanguagePage.getUrl();

        assertEquals(BASE_URL + multilanguagePageFormUrl, currentUrl);
        assertEquals(MULTILANGUAGE_FORM_TITLE, title.getText());
    }
}
