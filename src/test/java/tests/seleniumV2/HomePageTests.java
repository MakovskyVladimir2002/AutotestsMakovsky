package tests.seleniumV2;

import io.qameta.allure.Feature;
import org.example.makovsky_v.pageObjects.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("POM")
class HomePageTests extends BaseTest {

    @Test
    @DisplayName("Check HomePage page")
    void openHomePageTest() {
        HomePage homePage = new HomePage(driver);

        String actualTitle = homePage.getWebTitle();

        assertEquals("Hands-On Selenium WebDriver with Java", actualTitle);
    }
}
