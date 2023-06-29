/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Curriculum;
import model.SubjectGroup;

/**
 *
 * @author DungNT
 */
public class SubjectGroupDao extends BaseDao {
    PreparedStatement ps = null;
    ResultSet rs = null;
    public ArrayList<SubjectGroup> getAll(int idCurri) {

        ArrayList<SubjectGroup> list = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM subject_group "
                + "where curriculum_id = ? ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idCurri);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SubjectGroup(rs.getInt("subject_group_id"), new Curriculum(idCurri),
                        rs.getString("group_type"), rs.getString("name")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }
     public ArrayList<SubjectGroup> getAll() {
        ArrayList<SubjectGroup> list = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM subject_group "
                + " ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
          
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SubjectGroup(rs.getInt("subject_group_id"), new Curriculum(0),
                        rs.getString("group_type"), rs.getString("name")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public SubjectGroup getComboByID(int idSubjectGroup) {
        SubjectGroup subjectGroup = new SubjectGroup();
        String sql = ""
                + "SELECT * FROM subject_group "
                + "where curriculum_id = ? ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idSubjectGroup);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new SubjectGroup(rs.getInt("subject_group_id"), new Curriculum(rs.getInt("curriculum_id")),
                        rs.getString("group_type"), rs.getString("name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return null;
    }

    public void add(SubjectGroup subjectGroup) {
        String sql = ""
                + "INSERT INTO `subject_group`\n"
                + "( `curriculum_id`, `group_type`, `name`)\n"
                + "VALUES (?,?,?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, subjectGroup.getCurriculumID());
            ps.setString(2, subjectGroup.getGroup_type());
            ps.setString(3, subjectGroup.getName());
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
           closeConnection(connection, ps, rs);
        }

    }

    public void update(SubjectGroup subjectGroup) {
        String sql = ""
                + "UPDATE `subject_group`\n"
                + "SET  \n"
                + "`group_type` = ?,\n"
                + "`name` = ?\n"
                + "WHERE `subject_group_id` = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, subjectGroup.getGroup_type());
            ps.setString(2, subjectGroup.getName());
            ps.setInt(3, subjectGroup.getSubject_group_id());
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }

    }

    public static void main(String[] args) {
        new SubjectGroupDao().delete(2);
    }

    public void delete(int id) {
       
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        String sql1 = "DELETE FROM `curriculum_subject` WHERE subject_group_id = ?";
        String sql2 = "DELETE FROM `subject_group` WHERE subject_group_id = ?";

        try {
            connection = getConnection();

            // Delete from curriculum_subject table
            ps1 = connection.prepareStatement(sql1);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            // Delete from subject_group table
            ps2 = connection.prepareStatement(sql2);
            ps2.setInt(1, id);
            ps2.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps1, rs);
            closeConnection(connection, ps2, rs);
        }

    }
}
