import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Nested
@DisplayName("Chapter 3: WebDriver Fundamentals")
class Chapter3WebDriverFundamentalsTests {
    WebDriver driver;

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void openHomePageTest(){
        driver.manage().window().maximize();
        String title = driver.getTitle();

        assertEquals("Hands-On Selenium WebDriver with Java", title);
    }
    @DisplayName("Web-form")
    @Test
    void openClickWebForm() throws InterruptedException {
        String webFormUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.findElement(By.xpath("/html/body/main/div/div[4]/div[1]/div/div/a[1]")).click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(webFormUrl, currentUrl);
        // Находим элемент заголовка
        WebElement headerElement = driver.findElement(By.cssSelector("h1.display-6"));

        // Получаем *текст* элемента, используя метод getText()
        String title = headerElement.getText();

        // Сравниваем полученный текст с ожидаемым. Обратите внимание на регистр: "Web form"
        assertEquals("Web form", title, "Текст заголовка не соответствует ожидаемому");

    }
    @DisplayName("Navigation")
    @Test
    void openClickNavifation() throws InterruptedException {
        String webFormUrl = "https://bonigarcia.dev/selenium-webdriver-java/navigation1.html";
        driver.findElement(By.xpath("//a[@href='navigation1.html']")).click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(webFormUrl, currentUrl);
    }
    @DisplayName("Dropdown-menu")
    @Test
    void openClickDropDownMenu() throws InterruptedException {
        String webFormUrl = "https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html";
        driver.findElement(By.xpath("//a[@href='dropdown-menu.html']")).click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(webFormUrl, currentUrl);
    }
    @DisplayName("Mouse over")
    @Test
    void openMouseOverPage() {
        String expectedUrl = "https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html";
        driver.findElement(By.xpath("//a[@href='mouse-over.html']")).click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl);
    }
    @DisplayName("Drag and drop")
    @Test
    void openDragAndDropPage() {
        String expectedUrl = "https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html";
        driver.findElement(By.xpath("//a[@href='drag-and-drop.html']")).click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl);
    }
    @DisplayName("Draw in canvas")
    @Test
    void openDrawInCanvasPage() {
        String expectedUrl = "https://bonigarcia.dev/selenium-webdriver-java/draw-in-canvas.html";
        driver.findElement(By.xpath("//a[@href='draw-in-canvas.html']")).click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl);
    }
    @DisplayName("Loading images")
    @Test
    void openLoadingImagesPage() {
        String expectedUrl = "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html";
        driver.findElement(By.xpath("//a[@href='loading-images.html']")).click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl);
    }
    @DisplayName("Slow calculator")
    @Test
    void openSlowCalculatorPage() {
        String expectedUrl = "https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html";
        driver.findElement(By.xpath("//a[@href='slow-calculator.html']")).click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl);
    }

}
