package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private final WebDriverWait wait;

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
    }

    private final By productTitle =
            By.cssSelector(".entry-title");

    public boolean isOpened() {
        return wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(productTitle)
        ).isDisplayed();
    }
}
