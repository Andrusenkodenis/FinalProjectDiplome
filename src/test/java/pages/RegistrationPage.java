package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private final By usernameField = By.id("reg_username");
    private final By emailField = By.id("reg_email");
    private final By passwordField = By.id("reg_password");
    private final By registerButton = By.name("register");
    private final By errorMessage = By.className("woocommerce-error");

    public RegistrationPage enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).clear();
        driver.findElement(usernameField).sendKeys(username);
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).clear();
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public RegistrationPage clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
        return this;
    }

    public RegistrationPage register(String username, String email, String password) {

        return enterUsername(username).enterEmail(email).enterPassword(password).clickRegister();
    }

    public boolean isErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
