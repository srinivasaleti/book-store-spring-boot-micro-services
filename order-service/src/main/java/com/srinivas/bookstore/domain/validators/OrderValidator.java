package com.srinivas.bookstore.domain.validators;

import com.srinivas.bookstore.clients.catalog.CatalogServiceClient;
import com.srinivas.bookstore.clients.catalog.Product;
import com.srinivas.bookstore.exceptions.InvalidOrderException;
import com.srinivas.bookstore.domain.models.CreateOrderRequest;
import com.srinivas.bookstore.domain.models.OrderItem;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

    private static final Logger logger = LoggerFactory.getLogger(OrderValidator.class);

    private final CatalogServiceClient catalogServiceClient;

    public OrderValidator(CatalogServiceClient catalogServiceClient) {
        this.catalogServiceClient = catalogServiceClient;
    }

    public void validate(CreateOrderRequest request) {
        for (OrderItem item : request.orderItems()) {
            String code = item.getCode();
            int quantity = item.getQuantity();

            logger.debug("Validating order item",
                    StructuredArguments.keyValue("productCode", code),
                    StructuredArguments.keyValue("quantity", quantity)
            );

            if (quantity <= 0) {
                logger.error("Invalid quantity",
                        StructuredArguments.keyValue("productCode", code),
                        StructuredArguments.keyValue("quantity", quantity)
                );
                throw new InvalidOrderException("Quantity must be greater than 0 for productCode: " + code);
            }

            Product product = catalogServiceClient.getProductByCode(code).orElseThrow(() -> {
                logger.error("Invalid productCode",
                        StructuredArguments.keyValue("productCode", code)
                );
                return new InvalidOrderException("Invalid productCode: " + code);
            });

            if (item.getPrice().compareTo(product.getPrice()) != 0) {
                logger.error("Price mismatch",
                        StructuredArguments.keyValue("productCode", code),
                        StructuredArguments.keyValue("expectedPrice", product.getPrice()),
                        StructuredArguments.keyValue("actualPrice", item.getPrice())
                );
                throw new InvalidOrderException("Product price not matching");
            }

            logger.info("Validated product",
                    StructuredArguments.keyValue("productCode", code),
                    StructuredArguments.keyValue("quantity", quantity),
                    StructuredArguments.keyValue("price", item.getPrice())
            );
        }
    }
}
