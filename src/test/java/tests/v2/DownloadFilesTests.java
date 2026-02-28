package tests.v2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_9.DownloadFilesPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_9.DownloadFilesPage.DOWNLOAD_FILES_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DownloadFilesTests extends BaseTest {

    @Test
    @DisplayName("Check Download Files page")
    void openDownloadFilesTest() {
        HomePage homePage = new HomePage(driver);
        DownloadFilesPage downloadFilesPage = homePage.openDownloadFilesPage();
        String currentUrl = downloadFilesPage.getCurrentUrl();
        WebElement title = downloadFilesPage.getTitle();
        String downloadFilesPageFormUrl = downloadFilesPage.getUrl();

        assertEquals(BASE_URL + downloadFilesPageFormUrl, currentUrl);
        assertEquals(DOWNLOAD_FILES_FORM_TITLE, title.getText());
    }
}
