package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_FIRSTNAME_IS_BLANK;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_FIRSTNAME_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LASTNAME_IS_BLANK;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LASTNAME_IS_NULL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class FullNameTest {
    @Test
    void testFullName() {
        var fullName = new FullName("John", "Doe");
        assertThat(fullName).isNotNull();
    }

    @Test
    void testFullNameWithNullFirstName() {
        assertThatThrownBy(() -> new FullName(null, "Doe"))
                .isInstanceOf(NullPointerException.class)
                .hasMessage(VALIDATION_ERROR_FIRSTNAME_IS_NULL);
    }

    @Test
    void testFullNameWithNullLastName() {
        assertThatThrownBy(() -> new FullName("John", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage(VALIDATION_ERROR_LASTNAME_IS_NULL);
    }

    @Test
    void testFullNameWithBlankFirstName() {
        assertThatThrownBy(() -> new FullName("", "Doe"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(VALIDATION_ERROR_FIRSTNAME_IS_BLANK);
    }

    @Test
    void testFullNameWithBlankLastName() {
        assertThatThrownBy(() -> new FullName("John", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(VALIDATION_ERROR_LASTNAME_IS_BLANK);
    }

}
