/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserAction;

import Controler.Conexion;
import View.usuario02;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author GAMER
 */
public class DeleteUser {

    private Connection con;
    private CallableStatement ps;
    private CallableStatement pro;

    public DeleteUser() {
        Conexion.conectar();
    }

    // Method responsable for register a new user
    public boolean delete(String Login) {
        String sqlProcedimiento = "DELETE FROM mUsuario WHERE Login = ?"; // I prepare my query to the procedure, with all the parameters and even the output value, Dont avoid the {}
        try {
            pro = con.prepareCall(sqlProcedimiento);
            pro.setString(1,Login);
            pro.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
