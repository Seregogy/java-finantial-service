package com.financial.loan.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Car {
    private final UUID id;
    private final String brand;
    private final String model;
    private final LocalDateTime year;
    private final BigDecimal cost;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static Car create(
            String brand,
            String model,
            LocalDateTime year,
            BigDecimal cost) {

        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("brand cannot be null or blank");
        }
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("model cannot be null or blank");
        }
        if (year == null) {
            throw new IllegalArgumentException("year cannot be null");
        }
        if (cost == null || cost.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("cost cannot be null or negative");
        }

        LocalDateTime now = LocalDateTime.now();

        return new Car(
                UUID.randomUUID(),
                brand,
                model,
                year,
                cost,
                now,
                now);
    }
}
