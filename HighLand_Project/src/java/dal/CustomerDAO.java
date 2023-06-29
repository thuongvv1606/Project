/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Customers;

/**
 *
 * @author hp
 */
public class CustomerDAO extends DBContext {

    PreparedStatement pstm;
    ResultSet rs;

    public Customers getCustomer(int id) {
        String strSelect = "SELECT [CustomerID]\n"
                + "      ,[CustomerName]\n"
                + "      ,[phone]\n"
                + "      ,[Gender]\n"
                + "      ,[Address]\n"
                + "      ,[Birthdate]\n"
                + "  FROM [dbo].[Customers] "
                + "WHERE [CustomerID] = ?";
        try {
            pstm = connection.prepareStatement(strSelect);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return new Customers(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getDate(6));
            }
        } catch (SQLException e) {
            System.out.println("getCustomer: " + e);
        }
        return null;
    }

    public Customers getCustomer() {
        String strSelect = "SELECT TOP 1 [CustomerID]\n"
                + "  FROM [dbo].[Customers] \n"
                + "  ORDER  BY [CustomerID] DESC";
        try {
            pstm = connection.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return new Customers(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("getCustomer: " + e);
        }
        return null;
    }

    public void addCustomer() {
        try {
            String strSelect = "INSERT INTO [dbo].[Customers]\n"
                    + "           ([CustomerName])\n"
                    + "     VALUES\n"
                    + "           (null)";
            pstm = connection.prepareStatement(strSelect);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addCustomer: " + e);
        }
    }

    public static void main(String[] args) {
        CustomerDAO d = new CustomerDAO();
        d.addCustomer();
        Customers c = d.getCustomer();
        System.out.println(c);
        
        //System.out.println(f);
    }
}
