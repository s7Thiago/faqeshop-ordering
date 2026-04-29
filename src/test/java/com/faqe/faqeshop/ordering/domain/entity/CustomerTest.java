package com.faqe.faqeshop.ordering.domain.entity;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.ERROR_CUSTOMER_ARCHIVED;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertWith;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

import com.faqe.faqeshop.ordering.domain.exception.CustomerArchivedException;
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
                assertThatExceptionOfType(IllegalArgumentException.class)
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

                assertThatExceptionOfType(IllegalArgumentException.class)
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

                assertWith(customer,
                                c -> assertThat(c.fullName()).isEqualTo("Anonymous"),
                                c -> assertThat(c.email()).isNotEqualTo("john.doe@example.com"),
                                c -> assertThat(c.phone()).isEqualTo("000-000-0000"),
                                c -> assertThat(c.document()).isEqualTo("000-00-000"),
                                c -> assertThat(c.isPromotionalNotificationsAllowed()).isFalse(),
                                c -> assertThat(c.birthDate()).isNull());

        }

        @Test
        void given_archivedCustomer_whenTryToArchive_shouldGenerateException() {
                Customer customer = new Customer(
                                IdGenerator.generateTimeBasedUUID(),
                                "John Doe",
                                LocalDate.of(1991, 7, 5),
                                "john.doe@example.com",
                                "1234567890",
                                "12345678901",
                                false,
                                true,
                                OffsetDateTime.now(),
                                OffsetDateTime.now(),
                                0);

                assertThatExceptionOfType(CustomerArchivedException.class)
                                .isThrownBy(customer::archive)
                                .withMessage(ERROR_CUSTOMER_ARCHIVED);
        }

        @Test
        void given_archivedCustomer_whenTryToUpdate_shouldGenerateException() {
                Customer customer = new Customer(
                                IdGenerator.generateTimeBasedUUID(),
                                "John Doe",
                                LocalDate.of(1991, 7, 5),
                                "john.doe@example.com",
                                "1234567890",
                                "12345678901",
                                false,
                                true,
                                OffsetDateTime.now(),
                                OffsetDateTime.now(),
                                0);

                assertThatExceptionOfType(CustomerArchivedException.class)
                                .isThrownBy(() -> customer.changeName("New Name"))
                                .withMessage(ERROR_CUSTOMER_ARCHIVED);

                assertThatExceptionOfType(CustomerArchivedException.class)
                                .isThrownBy(() -> customer.changeEmail("new.email@example.com"))
                                .withMessage(ERROR_CUSTOMER_ARCHIVED);

                assertThatExceptionOfType(CustomerArchivedException.class)
                                .isThrownBy(() -> customer.changePhone("0987654321"))
                                .withMessage(ERROR_CUSTOMER_ARCHIVED);

                assertThatExceptionOfType(CustomerArchivedException.class)
                                .isThrownBy(() -> customer.enablePromotionalNotifications())
                                .withMessage(ERROR_CUSTOMER_ARCHIVED);

                assertThatExceptionOfType(CustomerArchivedException.class)
                                .isThrownBy(() -> customer.disablePromotionalNotifications())
                                .withMessage(ERROR_CUSTOMER_ARCHIVED);

                assertThatExceptionOfType(CustomerArchivedException.class)
                                .isThrownBy(() -> customer.addLoyaltyPoints(10))
                                .withMessage(ERROR_CUSTOMER_ARCHIVED);
        }
}
