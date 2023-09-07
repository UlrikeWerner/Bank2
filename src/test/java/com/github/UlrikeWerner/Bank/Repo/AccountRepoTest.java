package com.github.UlrikeWerner.Bank.Repo;

import com.github.UlrikeWerner.Bank.Entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountRepoTest {
    private static AccountRepo mockAccountRepo;
    @BeforeEach
    public void setUp() {
        UUID testAccountId = UUID.randomUUID();
        Client testAccountClient = new Client(UUID.randomUUID(), "Hans", "Glück");
        mockAccountRepo = new AccountRepo(testAccountId, testAccountClient);
    }
    @Test
    void saldoShouldBe5AfterDeposit5(){

        Assertions.assertEquals(new BigDecimal(0), mockAccountRepo.getSaldo());

        mockAccountRepo.deposit(new BigDecimal(5));
        Assertions.assertEquals(new BigDecimal(5), mockAccountRepo.getSaldo());
    }

    @Test
    void saldoShouldBe5Point7AfterDeposit5Point7(){

        Assertions.assertEquals(new BigDecimal(0), mockAccountRepo.getSaldo());

        mockAccountRepo.deposit(new BigDecimal("5.7"));
        Assertions.assertEquals(new BigDecimal("5.7"), mockAccountRepo.getSaldo());
    }

    @Test
    void withdraw2BySaldo5ShouldNewSaldoBeeing3(){
        mockAccountRepo.deposit(new BigDecimal(5));
        mockAccountRepo.withdraw(new BigDecimal(2));
        Assertions.assertEquals(new BigDecimal(3), mockAccountRepo.getSaldo());
    }

    @Test
    void withdraw2Point5BySaldo5ShouldNewSaldoBeeing2Point5(){
        mockAccountRepo.deposit(new BigDecimal(5));
        mockAccountRepo.withdraw(new BigDecimal("2.5"));
        Assertions.assertEquals(new BigDecimal("2.5"), mockAccountRepo.getSaldo());
    }

    @Test
    void withdrawMoreThenTheSaldoHaveSchouldGiveMinus(){
        mockAccountRepo.withdraw(new BigDecimal("2.5"));
        Assertions.assertEquals(new BigDecimal("-2.5"), mockAccountRepo.getSaldo());
    }

}
