package clases;

import java.util.Random;

/**
 * @author Rodrigo Pantoja
 * Clase para datos personales
 */
public class Cliente {
	
	/**
	 * Definimos los datos del cliente
	 */
	private String nombre;
	private String rut;
	private String numeroCuentaPeso;
	private String numeroCuentaDolar;
	private CuentaPeso cuentaPeso;
	private CuentaDolar cuentaDolar;
	
	/**
	 * constructor Vacio
	 */
	public Cliente() {
	}
	
	/**
	 * Constructor modificado para generar datos de numero de cuenta automaticamente
	 * @param nombreTitular
	 * @param rutTitular
	 * @param numCuentaPesosT
	 * @param numCuentaDolarT
	 * @param cuentaPeso ***Sera Aleatorio***
	 * @param cuentaDolar ***Sera Aleatorio***
	 */
	public Cliente(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
        this.numeroCuentaPeso = generarNumeroCuenta();
        this.numeroCuentaDolar = generarNumeroCuenta();;
	}
	/**
	 * Generamos el metodo para crear los numeros de cuenta aleatorios
	 * @return
	 */
	private String generarNumeroCuenta() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
	}
	
	/**
	 * Generamos Los getters & Setters
	 */
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getNumeroCuentaPeso() {
		return numeroCuentaPeso;
	}
	public void setNumeroCuentaPeso(String numeroCuentaPeso) {
		this.numeroCuentaPeso = numeroCuentaPeso;
	}
	public String getNumeroCuentaDolar() {
		return numeroCuentaDolar;
	}
	public void setNumeroCuentaDolar(String numeroCuentaDolar) {
		this.numeroCuentaDolar = numeroCuentaDolar;
	}
	public CuentaPeso getCuentaPeso() {
		return cuentaPeso;
	}
	public void setCuentaPeso(CuentaPeso cuentaPeso) {
		this.cuentaPeso = cuentaPeso;
	}
	public CuentaDolar getCuentaDolar() {
		return cuentaDolar;
	}
	public void setCuentaDolar(CuentaDolar cuentaDolar) {
		this.cuentaDolar = cuentaDolar;
	}

}
