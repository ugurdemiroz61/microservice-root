package com.uur.notificationservice.notification.service;

import com.uur.messaging.TicketNotification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationDistributionService {
    @RabbitListener(queues ="#{@environment.getProperty('msrabbitmq.queue')}")
    public void onNotification(TicketNotification ticketNotification) {
        System.out.println("———————————————————————————————————————————");
        System.out.println("Notification Alindi Kullanicilara gönderiliyor.");
        System.out.println("Notification -> " + ticketNotification.toString());
    }
}