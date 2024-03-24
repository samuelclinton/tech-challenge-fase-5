package com.ecommerce.payment.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.YearMonth;

@Data
public class CreditCard {

    @NotBlank
    private String number;

    @NotBlank
    private String name;

    @NotBlank
    private String cvv;

    @NotNull
    private YearMonth expiration;

}
