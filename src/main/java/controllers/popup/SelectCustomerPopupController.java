/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.CustomerDao;
import dao.Dao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import models.Customer;
import views.popup.SelectCustomerPopupView;
/**
 *
 * @author buiva
 */
public class SelectCustomerPopupController extends SelectEntityPopupController<SelectCustomerPopupView, CustomerDao, Customer>{
//    private CustomerDao customerDao;
//    private JFrame previousView;
//    
    public SelectCustomerPopupController() {
        this.entityDao = new CustomerDao();
    }
//
    public SelectCustomerPopupController(CustomerDao customerDao) {
        this.entityDao = customerDao;
    }
//    
//    
//    public interface Callback {
//        public abstract void run(Customer customer);
//    }
    
    @Override
    public void select(SelectCustomerPopupView view, Callback<Customer> callback) {
        super.select(view, callback);
        view.getBtnOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c = view.getEntityList().getSelectedValue();
                if(c == null) {
                    view.showError("Bạn chưa chọn khách hàng nào!");
                    return;
                }
                callback.run(c);
                view.dispose();
            }
            
        });
        view.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txtSearch = view.getEntityNameTxtField().getText();
                try {
                    view.renderEntity(entityDao.searchByKey("name", txtSearch));          
                }
                catch (Exception exception) {
                    view.showError(exception);
                }
            }
            
        });
    }
}
