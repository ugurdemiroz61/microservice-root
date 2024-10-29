package com.uur.ticketservice.service;

import com.uur.ticketservice.model.Ticket;

public interface ITicketNotificationService {
    void sendToQueue(Ticket ticket);
}
