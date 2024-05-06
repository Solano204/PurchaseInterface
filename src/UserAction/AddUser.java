/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserAction;

import Controler.Conexion;
import Entities.User;
import java.awt.Toolkit;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Date;

/**
 *
 * @author GAMER
 */
public class AddUser {

    private Connection con;
    private PreparedStatement ps;
    private CallableStatement pro;

    public AddUser() {
        con = Conexion.conectar();
    }

    // Method responsable for register a new user
    public boolean register(User user) {
        String dni = user.getCvUser();
        final String pass = user.getPass();
        String name = user.getName();
        String login = user.getLogin();
        boolean state = user.isState();
        Date dateStart = user.getDateStart();
        Date dateEnd = user.getDateEnd();

        String sqlProcedimiento = "INSERT INTO mUsuario(Login,PassWord,Fec,FecFin,EdoCta) VALUES (?, ?, ?,?,?,?)"; // I prepare my query to the procedure, with all the parameters and even the output value, Dont avoid the {}
        try {
            pro = con.prepareCall(sqlProcedimiento);
            pro.setString(1, login);
            pro.setString(2, pass);
            pro.setDate(3, dateStart);
            pro.setDate(4, dateEnd);
            pro.setBoolean(5,state);
            pro.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // 
}
