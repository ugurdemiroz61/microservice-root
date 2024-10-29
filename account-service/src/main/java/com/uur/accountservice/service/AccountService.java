package com.uur.accountservice.service;

import com.uur.accountservice.entity.Account;
import com.uur.accountservice.repo.AccountRepository;
import com.uur.client.contract.AccountDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    public AccountDto get(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(account, AccountDto.class);
    }

    @Transactional
    public AccountDto save(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        account.setId(UUID.randomUUID().toString());
        account = accountRepository.save(account);
        accountDto.setId(account.getId());
        return accountDto;
    }

    @Transactional
    public AccountDto update(AccountDto accountDto) {
        if (accountDto.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        Optional<Account> account = accountRepository.findById(accountDto.getId());
        Account accountToUpdate = account.map(it -> {
            it.setBirthDate(accountDto.getBirthDate());
            it.setName(accountDto.getName());
            it.setSurname(accountDto.getSurname());
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(accountRepository.save(accountToUpdate), AccountDto.class);
    }

    @Transactional
    public void delete(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        accountRepository.delete(account);
    }

    public Slice<AccountDto> findAll(Pageable pageable) {
        Slice<Account> accounts = accountRepository.findAll(pageable);
        return accounts.map(account -> modelMapper.map(account, AccountDto.class));
    }
}