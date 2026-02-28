package tests.v2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_7.LoginFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_7.LoginFormPage.LOGIN_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginFormTests extends BaseTest {

    @Test
    @DisplayName("Check Login form page")
    void openLoginFormTest() {
        HomePage homePage = new HomePage(driver);
        LoginFormPage loginFormPage = homePage.openLoginFormPage();
        String currentUrl = loginFormPage.getCurrentUrl();
        WebElement title = loginFormPage.getTitle();
        String loginFormPageFormUrl = loginFormPage.getUrl();

        assertEquals(BASE_URL + loginFormPageFormUrl, currentUrl);
        assertEquals(LOGIN_FORM_TITLE, title.getText());
    }
}
