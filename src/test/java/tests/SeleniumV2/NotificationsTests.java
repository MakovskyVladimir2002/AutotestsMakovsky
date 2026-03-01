package tests.SeleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_5.NotificationsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_5.NotificationsPage.NOTIFICATIONS_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationsTests extends BaseTest {

    @Test
    @DisplayName("Check Notifications page")
    void openNotificationsTest() {
        HomePage homePage = new HomePage(driver);
        NotificationsPage notificationsPage= homePage.openNotificationsPage();
        String currentUrl = notificationsPage.getCurrentUrl();
        WebElement title = notificationsPage.getTitle();
        String notificationsPageFormUrl = notificationsPage.getUrl();

        assertEquals(BASE_URL + notificationsPageFormUrl, currentUrl);
        assertEquals(NOTIFICATIONS_FORM_TITLE, title.getText());
    }
}
