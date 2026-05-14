package com.faqe.faqeshop.ordering.domain.valueObject;

import com.faqe.faqeshop.ordering.domain.exception.ErrorMessages;
import lombok.Builder;

import java.util.Objects;

@Builder
public record ShippingInfo(
        FullName fullName,
        Document document,
        Phone phone,
        Address address
) {

    public ShippingInfo {
        Objects.requireNonNull(fullName, ErrorMessages.VALIDATION_ERROR_SHIPPINGINFO_FULLNAME_IS_NULL);
        Objects.requireNonNull(document, ErrorMessages.VALIDATION_ERROR_SHIPPINGINFO_DOCUMENT_IS_NULL);
        Objects.requireNonNull(phone, ErrorMessages.VALIDATION_ERROR_SHIPPINGINFO_PHONE_IS_NULL);
        Objects.requireNonNull(address, ErrorMessages.VALIDATION_ERROR_SHIPPINGINFO_ADDRESS_IS_NULL);
    }

}
