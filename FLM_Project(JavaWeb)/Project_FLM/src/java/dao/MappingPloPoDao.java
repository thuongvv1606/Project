/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.MappingPloPo;

/**
 *
 * @author trinh
 */
public class MappingPloPoDao extends BaseDao{
    
    public static void main(String[] args) {
        MappingPLOSubjectDao a = new MappingPLOSubjectDao();
        System.out.println(a.getAll(1));
    }
     PreparedStatement ps;
    ResultSet rs;

    public ArrayList<MappingPloPo> getAll(int idCurrent) {
        ArrayList<MappingPloPo> list = new ArrayList<>();
        String query = "SELECT p.po_id, po.plo_id\n" +
"                FROM plo po \n" +
"                JOIN po_plo pl ON po.plo_id = pl.plo_id\n" +
"                Join po p On pl.po_id = p.po_id\n" +
"                WHERE po.curriculum_id = ? and p.curriculum_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, idCurrent);
            ps.setInt(2, idCurrent);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new MappingPloPo(rs.getInt("po_id"), rs.getInt("plo_id")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;}

    public boolean check(int id, int poId) {
        String query = "SELECT * FROM po_plo\n"
                + "WHERE plo_id = ? AND po_id =? ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, poId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return false;
    }

    public void delete(int id, int poId) {
    String query = "DELETE FROM po_plo\n"
                + "WHERE plo_id = ? AND po_id =? ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, poId);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }    
    }

    public void add(int id, int poId) {
        String query = " INSERT INTO po_plo\n"
                + "(`plo_id`, `po_id`)\n"
                + "VALUES (?, ?); ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, poId);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }
    

    
    
}
