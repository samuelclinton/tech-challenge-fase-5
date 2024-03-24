package com.ecommerce.payment.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@Document(collection = "payments")
public class Payment {

    @MongoId
    private String id;

    private Set<CartItem> cartItems;
    private PaymentStatus paymentStatus;
    private BigDecimal subtotal;
    private BigDecimal shipping;
    private BigDecimal total;

    public void updateTotal() {
        this.total = this.subtotal.add(this.shipping);
    }

}
