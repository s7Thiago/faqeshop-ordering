package com.faqe.faqeshop.ordering.domain.exception;

public class ErrorMessages {
    public static final String VALIDATION_ERROR_BIRTHDATE_IS_NULL = "Birth date cannot be null.";
    public static final String VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST = "Birth date cannot be in the future.";

    public static final String VALIDATION_ERROR_FULLNAME_IS_NULL = "FullName cannot be null.";
    public static final String VALIDATION_ERROR_FIRSTNAME_IS_NULL = "FirstName cannot be null.";
    public static final String VALIDATION_ERROR_LASTNAME_IS_NULL = "LastName cannot be null.";
    public static final String VALIDATION_ERROR_FULLNAME_IS_BLANK = "FullName cannot be blank.";
    public static final String VALIDATION_ERROR_FIRSTNAME_IS_BLANK = "FistName cannot be blank.";
    public static final String VALIDATION_ERROR_LASTNAME_IS_BLANK = "LastName cannot be blank.";

    public static final String VALIDATION_ERROR_EMAIL_IS_INVALID = "Email is invalid.";
    public static final String VALIDATION_ERROR_EMAIL_IS_BLANK = "Email cannot be blank.";
    public static final String VALIDATION_ERROR_EMAIL_CANNOT_BE_CHANGED = "Cannot change email for an archived customer.";
    
    public static final String VALIDATION_ERROR_ARCHIVED_CUSTOMER_CANNOT_ENABLE_PROMOTIONAL_NOTIFICATIONS = "Cannot enable promotional notifications for an archived customer.";
    public static final String VALIDATION_ERROR_ARCHIVED_CUSTOMER_CANNOT_CHANGE_NAME = "Cannot change name for an archived customer.";

    public static final String VALIDATION_ERROR_PHONE_CANNOT_BE_CHANGED = "Cannot change phone for an archived customer.";
    public static final String VALIDATION_ERROR_PHONE_IS_NULL_OR_EMPTY = "Phone cannot be null or empty.";
    public static final String VALIDATION_ERROR_PHONE_IS_NULL = "Phone cannot be null.";

    public static final String VALIDATION_ERROR_ID_IS_NULL = "Customer ID cannot be null.";
    public static final String VALIDATION_ERROR_ORDERID_IS_NULL = "Order ID cannot be null.";
    public static final String VALIDATION_ERROR_ORDERITEMID_IS_NULL = "Order Item ID cannot be null.";
    
    public static final String VALIDATION_ERROR_DOCUMENT_IS_NULL = "Document cannot be null.";
    public static final String VALIDATION_ERROR_DOCUMENT_IS_BLANK = "Document cannot be blank.";

    public static final String VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL = "Loyalty points cannot be null.";
    public static final String VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE = "Loyalty points must be a greather than zero";

    public static final String ERROR_CUSTOMER_ARCHIVED = "Customer is archived it cannot be changed.";

    public static final String VALIDATION_ERROR_ADDRESS_IS_NULL = "Address cannot be null.";

    public static final String VALIDATION_ERROR_MONEY_IS_NULL = "O valor não pode ser nulo";
    public static final String VALIDATION_ERROR_MONEY_IS_NEGATIVE = "O valor não pode ser negativo";
    public static final String VALIDATION_ERROR_MONEY_QUANTITY_IS_NULL = "Quantidade não pode ser nula";
    public static final String VALIDATION_ERROR_MONEY_QUANTITY_MUST_BE_POSITIVE = "Quantidade deve ser maior que 0";
    public static final String VALIDATION_ERROR_MONEY_DIVISOR_IS_NULL = "Divisor não pode ser nulo";
    public static final String VALIDATION_ERROR_MONEY_DIVISION_BY_ZERO = "Divisão por zero não é permitida";
    public static final String VALIDATION_ERROR_MONEY_COMPARISON_WITH_NULL = "Comparação com nulo não é permitida";
    public static final String VALIDATION_ERROR_MONEY_QUANTITY_CANNOT_BE_NEGATIVE = "Quantidade não pode ser negativa";

    public static final String VALIDATION_ERROR_ZIPCODE_IS_NULL = "Zip code cannot be null.";
    public static final String VALIDATION_ERROR_ZIPCODE_IS_BLANK = "Zip code cannot be blank.";
    public static final String VALIDATION_ERROR_ZIPCODE_SHORT = "Zip code must be at least 5 characters long.";

    public static final String VALIDATION_ERROR_PRODUCTNAME_IS_NULL = "Product name cannot be null.";
    public static final String VALIDATION_ERROR_PRODUCTNAME_IS_BLANK = "Product name cannot be blank.";

    public static final String VALIDATION_ERROR_BILLINGINFO_FULLNAME_IS_NULL = "Billing info - Full name cannot be null.";
    public static final String VALIDATION_ERROR_BILLINGINFO_DOCUMENT_IS_NULL = "Billing info - Document cannot be null.";
    public static final String VALIDATION_ERROR_BILLINGINFO_PHONE_IS_NULL = "Billing info - Phone cannot be null.";
    public static final String VALIDATION_ERROR_BILLINGINFO_ADDRESS_IS_NULL = "Billing info - Address cannot be null.";

    public static final String VALIDATION_ERROR_SHIPPINGINFO_FULLNAME_IS_NULL = "Shipping info - Full name cannot be null.";
    public static final String VALIDATION_ERROR_SHIPPINGINFO_DOCUMENT_IS_NULL = "Shipping info - Document cannot be null.";
    public static final String VALIDATION_ERROR_SHIPPINGINFO_PHONE_IS_NULL = "Shipping info - Phone cannot be null.";
    public static final String VALIDATION_ERROR_SHIPPINGINFO_ADDRESS_IS_NULL = "Shipping info - Address cannot be null.";
    
    // Additional validation messages used by Order and OrderItem
    public static final String VALIDATION_ERROR_CUSTOMERID_IS_NULL = "Customer ID cannot be null.";
    public static final String VALIDATION_ERROR_ORDERSTATUS_IS_NULL = "Order status cannot be null.";
    public static final String VALIDATION_ERROR_ITEMS_IS_NULL = "Order items cannot be null.";

    public static final String VALIDATION_ERROR_PRODUCTID_IS_NULL = "Product ID cannot be null.";
}
