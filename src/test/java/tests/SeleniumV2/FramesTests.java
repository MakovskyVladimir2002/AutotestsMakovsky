package tests.SeleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_4.FramesPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_4.FramesPage.FRAMES_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FramesTests extends BaseTest {

    @Test
    @DisplayName("Check Frames page")
    void openFramesTest() {
        HomePage homePage = new HomePage(driver);
        FramesPage framesPage = homePage.openFramesPage();
        framesPage.switchToFrame();
        String currentUrl = framesPage.getCurrentUrl();
        WebElement title = framesPage.getTitle();
        String framesPageFormUrl = framesPage.getUrl();

        assertEquals(BASE_URL + framesPageFormUrl, currentUrl);
        assertEquals(FRAMES_FORM_TITLE, title.getText());
    }
}
