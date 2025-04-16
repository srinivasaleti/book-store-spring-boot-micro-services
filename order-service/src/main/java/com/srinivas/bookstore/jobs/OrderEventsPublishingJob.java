package com.srinivas.bookstore.jobs;

import com.srinivas.bookstore.domain.OrderEventService;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsPublishingJob {

    private static final Logger logger = LoggerFactory.getLogger(OrderEventsPublishingJob.class);

    private final OrderEventService orderEventService;

    public OrderEventsPublishingJob(OrderEventService orderService) {
        this.orderEventService = orderService;
    }

    @Scheduled(cron = "${order.events.publish.cron}")
    @SchedulerLock(name = "publishNewOrders")
    public void publishNewOrders() {
        LockAssert.assertLocked();
        logger.info("Starting to publish order events...");
        orderEventService.publishOrderEvents();
        logger.info("Order events published successfully.");
    }
}
