package pl.jjarco.ecommerce.sales;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade createMySalesModule() {
        return new SalesFacade();
    }
}
