package org.example.makovsky_v.pageObjects.chapter_4;

import org.example.makovsky_v.pageObjects.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LongPage extends BasePage {
    private static final String LONG_FORM_URL = "long-page.html";
    public static final String LONG_FORM_TITLE = "This is a long page";

    public LongPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get slow calculator page url")
    public String getUrl() {
        return LONG_FORM_URL;
    }
}
