package zonafit.presentacion;

import zonafit.datos.ClienteDAO;
import zonafit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp(){
        var salir = false;
        var consola = new Scanner(System.in);
        var clienteDAO = new ClienteDAO();

        while (!salir){
            try {
                var opcion =  mostarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDAO);
            }catch (Exception e){
                System.out.println("Error al ejecutar opciones: "+ e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostarMenu(Scanner consola){
        System.out.print("""
                ** Menú zona fit **
                1. Listar clientes
                2. Buscar clientes
                3. Agregar cliente
                4. Moificar cliente
                5. Eliminar cliente
                6. Salir
                
                Leige una opcion del menú: \s""");

        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, ClienteDAO clienteDAO){
        var salir = false;
        switch (opcion){
            case 1 -> {
                //1. Lista cliente
                System.out.println("** Lista de clientes **");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 ->{
                //2. Buscar clente
                System.out.println("Introduce el id del cliente a buscar");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarCliente(cliente);

                if (encontrado)
                    System.out.println("Ciente encontrado: "+cliente);
                else
                    System.out.println("Cliente no encontrado: "+cliente);
            }
            case 3 ->{
                //3. Registrar cliente
                System.out.println(" ** Agregar cliente **");
                System.out.print("Ingresa nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Ingresa apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Ingresa membresia: ");

                var membresia = Integer.parseInt(consola.nextLine());

                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDAO.agregarCliente(cliente);
                if (agregado)
                    System.out.println("Cliente agregado: " + cliente);
                else
                    System.out.println("Cliente no ingresado");
            }
            case 4 ->{
                //4. Moicicar cliente
                System.out.println("** Modificar un cliente **");
                System.out.print("Ingresa el id de cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.print("Ingresa el nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Ingresa el apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Ingresa membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());

                var cliente = new Cliente(idCliente, nombre, apellido, membresia);
                var modificado = clienteDAO.modificarCliente(cliente);

                if (modificado)
                    System.out.println("El cliente fue modificado");
                else
                    System.out.println("El cliente no fue modificado");

            }
            case 5 ->{
                //5. Eliminar cliente
                System.out.println("** Eliminar cliente **");
                System.out.print("Ingresa el id del cliente a elminar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminado = clienteDAO.eliminarCliente(cliente);

                if (eliminado)
                    System.out.println("Cliente eliminado: " + cliente);
                else
                    System.out.println("EL cliente no fue eliminado: "+ cliente);
            }
            case 6-> {
                // 6. Salir del sistema
                System.out.println("Vuelva pronto..");
                salir = true;
            }
            default -> System.out.println("Opción desconocida..");
        }
        return salir;
    }
}
