package tests.seleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_3.NavigationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_3.NavigationPage.NAV_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NavigationTests extends BaseTest {

    @Test
    @DisplayName("Check Navigation page")
    void openNavigationTest() {
        HomePage homePage = new HomePage(driver);
        NavigationPage navigationPage = homePage.openNavigationPage();
        String currentUrl = navigationPage.getCurrentUrl();
        WebElement title = navigationPage.getTitle();
        String navigationFormUrl = navigationPage.getUrl();

        assertEquals(BASE_URL + navigationFormUrl, currentUrl);
        assertEquals(NAV_FORM_TITLE, title.getText());
    }
}
