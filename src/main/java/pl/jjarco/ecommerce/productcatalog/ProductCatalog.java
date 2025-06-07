package pl.jjarco.ecommerce.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductCatalog {

    ProductRepository productRepository;

    public ProductCatalog(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> allProducts() {
        return productRepository.allProducts();  // TECH
    }

    public String createProduct(String name, String description) {
        var uuid = UUID.randomUUID();

        var newProduct = new Product(
                uuid,
                name,
                description
        );  // DOMAIN  // BUSINESS

        this.productRepository.save(newProduct);  // TECH

        return newProduct.getId();
    }

    public Product loadProductById(String productId) {
        return productRepository.loadProductById(productId);
    }

    public void changePrice(String productId, BigDecimal bigDecimal) {
        var product = productRepository.loadProductById(productId);

        if (bigDecimal.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPriceException();
        }  // DOMAIN

        product.changePrice(bigDecimal);
    }

    public void changeImage(String productId, String url) {
        var product = loadProductById(productId);
        product.setImage(url);
    }
}
