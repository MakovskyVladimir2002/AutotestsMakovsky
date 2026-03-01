package tests.seleniumV1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Chapter3LinksValidationTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        // Убраны лишние пробелы в конце URL
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        driver.manage().window().maximize();
        // Инициализация WebDriverWait в setup
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testWebFormLink() {
        // Клик по ссылке "Web form" в разделе Chapter 3
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'web-form')]"))).click();
        // Находим элемент заголовка <h1> на новой странице
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Web form']")));
        // Сравниваем полученный текст с ожидаемым
        assertEquals("Web form", webElement.getText(), "Текст заголовка H1 для Web form не соответствует ожидаемому");
    }

    @Test
    public void testNavigationLink() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'navigation1')]"))).click();
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Navigation example']")));
        assertEquals("Navigation example", webElement.getText(), "Текст заголовка H1 для Navigation не соответствует ожидаемому");
    }

    @Test
    public void testDropdownMenuLink() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'dropdown-menu')]"))).click();
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Dropdown menu']")));
        assertEquals("Dropdown menu", webElement.getText(), "Текст заголовка H1 для Dropdown menu не соответствует ожидаемому");
    }

    @Test
    public void testMouseOverLink() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'mouse-over')]"))).click();
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Mouse over']")));
        assertEquals("Mouse over", webElement.getText(), "Текст заголовка H1 для Mouse over не соответствует ожидаемому");
    }

    @Test
    public void testDragAndDropLink() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'drag-and-drop')]"))).click();
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Drag and drop']")));
        assertEquals("Drag and drop", webElement.getText(), "Текст заголовка H1 для Drag and drop не соответствует ожидаемому");
    }

    @Test
    public void testDrawInCanvasLink() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'draw-in-canvas')]"))).click();
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Drawing in canvas']")));
        assertEquals("Drawing in canvas", webElement.getText(), "Текст заголовка H1 для Draw in canvas не соответствует ожидаемому");
    }

    @Test
    public void testLoadingImagesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'loading-images')]"))).click();
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Loading images']")));
        assertEquals("Loading images", webElement.getText(), "Текст заголовка H1 для Loading images не соответствует ожидаемому");
    }

    @Test
    public void testSlowCalculatorLink() {
        // Используем XPath из вашего оригинального примера
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text() = 'Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'slow-calculator')]"))).click();
        // Находим элемент заголовка <h1> на новой странице
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Slow calculator']")));
        // Сравниваем полученный текст с ожидаемым
        assertEquals("Slow calculator", webElement.getText(), "Текст заголовка H1 для Slow calculator не соответствует ожидаемому");
    }
}
