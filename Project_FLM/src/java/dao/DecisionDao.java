/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.BaseDao.getConnection;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Curriculum;
import model.Decision;
import model.Syllabus;
import model.User;

/**
 *
 * @author TT
 */
public class DecisionDao extends BaseDao {

    PreparedStatement pstm = null;
    ResultSet rs = null;
    public static void main(String[] args) throws Exception {
        DecisionDao decisionDao = new DecisionDao();
        ArrayList<Decision> d = new ArrayList<>();
        String a = "";
        d = decisionDao.getDecisionList(a, 0);
        for (Decision decision : d) {
            System.out.println(decision);
        }
        System.out.println(decisionDao.getNumberOfDecision());
    }
    public Decision getDecisionById(int id) {
        try {
            String query = "select * from decision where decision_id = ?";
            connection = getConnection();
            pstm = connection.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return new Decision(rs.getInt("decision_id"), rs.getString("decision_no"), rs.getDate("decision_date"),
                        new UserDao().getUser(rs.getInt("creator_id")), rs.getBoolean("is_active"), rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("getDecisionById: " + e.getMessage());
        }
        return null;
    }

    public boolean checkNo(String no) {
        String sql = "SELECT * FROM decision "
                + "where decision_no = ?";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, no);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, pstm, rs);
        }
        return false;
    }

    public boolean addDecision(Decision decision) {
        String sql = "INSERT INTO g5_flm.decision\n"
                + "(decision_no,\n"
                + "decision_date,\n"
                + "creator_id,"
                + "is_active,\n"
                + "name)\n"
                + "VALUES\n"
                + "(?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, decision.getDecision_no());
            pstm.setDate(2, decision.getDecision_date());
            pstm.setInt(3, decision.getCreator_id());
            pstm.setBoolean(4, decision.isIs_active());
            pstm.setString(5, decision.getDecision_name());
            pstm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, pstm, rs);
        }
        return false;
    }

    public ArrayList<Decision> getDecisionList(String inputSearch, int index) throws SQLException, Exception {
        ArrayList<Decision> decisionList = new ArrayList<>();
        String sql = "SELECT * FROM g5_flm.decision";
        if (inputSearch != null && !inputSearch.isEmpty()) {
            sql += " WHERE decision_no LIKE ? OR decision_id = ? ";
        }
        sql += " LIMIT ?,5";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            if (inputSearch != null && !inputSearch.isEmpty()) {
                pstm.setString(1, "%" + inputSearch + "%");
                pstm.setString(2, inputSearch);
                pstm.setInt(3, (index - 1) * 5);
            }else{
                pstm.setInt(1, (index - 1) * 5);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                int decisionID = rs.getInt("decision_id");
                String decisionNo = rs.getString("decision_no");
                String decisionName = rs.getString("name");
                Date decisionDate = rs.getDate("decision_date");
                int creatorID = rs.getInt("creator_id");
                Boolean active = rs.getBoolean("is_active");
                UserDao userDao = new UserDao();
                decisionList.add(new Decision(decisionID, decisionNo, decisionName, decisionDate, creatorID, userDao.getUser(creatorID), active));
            }

        } catch (SQLException ex) {
            System.out.println("getDecision: " + ex.getMessage());
        } finally {
            closeConnection(connection, pstm, rs);
        }
        return decisionList;
    }

    public ArrayList<Decision> getDecisionByStatus(String inputSearch, int index) throws SQLException, Exception {
        ArrayList<Decision> decisionList = new ArrayList<>();
        String sql = "SELECT * FROM g5_flm.decision WHERE is_active = ?"
                + " LIMIT ?,5";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, inputSearch);
            pstm.setInt(2, (index - 1) * 5);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int decisionID = rs.getInt("decision_id");
                String decisionNo = rs.getString("decision_no");
                String decisionName = rs.getString("name");
                Date decisionDate = rs.getDate("decision_date");
                int creatorID = rs.getInt("creator_id");
                Boolean active = rs.getBoolean("is_active");
                UserDao userDao = new UserDao();
                decisionList.add(new Decision(decisionID, decisionNo, decisionName, decisionDate, creatorID, userDao.getUser(creatorID), active));
            }

        } catch (SQLException ex) {
            System.out.println("getDecision: " + ex.getMessage());
        } finally {
            closeConnection(connection, pstm, rs);
        }
        return decisionList;
    }

    public void updateDecisionActive(int id, String status) {
        String sql = "UPDATE g5_flm.decision SET is_active = ? WHERE decision_id = ?";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            if (status.equals("1")) {
                pstm.setInt(1, 1);
            } else {
                pstm.setInt(1, 0);
            }
            pstm.setInt(2, id);
            pstm.executeUpdate();

        } catch (Exception ex) {
            System.out.println("updateDecision: ");
        } finally {
            closeConnection(connection, pstm, rs);
        }
    }

    public Decision getDecisionByID(int id) {
        ArrayList<Curriculum> curriculumList = new ArrayList<>();
        ArrayList<Syllabus> syllabusList = new ArrayList<>();

        String curriculumQuery = "SELECT * FROM g5_flm.curriculum WHERE decision_id = ?";
        String syllabusQuery = "SELECT * FROM g5_flm.syllabus WHERE decision_id = ?";
        String decisionQuery = "SELECT * FROM g5_flm.decision WHERE decision_id = ?";

        try ( Connection connection = getConnection();  PreparedStatement curriculumStatement = connection.prepareStatement(curriculumQuery);  PreparedStatement syllabusStatement = connection.prepareStatement(syllabusQuery);  PreparedStatement decisionStatement = connection.prepareStatement(decisionQuery)) {

            decisionStatement.setInt(1, id);
            try ( ResultSet decisionResult = decisionStatement.executeQuery()) {
                if (decisionResult.next()) {
                    int decisionID = decisionResult.getInt("decision_id");
                    String decisionNo = decisionResult.getString("decision_no");
                    String decisionName = decisionResult.getString("name");
                    Date decisionDate = decisionResult.getDate("decision_date");
                    int creatorID = decisionResult.getInt("creator_id");
                    Boolean active = decisionResult.getBoolean("is_active");
                    UserDao userDao = new UserDao();
                    User creator = userDao.getUser(creatorID);

                    curriculumStatement.setInt(1, id);
                    try ( ResultSet curriculumResult = curriculumStatement.executeQuery()) {
                        while (curriculumResult.next()) {
                            curriculumList.add(new Curriculum(
                                    curriculumResult.getInt("curriculum_id"),
                                    curriculumResult.getString("code"),
                                    curriculumResult.getString("name"),
                                    curriculumResult.getString("description"),
                                    curriculumResult.getInt("total_credit"),
                                    curriculumResult.getBoolean("is_active")
                            ));
                        }
                    }

                    syllabusStatement.setInt(1, id);
                    try ( ResultSet syllabusResult = syllabusStatement.executeQuery()) {
                        while (syllabusResult.next()) {
                            syllabusList.add(new Syllabus(
                                    syllabusResult.getInt("syllabus_id"),
                                    syllabusResult.getString("name"),
                                    syllabusResult.getString("description"),
                                    syllabusResult.getBoolean("is_active"),
                                    syllabusResult.getInt("decision_id"),
                                    syllabusResult.getInt("subject_id")
                            ));
                        }
                    }

                    return new Decision(decisionID, decisionNo, decisionName, decisionDate, active, creator, curriculumList, syllabusList);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(SyllabusDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean updateDecision(Decision decision) {
        String sql = "UPDATE g5_flm.decision\n"
                + "SET decision_no = ?, name = ?, decision_date = ?, is_active = ? \n"
                + "WHERE decision_id = ? ";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, decision.getDecision_no());
            pstm.setString(2, decision.getDecision_name());
            pstm.setDate(3, decision.getDecision_date());
            pstm.setBoolean(4, decision.isIs_active());
            pstm.setInt(5, decision.getId());
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection(connection, pstm, rs);
        }

    }
    
    public int getNumberOfDecision() {
        try {
            String query = "select count(*) from g5_flm.decision";
            connection = getConnection();
            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getNumberOfUser: " + e.getMessage());
        }finally {
            closeConnection(connection, pstm, rs);
        }

        return 0;
    }
}
