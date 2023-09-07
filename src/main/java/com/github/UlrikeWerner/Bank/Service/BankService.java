package com.github.UlrikeWerner.Bank.Service;

import com.github.UlrikeWerner.Bank.Entities.Client;
import com.github.UlrikeWerner.Bank.Repo.AccountRepo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class BankService {
    private final Set<AccountRepo> bankAccounts = new HashSet<>();
    private final Set<Client> clientList = new HashSet<>();

    public Set<AccountRepo> getBankAccounts() {
        return bankAccounts;
    }

    public Set<Client> getClientList() {
        return clientList;
    }

    public void addClient(Client client) {
        clientList.add(client);
    }

    public UUID openAccount(Client client) {
        addClient(client);
        UUID newUuid = UUID.randomUUID();
        bankAccounts.add(new AccountRepo(newUuid, client));
        return newUuid;
    }

    public void transfer(UUID sourceAccountId, BigDecimal amount, UUID targetAccountId) {
        Optional<AccountRepo> optionalSourceAccount = findAccountById(sourceAccountId);
        Optional<AccountRepo> optionalTargetAccount = findAccountById(targetAccountId);
        if(optionalSourceAccount.isPresent() && optionalTargetAccount.isPresent()){
            optionalSourceAccount.get().withdraw(amount);
            optionalTargetAccount.get().deposit(amount);
        } else {
            System.out.println("Ein oder mehrere Konten konnten nicht gefunden werden!");
        }
    }

    public Optional<AccountRepo> findAccountById(UUID id) {
        return bankAccounts.stream().filter(account -> account.getAccoutId().equals(id)).findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankService that = (BankService) o;
        return Objects.equals(bankAccounts, that.bankAccounts) && Objects.equals(clientList, that.clientList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankAccounts, clientList);
    }

    @Override
    public String toString() {
        return "BankService{" +
                "bankAccounts=" + bankAccounts +
                ", clientList=" + clientList +
                '}';
    }
}
