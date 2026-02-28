package org.example.makovsky_v.pageObjects.chapter_5;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class MultilanguagePage extends BasePage {
    private static final String MULTILANGUAGE_FORM_URL = "multilanguage.html";
    public static final String MULTILANGUAGE_FORM_TITLE = "";

    public MultilanguagePage(WebDriver driver) {
        super(driver);
    }

    @Step("Get Multilanguage page url")
    public String getUrl() {
        return MULTILANGUAGE_FORM_URL;
    }
}
