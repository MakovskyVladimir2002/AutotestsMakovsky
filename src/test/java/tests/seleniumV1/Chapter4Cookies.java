package tests.seleniumV1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class Chapter4Cookies {

    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/cookies.html";
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
    @DisplayName("TitleCookies")
    void titleTest() {
        String title = driver.getTitle();
        assertEquals("Hands-On Selenium WebDriver with Java", title);
    }

    @Test
    @DisplayName("Title2Cookies")
    void titleTest2() {
        WebElement practiceSite = driver.findElement(By.tagName("h5"));
        assertEquals("Practice site", practiceSite.getText());
    }

    @Test
    @DisplayName("Cookies")
    void cookies() {
        WebElement shadowDom = driver.findElement(By.className("display-6"));
        assertEquals("Cookies", shadowDom.getText());

    }

    @Test
    void displayCookies() {
        // Добавляем куку
        driver.manage().addCookie(new Cookie("key", "value"));
        Cookie cookieValue = driver.manage().getCookieNamed("key");
        assertEquals("value", cookieValue.getValue());

//        // Удаляем куку
//        driver.manage().deleteCookie(cookieValue);

        // Проверяем, что кука УДАЛЕНА ИЗ БРАУЗЕРА
        Cookie deletedCookie = driver.manage().getCookieNamed("key");
        assertNull(deletedCookie, "Кука 'key' не удалена из браузера!");
    }
    }
