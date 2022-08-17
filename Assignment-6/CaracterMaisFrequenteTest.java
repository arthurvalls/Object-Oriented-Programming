import org.junit.Test;

import static org.junit.Assert.*;

public class CaracterMaisFrequenteTest {

    @Test
    public void testarCaracterMaisFrequente() {
        assertEquals('a', CaracterMaisFrequente.encontrarCaracterMaisFrequenteQuadratica("arara"));
        assertEquals(' ', CaracterMaisFrequente.encontrarCaracterMaisFrequenteLinear("a r a r a 345 sdf hhh"));
    }
}