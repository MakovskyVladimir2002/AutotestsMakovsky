package org.example.makovsky_v.pageObjects.chapter_8;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class RandomCalculatorPage extends BasePage {
    private static final String RANDOM_CALCULATOR_FORM_URL = "random-calculator.html";
    public static final String RANDOM_CALCULATOR_FORM_TITLE = "Random calculator";

    public RandomCalculatorPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get random calculator page url")
    public String getUrl() {
        return RANDOM_CALCULATOR_FORM_URL;
    }
}
