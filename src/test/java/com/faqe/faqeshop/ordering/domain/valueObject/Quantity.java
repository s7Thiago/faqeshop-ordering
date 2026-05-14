package com.faqe.faqeshop.ordering.domain.valueObject;

import com.faqe.faqeshop.ordering.domain.exception.ErrorMessages;

import java.util.Objects;

public record Quantity(Integer value) implements Comparable<Quantity> {

	public static final Quantity ZERO = new Quantity(0);

	public Quantity {
		Objects.requireNonNull(value, ErrorMessages.VALIDATION_ERROR_MONEY_QUANTITY_IS_NULL);
		if (value < 0) {
			throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_MONEY_QUANTITY_CANNOT_BE_NEGATIVE);
		}
	}

	public Quantity add(Quantity other) {
		Objects.requireNonNull(other, ErrorMessages.VALIDATION_ERROR_MONEY_QUANTITY_IS_NULL);
		return new Quantity(Math.addExact(this.value, other.value));
	}

	@Override
	public int compareTo(Quantity other) {
		Objects.requireNonNull(other, ErrorMessages.VALIDATION_ERROR_MONEY_COMPARISON_WITH_NULL);
		return Integer.compare(this.value, other.value);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
