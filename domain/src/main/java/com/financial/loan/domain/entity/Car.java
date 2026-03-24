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

    public static Result<Car> create(
            String brand,
            String model,
            LocalDateTime year,
            BigDecimal cost) {

        Result<String> validBrand = validateBrand(brand);
        if (validBrand.isFailure()) {
            return Result.failure(validBrand.getError());
        }

        Result<String> validModel = validateModel(model);
        if (validModel.isFailure()) {
            return Result.failure(validModel.getError());
        }

        Result<LocalDateTime> validYear = validateYear(year);
        if (validYear.isFailure()) {
            return Result.failure(validYear.getError());
        }

        Result<BigDecimal> validCost = validateCost(cost);
        if (validCost.isFailure()) {
            return Result.failure(validCost.getError());
        }

        LocalDateTime now = LocalDateTime.now();

        return Result.success(new Car(
                UUID.randomUUID(),
                brand,
                model,
                year,
                cost,
                now,
                now
        ));
    }

    private static Result<String> validateBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            return Result.failure("brand cannot be null or blank");
        }
        return Result.success(brand);
    }

    private static Result<String> validateModel(String model) {
        if (model == null || model.isBlank()) {
            return Result.failure("model cannot be null or blank");
        }
        return Result.success(model);
    }

    private static Result<LocalDateTime> validateYear(LocalDateTime year) {
        if (year == null) {
            return Result.failure("year cannot be null");
        }
        return Result.success(year);
    }

    private static Result<BigDecimal> validateCost(BigDecimal cost) {
        if (cost == null) {
            return Result.failure("cost cannot be null");
        }
        if (cost.compareTo(BigDecimal.ZERO) < 0) {
            return Result.failure("cost cannot be negative");
        }
        return Result.success(cost);
    }
}