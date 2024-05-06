package Validaciones;

import Controler.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ValidateState implements Validacion {

    PreparedStatement ps;
    ResultSet res = null;
    Connection con;

    public ValidateState() {
        con = Conexion.conectar();
    }

    @Override
    public boolean validation(String user, String data) {
        if (getStateActive(user)) {
            return true;
        }
        return false;
    }

    public boolean getStateActive(String CvUser) {
        try {
            String sqlQuery = "SELECT EdoCta FROM mUsuario WHERE Login =(?)";
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1,CvUser);
            res = ps.executeQuery();
            if (res.next()) {
                boolean active = res.getBoolean(1);
                if (active) {
                    return true;
                }
            }

            JOptionPane.showMessageDialog(null, " Estado de la cuenta inactiva, No se puede ingresar");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error state active");
        }

        return false;
    }

}
