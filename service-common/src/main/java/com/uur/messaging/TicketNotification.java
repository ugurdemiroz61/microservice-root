package com.uur.messaging;

import java.io.Serializable;

public class TicketNotification  implements Serializable {
    private String ticketId;
    private String accountId;
    private String ticketDescription;

    public TicketNotification() {
    }

    public TicketNotification(String ticketId, String accountId, String ticketDescription) {
        this.ticketId = ticketId;
        this.accountId = accountId;
        this.ticketDescription = ticketDescription;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    @Override
    public String toString() {
        return "TicketNotification{" +
                "ticketId='" + ticketId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", ticketDescription='" + ticketDescription + '\'' +
                '}';
    }
}
