package zonafit.datos;

import zonafit.dominio.Cliente;

import java.util.List;

public interface ICLienteDAO {
    List<Cliente> listarClientes();
    boolean buscarCliente(Cliente cliente);
    boolean agregarCliente(Cliente cliente);
    boolean modificarCliente(Cliente cliente);
    boolean eliminarCliente(Cliente cliente);
}
