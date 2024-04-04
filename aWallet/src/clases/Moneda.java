package clases;

/**
 * @author Rodrigo Pantoja
 * Interfaz para menedas
 */
public interface Moneda {
	
	/**
	 * Simbolo
	 */
	String getSimbolo(); 
	/**
	 *Factor de conversion de cada moneda
	 */
    double getFactorDeConversion(); 
    /**
     * Metodo para convertir
     */
    double getConvertir(double cantidad);

}
