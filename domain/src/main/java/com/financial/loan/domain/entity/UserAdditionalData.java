package com.financial.loan.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAdditionalData {
    private final UUID id;
    private final UUID userId;
    private final LocalDateTime birthday;
    private final String password;
    private final BigDecimal monthlyIncome;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static UserAdditionalData create(
            UUID userId,
            LocalDateTime birthday,
            String password,
            BigDecimal monthlyIncome) {

        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be blank");
        }
        if (monthlyIncome == null || monthlyIncome.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("monthlyIncome cannot be null or negative");
        }

        LocalDateTime now = LocalDateTime.now();

        return new UserAdditionalData(
                UUID.randomUUID(),
                userId,
                birthday,
                password,
                monthlyIncome,
                now,
                now);
    }
}
