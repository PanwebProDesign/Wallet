package aWallet;

import java.util.ArrayList;
import java.util.Scanner;
import clases.Cliente;
import clases.Cuenta;
import clases.CuentaDolar;
import clases.CuentaPeso;
import clases.Dolar;
import clases.Info;
import clases.Peso;

/**
 * @author Rodrigo Pantoja
 * Clase main general, con metodos principales
 */
public class Main {

    public static void main(String[] args) {
       /**
        * Iniciamos Scanner
        */
    	Scanner scanner = new Scanner(System.in);
    	/**
    	 * Creamos array List de clientes, para almacenar mas de 1
    	 */
        ArrayList<Cliente> listaDeClientes = new ArrayList<>();

        /**
         * Generamos un menu para realizar todas las operaciones disponibles
         */
        int opcion;
        do {
            // Menu principal
            System.out.println("Bienvenido a **AlkeWallet 2.0** Seleccione una opcion");
            System.out.println("1. Crear nuevo cliente");
            System.out.println("2. Depositar en cuenta Peso o Dollar");
            System.out.println("3. Transferir saldo entre clientes");
            System.out.println("4. Retirar dinero de una cuenta");
            System.out.println("5. Consultar datos de un cliente");
            System.out.println("6. Informacion");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            /**
             * Creamos Switch para llamar a los metodos de cada caso
             */
            switch (opcion) {
                case 1:
                    crearNuevoCliente(scanner, listaDeClientes);
                    break;
                case 2:
                    depositarDinero(scanner, listaDeClientes);
                    break;
                case 3:
                    transferirSaldoEntreClientes(scanner, listaDeClientes);
                    break;
                case 4:
                    retirarDinero(scanner, listaDeClientes);
                    break;
                case 5:
                    consultarDatosCliente(scanner, listaDeClientes);
                    break;
                case 6:
                    Info.mostrarInfo();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione nuevamente.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }

    /**
     * Metodo para crear Clientes
     * @param scanner nombre del scanner que resive los datos
     * @param listaDeClientes array de clientes
     */
    private static void crearNuevoCliente(Scanner scanner, ArrayList<Cliente> listaDeClientes) {
        System.out.println("Ingrese los datos del nuevo cliente:");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("RUT: ");
        String rut = scanner.nextLine();
        System.out.println("Se creó una cuenta en pesos");
        System.out.print("Ingrese saldo inicial para la cuenta: ");
        double saldoInicialPeso = scanner.nextDouble();
        System.out.println("Se creó una cuenta adicional en Dólar");
        System.out.print("Ingrese saldo inicial para la cuenta: ");
        double saldoInicialDolar = scanner.nextDouble();
        scanner.nextLine();

        // creamos cliente, 
        Cliente cliente = new Cliente(nombre, rut);
        //creamos la cuenta peso y asignamos saldo inicial
        CuentaPeso cuentaPeso = new CuentaPeso(saldoInicialPeso);
        //creamos la cuenta dolar y asignamos saldo inicial
        CuentaDolar cuentaDolar = new CuentaDolar(saldoInicialDolar);
        
        //asignamos la cuenta peso y dolar al cliente creado
        cliente.setCuentaPeso(cuentaPeso);
        cliente.setCuentaDolar(cuentaDolar);

        //añadimos el cliente a la lista de clientes
        listaDeClientes.add(cliente);

        //mostramos la informacion del cliente
        System.out.println("Cliente creado:");
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("RUT: " + cliente.getRut());
        System.out.println("Numero de cuenta CLP: " + cliente.getNumeroCuentaPeso());
        System.out.println("Numero de cuenta USD: " + cliente.getNumeroCuentaDolar());
    }

    /**
     * Metodo para depositar en cuentas 
     */
    private static void depositarDinero(Scanner scanner, ArrayList<Cliente> listaDeClientes) {
        //si no hay clientes creados indicara que no hay clientes
    	if (listaDeClientes.isEmpty()) {
            System.out.println("No hay clientes.");
            return;
        }
        
        //pedimos el rut
        System.out.print("Ingrese el RUT del cliente: ");
        String rutCliente = scanner.nextLine();

        // Buscamos al cliente por RUT
        Cliente clienteEncontrado = buscarClientePorRut(listaDeClientes, rutCliente);
        if (clienteEncontrado == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        // Mostrar cuentas disponibles del cliente
        System.out.println("Cuentas disponibles para depósito del cliente " + clienteEncontrado.getNombre() + ":");
        mostrarCuentasYSaldos(clienteEncontrado);

        // seleccionamos una cuenta del cliente
        System.out.print("Seleccione la cuenta en la que desea depositar dinero: ");
        int opcionCuenta = scanner.nextInt();
        scanner.nextLine(); 

        // si la cuenta es valida la asignamos
        Cuenta cuentaSeleccionada = seleccionarCuenta(clienteEncontrado, opcionCuenta);
        if (cuentaSeleccionada == null) {
            System.out.println("Cuenta inválida.");
            return;
        }

        // pedimos monto a depositar
        System.out.print("Ingrese el monto a depositar: ");
        double montoDeposito = scanner.nextDouble();
        scanner.nextLine(); 

        //asignamos el deposito y lo sumamos
        cuentaSeleccionada.depositar(montoDeposito);
        System.out.println("¡Depósito exitoso! Saldo actual de la cuenta: " + cuentaSeleccionada.getSaldo());
    }

    /**
     * Metodo para transferir saldos
     */
    private static void transferirSaldoEntreClientes(Scanner scanner, ArrayList<Cliente> listaDeClientes) {
        /**
         * obligamos a que existan al menos cuentas para poder realizar la operacion,
         * de igual forma si hay dos, puede transferir el cliente dentro de sus mismas cuentas
         * dollar y peso
         */
    	if (listaDeClientes.size() < 2) {
            System.out.println("Debe haber al menos dos clientes para transferir saldo.");
            return;
        }

    	/**
    	 * Ingresamos los datos de transferencia
    	 * si no son correctos nos indicara que el cliente no se encontro
    	 */
        System.out.println("Transferencias:");
        System.out.print("Ingrese el RUT de la cuenta de ORIGEN: ");
        String rutOrigen = scanner.nextLine();
        Cliente clienteOrigen = buscarClientePorRut(listaDeClientes, rutOrigen);
        if (clienteOrigen == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        // Mostrar cuentas del cliente que transfiere
        System.out.println("Cuentas del cliente " + clienteOrigen.getNombre() + ":");
        mostrarCuentasYSaldos(clienteOrigen);

        // Seleccionar cuenta la cuenta con la que quiere transferir
        System.out.print("Seleccione la cuenta de origen: ");
        int opcionCuentaOrigen = scanner.nextInt();
        scanner.nextLine();
        Cuenta cuentaOrigen = seleccionarCuenta(clienteOrigen, opcionCuentaOrigen);
        if (cuentaOrigen == null) {
            System.out.println("Cuenta de origen inválida.");
            return;
        }

        // Ingresar monto a transferir
        System.out.print("Ingrese el monto a transferir: ");
        double montoTransferencia = scanner.nextDouble();
        scanner.nextLine();

        // Ingresar RUT del cliente de destino
        System.out.print("Ingrese el RUT del cliente destino: ");
        String rutDestino = scanner.nextLine();
        Cliente clienteDestino = buscarClientePorRut(listaDeClientes, rutDestino);
        if (clienteDestino == null) {
            System.out.println("Cliente destino no encontrado.");
            return;
        }

        // Muestra las cuentas del cliente de destino
        System.out.println("Cuentas del cliente " + clienteDestino.getNombre() + ":");
        mostrarCuentasYSaldos(clienteDestino);

        // Seleccionamos la cuenta a la que se depositara
        System.out.print("Seleccione la cuenta de destino: ");
        int opcionCuentaDestino = scanner.nextInt();
        scanner.nextLine();
        Cuenta cuentaDestino = seleccionarCuenta(clienteDestino, opcionCuentaDestino);
        if (cuentaDestino == null) {
            System.out.println("Cuenta de destino inválida.");
            return;
        }

        /**
         * Transferencia de Cuenta Peso a Dolar
         * ocupa factor de conversion y  convierte la moneda si la transferencia es exitosa
         */
        if (cuentaOrigen instanceof CuentaPeso && cuentaDestino instanceof CuentaDolar) {
        	System.out.println("transfiriendo entre cuenta - CL a USD");
			Peso peso = new Peso();
        	if (cuentaOrigen.retirar(montoTransferencia)) {
            	montoTransferencia = peso.getConvertir(montoTransferencia);
    			cuentaDestino.depositar(montoTransferencia);
    			System.out.println("Transferencia exitosa.");
    			System.out.println("Nuevo saldo de la cuenta de origen: " + cuentaOrigen.getSaldo());
                System.out.println("Nuevo saldo de la cuenta de destino: " + cuentaDestino.getSaldo());
			}else {
	            System.out.println("No se pudo realizar la transferencia. Saldo insuficiente en la cuenta");
	        }
		}
        /**
         * Transferencia de Cuenta Dolar a Peso
         * ocupa factor de conversion y convierte la moneda si la transferencia es exitosa
         */
        if (cuentaOrigen instanceof CuentaDolar && cuentaDestino instanceof CuentaPeso) {
			System.out.println("transfiriendo entre cuenta - USD a CLP");
			Dolar dolar = new Dolar();
			if (cuentaOrigen.retirar(montoTransferencia)) {
				montoTransferencia= dolar.getConvertir(montoTransferencia);
				cuentaDestino.depositar(montoTransferencia);
				System.out.println("Transferencia exitosa.");
    			System.out.println("Nuevo saldo de la cuenta de origen: " + cuentaOrigen.getSaldo());
                System.out.println("Nuevo saldo de la cuenta de destino: " + cuentaDestino.getSaldo());
			} else {
				System.out.println("No se pudo realizar la transferencia. Saldo insuficiente en la cuenta");
			}
		}
        /**
         * Transferencia de cuenta peso a peso
         * no ocupa factor de conversion.
         */
        if (cuentaOrigen instanceof CuentaPeso && cuentaDestino instanceof CuentaPeso) {
			System.out.println("transfiriendo entre cuenta - CLP a CLP");
			if (cuentaOrigen.retirar(montoTransferencia)) {
				cuentaDestino.depositar(montoTransferencia);
				System.out.println("Transferencia exitosa.");
				System.out.println("Nuevo saldo de la cuenta de origen: " + cuentaOrigen.getSaldo());
	            System.out.println("Nuevo saldo de la cuenta de destino: " + cuentaDestino.getSaldo());
			} else {
				System.out.println("No se pudo realizar la transferencia. Saldo insuficiente en la cuenta");
			}
		}
        /**
         * Transferencia de cuenta dolar a dolar
         * no ocupa factor de conversion.
         */
        if (cuentaOrigen instanceof CuentaDolar && cuentaDestino instanceof CuentaDolar) {
        	System.out.println("transfiriendo entre cuenta - USD a USD");
			if (cuentaOrigen.retirar(montoTransferencia)) {		
				cuentaDestino.depositar(montoTransferencia);
				System.out.println("Transferencia exitosa.");
				System.out.println("Nuevo saldo de la cuenta de origen: " + cuentaOrigen.getSaldo());
	            System.out.println("Nuevo saldo de la cuenta de destino: " + cuentaDestino.getSaldo());
			} else {
				System.out.println("No se pudo realizar la transferencia. Saldo insuficiente en la cuenta");
			}
		}
    }

    /**
     * Metodo para retirar dinero de una cuenta de la lista de clientes
     * @param scanner
     * @param listaDeClientes
     */
    private static void retirarDinero(Scanner scanner, ArrayList<Cliente> listaDeClientes) {
        if (listaDeClientes.isEmpty()) {
            System.out.println("No hay clientes para realizar retiros.");
            return;
        }
        
        System.out.print("Ingrese el RUT del cliente: ");
        String rutCliente = scanner.nextLine();

        // Buscar cliente por RUT
        Cliente clienteEncontrado = buscarClientePorRut(listaDeClientes, rutCliente);
        if (clienteEncontrado == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        // Mostrar cuentas disponibles para retiro
        System.out.println("Cuentas disponibles para retiro del cliente " + clienteEncontrado.getNombre() + ":");
        mostrarCuentasYSaldos(clienteEncontrado);

        // Seleccionar cuenta para retirar
        System.out.print("Seleccione la cuenta de la que desea retirar dinero: ");
        int opcionCuenta = scanner.nextInt();
        scanner.nextLine(); 

        // Asignar la cuenta seleccionada
        Cuenta cuentaSeleccionada = seleccionarCuenta(clienteEncontrado, opcionCuenta);
        if (cuentaSeleccionada == null) {
            System.out.println("Cuenta inválida.");
            return;
        }

        // Realizar el retiro
        System.out.print("Ingrese el monto a retirar: ");
        double montoRetiro = scanner.nextDouble();
        scanner.nextLine(); 
        //si se puede retirar se llama al metodo retirar dinero
        if (cuentaSeleccionada.retirar(montoRetiro)) {
            System.out.println("¡Retiro exitoso! Saldo actual de la cuenta: " + cuentaSeleccionada.getSaldo());
        } else {
            System.out.println("No se pudo realizar el retiro. Saldo insuficiente en la cuenta.");
        }
    }

    /**
     * Metodo para saber los datos del cliente
     * nos muestra el cliente con su informacion de cuentas
     * @param scanner
     * @param listaDeClientes
     */
    private static void consultarDatosCliente(Scanner scanner, ArrayList<Cliente> listaDeClientes) {
        if (listaDeClientes.isEmpty()) {
            System.out.println("No hay clientes para consultar datos.");
            return;
        }
        
        System.out.print("Ingrese el RUT del cliente: ");
        String rutCliente = scanner.nextLine();

        // Buscar cliente por RUT
        Cliente clienteEncontrado = buscarClientePorRut(listaDeClientes, rutCliente);
        if (clienteEncontrado == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        // Mostrar datos del cliente encontrado
        System.out.println("Datos del cliente:");
        System.out.println("Nombre: " + clienteEncontrado.getNombre());
        System.out.println("RUT: " + clienteEncontrado.getRut());
        System.out.println("Número de cuenta en pesos: " + clienteEncontrado.getNumeroCuentaPeso() + ", Saldo: " + clienteEncontrado.getCuentaPeso().getSaldo());
        System.out.println("Número de cuenta en dólares: " + clienteEncontrado.getNumeroCuentaDolar() + ", Saldo: " + clienteEncontrado.getCuentaDolar().getSaldo());
    }

    /**
     * metodo para mostrar las cuentas dolar y peso del cliente
     * @param cliente 
     */
    private static void mostrarCuentasYSaldos(Cliente cliente) {
        System.out.println("1. Cuenta en pesos: " + cliente.getNumeroCuentaPeso() + ", Saldo: " + cliente.getCuentaPeso().getSaldo());
        System.out.println("2. Cuenta en dólares: " + cliente.getNumeroCuentaDolar() + ", Saldo: " + cliente.getCuentaDolar().getSaldo());
    }

    /**
     * Metodo para seleccionar una cuenta de cliente cuando sea necesario en los menu
     * @param cliente
     * @param opcionCuenta
     * @return
     */
    private static Cuenta seleccionarCuenta(Cliente cliente, int opcionCuenta) {
        switch (opcionCuenta) {
            case 1:
                return cliente.getCuentaPeso();
            case 2:
                return cliente.getCuentaDolar();
            default:
                return null;
        }
    }

    /**
     * Metodo para buscar en el array list los clientes por su rut
     * @param listaDeClientes
     * @param rut
     * @return
     */
    private static Cliente buscarClientePorRut(ArrayList<Cliente> listaDeClientes, String rut) {
        for (Cliente cliente : listaDeClientes) {
            if (cliente.getRut().equals(rut)) {
                return cliente;
            }
        }
        return null;
    }
}
