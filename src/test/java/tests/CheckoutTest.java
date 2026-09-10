package tests;

import org.junit.Test;
import pages.CheckoutPage;
import pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class CheckoutTest extends BaseTest {

    private static final String USERNAME = "Hirosaki234";
    private static final String PASSWORD = "3lfk3fm3eg";
    private static final String EMAIL = "hirosaki234@mail.ru";

    @Test
    public void userCanOpenCheckoutPage() {
        CheckoutPage checkout = new CheckoutPage(driver, wait);
        addAnyProductToCart();
        checkout.openCheckoutPage();
        assertTrue(checkout.isOpened());
        assertTrue(checkout.isCheckoutTitleDisplayed());
    }

    @Test
    public void userCanFillCheckoutInformation() {
        ensureTestUserExists(USERNAME, EMAIL, PASSWORD);
        LoginPage loginPage = new LoginPage(driver, wait);
        CheckoutPage checkout = new CheckoutPage(driver, wait);
        driver.get("https://intershop5.skillbox.ru/my-account/");
        loginPage.login(USERNAME, PASSWORD);
        addAnyProductToCart();
        checkout.openCheckoutPage();
        checkout.fillCustomerInformation(
                "Иван",
                "Иванов",
                "Ленина 10",
                "Москва",
                "Москва",
                "123456",
                "89991234567",
                "test@test.ru"
        );
        checkout.addComment("Позвонить перед доставкой");
        assertTrue(checkout.hasProductsInOrder());
    }

    @Test
    public void userCanPlaceOrderWithCashPayment() {
        ensureTestUserExists(USERNAME, EMAIL, PASSWORD);
        LoginPage loginPage = new LoginPage(driver, wait);
        CheckoutPage checkout = new CheckoutPage(driver, wait);
        driver.get("https://intershop5.skillbox.ru/my-account/");
        loginPage.login(USERNAME, PASSWORD);
        addAnyProductToCart();
        checkout.openCheckoutPage();
        checkout.fillCustomerInformation(
                "Иван",
                "Петров",
                "Ленина 15",
                "Москва",
                "Москва",
                "123456",
                "89998887766",
                "ivan@test.ru"
        );
        checkout.selectCashPayment();
        assertTrue(checkout.isCashPaymentSelected());
        checkout.clickPlaceOrder();
        assertTrue(checkout.isOrderCreated());
    }
}
