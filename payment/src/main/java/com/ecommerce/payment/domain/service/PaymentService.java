package com.ecommerce.payment.domain.service;

import com.ecommerce.payment.api.model.CreditCard;
import com.ecommerce.payment.api.model.NewCheckoutDto;
import com.ecommerce.payment.core.cloud.client.ShoppingCartClient;
import com.ecommerce.payment.domain.model.Payment;
import com.ecommerce.payment.domain.model.PaymentStatus;
import com.ecommerce.payment.domain.model.ShoppingCart;
import com.ecommerce.payment.domain.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;
    private final ShoppingCartClient shoppingCartClient;

    public PaymentService(PaymentRepository paymentRepository, ShoppingCartClient shoppingCartClient) {
        this.paymentRepository = paymentRepository;
        this.shoppingCartClient = shoppingCartClient;
    }

    private Payment get(String id) {
        return paymentRepository.findById(id).orElseThrow();
    }

    public Payment checkout(NewCheckoutDto newCheckoutDto) {
        ShoppingCart shoppingCart = shoppingCartClient.getShoppingCart(newCheckoutDto.getCostumerId());
        Payment newPayment = Payment.builder()
                .cartItems(shoppingCart.getCartItems())
                .paymentStatus(PaymentStatus.PENDING)
                .shipping(newCheckoutDto.getShipping())
                .subtotal(shoppingCart.getSubtotal())
                .build();
        newPayment.updateTotal();
        return paymentRepository.save(newPayment);
    }

    public Payment pay(String paymentId, CreditCard creditCard) {
        Payment payment = get(paymentId);
        logger.info(String.format("Simulando chamada de pagamento com id %s para o cartão %s",
                paymentId, creditCard.getNumber()));
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        return paymentRepository.save(payment);
    }

}
