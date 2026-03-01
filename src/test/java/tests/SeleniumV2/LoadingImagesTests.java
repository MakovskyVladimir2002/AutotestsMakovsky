package tests.SeleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_3.LoadingImagesPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_3.LoadingImagesPage.LOADING_IMAGES_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoadingImagesTests extends BaseTest {

    @Test
    @DisplayName("Check Loading images page")
    void openLoadingImagesTest() {
        HomePage homePage = new HomePage(driver);
        LoadingImagesPage loadingImagesPage  = homePage.openLoadingImagesPage() ;
        String currentUrl = loadingImagesPage.getCurrentUrl();
        WebElement title = loadingImagesPage.getTitle();
        String loadingImagesPageFormUrl = loadingImagesPage.getUrl();

        assertEquals(BASE_URL + loadingImagesPageFormUrl, currentUrl);
        assertEquals(LOADING_IMAGES_FORM_TITLE, title.getText());
    }
}
