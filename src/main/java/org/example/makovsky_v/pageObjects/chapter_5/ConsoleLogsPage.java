package org.example.makovsky_v.pageObjects.chapter_5;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;

public class ConsoleLogsPage extends BasePage {
    private static final String CONSOLE_LOGS_FORM_URL = "console-logs.html";
    public static final String CONSOLE_LOGS_FORM_TITLE = "Console logs";

    public ConsoleLogsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get console logs page url")
    public String getUrl() {
        return CONSOLE_LOGS_FORM_URL;
    }
}
