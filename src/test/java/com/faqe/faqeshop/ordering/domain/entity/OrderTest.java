package com.faqe.faqeshop.ordering.domain.entity;

import static org.mockito.Mockito.inOrder;

import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.faqe.faqeshop.ordering.domain.valueObject.Money;
import com.faqe.faqeshop.ordering.domain.valueObject.ProductName;
import com.faqe.faqeshop.ordering.domain.valueObject.Quantity;
import com.faqe.faqeshop.ordering.domain.valueObject.id.CustomerId;
import com.faqe.faqeshop.ordering.domain.valueObject.id.OrderId;
import com.faqe.faqeshop.ordering.domain.valueObject.id.ProductId;

public class OrderTest {

    @Test
    public void shouldGenerate() {
        Order order = Order.draft(new CustomerId());
    }

    @Test
    void shouldAddItem() {
        Order order = Order.draft(new CustomerId());

        OrderItem orderItem = OrderItem.brandNew()
                .orderId(new OrderId())
                .productId(new ProductId())
                .productName(new ProductName("Mouse pad"))
                .price(new Money("100"))
                .quantity(new Quantity(2))
                .build();

        Assertions.assertThat(order.items()).isEmpty();

        order.addItem(
                orderItem.productId(),
                orderItem.productName(),
                orderItem.price(),
                orderItem.quantity());

        Assertions.assertThat(order.items()).hasSize(1);

        OrderItem itemAdded = order.items().iterator().next();
        Assertions.assertWith(itemAdded,
                (i) -> Assertions.assertThat(i.id()).isNotNull(),
                (i) -> Assertions.assertThat(i.productName()).isEqualTo(orderItem.productName()),
                (i) -> Assertions.assertThat(i.price()).isEqualTo(new Money("100")),
                (i) -> Assertions.assertThat(i.quantity()).isEqualTo(new Quantity(2))

        );
    }

    @Test
    public void shouldGenerateExceptionWhenTryToChangeItemSet() {
        Order order = Order.draft(new CustomerId());

        OrderItem orderItem = OrderItem.brandNew()
                .orderId(new OrderId())
                .productId(new ProductId())
                .productName(new ProductName("Mouse pad"))
                .price(new Money("100"))
                .quantity(new Quantity(2))
                .build();

        Assertions.assertThat(order.items()).isEmpty();

        order.addItem(
                orderItem.productId(),
                orderItem.productName(),
                orderItem.price(),
                orderItem.quantity());

        Set<OrderItem> items = order.items();

        Assertions.assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(items::clear);
    }

}
