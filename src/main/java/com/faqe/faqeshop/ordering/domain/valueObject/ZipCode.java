package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_ZIPCODE_IS_BLANK;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_ZIPCODE_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_ZIPCODE_SHORT;

import java.util.Objects;

public record ZipCode(String value) {

    public ZipCode {
        Objects.requireNonNull(value, VALIDATION_ERROR_ZIPCODE_IS_NULL);

        if (value.isBlank()) {
            throw new IllegalArgumentException(VALIDATION_ERROR_ZIPCODE_IS_BLANK);
        }

        if (value.length() < 5) {
            throw new IllegalArgumentException(VALIDATION_ERROR_ZIPCODE_SHORT);
        }
    }

    @Override
    public String toString() {
        return value;
    }

}
