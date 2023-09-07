package com.github.UlrikeWerner.Bank.Entities;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Account {
    private UUID accoutId;
    private BigDecimal saldo;
    private Client owner;

    public UUID getAccoutId() {
        return accoutId;
    }

    public void setAccoutId(UUID accoutId) {
        this.accoutId = accoutId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accoutId, account.accoutId) && Objects.equals(saldo, account.saldo) && Objects.equals(owner, account.owner);
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
