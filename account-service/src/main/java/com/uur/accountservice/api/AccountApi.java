package com.uur.accountservice.api;

import com.uur.accountservice.service.AccountService;
import com.uur.client.contract.AccountDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("account")
public class AccountApi {

    private final AccountService accountService;

    public AccountApi(AccountService accountService) {
        this.accountService = accountService;
    }

    @CircuitBreaker(name = "default", fallbackMethod = "getFallbackAccount")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> get(@PathVariable("id") String id) {
        AccountDto account = accountService.get(id);
        return ResponseEntity.ok(account);
    }

    @CircuitBreaker(name = "default", fallbackMethod = "saveFallbackAccount")
    @PostMapping
    public ResponseEntity<AccountDto> save(@RequestBody AccountDto account) {
        AccountDto savedAccount = accountService.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @CircuitBreaker(name = "default", fallbackMethod = "updateFallbackAccount")
    @PutMapping
    public ResponseEntity<AccountDto> update(@RequestBody AccountDto account) {
        AccountDto updatedAccount = accountService.update(account);
        return ResponseEntity.ok(updatedAccount);
    }

    @CircuitBreaker(name = "default", fallbackMethod = "deleteFallbackAccount")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @CircuitBreaker(name = "default", fallbackMethod = "getAllFallback")
    @GetMapping
    public ResponseEntity<Slice<AccountDto>> getAll(Pageable pageable) {
        Slice<AccountDto> accounts = accountService.findAll(pageable);
        return ResponseEntity.ok(accounts);
    }

    // Fallback methods
    public ResponseEntity<AccountDto> getFallbackAccount(String id, Throwable throwable) {
        AccountDto fallbackAccount = new AccountDto("", "Fallback Account", "No Data", "", "", null );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fallbackAccount);
    }

    public ResponseEntity<AccountDto> saveFallbackAccount(AccountDto account, Throwable throwable) {
        AccountDto fallbackAccount = new AccountDto("", "Fallback Save", "Failed to save", "", "", null );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fallbackAccount);
    }

    public ResponseEntity<AccountDto> updateFallbackAccount(AccountDto account, Throwable throwable) {
        AccountDto fallbackAccount = new AccountDto("", "Fallback Update", "Failed to update", "", "", null );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fallbackAccount);
    }

    public ResponseEntity<Void> deleteFallbackAccount(String id, Throwable throwable) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    public ResponseEntity<Slice<AccountDto>> getAllFallback(Pageable pageable, Throwable throwable) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }
}