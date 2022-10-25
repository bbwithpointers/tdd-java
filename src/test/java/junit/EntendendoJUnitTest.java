package junit;

import org.estudostdd.CamelCaseConverter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EntendendoJUnitTest {
    CamelCaseConverter camelCase;

    @Before
    public void setup() {
        camelCase = new CamelCaseConverter();

    }


    @Test
    public void aplicarCamelCaseEmNomeUnico() throws Exception {
        assertEquals("Lionel", camelCase.converter("lionel"));
    }

    @Test
    public void deveConverterNomeSimplesMisturadoMaiusculoEMinusculo() throws Exception {
        assertEquals("Lionel", camelCase.converter("lIoNeL"));
    }

}

