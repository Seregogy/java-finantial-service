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

    public static ApplicationHistory create(
            UUID applicationId,
            Status oldStatus,
            Status newStatus,
            UUID changedBy,
            LocalDateTime changedAt) {

        if (applicationId == null) {
            throw new IllegalArgumentException("applicationId cannot be null");
        }
        if (oldStatus == null) {
            throw new IllegalArgumentException("oldStatus cannot be null");
        }
        if (newStatus == null) {
            throw new IllegalArgumentException("newStatus cannot be null");
        }
        if (changedBy == null) {
            throw new IllegalArgumentException("changedBy cannot be null");
        }

        LocalDateTime timestamp = changedAt != null ? changedAt : LocalDateTime.now();

        return new ApplicationHistory(
                UUID.randomUUID(),
                applicationId,
                oldStatus,
                newStatus,
                changedBy,
                timestamp);
    }
}
