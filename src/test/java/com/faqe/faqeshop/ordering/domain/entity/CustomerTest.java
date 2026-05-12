package com.faqe.faqeshop.ordering.domain.entity;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.ERROR_CUSTOMER_ARCHIVED;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertWith;

import org.junit.jupiter.api.Test;

import com.faqe.faqeshop.ordering.domain.exception.CustomerArchivedException;
import com.faqe.faqeshop.ordering.domain.utility.IdGenerator;
import com.faqe.faqeshop.ordering.domain.valueObject.Email;
import com.faqe.faqeshop.ordering.domain.valueObject.FullName;
import com.faqe.faqeshop.ordering.domain.valueObject.LoyaltyPoints;

public class CustomerTest {

        @Test
        public void testingCustomer() {

                Customer customer = CustomerTestDataBuilder.brandNewCustomer().build();

                System.out.println("ID do cliente: " + customer.id());
                System.out.println("Novo ID teste: " + IdGenerator.generateTimeBasedUUID());

        }

        @Test
        public void given_invalidEmail_whenTryCreateCustomer_ShouldGenerateException() {
                assertThatExceptionOfType(IllegalArgumentException.class)
                                .isThrownBy(() -> CustomerTestDataBuilder.brandNewCustomer()
                                                .email(new Email("invalid"))
                                                .build())
                                .withMessage(VALIDATION_ERROR_EMAIL_IS_INVALID);
        }

        @Test
        public void given_invalidEmail_whenTryUpdateCustomerEmail_ShouldGenerateException() {
                Customer customer = CustomerTestDataBuilder.brandNewCustomer().build();

                assertThatExceptionOfType(IllegalArgumentException.class)
                                .isThrownBy(() -> {
                                        customer.changeEmail("invalid-email");
                                })
                                .withMessage(VALIDATION_ERROR_EMAIL_IS_INVALID);
        }

        @Test
        void given_unarchivedCustomer_whenArchive_shouldAnonymize() {
                Customer customer = CustomerTestDataBuilder.brandNewCustomer().build();

                customer.archive();

                assertWith(customer,
                                c -> assertThat(c.fullName()).isEqualTo(new FullName("Anonymous", "Customer")),
                                c -> assertThat(c.email()).isNotEqualTo(new Email("john.doe@example.com")),
                                c -> assertThat(c.phone().value()).isEqualTo("000-000-0000"),
                                c -> assertThat(c.document().value()).isEqualTo("000-00-000"),
                                c -> assertThat(c.isPromotionalNotificationsAllowed()).isFalse(),
                                c -> assertThat(c.birthDate()).isNull(),
                                c -> assertThat(c.address().number()).isEqualTo("Unknown"),
                                c -> assertThat(c.address().complement()).isNull());

        }

        @Test
        void given_archivedCustomer_whenTryToArchive_shouldGenerateException() {
                Customer customer = CustomerTestDataBuilder.brandNewCustomer()
                                .archived(true)
                                .build();

                assertThatExceptionOfType(CustomerArchivedException.class)
                                .isThrownBy(customer::archive)
                                .withMessage(ERROR_CUSTOMER_ARCHIVED);
        }

        @Test
        void given_archivedCustomer_whenTryToUpdate_shouldGenerateException() {
                Customer customer = CustomerTestDataBuilder.existingCustomer().build();

                customer.archive();

                assertThatExceptionOfType(CustomerArchivedException.class)
                                .isThrownBy(() -> customer.changeName(new FullName("Jane", "Smith")))
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
                                .isThrownBy(() -> customer.addLoyaltyPoints(new LoyaltyPoints(10)))
                                .withMessage(ERROR_CUSTOMER_ARCHIVED);
        }

        @Test
        void given_customer_whenAddNullLoyaltypoints_shouldGenerateException() {
                Customer customer = CustomerTestDataBuilder.brandNewCustomer().build();

                assertThatExceptionOfType(NullPointerException.class)
                                .isThrownBy(() -> customer.addLoyaltyPoints(null))
                                .withMessage(VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL);
        }

        @Test
        void given_customer_whenAddLoyaltypoints_shouldSumPoints() {
                Customer customer = CustomerTestDataBuilder.brandNewCustomer().build();

                customer.addLoyaltyPoints(new LoyaltyPoints(10));
                customer.addLoyaltyPoints(new LoyaltyPoints(5));

                assertThat(customer.loyaltyPoints()).isEqualTo(new LoyaltyPoints(15));
        }

        @Test
        void given_customer_whenAddInvalidLoyaltypoints_shouldGenerateException() {
                Customer customer = CustomerTestDataBuilder.brandNewCustomer().build();

                assertThatExceptionOfType(IllegalArgumentException.class)
                                .isThrownBy(() -> customer.addLoyaltyPoints(new LoyaltyPoints(-5)))
                                .withMessage(VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE);

                assertThatExceptionOfType(IllegalArgumentException.class)
                                .isThrownBy(() -> customer.addLoyaltyPoints(new LoyaltyPoints(0)))
                                .withMessage(VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE);
        }
}
