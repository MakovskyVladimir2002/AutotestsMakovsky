package tests.SeleniumV2;

import org.example.makovsky_v.pageObjects.HomePage;
import org.example.makovsky_v.pageObjects.chapter_3.DropdownMenuPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.example.makovsky_v.pageObjects.chapter_3.DropdownMenuPage.DROPDOWN_FORM_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DropdownMenuTests extends BaseTest {

    @Test
    @DisplayName("Check Dropdown menu page")
    void openDropdownMenuTest() {
        HomePage homePage = new HomePage(driver);
        DropdownMenuPage dropdownMenuPage = homePage.openDropdownMenuPage();
        String currentUrl = dropdownMenuPage.getCurrentUrl();
        WebElement title = dropdownMenuPage.getTitle();
        String dropdownMenuFormUrl = dropdownMenuPage.getUrl();

        assertEquals(BASE_URL + dropdownMenuFormUrl, currentUrl);
        assertEquals(DROPDOWN_FORM_TITLE, title.getText());
    }
}
