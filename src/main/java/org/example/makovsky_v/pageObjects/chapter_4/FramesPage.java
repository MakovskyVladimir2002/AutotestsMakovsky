package org.example.makovsky_v.pageObjects.chapter_4;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FramesPage extends BasePage {
    private static final String FRAMES_FORM_URL = "frames.html";
    public static final String FRAMES_FORM_TITLE = "Frames";
    WebElement frame = driver.findElement(By.cssSelector("frame[name='frame-header']"));

    public FramesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get frames page url")
    public String getUrl() {
        return FRAMES_FORM_URL;
    }

    public void switchToFrame() {
        driver.switchTo().frame(frame);
    }
}
