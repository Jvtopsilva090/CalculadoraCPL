import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    void testSoma() {
        assertEquals(5, Calculadora.somar(2, 3));
        assertEquals(-1, Calculadora.somar(-3, 2));
        assertEquals(0, Calculadora.somar(0, 0));
    }

    @Test
    void testSubtracao() {
        assertEquals(1, Calculadora.subtrair(4, 3));
        assertEquals(-5, Calculadora.subtrair(-3, 2));
        assertEquals(0, Calculadora.subtrair(0, 0));
    }

    @Test
    void testMultiplicacao() {
        assertEquals(6, Calculadora.multiplicar(2, 3));
        assertEquals(-6, Calculadora.multiplicar(-2, 3));
        assertEquals(0, Calculadora.multiplicar(0, 999));
    }

    @Test
    void testDivisao() {
        assertEquals(2, Calculadora.dividir(6, 3));
        assertEquals(-2, Calculadora.dividir(-6, 3));
        assertThrows(ArithmeticException.class, () -> {
            Calculadora.dividir(10, 0);
        });
    }
}