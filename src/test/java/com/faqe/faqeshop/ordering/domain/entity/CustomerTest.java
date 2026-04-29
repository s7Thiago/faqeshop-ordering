package com.faqe.faqeshop.ordering.domain.entity;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.faqe.faqeshop.ordering.domain.utility.IdGenerator;

public class CustomerTest {

    @Test
    public void testingCustomer() {

        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "John Doe",
                LocalDate.of(1991, 1, 1),
                "john.doe@example.com",
                "1234567890",
                "12345678901",
                true,
                false,
                OffsetDateTime.now(),
                null,
                0);

        System.out.println("ID do cliente: " + customer.id());
        System.out.println("Novo ID teste: " + IdGenerator.generateTimeBasedUUID());

    }

    @Test
    public void given_invalidEmail_whenTryCreateCustomer_ShouldGenerateException() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Customer(
                        IdGenerator.generateTimeBasedUUID(),
                        "John Doe",
                        LocalDate.of(1991, 7, 5),
                        "invalid-email",
                        "1234567890",
                        "12345678901",
                        true,
                        false,
                        OffsetDateTime.now(),
                        null,
                        0))
                .withMessage(VALIDATION_ERROR_EMAIL_IS_INVALID);
    }

    @Test
    public void given_invalidEmail_whenTryUpdateCustomerEmail_ShouldGenerateException() {
        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "John Doe",
                LocalDate.of(1991, 7, 5),
                "john.doe@example.com",
                "1234567890",
                "12345678901",
                false,
                false,
                OffsetDateTime.now(),
                null,
                0);

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    customer.changeEmail("invalid-email");
                })
                .withMessage(VALIDATION_ERROR_EMAIL_IS_INVALID);
    }

    @Test
    void given_unarchivedCustomer_whenArchive_shouldAnonymize() {
        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "John Doe",
                LocalDate.of(1991, 7, 5),
                "john.doe@example.com",
                "1234567890",
                "12345678901",
                false,
                false,
                OffsetDateTime.now(),
                null,
                0);

        customer.archive();

        Assertions.assertWith(customer,
                c -> assertThat(c.fullName()).isEqualTo("Anonymous"),
                c -> assertThat(c.email()).isNotEqualTo("john.doe@example.com"),
                c -> assertThat(c.phone()).isEqualTo("000-000-0000"),
                c -> assertThat(c.document()).isEqualTo("000-00-000"),
                c -> assertThat(c.birthDate()).isNull());

    }
}
