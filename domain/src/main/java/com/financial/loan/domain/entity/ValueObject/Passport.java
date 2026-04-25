package com.financial.loan.domain.entity.ValueObject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Passport {
    String series ;
    String number ;
}
