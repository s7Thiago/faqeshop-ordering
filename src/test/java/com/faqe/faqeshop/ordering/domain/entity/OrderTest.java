package com.faqe.faqeshop.ordering.domain.entity;

import org.junit.jupiter.api.Test;

import com.faqe.faqeshop.ordering.domain.valueObject.id.CustomerId;

public class OrderTest {

    @Test
    public void shouldGenerate() {
        Order order = Order.draft(new CustomerId());
    }

}
