package tests;

import org.junit.Test;
import pages.CartPage;

import static org.junit.Assert.assertTrue;

public class ShoppingCartTest extends BaseTest {

    @Test
    public void userCanRemoveProductFromCart() {
        CartPage cart = new CartPage(driver, wait);
        addAnyProductToCart();
        assertTrue(cart.isOpened());
        cart.removeProduct();
        assertTrue(cart.isCartEmpty());
    }

    @Test
    public void userCanRestoreRemovedProduct() {
        CartPage cart = new CartPage(driver, wait);
        addAnyProductToCart();
        assertTrue(cart.isOpened());
        cart.removeProduct();
        cart.restoreProduct();
        assertTrue(cart.hasProducts());

    }

    @Test
    public void userCanApplyCoupon() {
        CartPage cart = new CartPage(driver, wait);
        addAnyProductToCart();
        assertTrue(cart.isOpened());
        cart.applyCoupon("sert500");
        assertTrue("Купон не был применён", cart.isCouponApplied());
    }
}
