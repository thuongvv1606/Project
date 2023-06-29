/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Po;

/**
 *
 * @author LanChau
 */
public class PoDao extends BaseDao {

    PreparedStatement ps;
    ResultSet rs;

    public Po getPoById(int id) {

        String sql = ""
                + "SELECT * FROM po "
                + "where po_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Po(rs.getInt("po_id"), 
                        rs.getString("name").trim(), 
                        rs.getString("description").trim(), 
                        new CurriculumDao().
                                getCurriculumByID(rs.getInt("curriculum_id")),
                        rs.getBoolean("is_active"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return null;
    }

      public static void main(String[] args) {
          System.out.println(new PoDao().getPoById(1).toString());
         
    }

    public ArrayList<Po> getAllByCurriculum(int idCurriculum) {
        ArrayList<Po> list = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM po "
                + "where curriculum_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idCurriculum);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Po(rs.getInt("po_id"), rs.getString("name").trim(),
                        rs.getString("description").trim(),
                        new CurriculumDao().getCurriculumByID(rs.getInt("curriculum_id")),
                        rs.getBoolean("is_active")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }
    
    public ArrayList<Po> getAllByCurriculumWithName(int idCurriculum, String name) {
        ArrayList<Po> list = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM po "
                + "where curriculum_id = ? AND name like ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idCurriculum);
            ps.setString(2,"%" +name+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Po(rs.getInt("po_id"), rs.getString("name").trim(),
                        rs.getString("description").trim(),
                        new CurriculumDao().getCurriculumByID(rs.getInt("curriculum_id")),
                        rs.getBoolean("is_active")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public void add(Po po) {

        String sql = ""
                + "INSERT INTO `po`\n"
                + "(`name`,`description`,`curriculum_id`,`is_active`)\n"
                + "VALUES (?, ?, ?, 1);";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, po.getPoName());
            ps.setString(2, po.getPoDescription());
            ps.setInt(3, po.getCurriculumId());
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }

    }

    public void update(Po po) {

        String sql = ""
                + "UPDATE `po`\n"
                + "SET \n"
                + "`name` =  ?, \n"
                + "`description` =  ?, \n"
                + "`curriculum_id` = ?, \n"
                + "`is_active` =  ?\n"
                + "WHERE `po_id` =  ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, po.getPoName());
            ps.setString(2, po.getPoDescription());
            ps.setInt(3, po.getCurriculumId());
            ps.setBoolean(4, po.isIsActive());
            ps.setInt(5, po.getPoId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }

    }

    public void delete(int id) {

        String sql = ""
                + "UPDATE po "
                + "SET \n"
                + "`is_active` =  0\n"
                + "WHERE po_id =  ? ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        } 
    }
    
        
  

    public boolean checkCode(String name, int curriculumId) {

        String sql = ""
                + "SELECT *\n"
                + "FROM `po`\n"
                + "WHERE name = ? AND curriculum_id = ?";
        try {
            connection = getConnection();
            ps.setString(1, name);
            ps.setInt(2, curriculumId);
           rs = ps.executeQuery();
            while (rs.next()) {                
                return  true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return false;
    }
    
    public ArrayList<Po> getAllByCurrent(int idCurrent) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Po> list = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM po where curriculum_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idCurrent);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Po(rs.getInt("po_id"), rs.getString("name"), rs.getString("description"), rs.getInt("curriculum_id")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }
}
