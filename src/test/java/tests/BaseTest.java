package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CatalogPage;

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

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
