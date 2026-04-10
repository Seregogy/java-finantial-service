package com.financial.loan.domain.entity.interfaces;

import com.financial.loan.domain.entity.ApplicationHistory;
import com.financial.loan.domain.entity.Car;

import java.util.List;
import java.util.UUID;

public interface ApplicationHistoryRepository
{
    public UUID create(ApplicationHistory entity);

    public List<ApplicationHistory> getAll();

    public Car  getByLoan(UUID idLoan);

    public UUID Delete(UUID id);

    public UUID Update(UUID idLoa , Car car);

}
