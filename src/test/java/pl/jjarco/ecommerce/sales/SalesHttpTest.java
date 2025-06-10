package pl.jjarco.ecommerce.sales;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import pl.jjarco.ecommerce.productcatalog.ProductCatalog;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHttpTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Autowired
    ProductCatalog catalog;

    @Test
    void emptyOffer() {

        var toBeCalledURLOffer = getBaseURL("/api/current-offer");
        ResponseEntity<Offer> offerHttp = http.getForEntity(toBeCalledURLOffer, Offer.class);

        assertEquals(BigDecimal.ZERO, offerHttp.getBody().getTotal());
    }

    @Test
    void offerHappyPath() {
        //Arrange
        String productId = thereIsProduct("Product X", BigDecimal.valueOf(11));
        //curl -X POST http://localhost:9999/api/add-product/1234
        var toBeCalledURL = getBaseURL(String.format("/api/add/product/%s", productId));

        //Act
        http.postForEntity(toBeCalledURL, null, null);
        http.postForEntity(toBeCalledURL, null, null);

        var toBeCalledURLOffer = getBaseURL("/api/current-offer");
        ResponseEntity<Offer> offerHttp = http.getForEntity(toBeCalledURLOffer, Offer.class);

        assertEquals(BigDecimal.valueOf(22), offerHttp.getBody().getTotal());
    }

    private String thereIsProduct(String name, BigDecimal productPrice) {
        var id = catalog.createProduct(name, "nice one");
        catalog.changePrice(id, productPrice);

        return id;
    }

    private String getBaseURL(String endpoint) {
        return String.format("http://localhost:%s%s", port, endpoint);
    }
}
