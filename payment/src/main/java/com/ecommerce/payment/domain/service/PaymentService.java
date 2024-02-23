package com.ecommerce.payment.domain.service;

import com.ecommerce.payment.api.model.CreditCard;
import com.ecommerce.payment.domain.model.Payment;
import com.ecommerce.payment.domain.model.PaymentStatus;
import com.ecommerce.payment.domain.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Mono<Payment> pay(String paymentId, CreditCard creditCard) {
        return paymentRepository.findById(paymentId)
                .flatMap(payment -> {
                    logger.info(String.format("Simulando chamada de pagamento com id %s para o cartão %s",
                            paymentId, creditCard.getNumber()));
                    payment.setPaymentStatus(PaymentStatus.SUCCESS);
                    return paymentRepository.save(payment);
                });
    }

}
