package com.financial.loan.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Car{
    private final UUID id;
    private final String brand;
    private final String model;
    private final Timestamp year;
    private final Double cost;
    private final Timestamp createdAt;
    private final Timestamp uptatedAt;

    public static Car create(
            String brand,
            String model,
            Timestamp year,
            Double cost
    ){
        if (brand == null || brand.isBlank()){
            throw new IllegalArgumentException("brand cannot be null or blank");
        }
        if (model == null || model.isBlank()){
            throw new IllegalArgumentException("model cannot be null or blank");
        }
        if (year == null){
            throw new IllegalArgumentException("year cannot be null");
        }
        if (cost == null || cost < 0){
            throw new IllegalArgumentException("cost cannot be negative");
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());

        return new Car(UUID.randomUUID(),
                        brand,
                        model,
                        year,
                        cost,
                        now,
                        now);
    }
}
