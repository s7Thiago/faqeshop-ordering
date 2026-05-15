package com.faqe.faqeshop.ordering.domain.entity;

import com.faqe.faqeshop.ordering.domain.valueObject.Money;
import com.faqe.faqeshop.ordering.domain.valueObject.ProductName;
import com.faqe.faqeshop.ordering.domain.valueObject.Quantity;
import com.faqe.faqeshop.ordering.domain.valueObject.id.OrderId;
import com.faqe.faqeshop.ordering.domain.valueObject.id.OrderItemId;
import com.faqe.faqeshop.ordering.domain.valueObject.id.ProductId;
import java.util.Objects;
import com.faqe.faqeshop.ordering.domain.exception.ErrorMessages;

public class OrderItem {

    private OrderItemId id;
    private OrderId orderId;

    private ProductId productId;
    private ProductName productName;

    private Money price;
    private Quantity quantity;

    private Money totalAmount;

    public OrderItem(OrderItemId id,
            OrderId orderId,
            ProductId productId,
            ProductName productName,
            Money price,
            Quantity quantity,
            Money totalAmount) {
        this.setId(id);
        this.setOrderId(orderId);
        this.setProductId(productId);
        this.setProductName(productName);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setTotalAmount(totalAmount);
    }

    public OrderItemId id() {
        return id;
    }

    public OrderId orderId() {
        return orderId;
    }

    public ProductId productId() {
        return productId;
    }

    public ProductName productName() {
        return productName;
    }

    public Money price() {
        return price;
    }

    public Quantity quantity() {
        return quantity;
    }

    public Money totalAmount() {
        return totalAmount;
    }

    private void setId(OrderItemId id) {
        Objects.requireNonNull(id, ErrorMessages.VALIDATION_ERROR_ORDERITEMID_IS_NULL);
        this.id = id;
    }

    private void setOrderId(OrderId orderId) {
        Objects.requireNonNull(orderId, ErrorMessages.VALIDATION_ERROR_ORDERID_IS_NULL);
        this.orderId = orderId;
    }

    private void setProductId(ProductId productId) {
        Objects.requireNonNull(productId, ErrorMessages.VALIDATION_ERROR_PRODUCTID_IS_NULL);
        this.productId = productId;
    }

    private void setProductName(ProductName productName) {
        Objects.requireNonNull(productName, ErrorMessages.VALIDATION_ERROR_PRODUCTNAME_IS_NULL);
        this.productName = productName;
    }

    private void setPrice(Money price) {
        Objects.requireNonNull(price, ErrorMessages.VALIDATION_ERROR_MONEY_IS_NULL);
        this.price = price;
    }

    private void setQuantity(Quantity quantity) {
        Objects.requireNonNull(quantity, ErrorMessages.VALIDATION_ERROR_MONEY_QUANTITY_IS_NULL);
        this.quantity = quantity;
    }

    private void setTotalAmount(Money totalAmount) {
        Objects.requireNonNull(totalAmount, ErrorMessages.VALIDATION_ERROR_MONEY_IS_NULL);
        this.totalAmount = totalAmount;
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
        OrderItem other = (OrderItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
