package com.financial.loan.domain.usecase.car;

import com.financial.loan.domain.entity.Car;
import com.financial.loan.domain.entity.Result;
import com.financial.loan.domain.repository.CarRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
public class CreateCarUseCase {
    private final ICarRepository carRepository;

    /**
     * @param brand Марка
     * @param model Модель
     * @param year Год выпуска
     * @param cost Стоимость
     * @return Результат с объектом Car или ошибкой валидации
     */
    public Result<Car> execute(String brand, String model, LocalDateTime year, BigDecimal cost) {
        
        Result<Car> carResult = Car.create(brand, model, year, cost);
        
        if (carResult.isFailure()) {
            return carResult;
        }

        Car car = carResult.getValue();

        carRepository.save(car);
        return Result.success(car);
    }
}
