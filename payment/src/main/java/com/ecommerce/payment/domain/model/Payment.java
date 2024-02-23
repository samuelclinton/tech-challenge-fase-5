package com.ecommerce.payment.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.Set;

@Document(collection = "payments")
public class Payment {

    @MongoId
    private String id;
    private Set<Item> items;
    private PaymentStatus paymentStatus;
    private BigDecimal subtotal;
    private BigDecimal shipping;
    private BigDecimal total;

    public Payment(String id, Set<Item> items, PaymentStatus paymentStatus, BigDecimal subtotal, BigDecimal shipping) {
        this.id = id;
        this.items = items;
        this.paymentStatus = paymentStatus;
        this.subtotal = subtotal;
        this.shipping = shipping;
        this.total = subtotal.add(shipping);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getId() {
        return id;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
