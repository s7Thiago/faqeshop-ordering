package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_BIRTHDATE_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BirthDateTest {

    @Test
    void testBirthDateCannotBeNull() {
        Assertions.assertThatThrownBy(() -> new BirthDate(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage(VALIDATION_ERROR_BIRTHDATE_IS_NULL);
    }

    @Test
    void testBirthDateCannotBeInTheFuture() {
        var futureDate = LocalDate.now().plusDays(1);
        Assertions.assertThatThrownBy(() -> new BirthDate(futureDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST);
    }

    @Test
    void testAge() {
        var birthDate = new BirthDate(LocalDate.of(1990, 1, 1));
        Assertions.assertThat(birthDate.age()).isEqualTo(LocalDate.now().getYear() - 1990);
    }
}
