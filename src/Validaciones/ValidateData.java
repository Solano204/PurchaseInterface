package Validaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Controler.Conexion;

public class ValidateData implements Validacion {

    PreparedStatement ps;
    ResultSet res = null;
    Connection con;

    public ValidateData() {
        con = Conexion.conectar();
    }
    
    
    @Override
    public boolean validation(String user, String pass) {
        if(getUser(user) && getPass(user, pass)) { // If it meet the conditions
            return true;
        }
        return false;
    }

    public boolean getPass(String CvUser, String Pass) {
        try {

            String sqlQuery = "SELECT PassWord FROM mUsuario WHERE Login =(?)";
            ps = con.prepareStatement(sqlQuery);
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, CvUser);
            res = ps.executeQuery();
            if (res.next()) {
                String passExt = res.getString(1);
                if (Pass.equalsIgnoreCase(passExt)) {
                    return true;
                }
            }
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error password Incorrect");
        }

        return false;
    }

    public boolean getUser(String CvUser) {
        try {
            System.out.println(CvUser);
            String sqlQuery = "SELECT Login FROM mUsuario WHERE Login =(?)";
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, CvUser);
            res = ps.executeQuery();
            if (res.next()) {
                String user = res.getString(1);
                System.out.println(" j" + CvUser + user);
                if (CvUser.equalsIgnoreCase(user)) {
                    return true;
                }
            }
            JOptionPane.showMessageDialog(null, "El usuario es incorrecto");

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error user Incorrect ");
        }

        return false;
    }
}
