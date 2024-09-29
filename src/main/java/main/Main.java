/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import dao.CustomerDao;
import utils.DatabaseConnector;
import java.sql.Connection;
import java.sql.SQLException;
import views.LoginView;
import views.RegisterView;

/**
 *
 * @author P51
 */
public class Main {
    public static void main(String[] args) throws SQLException{
        Connection conn = DatabaseConnector.getInstance().getConn();
        System.out.println("Ket noi csdl thanh cong!");
//        CustomerDao c = new CustomerDao();
//        c.deleteById(3);

        try {
            javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            System.out.println("Set up UI Theme successfully!");
        } catch (Exception ex) {
            System.err.println("Set up UI Theme failed!");
        }
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
        RegisterView registerView = new RegisterView();
        registerView.setVisible(true);
        
    }
}
