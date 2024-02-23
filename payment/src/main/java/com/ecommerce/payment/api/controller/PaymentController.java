package com.ecommerce.payment.api.controller;

import com.ecommerce.payment.api.model.CheckoutStatusDto;
import com.ecommerce.payment.api.model.CreditCard;
import com.ecommerce.payment.domain.model.Payment;
import com.ecommerce.payment.domain.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{paymentId}")
    public Mono<CheckoutStatusDto> pay(@PathVariable String paymentId, @RequestBody @Valid CreditCard creditCard) {
        Mono<Payment> paymentMono = paymentService.pay(paymentId, creditCard);
        return paymentMono.flatMap(payment ->
                Mono.just(new CheckoutStatusDto(payment.getId(), payment.getPaymentStatus())));
    }

}
