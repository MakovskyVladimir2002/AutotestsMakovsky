package org.example.makovsky_v.pageObjects.chapter_4;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class DialogBoxesPage extends BasePage {
    private static final String DIALOG_BOXES_FORM_URL = "dialog-boxes.html";
    public static final String DIALOG_BOXES_FORM_TITLE = "Dialog boxes";

    public DialogBoxesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get dialog boxes page url")
    public String getUrl() {
        return DIALOG_BOXES_FORM_URL;
    }
}
