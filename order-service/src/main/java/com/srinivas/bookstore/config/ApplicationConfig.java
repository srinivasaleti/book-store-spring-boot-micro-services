package com.srinivas.bookstore.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApplicationConfig {

    @Value("${rabbitmq.exchange}")
    public String exchange;

    @Value("${rabbitmq.queue.new}")
    public String queueNew;

    @Value("${rabbitmq.queue.cancel}")
    public String queueCancel;

    @Value("${rabbitmq.queue.error}")
    public String queueError;

    @Value("${rabbitmq.queue.delivered}")
    public String queueDelivered;

    @Value("${rabbitmq.routing.new}")
    public String routingNew;

    @Value("${rabbitmq.routing.cancel}")
    public String routingCancel;

    @Value("${rabbitmq.routing.error}")
    public String routingError;

    @Value("${rabbitmq.routing.delivered}")
    public String routingDelivered;

}
