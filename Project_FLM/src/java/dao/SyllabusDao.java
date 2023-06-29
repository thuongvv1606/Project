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
        boolean check = dao.updateDecision(1, 1);
        System.out.println(check);
//        ArrayList<Syllabus> sList = (ArrayList<Syllabus>) dao.getAllSyllabus();
//        for (Syllabus syllabus : sList) {
//            System.out.println(syllabus);
//        }
//        Syllabus s = dao.getPre("prj301");
//        System.out.println(s);
//        ArrayList<Syllabus> sList = new ArrayList<>(); // Khởi tạo sList
//        if (s.getPre().size() != 0) {
//            ArrayList<Pre_requisite> p = s.getPre();
//
//            for (Pre_requisite pre_requisite : p) {
//                System.out.println(p);
//                sList.add(dao.getPre(String.valueOf(pre_requisite.getPreId())));
//            }
//        }
//        System.out.println(sList.size());
//        for (Syllabus syllabus : sList) {
//            System.out.println(syllabus);
//        }

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
        String str = "SELECT g5_flm.syllabus.syllabus_id,g5_flm.subject.code,g5_flm.subject.subject_id, g5_flm.subject.name , g5_flm.syllabus.name, g5_flm.decision.decision_no,\n"
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
                        new Decision(rs.getString("decision_no"), rs.getDate("decision_date")), pre);
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

        String str = "SELECT g5_flm.syllabus.syllabus_id,g5_flm.subject.code,g5_flm.subject.subject_id, g5_flm.subject.name , g5_flm.syllabus.name, g5_flm.decision.decision_no,\n"
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
                        new Decision(rs.getString("decision_no"), rs.getDate("decision_date")), pre);
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

    public  ArrayList<Syllabus> getAllSyllabus() {
        ArrayList<Syllabus> sList = new ArrayList<Syllabus>();
        String query = "select * from syllabus";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                sList.add(new Syllabus(rs.getInt("syllabus_id"), rs.getString("name"), rs.getString("description"),
                        rs.getBoolean("is_active"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        new UserDao().getUser(rs.getInt("designer_id")), new SubjectDao().getSubjectById(rs.getInt("subject_id"))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return sList;
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
        }
        return 0;
    }

    public List<Syllabus> getAllSyllabus(int index, boolean check) {
        List<Syllabus> list = new ArrayList<Syllabus>();
        String query = "select syllabus.*, decision_no, decision_date \n"
                + "from syllabus left join decision on syllabus.decision_id = decision.decision_id\n"
                + "limit ?, 8";
        if (check) {
            query = "select syllabus.*, decision_no, decision_date \n"
                    + "from syllabus left join decision on syllabus.decision_id = decision.decision_id\n"
                    + " where syllabus.is_active = 1 limit ?, 8";
        }
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 8);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Syllabus(rs.getInt("syllabus_id"), rs.getString("name"), rs.getString("description"),
                        rs.getBoolean("is_active"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        new UserDao().getUser(rs.getInt("designer_id")), new SubjectDao().getSubjectById(rs.getInt("subject_id"))));
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
                return new Syllabus(rs.getInt("syllabus_id"), rs.getString("name"), rs.getString("description"),
                        rs.getBoolean("is_active"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        new UserDao().getUser(rs.getInt("designer_id")), new SubjectDao().getSubjectById(rs.getInt("subject_id")));
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

    public List<Syllabus> searchSyllabus(String key, boolean check) {
        List<Syllabus> sList = new ArrayList<Syllabus>();
        String query = "select * from syllabus where name like ? or description like ? or syllabus_id = ?";
        if (check) {
            query += " and is_active = 1";
        }
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            ps.setString(3, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                sList.add(new Syllabus(rs.getInt("syllabus_id"), rs.getString("name"), rs.getString("description"),
                        rs.getBoolean("is_active"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        new UserDao().getUser(rs.getInt("designer_id")), new SubjectDao().getSubjectById(rs.getInt("subject_id"))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return sList;
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

    public List<Syllabus> filterSyllabus(String in_charge, String filterByStatus) {
        List<Syllabus> sList = new ArrayList<Syllabus>();
        String query = "select * from syllabus where";
        try {
            if (!in_charge.equals("0")) {
                query += " designer_id = ?";
            }
            if (!filterByStatus.equals("-1")) {
                if (!in_charge.equals("0")) {
                    query += " and is_active = ?";
                } else {
                    query += " is_active = ?";
                }
            }
            connection = getConnection();
            ps = connection.prepareStatement(query);
            if (!in_charge.equals("0")) {
                ps.setString(1, in_charge);
            }
            if (!filterByStatus.equals("-1")) {
                if (!in_charge.equals("0")) {
                    ps.setString(2, filterByStatus);
                } else {
                    ps.setString(1, filterByStatus);
                }
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                sList.add(new Syllabus(rs.getInt("syllabus_id"), rs.getString("name"), rs.getString("description"),
                        rs.getBoolean("is_active"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        new UserDao().getUser(rs.getInt("designer_id")), new SubjectDao().getSubjectById(rs.getInt("subject_id"))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return sList;
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
}
