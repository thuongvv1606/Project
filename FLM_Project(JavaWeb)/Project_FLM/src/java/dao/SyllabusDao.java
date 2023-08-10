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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Decision;
import model.Pre_requisite;
import model.Subject;
import model.Syllabus;
import model.User;

/**
 *
 * @author hp
 */
public class SyllabusDao extends BaseDao {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public SyllabusDao() {
    }

    public static void main(String[] args) throws Exception {
        SyllabusDao dao = new SyllabusDao();
    }

    public Syllabus getPre(String key) {
        ArrayList<Pre_requisite> pre = new ArrayList<>();
        String str1 = "SELECT \n"
                + "    s.subject_id, \n"
                + "    p.pre_requisite_id, \n"
                + "    (SELECT s2.code FROM g5_flm.subject s2 WHERE s2.subject_id = p.pre_requisite_id) AS code\n"
                + "FROM \n"
                + "    g5_flm.subject s\n"
                + "    JOIN g5_flm.pre_requisite p ON p.subject_id = s.subject_id\n"
                + "WHERE 1=1\n";
        if (key != null && !key.equals("")) {
            str1 += " and s.subject_id = ? or s.code like ?";
        }
        try {
            connection = getConnection();
            if (str1 != null) {
                ps = connection.prepareStatement(str1);
                ps.setString(1, key);
                ps.setString(2, key);
                rs = ps.executeQuery();
                while (rs.next()) {
                    pre.add(new Pre_requisite(rs.getInt("subject_id"), rs.getInt("pre_requisite_id"), rs.getString("code")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SyllabusDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(connection, ps, rs);
        }
        String str = "SELECT g5_flm.syllabus.syllabus_id,g5_flm.subject.code,g5_flm.subject.subject_id, g5_flm.subject.name , g5_flm.syllabus.name,g5_flm.decision.decision_id, g5_flm.decision.decision_no,\n"
                + "g5_flm.decision.decision_date From g5_flm.syllabus \n"
                + "join g5_flm.decision on  g5_flm.syllabus.decision_id =  g5_flm.decision.decision_id\n"
                + "join g5_flm.subject on g5_flm.syllabus.subject_id = g5_flm.subject.subject_id WHERE 1=1\n";
        if (key != null && !key.equals("")) {
            str += " and g5_flm.subject.subject_id = ? OR g5_flm.subject.code like ?";
        }
        try {
            connection = getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1, key);
            ps.setString(2, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Syllabus(rs.getInt("syllabus_id"), rs.getString("name"), new Subject(rs.getInt("subject_id"), rs.getString("name"), rs.getString("code")),
                        new Decision(rs.getInt("decision_id"), rs.getString("decision_no"), rs.getDate("decision_date")), pre);
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SyllabusDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(connection, ps, rs);
        }
        return null;
    }

    public Syllabus getPre1(String key) {
        ArrayList<Pre_requisite> pre = new ArrayList<>();
        String str1 = "SELECT \n"
                + "    s.subject_id, \n"
                + "    p.pre_requisite_id, \n"
                + "    (SELECT s2.code FROM g5_flm.subject s2 WHERE s2.subject_id = p.subject_id) AS cod\n"
                + "FROM \n"
                + "    g5_flm.subject s\n"
                + "    JOIN g5_flm.pre_requisite p ON p.subject_id = s.subject_id\n"
                + "WHERE 1=1\n";
        if (key != null && !key.equals("")) {
            str1 += " and p.pre_requisite_id = ? OR  p.pre_requisite_id=(SELECT subject_id FROM g5_flm.subject WHERE code like ?)";
        }
        try {
            connection = getConnection();
            if (str1 != null) {
                ps = connection.prepareStatement(str1);
                ps.setString(1, key);
                ps.setString(2, key);
                rs = ps.executeQuery();
                while (rs.next()) {
                    pre.add(new Pre_requisite(rs.getInt("subject_id"), rs.getInt("pre_requisite_id"), rs.getString("cod")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SyllabusDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(connection, ps, rs);
        }

        String str = "SELECT g5_flm.syllabus.syllabus_id,g5_flm.subject.code,g5_flm.subject.subject_id, g5_flm.subject.name , g5_flm.syllabus.name,g5_flm.decision.decision_id, g5_flm.decision.decision_no,\n"
                + "                g5_flm.decision.decision_date From g5_flm.syllabus\n"
                + "                join g5_flm.decision on  g5_flm.syllabus.decision_id =  g5_flm.decision.decision_id\n"
                + "                join g5_flm.subject on g5_flm.syllabus.subject_id = g5_flm.subject.subject_id\n"
                + "                WHERE 1=1\n";
        if (key != null && !key.equals("")) {
            str += "  and g5_flm.subject.subject_id = ? or g5_flm.subject.code like ?";
        }
        try {
            connection = getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1, key);
            ps.setString(2, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Syllabus(rs.getInt("syllabus_id"), rs.getString("name"), new Subject(rs.getInt("subject_id"), rs.getString("name"), rs.getString("code")),
                        new Decision(rs.getInt("decision_id"), rs.getString("decision_no"), rs.getDate("decision_date")), pre);
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SyllabusDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(connection, ps, rs);
        }

        return null;
    }

    public int getNumberOfSyllabus(boolean check) {
        try {
            String query = "select count(*) from syllabus";
            if (check) {
                query += " where syllabus.is_active = 1";
            }
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getNumberOfSyllabus: " + e.getMessage());
        } finally {
            closeConnection(connection, ps, rs);
        }
        return 0;
    }
    
    public int getNumberOfSyllabusReview() {
        try {
            String query = "select count(*) from syllabus where is_approved = 0";
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getNumberOfSyllabus: " + e.getMessage());
        } finally {
            closeConnection(connection, ps, rs);
        }
        return 0;
    }

    public List<Syllabus> getAllSyllabus(int index, boolean check) {
        List<Syllabus> list = new ArrayList<Syllabus>();
        String query = "select syllabus.*,decision.decision_id, decision_no, decision_date \n"
                + "from syllabus left join decision on syllabus.decision_id = decision.decision_id\n"
                + "limit ?, 8";
        if (check) {
            query = "select syllabus.*,decision.decision_id decision_no, decision_date \n"
                    + "from syllabus left join decision on syllabus.decision_id = decision.decision_id\n"
                    + " where syllabus.is_active = 1 limit ?, 8";
        }
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 8);
            rs = ps.executeQuery();
            while (rs.next()) {
                String date = "";
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-YYYY");
                if (rs.getDate("approved_date") != null) {
                    date = f.format(rs.getDate("approved_date"));
                }
                list.add(new Syllabus(rs.getInt("syllabus_id"), rs.getString("name"), rs.getString("englishname"),
                        rs.getInt("no_credit"), rs.getString("time"), rs.getString("student_task"), rs.getString("tool"), rs.getInt("score"), rs.getBoolean("is_approved"),
                        rs.getString("note"), rs.getInt("min_mark"), date, new SyllabusDao().getPreBySyllabusId(rs.getInt("syllabus_id")), rs.getString("description"),
                        rs.getBoolean("is_active"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        new UserDao().getUser(rs.getInt("designer_id")), new UserDao().getUser(rs.getInt("reviewer_id")), new SubjectDao().getSubjectById(rs.getInt("subject_id")),
                        rs.getString("degree_level")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }
    
    public List<Syllabus> getReviewSyllabus(int index) {
        List<Syllabus> list = new ArrayList<Syllabus>();
        String query = "select syllabus.*,decision.decision_id, decision_no, decision_date \n"
                + "from syllabus left join decision on syllabus.decision_id = decision.decision_id\n"
                + " where syllabus.is_approved = 0 limit ?, 8";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 8);
            rs = ps.executeQuery();
            while (rs.next()) {
                String date = "";
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-YYYY");
                if (rs.getDate("approved_date") != null) {
                    date = f.format(rs.getDate("approved_date"));
                }
                list.add(new Syllabus(rs.getInt("syllabus_id"), rs.getString("name"), rs.getString("englishname"),
                        rs.getInt("no_credit"), rs.getString("time"), rs.getString("student_task"), rs.getString("tool"), rs.getInt("score"), rs.getBoolean("is_approved"),
                        rs.getString("note"), rs.getInt("min_mark"), date, new SyllabusDao().getPreBySyllabusId(rs.getInt("syllabus_id")), rs.getString("description"),
                        rs.getBoolean("is_active"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        new UserDao().getUser(rs.getInt("designer_id")), new UserDao().getUser(rs.getInt("reviewer_id")), new SubjectDao().getSubjectById(rs.getInt("subject_id")),
                        rs.getString("degree_level")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public Syllabus getAllSyllabusById(int id) {
        String query = "select * from syllabus where syllabus_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Syllabus(rs.getInt("syllabus_id"), rs.getString("name"), rs.getString("englishname"),
                        rs.getInt("no_credit"), rs.getString("time"), rs.getString("student_task"), rs.getString("tool"), rs.getInt("score"), rs.getBoolean("is_approved"),
                        rs.getString("note"), rs.getInt("min_mark"), rs.getString("approved_date"), new SyllabusDao().getPreBySyllabusId(rs.getInt("syllabus_id")), rs.getString("description"),
                        rs.getBoolean("is_active"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        new UserDao().getUser(rs.getInt("designer_id")), new UserDao().getUser(rs.getInt("reviewer_id")), new SubjectDao().getSubjectById(rs.getInt("subject_id")),
                        rs.getString("degree_level"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return null;
    }

    public List<User> getAllOwner() {
        List<User> list = new ArrayList<User>();
        String query = "select distinct designer_id from syllabus";
        try {
            UserDao uDao = new UserDao();
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(uDao.getUser(rs.getInt(1)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public List<Syllabus> searchSyllabus(String key, boolean check, String in_charge, String filterByStatus, String filterByApprove) {
        List<Syllabus> sList = new ArrayList<>();
        String query = "select syllabus.*, decision_no, subject.name "
                + "from syllabus left join decision on syllabus.decision_id = decision.decision_id "
                + "join subject on syllabus.subject_id = subject.subject_id "
                + "where (syllabus.name like ? or description like ?"
                + " or decision_no like ? or subject.name like ?)";
        if (check) {
            query += " and syllabus.is_active = 1 and syllabus.is_approved = 1";
        }
        try {
            if (!in_charge.equals("0")) {
                query += " and designer_id = ?";
            }
            if (!filterByStatus.equals("-1")) {
                query += " and syllabus.is_active = ?";
            }
            if (!filterByApprove.equals("-1")) {
                query += " and syllabus.is_approved = ?";
            }
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            ps.setString(3, "%" + key + "%");
            ps.setString(4, "%" + key + "%");
            if (!in_charge.equals("0")) {
                ps.setString(5, in_charge);
            }
            if (!filterByStatus.equals("-1")) {
                if (!in_charge.equals("0")) {
                    ps.setString(6, filterByStatus);
                } else {
                    ps.setString(5, filterByStatus);
                }
            }
            if (!filterByApprove.equals("-1")) {
                if (!in_charge.equals("0") && !filterByStatus.equals("-1")) {
                    ps.setString(7, filterByApprove);
                } else if (!in_charge.equals("0") || !filterByStatus.equals("-1")) {
                    ps.setString(6, filterByApprove);
                } else {
                    ps.setString(5, filterByApprove);
                }
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                sList.add(new Syllabus(rs.getInt("syllabus_id"), rs.getString("name"), rs.getString("englishname"),
                        rs.getInt("no_credit"), rs.getString("time"), rs.getString("student_task"), rs.getString("tool"), rs.getInt("score"), rs.getBoolean("is_approved"),
                        rs.getString("note"), rs.getInt("min_mark"), rs.getString("approved_date"), new SyllabusDao().getPreBySyllabusId(rs.getInt("syllabus_id")), rs.getString("description"),
                        rs.getBoolean("is_active"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        new UserDao().getUser(rs.getInt("designer_id")), new UserDao().getUser(rs.getInt("reviewer_id")), new SubjectDao().getSubjectById(rs.getInt("subject_id")),
                        rs.getString("degree_level")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return sList;
    }

    public List<Subject> getPreBySyllabusId(int id) {
        List<Subject> list = new ArrayList<>();
        String query = "select subject.* from pre_requisite join syllabus on pre_requisite.syllabus_id = syllabus.syllabus_id\n"
                + "join subject on pre_requisite.subject_id = subject.subject_id where syllabus.syllabus_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
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

    public void activateOrDeactiveSyllabus(int curId, int status) {
        String query = "update syllabus set is_active = ? where syllabus_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, status);
            ps.setInt(2, curId);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public boolean updateDecision(int syllabusid, int decisionid) {
        String query = "UPDATE g5_flm.syllabus\n"
                + "SET\n"
                + "decision_id = ?\n"
                + "WHERE syllabus_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, decisionid);
            ps.setInt(2, syllabusid);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public ArrayList<Syllabus> getAllSyllabus() {
        ArrayList<Syllabus> sList = new ArrayList<Syllabus>();
        String query = "select * from syllabus";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                sList.add(new Syllabus(rs.getInt("syllabus_id"), rs.getString("name"), rs.getString("englishname"),
                        rs.getInt("no_credit"), rs.getString("time"), rs.getString("student_task"), rs.getString("tool"), rs.getInt("score"), rs.getBoolean("is_approved"),
                        rs.getString("note"), rs.getInt("min_mark"), rs.getString("approved_date"), new SyllabusDao().getPreBySyllabusId(rs.getInt("syllabus_id")), rs.getString("description"),
                        rs.getBoolean("is_active"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        new UserDao().getUser(rs.getInt("designer_id")), new UserDao().getUser(rs.getInt("reviewer_id")), new SubjectDao().getSubjectById(rs.getInt("subject_id")),
                        rs.getString("degree_level")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return sList;
    }

    public boolean checkDuplicateSyllabus(String name, String id) {
        String query = "select * from syllabus where name = ? and syllabus_id != ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, id);
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

    public int addSyllabus(String name, String engname, String credit, String time, String des, String task, 
            String tool, String score, String note, String mark, String status, String degree, int userId) {
        int generatedId = -1; // Giá trị mặc định nếu không có ID được tạo ra
        String query = "insert into syllabus(name, englishname, no_credit, time, description, student_task,"
                + " tool, score, note, min_mark, is_active, degree_level, designer_id, is_approved, approved_date) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, engname);
            ps.setString(3, credit);
            ps.setString(4, time);
            ps.setString(5, des);
            ps.setString(6, task);
            ps.setString(7, tool);
            ps.setString(8, score);
            ps.setString(9, note);
            ps.setString(10, mark);
            ps.setString(11, status);
            ps.setString(12, degree);
            ps.setInt(13, userId);
            ps.setString(14, "0");
            SimpleDateFormat f = new SimpleDateFormat("YYYY-MM-dd");
            String date = f.format(new java.util.Date());
            ps.setString(15, date);
            ps.executeUpdate();
            // Lấy các khóa sinh tự động được tạo ra
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1); // Lấy ID từ kết quả ResultSet
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return generatedId;
    }

    public void updateSyllabus(String name, String engname, String des, String credit, String time, String task,
            String tool, String score, String note, String mark, String status, String degree, String id) {
        String query = "update syllabus set name = ?, englishname = ?, no_credit = ?, time = ?, student_task = ?, "
                + "tool = ?, score = ?, note = ?, min_mark = ?, description = ?, is_active = ?, degree_level = ? where syllabus_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, engname);
            ps.setString(3, credit);
            ps.setString(4, time);
            ps.setString(5, task);
            ps.setString(6, tool);
            ps.setString(7, score);
            ps.setString(8, note);
            ps.setString(9, mark);
            ps.setString(10, des);
            ps.setString(11, status);
            ps.setString(12, degree);
            ps.setString(13, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public void approveSyllabus(int parseInt, int i, int userId) {
        String query = "update syllabus set is_approved = ?, reviewer_id = ? where syllabus_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, i);
            ps.setInt(2, userId);
            ps.setInt(3, parseInt);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }
}
