package com.faqe.faqeshop.ordering.domain.utility;

import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;

// Gera IDs únicos com ordenação temporal com UUIDv7
public class IdGenerator {

    private static final TimeBasedEpochRandomGenerator timeBasedGenerator = 
    Generators.timeBasedEpochRandomGenerator();

    private IdGenerator() {
        // Construtor privado para evitar instância
    }

    public static UUID generateTimeBasedUUID() {
        return timeBasedGenerator.generate();
    }

}
