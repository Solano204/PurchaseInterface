package DAO;

import Controler.Conexion;
import Entities.User;
import UserAction.AddUser;
import UserAction.DeleteUser;
import UserAction.SeekUser;
import UserAction.update;
import java.awt.Toolkit;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ActionUser implements DAO {

    private AddUser addUser;
    private DeleteUser deleteUser;
    private update updateUser;
    private SeekUser seekUsers;
    private User currentUser;
    private ArrayList userList;
    private String currentLogin;

    public ActionUser() {
        addUser = new AddUser();
        deleteUser = new DeleteUser();
        updateUser = new update();
        seekUsers = new SeekUser();
    }

    @Override
    public boolean add() {
        return addUser.register(currentUser);
    }

    @Override
    public boolean seek() {
        seekUsers.setUser(userList);
        return seekUsers.queryInformation();
    }

    @Override
    public boolean delete() {
        return deleteUser.delete(currentLogin);
    }

    @Override
    public boolean update() {
        return updateUser.update(currentUser);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentLogin() {
        return currentLogin;
    }

    public void setCurrentLogin(String currentLogin) {
        this.currentLogin = currentLogin;
    }

    public ArrayList getUserList() {
        return userList;
    }

    public void setUserList(ArrayList userList) {
        this.userList = userList;
    }

}
