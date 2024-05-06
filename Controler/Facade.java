/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import Validaciones.Validacion;
import Validaciones.ValidationGeneral;
import View.Consulta;
import View.Menu;
import View.ProyectoInterfaz;

/**
 *
 * @author Carlos Josue
 */
public class Facade {

    private ProyectoInterfaz viewLogin;
    private Validacion validation;
    private Menu succesfulLogin;

    public Facade(ProyectoInterfaz viewLogin) {
        succesfulLogin = new Menu();
        succesfulLogin.setFacade(this);
        this.viewLogin = viewLogin;
        validation = new ValidationGeneral();

    }

    public void validateData() {
        String user = viewLogin.getTxt_login().getText();
        String pass = viewLogin.getTxt_password().getText();
        if (validation.validation(user, pass)) {
            if (succesfulLogin != null) {
                this.viewLogin.setVisible(false);
                succesfulLogin.setLocationRelativeTo(null);
                succesfulLogin.setLoginBuscar(user);
                System.out.println("u" + user);
                viewLogin.getTxt_login().setText("");
                succesfulLogin.setVisible(true);
                succesfulLogin.searchUser();
                viewLogin.getTxt_password().setText("");
            }
            return;
        }
        viewLogin.getTxt_login().setText("");
        viewLogin.getTxt_password().setText("");

    }

    public void openLogin() {
        viewLogin.setVisible(true);
    }

}
