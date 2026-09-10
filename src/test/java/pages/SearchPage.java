package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private final By products = By.cssSelector(".products .product");
    private final By noProducts = By.className("woocommerce-info");

    public boolean hasProducts() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(products));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean hasNoProductsMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(noProducts));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isBooksPageOpened() {
        return driver.getTitle().contains("Книги")
                || driver.getPageSource().contains("Книги");
    }
}
