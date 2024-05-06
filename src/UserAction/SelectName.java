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
public class SelectName {

    private final Connection con;
    private ResultSet res;
    private CallableStatement ps;
    private final ArrayList<String> names;
    private DefaultComboBoxModel cbxModel;
    private usuario02 userView;

    public SelectName(usuario02 userView) {
        this.userView = userView;
        this.con = Conexion.conectar();
        this.names = new ArrayList<>();
    }

    public void fillCombo() {
        try {
            names.clear();
            String sqlQuery;
            sqlQuery = "SELECT DsNombre FROM cNombre";
            ps = (CallableStatement) con.prepareStatement(sqlQuery);
            res = ps.executeQuery();
            while (res.next()) {
                names.add(res.getString("DsNombre")); // I obtain the clave of each Latam
            }
            if (!names.isEmpty()) {
                cbxModel = new DefaultComboBoxModel(names.stream().toArray()); // I sent it in the new model, this will pass its values in the original combo box 
                userView.getCbxNames().setModel(cbxModel);
            }
        } catch (SQLException e) {
            System.out.println("Error Combo ");
            e.printStackTrace();
        }
    }

}
