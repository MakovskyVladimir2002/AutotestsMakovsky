package org.example.makovsky_v.pageObjects.chapter_4;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class WebStoragePage extends BasePage {
    private static final String WEB_STORAGE_FORM_URL = "web-storage.html";
    public static final String WEB_STORAGE_FORM_TITLE = "Web storage";

    public WebStoragePage(WebDriver driver) {
        super(driver);
    }

    @Step("Get web storage page url")
    public String getUrl() {
        return WEB_STORAGE_FORM_URL;
    }
}
