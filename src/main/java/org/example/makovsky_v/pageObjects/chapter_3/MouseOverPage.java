package org.example.makovsky_v.pageObjects.chapter_3;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class MouseOverPage extends BasePage {
    private static final String MOUSE_OVER_FORM_URL = "mouse-over.html";
    public static final String MOUSE_OVER_FORM_TITLE = "Mouse over";

    public MouseOverPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get mouse over page url")
    public String getUrl() {
        return MOUSE_OVER_FORM_URL;
    }
}
