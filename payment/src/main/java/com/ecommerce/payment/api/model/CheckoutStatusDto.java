package com.ecommerce.payment.api.model;

import com.ecommerce.payment.domain.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutStatusDto {

    private String paymentId;
    private PaymentStatus paymentStatus;

}
