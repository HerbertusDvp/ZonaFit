package zonafit.datos;

import zonafit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zonafit.conexion.Conexion.getConexion;

public class ClienteDAO implements ICLienteDAO{
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();

        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var query = "select * from cliente order by id";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));

                clientes.add(cliente);

            }

        }catch (Exception e){
            System.out.println("Error al listar los clientes: "+ e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }

        return clientes;
    }

    @Override
    public boolean buscarCliente(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        var con = getConexion();
        var query ="select * from cliente where id = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();

            if (rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }

        }catch (Exception e){
            System.out.println("Error al buscar el cliente: " + e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {

        PreparedStatement ps;
        Connection con = getConexion();
        var query = "insert into cliente (nombre, apellido, membresia)" +
                "values (?, ?, ?)";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al insertar el cliente: "+ e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        return false;
    }

    public static void main(String[] args) {
        ICLienteDAO clientesDAO = new ClienteDAO();

        // Lista clientes
        //System.out.println("*** Listado de cientes ***");
        //var clientes = clientesDAO.listarClientes();
        //clientes.forEach(System.out::println);

        // Buscar por id
//        var cliente = new Cliente(2);
//        System.out.println("Ciente antes de la busqueda: " + cliente);
//        var encontrado = clientesDAO.buscarCliente(cliente);
//
//        if (encontrado)
//            System.out.println("Cliente encontrado: " + cliente);
//        else
//            System.out.println("Cliente no encontrado: " + cliente.getId());
//
        // Agregar cliente

        var cliente = new Cliente("Daniel", "Ortiz", 300);
        var agregado = clientesDAO.agregarCliente(cliente);

        if (agregado)
            System.out.println("Cliente agregado");
        else
            System.out.println("Error al agregar el cliente");

        System.out.println("*** Listado de cientes ***");
        var clientes = clientesDAO.listarClientes();
        clientes.forEach(System.out::println);
    }
}

