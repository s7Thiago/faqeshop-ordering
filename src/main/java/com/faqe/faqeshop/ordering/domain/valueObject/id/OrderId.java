package com.faqe.faqeshop.ordering.domain.valueObject.id;

import java.util.Objects;

import com.faqe.faqeshop.ordering.domain.exception.ErrorMessages;
import io.hypersistence.tsid.TSID;

public record OrderId(TSID value) {

    public OrderId {
        Objects.requireNonNull(value, ErrorMessages.VALIDATION_ERROR_ORDERID_IS_NULL);
    }

    public OrderId(Long value) {
        this(TSID.from(value));
    }

    public OrderId(String value) {
        this(TSID.from(value));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    
}
