
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Order;
import utils.OrderStatus;

/**
 *
 * @author MSI
 */
public class OrderDao extends Dao<Order> {

    EmployeeDao employeeDao = new EmployeeDao();
    TableDao tableDao = new TableDao();
    CustomerDao customerDao = new CustomerDao();

    @Override
    public ArrayList<Order> getAll() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order` ORDER BY `order`.`orderDate` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            if (order == null) {
                continue;
            }
            order.setEmployee(employeeDao.getById(order.getEmployeeId()));
//            System.out.println(order);
//            System.out.println(order.getTable());
            order.setTable(tableDao.getById(order.getTableId()));
            order.setCustomer(customerDao.getById(order.getCustomerId()));
            orders.add(order);
        }
        return orders;
    }

    public ArrayList<Order> getAll(int EmployeeId) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order`  WHERE `employeeId`= '" + EmployeeId + "' ORDER BY `order`.`orderDate` DESC";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            order.setEmployee(employeeDao.getById(order.getEmployeeId()));
            order.setTable(tableDao.getById(order.getTableId()));
            order.setCustomer(customerDao.getById(order.getCustomerId()));
            orders.add(order);
        }
        return orders;
    }


    @Override
    public void save(Order t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order rỗng");
        }
        String query = "INSERT INTO `order` (`employeeId`, `tableId`, `customerId`, `type`, `status`, `orderDate`, `payDate`, `paidAmount`, `totalAmount`, `discount`) VALUES (?, ?, ?, ?, ?, current_timestamp(), NULL, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getEmployeeId());
        stmt.setInt(2, t.getTableId());
        stmt.setInt(3, t.getCustomerId());
        stmt.setNString(4, t.getType().getId());
        stmt.setNString(5, t.getStatus().getId());
        stmt.setInt(6, t.getPaidAmount());
        stmt.setInt(7, t.getTotalAmount());
        stmt.setInt(8, t.getDiscount());
        int row = stmt.executeUpdate();
    }

    @Override
    public void update(Order t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order rỗng");
        }
        String query = "UPDATE `order` SET `employeeId` = ?, `tableId` = ?, `customerId` = ?, `type` = ?, `status` = ?, `orderDate` = ?, `payDate` = ?, `paidAmount` = ?, `totalAmount` = ?, `discount` = ? WHERE `order`.`orderId` = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getEmployeeId());
        stmt.setInt(2, t.getTableId());
        stmt.setInt(3, t.getCustomerId());
        stmt.setNString(4, t.getType().getId());
        stmt.setNString(5, t.getStatus().getId());
        stmt.setTimestamp(6, t.getOrderDate());
        stmt.setTimestamp(7, t.getPayDate());
        stmt.setInt(8, t.getPaidAmount());
        stmt.setInt(9, t.getTotalAmount());
        stmt.setInt(10, t.getDiscount());
        stmt.setInt(11, t.getOrderId());
        int row = stmt.executeUpdate();
    }

    @Override
    public void delete(Order t) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `order` WHERE `order`.`orderId` = ?");
        stmt.setInt(1, t.getOrderId());
        stmt.executeUpdate();
    }

    @Override
    public void deleteById(int OrderId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `order` WHERE `order`.`orderId` = ?");
        stmt.setInt(1, OrderId);
        stmt.executeUpdate();
    }

    public void deleteItems(int OrderId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM `order_item` WHERE `orderId` = ?");
        stmt.setInt(1, OrderId);
        stmt.executeUpdate();
    }

    public ArrayList<Order> searchByKey(String key, String word) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order` WHERE " + key + " LIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            order.setEmployee(employeeDao.getById(order.getEmployeeId()));
            order.setTable(tableDao.getById(order.getTableId()));
            order.setCustomer(customerDao.getById(order.getCustomerId()));
            orders.add(order);
        }
        return orders;
    }

    public void create(Order t) throws SQLException {
        if (t == null) {
            throw new SQLException("Order rỗng");
        }
        String query = "INSERT INTO `order` (`employeeId`, `tableId`, `customerId`, `type`, `status`, `orderDate`, `payDate`, `paidAmount`, `totalAmount`, `discount`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getEmployeeId());
        stmt.setInt(2, t.getTableId());
        stmt.setInt(3, t.getCustomerId());
        stmt.setNString(4, t.getType().getId());
        stmt.setNString(5, t.getStatus().getId());
        stmt.setTimestamp(6, t.getOrderDate());
        stmt.setTimestamp(7, t.getPayDate());
        stmt.setInt(8, t.getPaidAmount());
        stmt.setInt(9, t.getTotalAmount());
        stmt.setInt(10, t.getDiscount());
        int row = stmt.executeUpdate();
    }

    public ArrayList<Order> searchByKey(int EmployeeId, String key, String word) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM `order` WHERE " + key + " LIKE ? AND employeeId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setNString(1, String.format("%s%s%s", "%", word, "%"));
        statement.setInt(2, EmployeeId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            order.setEmployee(employeeDao.getById(order.getEmployeeId()));
            order.setTable(tableDao.getById(order.getTableId()));
            order.setCustomer(customerDao.getById(order.getCustomerId()));
            orders.add(order);
        }
        return orders;
    }

    public Order getRandom() throws SQLException {
        String query = "SELECT * FROM `order` WHERE status = ? ORDER BY RAND() LIMIT 1";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setNString(1, OrderStatus.UNPAID.getId());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            order.setEmployee(employeeDao.getById(order.getEmployeeId()));
            order.setTable(tableDao.getById(order.getTableId()));
            order.setCustomer(customerDao.getById(order.getCustomerId()));
            return order;
        }
        return null;
    }

    @Override
    public Order getById(int id) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM `order` WHERE `orderId` = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            Order order = Order.getFromResultSet(rs);
            order.setEmployee(employeeDao.getById(order.getEmployeeId()));
            order.setTable(tableDao.getById(order.getTableId()));
            order.setCustomer(customerDao.getById(order.getCustomerId()));
            return order;
        }
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}