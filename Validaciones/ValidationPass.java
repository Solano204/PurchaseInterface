/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import Controler.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ValidationPass implements Validacion {

    private PreparedStatement ps;
    private ResultSet res = null;
    private Connection con;
    private String newPass;

    public ValidationPass() {
        con = Conexion.conectar();
    }

    @Override
    public boolean validation(String user, String pass) {
        return changePassWord(user, pass, newPass);
    }

    // Cambio la contraseña
    public boolean changePassWord(String CvUser, String pass, String newPass) {
        try {
            String sqlQuery = "SELECT Login FROM mUsuario WHERE Login =(?) AND PassWord = (?)";
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, CvUser);
            ps.setString(2, pass);
            res = ps.executeQuery();
            if (res.next()) {
                if (!pass.equals(newPass)) { // Si la pass no es la misma entonces cambio
                    ps.clearBatch();
                    if (!listPass(newPass)) { // No existe la contras entonces cambia la contraseña
                        String updDate = "UPDATE mUsuario SET PassWord = ?  WHERE Login = ? ";
                        PreparedStatement ps2 = con.prepareStatement(updDate);
                        ps2.setString(1, newPass);
                        ps2.setString(2, CvUser);
                        ps2.execute();
                        JOptionPane.showMessageDialog(null, "La contraseña fue cambiada");
                        return true;
                    }
                }
                JOptionPane.showMessageDialog(null, "La contraseña actual es la misma que la nueva,No se realizara ningun cambio");
                return false;
            }
            System.out.println("jks");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Pass Incorrect ");
        }
        return false;
    }

    // Retorno true si la contraseña ya existe
    public boolean listPass(String newPass) {
        try {
            List<String> listPass = new ArrayList<>();
            String sqlQuery = "SELECT PassWord FROM mUsuario ";
            ps = con.prepareStatement(sqlQuery);
            res = ps.executeQuery();
            while (res.next()) {
                listPass.add(res.getString("PassWord"));
            }
            if (listPass.stream().anyMatch(x -> x.equalsIgnoreCase(newPass))) {
                JOptionPane.showMessageDialog(null, "Error la contraseña Ya Existe");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error Lista capu" + e.getMessage());
        }
        return false;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
    
    

}
