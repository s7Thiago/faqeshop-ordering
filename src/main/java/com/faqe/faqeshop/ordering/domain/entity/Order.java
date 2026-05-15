package com.faqe.faqeshop.ordering.domain.entity;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

import com.faqe.faqeshop.ordering.domain.valueObject.BillingInfo;
import com.faqe.faqeshop.ordering.domain.valueObject.Money;
import com.faqe.faqeshop.ordering.domain.valueObject.Quantity;
import com.faqe.faqeshop.ordering.domain.valueObject.ShippingInfo;
import com.faqe.faqeshop.ordering.domain.valueObject.id.CustomerId;
import com.faqe.faqeshop.ordering.domain.exception.ErrorMessages;
import com.faqe.faqeshop.ordering.domain.valueObject.id.OrderId;

public class Order {

    private OrderId id;
    private CustomerId customerId;

    private Money totalAmount;
    private Quantity totalItems;

    private OffsetDateTime placedAt;
    private OffsetDateTime paidAt;
    private OffsetDateTime canceledAt;
    private OffsetDateTime readyAt;

    private BillingInfo billing;
    private ShippingInfo shipping;

    private OrderStatus status;
    private PaymentMethod paymentMethod;

    private Money shippingCost;
    private LocalDate expectedDeliveryDate;

    private Set<OrderItem> items;

    public Order(OrderId id,
            CustomerId customerId,
            Money totalAmount,
            Quantity totalItems,
            OffsetDateTime placedAt,
            OffsetDateTime paidAt,
            OffsetDateTime canceledAt,
            OffsetDateTime readyAt,
            BillingInfo billing,
            ShippingInfo shipping,
            OrderStatus status,
            PaymentMethod paymentMethod,
            Money shippingCost,
            LocalDate expectedDeliveryDate,
            Set<OrderItem> items) {
        this.setId(id);
        this.setCustomerId(customerId);
        this.setTotalAmount(totalAmount);
        this.setTotalItems(totalItems);
        this.setPlacedAt(placedAt);
        this.setPaidAt(paidAt);
        this.setCanceledAt(canceledAt);
        this.setReadyAt(readyAt);
        this.setBilling(billing);
        this.setShipping(shipping);
        this.setStatus(status);
        this.setPaymentMethod(paymentMethod);
        this.setShippingCost(shippingCost);
        this.setExpectedDeliveryDate(expectedDeliveryDate);
        this.setItems(items);
    }

    public OrderId id() {
        return id;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public Money totalAmount() {
        return totalAmount;
    }

    public Quantity totalItems() {
        return totalItems;
    }

    public OffsetDateTime placedAt() {
        return placedAt;
    }

    public OffsetDateTime paidAt() {
        return paidAt;
    }

    public OffsetDateTime canceledAt() {
        return canceledAt;
    }

    public OffsetDateTime readyAt() {
        return readyAt;
    }

    public BillingInfo billing() {
        return billing;
    }

    public ShippingInfo shipping() {
        return shipping;
    }

    public OrderStatus status() {
        return status;
    }

    public PaymentMethod paymentMethod() {
        return paymentMethod;
    }

    public Money shippingCost() {
        return shippingCost;
    }

    public LocalDate expectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public Set<OrderItem> items() {
        return items;
    }

    private void setId(OrderId id) {
        requireNonNull(id, ErrorMessages.VALIDATION_ERROR_ORDERID_IS_NULL);
        this.id = id;
    }

    private void setCustomerId(CustomerId customerId) {
        requireNonNull(customerId, ErrorMessages.VALIDATION_ERROR_CUSTOMERID_IS_NULL);
        this.customerId = customerId;
    }

    private void setTotalAmount(Money totalAmount) {
        requireNonNull(totalAmount, ErrorMessages.VALIDATION_ERROR_MONEY_IS_NULL);
        this.totalAmount = totalAmount;
    }

    private void setTotalItems(Quantity totalItems) {
        requireNonNull(totalItems, ErrorMessages.VALIDATION_ERROR_MONEY_QUANTITY_IS_NULL);
        this.totalItems = totalItems;
    }

    private void setPlacedAt(OffsetDateTime placedAt) {
        this.placedAt = placedAt;
    }

    private void setPaidAt(OffsetDateTime paidAt) {
        this.paidAt = paidAt;
    }

    private void setCanceledAt(OffsetDateTime canceledAt) {
        this.canceledAt = canceledAt;
    }

    private void setReadyAt(OffsetDateTime readyAt) {
        this.readyAt = readyAt;
    }

    private void setBilling(BillingInfo billing) {
        requireNonNull(billing, ErrorMessages.VALIDATION_ERROR_BILLINGINFO_IS_NULL);
        this.billing = billing;
    }

    private void setShipping(ShippingInfo shipping) {
        requireNonNull(shipping, ErrorMessages.VALIDATION_ERROR_SHIPPINGINFO_IS_NULL);
        this.shipping = shipping;
    }

    private void setStatus(OrderStatus status) {
        requireNonNull(status, ErrorMessages.VALIDATION_ERROR_ORDERSTATUS_IS_NULL);
        this.status = status;
    }

    private void setPaymentMethod(PaymentMethod paymentMethod) {
        requireNonNull(paymentMethod, ErrorMessages.VALIDATION_ERROR_PAYMENTMETHOD_IS_NULL);
        this.paymentMethod = paymentMethod;
    }

    private void setShippingCost(Money shippingCost) {
        this.shippingCost = shippingCost;
    }

    private void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    private void setItems(Set<OrderItem> items) {
        requireNonNull(items, ErrorMessages.VALIDATION_ERROR_ITEMS_IS_NULL);
        this.items = items;
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
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
