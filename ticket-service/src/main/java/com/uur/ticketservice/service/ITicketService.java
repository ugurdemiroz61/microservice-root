package com.uur.ticketservice.service;

import com.uur.ticketservice.dto.TicketDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ITicketService {
    TicketDto save(TicketDto ticketDto);

    TicketDto update( TicketDto ticketDto);

    TicketDto getById(String ticketId);

    Slice<TicketDto> getPagination(Pageable pageable);
}
