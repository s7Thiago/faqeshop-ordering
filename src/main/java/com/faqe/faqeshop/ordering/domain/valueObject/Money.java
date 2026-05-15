package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_MONEY_COMPARISON_WITH_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_MONEY_DIVISION_BY_ZERO;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_MONEY_DIVISOR_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_MONEY_IS_NEGATIVE;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_MONEY_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_MONEY_QUANTITY_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_MONEY_QUANTITY_MUST_BE_POSITIVE;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record Money(BigDecimal value) implements Comparable<Money> {
    public static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money {
        Objects.requireNonNull(value, VALIDATION_ERROR_MONEY_IS_NULL);
        if (value.signum() < 0) {
            throw new IllegalArgumentException(VALIDATION_ERROR_MONEY_IS_NEGATIVE);
        }
        value = value.setScale(2, DEFAULT_ROUNDING);
    }

    public Money(String value) {
        this(new BigDecimal(value));
    }

    public Money multiply(Quantity quantity) {
        Objects.requireNonNull(quantity, VALIDATION_ERROR_MONEY_QUANTITY_IS_NULL);
        if (quantity.value() < 1) {
            throw new IllegalArgumentException(VALIDATION_ERROR_MONEY_QUANTITY_MUST_BE_POSITIVE);
        }
        return new Money(this.value.multiply(new BigDecimal(quantity.value())));
    }

    public Money add(Money other) {
        Objects.requireNonNull(other, VALIDATION_ERROR_MONEY_IS_NULL);
        return new Money(this.value.add(other.value));
    }

    public Money divide(Money other) {
        Objects.requireNonNull(other, VALIDATION_ERROR_MONEY_DIVISOR_IS_NULL);
        if (other.value.signum() == 0) {
            throw new IllegalArgumentException(VALIDATION_ERROR_MONEY_DIVISION_BY_ZERO);
        }
        return new Money(this.value.divide(other.value, 2, DEFAULT_ROUNDING));
    }

    @Override
    public int compareTo(Money other) {
        Objects.requireNonNull(other, VALIDATION_ERROR_MONEY_COMPARISON_WITH_NULL);
        return this.value.compareTo(other.value);
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }
}
