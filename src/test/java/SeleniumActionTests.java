import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumActionTests {
    WebDriver driver;
    @BeforeEach
    void start(){
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
    }
    @AfterEach
    void close (){
        driver.close();
    }
    @Test
    void webForm() throws InterruptedException {
        WebElement form = driver.findElement(By.xpath("//a[@href = 'web-form.html']"));
        Thread.sleep(3000);
        form.click();
//        driver.findElement(By.xpath("//form")).submit();
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(3000);
    }
    @Test
    void select() throws InterruptedException {
        WebElement form = driver.findElement(By.xpath("//a[@href = 'web-form.html']"));
        Thread.sleep(3000);
        form.click();
//        driver.findElement(By.xpath("//form")).submit();
        WebElement selectElement = driver.findElement(By.xpath("//select"));
        Select select = new Select(selectElement);
//        select.selectByIndex(2);
        select.selectByValue("2");
        assertEquals("Two", select.getFirstSelectedOption().getText());
        Thread.sleep(3000);
        select.selectByIndex(0);
        List<WebElement> options = select.getOptions();
        assertEquals(4, options.size()); // проверка, что всего для выбора доступно 4 вариант
        List<WebElement> optionElement = select.getAllSelectedOptions();
        assertEquals(1, optionElement.size()); // проверка, что выбран только один вариант
    }
    @Test
    void getInfo () throws InterruptedException {
        WebElement form = driver.findElement(By.xpath("//a[@href = 'web-form.html']"));
        Thread.sleep(3000);
        form.click();

        WebElement disabledInput = driver.findElement(By.name("my-disabled"));
        Thread.sleep(3000);
        Assertions.assertFalse(disabledInput.isEnabled()); // проверка, что элемент неактивен

        Assertions.assertThrows(ElementNotInteractableException.class, ()-> disabledInput.sendKeys("test")); // проверка, что нельзя отправить текст


    }
}
