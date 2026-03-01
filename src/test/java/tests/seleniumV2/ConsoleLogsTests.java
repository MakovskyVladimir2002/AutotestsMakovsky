package tests.seleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_5.ConsoleLogsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_5.ConsoleLogsPage.CONSOLE_LOGS_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleLogsTests extends BaseTest {

    @Test
    @DisplayName("Check Console Logs page")
    void openConsoleLogsTest() {
        HomePage homePage = new HomePage(driver);
        ConsoleLogsPage consoleLogsPage = homePage.openConsoleLogsPage();
        String currentUrl = consoleLogsPage.getCurrentUrl();
        WebElement title = consoleLogsPage.getTitle();
        String consoleLogsPageFormUrl = consoleLogsPage.getUrl();

        assertEquals(BASE_URL + consoleLogsPageFormUrl, currentUrl);
        assertEquals(CONSOLE_LOGS_FORM_TITLE, title.getText());
    }
}