package Validaciones;

import javax.swing.JOptionPane;

public class ValidationGeneral implements Validacion {

    Validacion validateUserPass;
    Validacion validateState;
    Validacion validateDate;

    public ValidationGeneral() {
        validateUserPass = new ValidateData();
        validateDate = new ValidateDate();
        validateState = new ValidateState();
    }

    @Override
    public boolean validation(String user, String pass) {
        if (!validateUserPass.validation(user, pass)) { // It Doesnt meet with the condition the user or pass are incorrect
            return false;
        }
        if (!validateState.validation(user, pass)) {
            return false;
        }
        if (!validateDate.validation(user, pass)) {
            return false;
        }
        
        return true;
    }
    
    
    

}
