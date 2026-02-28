package org.example.makovsky_v.pageObjects.chapter_4;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class ShadowDOMPage extends BasePage {
    private static final String SHADOW_DOM_FORM_URL = "shadow-dom.html";
    public static final String SHADOW_DOM_FORM_TITLE = "Shadow DOM";

    public ShadowDOMPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get shadow dom page url")
    public String getUrl() {
        return SHADOW_DOM_FORM_URL;
    }
}
