package clases;

/**
 * @author Rodrigo Pantoja
 * Clase para datos de cuenta en general
 */
public class Cuenta {
	
	protected double saldo;
	
	/**
	 * metodo para iniciar el saldo cuando se abre la cuenta
	 * @param saldoInicial
	 */
    public Cuenta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

  
    /**
     * Generamos los Get & Set
     */
    public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/**
	 * Metodo para depositar
	 * @param cantidad
	 */
	public void depositar(double cantidad) {
        saldo += cantidad;
    }
	/**
	 * Metodo para retirar dinero de la cuenta
	 * @param cantidad que esta retirando
	 * @return Falso si no hay suficiente y Verdadero si se puede
	 */
    public boolean retirar(double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            return true;
        }
        return false;
    }

}
