package tests.SeleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_9.ABTestingPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_9.ABTestingPage.AB_TESTING_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ABTestingTests extends BaseTest {
    @Test
    @DisplayName("Check A/B Testing page")
    void openABTestingTest() {
        HomePage homePage = new HomePage(driver);
        ABTestingPage abTestingPage = homePage.openABTestingPage();
        String currentUrl = abTestingPage.getCurrentUrl();
        WebElement title = abTestingPage.getTitle();
        String abTestingPageFormUrl = abTestingPage.getUrl();

        assertEquals(BASE_URL + abTestingPageFormUrl, currentUrl);
        assertEquals(AB_TESTING_FORM_TITLE, title.getText());
    }
}
