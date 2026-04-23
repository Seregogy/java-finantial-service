package com.financial.loan.domain.entity.interfaces;

import com.financial.loan.domain.entity.UserAdditionalData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface UserAdditionalDataRepository {
    UserAdditionalData getById(UUID userId);

    UUID createUserAdditionalData(
            LocalDateTime birthday,
            String passwordHash,
            BigDecimal monthlyIncome
    );

    UserAdditionalData updateUserAdditionalData(
        UserAdditionalData entity
    );

    UUID deleteUserAdditionalData(UUID userAdditionalDataId);
}
