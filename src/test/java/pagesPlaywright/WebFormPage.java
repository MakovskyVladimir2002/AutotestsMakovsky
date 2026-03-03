package pagesPlaywright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebFormPage {
    private final Page page;
    private final Locator submitButton;

    public WebFormPage(Page page) {
        this.page = page;
        this.submitButton = page.getByText("Submit");
    }
    @Step("Submitting the form")
    public void submit() {

        submitButton.click();
    }
    @Step("Checking checkbox state (expected to be checked)")
    public boolean isCheckboxChecked() {
        Locator  checkedCheckbox = page.locator("xpath=//input[@checked and @type = \"checkbox\"]");
        return checkedCheckbox.isChecked();
    }
    @Step("Selecting option '{valueNumber}' in the dropdown list and verifying selection")
    public boolean isFirstOptionSelected(String valueNumber) {
        Locator selectElement  = page.locator(".form-select");
        selectElement .selectOption(valueNumber);
        String selectedValue = selectElement.evaluate("el => el.value").toString();
        return selectedValue.equals(valueNumber);
    }
    @Step("Entering text 'my text' into the input field")
    public void sendText() {
        String text = "my text";
        Locator sendTextInput = page.locator("[name='my-textarea']");
        sendTextInput.fill(text);
    }
    @Step("Retrieving the current value from the input field")
    public String getTextInput() {
        Locator sendTextInput = page.locator("[name='my-textarea']");
        return sendTextInput.inputValue();
    }


}
