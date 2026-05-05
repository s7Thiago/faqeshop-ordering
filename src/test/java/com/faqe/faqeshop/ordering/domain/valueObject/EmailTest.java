package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailTest {
    @Test
    void testEmailCannotBeInvalid() {
        var invalidEmail = "invalid-email";
        Assertions.assertThatThrownBy(() -> new Email(invalidEmail))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(VALIDATION_ERROR_EMAIL_IS_INVALID);
    }
}
