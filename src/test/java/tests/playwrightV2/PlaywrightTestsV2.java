package tests.playwrightV2;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import pagesPlaywright.HomePage;
import pagesPlaywright.WebFormPage;

import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaywrightTestsV2 {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(true) // Измените на true в CI/CD
                        .setArgs(List.of("--no-sandbox", "--disable-dev-shm-usage")) //  Для стабильности в Docker
        );
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @AfterEach
    void closeContext() {
        String testName = "trace_" + System.currentTimeMillis() + ".zip"; // Уникальное имя
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get(testName)));
        context.close();
    }


    @Test
    void openSiteTest() {
        HomePage homePage = new HomePage(page);
        homePage.open();
        assertEquals("Hands-On Selenium WebDriver with Java", page.title());
    }

    @Test
    void openWebFormPageTest() {
        HomePage homePage = new HomePage(page);
        homePage.open();
        WebFormPage webFormPage = homePage.openWebFormPage();
        webFormPage.submit();
        assertThat(page.url()).contains("https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html");
    }

    @Test
    void checkboxTest() {
        HomePage homePage = new HomePage(page);
        homePage.open();
        WebFormPage webFormPage = homePage.openWebFormPage();
        boolean isChecked = webFormPage.isCheckboxChecked();
        assertTrue(isChecked);
    }

    @Test
    void dropdownSelectTest() {
        HomePage homePage = new HomePage(page);
        homePage.open();
        WebFormPage webFormPage = homePage.openWebFormPage();
        boolean isOptionSelected = webFormPage.isFirstOptionSelected("2");
        assertTrue(isOptionSelected);
    }

    @Test
    void sendTextTest() {
        HomePage homePage = new HomePage(page);
        homePage.open();
        WebFormPage webFormPage = homePage.openWebFormPage();
        webFormPage.sendText();
        String textInput = webFormPage.getTextInput();
        assertEquals("my text",textInput);
    }

}
