package com.ecommerce.shoppingcart.core.cloud.client;

import com.ecommerce.shoppingcart.api.model.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "item-service-client",
        url = "http://localhost:8081",
        configuration = ClientConfiguration.class
)
public interface ItemServiceClient {

    @GetMapping("/items/{id}")
    Item getItem(@PathVariable String id);

}
