package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_DOCUMENT_IS_NULL;

import java.util.Objects;

import com.faqe.faqeshop.ordering.domain.exception.ErrorMessages;

public record Document(String value) {
    public Document {
        Objects.requireNonNull(value, VALIDATION_ERROR_DOCUMENT_IS_NULL);
        if (value.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_DOCUMENT_IS_BLANK);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
