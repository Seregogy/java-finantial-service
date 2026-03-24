package com.financial.loan.domain.entity;

import com.financial.loan.domain.entity.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanApplication {
    private final UUID id;
    private final UUID carId;
    private final UUID userId;
    private final BigDecimal loanAmount;
    private final BigDecimal firstPayment;
    private final LocalDateTime term;
    private final Status status;
    private final LocalDateTime created;
    private final LocalDateTime updated;

    public static LoanApplication create(
            UUID carId,
            UUID userId,
            BigDecimal loanAmount,
            BigDecimal firstPayment,
            LocalDateTime term) {

        if (carId == null) {
            throw new IllegalArgumentException("carId cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        if (loanAmount == null || loanAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("loanAmount cannot be null negative");
        }
        if (firstPayment == null || firstPayment.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("firstPayment cannot be null or negative");
        }
        if (firstPayment.compareTo(loanAmount) > 0) {
            throw new IllegalArgumentException("firstPayment cannot be greater than loanAmount");
        }
        if (term == null) {
            throw new IllegalArgumentException("term cannot be null");
        }

        LocalDateTime now = LocalDateTime.now();

        return new LoanApplication(
                UUID.randomUUID(),
                carId,
                userId,
                loanAmount,
                firstPayment,
                term,
                Status.NEW,
                now,
                now);
    }
}
