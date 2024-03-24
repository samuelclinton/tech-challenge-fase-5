package com.ecommerce.payment.api.controller;

import com.ecommerce.payment.api.model.CheckoutStatusDto;
import com.ecommerce.payment.api.model.CreditCard;
import com.ecommerce.payment.api.model.NewCheckoutDto;
import com.ecommerce.payment.domain.model.Payment;
import com.ecommerce.payment.domain.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static com.ecommerce.payment.core.springdoc.SchemaExampleUtils.ID_EXAMPLE;

@Tag(name = "Pagamento")
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(
            summary = "Cria um pagamento para um carrinho de compras",
            description = "Cria um pagamento para um carrinho de compras"
    )
    @PostMapping
    public Payment checkout(@RequestBody @Valid NewCheckoutDto newCheckoutDto) {
        return paymentService.checkout(newCheckoutDto);
    }

    @Operation(
            summary = "Realiza o pagamento",
            description = "Realiza o pagamento"
    )
    @PutMapping("/{paymentId}/pay")
    public CheckoutStatusDto pay(@Parameter(description = "O ID de um pagamento",
            in = ParameterIn.PATH, example = ID_EXAMPLE) @PathVariable String paymentId,
                                 @RequestBody @Valid CreditCard creditCard) {
        Payment payment = paymentService.pay(paymentId, creditCard);
        return CheckoutStatusDto.builder()
                .paymentId(payment.getId())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

}
