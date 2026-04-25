package com.financial.loan.domain.entity.interfaces;

import com.financial.loan.domain.entity.ApplicationHistory;
import com.financial.loan.domain.entity.Car;
import com.financial.loan.domain.entity.LoanApplication;

import java.util.List;
import java.util.UUID;

public interface ApplicationHistoryRepository
{
    List<ApplicationHistory> getAll(int page, int size);

    ApplicationHistory getById(UUID ApplicationHistoryId);

    UUID create(ApplicationHistory entity);

    UUID update(UUID idLoan ,ApplicationHistory loan);

    UUID delete(UUID ApplicationHistoryId);

}
