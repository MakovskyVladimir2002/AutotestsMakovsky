package tests.SeleniumV1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Chapter 9: Third-Party Integrations")
public class Chapter9ThirdPartyIntegrationsTests {

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
    @DisplayName("Open Download Files page")
    void openDownloadFilesPage() {
        clickLinkByText("Download files");
        assertUrlEquals(BASE_URL + "download.html");
    }

    @Test
    @DisplayName("Open A/B Testing page")
    void openABTestingPage() {
        clickLinkByText("A/B Testing");
        assertUrlEquals(BASE_URL + "ab-testing.html");
    }

    @Test
    @DisplayName("Open Data Types page")
    void openDataTypesPage() {
        clickLinkByText("Data types");
        assertUrlEquals(BASE_URL + "data-types.html");
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
