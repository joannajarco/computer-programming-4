package pl.jjarco.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.jjarco.productcatalog.ArrayListProductStorage;
import pl.jjarco.productcatalog.ProductCatalog;


@SpringBootApplication
public class App {

    public static void main(String[] args) {
        System.out.println("It works");
        SpringApplication.run(App.class, args);
    }

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
