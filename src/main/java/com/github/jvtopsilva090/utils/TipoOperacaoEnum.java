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

    public String toDisplay() {
        return switch (this) {
            case SOMA -> "+";
            case SUBTRACAO -> "-";
            case MULTIPLICACAO -> "*";
            case DIVISAO -> "/";
            case PORCENTAGEM -> "%";
            case RAIZ_QUADRADA -> "√";
            case POTENCIA -> "^";
            case NONE -> "";
        };
    }
}
