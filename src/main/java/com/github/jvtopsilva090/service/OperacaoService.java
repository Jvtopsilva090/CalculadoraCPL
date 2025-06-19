package com.github.jvtopsilva090.service;

import com.github.jvtopsilva090.entity.FragmentoOperacao;
import com.github.jvtopsilva090.utils.TipoOperacaoEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;

public class OperacaoService {

    public BigDecimal calcularListaOperacao(List<FragmentoOperacao> operacaoList) {
        Iterator<FragmentoOperacao> iterator = operacaoList.iterator();


        BigDecimal total = BigDecimal.ZERO;

        BigDecimal pastValue = BigDecimal.ZERO;
        TipoOperacaoEnum tipoOperacao = TipoOperacaoEnum.NONE;

        while (iterator.hasNext()) {
            FragmentoOperacao operacao = iterator.next();

            switch (tipoOperacao) {
                case NONE, SOMA -> total = total.add(operacao.getValor());
                case SUBTRACAO -> total = total.subtract(operacao.getValor());
                case MULTIPLICACAO -> total = total.multiply(operacao.getValor());
                case DIVISAO -> total = total.divide(operacao.getValor(), 2, RoundingMode.HALF_EVEN);
                case POTENCIA -> {
                    int expoente = operacao.getValor().intValue();

                    if (operacao.getValor().intValue() >= 0) {
                        total = total.pow(expoente);
                    } else {
                        if (total.compareTo(BigDecimal.ZERO) == 0) {
                            total = BigDecimal.ONE;
                        } else {
                            expoente = operacao.getValor().intValue() * (-1);

                            total = BigDecimal.ONE.divide(
                                    total.pow(expoente),
                                    2,
                                    RoundingMode.HALF_EVEN
                            );
                        }
                    }
                }
                case RAIZ_QUADRADA -> total = total.add(BigDecimal.valueOf(Math.sqrt(operacao.getValor().doubleValue())));
                case PORCENTAGEM -> {
                    if (pastValue.equals(BigDecimal.ZERO)) {
                        total = BigDecimal.ZERO;
                    } else {
                        BigDecimal porcentagem = pastValue.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
                        total = total.subtract(pastValue).add(porcentagem.multiply(operacao.getValor()));
                    }
                }
            }

            tipoOperacao = operacao.getTipoOperacao();
            pastValue = operacao.getValor();
        }

        return total.setScale(2, RoundingMode.HALF_EVEN);
    }

    public String converterOperacaoCompletaToString(List<FragmentoOperacao> operacaoList) {
        StringBuilder stringValue = new StringBuilder();

        Iterator<FragmentoOperacao> iterator = operacaoList.iterator();

        while (iterator.hasNext()) {
            FragmentoOperacao operacao = iterator.next();

            stringValue.append(operacao.getValor().toString());

            if (iterator.hasNext()) {
                switch (operacao.getTipoOperacao()) {
                    case SOMA -> stringValue.append("+");
                    case SUBTRACAO -> stringValue.append("-");
                    case MULTIPLICACAO -> stringValue.append("*");
                    case DIVISAO -> stringValue.append("/");
                    case POTENCIA -> stringValue.append("^");
                    case PORCENTAGEM -> stringValue.append("%");
                    case RAIZ_QUADRADA -> stringValue.append("√");
                }
            }
        }

        return stringValue.toString();
    }
}
