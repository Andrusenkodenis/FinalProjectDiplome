package tests;

import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AuthorizationTests extends BaseTest {

    private LoginPage loginPage;

    private static final String USERNAME = "Hirosaki234";
    private static final String PASSWORD = "3lfk3fm3eg";

    @Before
    public void openPage() {
        driver.get("https://intershop5.skillbox.ru/my-account/");
        loginPage = new LoginPage(driver, wait);
    }

    @Test
    public void successfulUserAuthorization() {
        loginPage.login(USERNAME, PASSWORD);
        assertFalse("Появилась ошибка авторизации", loginPage.isErrorDisplayed());
    }

    @Test
    public void loginWithWrongPasswordShowsError() {
        loginPage.login(USERNAME, "wrongPassword");
        assertTrue(loginPage.isErrorDisplayed());
    }

    @Test
    public void loginWithWrongUsernameShowsError() {
        loginPage.login("WrongUser", PASSWORD);
        assertTrue(loginPage.isErrorDisplayed());
    }

    @Test
    public void loginWithEmptyFieldsShowsError() {
        loginPage.clickLoginButton();
        assertTrue(loginPage.isErrorDisplayed());
    }
}
