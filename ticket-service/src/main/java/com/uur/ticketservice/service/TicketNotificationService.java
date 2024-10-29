package com.uur.ticketservice.service;

import com.uur.messaging.TicketNotification;
import com.uur.ticketservice.config.TicketConfiguration;
import com.uur.ticketservice.model.Ticket;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TicketNotificationService implements ITicketNotificationService {
    private final RabbitTemplate rabbitTemplate;

    @Value("${msrabbitmq.queue}")
    private String QUEUE_NAME;
    @Value("${msrabbitmq.exchange}")
    private String EXCHANGE_NAME;
    @Value("${msrabbitmq.routingKey}")
    private String ROUTING_KEY;

    public TicketNotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendToQueue(Ticket ticket) {
        TicketNotification ticketNotification =new TicketNotification();
        ticketNotification.setAccountId(ticket.getAssignee());
        ticketNotification.setTicketId(ticket.getId());
        ticketNotification.setTicketDescription(ticket.getDescription());
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, ticketNotification);
    }

}
