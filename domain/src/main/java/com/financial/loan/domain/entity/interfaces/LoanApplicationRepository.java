package com.financial.loan.domain.entity.interfaces;

import com.financial.loan.domain.entity.ApplicationHistory;
import com.financial.loan.domain.entity.LoanApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface LoanApplicationRepository {
    List<LoanApplication> getLoansApplication();

    LoanApplication getLoanApplicationById(UUID loanApplicationId);

    UUID createLoanApplication(ApplicationHistory entity);

    UUID updateLoansApplication(
            UUID carId,
            UUID userId,
            BigDecimal loanAmount,
            BigDecimal firstPayment,
            LocalDateTime term
    );

    UUID deleteLoanApplication(UUID loanApplicationId);
}
