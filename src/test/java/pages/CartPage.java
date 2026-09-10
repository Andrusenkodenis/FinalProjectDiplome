package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private final By removeButton = By.cssSelector("a.remove");
    private final By emptyCartMessage = By.cssSelector(".cart-empty");
    private final By undoLink = By.cssSelector(".restore-item");
    private final By couponField = By.id("coupon_code");
    private final By applyCouponButton = By.cssSelector("button[name='apply_coupon']");
    private final By couponMessage = By.cssSelector(".woocommerce-message");
    private final By totalPrice = By.cssSelector(".order-total .amount");
    private final By cartItems = By.cssSelector("tr.cart_item");

    public void removeProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
        wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage),
                ExpectedConditions.visibilityOfElementLocated(undoLink)));
    }

    public void restoreProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(undoLink)).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(cartItems, 0));
    }

    public void applyCoupon(String coupon) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(couponField)).clear();
        driver.findElement(couponField).sendKeys(coupon);
        driver.findElement(applyCouponButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(couponMessage));
    }

    public boolean isCouponApplied() {
        return driver.findElement(couponMessage).getText().contains("Купон успешно применён");
    }

    public String getTotalPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice)).getText();
    }

    public boolean hasProducts() {
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(cartItems, 0)).size() > 0;
    }

    public boolean isOpened() {
        return wait.until(ExpectedConditions.urlContains("cart"));
    }

    public boolean isCartEmpty() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage)).isDisplayed();
    }
}
