package tests;

import org.junit.Before;
import org.junit.Test;
import pages.*;

import static org.junit.Assert.*;

public class HomePageTests extends BaseTest {
    private HomePage homePage;
    private SearchPage searchPage;
    private CartPage cartPage;
    private LoginPage loginPage;

    private static final String USERNAME = "Hirosaki234";
    private static final String PASSWORD = "3lfk3fm3eg";
    private static final String EMAIL = "hirosaki234@mail.ru";

    @Before
    public void openHomePage() {

        homePage = new HomePage(driver, wait);
        searchPage = new SearchPage(driver, wait);
        cartPage = new CartPage(driver, wait);
        homePage.open();
    }

    @Test
    public void accountPageShouldOpen() {
        ensureTestUserExists(USERNAME, EMAIL, PASSWORD);
        homePage.open();
        homePage.openAccount();
        loginPage = new LoginPage(driver, wait);
        loginPage.login(USERNAME, PASSWORD);
        homePage.openAccount();
        assertTrue("Раздел Мой аккаунт не открыт", driver.getCurrentUrl().contains("my-account"));
    }

    @Test
    public void searchExistingProductShouldReturnResult() {
        homePage.search("Книга");
        assertTrue(searchPage.hasProducts());
    }

    @Test
    public void searchForNonExistentProduct() {
        homePage.search("Волшебник");
        assertTrue(searchPage.hasNoProductsMessage());
    }

    @Test
    public void addingItemToTheShoppingCart() {
        homePage.openCatalog();
        CatalogPage catalogPage = new CatalogPage(driver, wait);
        catalogPage.addFirstProductToCart();
        homePage.openCart();
        assertTrue("Товар не добавился в корзину", cartPage.hasProducts());
    }

    @Test
    public void logoShouldReturnToMainPage() {
        homePage.openAccount();
        homePage.clickLogo();
        assertEquals("https://intershop5.skillbox.ru/", driver.getCurrentUrl());
    }

    @Test
    public void TheShoppingCartSectionIsOpen() {
        homePage.openCart();
        assertTrue("Раздел корзина не открыт", cartPage.isOpened());
    }

    @Test
    public void clickOnTheBooksSection() {
        homePage.openBooks();
        assertTrue("Раздел книги не открыт", searchPage.isBooksPageOpened());
    }
}
