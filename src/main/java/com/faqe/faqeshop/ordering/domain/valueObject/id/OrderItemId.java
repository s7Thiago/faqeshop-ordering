package com.faqe.faqeshop.ordering.domain.valueObject.id;

import java.util.Objects;

import com.faqe.faqeshop.ordering.domain.exception.ErrorMessages;
import com.faqe.faqeshop.ordering.domain.utility.IdGenerator;

import io.hypersistence.tsid.TSID;

public record OrderItemId(TSID value) {

    public OrderItemId {
        Objects.requireNonNull(value, ErrorMessages.VALIDATION_ERROR_ORDERITEMID_IS_NULL);
    }

    public OrderItemId(Long value) {
        this(TSID.from(value));
    }

    public OrderItemId() {
        this(IdGenerator.generateTsid());
    }

    public OrderItemId(String value) {
        this(TSID.from(value));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    
}
