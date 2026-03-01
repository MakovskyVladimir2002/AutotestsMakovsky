package tests.seleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_4.DialogBoxesPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_4.DialogBoxesPage.DIALOG_BOXES_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DialogBoxesTests extends BaseTest {

    @Test
    @DisplayName("Check Dialog Boxes page")
    void openDialogBoxesTest() {
        HomePage homePage = new HomePage(driver);
        DialogBoxesPage dialogBoxesPage = homePage.openDialogBoxesPage();
        String currentUrl = dialogBoxesPage.getCurrentUrl();
        WebElement title = dialogBoxesPage.getTitle();
        String dialogBoxesPageFormUrl = dialogBoxesPage.getUrl();

        assertEquals(BASE_URL + dialogBoxesPageFormUrl, currentUrl);
        assertEquals(DIALOG_BOXES_FORM_TITLE, title.getText());
    }
}
