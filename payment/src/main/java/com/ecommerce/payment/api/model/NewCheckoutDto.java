package com.ecommerce.payment.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewCheckoutDto {

    @NotBlank
    private String costumerId;

    @NotNull
    @PositiveOrZero
    private BigDecimal shipping;

}
