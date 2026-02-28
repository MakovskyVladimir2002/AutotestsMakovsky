package org.example.makovsky_v.pageObjects.chapter_4;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class InfiniteScrollPage extends BasePage {
    private static final String INFINITE_SCROLL_FORM_URL = "infinite-scroll.html";
    public static final String INFINITE_SCROLL_FORM_TITLE = "Infinite scroll";

    public InfiniteScrollPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get infinite scroll page url")
    public String getUrl() {
        return INFINITE_SCROLL_FORM_URL;
    }
}
