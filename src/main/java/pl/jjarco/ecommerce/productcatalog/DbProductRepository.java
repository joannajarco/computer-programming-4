package pl.jjarco.ecommerce.productcatalog;

import java.util.List;

public class DbProductRepository implements ProductRepository {
    @Override
    public List<Product> allProducts() {
        return null;
    }

    @Override
    public void save(Product newProduct) {

    }

    @Override
    public Product loadProductById(String productId) {
        return null;
    }
}
