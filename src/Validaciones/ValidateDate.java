package Validaciones;

import Controler.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

public class ValidateDate implements Validacion {

    PreparedStatement ps;
    ResultSet res = null;
    Connection con;

    public ValidateDate() {
        con = Conexion.conectar();
    }

    @Override
    public boolean validation(String user, String pass) {
        if (getDateIni(user) && getDateFin(user)) {
            System.out.println("All is correct ");
            return true;
        }
        return false;
    }

    // False if it doesnt cumpply with condition
    public boolean getDateIni(String CvUser) {
        try {
            String sqlQuery = "SELECT FecIni,EdoCta FROM mUsuario WHERE Login  =(?)";
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, CvUser);
            res = ps.executeQuery();
            if (res.next()) {
                Date dateIni = res.getDate(1);
                boolean StateAccount = res.getBoolean(2);
                Date currentDate = new Date();
                if (currentDate.before(dateIni)&& !StateAccount) {
                    JOptionPane.showMessageDialog(null, "Cuenta caducada ,/contactate con el administrador ");
                    return false;
                }
                if (currentDate.before(dateIni) && StateAccount) {
                    JOptionPane.showMessageDialog(null, "Cuenta por activar , contactate con el administrador ");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error date inicio");
            e.printStackTrace();
        }
        return false;
    }

    // False if it doesnt cumpply with condition
    public boolean getDateFin(String CvUser) {
        try {
            String sqlQuery = "SELECT FecFin,EdoCta FROM mUsuario WHERE Login =(?)";
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, CvUser);
            res = ps.executeQuery();
            if (res.next()) {
                Date dateFin = res.getDate(1);
                boolean StateAccount = res.getBoolean(2);
                
                Date currentDate = new Date();
                if (currentDate.getDay() == dateFin.getDay() && StateAccount) {
                    return true;
                }
                
                if (  currentDate.after(dateFin) && !StateAccount ) {
                    JOptionPane.showMessageDialog(null, "Cuenta caducada, Ponganse en contacto con el administrador ");
                    return false;
                }
                // I change the state = desactive 
                if (currentDate.after(dateFin) && StateAccount  ) {
                    boolean desactive = true;
                    ps.clearBatch();
                    String updDate = "UPDATE mUsuario SET EdoCta = ?  WHERE Login = ? ";
                    PreparedStatement ps2 = con.prepareStatement(updDate);
                    ps2.setBoolean(1, false);
                    ps2.setString(2, CvUser);
                    ps2.execute();
                    JOptionPane.showMessageDialog(null, "Cuenta ha vencido, Se desactivara el estado de la cuenta a FALSE  ");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error date fin ");
            e.printStackTrace();
        }
        return false;
    }
}
