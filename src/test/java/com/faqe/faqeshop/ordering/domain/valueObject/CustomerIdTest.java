package com.faqe.faqeshop.ordering.domain.valueObject;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerIdTest {

    @Test
    void testCustomerId() {
        var customerId = new CustomerId().value();
        assertThat(customerId).isNotNull();
    }

}
