package com.financial.loan.domain.entity;

import com.financial.loan.domain.entity.enums.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private final UUID id;
    private final String fullName;
    private final Role role;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static User create(
            String fullName,
            Role role) {

        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("fullName cannot be null or blank");
        }
        if (role == null) {
            throw new IllegalArgumentException("role cannot be null");
        }

        LocalDateTime now = LocalDateTime.now();

        return new User(
                UUID.randomUUID(),
                fullName,
                role,
                now,
                now);
    }
}
