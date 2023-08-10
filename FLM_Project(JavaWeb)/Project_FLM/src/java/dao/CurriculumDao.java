/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.BaseDao.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import model.*;

/**
 *
 * @author ADMIN
 */
public class CurriculumDao extends BaseDao {

    PreparedStatement ps;
    ResultSet rs;
    
    public static void main(String[] args) {
        CurriculumDao c = new CurriculumDao();
        List<Curriculum>  list = c.getAllCurriculum(1, true);
        for (Curriculum curriculum : list) {
            System.out.println(curriculum);
        }
    }

    public CurriculumDao() {

    }

    public Curriculum getCurriculumByID(int id) {

        Curriculum curriculum = null;
        String query = "SELECT * FROM Curriculum c INNER JOIN Decision d ON c.decision_id = d.decision_id where curriculum_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                curriculum = new Curriculum();
                curriculum.setCurriculumID(id);
                curriculum.setCode(rs.getString("code"));
                curriculum.setName(rs.getString("name"));
                curriculum.setDescription(rs.getString("description"));
                curriculum.setOwner(new UserDao().getUser(rs.getInt("owner_id")));
                curriculum.setTotalCredit(Integer.parseInt(rs.getString("total_credit")));
                Decision decision = new Decision();
                decision.setDecision_no(rs.getString("decision_no"));
                decision.setId(rs.getInt("decision_id"));
                String date = "";
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-YYYY");
                if (rs.getDate("decision_date") != null) {
                    date = f.format(rs.getDate("decision_date"));
                }
                decision.setDate(date);
                curriculum.setDecision(decision);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return curriculum;
    }

    public boolean checkCode(String no) {
        String sql = "SELECT * FROM curriculum "
                + "where code = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, no);
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
    
    public void addCuriculum(Curriculum curriculum) {

        String sql = "INSERT INTO `g5_flm`.`curriculum` ( `code`, `name`, `description`, owner_id) "
                + "VALUES (?, ?, ?, ?);";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, curriculum.getCode());
            ps.setString(2, curriculum.getName());
            ps.setString(3, curriculum.getDescription());
            ps.setInt(4, curriculum.getOwnerID());
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }

    }
    
    public int getNumberOfCurriculum(boolean check) {
        try {
            String query = "select count(*) from curriculum";
            if (check) {
                query += " where curriculum.is_active = 1";
            }
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getNumberOfCurriculum: " + e.getMessage());
        } finally {
            closeConnection(connection, ps, rs);
        }
        return 0;
    }

    public List<Curriculum> getAllCurriculum(int index, boolean check) {
        List<Curriculum> curList = new ArrayList<Curriculum>();
        String query = "select curriculum.*, decision.decision_id, decision_no, decision_date "
                + "from curriculum left join decision on curriculum.decision_id = decision.decision_id"
                + " limit ?, 5";
        if (check) {
            query = "select curriculum.*,decision.decision_id, decision_no, decision_date "
                    + "from curriculum left join decision on curriculum.decision_id = decision.decision_id"
                    + " where curriculum.is_active = 1 limit ?, 5";
        }
        try {
            UserDao uDao = new UserDao();
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                curList.add(new Curriculum(rs.getInt("curriculum_id"), rs.getString("code"), rs.getString("name"),
                        rs.getString("description"), rs.getInt("total_credit"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        uDao.getUser(rs.getInt("owner_id")), rs.getBoolean("is_active")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return curList;
    }

    public List<Curriculum> searchCurriculum(String key, boolean check) {
        List<Curriculum> curList = new ArrayList<Curriculum>();
        String query = "select curriculum.*,decision.decision_id, decision_no, decision_date \n"
                + "from curriculum left join decision on curriculum.decision_id = decision.decision_id\n"
                + "where (code like ? or curriculum.name like ?)";
        if (check) {
            query += " and curriculum.is_active = 1";
        }
        try {
            UserDao uDao = new UserDao();
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                curList.add(new Curriculum(rs.getInt("curriculum_id"), rs.getString("code"), rs.getString("name"),
                        rs.getString("description"), rs.getInt("total_credit"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        uDao.getUser(rs.getInt("owner_id")), rs.getBoolean("is_active")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return curList;
    }

    public int getCurriculumIdLast() {
        String query = "SELECT  curriculum_id FROM g5_flm.curriculum  ORDER BY curriculum_id DESC LIMIT 1";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getInt("curriculum_id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return 0;
    }

    public void addCuriculum(int curriculumId, String code, String name,
            String description, int decisionId, int total, int active, int oid) {

        String sql = "INSERT INTO `g5_flm`.`curriculum` (`curriculum_id`, `code`, `name`, `description`, `decision_id`, `total_credit`, `is_active`, owner_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, curriculumId);
            ps.setString(2, code);
            ps.setString(3, name);
            ps.setString(4, description);
            ps.setInt(5, decisionId);
            ps.setInt(6, total);
            ps.setInt(7, active);
            ps.setInt(8, oid);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }

    }

    public boolean updateCurriculumById(int id, String curriculumCode, String curriculumName, String curriculumDescription) {
        boolean f = false;
        String query = "update Curriculum c INNER JOIN Decision d ON \n"
                + "c.decision_id = d.decision_id set c.code = ?, c.name = ?"
                + ", c.description = ? where curriculum_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, curriculumCode);
            ps.setString(2, curriculumName);
            ps.setString(3, curriculumDescription);
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

    public void activateOrDeactiveCurriculum(int curId, int status) {
        String query = "update curriculum set is_active = ? where curriculum_id = ?";
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

    public void addCuriculumSubject(int idSub, int idSubGroup, int idCurri, int idUser) throws Exception {
        String query = ""
                + "INSERT INTO `curriculum_subject`\n"
                + "(`curriculum_id`,\n"
                + "`subject_id`,\n"
                + "`subject_group_id`,\n"
                + "`learning_order`)\n"
                + "VALUES ( ? , ? , ? , ? );";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, idCurri);
            ps.setInt(2, idSub);
            ps.setInt(3, idSubGroup);
            ps.setInt(4, idUser);
            ps.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public List<Curriculum> getAllCurriculum() {
        List<Curriculum> curList = new ArrayList<Curriculum>();
        String query = "select curriculum.*, decision_no, decision_date "
                + "from curriculum left join decision on curriculum.decision_id = decision.decision_id";
        try {
            UserDao uDao = new UserDao();
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                curList.add(new Curriculum(rs.getInt("curriculum_id"), rs.getString("code"), rs.getString("name"),
                        rs.getString("description"), rs.getInt("total_credit"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        uDao.getUser(rs.getInt("owner_id")), rs.getBoolean("is_active")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return curList;
    }

    public List<User> getAllOwner() {
        List<User> list = new ArrayList<User>();
        String query = "select distinct owner_id "
                + "from curriculum left join decision on curriculum.decision_id = decision.decision_id";
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

    public List<Curriculum> filterCurriculum(String key, String in_charge, String filterByStatus, boolean check) {
        List<Curriculum> curList = new ArrayList<Curriculum>();
        String query = "select curriculum.*,decision.decision_id, decision_no, decision_date \n"
                + "from curriculum left join decision on curriculum.decision_id = decision.decision_id\n"
                + "where (code like ? or curriculum.name like ?)";
        if (check) {
            query += " and curriculum.is_active = 1";
        }
        try {
            if (!in_charge.equals("0")) {
                query += " and owner_id = ?";
            }
            if (!filterByStatus.equals("-1")) {
                query += " and curriculum.is_active = ?";
            }
            UserDao uDao = new UserDao();
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            if (!in_charge.equals("0")) {
                ps.setString(3, in_charge);
            }
            if (!filterByStatus.equals("-1")) {
                if (!in_charge.equals("0")) {
                    ps.setString(4, filterByStatus);
                } else {
                    ps.setString(3, filterByStatus);
                }
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                curList.add(new Curriculum(rs.getInt("curriculum_id"), rs.getString("code"), rs.getString("name"),
                        rs.getString("description"), rs.getInt("total_credit"), new DecisionDao().getDecisionById(rs.getInt("decision_id")),
                        uDao.getUser(rs.getInt("owner_id")), rs.getBoolean("is_active")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return curList;
    }

    public boolean updateDecision(int curriculumId, int decisionId) {
        String query = "UPDATE g5_flm.curriculum\n"
                + "SET\n"
                + "decision_id = ?\n"
                + "WHERE curriculum_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, decisionId);
            ps.setInt(2, curriculumId);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public ArrayList<Curriculum> getAllCurriculumList() {
        ArrayList<Curriculum> curList = new ArrayList<Curriculum>();
        String query = "select * from curriculum ";
        try {
            UserDao uDao = new UserDao();
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                curList.add(new Curriculum(rs.getInt("curriculum_id"), rs.getString("code"), rs.getString("name"),
                        rs.getString("description"), rs.getInt("total_credit"), rs.getBoolean("is_active")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return curList;
    }
}
