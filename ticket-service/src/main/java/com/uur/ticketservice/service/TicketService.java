package com.uur.ticketservice.service;

import com.uur.client.AccountServiceClient;
import com.uur.client.contract.AccountDto;
import com.uur.ticketservice.dto.TicketDto;
import com.uur.ticketservice.model.PriorityType;
import com.uur.ticketservice.model.Ticket;
import com.uur.ticketservice.model.TicketStatus;
import com.uur.ticketservice.model.es.TicketModel;
import com.uur.ticketservice.repository.TicketRepository;
import com.uur.ticketservice.repository.es.TicketElasticRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class TicketService implements ITicketService {

    private final TicketElasticRepository ticketElasticRepository;
    private final TicketRepository ticketRepository;
    private final ITicketNotificationService ticketNotificationService;
    private final AccountServiceClient accountServiceClient;
    private final ModelMapper modelMapper;

    public TicketService(TicketElasticRepository ticketElasticRepository, TicketRepository ticketRepository, ITicketNotificationService ticketNotificationService, AccountServiceClient accountServiceClient, ModelMapper modelMapper) {
        this.ticketElasticRepository = ticketElasticRepository;
        this.ticketRepository = ticketRepository;
        this.ticketNotificationService = ticketNotificationService;
        this.accountServiceClient = accountServiceClient;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        // Ticket Entity
        if (ticketDto.getDescription() == null)
            throw new IllegalArgumentException("Description bos olamaz");

        Ticket ticket = new Ticket();
        ResponseEntity<AccountDto> accountDtoResponseEntity = accountServiceClient.get(ticketDto.getAssignee());

        ticket.setDescription(ticketDto.getDescription());
        ticket.setNotes(ticketDto.getNotes());
        ticket.setTicketDate(ticketDto.getTicketDate());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus().toUpperCase()));
        ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType().toUpperCase()));
        ticket.setAssignee(Objects.requireNonNull(accountDtoResponseEntity.getBody()).getId());

        ticket = ticketRepository.save(ticket);

        TicketModel model = new TicketModel();
        model.setDescription(ticket.getDescription());
        model.setNotes(ticket.getNotes());
        model.setId(ticket.getId());
        model.setAssignee(accountDtoResponseEntity.getBody().getNameSurname());
        model.setPriorityType(ticket.getPriorityType().toString());
        model.setTicketStatus(ticket.getTicketStatus().toString());
        model.setTicketDate(ticket.getTicketDate());

        ticketElasticRepository.save(model);

        ticketDto.setId(ticket.getId());
        // Kuyruga notification yaz
        ticketNotificationService.sendToQueue(ticket);
        return ticketDto;
    }

    @Override
    public TicketDto update(TicketDto ticketDto) {
        Ticket ticket = ticketRepository.findById(ticketDto.getId())
                .orElseThrow(IllegalArgumentException::new);
        ticket.setNotes(ticketDto.getNotes());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
        ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));
        ticket = ticketRepository.save(ticket);
        TicketModel model = new TicketModel();
        model.setDescription(ticket.getDescription());
        model.setNotes(ticket.getNotes());
        model.setId(ticket.getId());
        model.setAssignee(ticket.getAssignee());
        model.setPriorityType(ticket.getPriorityType().toString());
        model.setTicketStatus(ticket.getTicketStatus().toString());
        model.setTicketDate(ticket.getTicketDate());
        ticketElasticRepository.save(model);
        return modelMapper.map(ticket, TicketDto.class);
    }

    @Override
    public TicketDto getById(String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(ticket, TicketDto.class);
    }

    @Override
    public Slice<TicketDto> getPagination(Pageable pageable) {
        Slice<Ticket> tickets = ticketRepository.findAll(pageable);
        return tickets.map(ticket -> modelMapper.map(ticket, TicketDto.class));
    }
}
