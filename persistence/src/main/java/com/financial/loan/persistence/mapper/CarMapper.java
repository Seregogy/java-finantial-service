package com.financial.loan.persistence.mapper;

import com.financial.loan.domain.entity.Car;
import com.financial.loan.persistence.model.tables.records.CarRecord;
import org.jooq.RecordMapper;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.jooq.Record;

public class CarMapper implements RecordMapper<Record, Car> {

    @Override
    public Car map(Record record) {

        String brand = record.get("brand", String.class);
        String model = record.get("model", String.class);
        Integer yearInt = record.get("year", Integer.class);
        Double costDouble = record.get("cost", Double.class);

        LocalDateTime yearAsDateTime = LocalDateTime.of(yearInt, 1, 1, 0, 0);
        BigDecimal costAsBigDecimal = BigDecimal.valueOf(costDouble);

        return Car.create(brand, model, yearAsDateTime, costAsBigDecimal).getValue();
    }
}