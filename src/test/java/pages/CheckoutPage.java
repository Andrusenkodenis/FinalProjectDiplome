package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private final By checkoutTitle = By.cssSelector(".post-title");
    private final By firstNameField = By.id("billing_first_name");
    private final By lastNameField = By.id("billing_last_name");
    private final By addressField = By.id("billing_address_1");
    private final By cityField = By.id("billing_city");
    private final By stateField = By.id("billing_state");
    private final By postcodeField = By.id("billing_postcode");
    private final By phoneField = By.id("billing_phone");
    private final By emailField = By.id("billing_email");
    private final By orderCommentField = By.id("order_comments");
    private final By orderItems = By.cssSelector(".woocommerce-checkout-review-order-table .cart_item");
    private final By orderTotal = By.cssSelector(".order-total .amount");
    private final By paymentCash = By.id("payment_method_cod");
    private final By paymentBank = By.id("payment_method_bacs");
    private final By placeOrderButton = By.id("place_order");
    private final By orderReceivedMessage = By.cssSelector(".woocommerce-order");

    public void openCheckoutPage() {
        driver.get("https://intershop5.skillbox.ru/checkout/");
    }

    public boolean isOpened() {
        return wait.until(ExpectedConditions.urlContains("checkout"));
    }

    public boolean isCheckoutTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutTitle)).isDisplayed();
    }

    public void fillCustomerInformation(String firstName, String lastName, String address, String city,
                                        String state, String postcode, String phone, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(stateField).sendKeys(state);
        driver.findElement(postcodeField).sendKeys(postcode);
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void addComment(String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderCommentField)).sendKeys(comment);
    }

    public boolean hasProductsInOrder() {
        wait.until(ExpectedConditions.presenceOfElementLocated(orderItems));
        return true;
    }

    public String getOrderTotal() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderTotal)).getText();
    }

    public void selectCashPayment() {
        wait.until(ExpectedConditions.elementToBeClickable(paymentCash)).click();
    }

    public void selectBankPayment() {
        wait.until(ExpectedConditions.elementToBeClickable(paymentBank)).click();
    }

    public boolean isCashPaymentSelected() {

        return driver.findElement(paymentCash).isSelected();
    }

    public boolean isBankPaymentSelected() {

        return driver.findElement(paymentBank)
                .isSelected();
    }

    public void clickPlaceOrder() {

        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();

    }

    public boolean isOrderCreated() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderReceivedMessage)).isDisplayed();
    }
}
