package tests.SeleniumV1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class Chapter4AnywayLocators {
    WebDriver driver;
    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
    }
    @AfterEach
    void close(){
        if (driver != null) {
            driver.quit(); // Закрытие всех окон сессии драйвера
        }
    }
    @Test
    void textInput() {
        WebElement text = driver.findElement(By.id("my-text-id"));
        text.sendKeys("Привет, я мама");
        driver.findElement(By.xpath("//button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> webDriver.getCurrentUrl().contains("submitted-form.html"));

        String url = driver.getCurrentUrl();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html?my-text=%D0%9F%D1%80%D0%B8%D0%B2%D0%B5%D1%82%2C+%D1%8F+%D0%BC%D0%B0%D0%BC%D0%B0&my-password=&my-textarea=&my-readonly=Readonly+input&my-select=Open+this+select+menu&my-datalist=&my-file=&my-check=on&my-radio=on&my-colors=%23563d7c&my-date=&my-range=5&my-hidden=", url);
    }
    @Test
    void password() {
        WebElement password = driver.findElement(By.name("my-password"));
        password.sendKeys("12345");
        driver.findElement(By.xpath("//button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> webDriver.getCurrentUrl().contains("submitted-form.html"));

        String url = driver.getCurrentUrl();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html?my-text=&my-password=12345&my-textarea=&my-readonly=Readonly+input&my-select=Open+this+select+menu&my-datalist=&my-file=&my-check=on&my-radio=on&my-colors=%23563d7c&my-date=&my-range=5&my-hidden=", url);
    }
    @Test
    void textArea() {
        WebElement textArea = driver.findElement(By.name("my-textarea"));
        textArea.sendKeys("Спартак Москва - лучший клуб в России");
        driver.findElement(By.xpath("//button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> webDriver.getCurrentUrl().contains("submitted-form.html"));

        String url = driver.getCurrentUrl();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html?my-text=&my-password=&my-textarea=%D0%A1%D0%BF%D0%B0%D1%80%D1%82%D0%B0%D0%BA+%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0+-+%D0%BB%D1%83%D1%87%D1%88%D0%B8%D0%B9+%D0%BA%D0%BB%D1%83%D0%B1+%D0%B2+%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D0%B8&my-readonly=Readonly+input&my-select=Open+this+select+menu&my-datalist=&my-file=&my-check=on&my-radio=on&my-colors=%23563d7c&my-date=&my-range=5&my-hidden=", url);
    }
    @Test
    void disabledInput() {
        WebElement disabledInput = driver.findElement(By.name("my-disabled"));
        disabledInput.isDisplayed();
        assertThrows(ElementNotInteractableException.class,
                () -> disabledInput.sendKeys("test"), // Попытка ввести текст
                "Отправка клавиш в отключенное поле должна вызвать ElementNotInteractableException"
        );
    }
    @Test
    void readonlyInput() {
        WebElement readonlyInput = driver.findElement(By.name("my-readonly"));
        readonlyInput.isEnabled();
        readonlyInput.isDisplayed();
    }
    @Test
    void dropdownSelect() {
        WebElement dropdownSelect = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdownSelect);

        select.selectByValue("1");
        List<WebElement> options = select.getOptions();
        assertThat(options) // Проверка количества опций
                .as("Общее количество доступных опций")
                .hasSize(4);
        List<WebElement> selectedOptions = select.getAllSelectedOptions(); // Получение выбранных опций
        assertThat(selectedOptions) // Проверка, что выбран только один элемент
                .as("Количество текущих выбранных опций")
                .hasSize(1);
    }
    @Test
    void fileInput() {
        String path = "C:\\Users\\1\\Downloads\\файл1.jpg";
        WebElement input = driver.findElement(By.name("my-file"));
        input.sendKeys(path);

        driver.findElement(By.xpath("//button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> webDriver.getCurrentUrl().contains("submitted-form.html"));

        String url = driver.getCurrentUrl();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html?my-text=&my-password=&my-textarea=&my-readonly=Readonly+input&my-select=Open+this+select+menu&my-datalist=&my-file=%D1%84%D0%B0%D0%B9%D0%BB1.jpg&my-check=on&my-radio=on&my-colors=%23563d7c&my-date=&my-range=5&my-hidden=", url);
    }
    @Test
    void colorPicker() {
        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        String color = colorPicker.getAttribute("value");
        System.out.println(color);
    }
    @Test
    void dataPickerHandle() {
        WebElement dataPicker = driver.findElement(By.name("my-date"));
        dataPicker.sendKeys("24.02.2025");
        driver.findElement(By.xpath("//button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> webDriver.getCurrentUrl().contains("submitted-form.html"));

        String url = driver.getCurrentUrl();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html?my-text=&my-password=&my-textarea=&my-readonly=Readonly+input&my-select=Open+this+select+menu&my-datalist=&my-file=&my-check=on&my-radio=on&my-colors=%23563d7c&my-date=12%2F02%2F2025&my-range=5&my-hidden=", url);
    }
    @Test
    void dataPickerClickOptions() {
        driver.findElement(By.name("my-date")).click();
//        WebElement dataPicker = driver.findElement(By.xpath("//td[text() = '8'"));
         driver.findElement(By.xpath("//td[text()='17']")).click();
        driver.findElement(By.xpath("//button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> webDriver.getCurrentUrl().contains("submitted-form.html"));

        String url = driver.getCurrentUrl();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html?my-text=&my-password=&my-textarea=&my-readonly=Readonly+input&my-select=Open+this+select+menu&my-datalist=&my-file=&my-check=on&my-radio=on&my-colors=%23563d7c&my-date=02%2F17%2F2026&my-range=5&my-hidden=", url);

    }
    @Test
    void rangeInput() {
        // Находим элемент range
        WebElement range = driver.findElement(By.name("my-range"));

        // Проверяем начальное значение через JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String initialValue = (String) js.executeScript("return arguments[0].value;", range);
        assertEquals("5", initialValue, "Initial value of range should be 5");

        // Изменяем значение ползунка через JavaScript
        js.executeScript("arguments[0].value = '7'; " +
                "arguments[0].dispatchEvent(new Event('change'));", range);

        // Нажимаем кнопку отправки формы
        driver.findElement(By.xpath("//button")).click();

        // Ожидаем перехода на страницу с подтверждением
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> webDriver.getCurrentUrl().contains("submitted-form.html"));

        // Проверяем URL на наличие правильного значения range
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("my-range=7"), "URL should contain my-range=7 parameter");

        // Дополнительная проверка: можно проверить полный URL
        assertTrue(url.contains("my-range=7"), "Range value in URL should be 7");
    }
}
