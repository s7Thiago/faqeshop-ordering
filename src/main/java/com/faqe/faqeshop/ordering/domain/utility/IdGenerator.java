package com.faqe.faqeshop.ordering.domain.utility;

import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;

import io.hypersistence.tsid.TSID;

// Gera IDs únicos com ordenação temporal com UUIDv7
public class IdGenerator {

    private static final TimeBasedEpochRandomGenerator timeBasedGenerator = 
    Generators.timeBasedEpochRandomGenerator();

    // Essa factory carrega variáveis de ambiente que só precisam ser informadas em ambiente produtivo.
    private static final TSID.Factory tsidFactory = TSID.Factory.INSTANCE;

    private IdGenerator() {
        // Construtor privado para evitar instância
    }

    public static UUID generateTimeBasedUUID() {
        return timeBasedGenerator.generate();
    }
    
    /**
     * Gera um ID único do tipo TSID.
     * 
     * Necessário passar em produção:
     *  - TSID_NODE: Quantidade de microsserviços no nó
     *  - TSID_NODE_COUNT: Quantidade total de nós (clusters) no ambiente
     * 
     * @return O ID gerado.
     */
    public static TSID generateTsid() {
        return tsidFactory.generate();
    }

}
