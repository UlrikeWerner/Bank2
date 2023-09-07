package com.github.UlrikeWerner.Bank.Entities;

import java.util.UUID;

public record Client(
        UUID id,
        String firstname,
        String lastname
) {
}
