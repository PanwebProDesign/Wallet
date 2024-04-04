package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clases.Cuenta;

class CuentaTest {

	/**
	 * Test al constructor
	 */
	@Test
	void testCuenta() {
		double saldoInicial = 100;
        Cuenta cuenta = new Cuenta(saldoInicial);
        assertEquals(saldoInicial, cuenta.getSaldo());
	}

	@Test
	void testDepositar() {
		double saldoInicial = 100;
        double cantidadDeposito = 50;
        Cuenta cuenta = new Cuenta(saldoInicial);
        cuenta.depositar(cantidadDeposito);
        assertEquals(saldoInicial + cantidadDeposito, cuenta.getSaldo());
	}

	@Test
	void testRetirar() {
		double saldoInicial = 100;
        double cantidadRetiro = 50;
        Cuenta cuenta = new Cuenta(saldoInicial);
        assertTrue(cuenta.retirar(cantidadRetiro));
        assertEquals(saldoInicial - cantidadRetiro, cuenta.getSaldo());
	}
	
	@Test
    public void testRetirarInsuficiente() {
        double saldoInicial = 100.0;
        double cantidadRetiro = 150.0;
        Cuenta cuenta = new Cuenta(saldoInicial);
        assertFalse(cuenta.retirar(cantidadRetiro));
        assertEquals(saldoInicial, cuenta.getSaldo(), 0.001);
    }

}
