package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CatalogPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CatalogPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private final By catalogTitle = By.cssSelector(".entry-title");
    private final By products = By.cssSelector("ul.products li.product");
    private final By resultCount = By.cssSelector(".woocommerce-result-count");
    private final By categories = By.cssSelector(".woocommerce_product_categories .product-categories li a");
    private final By paginationLinks = By.cssSelector(".woocommerce-pagination a.page-numbers");
    private final By firstProduct = By.cssSelector(".products li.product .collection_title");
    private final By addToCartButtons = By.cssSelector("a.add_to_cart_button.ajax_add_to_cart");
    private final By catalogMenu = By.cssSelector("#menu-item-46 > a");
    private final By addCartButton = By.cssSelector("a.add_to_cart_button.ajax_add_to_cart");
    private final By detailsButton = By.cssSelector("a.added_to_cart.wc-forward");

    public void open() {
        driver.get("https://intershop5.skillbox.ru/product-category/catalog/");
    }

    public void openHomePage() {
        driver.get("https://intershop5.skillbox.ru/");
    }

    public String getCatalogTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(catalogTitle)).getText();
    }

    public int getProductsCount() {
        return driver.findElements(products).size();
    }

    public String getResultCountText() {
        return driver.findElement(resultCount).getText();

    }

    public void openFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();

    }

    public int getCategoriesCount() {
        return driver.findElements(categories).size();
    }

    public void openFirstCategory() {
        driver.findElements(categories).get(0).click();

    }

    public void openSecondPage() {
        List<WebElement> pages = driver.findElements(paginationLinks);
        pages.get(1).click();
    }

    public void openCatalogFromMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(catalogMenu)).click();
    }

    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addCartButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(detailsButton));
    }

    public void clickDetailsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(detailsButton)).click();
    }

    public void addFirstProductToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (buttons.isEmpty()) {
            throw new RuntimeException(
                    "Нет товаров с кнопкой 'В корзину'"
            );
        }
        buttons.get(0).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(detailsButton));

    }
}
