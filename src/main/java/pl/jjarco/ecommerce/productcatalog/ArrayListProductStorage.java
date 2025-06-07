package pl.jjarco.ecommerce.productcatalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListProductStorage implements ProductRepository {

    private final List<Product> products;//TECH

    public ArrayListProductStorage() {
        this.products = new ArrayList<>(); //TECH
    }

    @Override
    public List<Product> allProducts() {
        return Collections.unmodifiableList(products);  // TECH
    }

    @Override
    public void save(Product newProduct) {
        products.add(newProduct);
    }

    @Override
    public Product loadProductById(String productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .get();
    }
}
