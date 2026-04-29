package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_FIRSTNAME_IS_BLANK;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_FIRSTNAME_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LASTNAME_IS_BLANK;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LASTNAME_IS_NULL;

import java.util.Objects;

public record FullName(String firstName, String lastName) {

    public FullName (String firstName, String lastName) {
        this.firstName = Objects.requireNonNull(firstName, VALIDATION_ERROR_FIRSTNAME_IS_NULL).trim();
        this.lastName = Objects.requireNonNull(lastName, VALIDATION_ERROR_LASTNAME_IS_NULL).trim();

        if(firstName.isBlank()) {
            throw new IllegalArgumentException(VALIDATION_ERROR_FIRSTNAME_IS_BLANK);
        }

        if(lastName.isBlank()) {
            throw new IllegalArgumentException(VALIDATION_ERROR_LASTNAME_IS_BLANK);
        }

    }

}
