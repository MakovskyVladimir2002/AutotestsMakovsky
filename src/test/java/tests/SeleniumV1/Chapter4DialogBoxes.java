package tests.SeleniumV1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Chapter4DialogBoxes {

    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html";
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
    @DisplayName("TitleDialogBoxes")
    void titleTest() {
        String title = driver.getTitle();
        assertEquals("Hands-On Selenium WebDriver with Java", title);
    }
    @Test
    @DisplayName("Title2DialogBoxes")
    void titleTest2() {
        WebElement practiceSite = driver.findElement(By.tagName("h5"));
        assertEquals("Practice site", practiceSite.getText());
    }
    @Test
    @DisplayName("Dialog boxes")
    void dialogBoxes() {
        WebElement shadowDom = driver.findElement(By.className("display-6"));
        assertEquals("Dialog boxes", shadowDom.getText());

    }
    @Test
    @DisplayName("FirstApartLoremIpsum")
    void alertLaunch() throws InterruptedException {
        driver.findElement(By.id("my-alert")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);
        Thread.sleep(2000);
        alert.accept();
        Thread.sleep(2000);
    }
    @Test
    @DisplayName("FirstApartLoremIpsum")
    void alertConfirm() throws InterruptedException {
        driver.findElement(By.id("my-confirm")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        assertEquals("Is this correct?", text);
        alert.dismiss();
        driver.switchTo().activeElement();
        WebElement youChose = (driver.findElement(By.id("confirm-text")));
        assertEquals("You chose: false", youChose.getText());
    }
    @Test
    @DisplayName("FirstApartLoremIpsum")
    void alertPromt() throws InterruptedException {
        driver.findElement(By.id("my-prompt")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        assertEquals("Please enter your name", text);
        alert.sendKeys("фартук");
        alert.accept();
        driver.switchTo().activeElement();
        WebElement youChose1 = (driver.findElement(By.id("prompt-text")));
        assertEquals("You typed: фартук", youChose1.getText());

        driver.findElement(By.id("my-prompt")).click();
        Alert alertDis = driver.switchTo().alert();
        alertDis.sendKeys("жучка");
        alertDis.dismiss();
        driver.switchTo().activeElement();
        WebElement youChose2 = (driver.findElement(By.id("prompt-text")));
        assertEquals("You typed: null", youChose2.getText());
    }
    @Test
    @DisplayName("FirstApartLoremIpsum")
    void alertModal() throws InterruptedException {
        driver.findElement(By.id("my-modal")).click();
        Thread.sleep(2000);
        WebElement title = driver.findElement(By.id("exampleModalLabel"));
        assertEquals("Modal title", title.getText());
        driver.findElement(By.xpath("//div//button[normalize-space() = 'Save changes']")).click();
        WebElement text = driver.findElement(By.xpath("//p[normalize-space() = 'You chose: Save changes']"));
        assertEquals("You chose: Save changes", text.getText());
    }
}
