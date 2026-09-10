package tests;

import org.junit.Test;
import pages.CatalogPage;
import pages.CartPage;

import static org.junit.Assert.assertTrue;

public class ShoppingCartTest extends BaseTest {

    @Test
    public void userCanRemoveProductFromCart() {
        CatalogPage catalog = new CatalogPage(driver, wait);
        CartPage cart = new CartPage(driver, wait);
        catalog.openHomePage();
        catalog.openCatalogFromMenu();
        catalog.addProductToCart();
        catalog.clickDetailsButton();
        assertTrue(cart.isOpened());
        cart.removeProduct();
        assertTrue(cart.isCartEmpty());
    }

    @Test
    public void userCanRestoreRemovedProduct() {
        CatalogPage catalog = new CatalogPage(driver, wait);
        CartPage cart = new CartPage(driver, wait);
        catalog.openHomePage();
        catalog.openCatalogFromMenu();
        catalog.addProductToCart();
        catalog.clickDetailsButton();
        assertTrue(cart.isOpened());
        cart.removeProduct();
        cart.restoreProduct();
        assertTrue(cart.hasProducts());

    }

    @Test
    public void userCanApplyCoupon() {
        CatalogPage catalog = new CatalogPage(driver, wait);
        CartPage cart = new CartPage(driver, wait);
        catalog.openHomePage();
        catalog.openCatalogFromMenu();
        catalog.addProductToCart();
        catalog.clickDetailsButton();
        assertTrue(cart.isOpened());
        cart.applyCoupon("sert500");
    }

}
