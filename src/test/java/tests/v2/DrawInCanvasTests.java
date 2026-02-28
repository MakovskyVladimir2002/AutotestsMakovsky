package tests.v2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_3.DrawingInCanvasPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_3.DrawingInCanvasPage.DRAWING_IN_CANVAS_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DrawInCanvasTests extends BaseTest {

    @Test
    @DisplayName("Check Draw in canvas page")
    void openDrawInCanvasTest() {
        HomePage homePage = new HomePage(driver);
        DrawingInCanvasPage drawingInCanvasPage = homePage.openDrawingInCanvasPage();
        String currentUrl = drawingInCanvasPage.getCurrentUrl();
        WebElement title = drawingInCanvasPage.getTitle();
        String drawingInCanvasPageFormUrl = drawingInCanvasPage.getUrl();

        assertEquals(BASE_URL + drawingInCanvasPageFormUrl, currentUrl);
        assertEquals(DRAWING_IN_CANVAS_FORM_TITLE, title.getText());
    }
}
