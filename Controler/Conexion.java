package Controler;

import java.sql.*;
import javax.swing.JOptionPane;
public class Conexion {
    Conexion() {
    }

    /**
     *
     * @author DANIEL DJ
     */
    // Lo primero creamos una variable en la cila vamos a guardar el estado de la conexion.
    private static Connection conexion;

    // creamos una variable para crear una sola instancia.
    private static Conexion instancia;

// creamos las variables para poder conectarnos a la base de datos.
    private static final String URL = "jdbc:mysql://localhost:3306/MyBDcomp4_10";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static Connection conectar() {
        try     {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("JJD");
            
            return conexion;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e);
        }
        return conexion;
    }

// Creamos el mentodo para cerrar la conexion a la base de datos
    public static    void cerrarConexion() throws SQLException {
        try {
            conexion.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            conexion.close();
        } finally {
            conexion.close();
        }

    }
    // Patron singleton

    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;

    }
}
