package tests.playwrightV1;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.junit.jupiter.api.*;
import playwright.HomePage;
import playwright.WebFormPage;

import java.io.IOException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import steps.AllureSteps;

public class PlaywrightTestsV1 {

    static Playwright playwright;
    static Browser browser;

    // Новый экземпляр для каждого тестового метода.
    BrowserContext context;
    Page page;

    AllureSteps allureSteps = new AllureSteps();

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
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
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
        context.close();
    }

    @Test
    void shouldClickButton() {
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("File input")).setInputFiles(Paths.get("C:\\Users\\1\\Downloads\\5445344554.mp4"));
        page.getByText("Text input").fill("Тест1");
        page.getByText("Password").fill("12345");
        page.getByText("Textarea").pressSequentially("Hello World!");
        page.getByText("Disabled input").isDisabled();
        page.getByText("Readonly input").isVisible();
        page.getByText("Checked checkbox").check();
        page.getByText("Default checkbox").check();
        page.getByText("Checked radio").check();page.getByText("Dropdown (select)").selectOption("One");
        page.evaluate("""
    () => {
        const input = document.querySelector('input[type="file"]');
        if (input.files.length > 0) {
            // Найдем элемент, который отображает имя файла
            // На вашей странице это может быть элемент с классом или текстом
            const fileDisplay = input.nextElementSibling;
            if (fileDisplay) {
                fileDisplay.textContent = input.files[0].name;
            }
        }
    }
""");
        page.getByText("Submit").click();
        assertThat(page).hasURL("https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html?my-text=%D0%A2%D0%B5%D1%81%D1%821&my-password=12345&my-textarea=Hello+World%21&my-readonly=Readonly+input&my-select=1&my-datalist=&my-file=5445344554.mp4&my-check=on&my-check=on&my-radio=on&my-colors=%23563d7c&my-date=&my-range=5&my-hidden=");
    }

    @Test
    void successfulLoginTest() {
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");

        Locator subTitle = page.locator(".display-6");
        Locator loginInput = page.locator("#username");
        Locator passwordInput = page.locator("#password");
        Locator submitButton = page.locator("xpath=//button[@type='submit']");

        loginInput.fill("user");
        passwordInput.fill("user");
        String textBeforeClick = subTitle.innerText();
        submitButton.click();

        assertThat(textBeforeClick).isEqualTo("Login form");
        Locator successMessage = page.locator("#success");
        assertThat(successMessage.isVisible()).isTrue();
    }

    @Test
    void openSiteTest() {
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/");
        assertEquals("Hands-On Selenium WebDriver with Java", page.title());
    }

    @Test
    void openForm() {
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/");
        Locator webFormButton = page.locator("xpath=//div[@class = 'card-body']")
                .locator("xpath=.//a[contains(@class, 'btn')]")
                .first();
        webFormButton.click();
        Locator actualH1 = page.locator("css=.display-6");
        assertEquals("Web form", actualH1.innerText());
    }

    @Test
    @DisplayName("Check screenshot attachment")
    void infiniteScrollTestWithAttach() throws InterruptedException, IOException {
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html");

        page.waitForSelector("xpath=//p");
        int initParagraphsNumber = page.locator("xpath=//p").all().size();

        Locator lastParagraph = page.locator(String.format("xpath=//p[%s]", initParagraphsNumber));
        lastParagraph.evaluate("e => e.scrollIntoView()");

        page.waitForFunction("() => document.querySelectorAll('p').length > " + initParagraphsNumber);
        Thread.sleep(3000);
        allureSteps.captureScreenshotPlaywright(page);
        allureSteps.captureScreenshotPlaywrightSpoiler(page);
    }

    @Test
    void loadingImagesDefaultWaitTest() {
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        Locator image = page.locator("#landscape");

        assertThat(image.getAttribute("src")).contains("landscape");
    }

    @Test
    void loadingImagesWithExplicitTimeoutWaitTest() {
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        ElementHandle image = page.waitForSelector("#landscape", new Page.WaitForSelectorOptions().setTimeout(10_000));

        assertThat(image.getAttribute("src")).contains("landscape");
    }

    @Test
    void loadingImagesWithCustomTimeoutWaitTest() {
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        int expectedCount = 4;
        Locator images = page.locator("img");
        while (images.count() != expectedCount) {
            page.waitForTimeout(1000); // Ожидание 1 секунду
        }

        PlaywrightAssertions.assertThat(images).hasCount(expectedCount);
    }

    @Test
    void pageObjectTest() {
        HomePage homePage = new HomePage(page);
        homePage.open();
        WebFormPage webFormPage = homePage.openWebFormPage();
        webFormPage.submit();

        assertThat(page.url()).contains("https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html");
    }
}
