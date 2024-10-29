package com.uur.ticketservice.model.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "ticket")
public class TicketModel implements Serializable {

    @Id
    private String id;

    private String description;

    private String notes;

    private String assignee;

    private Date ticketDate;

    private String priorityType;

    private String ticketStatus;


    public TicketModel() {
    }

    public TicketModel(String ticketStatus, String priorityType, Date ticketDate, String assignee, String notes, String description, String id) {
        this.ticketStatus = ticketStatus;
        this.priorityType = priorityType;
        this.ticketDate = ticketDate;
        this.assignee = assignee;
        this.notes = notes;
        this.description = description;
        this.id = id;
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