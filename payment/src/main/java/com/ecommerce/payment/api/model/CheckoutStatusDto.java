package com.ecommerce.payment.api.model;

import com.ecommerce.payment.domain.model.PaymentStatus;

public class CheckoutStatusDto {

    private String paymentId;
    private PaymentStatus paymentStatus;

    public CheckoutStatusDto(String paymentId, PaymentStatus paymentStatus) {
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
    }

}
