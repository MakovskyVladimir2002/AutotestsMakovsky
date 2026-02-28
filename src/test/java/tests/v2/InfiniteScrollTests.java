package tests.v2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_4.InfiniteScrollPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_4.InfiniteScrollPage.INFINITE_SCROLL_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InfiniteScrollTests extends BaseTest {

    @Test
    @DisplayName("Check Infinite scroll page")
    void openInfiniteScrollTest() {
        HomePage homePage = new HomePage(driver);
        InfiniteScrollPage infiniteScrollPage = homePage.openInfiniteScrollPage();
        String currentUrl = infiniteScrollPage.getCurrentUrl();
        WebElement title = infiniteScrollPage.getTitle();
        String infiniteScrollPageFormUrl  = infiniteScrollPage.getUrl();

        assertEquals(BASE_URL + infiniteScrollPageFormUrl, currentUrl);
        assertEquals(INFINITE_SCROLL_FORM_TITLE, title.getText());
    }
}