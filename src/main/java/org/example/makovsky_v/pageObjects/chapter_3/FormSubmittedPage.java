package org.example.makovsky_v.pageObjects.chapter_3;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormSubmittedPage extends BasePage {
    private static final String SUBMITTED_FORM_URL = "https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html?my-text=&my-password=&my-textarea=&my-readonly=Readonly+input&my-select=Open+this+select+menu&my-datalist=&my-file=&my-check=on&my-radio=on&my-colors=%23563d7c&my-date=&my-range=5&my-hidden=";
    public static final String SUBMITTED_FORM_TITLE = "Form submitted";
    public static final String SUBMITTED_FORM_TEXT = "Received!";

    WebElement submittedText = driver.findElement(By.className("lead"));

    public FormSubmittedPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get subpage url")
    public String getSubmittedFormUrl() {
        return SUBMITTED_FORM_URL;
    }

    @Step("Get submittedPage title")
    public WebElement getSubmittedText() {
        return submittedText;
    }
}
