package tests.SeleniumV1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Chapter 5: Browser-Specific Manipulation")
public class Chapter5BrowserSpecificManipulationTests {

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
    @DisplayName("Open Geolocation page")
    void openGeolocationPage() {
        clickLinkByText("Geolocation");
        assertUrlEquals(BASE_URL + "geolocation.html");
    }

    @Test
    @DisplayName("Open Notifications page")
    void openNotificationsPage() {
        clickLinkByText("Notifications");
        assertUrlEquals(BASE_URL + "notifications.html");
    }

    @Test
    @DisplayName("Open Get user media page")
    void openGetUserMediaPage() {
        clickLinkByText("Get user media");
        assertUrlEquals(BASE_URL + "get-user-media.html");
    }

    @Test
    @DisplayName("Open Multilanguage page")
    void openMultilanguagePage() {
        clickLinkByText("Multilanguage");
        assertUrlEquals(BASE_URL + "multilanguage.html");
    }

    @Test
    @DisplayName("Open Console logs page")
    void openConsoleLogsPage() {
        clickLinkByText("Console logs");
        assertUrlEquals(BASE_URL + "console-logs.html");
    }

    // --- Вспомогательные методы ---

    /**
     * Кликает по ссылке, содержащей указанный текст.
     * Использует явное ожидание, чтобы избежать проблем с таймингами.
     */
    private void clickLinkByText(String linkText) {
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(linkText))).click();
    }

    /**
     * Проверяет, что текущий URL совпадает с ожидаемым.
     * Выводит понятное сообщение об ошибке в случае несоответствия.
     */
    private void assertUrlEquals(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl,
                String.format("Expected URL: %s, but actual URL is: %s", expectedUrl, actualUrl));
    }
}