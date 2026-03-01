package tests.seleniumV1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class Chapter4Frame {

    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/frames.html";
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
    @DisplayName("TitleFrame")
    void titleTest() {
        String title = driver.getTitle();
        assertEquals("Hands-On Selenium WebDriver with Java", title);
    }

    @Test
    @DisplayName("Title2Frame")
    void titleTest2() {
        WebElement practiceSite = driver.findElement(By.tagName("h5"));
        assertEquals("Practice site", practiceSite.getText());
    }

    @Test
    @DisplayName("Frames")
    void frames() throws InterruptedException {
        Thread.sleep(2000);
        WebElement frames = driver.findElement(By.name("frame-header"));
        driver.switchTo().frame(frames);
        driver.findElement(By.xpath("div//p//b"));


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
