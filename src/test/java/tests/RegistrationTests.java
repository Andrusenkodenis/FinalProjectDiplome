package tests;

import org.junit.Before;
import org.junit.Test;
import pages.RegistrationPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegistrationTests extends BaseTest {

    private RegistrationPage registrationPage;

    @Before
    public void openPage() {
        driver.get("https://intershop5.skillbox.ru/register/");
        registrationPage =
                new RegistrationPage(driver, wait);
    }

    @Test
    public void successfulRegistrationNewUser() {
        registrationPage.register("Hirosaki234", "hiro4@mail.ru", "3lfk3fm3eg");
        assertFalse("Появилась ошибка регистрации", registrationPage.isErrorDisplayed());
    }

    @Test
    public void registrationWithoutUsernameShowsError() {
        registrationPage.enterEmail("test@mail.com").enterPassword("Password123!").clickRegister();
        assertTrue(registrationPage.isErrorDisplayed());
    }

    @Test
    public void registrationWithoutPasswordShowsError() {
        registrationPage.enterUsername("User123").enterEmail("test@mail.com").clickRegister();
        assertTrue(registrationPage.isErrorDisplayed());
    }

    @Test
    public void registrationWithoutEmailShowsError() {
        registrationPage.enterUsername("User123").enterPassword("Password123!").clickRegister();
        assertTrue(registrationPage.isErrorDisplayed());
    }
}
