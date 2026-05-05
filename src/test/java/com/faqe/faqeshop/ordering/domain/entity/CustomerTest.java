package com.faqe.faqeshop.ordering.domain.entity;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.ERROR_CUSTOMER_ARCHIVED;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertWith;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

import com.faqe.faqeshop.ordering.domain.exception.CustomerArchivedException;
import com.faqe.faqeshop.ordering.domain.utility.IdGenerator;
import com.faqe.faqeshop.ordering.domain.valueObject.Address;
import com.faqe.faqeshop.ordering.domain.valueObject.BirthDate;
import com.faqe.faqeshop.ordering.domain.valueObject.CustomerId;
import com.faqe.faqeshop.ordering.domain.valueObject.Document;
import com.faqe.faqeshop.ordering.domain.valueObject.Email;
import com.faqe.faqeshop.ordering.domain.valueObject.FullName;
import com.faqe.faqeshop.ordering.domain.valueObject.LoyaltyPoints;
import com.faqe.faqeshop.ordering.domain.valueObject.Phone;
import com.faqe.faqeshop.ordering.domain.valueObject.ZipCode;

public class CustomerTest {

        @Test
        public void testingCustomer() {

                Customer customer = Customer.brandNew()
                                .fullName(new FullName("John", "Doe"))
                                .birthDate(new BirthDate(LocalDate.of(1991, 1, 1)))
                                .email(new Email("john.doe@example.com"))
                                .phone(new Phone("1234567890"))
                                .document(new Document("12345678901"))
                                .promotionalNotificationsAllowed(true)
                                .archived(false)
                                .address(Address.builder()
                                                .street("123 Main St")
                                                .complement("Apt 4B")
                                                .neighborhood("Downtown")
                                                .city("Anytown")
                                                .state("CA")
                                                .zipCode(new ZipCode("12345"))
                                                .build())
                                .build();

                System.out.println("ID do cliente: " + customer.id());
                System.out.println("Novo ID teste: " + IdGenerator.generateTimeBasedUUID());

        }

        @Test
        public void given_invalidEmail_whenTryCreateCustomer_ShouldGenerateException() {
                assertThatExceptionOfType(IllegalArgumentException.class)
                                .isThrownBy(() -> Customer.brandNew()
                                                .fullName(new FullName("John", "Doe"))
                                                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                                                .email(new Email("invalid-email"))
                                                .phone(new Phone("1234567890"))
                                                .document(new Document("12345678901"))
                                                .promotionalNotificationsAllowed(false)
                                                .archived(false)
                                                .address(Address.builder()
                                                                .street("123 Main St")
                                                                .number("123")
                                                                .complement("Apt 4B")
                                                                .neighborhood("Downtown")
                                                                .city("Anytown")
                                                                .state("CA")
                                                                .zipCode(new ZipCode("12345"))
                                                                .build()))
                                .withMessage(VALIDATION_ERROR_EMAIL_IS_INVALID);
        }

        @Test
        public void given_invalidEmail_whenTryUpdateCustomerEmail_ShouldGenerateException() {
                Customer customer = Customer.brandNew()
                                .fullName(new FullName("John", "Doe"))
                                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                                .email(new Email("john.doe@example.com"))
                                .phone(new Phone("1234567890"))
                                .document(new Document("12345678901"))
                                .promotionalNotificationsAllowed(false)
                                .archived(false)
                                .address(Address.builder()
                                                .street("123 Main St")
                                                .number("123")
                                                .complement("Apt 4B")
                                                .neighborhood("Downtown")
                                                .city("Anytown")
                                                .state("CA")
                                                .zipCode(new ZipCode("12345"))
                                                .build())
                                .build();

                assertThatExceptionOfType(IllegalArgumentException.class)
                                .isThrownBy(() -> {
                                        customer.changeEmail("invalid-email");
                                })
                                .withMessage(VALIDATION_ERROR_EMAIL_IS_INVALID);
        }

        @Test
        void given_unarchivedCustomer_whenArchive_shouldAnonymize() {
                Customer customer = Customer.existing()
                                .id(new CustomerId())
                                .fullName(new FullName("John", "Doe"))
                                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                                .email(new Email("john.doe@example.com"))
                                .phone(new Phone("1234567890"))
                                .document(new Document("12345678901"))
                                .promotionalNotificationsAllowed(false)
                                .archived(false)
                                .registeredAt(OffsetDateTime.now())
                                .archived(null)
                                .loyaltyPoints(new LoyaltyPoints(10))
                                .address(Address.builder()
                                                .street("123 Main St")
                                                .number("123")
                                                .complement("Apt 4B")
                                                .neighborhood("Downtown")
                                                .city("Anytown")
                                                .state("CA")
                                                .zipCode(new ZipCode("12345"))
                                                .build())
                                .build();

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
                Customer customer = Customer.existing()
                                .id(new CustomerId())
                                .fullName(new FullName("John", "Doe"))
                                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                                .email(new Email("john.doe@example.com"))
                                .phone(new Phone("1234567890"))
                                .document(new Document("12345678901"))
                                .promotionalNotificationsAllowed(false)
                                .archived(true)
                                .registeredAt(OffsetDateTime.now())
                                .archivedAt(OffsetDateTime.now())
                                .loyaltyPoints(new LoyaltyPoints(10))
                                .address(Address.builder()
                                                .street("123 Main St")
                                                .number("123")
                                                .complement("Apt 4B")
                                                .neighborhood("Downtown")
                                                .city("Anytown")
                                                .state("CA")
                                                .zipCode(new ZipCode("12345"))
                                                .build())
                                .build();

                assertThatExceptionOfType(CustomerArchivedException.class)
                                .isThrownBy(customer::archive)
                                .withMessage(ERROR_CUSTOMER_ARCHIVED);
        }

        @Test
        void given_archivedCustomer_whenTryToUpdate_shouldGenerateException() {
                Customer customer = Customer.existing()
                                .id(new CustomerId())
                                .fullName(new FullName("John", "Doe"))
                                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                                .email(new Email("john.doe@example.com"))
                                .phone(new Phone("1234567890"))
                                .document(new Document("12345678901"))
                                .promotionalNotificationsAllowed(false)
                                .archived(false)
                                .registeredAt(OffsetDateTime.now())
                                .archivedAt(null)
                                .loyaltyPoints(new LoyaltyPoints(10))
                                .address(Address.builder()
                                                .street("123 Main St")
                                                .number("123")
                                                .complement("Apt 4B")
                                                .neighborhood("Downtown")
                                                .city("Anytown")
                                                .state("CA")
                                                .zipCode(new ZipCode("12345"))
                                                .build())
                                .build();

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
                Customer customer = Customer.brandNew()
                                .fullName(new FullName("John", "Doe"))
                                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                                .email(new Email("john.doe@example.com"))
                                .phone(new Phone("1234567890"))
                                .document(new Document("12345678901"))
                                .promotionalNotificationsAllowed(false)
                                .archived(false)
                                .address(Address.builder()
                                                .street("123 Main St")
                                                .number("123")
                                                .complement("Apt 4B")
                                                .neighborhood("Downtown")
                                                .city("Anytown")
                                                .state("CA")
                                                .zipCode(new ZipCode("12345"))
                                                .build()).build();

                assertThatExceptionOfType(NullPointerException.class)
                                .isThrownBy(() -> customer.addLoyaltyPoints(null))
                                .withMessage(VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL);
        }

        @Test
        void given_customer_whenAddLoyaltypoints_shouldSumPoints() {
                Customer customer = Customer.brandNew()
                                .fullName(new FullName("John", "Doe"))
                                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                                .email(new Email("john.doe@example.com"))
                                .phone(new Phone("1234567890"))
                                .document(new Document("12345678901"))
                                .promotionalNotificationsAllowed(false)
                                .archived(false)
                                .address(Address.builder()
                                                .street("123 Main St")
                                                .number("123")
                                                .complement("Apt 4B")
                                                .neighborhood("Downtown")
                                                .city("Anytown")
                                                .state("CA")
                                                .zipCode(new ZipCode("12345"))
                                                .build()).build();

                customer.addLoyaltyPoints(new LoyaltyPoints(10));
                customer.addLoyaltyPoints(new LoyaltyPoints(5));

                assertThat(customer.loyaltyPoints()).isEqualTo(new LoyaltyPoints(15));
        }

        @Test
        void given_customer_whenAddInvalidLoyaltypoints_shouldGenerateException() {
                Customer customer = Customer.brandNew()
                                .fullName(new FullName("John", "Doe"))
                                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                                .email(new Email("john.doe@example.com"))
                                .phone(new Phone("1234567890"))
                                .document(new Document("12345678901"))
                                .promotionalNotificationsAllowed(false)
                                .archived(false)
                                .address(Address.builder()
                                                .street("123 Main St")
                                                .number("123")
                                                .complement("Apt 4B")
                                                .neighborhood("Downtown")
                                                .city("Anytown")
                                                .state("CA")
                                                .zipCode(new ZipCode("12345"))
                                                .build()).build();

                assertThatExceptionOfType(IllegalArgumentException.class)
                                .isThrownBy(() -> customer.addLoyaltyPoints(new LoyaltyPoints(-5)))
                                .withMessage(VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE);

                assertThatExceptionOfType(IllegalArgumentException.class)
                                .isThrownBy(() -> customer.addLoyaltyPoints(new LoyaltyPoints(0)))
                                .withMessage(VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE);
        }
}
