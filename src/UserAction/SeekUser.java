/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserAction;

import Controler.Conexion;
import Entities.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author GAMER
 */
public class SeekUser {

    private Connection con;
    private ResultSet res;
    private CallableStatement ps;
    private ArrayList<User> user;

    public SeekUser() {
        con = Conexion.conectar();
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public boolean queryInformation() {
        try {
            user.clear();
            String sqlQuery;
            sqlQuery = "SELECT Login,PassWord,FecIni,FecFin,EdoCta"
                    + "FROM mUsuario as u JOIN mdtPerson as mdt ON  u.Cvperson = mdt.CvPerson \n"
                    + "jOIN cNombre as n ON mdt.CvNombre= n.cvNombre\n";
            ps = (CallableStatement) con.prepareStatement(sqlQuery);
            res = ps.executeQuery();
            while (res.next()) {
                User userT = new User();
                userT.setLogin(res.getString("Login"));
                userT.setPass(res.getString("PassWord"));
                userT.setDateStart(res.getDate("FecIni"));
                userT.setDateEnd(res.getDate("FecFin"));
                userT.setState(res.getBoolean("EdoCta"));
                user.add(userT);
            }
            if (!user.isEmpty()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error " + e.getMessage());
            return false;
        }
    }
}
