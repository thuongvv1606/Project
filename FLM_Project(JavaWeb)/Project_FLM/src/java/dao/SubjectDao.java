/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.BaseDao.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Subject;

/**
 *
 * @author DungNT
 */
public class SubjectDao extends BaseDao {

    public ArrayList<Subject> getAllByCurrent(int currentId, String code, String name) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Subject> list = new ArrayList<>();
        String sql = ""
                + "SELECT distinct sub.subject_id, sub.code, sub.name, sub.semester, sub.no_credit , sub.pre_condition\n"
                + "FROM subject sub \n"
                + "JOIN curriculum_subject cuSub ON sub.subject_id = cuSub.subject_id\n"
                + "WHERE cuSub.curriculum_id = ?  AND sub.code LIKE ? AND sub.name LIKE ? ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, currentId);
            ps.setString(2, "%" + code + "%");
            ps.setString(3, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt("subject_id"),
                        rs.getInt("semester"), rs.getInt("no_credit"),
                        rs.getString("name"), rs.getString("code"), rs.getString("pre_condition")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }
    public ArrayList<Subject> getAllListSubject(String code, String name) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Subject> list = new ArrayList<>();
        String sql = ""
                + "SELECT sub.subject_id, sub.code, sub.name, sub.semester, sub.no_credit , sub.pre_condition\n"
                + "FROM subject sub  WHERE sub.code LIKE ? AND sub.name LIKE ? ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            
            ps.setString(1, "%" + code + "%");
            ps.setString(2, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt("subject_id"),
                        rs.getInt("semester"), rs.getInt("no_credit"),
                        rs.getString("name"), rs.getString("code"), rs.getString("pre_condition")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public static void main(String[] args) {
        SubjectDao a = new SubjectDao();
        System.out.println(a.getAllListSubject("", ""));
    }

    public ArrayList<Subject> getAllByCurrent(int currentId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Subject> list = new ArrayList<>();
        String sql = ""
                + "SELECT sub.subject_id, sub.code, sub.name, sub.semester, sub.no_credit , sub.pre_condition\n"
                + "FROM subject sub \n"
                + "JOIN curriculum_subject cuSub ON sub.subject_id = cuSub.subject_id\n"
                + "WHERE cuSub.curriculum_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, currentId);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt("subject_id"),
                        rs.getInt("semester"), rs.getInt("no_credit"),
                        rs.getString("name"), rs.getString("code"), rs.getString("pre_condition")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public Subject getSubjectById(int id) {

        String sql = ""
                + "SELECT sub.subject_id, sub.code, sub.name, sub.semester, sub.no_credit, sub.pre_condition \n"
                + "FROM subject sub  \n"
                + "where sub.subject_id = ? ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Subject(rs.getInt("subject_id"),
                        rs.getInt("semester"), rs.getInt("no_credit"),
                        rs.getString("name"), rs.getString("code"), rs.getString("pre_condition"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return null;
    }

    public ArrayList<Subject> getSubjectByElectiveId(int id) {

        ArrayList<Subject> list = new ArrayList<>();
        String sql = "select subject.* from elective join elective_subject\n"
                + "on elective.elective_id = elective_subject.elective_id\n"
                + "join subject on subject.subject_id = elective_subject.subject_id\n"
                + "where elective_subject.elective_id = ? order by semester;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt("subject_id"),
                        rs.getInt("semester"), rs.getInt("no_credit"),
                        rs.getString("name"), rs.getString("code"), rs.getString("pre_condition")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public ArrayList<Subject> getAllSubject() {
        ArrayList<Subject> list = new ArrayList<>();
        String query = "select * from subject order by semester;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt("subject_id"),
                        rs.getInt("semester"), rs.getInt("no_credit"),
                        rs.getString("name"), rs.getString("code"), rs.getString("pre_condition")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public ArrayList<Subject> getAllElectiveSubject(int curId) {
        ArrayList<Subject> list = new ArrayList<>();
        String query = "select distinct subject.* from subject join curriculum_subject on subject.subject_id = \n"
                + "curriculum_subject.subject_id where curriculum_subject.curriculum_id = ? and type = 'elective' order by semester;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, curId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt("subject_id"),
                        rs.getInt("semester"), rs.getInt("no_credit"),
                        rs.getString("name"), rs.getString("code"), rs.getString("pre_condition")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public ArrayList<Subject> getAllSubject(String code, String name) {
        ArrayList<Subject> list = new ArrayList<>();
        String query = "select * from subject where code = ? and name = ? order by semester;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + code + "%");
            ps.setString(2, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt("subject_id"),
                        rs.getInt("semester"), rs.getInt("no_credit"),
                        rs.getString("name"), rs.getString("code"), rs.getString("pre_condition")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Subject> getSubjectByContentgroup(int id) {

        ArrayList<Subject> list = new ArrayList<>();
        String query = "select subject.* from subject join curriculum_subject on \n"
                + "subject.subject_id = curriculum_subject.subject_id where subject_group_id = ? order by semester;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subject(rs.getInt("subject_id"), rs.getInt("semester"), rs.getInt("no_credit"),
                        rs.getString("name"), rs.getString("code"), rs.getString("pre_condition")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public boolean checkCodeSubject(String code, int idCurriculum) {
        String sql = ""
                + "SELECT * FROM subject s JOIN curriculum_subject cs \n"
                + "ON s.subject_id = cs.subject_id \n"
                + "WHERE s.code = ? AND cs.curriculum_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            ps.setInt(2, idCurriculum);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return false;
    }

    public int add(Subject subject) throws Exception {
        String sql = ""
                + "INSERT INTO subject\n"
                + "( code, name, semester, no_credit, pre_condition)\n"
                + "VALUES\n"
                + "( ?, ?, ?, ? , ?);";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, subject.getCode());
            ps.setString(2, subject.getName());
            ps.setInt(3, subject.getSemester());
            ps.setInt(4, subject.getNo_credit());
            ps.setString(5, subject.getPre_condition());
            ps.executeUpdate();

            // Lấy id bản ghi mới tạo
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            closeConnection(connection, ps, rs);
        }
        return -1;
    }

    public boolean checkCodeSubject(String code) {

        String sql = "SELECT * FROM subject\n"
                + "where code = ? ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return false;
    }

    public void update(Subject subject) {

        String sql = ""
                + "UPDATE `subject`\n"
                + "SET\n"
                + "`code` =  ?,\n"
                + "`name` =  ?,\n"
                + "`semester` =  ?,\n"
                + "`no_credit` =  ?,\n"
                + "`pre_condition` =  ?\n"
                + "WHERE `subject_id` =   ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, subject.getCode());
            ps.setString(2, subject.getName());
            ps.setInt(3, subject.getSemester());
            ps.setInt(4, subject.getNo_credit());
            ps.setString(5, subject.getPre_condition());
            ps.setInt(6, subject.getId());
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }

    }

    public boolean checkSubject(String code) {
        boolean result = false;
        String query = "SELECT `subject`.`subject_id`,\n"
                + "    `subject`.`code`,\n"
                + "    `subject`.`name`,\n"
                + "    `subject`.`semester`,\n"
                + "    `subject`.`no_credit`,\n"
                + "    `subject`.`pre_condition`\n"
                + "FROM `g5_flm`.`subject` WHERE `subject`.`code` LIKE ? OR `subject`.`subject_id` = ?";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, code);
            ps.setString(2, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception ex) {
            System.out.println("Check Subject: " + ex.getMessage());
        } finally {
            closeConnection(connection, ps, rs);
        }
        return result;
    }

    public boolean InsertData(Subject subject) throws Exception {
        String sql = "INSERT INTO `g5_flm`.`subject` (`code`, `name`, `semester`, `no_credit`, `pre_condition`) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, subject.getCode());
            ps.setString(2, subject.getName());
            ps.setInt(3, subject.getSemester());
            ps.setInt(4, subject.getNo_credit());
            ps.setString(5, subject.getPre_condition());
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Subject inserted successfully");
                return true;
            } else {
                System.out.println("Failed to insert subject");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error while inserting subject: " + ex.getMessage());
            return false;
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

}
