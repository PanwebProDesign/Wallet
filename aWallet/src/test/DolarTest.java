package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clases.Dolar;

class DolarTest {

	/**
	 * Probamos que el signo sea el mismo de dollar
	 */
	@Test
	void testGetSimbolo() {
		Dolar dolar = new Dolar();
        assertEquals("$", dolar.getSimbolo());
	}

	/**
	 * Verificamos que el factor de conversion sea el correcto
	 */
	@Test
	void testGetFactorDeConversion() {
		Dolar dolar = new Dolar();
        assertEquals(0.0013, dolar.getFactorDeConversion());
	}

	/**
	 * Test para convertir dolar a peso
	 * aplicamos un margen de error en assertEquals de 0.001
	 */
	@Test
	void testGetConvertir() {
		Dolar dolar = new Dolar();
        double valorEsperado =1000;
        assertEquals(valorEsperado, dolar.getConvertir(1.3),0.001);
	}

}
