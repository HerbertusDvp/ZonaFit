package zonafit.conexion;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;
        var db = "zonafit";
        var url = "jdbc:mysql://localhost:3306/" + db;
        var usuario = "root";
        var password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch (Exception e){
            System.out.println("Error al conectarse a las bd: " + e.getMessage());
        }

        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();

        if (conexion != null){
            System.out.println("Conexion exitosa: " + conexion);
        }else {
            System.out.println("Error al conectarse");
        }
    }
}
