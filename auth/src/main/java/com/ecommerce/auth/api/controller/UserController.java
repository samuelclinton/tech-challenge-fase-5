package com.ecommerce.auth.api.controller;

import com.ecommerce.auth.api.model.ChangeAuthorityDto;
import com.ecommerce.auth.api.model.ChangeEmailDto;
import com.ecommerce.auth.api.model.ChangePasswordDto;
import com.ecommerce.auth.domain.model.User;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.ecommerce.auth.core.springdoc.SchemaExampleUtils.ID_EXAMPLE;

@Tag(name = "User")
public interface UserController {

    User changeEmail(@Parameter(description = "O ID de um usuário", in = ParameterIn.PATH, example = ID_EXAMPLE) String userId,
                     ChangeEmailDto changeEmailDto);
    void changePassword(@Parameter(description = "O ID de um usuário", in = ParameterIn.PATH, example = ID_EXAMPLE) String userId,
                        ChangePasswordDto changePasswordDto);

    User changeAuthority(@Parameter(description = "O ID de um usuário", in = ParameterIn.PATH, example = ID_EXAMPLE) String userId,
                         ChangeAuthorityDto changeAuthorityDto);

    void delete(@Parameter(description = "O ID de um usuário", in = ParameterIn.PATH, example = ID_EXAMPLE) String userId);

}
