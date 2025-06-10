CREATE table `product_catalog__products` (
              id VARCHAR(100) NOT NULL,
              name VARCHAR(50) NOT NULL,
              description VARCHAR(144) NOT NULL,
              cover VARCHAR(50),
              price DECIMAL(12,2),
              PRIMARY KEY(id)
            );