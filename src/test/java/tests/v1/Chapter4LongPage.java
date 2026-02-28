package tests.v1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


public class Chapter4LongPage {

    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/long-page.html";
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
    @DisplayName("TitleLongPage")
    void titleTest() {
        String title = driver.getTitle();
        assertEquals("Hands-On Selenium WebDriver with Java", title);
    }
    @Test
    @DisplayName("Title2LongPage")
    void titleTest2() {
        WebElement practiceSite = driver.findElement(By.tagName("h5"));
        assertEquals("Practice site", practiceSite.getText());
    }
    @Test
    @DisplayName("This is a long page")
    void thisIsLongPage() {
        WebElement thisIsLongPage = driver.findElement(By.className("display-6"));
        assertEquals("This is a long page", thisIsLongPage.getText());
    }
    @Test
    @DisplayName("FirstApartLoremIpsum")
    void firstApartLoremIpsum() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ждать до 10 секунд

        // Ждём, пока элемент станет видимым
        WebElement firstParagraph = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']/p/b"))
        );

        assertThat(firstParagraph.getText()).contains("sum");
    }
    @Test
    @DisplayName("FirstApart - P")
    void firstApart() {
        // Ждём, пока элемент станет видимым
        WebElement firstParagraph = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']/p[1]"))
        );

        assertThat(firstParagraph.getText()).contains("aenean potenti");
    }
}
