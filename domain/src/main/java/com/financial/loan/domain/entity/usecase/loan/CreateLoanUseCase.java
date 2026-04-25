package com.financial.loan.domain.entity.usecase.loan;


import com.financial.loan.domain.entity.LoanApplication;
import com.financial.loan.domain.entity.Result;
import com.financial.loan.domain.entity.User;
import com.financial.loan.domain.entity.ValueObject.Passport;
import com.financial.loan.domain.entity.enums.Status;
import com.financial.loan.domain.entity.interfaces.LoanApplicationRepository;
import com.financial.loan.domain.entity.interfaces.UserAdditionalDataRepository;
import com.financial.loan.domain.entity.interfaces.UserRepository;
import com.financial.loan.domain.entity.usecase.user.GetUserByIdUseCase;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class CreateLoanUseCase {

    private final LoanApplicationRepository loanRepository;
    private final UserRepository userRepository;
    private final GetLoansByUserIdUseCase loanCase  ;
    private final UserAdditionalDataRepository _useraddrep;

    public Result<UUID> execute( UUID carId, UUID userId, BigDecimal loanAmount, BigDecimal firstPayment,  LocalDateTime term) {

        User user = userRepository.getUserById(userId);
        if (user == null) {
            return Result.failure("Пользователь не найден");
        }


        List<LoanApplication> userLoans = loanRepository.getByIdUser(user.getId());


        long activeLoans = userLoans.stream()
                .filter(loan -> loan.getStatus() != Status.REJECTED &&
                        loan.getStatus() != Status.EXPIRED)
                .count();

        if (activeLoans >= 10) {
            return Result.failure("Пользователь " + user.getId() + " подал слишком много активных заявок (максимум 10)");
        }


        Passport passportNumber = _useraddrep.getPassportByUserId(user.getId());

        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);

        boolean hasDuplicateInLast24Hours = userLoans.stream()
                .anyMatch(loan ->
                        loan.getCreated().isAfter(twentyFourHoursAgo) &&
                                _useraddrep.getPassportByUserId(loan.getUserId()).equals(passportNumber) &&
                                loan.getStatus() != Status.REJECTED &&
                                loan.getStatus() != Status.EXPIRED
                );

        if (hasDuplicateInLast24Hours) {
            return Result.failure("За последние 24 часа уже была подана заявка с паспортом " +
                    passportNumber + ". Пожалуйста, подождите перед подачей новой заявки.");
        }

        Result<LoanApplication> loanResult = LoanApplication.create(carId, userId, loanAmount, firstPayment , term);


        if (loanResult.isFailure()) {
            return  Result.failure(loanResult.getError());
        }

        LoanApplication loan = loanResult.getValue();

        loanRepository.create(loan);

        return Result.success(loan.getId());
    }
}
