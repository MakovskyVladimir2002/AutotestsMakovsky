package tests.v2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_3.DragAndDropPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_3.DragAndDropPage.DRAG_AND_DROP_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DragAndDropTests extends BaseTest {

    @Test
    @DisplayName("Check Drag and Drop page")
    void openDragAndDropTest() {
        HomePage homePage = new HomePage(driver);
        DragAndDropPage dragAndDropPage = homePage.openDragAndDropPage();
        String currentUrl = dragAndDropPage.getCurrentUrl();
        WebElement title = dragAndDropPage.getTitle();
        String dragAndDropPageFormUrl = dragAndDropPage.getUrl();

        assertEquals(BASE_URL + dragAndDropPageFormUrl, currentUrl);
        assertEquals(DRAG_AND_DROP_FORM_TITLE, title.getText());
    }
}
