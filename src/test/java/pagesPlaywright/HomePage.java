package pagesPlaywright;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;


public class HomePage {
    private final Page page;
    private final Locator webFormButton;


    public HomePage(Page page) {
        this.page = page;
        this.webFormButton = page.getByText("Web form");
    }
    @Step("Open the main page via URL")
    public void open() {
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/");
    }
    @Step("Open web form page")
    public WebFormPage openWebFormPage() {
        webFormButton.click();
        return new WebFormPage(page);
    }

}