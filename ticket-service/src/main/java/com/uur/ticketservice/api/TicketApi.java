package com.uur.ticketservice.api;

import com.uur.ticketservice.dto.TicketDto;
import com.uur.ticketservice.service.TicketService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/ticket")
@RestController
public class TicketApi {
    private final TicketService ticketService;

    public TicketApi(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "getByIdFallback")
    public ResponseEntity<TicketDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(ticketService.getById(id));
    }

    @PostMapping
   // @CircuitBreaker(name = "default", fallbackMethod = "createTicketFallback")
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.save(ticketDto));
    }

    @PutMapping("/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "updateTicketFallback")
    public ResponseEntity<TicketDto> updateTicket(@RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.update(ticketDto));
    }

    @GetMapping
    @CircuitBreaker(name = "default", fallbackMethod = "getAllFallback")
    public ResponseEntity<Slice<TicketDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(ticketService.getPagination(pageable));
    }

    // Fallback methods

    public ResponseEntity<TicketDto> getByIdFallback(String id, Throwable throwable) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new TicketDto("", "Service unavailable. Could not retrieve ticket with id: " + id, "", "", null, "", ""));
    }

    public ResponseEntity<TicketDto> createTicketFallback(TicketDto ticketDto, Throwable throwable) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new TicketDto("", "Service unavailable. Could not create ticket.", "", "", null, "", ""));
    }

    public ResponseEntity<TicketDto> updateTicketFallback(String id, TicketDto ticketDto, Throwable throwable) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new TicketDto("", "Service unavailable. Could not update ticket with id: " + id, "", "", null, "", ""));
    }

    public ResponseEntity<Page<TicketDto>> getAllFallback(Pageable pageable, Throwable throwable) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Page.empty());
    }
}
