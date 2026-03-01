package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public TargetPage clickLink(String sectionTitle, String linkPartialHref) {
        String xpath = String.format(
                "//h5[text()='%s']/following-sibling::div//a[contains(@href, '%s')]",
                sectionTitle, linkPartialHref
        );
        WebElement link = wait.until(elementToBeClickable(By.xpath(xpath)));
        link.click();
        return new TargetPage(driver, wait);
    }
}