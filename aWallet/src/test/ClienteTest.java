package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clases.Cliente;

class ClienteTest {

	/**
	 * Test Constructor Vacio
	 */
	@Test
	void testCliente() {
		Cliente cliente = new Cliente();
        assertNotNull(cliente);
	}

	/**
	 * Verificamos que podemos crear los clientes con sus cuentas
	 */
	@Test
    public void testConstructorConDatos() {
        String nombre = "Usuario";
        String rut = "123456789";
        Cliente cliente = new Cliente(nombre, rut);
        assertNotNull(cliente);
        assertEquals(nombre, cliente.getNombre());
        assertEquals(rut, cliente.getRut());
        assertNotNull(cliente.getNumeroCuentaPeso());
        assertNotNull(cliente.getNumeroCuentaDolar());
    }

}