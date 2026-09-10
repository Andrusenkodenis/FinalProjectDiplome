package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CatalogPage;
import pages.RegistrationPage;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void addAnyProductToCart() {
        CatalogPage catalog = new CatalogPage(driver, wait);

        catalog.openHomePage();
        catalog.openCatalogFromMenu();
        catalog.addProductToCart();
        catalog.clickDetailsButton();
    }

    protected void ensureTestUserExists(String username, String email, String password) {
        driver.get("https://intershop5.skillbox.ru/register/");
        RegistrationPage registrationPage = new RegistrationPage(driver, wait);
        registrationPage.tryRegister(username, email, password);
    }

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
