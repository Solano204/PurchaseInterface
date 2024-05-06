/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserAction;

import Controler.Conexion;
import Entities.User;
import View.usuario02;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Connection;

public class update {

    private CallableStatement pro;
    private Connection con;

    public update() {
        con = (Connection) Conexion.conectar();
    }

    // Method responsable for register a new user
    public boolean update(User user) {
        String dni = user.getCvUser();
        final String pass = user.getPass();
        String name = user.getName();
        String login = user.getLogin();
        boolean state = user.isState();
        Date dateStart = user.getDateStart();
        Date dateEnd = user.getDateEnd();

        String sqlProcedimiento = "Update mUsuario SET Login = ?,PassWord = ? ,FechaIni = ?,FechaFin = ?,EdoCta= ?  WHERE Login = ?"; // I prepare my query to the procedure, with all the parameters and even the output value, Dont avoid the {}
        try {
            pro = con.prepareCall(sqlProcedimiento);
            pro.setString(1, login);
            pro.setString(2, pass);
            pro.setDate(3, dateStart);
            pro.setDate(4, dateEnd);
            pro.setBoolean(5, state);
            pro.setString(6, login);
            pro.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
