package com.financial.loan.domain.entity;

import com.financial.loan.domain.entity.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationHistory {
    private final UUID id;
    private final UUID applicationId;
    private final Status oldStatus;
    private final Status newStatus;
    private final UUID changedBy;
    private final LocalDateTime changedAt;

    public static Result<ApplicationHistory> create(
            UUID applicationId,
            Status oldStatus,
            Status newStatus,
            UUID changedBy,
            LocalDateTime changedAt) {


        Result<UUID> validId = validateApplicationId(applicationId);
        if (validId.isFailure()) {
            return Result.failure(validId.getError());
        }


        Result<Boolean> validStatuses = validateStatuses(oldStatus, newStatus);
        if (validStatuses.isFailure()) {
            return Result.failure(validStatuses.getError());
        }


        Result<UUID> validChangedBy = validateChangedBy(changedBy);
        if (validChangedBy.isFailure()) {
            return Result.failure(validChangedBy.getError());
        }


        LocalDateTime timestamp = changedAt != null ? changedAt : LocalDateTime.now();

        return Result.success(new ApplicationHistory(
                UUID.randomUUID(),
                applicationId,
                oldStatus,
                newStatus,
                changedBy,
                timestamp
        ));
    }

    private static Result<UUID> validateApplicationId(UUID applicationId) {
        if (applicationId == null) {
            return Result.failure("applicationId cannot be null");
        }
        return Result.success(applicationId);
    }

    private static Result<Boolean> validateStatuses(Status oldStatus, Status newStatus) {
        if (oldStatus == null) {
            return Result.failure("oldStatus cannot be null");
        }
        if (newStatus == null) {
            return Result.failure("newStatus cannot be null");
        }
        if (oldStatus == newStatus) {
            return Result.failure("oldStatus and newStatus cannot be the same");
        }
        return Result.success(true);
    }

    private static Result<UUID> validateChangedBy(UUID changedBy) {
        if (changedBy == null) {
            return Result.failure("changedBy cannot be null");
        }
        return Result.success(changedBy);
    }
}