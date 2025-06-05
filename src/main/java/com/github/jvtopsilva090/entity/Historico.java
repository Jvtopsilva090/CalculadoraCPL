package com.github.jvtopsilva090.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Historico {

    private int id;
    private String operacao;
    private BigDecimal resultado;

    public Historico(String operacao, BigDecimal resultado) {
        this.operacao = operacao;
        this.resultado = resultado;
    }
}

