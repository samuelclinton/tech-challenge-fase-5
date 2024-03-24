package com.ecommerce.shoppingcart.api.controller;

import com.ecommerce.shoppingcart.api.model.NewCartItemDto;
import com.ecommerce.shoppingcart.domain.model.ShoppingCart;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.ecommerce.shoppingcart.core.springdoc.SchemaExampleUtils.ID_EXAMPLE;

@Tag(name = "Carrinho de compras")
public interface ShoppingCartController {

    @Operation(
            summary = "Busca um carrinho de compras",
            description = "Busca um carrinho de compras"
    )
    ShoppingCart get(@Parameter(description = "O ID de um usuário",
            in = ParameterIn.PATH, example = ID_EXAMPLE) String costumerId);

    @Operation(
            summary = "Adiciona um item a um carrinho de compras",
            description = "Adiciona um item a um carrinho de compras"
    )
    ShoppingCart addItem(@Parameter(description = "O ID de um usuário",
            in = ParameterIn.PATH, example = ID_EXAMPLE) String costumerId, NewCartItemDto newCartItemDto);

    @Operation(
            summary = "Remove um item de um carrinho de compras",
            description = "Remove um item de um carrinho de compras"
    )
    ShoppingCart removeItem(@Parameter(description = "O ID de um usuário",
            in = ParameterIn.PATH, example = ID_EXAMPLE) String costumerId,
                            @Parameter(description = "O ID de um item",
                                    in = ParameterIn.PATH, example = ID_EXAMPLE) String itemId);

    @Operation(
            summary = "Remove todos os itens de um carrinho de compras",
            description = "Remove todos os itens de um carrinho de compras"
    )
    ShoppingCart clear(String costumerId);

}
