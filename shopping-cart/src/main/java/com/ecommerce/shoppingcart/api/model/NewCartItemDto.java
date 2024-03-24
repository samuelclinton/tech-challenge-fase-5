package com.ecommerce.shoppingcart.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import static com.ecommerce.shoppingcart.core.springdoc.SchemaExampleUtils.ID_EXAMPLE;

public class NewCartItemDto {

    @NotBlank
    @Schema(example = ID_EXAMPLE, description = "O ID do item a ser adicionado")
    private String itemId;

    @NotNull
    @PositiveOrZero
    private Integer amount;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
