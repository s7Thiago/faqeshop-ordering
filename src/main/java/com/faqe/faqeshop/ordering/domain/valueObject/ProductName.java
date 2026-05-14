package com.faqe.faqeshop.ordering.domain.valueObject;

import com.faqe.faqeshop.ordering.domain.exception.ErrorMessages;

import java.util.Objects;

public record ProductName(String value) {

	public ProductName {
		Objects.requireNonNull(value, ErrorMessages.VALIDATION_ERROR_PRODUCTNAME_IS_NULL);
		if (value.isBlank()) {
			throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_PRODUCTNAME_IS_BLANK);
		}
	}

	@Override
	public String toString() {
		return value;
	}

}
