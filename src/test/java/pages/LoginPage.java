package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {

        this.driver = driver;
        this.wait = wait;
    }

    private final By usernameField =
            By.id("username");

    private final By passwordField =
            By.id("password");

    private final By loginButton =
            By.name("login");

    private final By errorMessage =
            By.className("woocommerce-error");

    private final By logoutLink =
            By.linkText("Logout");

    public void openLoginPage() {

        driver.get(
                "https://intershop5.skillbox.ru/my-account/"
        );

    }

    public LoginPage enterUsername(String username) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameField)
        ).clear();

        driver.findElement(usernameField)
                .sendKeys(username);

        return this;
    }

    public LoginPage enterPassword(String password) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField)
        ).clear();

        driver.findElement(passwordField)
                .sendKeys(password);

        return this;
    }

    public LoginPage clickLoginButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        ).click();

        return this;
    }

    public LoginPage login(String username, String password) {

        return enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
    }

    public boolean isErrorDisplayed() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(errorMessage)
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isAuthorized() {

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(logoutLink)
        ).isDisplayed();
    }
}
