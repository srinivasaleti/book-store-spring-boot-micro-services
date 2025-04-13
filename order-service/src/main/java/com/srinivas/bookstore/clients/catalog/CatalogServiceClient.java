package com.srinivas.bookstore.clients.catalog;

import com.srinivas.bookstore.config.ApplicationConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Configuration
public class CatalogServiceClient {

    private final RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(CatalogServiceClient.class);

    public CatalogServiceClient(ApplicationConfig applicationConfig) {
        var requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(2000);
        requestFactory.setReadTimeout(2000);

        this.restClient = RestClient.builder().baseUrl(applicationConfig.catalogServiceUrl).requestFactory(requestFactory).build();
    }

    @CircuitBreaker(name = "catalog-service")
    @Retry(name = "catalog-service", fallbackMethod = "getProductByCodeFallbackMethod")
    public Optional<Product> getProductByCode(String code) {
        logger.info("Fetching product by productCode", StructuredArguments.keyValue("product_code", code));
        Product product = restClient.get().uri("/api/products/{code}", code).retrieve().body(Product.class);
        logger.info("Successfully fetched product", StructuredArguments.keyValue("product_code", code), StructuredArguments.keyValue("product", product));
        return Optional.ofNullable(product);
    }

    public Optional<Product> getProductByCodeFallbackMethod(String code, Throwable throwable) {
        logger.info("getProductByCodeFallbackMethod", StructuredArguments.keyValue("product_code", code));
        return Optional.empty();
    }

    public Optional<Product> getProductByCodeCircuitBreakerFallbackMethod(String code, Throwable throwable) {
        logger.info("getProductByCodeCircuitBreakerFallbackMethod", StructuredArguments.keyValue("product_code", code));
        return Optional.empty();
    }
}
