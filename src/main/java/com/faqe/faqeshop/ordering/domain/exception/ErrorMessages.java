package com.faqe.faqeshop.ordering.domain.exception;

public class ErrorMessages {
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
    
    public static final String VALIDATION_ERROR_DOCUMENT_IS_NULL = "Document cannot be null.";

    public static final String VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL = "Loyalty points cannot be null.";
    public static final String VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE = "Loyalty points must be a greather than zero";

    public static final String ERROR_CUSTOMER_ARCHIVED = "Customer is archived it cannot be changed.";
}
