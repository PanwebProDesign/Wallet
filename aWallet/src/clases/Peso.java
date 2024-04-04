package clases;

public class Peso implements Moneda {
    
	/**
	 * Simbolo Peso
	 */
	@Override
    public String getSimbolo() {
        return "$"; // Símbolo del peso
    }

	/**
	 * Factor de conversion
	 */
    @Override
    public double getFactorDeConversion() {
        return 0.0013; // Factor de conversión de peso a dólar
    }

    /**
     * Convertimos de peso a dolar
     */
    @Override
    public double getConvertir(double cantidad) {
        return cantidad * getFactorDeConversion(); // Convertir la cantidad a peso
    }
}