/*
 * Repositório JPA para manipular dados da Entidade SequenciaEstavelOperacao
 */
package com.bolt.repository;

import com.bolt.entity.SequenciaEstavelOperacao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author priscilaluz
 */
public interface SequenciaEstavelOperacaoRepository extends JpaRepository<SequenciaEstavelOperacao, Long> {

    SequenciaEstavelOperacao findBySequencia(String sequencia);
}
