package org.example.makovsky_v.pageObjects.chapter_3;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class LoadingImagesPage extends BasePage {
    private static final String LOADING_IMAGES_FORM_URL = "loading-images.html";
    public static final String LOADING_IMAGES_FORM_TITLE = "Loading images";

    public LoadingImagesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get loading images page url")
    public String getUrl() {
        return LOADING_IMAGES_FORM_URL;
    }
}
