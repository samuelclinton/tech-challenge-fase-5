package com.ecommerce.payment.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(type =  "string", example = "2030-12")
    private YearMonth expiration;

}
