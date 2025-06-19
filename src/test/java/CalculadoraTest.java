import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    void testSoma() {
        assertEquals(5, Calculadora.somar(2, 3));
    }

    @Test
    void testSubtracao() {
        assertEquals(1, Calculadora.subtrair(4, 3));
    }

    @Test
    void testMultiplicacao() {
        assertEquals(6, Calculadora.multiplicar(2, 3));
    }

    @Test
    void testDivisao() {
        assertEquals(2, Calculadora.dividir(6, 3));
        assertThrows(ArithmeticException.class, () -> Calculadora.dividir(5, 0));
    }
}
