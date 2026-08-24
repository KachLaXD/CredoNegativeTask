package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WaitUtils;

public class LoginPage {

    private final WaitUtils waitUtils;

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By firstError = By.id("error-0");
    private final By secondError = By.id("error-1");
    private final By alertPopup = By.xpath("//*[@role='alert']");

    public LoginPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
    }

    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        WebElement input = waitUtils.getExplicitWait()
                .until(ExpectedConditions.visibilityOfElementLocated(usernameInput));

        input.clear();
        input.sendKeys(username);

        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        WebElement input = waitUtils.getExplicitWait()
                .until(ExpectedConditions.visibilityOfElementLocated(passwordInput));

        input.clear();
        input.sendKeys(password);

        return this;
    }

    @Step("Click login")
    public LoginPage clickLogin() {
        waitUtils.getExplicitWait()
                .until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();

        return this;
    }

    @Step("Attempt login")
    public LoginPage login(String username, String password) {
        if (!username.isEmpty()) {
            enterUsername(username);
        }

        if (!password.isEmpty()) {
            enterPassword(password);
        }

        return clickLogin();
    }

    @Step("Check first validation error")
    public boolean isFirstErrorDisplayed() {
        try {
            return waitUtils.getExplicitWait()
                    .until(ExpectedConditions.visibilityOfElementLocated(firstError))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check second validation error")
    public boolean isSecondErrorDisplayed() {
        try {
            return waitUtils.getExplicitWait()
                    .until(ExpectedConditions.visibilityOfElementLocated(secondError))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check incorrect credentials alert")
    public boolean isAlertDisplayed() {
        try {
            return waitUtils.getExplicitWait()
                    .until(ExpectedConditions.visibilityOfElementLocated(alertPopup))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Get alert text")
    public String getAlertText() {
        try {
            return waitUtils.getExplicitWait()
                    .until(ExpectedConditions.visibilityOfElementLocated(alertPopup))
                    .getText()
                    .trim();
        } catch (Exception e) {
            return "";
        }
    }
}
