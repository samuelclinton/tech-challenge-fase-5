package com.ecommerce.payment.api.controller;

import com.ecommerce.payment.api.model.CheckoutStatusDto;
import com.ecommerce.payment.api.model.CreditCard;
import com.ecommerce.payment.api.model.NewCheckoutDto;
import com.ecommerce.payment.domain.model.Payment;
import com.ecommerce.payment.domain.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment checkout(@RequestBody @Valid NewCheckoutDto newCheckoutDto) {
        return paymentService.checkout(newCheckoutDto);
    }

    @PutMapping("/{paymentId}/pay")
    public CheckoutStatusDto pay(@PathVariable String paymentId, @RequestBody @Valid CreditCard creditCard) {
        Payment payment = paymentService.pay(paymentId, creditCard);
        return CheckoutStatusDto.builder()
                .paymentId(payment.getId())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

}
