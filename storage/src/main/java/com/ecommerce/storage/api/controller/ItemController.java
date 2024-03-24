package com.ecommerce.storage.api.controller;

import com.ecommerce.storage.api.model.ChangePriceDto;
import com.ecommerce.storage.api.model.NewItemDto;
import com.ecommerce.storage.domain.model.Item;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;

import static com.ecommerce.storage.core.springdoc.SchemaExampleUtils.ID_EXAMPLE;

@Tag(name = "Item")
public interface ItemController {


    @Operation(
            summary = "Cadastra um usuário",
            description = "Cadastra um usuário"
    )
    Item get(@Parameter(description = "O ID de um item", in = ParameterIn.PATH, example = ID_EXAMPLE) String id);

    @Operation(
            summary = "Lista os itens",
            description = "Lista os itens"
    )
    Page<Item> list(
            @Parameter(description = "Quantidade de itens retornados por página",
                    in = ParameterIn.QUERY, example =  "5") int size,
            @Parameter(description = "Número da página",
                    in = ParameterIn.QUERY, example =  "1") int page,
            @Parameter(description = "Direção da ordenação da página (asc ou desc)",
                    in = ParameterIn.QUERY, example =  "desc") String sortDirection,
            @Parameter(description = "Nome do campo pelo qual a página será ordenada",
                    in = ParameterIn.QUERY, example =  "id") String sortProperty);

    @Operation(
            summary = "Cadastra um novo item",
            description = "Cadastra um novo item"
    )
    Item register(NewItemDto newItemDto);

    @Operation(
            summary = "Altera o preço de um item",
            description = "Altera o preço de um item"
    )
    Item changePrice(@Parameter(description = "O ID de um item", in = ParameterIn.PATH, example = ID_EXAMPLE) String itemId,
                     ChangePriceDto changePriceDto);

    @Operation(
            summary = "Exclui um item",
            description = "Exclui um item"
    )
    void delete(@Parameter(description = "O ID de um item", in = ParameterIn.PATH, example = ID_EXAMPLE) String itemId);

}
