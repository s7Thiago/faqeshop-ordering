package com.faqe.faqeshop.ordering.domain.valueObject;

import java.util.Objects;

import com.faqe.faqeshop.ordering.domain.validator.FieldValidations;

import lombok.Builder;

@Builder(toBuilder = true)
public record Address(
    String street,
    String number,
    String complement,
    String neighborhood,
    String city,
    String state,
    ZipCode zipCode
) {
    public Address {
        FieldValidations.requiresNonBlank(street);
        FieldValidations.requiresNonBlank(neighborhood);
        FieldValidations.requiresNonBlank(city);
        FieldValidations.requiresNonBlank(state);
        Objects.requireNonNull(zipCode);
    }
}
