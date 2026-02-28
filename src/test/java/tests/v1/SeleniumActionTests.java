package tests.v1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Расширенные тесты WebDriver Selenium, демонстрирующие лучшие практики
 * для поддержки, надежности и соответствия индустриальным стандартам.
 *
 * Этот тестовый набор охватывает различные взаимодействия с пользовательским интерфейсом,
 * включая формы, списки выбора, загрузку файлов и проверку состояния элементов.
 */
@DisplayName("WebFormTests")
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class SeleniumActionTests {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10); // Стандартный таймаут ожидания
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/"; // Базовый URL тестируемого приложения

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @BeforeAll
    static void setupClass() { // Настройка драйвера перед всеми тестами
        WebDriverManager.chromedriver().setup(); // Автоматическое управление бинарными файлами драйвера
    }

    @BeforeEach
    void setUp() { // Подготовка перед каждым тестом
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Запуск в фоновом режиме для CI/CD
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options); // Инициализация драйвера
        wait = new WebDriverWait(driver, DEFAULT_TIMEOUT); // Инициализация явного ожидания
        actions = new Actions(driver); // Инициализация действий мыши/клавиатуры

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Настройка неявного ожидания
        driver.manage().window().maximize(); // Максимизация окна браузера
        driver.get(BASE_URL); // Открытие базовой страницы
    }

    @AfterEach
    void tearDown() { // Очистка после каждого теста
        if (driver != null) {
            driver.quit(); // Закрытие всех окон сессии драйвера
        }
    }

    @Test
    @Order(1)
    @DisplayName("Должен успешно отправить веб-форму")
    void shouldSubmitWebFormSuccessfully() {
        navigateToWebForm(); // Переход к форме

        WebElement submitButton = wait.until( // Ожидание кликабельности кнопки
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit'], button"))
        );

        submitButton.click(); // Клик по кнопке отправки

        // Добавить проверку успешной отправки (например, появление сообщения об успехе)
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.className("success-message")));
    }

    @Test
    @Order(2)
    @DisplayName("Должен корректно обрабатывать операции со списком выбора")
    void shouldHandleSelectOperationsCorrectly() {
        navigateToWebForm(); // Переход к форме

        WebElement selectElement = wait.until( // Ожидание появления элемента списка
                ExpectedConditions.presenceOfElementLocated(By.tagName("select"))
        );

        Select select = new Select(selectElement); // Создание объекта Select

        select.selectByValue("2"); // Выбор значения по атрибуту value
        assertThat(select.getFirstSelectedOption().getText()) // Проверка выбранного текста
                .as("Текст выбранного варианта должен совпадать с ожидаемым значением")
                .isEqualTo("Two");

        select.selectByIndex(0); // Сброс выбора на первый элемент

        List<WebElement> options = select.getOptions(); // Получение всех доступных опций
        assertThat(options) // Проверка количества опций
                .as("Общее количество доступных опций")
                .hasSize(4);

        List<WebElement> selectedOptions = select.getAllSelectedOptions(); // Получение выбранных опций
        assertThat(selectedOptions) // Проверка, что выбран только один элемент
                .as("Количество текущих выбранных опций")
                .hasSize(1);
    }

    @Test
    @Order(3)
    @DisplayName("Должен корректно проверять состояния элементов")
    void shouldValidateElementStatesCorrectly() {
        navigateToWebForm(); // Переход к форме

        WebElement disabledInput = wait.until( // Ожидание появления отключенного поля
                ExpectedConditions.presenceOfElementLocated(By.name("my-disabled"))
        );

        assertThat(disabledInput.isEnabled()) // Проверка, что поле действительно отключено
                .as("Отключенное поле ввода не должно быть активным")
                .isFalse();

        // Проверка, что попытка взаимодействовать с отключенным элементом вызывает исключение
        assertThrows(ElementNotInteractableException.class,
                () -> disabledInput.sendKeys("test"), // Попытка ввести текст
                "Отправка клавиш в отключенное поле должна вызвать ElementNotInteractableException"
        );

        WebElement readonlyInput = driver.findElement(By.name("my-readonly")); // Поиск readonly поля
        assertThat(readonlyInput.getAttribute("readonly")) // Проверка атрибута readonly
                .as("Атрибут 'readonly' должен присутствовать")
                .isNotNull();
    }

    @Test
    @Order(4)
    @DisplayName("Должен обрабатывать ввод с автодополнением из datalist")
    void shouldHandleDatalistInput() {
        navigateToWebForm(); // Переход к форме

        WebElement inputWithDatalist = wait.until( // Ожидание кликабельности поля datalist
                ExpectedConditions.elementToBeClickable(By.name("my-datalist"))
        );

        actions.click(inputWithDatalist) // Кликнуть на поле
                .sendKeys(inputWithDatalist, "Chicago") // Ввести значение
                .sendKeys(org.openqa.selenium.Keys.ENTER) // Нажать Enter
                .build()
                .perform();

        // Проверка значения в поле после выбора
        assertThat(inputWithDatalist.getAttribute("value"))
                .as("Значение в поле после выбора из datalist")
                .containsIgnoringCase("Chicago");
    }

    @Test
    @Order(5)
    @DisplayName("Должен успешно загрузить файл")
    void shouldUploadFileSuccessfully() {
        navigateToWebForm(); // Переход к форме

        // Использование относительного пути или настраиваемого свойства для расположения файла
        String filePath = new File("src/test/resources/test-image.jpg").getAbsolutePath(); // Абсолютный путь к файлу

        WebElement fileInput = wait.until( // Ожидание наличия поля загрузки
                ExpectedConditions.presenceOfElementLocated(By.name("my-file"))
        );

        fileInput.sendKeys(filePath); // Прямое указание пути к файлу в поле ввода (надежный метод)

        WebElement submitButton = wait.until( // Ожидание кликабельности кнопки отправки
                ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-outline-primary.mt-3"))
        );

        submitButton.click(); // Отправка формы с файлом

        // Добавить проверку успешной загрузки (например, сообщение о подтверждении)
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.id("upload-success")));
    }

    @Test
    @Order(6)
    @DisplayName("Должен получить и проверить свойства цвета CSS")
    void shouldRetrieveAndValidateCssColorProperties() {
        navigateToWebForm(); // Переход к форме

        WebElement colorInputElement = wait.until( // Ожидание наличия элемента цвета
                ExpectedConditions.presenceOfElementLocated(By.name("my-colors"))
        );

        String backgroundColor = colorInputElement.getCssValue("background-color"); // Получение CSS-значения цвета

        String normalizedColor = normalizeRgbaToRgb(backgroundColor); // Нормализация RGBA в RGB для сравнения

        assertThat(normalizedColor.toLowerCase()) // Проверка цвета в формате RGB
                .as("Цвет фона в формате RGB")
                .isEqualTo("rgb(86, 61, 124)");
    }

    /**
     * Переходит к странице веб-формы с помощью явных ожиданий для обеспечения надежности.
     */
    private void navigateToWebForm() {
        WebElement formLink = wait.until( // Ожидание кликабельности ссылки на форму
                ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='web-form.html']"))
        );
        formLink.click(); // Клик по ссылке

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form"))); // Ожидание загрузки формы
    }

    /**
     * Нормализует строку цвета RGBA в формат RGB для согласованного сравнения.
     * Пример: "rgba(86, 61, 124, 1)" становится "rgb(86, 61, 124)"
     */
    private String normalizeRgbaToRgb(String rgbaColor) {
        if (rgbaColor.startsWith("rgba")) { // Если цвет в формате rgba
            // Извлечение значений RGB из формата rgba(r, g, b, a)
            String[] parts = rgbaColor.replaceAll("[rgba()]", "").split(","); // Удаление скобок и разделение
            return String.format("rgb(%s, %s, %s)", // Формирование строки RGB
                    parts[0].trim(), // Красный
                    parts[1].trim(), // Зеленый
                    parts[2].trim()  // Синий
            );
        }
        return rgbaColor; // Уже в формате RGB
    }
}
