package com.github.jvtopsilva090.entity;

import com.github.jvtopsilva090.utils.TipoOperacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FragmentoOperacao {
    private BigDecimal valor;
    private TipoOperacaoEnum tipoOperacao; //Tipo de operacao que sera feito com o proximo valor
}
