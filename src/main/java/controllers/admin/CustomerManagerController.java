/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin;

import controllers.ManagerController;
import controllers.popup.CustomerPopupController;
import dao.CustomerDao;
import views.popup.CustomerPopupView;
//import models.Customer;

/**
 *
 * @author P51
 */
public class CustomerManagerController extends ManagerController{
    
    CustomerDao customerDao = new CustomerDao();
    CustomerPopupController popupController = new CustomerPopupController();
    CustomerPopupController customerPopupController = new CustomerPopupController();
    
    public CustomerManagerController(){ 
        super();
    }
    
    @Override
    public void actionAdd() {
        customerPopupController.add(new CustomerPopupView(), this::updateData, view::showError);
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionSearch() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionDelete() {
//        int selectedIds[] =
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionEdit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
