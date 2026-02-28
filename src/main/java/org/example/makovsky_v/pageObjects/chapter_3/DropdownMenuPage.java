package org.example.makovsky_v.pageObjects.chapter_3;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class DropdownMenuPage extends BasePage {
    private static final String DROPDOWN_FORM_URL = "dropdown-menu.html";
    public static final String DROPDOWN_FORM_TITLE = "Dropdown menu";

    public DropdownMenuPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get DropdownMenuPage url")
    public String getUrl() {
        return DROPDOWN_FORM_URL;
    }
}
