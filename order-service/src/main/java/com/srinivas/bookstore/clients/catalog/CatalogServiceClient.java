package com.srinivas.bookstore.clients.catalog;

import com.srinivas.bookstore.config.ApplicationConfig;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.util.Optional;

@Configuration
public class CatalogServiceClient {

    private final RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(CatalogServiceClient.class);

    public CatalogServiceClient(ApplicationConfig applicationConfig) {
        this.restClient = RestClient.builder()
                .baseUrl(applicationConfig.catalogServiceUrl)
                .build();
    }

    public Optional<Product> getProductByCode(String code) {
        logger.info("Fetching product by productCode", StructuredArguments.keyValue("product_code", code));
        try {
            Product product = restClient.get().uri("/api/products/{code}", code).retrieve().body(Product.class);
            logger.info("Successfully fetched product",
                    StructuredArguments.keyValue("product_code", code),
                    StructuredArguments.keyValue("product", product));

            return Optional.ofNullable(product);

        } catch (HttpClientErrorException.NotFound e) {
            logger.warn("Product not found",
                    StructuredArguments.keyValue("product_code", code),
                    StructuredArguments.keyValue("error_message", e.getMessage()));

            return Optional.empty();

        } catch (HttpClientErrorException e) {
            logger.error("Error fetching product",
                    StructuredArguments.keyValue("product_code", code),
                    StructuredArguments.keyValue("http_status", e.getStatusCode()),
                    StructuredArguments.keyValue("response_body", e.getResponseBodyAsString()),
                    StructuredArguments.keyValue("error_message", e.getMessage()));
            throw e;
        }
    }
}
