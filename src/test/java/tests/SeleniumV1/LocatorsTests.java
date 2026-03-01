package tests.SeleniumV1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LocatorsTests {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    static void setup() {
        driver = new ChromeDriver();
        // Убраны лишние пробелы в конце URL
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        driver.manage().window().maximize();
        // Инициализация WebDriverWait в setup
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    void webFormLocatorLinkText(){
        driver.findElement(By.linkText("Web form")).click();
        String url_actual = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/web-form.html", url_actual);
    }
    @Test
    @Order(2)
    void webFormLocator(){
//        driver.findElement(By.cssSelector("input[name='my-file']")).sendKeys("C:/Users/1/Downloads/merry-christmas-background-with-realistic-christmas-decoration_1361-3516.png");
        driver.findElement(By.name("my-text")).sendKeys("Привет, как дела");
        driver.findElement(By.name("my-password")).sendKeys("12345");
        driver.findElement(By.name("my-textarea")).sendKeys("TextArea228337");
        driver.findElement(By.xpath("//select[@name='my-select']//option[text()='One']")).click();
        driver.findElement(By.xpath("//select[@name='my-select']//option[@value='1']")).click();
        driver.findElement(By.xpath("//select[@name='my-select']//option[text()='Two']")).click();
        driver.findElement(By.xpath("//select[@name='my-select']//option[@value='2']")).click();
        driver.findElement(By.xpath("//select[@name='my-select']//option[text()='Three']")).click();
        driver.findElement(By.xpath("//select[@name='my-select']//option[@value='3']")).click();

    }
    @Test
    @Order(3)
    void FileLocator(){
//        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        driver.findElement(By.cssSelector("input[name='my-file']")).sendKeys("C:/Users/1/Downloads/merry-christmas-background-with-realistic-christmas-decoration_1361-3516.png");
    }
    @Test
    @Order(4)
    void CheckboxLocator(){
//        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        driver.findElement(By.xpath("//input[@id='my-check-2']")).click();
    }
    @Test
    @Order(5)
    void RadioLocator(){
//        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        driver.findElement(By.xpath("//input[@id='my-radio-1']")).click();
    }

    @Test
    @Order(6)
    void testRangeSliderWithValue() {
        // Находим элемент
        WebElement rangeSlider = driver.findElement(By.name("my-range"));

        // Проверяем начальное значение
        String initialValue = rangeSlider.getAttribute("value");
        assertThat(initialValue).as("Начальное значение должно быть 5").isEqualTo("5");

        // Устанавливаем значение через JavaScript (через Java-обертку)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input'));" +
                        "arguments[0].dispatchEvent(new Event('change'));",
                rangeSlider,
                "8"
        );

        // Ждём, пока значение установится
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(d -> "8".equals(rangeSlider.getAttribute("value")));

        // Проверяем финальное значение
        String finalValue = rangeSlider.getAttribute("value");
        assertThat(finalValue).as("Значение должно быть 8").isEqualTo("8");
    }

    @Test
    @Order(7)
    void testDatePickerSelectionWithAllChecks() {

        // Находим input, который вызывает календарь
        WebElement dateInput = driver.findElement(By.name("my-date"));

        // 1. Проверяем, что поле даты существует и доступно
        assertThat(dateInput)
                .as("Поле ввода даты должно существовать и быть доступно")
                .isNotNull()
                .matches(we -> we.isDisplayed() && we.isEnabled());

        // 2. Проверяем, что поле пустое
        assertThat(dateInput.getAttribute("value"))
                .as("Поле ввода даты должно быть пустым")
                .isEmpty();

        // 3. Кликаем в поле, чтобы открыть календарь
        dateInput.click();

        // 4. Ждём появления календаря
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement datePicker = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("datepicker-days"))
        );

        // 5. Проверяем, что календарь открылся
        assertThat(datePicker.isDisplayed())
                .as("Календарь должен быть видим")
                .isTrue();

        // 6. Проверяем, что отображается текущий месяц (февраль 2026)
        String calendarTitle = driver.findElement(By.className("datepicker-switch")).getText();
        assertThat(calendarTitle)
                .as("Календарь должен отображать January 2026")
                .isEqualTo("February 2026");

        // 7. Выбираем **первый доступный день** в календаре (не конкретную дату)
        // Это делает тест независимым от дня недели, високосных годов и т.д.
        WebElement firstAvailableDay = driver.findElement(
                By.xpath("//td[@class='day' and not(contains(@class, 'old')) and not(contains(@class, 'new'))][1]")
        );

        String targetDayText = firstAvailableDay.getText();
        assertThat(firstAvailableDay.isDisplayed() && firstAvailableDay.isEnabled())
                .as("День '%s' должен быть доступен для выбора", targetDayText)
                .isTrue();

        // 8. Кликаем по выбранному дню
        firstAvailableDay.click();

        // 9. Кликаем вне календаря, чтобы закрыть его
        driver.findElement(By.tagName("body")).click();

        // 10. Ждём, пока значение в поле обновится
        wait.until(ExpectedConditions.not(
                ExpectedConditions.attributeToBe(dateInput, "value", "")
        ));

        // 11. Проверяем, что дата установлена в поле
        String selectedDate = dateInput.getAttribute("value");

        // 12. Проверяем формат даты (MM/DD/YYYY)
        assertThat(selectedDate)
                .as("Дата должна быть в формате MM/DD/YYYY")
                .matches("\\d{2}/\\d{2}/\\d{4}");

    }

    @Test
    @Order(8) // Убедитесь, что это после всех заполнений полей
    void ButtonLocator(){
        // Находим кнопку Submit
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));

        // Проверяем, что кнопка существует и кликабельна
        assertThat(submitButton.isDisplayed()).as("Кнопка Submit должна быть видима").isTrue();
        assertThat(submitButton.isEnabled()).as("Кнопка Submit должна быть активна").isTrue();

        // Прокручиваем к кнопке, чтобы убедиться, что она в поле зрения
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

        // Кликаем по кнопке
        submitButton.click();

        // Ждём перехода на страницу submitted-form.html
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("submitted-form.html"));

        // Проверяем, что URL стал правильным
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl)
                .as("URL должен содержать submitted-form.html")
                .contains("submitted-form.html");
    }
}
