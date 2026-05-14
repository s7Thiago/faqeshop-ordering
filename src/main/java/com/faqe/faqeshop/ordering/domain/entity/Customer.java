package com.faqe.faqeshop.ordering.domain.entity;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_ADDRESS_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_ARCHIVED_CUSTOMER_CANNOT_ENABLE_PROMOTIONAL_NOTIFICATIONS;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_CANNOT_BE_CHANGED;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_ID_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_PHONE_CANNOT_BE_CHANGED;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_PHONE_IS_NULL_OR_EMPTY;
import static java.util.Objects.requireNonNull;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

import com.faqe.faqeshop.ordering.domain.exception.CustomerArchivedException;
import com.faqe.faqeshop.ordering.domain.valueObject.Address;
import com.faqe.faqeshop.ordering.domain.valueObject.BirthDate;
import com.faqe.faqeshop.ordering.domain.valueObject.id.CustomerId;
import com.faqe.faqeshop.ordering.domain.valueObject.Document;
import com.faqe.faqeshop.ordering.domain.valueObject.Email;
import com.faqe.faqeshop.ordering.domain.valueObject.FullName;
import com.faqe.faqeshop.ordering.domain.valueObject.LoyaltyPoints;
import com.faqe.faqeshop.ordering.domain.valueObject.Phone;

import lombok.Builder;

public class Customer {
    private CustomerId id;
    private FullName fullName;
    private BirthDate birthDate;
    private Email email;
    private Phone phone;
    private Document document;
    private Boolean promotionalNotificationsAllowed;
    private Boolean archived;
    private OffsetDateTime registeredAt;
    private OffsetDateTime archivedAt;
    private LoyaltyPoints loyaltyPoints; // just can be added
    private Address address;

    @Builder(builderClassName = "BrandNewCustomerBuilder", builderMethodName = "brandNew")
    private static Customer createBrandNew(
            FullName fullName,
            BirthDate birthDate,
            Email email,
            Phone phone,
            Document document,
            Boolean promotionalNotificationsAllowed,
            Boolean archived,
            Address address) {
        return new Customer(
                new CustomerId(),
                fullName,
                birthDate,
                email,
                phone,
                document,
                promotionalNotificationsAllowed,
                archived, // archived
                OffsetDateTime.now(), // registeredAt
                archived ? OffsetDateTime.now() : null, // archivedAt
                LoyaltyPoints.ZERO, // loyaltyPoints
                address);
    }

    @Builder(builderClassName = "ExistingCustomerBuilder", builderMethodName = "existing")
    private Customer(
            CustomerId id,
            FullName fullName,
            BirthDate birthDate,
            Email email,
            Phone phone,
            Document document,
            Boolean promotionalNotificationsAllowed,
            Boolean archived,
            OffsetDateTime registeredAt,
            OffsetDateTime archivedAt,
            LoyaltyPoints loyaltyPoints,
            Address address) {
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
        setAddress(address);
    }

    public void addLoyaltyPoints(LoyaltyPoints points) {
        verifyIfChangeable();
        setLoyaltyPoints(this.loyaltyPoints.add(points));
    }

    public void archive() {
        verifyIfChangeable();

        this.setArchived(true);
        this.setArchivedAt(OffsetDateTime.now());
        this.setFullName(new FullName("Anonymous", "Customer"));
        this.setPhone(new Phone("000-000-0000"));

        Address.AddressBuilder addressBuilder = this.address().toBuilder();
        this.setAddress(addressBuilder.number("Unknown")
                .complement(null)
                .number("Unknown")
                .build());
        this.setDocument(new Document("000-00-000"));
        this.setEmail(new Email(UUID.randomUUID() + "@anonymous.com"));
        this.setBirthDate(null);
        this.setPromotionalNotificationsAllowed(false);
    }

    public void enablePromotionalNotifications() {
        verifyIfChangeable();
        if (this.isArchived().equals(Boolean.FALSE)) {
            this.setPromotionalNotificationsAllowed(true);
        } else {
            throw new IllegalStateException(VALIDATION_ERROR_ARCHIVED_CUSTOMER_CANNOT_ENABLE_PROMOTIONAL_NOTIFICATIONS);
        }
    }

    public void disablePromotionalNotifications() {
        verifyIfChangeable();
        this.setPromotionalNotificationsAllowed(false);
    }

    public void changeName(FullName fullName) {
        verifyIfChangeable();
        this.setFullName(fullName);
    }

    public void changeEmail(String email) {
        verifyIfChangeable();
        if (this.isArchived().equals(Boolean.TRUE)) {
            throw new IllegalStateException(VALIDATION_ERROR_EMAIL_CANNOT_BE_CHANGED);
        }

        if (email != null && !email.trim().isEmpty()) {
            this.setEmail(new Email(email));
        } else {
            throw new IllegalArgumentException(VALIDATION_ERROR_EMAIL_IS_INVALID);
        }
    }

    public void changePhone(String phone) {
        verifyIfChangeable();
        if (this.isArchived().equals(Boolean.TRUE)) {
            throw new IllegalStateException(VALIDATION_ERROR_PHONE_CANNOT_BE_CHANGED);
        }

        if (phone != null && !phone.trim().isEmpty()) {
            this.setPhone(new Phone(phone));
        } else {
            throw new IllegalArgumentException(VALIDATION_ERROR_PHONE_IS_NULL_OR_EMPTY);
        }
    }

    public void changeAddress(Address address) {
        verifyIfChangeable();
        this.setAddress(address);
    }

    private void verifyIfChangeable() {
        if (this.isArchived()) {
            throw new CustomerArchivedException();
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

    public CustomerId id() {
        return id;
    }

    public FullName fullName() {
        return fullName;
    }

    public BirthDate birthDate() {
        return birthDate;
    }

    public Email email() {
        return email;
    }

    public Phone phone() {
        return phone;
    }

    public Document document() {
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

    public LoyaltyPoints loyaltyPoints() {
        return loyaltyPoints;
    }

    public Address address() {
        return address;
    }

    private void setId(CustomerId id) {
        requireNonNull(id, VALIDATION_ERROR_ID_IS_NULL);
        this.id = id;
    }

    private void setFullName(FullName fullName) {
        requireNonNull(fullName, VALIDATION_ERROR_FULLNAME_IS_NULL);
        this.fullName = fullName;
    }

    private void setBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    private void setEmail(Email email) {
        this.email = email;
    }

    private void setPhone(Phone phone) {
        this.phone = phone;
    }

    private void setDocument(Document document) {
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
        this.archived = archivedAt != null;
    }

    private void setLoyaltyPoints(LoyaltyPoints addedPoints) {
        requireNonNull(addedPoints, VALIDATION_ERROR_LOYALTYPOINTS_IS_NULL);
        this.loyaltyPoints = addedPoints;
    }

    private void setAddress(Address address) {
        Objects.requireNonNull(address, VALIDATION_ERROR_ADDRESS_IS_NULL);
        this.address = address;
    }

}
