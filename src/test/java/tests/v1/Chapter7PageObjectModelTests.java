package tests.v1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Chapter 7: The Page Object Model")
public class Chapter7PageObjectModelTests {

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
    @DisplayName("Open Login Form page")
    void openLoginFormPage() {
        clickLinkByText("Login form");
        assertUrlEquals(BASE_URL + "login-form.html");
    }

    @Test
    @DisplayName("Open Slow Login page")
    void openSlowLoginPage() {
        clickLinkByText("Slow login");
        assertUrlEquals(BASE_URL + "login-slow.html");
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
