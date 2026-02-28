package org.example.makovsky_v.pageObjects.chapter_9;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class ABTestingPage extends BasePage {
    private static final String AB_TESTING_FORM_URL = "ab-testing.html";
    public static final String AB_TESTING_FORM_TITLE = "A/B Testing";


    public ABTestingPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get AB testing page url")
    public String getUrl() {
        return AB_TESTING_FORM_URL;
    }
}
