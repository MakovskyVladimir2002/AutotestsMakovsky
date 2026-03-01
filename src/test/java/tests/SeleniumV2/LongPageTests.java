package tests.SeleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_4.LongPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_4.LongPage.LONG_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LongPageTests extends BaseTest {

    @Test
    @DisplayName("Check Long page")
    void openLongPageTest() {
        HomePage homePage = new HomePage(driver);
        LongPage longPage = homePage.openLongPage();
        String currentUrl = longPage.getCurrentUrl();
        WebElement title = longPage.getTitle();
        String longPageFormUrl = longPage.getUrl();

        assertEquals(BASE_URL + longPageFormUrl, currentUrl);
        assertEquals(LONG_FORM_TITLE, title.getText());
    }
}
