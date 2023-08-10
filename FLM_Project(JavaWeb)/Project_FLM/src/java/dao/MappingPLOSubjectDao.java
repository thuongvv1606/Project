/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.BaseDao.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.MappingPLOSubject;

/**
 *
 * @author DungNT
 */
public class MappingPLOSubjectDao extends BaseDao {

    PreparedStatement ps;
    ResultSet rs;

    public MappingPLOSubjectDao() {

    }

    public static void main(String[] args) {
        System.out.println(new MappingPLOSubjectDao().getAll(1).size());
    }

    public ArrayList<MappingPLOSubject> getAll(int idCurriculum) {

        ArrayList<MappingPLOSubject> list = new ArrayList<>();
        String query = "SELECT cs.subject_id, ps.plo_id \n"
                + "FROM plo_subject ps JOIN curriculum_subject cs \n"
                + "ON ps.subject_id = cs.subject_id\n"
                + "WHERE cs.curriculum_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, idCurriculum);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new MappingPLOSubject(rs.getInt("plo_id"), rs.getInt("cs.subject_id")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public boolean check(int pid, int sid) {
        String query = "SELECT * FROM plo_subject\n"
                + "WHERE plo_id = ? AND subject_id =? ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setInt(2, sid);
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

    public void delete(int pid, int sid) {
        String query = "DELETE FROM plo_subject\n"
                + "WHERE  plo_id = ? AND subject_id =?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setInt(2, sid);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public void add(int pid, int sid) {
        String query = " INSERT INTO plo_subject\n"
                + "(`plo_id`, `subject_id`)\n"
                + "VALUES (?, ?); ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setInt(2, sid);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }
}
