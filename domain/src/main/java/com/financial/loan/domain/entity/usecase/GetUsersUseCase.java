package com.financial.loan.domain.entity.usecase;

import com.financial.loan.domain.entity.User;
import com.financial.loan.domain.entity.interfaces.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class GetUsersUseCase {
    private final UserRepository userRepository;

    public List<User> execute() {
        return userRepository.getUsers();
    }
}
