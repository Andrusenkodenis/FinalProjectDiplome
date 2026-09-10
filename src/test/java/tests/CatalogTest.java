package tests;

import org.junit.Test;
import pages.CartPage;
import pages.CatalogPage;
import pages.ProductPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CatalogTest extends BaseTest {

    @Test
    public void catalogPageShouldBeOpened() {
        CatalogPage catalog = new CatalogPage(driver, wait);
        catalog.open();
        assertEquals("КАТАЛОГ", catalog.getCatalogTitle());
    }

    @Test
    public void productsShouldBeDisplayed() {
        CatalogPage catalog = new CatalogPage(driver, wait);
        catalog.open();
        assertTrue(catalog.getProductsCount() > 0);
    }

    @Test
    public void productCounterShouldBeDisplayed() {
        CatalogPage catalog = new CatalogPage(driver, wait);
        catalog.open();
        assertTrue(catalog.getResultCountText().contains("Отображение"));
    }

    @Test
    public void userCanOpenProductCard() {
        CatalogPage catalog = new CatalogPage(driver, wait);
        ProductPage product = new ProductPage(driver, wait);
        catalog.open();
        catalog.openFirstProduct();
        assertTrue(product.isOpened());
    }

    @Test
    public void userCanAddWashingMachineToCartAndCheckProduct() {
        CatalogPage catalog = new CatalogPage(driver, wait);
        CartPage cart = new CartPage(driver, wait);
        catalog.openHomePage();
        catalog.openCatalogFromMenu();
        catalog.addProductToCart();
        catalog.clickDetailsButton();
        assertTrue(cart.isOpened());
        assertTrue(cart.hasProducts());
    }
}
