package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_PHONE_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_PHONE_IS_NULL_OR_EMPTY;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    void testPhoneNull() {
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Phone(null))
                .withMessage(VALIDATION_ERROR_PHONE_IS_NULL);
    }

    @Test
    void testPhoneEmpty() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Phone(""))
                .withMessage(VALIDATION_ERROR_PHONE_IS_NULL_OR_EMPTY);
    }

}
