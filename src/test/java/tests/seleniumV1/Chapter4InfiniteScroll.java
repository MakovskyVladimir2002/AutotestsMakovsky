package tests.seleniumV1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Chapter4InfiniteScroll {

    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html";
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
    @DisplayName("TitleInfiniteScrollPage")
    void titleTest() {
        String title = driver.getTitle();
        assertEquals("Hands-On Selenium WebDriver with Java", title);
    }
    @Test
    @DisplayName("Title2InfiniteScrollPage")
    void titleTest2() {
        WebElement practiceSite = driver.findElement(By.tagName("h5"));
        assertEquals("Practice site", practiceSite.getText());
    }
    @Test
    @DisplayName("Infinite scroll")
    void infititeScroll() {
        WebElement infititeScroll = driver.findElement(By.className("display-6"));
        assertEquals("Infinite scroll", infititeScroll.getText());
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

// ... другие импорты

    @Test
    @DisplayName("FirstApart - P")
    void searchAfterTab() throws InterruptedException {
        // 1. ИСПРАВЛЕНО: Убрана лишняя скобка в XPath
        Thread.sleep(3000);
        WebElement firstParagraph = driver.findElement(By.xpath("//div[@id='content']/p[100]"));

        // 2. Прокрутка с помощью JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstParagraph);

        Thread.sleep(2000);
    }
    @Test
    void infiniteScroll () throws IOException, InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By pLocator = By.tagName("p");
// Ждем загрузки начальных параграфов
        List<WebElement> paragraphs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, 0));
        int initialCount = paragraphs.size();

// ПЕРВЫЙ СКРОЛЛ
// Прокручиваем к последнему параграфу
        WebElement lastParagraph = driver.findElement(By.xpath(String.format("//p[%d]", initialCount)));
        js.executeScript("arguments[0].scrollIntoView();", lastParagraph);

// Ждем подгрузки новых параграфов
// Указываем, что должно появиться больше параграфов, чем было изначально
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, initialCount));

// ВТОРОЙ СКРОЛЛ
// Получаем обновленное количество параграфов
        paragraphs = driver.findElements(pLocator);
        int newCount = paragraphs.size();
        System.out.println(newCount);

// Находим новый последний параграф и прокручиваем к нему
        lastParagraph = driver.findElement(By.xpath(String.format("//p[%d]", newCount)));
        js.executeScript("arguments[0].scrollIntoView();", lastParagraph);

// При необходимости можно снова подождать подгрузки новых параграфов
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, newCount));

//        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, initParagraphsNumber));
//        Thread.sleep(3000);
//        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(scrFile, new File("./image.png"));
    }
}
