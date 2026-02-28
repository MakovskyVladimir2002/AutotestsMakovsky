package org.example.makovsky_v.pageObjects.chapter_3;

import io.qameta.allure.Step;
import org.example.makovsky_v.pageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.Color;

import static org.example.makovsky_v.pageObjects.HomePage.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebFormPage extends BasePage {
    private static final String WEB_FORM_URL = "web-form.html";
    public static final String WEB_FORM_TITLE = "Web form";
    public static final String DISABLED_INPUT_FIELD = "Disabled input";
    public static final String READONLY_INPUT_FIELD = "Readonly input";
    public static final String BIG_TEXT = "Lorem ipsum dolor sit amet consectetur adipiscing elit habitant metus, " +
            "tincidunt maecenas posuere sollicitudin augue duis bibendum mauris eu, et dignissim magna ad nascetur suspendisse quis nunc. " +
            "Fames est ligula molestie aliquam pretium bibendum nullam, sociosqu maecenas mus etiam consequat ornare leo, sem mattis " +
            "varius luctus litora senectus. Parturient quis tristique erat natoque tortor nascetur, primis augue vivamus habitasse " +
            "senectus porta leo, aenean potenti ante a nam.";
    public static final Color GREEN = new Color(0, 255, 0, 1);

    private static final String DROPDOWN_SELECT_TEXT = "Open this select menu";
    public static final String VALUE_NAME = "value";


    String selectFile = System.getProperty("user.dir") + "/src/test/resources/STE In Banner.jpg";

    //locators
    WebElement submitButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
    WebElement textInputField = driver.findElement(By.id("my-text-id"));
    WebElement passwordInputField = driver.findElement(By.name("my-password"));
    WebElement textAreaInputField = driver.findElement(By.name("my-textarea"));
    WebElement disabledInputField = driver.findElement(By.name("my-disabled"));
    WebElement readonlyInputField = driver.findElement(By.name("my-readonly"));
    WebElement dropdownSelectMenu = driver.findElement(By.name("my-select"));
    WebElement dropdownDataList = driver.findElement(By.name("my-datalist"));
    WebElement fileInputButton = driver.findElement(By.name("my-file"));
    WebElement checkedCheckbox = driver.findElement(By.id("my-check-1"));
    WebElement defaultCheckbox = driver.findElement(By.id("my-check-2"));
    WebElement checkedRadioButton = driver.findElement(By.id("my-radio-1"));
    WebElement defaultRadioButton = driver.findElement(By.id("my-radio-2"));
    WebElement colorPicker = driver.findElement(By.name("my-colors"));
    WebElement dateBox = driver.findElement(By.name("my-date"));
    WebElement rangeElement = driver.findElement(By.name("my-range"));

    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get subpage url")
    public String getUrl() {
        return WEB_FORM_URL;
    }

    @Step("Input text in user field")
    public void inputValue(String user) {
        textInputField.sendKeys(user);
    }

    @Step("Clear text in user field")
    public void clearTextValue() {
        textInputField.clear();
    }

    @Step("Assert value text")
    public String getTextValue() {
        return textInputField.getDomProperty(VALUE_NAME);
    }

    @Step("Input password")
    public void inputPassword(String password) {
        passwordInputField.sendKeys(password);;
    }

    @Step("Clear password")
    public void clearPasswordValue() {
        passwordInputField.clear();
    }

    @Step("Assert value password")
    public String getTextPassword() {
        return passwordInputField.getDomProperty(VALUE_NAME);
    }

    @Step("Input big text")
    public void inputTextareaValue(String bigText) {
        textAreaInputField.sendKeys(bigText);
    }

    @Step("Clear big text")
    public void clearTextareaValue() {
        textAreaInputField.clear();
    }

    @Step("Assert value big text")
    public String getTextarea() {
        return textAreaInputField.getDomProperty(VALUE_NAME);
    }

    @Step("Check disabled field")
    public WebElement disabledInput() {
        return disabledInputField;
    }

    @Step("Check disabled field")
    public String getDisabledInputValue() {
        return disabledInputField.getDomAttribute("placeholder");
    }

    @Step("Check readonly field")
    public WebElement readonlyInput() {
        return readonlyInputField;
    }

    @Step("Check readonly field")
    public String getReadonlyInputField() {
        return readonlyInputField.getDomAttribute(VALUE_NAME);
    }

    @Step("Check dropdown and choose option")
    public void dropdownSelectorByVisibleText(String option) {
        Select select = new Select(dropdownSelectMenu);
        assertEquals(DROPDOWN_SELECT_TEXT, select.getFirstSelectedOption().getText());
        select.selectByVisibleText(option);
        assertEquals(option, select.getFirstSelectedOption().getText());
    }

    @Step("Check dropdown and choose value")
    public void dropdownSelectorByValue(String value) {
        Select select = new Select(dropdownSelectMenu);
        assertEquals(DROPDOWN_SELECT_TEXT, select.getFirstSelectedOption().getText());
        select.selectByValue(value);
        assertEquals(value, select.getFirstSelectedOption().getDomProperty(VALUE_NAME));
    }

    public WebElement getDropdownDataList() {
        return dropdownDataList;
    }

    @Step("Choose the city in data list menu")
    public void dropDownDataListMenu(String city) {
        dropdownDataList.sendKeys(city);
    }

    @Step("File input")
    public void downloadFile() {
        fileInputButton.sendKeys(selectFile);
    }

    public WebElement getCheckedCheckbox() {
        return checkedCheckbox;
    }

    @Step("Check checkbox")
    public void clickCheckedCheckbox() {
        checkedCheckbox.click();
    }

    public WebElement getDefaultCheckbox() {
        return defaultCheckbox;
    }

    @Step("Check default checkbox")
    public void clickDefaultCheckbox() {
        defaultCheckbox.click();
    }

    public WebElement getCheckedRadio() {
        return checkedRadioButton;
    }

    public WebElement getDefaultRadio() {
        return defaultRadioButton;
    }

    @Step("Check default radio")
    public void clickDefaultRadio() {
        defaultRadioButton.click();
    }

    @Step("Check color picker")
    public void chooseColorPicker() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String initColor = getColor();
        System.out.println("The initial color is " + initColor);

        String script = String.format("arguments[0].setAttribute('value', '%s');", GREEN.asHex());
        js.executeScript(script, colorPicker);
        String finalColor = getColor();
        System.out.println("The initial color is " + finalColor);
    }

    public String getColor() {
        return colorPicker.getAttribute(VALUE_NAME);
    }

    public WebElement getDatePicker() {
        return dateBox;
    }

    @Step("Check date picker")
    public void chooseDatePicker(String date) {
        dateBox.click();
        dateBox.sendKeys(date);
    }

    public WebElement getRangeElement() {
        return rangeElement;
    }

    @Step("Check example range")
    public void moveSliderRight() {
        int width = rangeElement.getSize().getWidth();
        System.out.println("width = " + width);
        int x = rangeElement.getLocation().getX();
        System.out.println("x = " + x);
        int y = rangeElement.getLocation().getY();
        System.out.println("y = " + y);
        for (int i = 5; i <= 10; i++) {
            new Actions(driver)
                    .moveToElement(rangeElement)
                    .clickAndHold()
                    .moveToLocation(x + width / 10 * i, y)
                    .release()
                    .perform();
        }
    }

    @Step("Submit form")
    public void submitForm() {
        submitButton.click();
    }

    @Step("Check that page is web form")
    public void checkIsWebPage() {
        assertEquals(BASE_URL + getUrl(), getCurrentUrl());
        assertEquals("Web form", getTitle().getText());
    }
}
