package com.faqe.faqeshop.ordering.domain.valueObject;

import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_DOCUMENT_IS_BLANK;
import static com.faqe.faqeshop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_DOCUMENT_IS_NULL;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class DocumentTest {
    @Test
    void testDocumentCannotBeNull() {
        assertThatThrownBy(() -> new Document(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage(VALIDATION_ERROR_DOCUMENT_IS_NULL);
    }

    @Test
    void testDocumentCannotBeBlank() {
        assertThatThrownBy(() -> new Document(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(VALIDATION_ERROR_DOCUMENT_IS_BLANK);
    }
}
