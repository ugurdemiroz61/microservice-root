package com.uur.ticketservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "ticket")


public class Ticket extends BaseEntityModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;


    @Column(name = "description", length = 600)
    private String description;


    @Column(name = "notes", length = 1000)
    private String notes;


    @Column(name = "assignee", length = 50)
    private String assignee;


    @Column(name = "ticket_date")
    private Date ticketDate;


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "priority_type")
    private PriorityType priorityType;


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ticket_status")
    private TicketStatus ticketStatus;


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

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
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

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Ticket() {
    }

    public Ticket(TicketStatus ticketStatus, PriorityType priorityType, Date ticketDate, String assignee, String notes, String description, String id) {
        this.ticketStatus = ticketStatus;
        this.priorityType = priorityType;
        this.ticketDate = ticketDate;
        this.assignee = assignee;
        this.notes = notes;
        this.description = description;
        this.id = id;
    }
}
