package org.example.makovsky_v.pageObjects.chapter_3;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class DragAndDropPage extends BasePage {
    private static final String DRAG_AND_DROP_FORM_URL = "drag-and-drop.html";
    public static final String DRAG_AND_DROP_FORM_TITLE = "Drag and drop";

    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get drag and drop page url")
    public String getUrl() {
        return DRAG_AND_DROP_FORM_URL;
    }
}
