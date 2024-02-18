package com.ecommerce.auth.domain.model.validation;

import com.ecommerce.auth.domain.model.Authority;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AuthorityValidator implements ConstraintValidator<ValidAuthority, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = false;
        for (Authority authority : Authority.values()) {
            if (authority.name().equals(value)) {
                valid = true;
                break;
            }
        }
        return value == null || valid;
    }

}
