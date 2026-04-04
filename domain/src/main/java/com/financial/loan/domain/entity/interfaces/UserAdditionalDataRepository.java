package com.financial.loan.domain.entity.interfaces;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface UserAdditionalDataRepository {
    UserAdditionalDataRepository getById(UUID userAdditionalDataId);

    UUID createUserAdditionalData(UserAdditionalDataRepository entity);

    UUID updateUserAdditionalData(
            UUID userId,
            LocalDateTime birthday,
            String password,
            BigDecimal monthlyIncome
    );

    UUID deleteUserAdditionalData(UUID userAdditionalDataId);
}
