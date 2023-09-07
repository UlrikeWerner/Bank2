package com.github.UlrikeWerner.Bank.Service;

import com.github.UlrikeWerner.Bank.Entities.Client;
import com.github.UlrikeWerner.Bank.Repo.AccountRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class BankServiceTest {
    @Test
    void setHaveTheRightClientAfterAdding() {
        BankService bankService = new BankService();
        Client paul = new Client(UUID.randomUUID(), "Paul", "Klein");
        Set<Client> clients = Set.of(paul);
        bankService.addClient(paul);

        Set<Client> list = bankService.getClientList();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(clients, list);
    }

    @Test
    void setHaveTheRightAccountRepoAfterOpenAccount() {
        BankService bankService = new BankService();
        Client paul = new Client(UUID.randomUUID(), "Paul", "Klein");
        UUID paulsAccountUuid = bankService.openAccount(paul);

        Set<AccountRepo> list = bankService.getBankAccounts();
        Assertions.assertNotNull(list);
        Assertions.assertTrue(bankService.getBankAccounts().contains(new AccountRepo(paulsAccountUuid, paul)));
        System.out.println(list);
    }

    @Test
    void findAccountByIdReturnTheAccountOfPaul() {
        BankService bankService = new BankService();
        Client paul = new Client(UUID.randomUUID(), "Paul", "Klein");
        UUID paulsAccountUuid = bankService.openAccount(paul);

        Optional<AccountRepo> paulsAccount = bankService.findAccountById(paulsAccountUuid);

        Assertions.assertTrue(paulsAccount.isPresent());
        Assertions.assertEquals(paul, paulsAccount.get().getOwner());
    }

    @Test
    void findAccountByIdReturnEmptyOptionalWhenThereIsNoAccount() {
        BankService bankService = new BankService();

        Optional<AccountRepo> optionalAccount = bankService.findAccountById(UUID.randomUUID());

        Assertions.assertFalse(optionalAccount.isPresent());
    }

    @Test
    void afterTransferOfMoneyTheAccountsHaveTheRightSaldo() {
        BankService bankService = new BankService();
        Client paul = new Client(UUID.randomUUID(), "Paul", "Klein");
        Client peter = new Client(UUID.randomUUID(), "Peter", "Klein");
        UUID paulsAccountUuid = bankService.openAccount(paul);
        Optional<AccountRepo> paulsAccount = bankService.findAccountById(paulsAccountUuid);
        paulsAccount.ifPresent(accountRepo -> accountRepo.setSaldo(new BigDecimal(15)));
        UUID peterAccountUuid = bankService.openAccount(peter);
        Optional<AccountRepo> peterAccount = bankService.findAccountById(peterAccountUuid);

        bankService.transfer(paulsAccountUuid, new BigDecimal("5.5"), peterAccountUuid);

        BigDecimal paulSaldo = BigDecimal.ZERO;
        BigDecimal peterSaldo = BigDecimal.ZERO;

        if(paulsAccount.isPresent()){
            paulSaldo = paulsAccount.get().getSaldo();
        }
        if(peterAccount.isPresent()){
            peterSaldo = peterAccount.get().getSaldo();
        }

        Assertions.assertEquals(new BigDecimal("9.5"), paulSaldo);
        Assertions.assertEquals(new BigDecimal("5.5"), peterSaldo);
    }
}
