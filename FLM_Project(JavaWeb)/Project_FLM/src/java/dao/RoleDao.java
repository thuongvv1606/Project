/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Role;

/**
 *
 * @author HaPi
 */
public class RoleDao extends BaseDao {
    
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Role> getRolesByUserId(int userId) {
        List<Role> listRole = new ArrayList<>();
        String query = "select r.role_id, r.name, r.description from user_role u JOIN role r ON u.role_id = r.role_id"
                + " where u.user_id = ? and u.is_active = true";
        try {
            connection =getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listRole.add(new Role(rs.getInt("role_id"), rs.getString("name"), rs.getString("description")));
            }
        } catch (Exception ex) {
        } finally {
            closeConnection(connection, ps, rs);
        }
        return listRole;
    }

    public void deleteUserRole(int userId) {
        
        String query = ""
                + "DELETE FROM `user_role`\n"
                + "WHERE user_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (Exception ex) {
        } finally {
            closeConnection(connection, ps, rs);
        }

    }

    public void addUserRole(int userId, int roleId) {
        
        String query = ""
                + "INSERT INTO `user_role`\n"
                + "(`role_id`,\n"
                + "`user_id`,\n"
                + "`is_active`)\n"
                + "VALUES\n"
                + "(?, ?,1);";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, roleId);
            ps.setInt(2, userId);
            ps.executeUpdate();

        } catch (Exception ex) {
        } finally {
            closeConnection(connection, ps, rs);
        }

    }

    public List<Role> getAll() {
        
        List<Role> listRole = new ArrayList<>();
        String query = "SELECT * FROM role";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listRole.add(new Role(rs.getInt("role_id"), rs.getString("name"), rs.getString("description")));
            }

        } catch (Exception ex) {
        } finally {
            closeConnection(connection, ps, rs);
        }
        return listRole;

    }
}
