package com.github.jvtopsilva090.utils;

public enum TipoOperacaoEnum {
    NONE,
    SOMA,
    SUBTRACAO,
    MULTIPLICACAO,
    DIVISAO,
    PORCENTAGEM,
    RAIZ_QUADRADA,
    POTENCIA;

    public static TipoOperacaoEnum from(String comando) {
        return switch (comando) {
            case "+" -> SOMA;
            case "-" -> SUBTRACAO;
            case "*" -> MULTIPLICACAO;
            case "/" -> DIVISAO;
            case "%" -> PORCENTAGEM;
            case "√" -> RAIZ_QUADRADA;
            case "^" -> POTENCIA;
            default -> throw new IllegalStateException("Unexpected value: " + comando);
        };
    }
}
