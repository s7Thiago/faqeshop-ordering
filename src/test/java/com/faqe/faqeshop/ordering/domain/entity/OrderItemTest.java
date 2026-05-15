package com.faqe.faqeshop.ordering.domain.entity;

import org.junit.jupiter.api.Test;

import com.faqe.faqeshop.ordering.domain.valueObject.Money;
import com.faqe.faqeshop.ordering.domain.valueObject.ProductName;
import com.faqe.faqeshop.ordering.domain.valueObject.Quantity;
import com.faqe.faqeshop.ordering.domain.valueObject.id.OrderId;
import com.faqe.faqeshop.ordering.domain.valueObject.id.ProductId;

public class OrderItemTest {

    @Test
    public void shouldGenerate() {
        OrderItem orderItem = OrderItem.brandNew()
                .orderId(new OrderId())
                .productId(new ProductId())
                .productName(new ProductName("Mouse pad"))
                .price(new Money("100.0"))
                .quantity(new Quantity(2))
                .build();
    }
}
