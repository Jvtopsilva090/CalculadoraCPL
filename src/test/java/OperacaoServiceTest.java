import com.github.jvtopsilva090.entity.FragmentoOperacao;
import com.github.jvtopsilva090.service.OperacaoService;
import com.github.jvtopsilva090.utils.TipoOperacaoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OperacaoServiceTest {

    private final OperacaoService operacaoService = new OperacaoService();

    private final BigDecimal a = new BigDecimal(11);
    private final BigDecimal b = new BigDecimal(7);
    private final BigDecimal c = new BigDecimal(-35);
    private final BigDecimal d = new BigDecimal(0);

    @Test
    @DisplayName("Teste Operação de Soma")
    void testeOperacaoSoma() {
        final List<FragmentoOperacao> operacao1 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.SOMA));
            add(new FragmentoOperacao(b, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao2 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.SOMA));
            add(new FragmentoOperacao(c, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao3 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.SOMA));
            add(new FragmentoOperacao(d, TipoOperacaoEnum.NONE));
        }};

        final BigDecimal resultado1 = operacaoService.calcularListaOperacao(operacao1);
        final BigDecimal resultado2 = operacaoService.calcularListaOperacao(operacao2);
        final BigDecimal resultado3 = operacaoService.calcularListaOperacao(operacao3);

        Assertions.assertEquals(new BigDecimal("18.00"), resultado1);
        Assertions.assertEquals(new BigDecimal("-24.00"), resultado2);
        Assertions.assertEquals(new BigDecimal("11.00"), resultado3);
    }

    @Test
    @DisplayName("Teste Operação de Subtração")
    void testeOperacaoSubtracao() {
        final List<FragmentoOperacao> operacao1 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.SUBTRACAO));
            add(new FragmentoOperacao(b, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao2 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.SUBTRACAO));
            add(new FragmentoOperacao(c, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao3 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.SUBTRACAO));
            add(new FragmentoOperacao(d, TipoOperacaoEnum.NONE));
        }};

        final BigDecimal resultado1 = operacaoService.calcularListaOperacao(operacao1);
        final BigDecimal resultado2 = operacaoService.calcularListaOperacao(operacao2);
        final BigDecimal resultado3 = operacaoService.calcularListaOperacao(operacao3);

        Assertions.assertEquals(new BigDecimal("4.00"), resultado1);
        Assertions.assertEquals(new BigDecimal("46.00"), resultado2);
        Assertions.assertEquals(new BigDecimal("11.00"), resultado3);
    }

    @Test
    @DisplayName("Teste Operação de Multiplicação")
    void testeOperacaoMultiplicacao() {
        final List<FragmentoOperacao> operacao1 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.MULTIPLICACAO));
            add(new FragmentoOperacao(b, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao2 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.MULTIPLICACAO));
            add(new FragmentoOperacao(c, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao3 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.MULTIPLICACAO));
            add(new FragmentoOperacao(d, TipoOperacaoEnum.NONE));
        }};

        final BigDecimal resultado1 = operacaoService.calcularListaOperacao(operacao1);
        final BigDecimal resultado2 = operacaoService.calcularListaOperacao(operacao2);
        final BigDecimal resultado3 = operacaoService.calcularListaOperacao(operacao3);

        Assertions.assertEquals(new BigDecimal("77.00"), resultado1);
        Assertions.assertEquals(new BigDecimal("-385.00"), resultado2);
        Assertions.assertEquals(new BigDecimal("0.00"), resultado3);
    }

    @Test
    @DisplayName("Teste Operação de Divisão")
    void testeOperacaoDivisao() {
        final List<FragmentoOperacao> operacao1 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.DIVISAO));
            add(new FragmentoOperacao(b, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao2 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.DIVISAO));
            add(new FragmentoOperacao(c, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao3 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.DIVISAO));
            add(new FragmentoOperacao(d, TipoOperacaoEnum.NONE));
        }};

        final BigDecimal resultado1 = operacaoService.calcularListaOperacao(operacao1);
        final BigDecimal resultado2 = operacaoService.calcularListaOperacao(operacao2);

        Assertions.assertEquals(new BigDecimal("1.57"), resultado1);
        Assertions.assertEquals(new BigDecimal("-0.31"), resultado2);
        // Deve retorna erro por ser divisão com 0
        Assertions.assertThrowsExactly(ArithmeticException.class, () -> operacaoService.calcularListaOperacao(operacao3));
    }

    @Test
    @DisplayName("Teste Operação de Porcentagem")
    void testeOperacaoPorcentagem() {
        final List<FragmentoOperacao> operacao1 = new ArrayList<>() {{
            // 11% de 7
            add(new FragmentoOperacao(a, TipoOperacaoEnum.PORCENTAGEM));
            add(new FragmentoOperacao(b, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao2 = new ArrayList<>() {{
            // 11% de -35
            add(new FragmentoOperacao(a, TipoOperacaoEnum.PORCENTAGEM));
            add(new FragmentoOperacao(c, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao3 = new ArrayList<>() {{
            // 11% de 0
            add(new FragmentoOperacao(a, TipoOperacaoEnum.PORCENTAGEM));
            add(new FragmentoOperacao(d, TipoOperacaoEnum.NONE));
        }};

        final BigDecimal resultado1 = operacaoService.calcularListaOperacao(operacao1);
        final BigDecimal resultado2 = operacaoService.calcularListaOperacao(operacao2);
        final BigDecimal resultado3 = operacaoService.calcularListaOperacao(operacao3);

        Assertions.assertEquals(new BigDecimal("0.77"), resultado1);
        Assertions.assertEquals(new BigDecimal("-3.85"), resultado2);
        Assertions.assertEquals(new BigDecimal("0.00"), resultado3);
    }

    @Test
    @DisplayName("Teste Operação de Raiz Quadrada")
    void testeOperacaoRaizQuadrada() {
        final List<FragmentoOperacao> operacao1 = new ArrayList<>() {{
            add(new FragmentoOperacao(new BigDecimal(0), TipoOperacaoEnum.RAIZ_QUADRADA));
            add(new FragmentoOperacao(a, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao2 = new ArrayList<>() {{
            add(new FragmentoOperacao(new BigDecimal(0), TipoOperacaoEnum.RAIZ_QUADRADA));
            add(new FragmentoOperacao(b, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao3 = new ArrayList<>() {{
            add(new FragmentoOperacao(new BigDecimal(0), TipoOperacaoEnum.RAIZ_QUADRADA));
            add(new FragmentoOperacao(c, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao4 = new ArrayList<>() {{
            add(new FragmentoOperacao(new BigDecimal(0), TipoOperacaoEnum.RAIZ_QUADRADA));
            add(new FragmentoOperacao(d, TipoOperacaoEnum.NONE));
        }};

        final BigDecimal resultado1 = operacaoService.calcularListaOperacao(operacao1);
        final BigDecimal resultado2 = operacaoService.calcularListaOperacao(operacao2);
        final BigDecimal resultado4 = operacaoService.calcularListaOperacao(operacao4);

        Assertions.assertEquals(new BigDecimal("3.32"), resultado1);
        Assertions.assertEquals(new BigDecimal("2.65"), resultado2);
        // Deve retornar erro por ser raiz quadrada de um numero negativo
        Assertions.assertThrowsExactly(NumberFormatException.class, () -> operacaoService.calcularListaOperacao(operacao3));
        Assertions.assertEquals(new BigDecimal("0.00"), resultado4);
    }

    @Test
    @DisplayName("Teste Operação de Pontencia")
    void testeOperacaoPotencia() {
        final List<FragmentoOperacao> operacao1 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.POTENCIA));
            add(new FragmentoOperacao(b, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao2 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.POTENCIA));
            add(new FragmentoOperacao(c, TipoOperacaoEnum.NONE));
        }};

        final List<FragmentoOperacao> operacao3 = new ArrayList<>() {{
            add(new FragmentoOperacao(a, TipoOperacaoEnum.POTENCIA));
            add(new FragmentoOperacao(d, TipoOperacaoEnum.NONE));
        }};

        final BigDecimal resultado1 = operacaoService.calcularListaOperacao(operacao1);
        final BigDecimal resultado2 = operacaoService.calcularListaOperacao(operacao2);
        final BigDecimal resultado3 = operacaoService.calcularListaOperacao(operacao3);

        Assertions.assertEquals(new BigDecimal("19487171.00"), resultado1);
        Assertions.assertEquals(new BigDecimal("0.00"), resultado2);
        Assertions.assertEquals(new BigDecimal("1.00"), resultado3);
    }

    @Test
    void converterOperacaoCompletaToString() {
//        Assertions.assertEquals(
//                "11+7",
//                operacaoService.converterOperacaoCompletaToString(operacao1)
//        );
//        Assertions.assertEquals(
//                "11+-35",
//                operacaoService.converterOperacaoCompletaToString(operacao2)
//        );
    }
}
