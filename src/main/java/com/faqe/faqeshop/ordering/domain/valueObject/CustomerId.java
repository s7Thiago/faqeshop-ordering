package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_ID_IS_NULL;

import java.util.Objects;
import java.util.UUID;

import com.faqe.faqeshop.ordering.domain.utility.IdGenerator;

public record CustomerId(UUID value) {
    public CustomerId() {
        this(IdGenerator.generateTimeBasedUUID());
    }

    public CustomerId(UUID value) {
        this.value = Objects.requireNonNull(value, VALIDATION_ERROR_ID_IS_NULL);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    

}
