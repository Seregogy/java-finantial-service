package com.financial.loan.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAdditionalData {
    private final UUID id;
    private final UUID userId;
    private final Timestamp birthday;
    private final String password;
    private final Double monthlyIncome;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    public static UserAdditionalData create(UUID userId,
                                            Timestamp birthday,
                                            String password,
                                            Double monthlyIncome){
        if (userId == null){
            throw new IllegalArgumentException("userId cannot be null");
        }
        if (password == null || password.isBlank()){
            throw new IllegalArgumentException("password cannot be blank");
        }
        if (monthlyIncome != null && monthlyIncome < 0){
            throw new IllegalArgumentException("monthlyIncome cannot be negative");
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());

        return new UserAdditionalData(UUID.randomUUID(),
                userId,
                birthday,
                password,
                monthlyIncome,
                now,
                now);
    }
}
