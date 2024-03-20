package com.ecommerce.auth.domain.model.validation;

import com.ecommerce.auth.domain.model.RoleType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = false;
        for (RoleType roleType : RoleType.values()) {
            if (roleType.name().equals(value)) {
                valid = true;
                break;
            }
        }
        return value == null || valid;
    }

}
