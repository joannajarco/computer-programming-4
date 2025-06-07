package pl.jjarco.ecommerce.productcatalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductCatalogConfiguration {
    @Bean
    ProductCatalog createProductCatalog() {
        ProductCatalog catalog = new ProductCatalog(
                new ArrayListProductStorage()
        );

        catalog.createProduct("nice one 1", "desc");
        catalog.createProduct("nice one 2", "desc");
        catalog.createProduct("nice one 3", "desc");
        catalog.createProduct("nice one 4", "desc");

        return catalog;
    }
}
