package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL;

import java.util.Objects;

public record LoyaltyPoints(Integer value) implements Comparable<LoyaltyPoints> {

    public LoyaltyPoints() {
        this(0);
    }

    public LoyaltyPoints(Integer value) {
        Objects.requireNonNull(value, VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL);
        if (value < 0) {
            throw new IllegalArgumentException(VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE);
        }
        this.value = value;
    }

    public LoyaltyPoints add(Integer value) {
        return add(new LoyaltyPoints(value));
    }

    public LoyaltyPoints add(LoyaltyPoints loyaltyPoints) {
        Objects.requireNonNull(loyaltyPoints, VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL);
        if (loyaltyPoints.value() < 0) {
            throw new IllegalArgumentException(VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE);
        }

        return new LoyaltyPoints(this.value() + loyaltyPoints.value);
    }

    @Override
    public int compareTo(LoyaltyPoints other) {
        return this.value().compareTo(other.value());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
