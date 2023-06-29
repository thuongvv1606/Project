/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Cart;
import model.Customers;
import model.Item;

/**
 *
 * @author hp
 */
public class OrderDAO extends DBContext {
    
    public void addOrder( Cart cart) {

        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        CustomerDAO cdo = new CustomerDAO();
        cdo.addCustomer();
        Customers u = cdo.getCustomer();
        try {
            //add vào bảng Order
            String sql = "INSERT INTO [dbo].[Orders]\n"
                    + "           ([CustomerID]\n"
                    + "           ,[OrderDate])\n"
                    + "     VALUES\n"
                    + "           (?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, u.getCustomerID());
            pstm.setString(2, date);
            pstm.executeUpdate();

            //lấy ra id của ỏder vừa add
            String sql1 = "SELECT TOP(1)\n"
                    + "       [OrderID]\n"
                    + "      ,[CustomerID]\n"
                    + "      ,[OrderDate]\n"
                    + "  FROM [dbo].[Orders]\n"
                    + "  ORDER BY [OrderID] DESC";
            PreparedStatement pstm1 = connection.prepareStatement(sql1);
            ResultSet rs = pstm1.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
            
            for (Item i : cart.getItems()) {
                String sql2 = "INSERT INTO [dbo].[OrderItems]\n"
                        + "           ([OrderID]\n"
                        + "           ,[ProductID]\n"
                        + "           ,[Quantity])\n"
                        + "     VALUES\n"
                        + "           (?, ? ,?)";
                PreparedStatement pstm2 = connection.prepareStatement(sql2);
                pstm2.setInt(1, oid);
                pstm2.setString(2, i.getProduct().getProductID());
                pstm2.setInt(3, i.getQuantity());
                pstm2.executeUpdate();
            }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    public void addOrder(Customers u, Cart cart) {
//
//        LocalDate curDate = java.time.LocalDate.now();
//        String date = curDate.toString();
//        try {
//            //add vào bảng Order
//            String sql = "INSERT INTO [dbo].[Orders]\n"
//                    + "           ([CustomerID]\n"
//                    + "           ,[OrderDate])\n"
//                    + "     VALUES\n"
//                    + "           (?, ?)";
//            PreparedStatement pstm = connection.prepareStatement(sql);
//            pstm.setInt(1, u.getCustomerID());
//            pstm.setString(2, date);
//            pstm.executeUpdate();
//
//            //lấy ra id của ỏder vừa add
//            String sql1 = "SELECT TOP(1)\n"
//                    + "       [OrderID]\n"
//                    + "      ,[CustomerID]\n"
//                    + "      ,[OrderDate]\n"
//                    + "  FROM [dbo].[Orders]\n"
//                    + "  ORDER BY [OrderID] DESC";
//            PreparedStatement pstm1 = connection.prepareStatement(sql1);
//            ResultSet rs = pstm1.executeQuery();
//            if (rs.next()) {
//                int oid = rs.getInt(1);
//            
//            for (Item i : cart.getItems()) {
//                String sql2 = "INSERT INTO [dbo].[OrderItems]\n"
//                        + "           ([OrderID]\n"
//                        + "           ,[ProductID]\n"
//                        + "           ,[Quantity])\n"
//                        + "     VALUES\n"
//                        + "           (?, ? ,?)";
//                PreparedStatement pstm2 = connection.prepareStatement(sql2);
//                pstm2.setInt(1, oid);
//                pstm2.setString(2, i.getProduct().getProductID());
//                pstm2.setInt(3, i.getQuantity());
//                pstm2.executeUpdate();
//            }
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
    public ArrayList<Cart> getOrder (String date) {
        try {
            
        } catch (Exception e) {
        }
        return null;
    }
}
