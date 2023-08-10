/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.BaseDao.getConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Combo;
import model.ContentGroup;
import model.Decision;
import model.Subject;
import model.SubjectGroup;

/**
 *
 * @author hp
 */
public class ComboDao extends BaseDao {

    PreparedStatement pstm;
    ResultSet rs;

    public static void main(String[] args) {
        ComboDao dao = new ComboDao();
        List<Combo> list = dao.getComboListByCurriculumId(1);
        for (Combo combo : list) {
            System.out.println(combo);
        }
        System.out.println(dao.getComboListSize(1, "21", 1).size());
        System.out.println(dao.getCombo(70));
        dao.deleteSubjectCombo(1, 1);
        dao.updateCombo(new Combo(45, 1, "", "dfdggfdfgf", "dfndkfdk"));
        System.out.println(dao.addCombo(2, "", "", ""));
    }

    public Combo getCombo(int id) {
        String query = "select * from subject_group WHERE subject_group_id = ? ";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return new Combo(rs.getInt("subject_group_id"), rs.getInt("curriculum_id"),
                        new CurriculumDao().getCurriculumByID(rs.getInt("subject_group_id")),
                        rs.getString("group_type"), rs.getString("name"), rs.getString("english_name"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, pstm, rs);
        }
        return null;
    }

    public List<Combo> getComboAllList() {
        List<Combo> list = new ArrayList<>();
        String query = "select * from subject_group WHERE group_type LIKE 'combo' ";

        try {
            connection = getConnection();
            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Combo(rs.getInt("subject_group_id"), rs.getInt("curriculum_id"),
                        new CurriculumDao().getCurriculumByID(rs.getInt("subject_group_id")),
                        rs.getString("group_type"), rs.getString("name"), rs.getString("english_name")));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, pstm, rs);
        }
        return list;
    }

    public List<Combo> getComboListByCurriculumId(int id) {
        List<Combo> list = new ArrayList<>();
        String query = "select * from subject_group WHERE curriculum_id = ? AND group_type LIKE 'combo'";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Combo(rs.getInt("subject_group_id"), rs.getInt("curriculum_id"),
                        new CurriculumDao().getCurriculumByID(rs.getInt("subject_group_id")),
                        rs.getString("group_type"), rs.getString("name"), rs.getString("english_name")));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, pstm, rs);
        }
        return list;
    }

    public List<Combo> getComboListSize(int id, String key, int index) {
        List<Combo> list = new ArrayList<>();
        String query = "select * from subject_group WHERE curriculum_id = ? AND group_type LIKE 'combo'";
        if (key != null && !key.isEmpty()) {
            query += "AND( subject_group_id = ? OR name LIKE ?)";
        }
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(query);

            if (key != null && !key.isEmpty()) {
                pstm.setInt(1, id);
                pstm.setString(2, key);
                pstm.setString(3, "%" + key + "%");
            } else {
                pstm.setInt(1, id);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Combo(rs.getInt("subject_group_id"), rs.getInt("curriculum_id"),
                        new CurriculumDao().getCurriculumByID(rs.getInt("subject_group_id")),
                        rs.getString("group_type"), rs.getString("name"), rs.getString("english_name")));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, pstm, rs);
        }
        return list;
    }

    public List<Combo> getComboList(int id, String key, int index) {
        List<Combo> list = new ArrayList<>();
        String query = "select * from subject_group WHERE curriculum_id = ? AND group_type LIKE 'combo'";
        if (key != null && !key.isEmpty()) {
            query += "AND( subject_group_id = ? OR name LIKE ?)";
        }
        query += " LIMIT ?,5";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(query);

            if (key != null && !key.isEmpty()) {
                pstm.setInt(1, id);
                pstm.setString(2, key);
                pstm.setString(3, "%" + key + "%");
                pstm.setInt(4, (index - 1) * 5);
            } else {
                pstm.setInt(1, id);
                pstm.setInt(2, (index - 1) * 5);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Combo(rs.getInt("subject_group_id"), rs.getInt("curriculum_id"),
                        new CurriculumDao().getCurriculumByID(rs.getInt("subject_group_id")),
                        rs.getString("group_type"), rs.getString("name"), rs.getString("english_name")));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, pstm, rs);
        }
        return list;
    }

    public boolean addCombo(int curriculumId, String groupType, String name, String englishName) {
        String sql = "INSERT INTO `g5_flm`.`subject_group` (`curriculum_id`, `group_type`, `english_name`, `name`) VALUES (?, ?, ?, ?);";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, curriculumId);
            pstm.setString(2, "combo");
            pstm.setString(3, englishName);
            pstm.setString(4, name);
            pstm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, pstm, rs);
        }
        return false;
    }

    public void deleteCombo(int id) {

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

    public ArrayList<Subject> getSubjectByCombo(int currentId, int comboId) {
        ArrayList<Subject> list = new ArrayList<>();
        String sql = "SELECT sub.subject_id, sub.code, sub.name, sub.semester, sub.no_credit , sub.pre_condition\n"
                + "FROM subject sub \n"
                + "JOIN curriculum_subject cuSub ON sub.subject_id = cuSub.subject_id\n"
                + "WHERE cuSub.curriculum_id = ? AND subject_group_id = ?";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, currentId);
            pstm.setInt(2, comboId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt("subject_id"),
                        rs.getInt("semester"), rs.getInt("no_credit"),
                        rs.getString("name"), rs.getString("code"), rs.getString("pre_condition")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, pstm, rs);
        }
        return list;
    }

    public void deleteSubjectCombo(int comboId, int subjectId) {

        String sql1 = "DELETE FROM `g5_flm`.`curriculum_subject`\n"
                + "WHERE subject_group_id = ? AND subject_id = ?";

        try {
            connection = getConnection();
            // Delete from curriculum_subject table
            pstm = connection.prepareStatement(sql1);
            pstm.setInt(1, comboId);
            pstm.setInt(2, subjectId);
            pstm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, pstm, rs);
        }

    }

    public boolean updateCombo(Combo combo) {
        String sql = "UPDATE `subject_group`\n"
                + "SET  \n"
                + "`group_type` = ?,\n"
                + "`name` = ?,\n"
                + "`english_name` = ?\n"
                + "WHERE `subject_group_id` = ?";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, "combo");
            pstm.setString(2, combo.getName());
            pstm.setString(3, combo.getEnglishName());
            pstm.setInt(4, combo.getComboId());
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, pstm, rs);
        }
        return false;
    }

}
