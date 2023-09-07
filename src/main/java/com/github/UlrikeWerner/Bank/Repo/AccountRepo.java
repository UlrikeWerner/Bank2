package com.github.UlrikeWerner.Bank.Repo;

import com.github.UlrikeWerner.Bank.Entities.Client;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class AccountRepo {
    private final UUID accoutId;
    private BigDecimal saldo = BigDecimal.ZERO;
    private Client owner;

    public AccountRepo(UUID id, Client owner) {
        accoutId = id;
        this.owner = owner;
    }

    public AccountRepo(UUID id, Client owner, BigDecimal saldo) {
        accoutId = id;
        this.owner = owner;
        this.saldo = saldo;
    }

    public UUID getAccoutId() {
        return accoutId;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public void deposit(BigDecimal amount) {
        saldo = saldo.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        saldo = saldo.subtract(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountRepo accountRepo = (AccountRepo) o;
        return Objects.equals(accoutId, accountRepo.accoutId) && Objects.equals(saldo, accountRepo.saldo) && Objects.equals(owner, accountRepo.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accoutId, saldo, owner);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accoutId=" + accoutId +
                ", saldo=" + saldo +
                ", owner=" + owner +
                '}';
    }

}
