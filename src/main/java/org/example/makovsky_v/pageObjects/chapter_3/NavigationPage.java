package org.example.makovsky_v.pageObjects.chapter_3;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class NavigationPage extends BasePage {
    private static final String NAV_FORM_URL = "navigation1.html";
    public static final String NAV_FORM_TITLE = "Navigation example";

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get navigation page url")
    public String getUrl() {
        return NAV_FORM_URL;
    }
}
