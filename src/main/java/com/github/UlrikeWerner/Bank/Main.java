package com.github.UlrikeWerner.Bank;

import com.github.UlrikeWerner.Bank.Entities.Client;
import com.github.UlrikeWerner.Bank.Service.BankService;

import java.math.BigDecimal;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Client dagobert = new Client(UUID.randomUUID(), "Dagobert", "Rich");
        Client donald = new Client(UUID.randomUUID(), "Donald", "Poor");
        BankService bankOfDuckhausen = new BankService();

        UUID dagobertAccountId = bankOfDuckhausen.openAccount(dagobert);
        UUID donaldAccountId = bankOfDuckhausen.openAccount(donald);

        bankOfDuckhausen.transfer(dagobertAccountId, BigDecimal.valueOf(100), donaldAccountId);

        System.out.println(bankOfDuckhausen);
    }
}
