package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TargetPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public TargetPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getH1Text() {
        WebElement h1 = wait.until(visibilityOfElementLocated(By.tagName("h1")));
        return h1.getText().trim();
    }

    public boolean isPageLoaded() {
        try {
            wait.until(visibilityOfElementLocated(By.tagName("h1")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
