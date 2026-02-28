package tests.v1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Chapter 4: Browser-Agnostic Features")
public class Chapter4BrowserAgnosticFeaturesTests {

    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Open Long Page")
    void openLongPage() {
        clickLinkByText("Long page");
        assertUrlEquals(BASE_URL + "long-page.html");
    }

    @Test
    @DisplayName("Open Infinite Scroll")
    void openInfiniteScroll() {
        clickLinkByText("Infinite scroll");
        assertUrlEquals(BASE_URL + "infinite-scroll.html");
    }

    @Test
    @DisplayName("Open Shadow DOM")
    void openShadowDom() {
        clickLinkByText("Shadow DOM");
        assertUrlEquals(BASE_URL + "shadow-dom.html");
    }

    @Test
    @DisplayName("Open Cookies")
    void openCookies() {
        clickLinkByText("Cookies");
        assertUrlEquals(BASE_URL + "cookies.html");
    }

    @Test
    @DisplayName("Open Frames")
    void openFrames() {
        clickLinkByText("Frames");
        assertUrlEquals(BASE_URL + "frames.html");
    }

    @Test
    @DisplayName("Open IFrames")
    void openIFrames() {
        clickLinkByText("IFrames");
        assertUrlEquals(BASE_URL + "iframes.html");
    }

    @Test
    @DisplayName("Open Dialog Boxes")
    void openDialogBoxes() {
        clickLinkByText("Dialog boxes");
        assertUrlEquals(BASE_URL + "dialog-boxes.html");
    }

    @Test
    @DisplayName("Open Web Storage")
    void openWebStorage() {
        clickLinkByText("Web storage");
        assertUrlEquals(BASE_URL + "web-storage.html");
    }

    // --- Вспомогательные методы ---

    private void clickLinkByText(String linkText) {
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(linkText))).click();
    }

    private void assertUrlEquals(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl,
                String.format("Expected URL: %s, but got: %s", expectedUrl, actualUrl));
    }
}
