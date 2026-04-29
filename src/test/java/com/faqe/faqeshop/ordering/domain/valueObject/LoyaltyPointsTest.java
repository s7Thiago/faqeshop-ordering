package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoyaltyPointsTest {

    @Test
     void shouldGenerateWithValue() {
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);
        Assertions.assertThat(loyaltyPoints.value()).isEqualTo(10);
    }

    @Test
     void shouldAddWithValue() {
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);
        var addedLoyaltyPoint = loyaltyPoints.add(5);
        Assertions.assertThat(addedLoyaltyPoint.value()).isEqualTo(15);
    }

    @Test
     void shouldNotAddWithValue() {
        LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);
        
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> loyaltyPoints.add(-5))
            .withMessage(VALIDATION_ERROR_LOYALTYPOINTS_IS_NEGATIVE);

        Assertions.assertThat(loyaltyPoints.value()).isEqualTo(10);
    }
}
