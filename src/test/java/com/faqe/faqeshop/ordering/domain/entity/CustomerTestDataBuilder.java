package com.faqe.faqeshop.ordering.domain.entity;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.faqe.faqeshop.ordering.domain.valueObject.Address;
import com.faqe.faqeshop.ordering.domain.valueObject.BirthDate;
import com.faqe.faqeshop.ordering.domain.valueObject.id.CustomerId;
import com.faqe.faqeshop.ordering.domain.valueObject.Document;
import com.faqe.faqeshop.ordering.domain.valueObject.Email;
import com.faqe.faqeshop.ordering.domain.valueObject.FullName;
import com.faqe.faqeshop.ordering.domain.valueObject.LoyaltyPoints;
import com.faqe.faqeshop.ordering.domain.valueObject.Phone;
import com.faqe.faqeshop.ordering.domain.valueObject.ZipCode;

public class CustomerTestDataBuilder {

    private CustomerTestDataBuilder() {
    }

    /**
     * Brand new customer with all required fields and default values for optional
     * fields.
     */
    public static Customer.BrandNewCustomerBuilder brandNewCustomer() {
        return Customer.brandNew()
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
                        .build());
    }

    public static Customer.ExistingCustomerBuilder existingCustomer() {
        return Customer.existing()
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
                        .build());
    }
}
