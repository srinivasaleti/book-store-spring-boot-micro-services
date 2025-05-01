package com.srinivas.bookstore.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  private final ApplicationConfig applicationConfig;

  public RabbitMQConfig(ApplicationConfig applicationConfig) {
    this.applicationConfig = applicationConfig;
  }

  // 1️⃣ Exchange
  @Bean
  public DirectExchange orderExchange() {
    return new DirectExchange(this.applicationConfig.exchange);
  }

  // 2️⃣ Queues
  @Bean
  public Queue newOrdersQueue() {
    return new Queue(this.applicationConfig.queueNew, true);
  }

  @Bean
  public Queue cancelledOrdersQueue() {
    return new Queue(this.applicationConfig.queueCancel, true);
  }

  @Bean
  public Queue errorOrdersQueue() {
    return new Queue(this.applicationConfig.queueError, true);
  }

  @Bean
  public Queue deliveredOrdersQueue() {
    return new Queue(this.applicationConfig.queueDelivered, true);
  }

  // 3️⃣ Bindings
  @Bean
  public Binding bindingNew(Queue newOrdersQueue, DirectExchange exchange) {
    return BindingBuilder.bind(newOrdersQueue).to(exchange).with(this.applicationConfig.routingNew);
  }

  @Bean
  public Binding bindingCancelled(Queue cancelledOrdersQueue, DirectExchange exchange) {
    return BindingBuilder.bind(cancelledOrdersQueue)
        .to(exchange)
        .with(this.applicationConfig.routingCancel);
  }

  @Bean
  public Binding bindingError(Queue errorOrdersQueue, DirectExchange exchange) {
    return BindingBuilder.bind(errorOrdersQueue)
        .to(exchange)
        .with(this.applicationConfig.routingError);
  }

  @Bean
  public Binding bindingDelivered(Queue deliveredOrdersQueue, DirectExchange exchange) {
    return BindingBuilder.bind(deliveredOrdersQueue)
        .to(exchange)
        .with(this.applicationConfig.routingDelivered);
  }
}
