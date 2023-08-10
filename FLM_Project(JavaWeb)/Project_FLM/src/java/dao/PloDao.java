/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.BaseDao.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Plo;



/**
 *
 * @author DungNT
 */
public class PloDao extends BaseDao {

    PreparedStatement ps;
    ResultSet rs;
    

    public PloDao() {

    }

    public ArrayList<Plo> getAllByCurrent(int currentId) {

        ArrayList<Plo> list = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM plo "
                + "where curriculum_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, currentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Plo(rs.getInt("plo_id"), rs.getString("name").trim(), rs.getString("description").trim(), currentId, rs.getBoolean("is_active")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }
    
     public boolean checkDupicateNamePLO(String name, int id) {
        boolean check = false;
        String query = "SELECT * FROM plo where name = ? and curriculum_id = ?";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            if (rs.next()) {
            // Nếu tìm thấy ít nhất một bản ghi có giá trị name trùng khớp
            check = true;
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return check;
    }
    
    
     public ArrayList<Plo> getAllPloById(int id, String name, int index) {
        ArrayList<Plo> list = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM plo \n"
                + "where curriculum_id = ? AND name LIKE ? "
                + "limit ?,5 ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, "%" + name + "%");
            ps.setInt(3, 5 * (index - 1));
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Plo(rs.getInt("plo_id"), rs.getString("name"), rs.getString("description"), rs.getInt("curriculum_id")));
            }
        } catch (Exception ex) {
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public boolean addNewPlo(String name, String description, int curriculum_id, int active) {
        boolean check = false;
        String query = "INSERT INTO plo (name, description, curriculum_id, is_active)\n"
                + "                VALUES (?, ?, ?, ?);";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, curriculum_id);
            ps.setInt(4, active);
            ps.executeUpdate();
            check = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return check;
    }

    public void deletePloById(int id) {
        String query = "DELETE FROM plo\n"
                + "WHERE plo_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public Plo getPloByID(int id) {
        Plo plo = null;
        String query = "SELECT * FROM plo  where plo_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                plo = new Plo();
                plo.setId(rs.getInt("plo_id"));
                plo.setName(rs.getString("name"));
                plo.setDescription(rs.getString("description"));
                plo.setCurriculum_id(rs.getInt("curriculum_id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return plo;
    }

    public boolean updatePloById(String ploName, String ploDescription, int curriculum_id, int id) {
        boolean f = false;
        String query = "UPDATE plo SET name = ?, description = ?, "
                + "curriculum_id = ? WHERE plo_id = ?;";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, ploName);
            ps.setString(2, ploDescription);
            ps.setInt(3, curriculum_id);
            ps.setInt(4, id);
            ps.executeUpdate();
            f = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return f;
    }

    public int getSizeList() {
        String sql = ""
                + "SELECT count(*) as count FROM plo";
        try {
            Plo plo = null;
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return 0;
    }
    
   
}
