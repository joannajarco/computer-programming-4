package pl.jjarco.productcatalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {

    @Test
    void itListAvailableProducts() {
        ProductCatalog catalog = thereIsProductCatalog();

        List<Product> products = catalog.allProducts();

        assert products.isEmpty();
    }

    @Test
    void itAllowsToCreateProduct() {
        ProductCatalog catalog = thereIsProductCatalog();

        catalog.createProduct("Lego set 8083", "nice one");

        List<Product> products = catalog.allProducts();
        assertFalse(products.isEmpty());
    }

    @Test
    void createdProductsAreUniquelyIdentifiable() {
        ProductCatalog catalog = thereIsProductCatalog();

        String productId1 = catalog.createProduct("Lego set 8083", "nice one");
        String productId2 = catalog.createProduct("Lego set 6285", "nice one");

        assertNotEquals(productId1, productId2);
    }

    @Test
    void itLoadsProductById() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId1 = catalog.createProduct("Lego set 8083", "nice one");

        Product loaded = catalog.loadProductById(productId1);

        assertEquals(productId1, loaded.getId());
        assertEquals("Lego set 8083", loaded.getName());
        assertEquals("nice one", loaded.getDescription());
    }

    @Test
    void allowsToApplyPrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.createProduct("Lego set 8083", "nice one");

        catalog.changePrice(productId, BigDecimal.valueOf(100.10));

        Product loaded =catalog.loadProductById(productId);
        assertEquals(BigDecimal.valueOf(100.10), loaded.getPrice());
    }

    @Test
    void denyToApplyPriceThatViolateMinimumRange() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.createProduct("Lego set 8083", "nice one");

        assertThrows(
                InvalidPriceException.class,
                () ->catalog.changePrice(productId, BigDecimal.valueOf(-10))
        );
    }

    @Test
    void allowsToApplyImage() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.createProduct("Lego set 8083", "nice one");

        catalog.changeImage(productId, "https://picsum.photos/id/237/200/300");

        Product loaded =catalog.loadProductById(productId);
        assertEquals("https://picsum.photos/id/237/200/300", loaded.getImage());
    }

    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(
                new HashMapProductRepository()
        );
    }

}
