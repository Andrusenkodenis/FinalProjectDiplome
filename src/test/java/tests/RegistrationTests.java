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

        String random = String.valueOf(System.currentTimeMillis() % 100000000);

        String uniqueUsername = "user_" + random;
        String uniqueEmail = random + "@mail.ru";
        String uniquePassword = "Pass" + random;

        registrationPage.register(uniqueUsername, uniqueEmail, uniquePassword);
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
