package clases;

public class Dolar implements Moneda {
    
	/**
	 * Simbolo de Dolar
	 */
	@Override
    public String getSimbolo() {
        return "$"; // Símbolo del dólar
    }

    /**
     * Factor de conversion de dolar
     */
    @Override
    public double getFactorDeConversion() {
        return 0.0013; // Factor de conversión de dólar a dólar (1 dólar es igual a 1 dólar)
    }

    /**
     * Metodo para convertir de dolar a peso
     */
    @Override
    public double getConvertir(double cantidad) {
    	return cantidad / getFactorDeConversion(); // Convertir la cantidad a pesos
    }
}

