package com.faqe.faqeshop.ordering.domain.entity;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_ARCHIVED_CUSTOMER_CANNOT_CHANGE_NAME;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_ARCHIVED_CUSTOMER_CANNOT_ENABLE_PROMOTIONAL_NOTIFICATIONS;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_DOCUMENT_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_CANNOT_BE_CHANGED;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_BLANK;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_ID_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_PHONE_CANNOT_BE_CHANGED;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_PHONE_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_PHONE_IS_NULL_OR_EMPTY;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

import com.faqe.faqeshop.ordering.domain.validator.FieldValidations;

public class Customer {
    private UUID id;
    private String fullName;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String document;
    private Boolean promotionalNotificationsAllowed;
    private Boolean archived;
    private OffsetDateTime registeredAt;
    private OffsetDateTime archivedAt;
    private Integer loyaltyPoints; // just can be added

    public Customer(UUID id, String fullName, LocalDate birthDate, String email, String phone, String document,
            Boolean promotionalNotificationsAllowed, OffsetDateTime registeredAt) {
        setId(id);
        setFullName(fullName);
        setBirthDate(birthDate);
        setEmail(email);
        setPhone(phone);
        setDocument(document);
        setPromotionalNotificationsAllowed(promotionalNotificationsAllowed);
        setRegisteredAt(registeredAt);
        this.setArchived(false);
        this.setLoyaltyPoints(0);
    }

    public Customer(UUID id, String fullName, LocalDate birthDate, String email, String phone, String document,
            Boolean promotionalNotificationsAllowed, Boolean archived, OffsetDateTime registeredAt,
            OffsetDateTime archivedAt, Integer loyaltyPoints) {
        setId(id);
        setFullName(fullName);
        setBirthDate(birthDate);
        setEmail(email);
        setPhone(phone);
        setDocument(document);
        setPromotionalNotificationsAllowed(promotionalNotificationsAllowed);
        setArchived(archived);
        setRegisteredAt(registeredAt);
        setArchivedAt(archivedAt);
        setLoyaltyPoints(loyaltyPoints);
    }

    public void addLoyaltyPoints(Integer points) {
        if (points != null && points > 0) {
            if (loyaltyPoints == null) {
                loyaltyPoints = 0;
            }
            setLoyaltyPoints(this.loyaltyPoints + points);
        }
    }

    public void archive() {
        this.setArchived(true);
        this.setArchivedAt(OffsetDateTime.now());
    }

    public void enablePromotionalNotifications() {
        if (this.isArchived().equals(Boolean.FALSE)) {
            this.setPromotionalNotificationsAllowed(true);
        } else {
            throw new IllegalStateException(VALIDATION_ERROR_ARCHIVED_CUSTOMER_CANNOT_ENABLE_PROMOTIONAL_NOTIFICATIONS);
        }
    }

    public void disablePromotionalNotifications() {
        this.setPromotionalNotificationsAllowed(false);
    }

    public Boolean changeName(String fullName) {

        Objects.requireNonNull(fullName, VALIDATION_ERROR_FULLNAME_IS_NULL);

        if (this.isArchived().equals(Boolean.TRUE)) {
            throw new IllegalStateException(VALIDATION_ERROR_ARCHIVED_CUSTOMER_CANNOT_CHANGE_NAME);
        }

        if (!fullName.trim().isEmpty()) {
            this.setFullName(fullName);
        } else {
            throw new IllegalArgumentException(VALIDATION_ERROR_FULLNAME_IS_BLANK);
        }
        return true;
    }

    public void changeEmail(String email) {
        if (this.isArchived().equals(Boolean.TRUE)) {
            throw new IllegalStateException(VALIDATION_ERROR_EMAIL_CANNOT_BE_CHANGED);
        }

        if (email != null && !email.trim().isEmpty()) {
            this.setEmail(email);
        } else {
            throw new IllegalArgumentException(VALIDATION_ERROR_EMAIL_IS_INVALID);
        }
    }

    public void changePhone(String phone) {
        if (this.isArchived().equals(Boolean.TRUE)) {
            throw new IllegalStateException(VALIDATION_ERROR_PHONE_CANNOT_BE_CHANGED);
        }

        if (phone != null && !phone.trim().isEmpty()) {
            this.setPhone(phone);
        } else {
            throw new IllegalArgumentException(VALIDATION_ERROR_PHONE_IS_NULL_OR_EMPTY);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public UUID id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public LocalDate birthDate() {
        return birthDate;
    }

    public String email() {
        return email;
    }

    public String phone() {
        return phone;
    }

    public String document() {
        return document;
    }

    public Boolean isPromotionalNotificationsAllowed() {
        return promotionalNotificationsAllowed;
    }

    public Boolean isArchived() {
        return archived;
    }

    public OffsetDateTime registeredAt() {
        return registeredAt;
    }

    public OffsetDateTime archivedAt() {
        return archivedAt;
    }

    public Integer loyaltyPoints() {
        return loyaltyPoints;
    }

    private void setId(UUID id) {
        Objects.requireNonNull(id, VALIDATION_ERROR_ID_IS_NULL);
        this.id = id;
    }

    private void setFullName(String fullName) {
        Objects.requireNonNull(fullName, VALIDATION_ERROR_FULLNAME_IS_NULL);

        if (fullName.isBlank()) {
            throw new IllegalArgumentException(VALIDATION_ERROR_FULLNAME_IS_BLANK);
        }

        this.fullName = fullName;
    }

    private void setBirthDate(LocalDate birthDate) {

        if (birthDate == null) {
            this.birthDate = null;
            return;
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST);
        }

        this.birthDate = birthDate;
    }

    private void setEmail(String email) {
        FieldValidations.requiresValidEmail(email, VALIDATION_ERROR_EMAIL_IS_INVALID);
        this.email = email;
    }

    private void setPhone(String phone) {
        Objects.requireNonNull(phone, VALIDATION_ERROR_PHONE_IS_NULL);
        this.phone = phone;
    }

    private void setDocument(String document) {
        Objects.requireNonNull(document, VALIDATION_ERROR_DOCUMENT_IS_NULL);
        this.document = document;
    }

    private void setPromotionalNotificationsAllowed(Boolean promotionalNotificationsAllowed) {
        this.promotionalNotificationsAllowed = promotionalNotificationsAllowed;
    }

    private void setArchived(Boolean archived) {
        this.archived = archived;
    }

    private void setRegisteredAt(OffsetDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    private void setArchivedAt(OffsetDateTime archivedAt) {
        this.archivedAt = archivedAt;
    }

    private void setLoyaltyPoints(Integer loyaltyPoints) {
        Objects.requireNonNull(loyaltyPoints, VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL);
        if (loyaltyPoints < 0) {
            throw new IllegalArgumentException(VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE);
        }
        this.loyaltyPoints = loyaltyPoints;
    }

}
