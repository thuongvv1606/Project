/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Constants;

/**
 *
 * @author LanChau
 */
public class BaseDao {
    protected Connection connection;
    
    public static Connection getConnection() throws Exception {
        
        try {
            Class.forName(Constants.DB_CLASSFORNAME);
            
            return DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER_NAME, Constants.DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    public void closeConnection(Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (cs != null && !cs.isClosed()) {
                cs.close();
            }
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
        }

    }
    
    public void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
        }

    }
    
    

public static void main(String[] args) throws Exception {
        Connection c = getConnection();
        if (c == null) {
            System.out.println("something wrong");
        } else {
            System.out.println("ok");
        }
}
}
