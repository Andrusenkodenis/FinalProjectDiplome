package tests;

import org.junit.Test;
import pages.CatalogPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class CheckoutTest extends BaseTest {

    private static final String USERNAME = "Hirosaki234";
    private static final String PASSWORD = "3lfk3fm3eg";

    @Test
    public void userCanOpenCheckoutPage() {
        CatalogPage catalog = new CatalogPage(driver, wait);
        CartPage cart = new CartPage(driver, wait);
        CheckoutPage checkout = new CheckoutPage(driver, wait);
        catalog.openHomePage();
        catalog.openCatalogFromMenu();
        catalog.addProductToCart();
        catalog.clickDetailsButton();
        checkout.openCheckoutPage();
        assertTrue(checkout.isOpened());
        assertTrue(checkout.isCheckoutTitleDisplayed());
    }

    @Test
    public void userCanFillCheckoutInformation() {
        LoginPage loginPage = new LoginPage(driver, wait);
        CatalogPage catalog = new CatalogPage(driver, wait);
        CheckoutPage checkout = new CheckoutPage(driver, wait);
        driver.get("https://intershop5.skillbox.ru/my-account/");
        loginPage.login(USERNAME, PASSWORD);
        catalog.openHomePage();
        catalog.openCatalogFromMenu();
        catalog.addProductToCart();
        catalog.clickDetailsButton();
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
        LoginPage loginPage = new LoginPage(driver, wait);
        CatalogPage catalog = new CatalogPage(driver, wait);
        CheckoutPage checkout = new CheckoutPage(driver, wait);
        driver.get("https://intershop5.skillbox.ru/my-account/");
        loginPage.login(USERNAME, PASSWORD);
        catalog.openHomePage();
        catalog.openCatalogFromMenu();
        catalog.addProductToCart();
        catalog.clickDetailsButton();
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
