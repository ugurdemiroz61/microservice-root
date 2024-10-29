package com.uur.ticketservice.dto;

import java.util.Date;

public class TicketDto {
    private String id;

    private String description;

    private String notes;

    private String assignee;

    private Date ticketDate;

    private String priorityType;

    private String ticketStatus;

    public TicketDto() {
    }

    public TicketDto(String id, String description, String notes, String assignee, Date ticketDate, String priorityType, String ticketStatus) {
        this.id = id;
        this.description = description;
        this.notes = notes;
        this.assignee = assignee;
        this.ticketDate = ticketDate;
        this.priorityType = priorityType;
        this.ticketStatus = ticketStatus;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }

    public String getAssignee() {
        return assignee;
    }

    public Date getTicketDate() {
        return ticketDate;
    }

    public String getPriorityType() {
        return priorityType;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    public void setPriorityType(String priorityType) {
        this.priorityType = priorityType;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
