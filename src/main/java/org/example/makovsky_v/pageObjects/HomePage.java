package org.example.makovsky_v.pageObjects;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.chapter_3.*;
import org.example.makovsky_v.pageObjects.chapter_4.*;
import org.example.makovsky_v.pageObjects.chapter_5.*;
import org.example.makovsky_v.pageObjects.chapter_7.LoginFormPage;
import org.example.makovsky_v.pageObjects.chapter_7.SlowLoginPage;
import org.example.makovsky_v.pageObjects.chapter_8.RandomCalculatorPage;
import org.example.makovsky_v.pageObjects.chapter_9.ABTestingPage;
import org.example.makovsky_v.pageObjects.chapter_9.DataTypesPage;
import org.example.makovsky_v.pageObjects.chapter_9.DownloadFilesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage{
    public static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    public HomePage(WebDriver driver) {
        super(driver);
        open();
    }

    //locators

    //actions
    @Step("Open homepage")
    private void open() {
        driver.get(BASE_URL);
    }

    @Step("Get web title")
    public String getWebTitle() {
        return driver.getTitle();
    }

    //method -> open another Page Object
    @Step("Open Web form page")
    public WebFormPage openWebFormPage() {
        driver.findElement(By.linkText("Web form")).click();
        return new WebFormPage(driver);
    }

    @Step("Open Navigation page")
    public NavigationPage openNavigationPage() {
        driver.findElement(By.linkText("Navigation")).click();
        return new NavigationPage(driver);
    }

    @Step("Open Dropdown menu page")
    public DropdownMenuPage openDropdownMenuPage() {
        driver.findElement(By.linkText("Dropdown menu")).click();
        return new DropdownMenuPage(driver);
    }

    @Step("Open Mouse over page")
    public MouseOverPage openMouseOverPage() {
        driver.findElement(By.linkText("Mouse over")).click();
        return new MouseOverPage(driver);
    }

    @Step("Open Drag and drop page")
    public DragAndDropPage openDragAndDropPage() {
        driver.findElement(By.linkText("Drag and drop")).click();
        return new DragAndDropPage(driver);
    }

    @Step("Open Drawing in canvas page")
    public DrawingInCanvasPage openDrawingInCanvasPage() {
        driver.findElement(By.linkText("Draw in canvas")).click();
        return new DrawingInCanvasPage(driver);
    }

    @Step("Open Loading images page")
    public LoadingImagesPage openLoadingImagesPage() {
        driver.findElement(By.linkText("Loading images")).click();
        return new LoadingImagesPage(driver);
    }

    @Step("Open Slow calculator page")
    public SlowCalculatorPage openSlowCalculatorPage() {
        driver.findElement(By.linkText("Slow calculator")).click();
        return new SlowCalculatorPage(driver);
    }

    @Step("Open Long page")
    public LongPage openLongPage() {
        driver.findElement(By.linkText("Long page")).click();
        return new LongPage(driver);
    }

    @Step("Open Infinite scroll page")
    public InfiniteScrollPage openInfiniteScrollPage() {
        driver.findElement(By.linkText("Infinite scroll")).click();
        return new InfiniteScrollPage(driver);
    }

    @Step("Open Shadow DOM page")
    public ShadowDOMPage openShadowDOMPage() {
        driver.findElement(By.linkText("Shadow DOM")).click();
        return new ShadowDOMPage(driver);
    }

    @Step("Open Cookies page")
    public CookiesPage openCookiesPage() {
        driver.findElement(By.linkText("Cookies")).click();
        return new CookiesPage(driver);
    }

    @Step("Open Frames page")
    public FramesPage openFramesPage() {
        driver.findElement(By.linkText("Frames")).click();
        return new FramesPage(driver);
    }

    @Step("Open IFrames page")
    public IFramesPage openIFramesPage() {
        driver.findElement(By.linkText("IFrames")).click();
        return new IFramesPage(driver);
    }

    @Step("Open Dialog boxes page")
    public DialogBoxesPage openDialogBoxesPage() {
        driver.findElement(By.linkText("Dialog boxes")).click();
        return new DialogBoxesPage(driver);
    }

    @Step("Open Web storage page")
    public WebStoragePage openWebStoragePage() {
        driver.findElement(By.linkText("Web storage")).click();
        return new WebStoragePage(driver);
    }

    @Step("Open Geolocation page")
    public GeolocationPage openGeolocationPage() {
        driver.findElement(By.linkText("Geolocation")).click();
        return new GeolocationPage(driver);
    }

    @Step("Open Notifications page")
    public NotificationsPage openNotificationsPage() {
        driver.findElement(By.linkText("Notifications")).click();
        return new NotificationsPage(driver);
    }

    @Step("Open Get user media page")
    public GetUserMediaPage openGetUserMediaPage() {
        driver.findElement(By.linkText("Get user media")).click();
        return new GetUserMediaPage(driver);
    }

    @Step("Open Multilanguage page")
    public MultilanguagePage openMultilanguagePage() {
        driver.findElement(By.linkText("Multilanguage")).click();
        return new MultilanguagePage(driver);
    }

    @Step("Open Console logs page")
    public ConsoleLogsPage openConsoleLogsPage() {
        driver.findElement(By.linkText("Console logs")).click();
        return new ConsoleLogsPage(driver);
    }

    @Step("Open Login form page")
    public LoginFormPage openLoginFormPage() {
        driver.findElement(By.linkText("Login form")).click();
        return new LoginFormPage(driver);
    }

    @Step("Open Slow login form page")
    public SlowLoginPage openSlowLoginPage() {
        driver.findElement(By.linkText("Slow login")).click();
        return new SlowLoginPage(driver);
    }

    @Step("Open Random calculator page")
    public RandomCalculatorPage openRandomCalculatorPage() {
        driver.findElement(By.linkText("Random calculator")).click();
        return new RandomCalculatorPage(driver);
    }

    @Step("Open Download files page")
    public DownloadFilesPage openDownloadFilesPage() {
        driver.findElement(By.linkText("Download files")).click();
        return new DownloadFilesPage(driver);
    }

    @Step("Open A/B Testing page")
    public ABTestingPage openABTestingPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Находим элемент по тексту ссылки и ждем, пока он станет кликабельным
        WebElement abTestingLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("A/B Testing")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", abTestingLink);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Корректная обработка прерывания
            throw new RuntimeException("Interrupted while waiting after scroll", e);
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", abTestingLink);
        return new ABTestingPage(driver);
    }

    @Step("Open Data types page")
    public DataTypesPage openDataTypesPage() {
        driver.findElement(By.linkText("Data types")).click();
        return new DataTypesPage(driver);
    }
}
