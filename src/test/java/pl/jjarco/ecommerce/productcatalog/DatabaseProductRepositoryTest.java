package pl.jjarco.ecommerce.productcatalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DatabaseProductRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void  itQueryDb() {
        var sql = "select now() curr_time";
        var result = jdbcTemplate.queryForObject(sql, String.class);

        assert result.contains("2025");
    }

    @Test
    void itCreatesTables() {
        var sql = """
            create table `product__catalog` (
              id VARCHAR(100) NOT NULL,
              name VARCHAR(50) NOT NULL,
              PRIMARY KEY(id)
            );
        """;
        jdbcTemplate.execute(sql);
        jdbcTemplate.query();
        var result = jdbcTemplate.queryForObject("select count(*) from `product__catalog`", Integer.class);

        assert result == 0;
    }

    @Test
    void itStoresAndLoadsProduct() {
        Product product = thereIsProduct();
        ProductRepository repository = thereIsProductRepository();

        repository.save(product);
        Product loaded = repository.loadProductById(product.getId());

        assertEquals(product.getId(), loaded.getId());
        assertEquals(product.getName(), loaded.getDescription());
    }

    private ProductRepository thereIsProductRepository() {

        return new DbProductRepository();
    }

    private Product thereIsProduct() {
        return new Product(UUID.randomUUID(), "test", "desc");
    }

    @Test
    void itLoadsAllProducts() {
        Product product = thereIsProduct();
        ProductRepository repository = thereIsProductRepository();

        repository.save(product);
        List<Product> products = repository.allProducts();

        assertEquals(1, products.size());
    }
}
