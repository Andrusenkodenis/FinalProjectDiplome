package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private final By logo = By.cssSelector(".site-logo");
    private final By accountLink = By.xpath("//a[text()='Мой аккаунт']");
    private final By cartLink = By.linkText("Корзина");
    private final By booksLink = By.id("accesspress_storemo-2");
    private final By catalogLink = By.xpath("//a[text()='Каталог']");

    private final By searchField = By.name("s");
    private final By searchButton = By.cssSelector("button[type='submit']");

    public void openCatalog() {
        wait.until(ExpectedConditions.elementToBeClickable(catalogLink)).click();
    }

    public void open() {
        driver.get("https://intershop5.skillbox.ru/");
    }

    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }

    public void openAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(accountLink)).click();
    }

    public void openCart() {

        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public void openBooks() {
        wait.until(ExpectedConditions.elementToBeClickable(booksLink)).click();
    }

    public void search(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).clear();
        driver.findElement(searchField).sendKeys(text);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
}
