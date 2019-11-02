package com.bolt.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidade que guarda as quantidades de operações para estabilizar sequencia
 * @author priscilaluz
 */
@Getter
@Setter
@Entity
@Table(name = "SEQUENCIA_ESTAVEL_OPERACAO")
public class SequenciaEstavelOperacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "SEQUENCIA", nullable = false)
    private String sequencia;
    
    @Column(name = "QNT_OPERACAO", nullable = false)
    private Integer qntOperacoes;
    
}
