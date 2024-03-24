package com.ecommerce.payment.core.cloud.client;

import com.ecommerce.payment.domain.model.ShoppingCart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "shopping-cart-client",
        url = "http://localhost:8082",
        configuration = ClientConfiguration.class)
public interface ShoppingCartClient {

    @GetMapping("/{costumerId}")
    ShoppingCart getShoppingCart(@PathVariable String costumerId);

}
