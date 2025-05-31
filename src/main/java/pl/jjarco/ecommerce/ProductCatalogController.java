package pl.jjarco.ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jjarco.productcatalog.Product;
import pl.jjarco.productcatalog.ProductCatalog;

import java.util.List;

@RestController
public class ProductCatalogController {

    ProductCatalog productCatalog;

    public ProductCatalogController(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }


    @GetMapping("/api/version")
    String version() {
        return "v0.0.1"; //https://semver.org/
    }

    @GetMapping("/api/products")
    List<Product>products() {
        return productCatalog.allProducts();
    }

}
